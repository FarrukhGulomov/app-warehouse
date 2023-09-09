package uz.pdp.online.appwarehouse.payload;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.online.appwarehouse.entity.Warehouse;

@Getter
@Setter
public class OutputDto {
    private Integer warehouseId;
    private Integer currencyId;
    private String factureNumber;
    private Integer clientId;

}
