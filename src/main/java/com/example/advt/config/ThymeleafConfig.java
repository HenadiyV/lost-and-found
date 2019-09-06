package com.example.advt.config;


import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *04.07.2019
 */
//
//@Configuration
//@AllArgsConstructor
//public class ThymeleafConfig {
//    private ApplicationContext applicationContext;
//
//    @Bean
//    public ViewResolver viewResolver(){
//        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//private SpringResourceTemplateResolver templateResolver(){
//    SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
//    templateResolver.setApplicationContext(this.applicationContext);
//    templateResolver.setPrefix("/templates/");
//    templateResolver.setSuffix(".html");
//    templateResolver.setTemplateMode(TemplateMode.HTML);
//    templateResolver.setCacheable(false);
//    return templateResolver;
//}
//@Bean
//    public SpringTemplateEngine templateEngine() {
//    SpringTemplateEngine templateEngine = new  SpringTemplateEngine();
//    templateEngine.setTemplateResolver(templateResolver());
//    templateEngine.setEnableSpringELCompiler(true);
//    return templateEngine;
//    }
//}