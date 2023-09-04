package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.appwarehouse.entity.template.AbstractEntity;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbstractEntity {

}
