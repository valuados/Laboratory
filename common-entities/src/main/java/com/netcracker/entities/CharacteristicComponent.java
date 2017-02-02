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
@Table(name = "T_CHARACTERISTIC_COMPONENT")
public class CharacteristicComponent implements Serializable{

    private final static Long serialVersionUID=2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_CH_COMPONENT_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_CH_CONTAINER_ID", nullable = false, length = 11,
            insertable = false, updatable = false)
    private Integer containerId;

    @Column(name = "F_CH_COMPONENT_VALUE", nullable = false, length = 100)
    private String value;

}
