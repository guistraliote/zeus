package net.guistraliote.zeus.balance;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.internaluser.InternalUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "internal_user_id", nullable = false)
    private InternalUser internalUser;

    private String name;

    @Column(name = "current_amount")
    private BigDecimal currentAmount;

    @Column(name = "previous_amount")
    private BigDecimal previousAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
