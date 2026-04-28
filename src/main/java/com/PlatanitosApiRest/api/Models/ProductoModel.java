package com.PlatanitosApiRest.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto")
public class ProductoModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false) @Getter @Setter
    private String nombre;

    @Column(nullable = false) @Getter @Setter
    private String descripcion;

    @Column(nullable = false) @Getter @Setter
    private Double precio;

    @Column @Getter @Setter
    private Integer stock;

    @Column @Getter @Setter
    private Boolean isFavorite;

    @Column @Getter @Setter
    private Boolean isActive;

    @Column @Getter @Setter
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    @Getter @Setter
    private CategoriaModel idCategoria;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    @Getter @Setter
    private MarcaModel idMarca;

    @ManyToOne
    @JoinColumn(name = "idTalla")
    @Getter @Setter
    private TallaModel idTalla;
}
