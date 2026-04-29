package com.PlatanitosApiRest.api.DTO.Categoria;

import lombok.Data;

@Data
public class CategoriaRequestDTO {
    private String nombre;
    private String descripcion;
    private Long idSeccion;
}
