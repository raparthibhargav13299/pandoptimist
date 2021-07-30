//package com.stackroute.usermanagementservice1.config;
//
//
//import com.stackroute.usermanagementservice1.jwtfilter.JwtFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JwtConfig {    /*
// *  Create a bean for FilterRegistrationBean.
// *  1. Register the JwtFilter
// *  2. add URL pattern for all requests so that any request for
// *     that URL pattern will be intercepted by the filter
// */
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        FilterRegistrationBean filter = new FilterRegistrationBean();
//        filter.setFilter(new JwtFilter());
//        filter.addUrlPatterns("/api/v2/*");
//        return filter;
//    }
//}