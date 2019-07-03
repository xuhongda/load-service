package com.xu.loadservicewebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xuhongda on 2019/6/27
 * com.xu.loadservicewebsocket.config
 * load-service
 */
@Configuration
@EnableWebMvc
public class WebMvcConfigurerImpl implements WebMvcConfigurer {


    /**
     * 跨域支持 也可使用 CORS Filter {@see https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-cors}
     * <p>
     * <p>
     * Configure cross origin requests processing.
     *
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE")
                //.allowedHeaders("header1", "header2", "header3")
                //.exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/gid").setViewName("gid");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/websocket").setViewName("websocket");
        registry.addViewController("/refuel").setViewName("refuel-new");
    }
}
