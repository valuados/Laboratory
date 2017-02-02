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
@Table(name = "T_OFFER")
public class Offer implements Serializable{

    private final static Long serialVersionUID=6L;

    public Offer(Integer id, Integer categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_OFFER_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_CATEGORY_ID", nullable = false, length = 11,
            insertable = false, updatable = false)
    private Integer categoryId;

    @Column(name = "F_OFFER_NAME", nullable = false, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "F_OFFER_ID", insertable = false, updatable = false)
    private Collection<Component> components;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_OFFER_COMP_M2M_CH_CONT",
            joinColumns = {@JoinColumn(name = "F_OFFER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_CH_CONTAINER_ID")})
    private Collection<CharacteristicContainer> characteristics;

}
