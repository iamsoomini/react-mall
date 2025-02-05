package org.jrock.mallapi.config;

import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new LocalDateFormatter());
    }


    // addMapping(괄호안의 경로에 cors 연결)
    // allowedOrigins(괄호안의 url 접속 허용)
    // allowedMethods(괄호안의 메서드 접속 허용)
    // options는 pre-flight 처럼 미리 찔러볼때의 메서드임 중요.
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .maxAge(500)
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                .allowedOrigins("*");


    }
}
