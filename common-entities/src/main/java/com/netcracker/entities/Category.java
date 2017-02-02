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
@Table(name = "T_CATEGORY")
public class Category implements Serializable{

    private final static Long serialVersionUID=1L;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_CATEGORY_ID", nullable = false, length = 11)
    private Integer id;

    @Column(name = "F_CATEGORY_NAME", nullable = false, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "F_CATEGORY_ID", nullable = false)
    private Collection<Offer> offers;
}
