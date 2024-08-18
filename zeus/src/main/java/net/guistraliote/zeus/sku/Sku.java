package net.guistraliote.zeus.sku;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "sku")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "sku")
    private Product product;

    @Column(name = "sku_code")
    private String skuCode;

    private BigDecimal cost;
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "critical_quantity")
    private Integer criticalQuantity;

    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

