package net.guistraliote.zeus.address;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.customer.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    private String street;
    private Integer number;
    private String complement;
    private String neighborhood;
    private String city;
    private String uf;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

    @Column(name = "work_space")
    private String workSpace;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
