package uz.uzkassa.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.service.CompanyService;

import javax.validation.Valid;

/**
 * @author Dilshodbek Akhmedov, Thu 10:34 PM. 2/23/23
 */

@RestController
@PreAuthorize("hasRole('OWNER')")
public class CompanyController extends AbstractController<CompanyService> {
    public CompanyController(CompanyService service) {
        super(service);
    }

    @PostMapping(PATH + "/company")
    public ResponseEntity<DataDto<Company>> create(@Valid @RequestBody CreateCompanyDto dto) {

        Company company = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(company), HttpStatus.OK);
    }

    @PutMapping(PATH + "/company")
    public ResponseEntity<DataDto<Company>> edit(@Valid @RequestBody UpdateCompanyDto dto) {

        Company company = service.edit(dto);
        return new ResponseEntity<>(new DataDto<>(company), HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/block/{id}")
    public ResponseEntity<DataDto<String>> block(@PathVariable Long id) {

        String message = service.block(id);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping(PATH + "/company/{id}")
    public ResponseEntity<DataDto<Company>> get(@PathVariable Long id) {

        Company company = service.get(id);
        return new ResponseEntity<>(new DataDto<>(company), HttpStatus.OK);
    }

}
