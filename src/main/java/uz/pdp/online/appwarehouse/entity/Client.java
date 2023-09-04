package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client extends AbsEntity {
    @Column(unique = true,nullable = false)
    private String phoneNumber;

}
