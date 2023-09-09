package uz.pdp.online.appwarehouse.payload;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.pdp.online.appwarehouse.entity.Input;
import uz.pdp.online.appwarehouse.entity.Product;

import java.util.Date;

@Data
public class InputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer inputId;
}
