package uz.uzkassa.helper;

import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.entity.User;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:33 AM. 2/26/23
 */

public class DataHelper {
    public static List<Company> getCompaniesToIntialize() {
        Company companyDtoOne = Company.builder()
                .name("CompanyOne")
                .address("Tashkent 1")
                .zipCode("1111111")
                .status(Status.ACTIVE)
                .createdBy(1L)
                .users(new ArrayList<>())
                .build();

        Company companyDtoTwo = Company.builder()
                .name("CompanyTwo")
                .address("Tashkent 2")
                .zipCode("2222222")
                .status(Status.BLOCK)
                .createdBy(1L)
                .users(new ArrayList<>())
                .build();
        return Arrays.asList(companyDtoOne, companyDtoTwo);
    }

    public static User getUsersToIntialize() {
        return User.builder()
                .username("userOne")
                .password("123123")
                .email("dilshodbeka404@gmail.com")
                .role(Role.OWNER)
                .company(null)
                .status(Status.ACTIVE)
                .build();
    }
}
