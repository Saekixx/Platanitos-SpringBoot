package com.PlatanitosApiRest.api.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandarResponse<T> {
    private int status;
    private String message;
    private T data;
}
