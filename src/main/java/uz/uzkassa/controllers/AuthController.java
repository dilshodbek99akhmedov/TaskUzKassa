package uz.uzkassa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dtos.auth.LoginDto;
import uz.uzkassa.dtos.auth.RegisterDto;
import uz.uzkassa.services.AuthService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:44 PM. 2/23/23
 */

@RestController
public class AuthController extends AbstractController<AuthService> {

    protected AuthController(AuthService service) {
        super(service);
    }


    @PostMapping(value = PATH + "/auth/sign-up")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {

        String string = service.register(dto);

        return new ResponseEntity<>(string, HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/auth/confirmation-email/{token}")
    public ResponseEntity<String> confirmation(@PathVariable String token) {

        String message = service.confirmation(token);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = PATH + "/auth/sign-in")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {

        String string = service.login(dto);

        return new ResponseEntity<>(string, HttpStatus.OK);
    }


}
