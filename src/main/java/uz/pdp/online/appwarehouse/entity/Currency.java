package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import uz.pdp.online.appwarehouse.entity.template.AbsEntity;

import java.util.Objects;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Currency extends AbsEntity {


}
