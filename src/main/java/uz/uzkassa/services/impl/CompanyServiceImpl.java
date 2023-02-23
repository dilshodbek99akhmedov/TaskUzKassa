package uz.uzkassa.services.impl;

import org.springframework.stereotype.Service;
import uz.uzkassa.dtos.company.CreateCompanyDto;
import uz.uzkassa.dtos.company.CompanyDto;
import uz.uzkassa.dtos.company.UpdateCompanyDto;
import uz.uzkassa.services.CompanyService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:27 PM. 2/23/23
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    @Override
    public Long create(CreateCompanyDto dto) {

        return null;
    }

    @Override
    public void edit(UpdateCompanyDto dto) {

    }

    @Override
    public void block(Long id) {

    }

    @Override
    public CompanyDto get(Long id) {
        return null;
    }
}
