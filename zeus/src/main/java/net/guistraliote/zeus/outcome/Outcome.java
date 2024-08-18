package net.guistraliote.zeus.outcome;

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
@Entity(name = "outcome")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal amount;
    private String description;

    @Column(name = "out_date")
    private LocalDateTime outDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
