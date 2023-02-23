package uz.uzkassa.services;

import uz.uzkassa.dtos.user.CreateUserDto;
import uz.uzkassa.dtos.user.UpdateUserDto;
import uz.uzkassa.dtos.user.UserDto;

/**
 * @author Dilshodbek Akhmedov, Thu 10:23 PM. 2/23/23
 */
public interface UserService {
    /**
     * @param dto
     * @return
     */
    Long create(CreateUserDto dto);

    /**
     * @param dto
     */
    void edit(UpdateUserDto dto);

    /**
     * @param id
     */
    void block(Long id);

    /**
     * @param id
     * @return
     */
    UserDto get(Long id);
}
