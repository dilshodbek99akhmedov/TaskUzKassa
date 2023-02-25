package uz.uzkassa.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.uzkassa.dto.company.CreateCompanyDto;
import uz.uzkassa.dto.company.UpdateCompanyDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.enums.Status;
import uz.uzkassa.repository.CompanyRepository;
import uz.uzkassa.service.CompanyService;
import uz.uzkassa.utils.SessionUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Dilshodbek Akhmedov, Thu 10:27 PM. 2/23/23
 */

@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Long create(CreateCompanyDto dto) {
        return companyRepository.save(
                Company.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .zipCode(dto.getZipCode())
                        .status(Status.ACTIVE)
                        .createdBy(SessionUtils.getSessionId())
                        .build()
        ).getId();
    }

    @Transactional
    @Override
    public String edit(UpdateCompanyDto dto) {
        Company company = get(dto.getId());
        companyRepository.save(updateCompanyParams(dto, company));
        return "Company updated";
    }

    private Company updateCompanyParams(UpdateCompanyDto dto, Company company) {
        if (Objects.nonNull(dto.getName()) && !dto.getName().isEmpty())
            company.setName(dto.getName());

        if (Objects.nonNull(dto.getAddress()) && !dto.getAddress().isEmpty())
            company.setAddress(dto.getAddress());

        if (Objects.nonNull(dto.getZipCode()) && !dto.getZipCode().isEmpty())
            company.setZipCode(dto.getZipCode());

        return company;
    }

    @Transactional
    @Override
    public String block(Long id) {
        Company company = get(id);
        company.setStatus(Status.BLOCK);
        companyRepository.save(company);
        return "Company blocked";
    }

    @Override
    public Company get(Long id) {
        Optional<Company> companyOptional = companyRepository.findByIdAndCreatedBy(id, SessionUtils.getSessionId());
        if (!companyOptional.isPresent())
            throw new RuntimeException("Company not found");

        return companyOptional.get();
    }
}
