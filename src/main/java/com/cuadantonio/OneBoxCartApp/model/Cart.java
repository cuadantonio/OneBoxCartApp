package com.cuadantonio.OneBoxCartApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Long id;
    private List<CartProduct> cartProducts;
    private LocalDateTime createdAndUpdatedAt;
}
