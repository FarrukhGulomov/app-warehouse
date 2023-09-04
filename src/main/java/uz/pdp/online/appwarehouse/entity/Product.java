package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment photo;
    private String code;
    @ManyToOne
    private Measurement measurement;

}
