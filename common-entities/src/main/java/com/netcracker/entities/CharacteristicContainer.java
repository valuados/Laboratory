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
@Table(name = "T_CHARACTERISTIC_CONTAINER")
public class CharacteristicContainer implements Serializable{

    private final static Long serialVersionUID=3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_CH_CONTAINER_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_CH_CONTAINER_NAME", nullable = false, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "F_CH_CONTAINER_ID", nullable = false)
    private Collection<CharacteristicComponent> components;
}
