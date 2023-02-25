package uz.uzkassa.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.uzkassa.dto.currency.NbuCurrencyRateDto;
import uz.uzkassa.services.CurrencyService;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Dilshodbek Akhmedov, Fri 12:02 AM. 2/24/23
 */

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Value("${nbu.currency.url}")
    private String url;

    private final RestTemplate restTemplate;

    public CurrencyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NbuCurrencyRateDto getCurrencyRate(String code) {
        // Fetch JSON response as NbuCurrencyRateDto[] wrapped in ResponseEntity
        ResponseEntity<NbuCurrencyRateDto[]> response = restTemplate.getForEntity(url, NbuCurrencyRateDto[].class);
        // todo check exception
        return Arrays.stream(Objects.requireNonNull(response.getBody()))
                .filter(rate -> rate.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Currency not found"));
    }
}
