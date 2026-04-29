package com.PlatanitosApiRest.api.DTO.Producto;

import lombok.Data;

@Data
public class ProductoRequestDTO {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Boolean isFavorite;
    private Boolean isActive;
    private Long idCategoria;
    private Long idMarca;
    private Long idTalla;
    private String imgURL;
}
