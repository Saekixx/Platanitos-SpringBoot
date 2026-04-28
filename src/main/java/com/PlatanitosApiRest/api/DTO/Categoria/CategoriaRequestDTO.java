package com.PlatanitosApiRest.api.DTO.Categoria;

import lombok.Getter;
import lombok.Setter;

public class CategoriaRequestDTO {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private Long idSeccion;
}
