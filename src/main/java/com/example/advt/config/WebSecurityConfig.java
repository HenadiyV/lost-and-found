//package com.example.advt.config;
//
//import com.example.advt.domain.UserSocial;
//import com.example.advt.repos.UserDetailsRepo;
//import com.example.advt.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.NestedConfigurationProperty;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.filter.CompositeFilter;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.Filter;
///*
// *@autor Hennadiy Voroboiv
// *@email henadiyv@gmail.com
// *12.08.2019
// */
//@Configuration
//@EnableWebSecurity
//@EnableOAuth2Client
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    OAuth2ClientContext oauth2ClientContext;
////    @Bean
////    public AuthenticationSuccessHandler successHandler() {
////        return new MyCustomLoginSuccessHandler();
////    }
//
////    @Autowired
////    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService);
////    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf()
////               //.ignoringAntMatchers("/advt/**")
////                .disable()
////                //, .and() "/contact", "/about", "/page-register" "/static/static/img/homepage-slider/**", "/static/static/img/service-icon/**",.ignoringAntMatchers("/test/category","/admin/category/{id}", "/admin_city/city",  "/admin_city/city/{id}" , "/admin_status/status",  "/admin_status/status/{id}", "/admin_role/role",  "/admin_role/role/{id}")
////
////                .authorizeRequests()
////
////                .antMatchers("/**", "/static/static/**", "/static/static/img/flags/**", "/img/**","/advt/**"
////
////                ).permitAll().anyRequest()
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////
////                .successHandler(successHandler())
////
////
////                .permitAll()
////                .and()
////
////
////                .logout()
////
////                .invalidateHttpSession(false)
////                .logoutSuccessUrl("/")
////
////                .permitAll();
//
////        http.antMatcher("/**")
////                .authorizeRequests()
////                .antMatchers( "/login**","/**", "/static/static/**", "/static/static/img/flags/**", "/img/**","/advt/**").permitAll()
////                .anyRequest().authenticated()
////                .and().exceptionHandling()
////                .and()
////                .csrf().disable();
//
//        // @formatter:off
//        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/error**").permitAll().anyRequest()
//                .authenticated().and().exceptionHandling()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
//                .logoutSuccessUrl("/").permitAll().and().csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//        // @formatter:on
//    }
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.userDetailsService(userService)
////                .passwordEncoder(NoOpPasswordEncoder.getInstance());
////
////    }
////    @Bean
////    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
////        return map -> {
////            return new UserSocial();
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
////             };
////    }
////}
//
////@EnableResourceServer
////protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        // @formatter:off
////        http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
////        // @formatter:on
////    }
////}
//
//
//
//    @Bean
//    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }
//
//    @Bean
//    @ConfigurationProperties("github")
//    public ClientResources github() {
//        return new ClientResources();
//    }
//
//    @Bean
//    @ConfigurationProperties("facebook")
//    public ClientResources facebook() {
//        return new ClientResources();
//    }
//
//    private Filter ssoFilter() {
//        CompositeFilter filter = new CompositeFilter();
//        List<Filter> filters = new ArrayList<>();
//        filters.add(ssoFilter(facebook(), "/login/facebook"));
//        filters.add(ssoFilter(github(), "/login/github"));
//        filter.setFilters(filters);
//        return filter;
//    }
//
//    private Filter ssoFilter(ClientResources client, String path) {
//        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
//        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
//        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
//                client.getClient().getClientId());
//        tokenServices.setRestTemplate(oAuth2RestTemplate);
//        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
//        return oAuth2ClientAuthenticationFilter;
//    }
//
//}
//
//class ClientResources {
//
//    @NestedConfigurationProperty
//    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
//
//    @NestedConfigurationProperty
//    private ResourceServerProperties resource = new ResourceServerProperties();
//
//    public AuthorizationCodeResourceDetails getClient() {
//        return client;
//    }
//
//    public ResourceServerProperties getResource() {
//        return resource;
//    }
//}
////@Bean
////@Override
////public UserDetailsService userDetailsService() {
////    UserDetails user =
////            User.withDefaultPasswordEncoder()
////                    .username("user")
////                    .password("password")
////                    .roles("USER")
////                    .build();
////
////    return new InMemoryUserDetailsManager(user);
////}
