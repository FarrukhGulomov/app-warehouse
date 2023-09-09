package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;
@Getter
@Setter
@Entity
public class Category extends AbsEntity {

    @ManyToOne
    private Category parentCategory;

}
