package uz.uzkassa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import uz.uzkassa.dto.currency.NbuCurrencyRateDto;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.service.CurrencyService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:25 AM. 2/26/23
 */

class CurrencyControllerInTest {
    @Mock
    CurrencyService service;
    @InjectMocks
    CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(service);
    }

    @Test
    void testGet() {
        ResponseEntity<DataDto<NbuCurrencyRateDto>> result = currencyController.get("code");
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme