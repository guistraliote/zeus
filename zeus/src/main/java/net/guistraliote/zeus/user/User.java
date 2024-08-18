package net.guistraliote.zeus.user;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.balance.Balance;
import net.guistraliote.zeus.income.Income;
import net.guistraliote.zeus.outcome.Outcome;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Balance> balances;

    @OneToMany
            (mappedBy = "user",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    private List<Income> incomes;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Outcome> outcomes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
