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
     * @param dto Parameters for creating a User
     * @return Complete information about the created User
     */
    User create(CreateUserDto dto);

    /**
     * @param dto Parameters for editing the User
     * @return The edited User is returned
     */
    User edit(UpdateUserDto dto);

    /**
     * @param id The id of the User to be blocked
     * @return A message that the User has been blocked
     */
    String block(Long id);

    /**
     * @param id The id of the User to get
     * @return User returned from DB
     */
    User get(Long id);

    /**
     * @param companyId company id si whose users are being taken
     * @return Company users found in DB
     */
    List<User> getAll(Long companyId);
}
