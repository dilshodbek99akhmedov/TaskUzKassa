package uz.uzkassa.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.uzkassa.dtos.currency.NbuCurrencyRateDto;
import uz.uzkassa.services.CurrencyService;

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
        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<NbuCurrencyRateDto[]> response = restTemplate.getForEntity(url, NbuCurrencyRateDto[].class);
        NbuCurrencyRateDto[] currencyResponse = response.getBody();
        return currencyResponse[0];
    }
}
