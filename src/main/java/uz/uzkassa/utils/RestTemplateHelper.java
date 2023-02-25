package uz.uzkassa.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Dilshodbek Akhmedov, Thu 11:19 PM. 2/23/23
 */

@Configuration
public class RestTemplateHelper {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // Add one interceptor like in your example, except using anonymous class.
        restClient.setInterceptors(Collections.singletonList(
                (request, body, execution) -> execution.execute(request, body)
        ));
        return restClient;
    }
}
