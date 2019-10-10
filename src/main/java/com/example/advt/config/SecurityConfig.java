package com.example.advt.config;

import com.example.advt.repos.UserRepository;
import com.example.advt.service.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

//*
// *@autor Hennadiy Voroboiv
// *@email henadiyv@gmail.com
// *25.08.2019
// */
@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private UserRepository userRepo;

    @Bean
    PasswordEncoder passwordEncoder()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new MyCustomLoginSuccessHandler();
    }
@Bean
public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
    FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
}
    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
     filters.add(ssoFilter(google(), "/login/google"));
 filters.add(ssoFilter(twitter(), "/login/twitter"));
        filters.add(ssoFilter(facebook(), "/login/facebook"));
       // filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
//                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        tokenServices.setUserRepository(userRepo);
        tokenServices.setPasswordEncoder(passwordEncoder);
        return filter;
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }
    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }@Bean
    @ConfigurationProperties("twitter")
    public ClientResources twitter() {
        return new ClientResources();
    }

    class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }

//    @Bean
//    public AuthoritiesExtractor authoritiesExtractor(OAuth2RestOperations template) {
//        return map -> {
//            String url = (String) map.get("organizations_url");
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> orgs = template.getForObject(url, List.class);
//            if (orgs.stream()
//                    .anyMatch(org -> "spring-projects".equals(org.get("login")))) {
//                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
//            }
//            throw new BadCredentialsException("Not in Spring Projects origanization");
//        };
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
           //.antMatcher("/**")
            //,"/user", .and() "/contact", "/about", "/page-register" "/static/static/img/homepage-slider/**", "/static/static/img/service-icon/**",.ignoringAntMatchers("/test/category","/admin/category/{id}", "/admin_city/city",  "/admin_city/city/{id}" , "/admin_status/status",  "/admin_status/status/{id}", "/admin_role/role",  "/admin_role/role/{id}")

            .authorizeRequests()
//                ,"/advt"
            .antMatchers("/test","/testVue","/*","/login**", "/static/static/**","/view/**"
                    ,"/static/static/fonts","/activate/*","/sendSimpleEmail"
//                    , "/static/static/img/flags/**", "/static/static/img/logo/",
                    , "/img/**", "/register","/webjars/**", "/error**")
                .permitAll().anyRequest()
                .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")

        .successHandler(successHandler())
                .failureUrl("/login?error")

            .permitAll()
            .and()


            .logout()

           // .invalidateHttpSession(false)
            .logoutSuccessUrl("/")

            .permitAll()
                .and()
                .csrf()

                //.ignoringAntMatchers("/advt/**")
               .disable();
//.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

     //   http
////                .authorizeRequests()
////                .antMatchers("/resources/**", "/", "/login**", "/registration").permitAll()
////                .anyRequest().authenticated()
////                .and().formLogin().loginPage("/login")
////                .defaultSuccessUrl("/notes").failureUrl("/login?error").permitAll()
////                .and().logout().logoutSuccessUrl("/").permitAll();

        http
                .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }

}
/////////////////////////////////////////////////////////
//private Filter ssoFilter() {
//
//    CompositeFilter filter = new CompositeFilter();
//    List<Filter> filters = new ArrayList<>();
//
//    OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
//    OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
//    facebookFilter.setRestTemplate(facebookTemplate);
//    UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId());
//    tokenServices.setRestTemplate(facebookTemplate);
//    facebookFilter.setTokenServices(tokenServices);
//    filters.add(facebookFilter);
//
//    OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
//    OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
//    githubFilter.setRestTemplate(githubTemplate);
//    tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId());
//    tokenServices.setRestTemplate(githubTemplate);
//    githubFilter.setTokenServices(tokenServices);
//    filters.add(githubFilter);
//
//    filter.setFilters(filters);
//    return filter;
//
//}

//    @Bean
//    @ConfigurationProperties("google.client")
//    public AuthorizationCodeResourceDetails google()
//    {
//        return new AuthorizationCodeResourceDetails();
//    }
//
//    @Bean
//    @ConfigurationProperties("google.resource")
//    public ResourceServerProperties googleResource()
//    {
//        return new ResourceServerProperties();
//    }
//
//    @Bean
//    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter)
//    {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(oAuth2ClientContextFilter);
//        registration.setOrder(-100);
//        return registration;
//    }
//    private Filter ssoFilter()
//    {
//        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
//        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
//        googleFilter.setRestTemplate(googleTemplate);
//        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
//        tokenServices.setRestTemplate(googleTemplate);
//        googleFilter.setTokenServices(tokenServices);
//        tokenServices.setUserRepo(userRepo);
//        tokenServices.setPasswordEncoder(passwordEncoder);
//        return googleFilter;
//    }

//    @Bean
////    @ConfigurationProperties("facebook.client")
////    public AuthorizationCodeResourceDetails facebook() {
////        return new AuthorizationCodeResourceDetails();
////    }
////    @Bean
////    @ConfigurationProperties("facebook.resource")
////    public ResourceServerProperties facebookResource() {
////        return new ResourceServerProperties();
////    }

//        @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepo) {
//        return map -> {
//            return new User();
//////            String id = (String) map.get("sub");
//////
//////            UserSocial user = userDetailsRepo.findById(id).orElseGet(() -> {
//////                UserSocial newUser = new UserSocial();
//////
//////                newUser.setId(id);
//////                newUser.setName((String) map.get("name"));
//////                newUser.setEmail((String) map.get("email"));
//////                newUser.setGender((String) map.get("gender"));
//////                newUser.setLocale((String) map.get("locale"));
//////                newUser.setUserpic((String) map.get("picture"));
////
////                //return usr;
////         //   });
////
////           // user.setLastVisit(LocalDateTime.now());
////
////            //return userDetailsRepo.save(user);
////       // };
//     };
//     }