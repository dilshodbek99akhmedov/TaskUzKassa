package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.company.CompanyDto;
import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.services.CompanyService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:34 PM. 2/23/23
 */

@RestController
public class CompanyController extends AbstractController<CompanyService> {
    public CompanyController(CompanyService service) {
        super(service);
    }

    @PostMapping(PATH + "/company/create")
    public ResponseEntity<String> create(@RequestBody CreateCompanyDto dto) {
        Long companyId = service.create(dto);
        return new ResponseEntity<>("Company created company id = " + companyId, HttpStatus.OK);
    }

    @PostMapping(PATH + "/company/edit")
    public ResponseEntity<String> edit(@RequestBody UpdateCompanyDto dto) {
        service.edit(dto);
        return new ResponseEntity<>("Company updated", HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/block/{id}")
    public ResponseEntity<String> block(@PathVariable Long id) {
        service.block(id);
        return new ResponseEntity<>("Company blocked", HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/get/{id}")
    public ResponseEntity<CompanyDto> get(@PathVariable Long id) {
        CompanyDto dto = service.get(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
