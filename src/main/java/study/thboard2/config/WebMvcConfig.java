package study.thboard2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.thboard2.common.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    //인터셉터
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = loginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/log**", "/reg**", "/idCheck/**", "/mod**", "/delAjax/**", "/download/**", "/reply/**", "/err**", "/css/**", "/assets/**", "/js/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
