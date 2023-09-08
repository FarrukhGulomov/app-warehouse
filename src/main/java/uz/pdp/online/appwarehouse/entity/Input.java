package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp date;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Currency currency;

    private String factureNumber;
    @Column(unique = true,nullable = false)
    private String code;

}
