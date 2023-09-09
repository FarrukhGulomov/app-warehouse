package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne(optional = false)
    private Category category;
    @OneToOne
    private Attachment photo;
    private String code;
    @ManyToOne(optional = false)
    private Measurement measurement;

}
