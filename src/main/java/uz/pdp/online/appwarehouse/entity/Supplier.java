package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Supplier extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;

}
