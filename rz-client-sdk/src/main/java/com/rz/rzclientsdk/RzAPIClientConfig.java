package com.rz.rzclientsdk;

import com.rz.rzclientsdk.client.HttpClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
@ConfigurationProperties("rz.client")
@ComponentScan
public class RzAPIClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public HttpClient httpClient() {
       return new HttpClient(accessKey,secretKey);
    }

}
