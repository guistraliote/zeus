package net.guistraliote.zeus.balance;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.user.User;

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
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user")
    @Column(name = "user_id")
    private User userId;

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
