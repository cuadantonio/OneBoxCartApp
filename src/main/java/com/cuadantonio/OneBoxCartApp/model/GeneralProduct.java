package com.cuadantonio.OneBoxCartApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Class created to control the stock of a product when created
public class GeneralProduct {
    private Long id;
    private String description;
    private Long amount;
}
