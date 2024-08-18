package net.guistraliote.zeus.brand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome da marca deve ser preenchido.")
    private String name;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
