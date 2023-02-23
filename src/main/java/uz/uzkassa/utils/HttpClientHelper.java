package uz.uzkassa.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dilshodbek Akhmedov, Thu 11:19 PM. 2/23/23
 */

@Configuration
public class HttpClientHelper {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
