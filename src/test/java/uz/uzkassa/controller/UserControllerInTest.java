package uz.uzkassa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.entity.User;
import uz.uzkassa.service.UserService;

import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:27 AM. 2/26/23
 */

class UserControllerInTest {
    @Mock
    UserService service;
    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        ResponseEntity<DataDto<User>> result = userController.create(new CreateUserDto("username", "password", "email", Long.valueOf(1)));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testEdit() {
        ResponseEntity<DataDto<User>> result = userController.edit(new UpdateUserDto(Long.valueOf(1), "username", "password", "email"));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testBlock() {
        ResponseEntity<DataDto<String>> result = userController.block(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGet() {
        ResponseEntity<DataDto<User>> result = userController.get(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetAll() {
        ResponseEntity<DataDto<List<User>>> result = userController.getAll(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme