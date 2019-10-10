package com.example.advt.config;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *04.06.2019
 */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
      registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///"+uploadPath+"/");
        registry.addResourceHandler("/static/static/**")
                .addResourceLocations("classpath:/static/static/");
//        registry.setOrder(Integer.MIN_VALUE);
//        registry.addResourceHandler("/favicon.ico")
//                .addResourceLocations("/")
//                .setCachePeriod(0);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    ///////////////////////////////
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
//        handler.setUseReferer(true);
//        return handler;
//    }
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                             Authentication authentication) throws ServletException, IOException {
//        String userTargetUrl = "/welcome.xhtml";
//        String adminTargetUrl = "/admin/welcome.xhtml";
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        if (roles.contains("ROLE_ADMIN")) {
//            getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
//        }
//        else if(roles.contains("ROLE_USER")) {
//            getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
//        }
//        else {
//            super.onAuthenticationSuccess(request, response, authentication);
//            return;
//        }
//    }
//
//}

//////////////////////////////////
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//      slr.setDefaultLocale(Locale.US);
//        return slr;
//    }
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    G:\Java\1_1_Kursovoy\advt\src\main\resources\static\static\img}
}

