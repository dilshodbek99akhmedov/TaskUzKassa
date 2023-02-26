package uz.uzkassa.service;

import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.entity.Company;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 PM. 2/23/23
 */
public interface CompanyService {
    /**
     * @param dto Parameters for creating a Company
     * @return Complete information about the created Company
     */
    Company create(CreateCompanyDto dto);

    /**
     * @param dto Parameters for editing the Company
     * @return The edited Company is returned
     */
    Company edit(UpdateCompanyDto dto);

    /**
     * @param id The id of the Company to be blocked
     * @return A message that the Company has been blocked
     */
    String block(Long id);

    /**
     * @param id The id of the Company to get
     * @return Company returned from DB
     */
    Company get(Long id);
}
