package com.netcracker.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_CHARACTERISTIC_ITEM")
public class CharacteristicItem implements Serializable{

    private final static Long serialVersionUID = 4L;

    @Id
    @Column(name = "F_CH_ITEM_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_ORDER_COMPONENT_ID", nullable = false, length = 11)
    private Integer orderComponentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_CH_CONTAINER_ID")
    private CharacteristicContainer characteristicContainer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_CH_COMPONENT_ID")
    private CharacteristicComponent characteristicComponent;
}
