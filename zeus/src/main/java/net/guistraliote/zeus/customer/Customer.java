package net.guistraliote.zeus.customer;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.address.Address;
import net.guistraliote.zeus.customer.enums.Gender;
import net.guistraliote.zeus.customerorder.CustomerOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CustomerOrder> orders;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
