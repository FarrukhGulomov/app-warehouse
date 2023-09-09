package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private Double amount=0.0;
    @Column(nullable = false)
    private Double price=0.0;
    private Date expireDate;
    @ManyToOne
    private Input input;

    public InputProduct(Double amount) {
        this.amount = amount;
    }
}
