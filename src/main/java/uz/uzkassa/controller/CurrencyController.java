package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.uzkassa.dto.currency.NbuCurrencyRateDto;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.service.CurrencyService;

/**
 * @author Dilshodbek Akhmedov, Fri 12:10 AM. 2/24/23
 */

@RestController
public class CurrencyController extends AbstractController<CurrencyService> {
    protected CurrencyController(CurrencyService service) {
        super(service);
    }

    @GetMapping(PATH + "/currency/{code}")
    public ResponseEntity<DataDto<NbuCurrencyRateDto>> get(@PathVariable String code) {

        NbuCurrencyRateDto dto = service.getCurrencyRate(code);
        return new ResponseEntity<>(new DataDto<>(dto), HttpStatus.OK);
    }

}
