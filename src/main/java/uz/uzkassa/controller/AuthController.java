package uz.uzkassa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzkassa.dto.auth.RegisterDto;
import uz.uzkassa.dto.data.DataDto;
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
    public ResponseEntity<DataDto<String>> register(@RequestBody RegisterDto dto) {

        String string = service.register(dto);
        return new ResponseEntity<>(new DataDto<>(string), HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/auth/confirmation-email/{token}")
    public ResponseEntity<DataDto<String>> confirmation(@PathVariable String token) {

        String message = service.confirmation(token);
        return new ResponseEntity<>(new DataDto<>(message), HttpStatus.OK);
    }

}
