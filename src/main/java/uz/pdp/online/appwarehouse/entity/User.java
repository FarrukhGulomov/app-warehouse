package uz.pdp.online.appwarehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String password;
    private boolean active=true;
    @ManyToMany
    private Set<Warehouse> warehouses;  // codeni unique qilish uchun set ishlatdik list ga qancha bolsa qoshish mumkin
}
