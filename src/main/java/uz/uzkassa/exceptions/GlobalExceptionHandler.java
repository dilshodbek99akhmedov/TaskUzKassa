package uz.uzkassa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import uz.uzkassa.dto.data.AppErrorDto;
import uz.uzkassa.dto.data.DataDto;

@RestController
@ControllerAdvice("uz.uzkassa")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<DataDto> handleRuntimeException(RuntimeException exception, WebRequest request) {

        return new ResponseEntity<>(
                DataDto.builder()
                        .success(false)
                        .error(
                                new AppErrorDto(exception.getMessage(),
                                        request,
                                        HttpStatus.BAD_REQUEST
                                )
                        )
                        .build(), HttpStatus.OK);
    }

}
