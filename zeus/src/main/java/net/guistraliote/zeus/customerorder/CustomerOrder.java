package net.guistraliote.zeus.customerorder;

import jakarta.persistence.*;
import lombok.*;
import net.guistraliote.zeus.customer.Customer;
import net.guistraliote.zeus.customerorder.enums.PaymentMethod;
import net.guistraliote.zeus.customerorder.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CustomerOrderItems> orderItems;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "paid_amount")
    private BigDecimal paidAmount;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
