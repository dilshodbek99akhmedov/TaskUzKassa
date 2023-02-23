package uz.uzkassa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.uzkassa.dtos.currency.NbuCurrencyRateDto;
import uz.uzkassa.services.CurrencyService;

/**
 * @author Dilshodbek Akhmedov, Fri 12:10 AM. 2/24/23
 */

@RestController
public class CurrencyController extends AbstractController<CurrencyService> {
    protected CurrencyController(CurrencyService service) {
        super(service);
    }

    @GetMapping(PATH + "/currency/{code}")
    public ResponseEntity<NbuCurrencyRateDto> get(@PathVariable String code) {
        NbuCurrencyRateDto dto = service.getCurrencyRate(code);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
