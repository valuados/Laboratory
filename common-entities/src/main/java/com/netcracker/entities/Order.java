package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_ORDER")
public class Order implements Serializable{

    private final static Long serialVersionUID = 7L;

    @Id
    @Column(name = "F_ORDER_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_SALES_ORDER_ID", nullable = false, length = 11)
    private Integer salesOrderId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "F_OFFER_ID", nullable = false)
    private Offer offer;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_ORDER_ID")
    private Collection<OrderComponent> orderComponents;

}
