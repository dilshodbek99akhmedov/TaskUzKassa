package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.dto.user.UserDto;
import uz.uzkassa.services.UserService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:32 PM. 2/23/23
 */

@RestController
public class UserController extends AbstractController<UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @PostMapping(PATH + "/user/create")
    public ResponseEntity<String> create(@RequestBody CreateUserDto dto) {
        Long userId = service.create(dto);
        return new ResponseEntity<>("User created user_id = " + userId, HttpStatus.OK);
    }

    @PostMapping(PATH + "/user/edit")
    public ResponseEntity<String> edit(@RequestBody UpdateUserDto dto) {
        service.edit(dto);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @GetMapping(PATH + "/user/block/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id) {
        service.block(id);
        return new ResponseEntity<>("User blocked", HttpStatus.OK);
    }

    @GetMapping(PATH + "/user/get/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        UserDto dto = service.get(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
