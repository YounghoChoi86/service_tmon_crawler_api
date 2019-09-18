package com.tmon.crawler.config;

import com.tmon.crawler.util.AlphabetComparator;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000); // 읽기시간초과, ms
        factory.setConnectTimeout(3000); // 연결시간초과, ms

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(20)
                .setMaxConnPerRoute(15)
                .build();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }
}
