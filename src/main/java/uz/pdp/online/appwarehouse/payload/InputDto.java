package uz.pdp.online.appwarehouse.payload;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class InputDto  {

    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;

}
