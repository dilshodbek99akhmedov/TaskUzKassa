package uz.uzkassa.services;

import uz.uzkassa.dtos.company.CreateCompanyDto;
import uz.uzkassa.dtos.company.CompanyDto;
import uz.uzkassa.dtos.company.UpdateCompanyDto;

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
    void edit(UpdateCompanyDto dto);

    /**
     * @param id
     */
    void block(Long id);

    /**
     * @param id
     * @return
     */
    CompanyDto get(Long id);
}
