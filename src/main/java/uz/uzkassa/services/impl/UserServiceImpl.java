package uz.uzkassa.services.impl;

import org.springframework.stereotype.Service;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.dto.user.UserDto;
import uz.uzkassa.services.UserService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:28 PM. 2/23/23
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Long create(CreateUserDto dto) {
        return null;
    }

    @Override
    public void edit(UpdateUserDto dto) {

    }

    @Override
    public void block(Long id) {

    }

    @Override
    public UserDto get(Long id) {
        return null;
    }
}
