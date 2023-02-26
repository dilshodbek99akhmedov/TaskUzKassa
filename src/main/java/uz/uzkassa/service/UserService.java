package uz.uzkassa.service;

import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.entity.User;

import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 PM. 2/23/23
 */
public interface UserService {
    /**
     * @param dto
     * @return
     */
    User create(CreateUserDto dto);

    /**
     * @param dto
     */
    User edit(UpdateUserDto dto);

    /**
     * @param id
     */
    String block(Long id);

    /**
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * @return
     */
    List<User> getAll(Long companyId);
}
