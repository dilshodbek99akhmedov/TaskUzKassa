package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.services.CompanyService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:34 PM. 2/23/23
 */

@RestController
@PreAuthorize("hasRole('OWNER')")
public class CompanyController extends AbstractController<CompanyService> {
    public CompanyController(CompanyService service) {
        super(service);
    }

    @PostMapping(PATH + "/company/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody CreateCompanyDto dto) {

        Long companyId = service.create(dto);
        return new ResponseEntity<>(new DataDto<>("company_id = " + companyId), HttpStatus.OK);
    }

    @PostMapping(PATH + "/company/edit")
    public ResponseEntity<DataDto<String>> edit(@RequestBody UpdateCompanyDto dto) {

        String message = service.edit(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/block/{id}")
    public ResponseEntity<DataDto<String>> block(@PathVariable Long id) {

        String message = service.block(id);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/get/{id}")
    public ResponseEntity<DataDto<Company>> get(@PathVariable Long id) {

        Company company = service.get(id);
        return new ResponseEntity<>(new DataDto<>(company), HttpStatus.OK);
    }

}
