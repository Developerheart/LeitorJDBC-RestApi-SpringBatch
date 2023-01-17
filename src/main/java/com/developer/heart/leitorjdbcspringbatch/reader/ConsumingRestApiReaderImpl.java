package com.developer.heart.leitorjdbcspringbatch.reader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumingRestApiReaderImpl {

    @Bean
    public ConsumingRestApiReader itemReader(Environment environment, RestTemplate restTemplate) {
        return new ConsumingRestApiReader(environment.getRequiredProperty("rest.api.url"), restTemplate);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
