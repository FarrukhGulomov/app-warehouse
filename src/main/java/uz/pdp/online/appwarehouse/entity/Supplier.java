package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String phoneNumber;


}
