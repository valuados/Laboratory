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
@Table(name = "T_SALES_ORDER")
public class SalesOrder implements Serializable{

    private final static Long serialVersionUID=9L;

    @Id
    @Column(name = "F_SALES_ORDER_ID", nullable = false, length = 11)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "F_SALES_ORDER_ID")
    private Collection<Order> orders;

}
