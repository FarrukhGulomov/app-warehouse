package uz.pdp.online.appwarehouse.payload;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uz.pdp.online.appwarehouse.entity.Warehouse;

import java.util.Set;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Set<Integer> warehousesId;
}
