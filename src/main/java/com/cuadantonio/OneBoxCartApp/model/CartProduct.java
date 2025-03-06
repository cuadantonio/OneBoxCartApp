package com.cuadantonio.OneBoxCartApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Class created to control the proudcts that are added to a cart
public class CartProduct {
    private String description;
    private Long amount;
}