package net.guistraliote.zeus.income;

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
@Entity(name = "income")
public class Income {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "internal_user_id", nullable = false)
    private InternalUser internalUser;

    private BigDecimal amount;
    private String description;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
