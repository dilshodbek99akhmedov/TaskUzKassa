package uz.uzkassa.service;

import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.entity.Company;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 PM. 2/23/23
 */
public interface CompanyService {
    /**
     * @param dto Parameters for creating a company
     * @return Complete information about the created company
     */
    Company create(CreateCompanyDto dto);

    /**
     * @param dto
     * @return
     */
    Company edit(UpdateCompanyDto dto);

    /**
     * @param id
     */
    String block(Long id);

    /**
     * @param id
     * @return
     */
    Company get(Long id);
}
