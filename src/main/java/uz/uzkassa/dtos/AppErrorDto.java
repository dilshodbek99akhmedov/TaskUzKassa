package uz.uzkassa.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Dilshodbek Akhmedov, Fri 10:53 AM. 2/24/23
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrorDto {
    private Timestamp timestamp;
    private Integer status;
    private String code;
    private String path;
    private String message;

    public AppErrorDto(String message, HttpStatus httpStatus) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
        this.message = message;
    }

    @Builder
    public AppErrorDto(HttpStatus status, String message,String path) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.code = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    public AppErrorDto(String message) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.message = message;
    }

}
