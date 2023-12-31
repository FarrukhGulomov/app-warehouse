package uz.pdp.online.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Result {

    private String message;
    private boolean success;
    private Object object;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
