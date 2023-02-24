package uz.uzkassa.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author Dilshodbek Akhmedov, Fri 10:52 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto<T> implements Serializable {
    protected T data;

    protected AppErrorDto error;

    protected boolean success;

    private Long totalCount;

    public DataDto(boolean success) {
        this.success = success;
    }

    public DataDto(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDto(AppErrorDto error) {
        this.error = error;
        this.success = false;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }
}
