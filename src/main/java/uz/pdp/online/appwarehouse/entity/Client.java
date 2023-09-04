package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbstractEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client extends AbstractEntity {
    @Column(unique = true,nullable = false)
    private String phoneNumber;

}
