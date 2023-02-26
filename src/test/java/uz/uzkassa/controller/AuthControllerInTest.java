package uz.uzkassa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import uz.uzkassa.dto.auth.RegisterDto;
import uz.uzkassa.dto.data.DataDto;
import uz.uzkassa.service.AuthService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:30 AM. 2/26/23
 */

class AuthControllerInTest {
    @Mock
    AuthService service;
    @InjectMocks
    AuthController authController;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        ResponseEntity<DataDto<String>> result = authController.register(new RegisterDto("username", "password", "email"));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testConfirmation() {
        ResponseEntity<DataDto<String>> result = authController.confirmation("token");
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme