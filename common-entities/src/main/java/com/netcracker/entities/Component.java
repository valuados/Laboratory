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
@Table(name = "T_COMPONENT")
public class Component implements Serializable{

    private final static Long serialVersionUID=5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_COMPONENT_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_OFFER_ID", nullable = false, length = 11,
            insertable = false, updatable = false)
    private Integer parentOfferId;

    @Column(name = "F_COMPONENT_NAME", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_OFFER_COMP_M2M_CH_CONT",
            joinColumns = {@JoinColumn(name = "F_COMPONENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_CH_CONTAINER_ID")})
    private Collection<CharacteristicContainer> characteristics;

}

