package net.guistraliote.zeus.outcome;

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
@Entity(name = "outcome")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "internal_user_id", nullable = false)
    private InternalUser internalUser;

    private BigDecimal amount;
    private String description;

    @Column(name = "out_date")
    private LocalDateTime outDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
