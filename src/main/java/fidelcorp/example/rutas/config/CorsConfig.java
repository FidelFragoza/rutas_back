package fidelcorp.example.rutas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsConfig {

    @Value("${FRONT_ORIGIN}")
    private String frontOrigin;
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
             @Override
             public void addCorsMappings(CorsRegistry registry) {
                 
                 registry.addMapping("/api/ciudades/**")
                         .allowedOrigins(frontOrigin)
                         .allowedMethods("GET")
                         .allowedHeaders("*")
                         .allowCredentials(true);
                 
                 registry.addMapping("/api/choferes/**")
                         .allowedOrigins(frontOrigin)
                         .allowedMethods("GET", "POST", "PUT", "DELETE")
                         .allowedHeaders("*")
                         .allowCredentials(true);
                 
                 registry.addMapping("/api/rutas/**")
                         .allowedOrigins(frontOrigin)
                         .allowedMethods("GET", "POST", "PUT", "DELETE")
                         .allowedHeaders("*")
                         .allowCredentials(true);
             }
         };
    }
}
