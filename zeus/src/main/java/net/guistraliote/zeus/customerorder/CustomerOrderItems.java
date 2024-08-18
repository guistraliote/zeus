package net.guistraliote.zeus.customerorder;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "customer_order_items")
public class CustomerOrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_order_id", nullable = false)
    private CustomerOrder order;


}
