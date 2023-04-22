//package com.thanh.blog.security;
//
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Role;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.core.GrantedAuthorityDefaults;
//
//@Configuration
//@EnableMethodSecurity(securedEnabled = true)
//public class MethodSecurityConfig {
//    @Bean
//    static MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
//        handler.setTrustResolver(myCustomTrustResolver);
//        return handler;
//    }
//
//    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("ROLE_");
//    }
//
//    @Bean
//    @Role(com.thanh.blog.user.Role.ROLE_ADMIN)
//    Advisor postFilterAuthorizationMethodInterceptor() {
//        return new PostFilterAuthorizationMethodInterceptor();
//    }
//}
