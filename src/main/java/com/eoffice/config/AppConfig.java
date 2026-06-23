package com.eoffice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {

            request.getHeaders().set(
                    "User-Agent",
                    "EOffice/1.0 (contact: admin@company.com)"
            );

            return execution.execute(request, body);
        });

        return restTemplate;
    }
}