package uz.uzkassa.services;

import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.entity.Company;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 PM. 2/23/23
 */
public interface CompanyService {
    /**
     * @param dto
     * @return
     */
    Long create(CreateCompanyDto dto);

    /**
     * @param dto
     */
    String edit(UpdateCompanyDto dto);

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
