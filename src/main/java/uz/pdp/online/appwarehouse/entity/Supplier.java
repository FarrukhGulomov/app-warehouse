package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbstractEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier extends AbstractEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;

}
