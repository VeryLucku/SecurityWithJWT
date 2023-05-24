package com.edu.security.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "cakes")
public class CakeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cake_id")
    private UUID id;

    @Column(name = "cake_name")
    private String name;

    @Column(name = "cake_cost")
    private Integer cost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cake")
    private List<CakeIngredientEntity> types;

}
