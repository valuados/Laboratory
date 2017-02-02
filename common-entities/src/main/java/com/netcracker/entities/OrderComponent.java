package com.netcracker.entities;

import com.netcracker.entities.Component;
import com.netcracker.entities.CharacteristicItem;
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
@Table(name = "T_ORDER_COMPONENT")
public class OrderComponent implements Serializable{

    private final static Long serialVersionUID = 8L;

    @Id
    @Column(name = "F_ORDER_COMPONENT_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_ORDER_ID", nullable = false, length = 11)
    private Integer orderId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "F_COMPONENT_ID")
    private Component component;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "F_ORDER_COMPONENT_ID")
    private Collection<CharacteristicItem> characteristicItems;

}
