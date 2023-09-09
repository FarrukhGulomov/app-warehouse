package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client extends AbsEntity {
    @Column(unique = true,nullable = false)
    private String phoneNumber;

}
