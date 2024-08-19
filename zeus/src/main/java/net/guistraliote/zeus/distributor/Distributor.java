package net.guistraliote.zeus.distributor;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.distributororder.DistributorOrder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "distributor")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "distributor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DistributorOrder> orders;

    private String name;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
