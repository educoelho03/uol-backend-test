package br.com.testbackend.javaspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${avengers}")
    private String addressBaseUrl;

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder().baseUrl(addressBaseUrl);
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder){
        return webClientBuilder.build();
    }
}
