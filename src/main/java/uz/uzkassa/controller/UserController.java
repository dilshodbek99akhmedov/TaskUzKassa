package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.entity.User;
import uz.uzkassa.service.UserService;

import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:32 PM. 2/23/23
 */

@RestController
@PreAuthorize("hasRole('OWNER')")
public class UserController extends AbstractController<UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @PostMapping(PATH + "/user/create")
    public ResponseEntity<DataDto<String>> create(@RequestBody CreateUserDto dto) {

        Long userId = service.create(dto);
        return new ResponseEntity<>(new DataDto<>("user_id = " + userId), HttpStatus.OK);
    }

    @PostMapping(PATH + "/user/edit")
    public ResponseEntity<DataDto<String>> edit(@RequestBody UpdateUserDto dto) {

        String message = service.edit(dto);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping(PATH + "/user/block/{id}")
    public ResponseEntity<DataDto<String>> block(@PathVariable Long id) {

        String message = service.block(id);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

    @GetMapping(PATH + "/user/get/{id}")
    public ResponseEntity<DataDto<User>> get(@PathVariable Long id) {

        User user = service.get(id);
        return new ResponseEntity<>(new DataDto<>(user), HttpStatus.OK);
    }

    @GetMapping(PATH + "/user/get/company-employees/{companyId}")
    public ResponseEntity<DataDto<List<User>>> getAll(@PathVariable Long companyId) {

        List<User> users = service.getAll(companyId);
        return new ResponseEntity<>(new DataDto<>(users, users.size()), HttpStatus.OK);
    }

}
