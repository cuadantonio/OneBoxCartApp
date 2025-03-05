package com.cuadantonio.OneBoxCartApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralProduct {
    private Long id;
    private String description;
    private Long amount;
}
