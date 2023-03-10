package uz.uzkassa.service;

import uz.uzkassa.dto.currency.NbuCurrencyRateDto;

/**
 * @author Dilshodbek Akhmedov, Fri 12:01 AM. 2/24/23
 */
public interface CurrencyService {
    /**
     * @param code Currency code
     * @return Currency value from nbu API
     */
    NbuCurrencyRateDto getCurrencyRate(String code);
}
