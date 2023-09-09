package uz.pdp.online.appwarehouse.payload;

import jakarta.persistence.*;
import lombok.Data;
import uz.pdp.online.appwarehouse.entity.Output;
import uz.pdp.online.appwarehouse.entity.Product;

import java.util.Date;

@Data
public class OutputProductDto {


    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
