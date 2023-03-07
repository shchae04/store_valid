package start.intro;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import start.intro.web.intercepter.LogInfoInterceptor;
import start.intro.web.intercepter.LoginAuthInterceptor;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * Log Interceptor
         */
        registry.addInterceptor(new LogInfoInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*.ico", "/error");

        /**
         * Login Interceptor
         */
        registry.addInterceptor(new LoginAuthInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","intro/add","/login","/css/**","*.ico","/error");

        /**
         * Admin Interceptor
         */


    }
}
