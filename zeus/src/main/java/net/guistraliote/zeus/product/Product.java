package net.guistraliote.zeus.product;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.brand.Brand;
import net.guistraliote.zeus.category.Category;
import net.guistraliote.zeus.sku.Sku;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private Sku sku;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    private BigDecimal weight;
    private String dimensions;
    private String ean;
    private Integer volume;

    @Column(name = "volume_unit")
    private String volumeUnit;

    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

