package hello.hellospring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//cors 오류 제어
@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  //3000포트 허용
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")  //모든옵션에 대해서
                .allowCredentials(true);
    }
}
