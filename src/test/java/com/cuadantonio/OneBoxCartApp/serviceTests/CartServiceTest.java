package com.cuadantonio.OneBoxCartApp.serviceTests;

import com.cuadantonio.OneBoxCartApp.model.Cart;
import com.cuadantonio.OneBoxCartApp.model.CartProduct;
import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import com.cuadantonio.OneBoxCartApp.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @Autowired
    private GeneralProductRepository generalProductRepository;

    @Test
    public void testCreateCart(){
        Long cartId = 2L;
        Cart cartMock = new Cart(cartId,new ArrayList<>(), LocalDateTime.now());

        Cart cartResult = this.cartService.createCart();
        System.out.println(cartResult);
        Assertions.assertNotNull(cartResult);
        Assertions.assertEquals(cartId, cartResult.getId());
        Assertions.assertEquals(cartMock.getCartProducts(),cartResult.getCartProducts());
        Assertions.assertEquals(cartMock.getCreatedAt(),cartResult.getCreatedAt());
    }

    @Test
    public void testAddProductToCart(){
        Long id = 1L;
        CartProduct productMock = new CartProduct("Manzanas",5L);
        Cart cartMock = new Cart(id,new ArrayList<>(), LocalDateTime.now());
        cartMock.getCartProducts().add(productMock);

        Cart cart = this.cartService.createCart();
        GeneralProduct generalProduct = new GeneralProduct(null,"Manzanas",20L);
        this.generalProductRepository.save(generalProduct);
        CartProduct cartProduct = new CartProduct("Manzanas",5L);

        Cart cartResult = this.cartService.addProductToCart(cart.getId(), cartProduct);

        Assertions.assertNotNull(cartResult);
        Assertions.assertEquals(id,cartResult.getId());
        Assertions.assertEquals(cartMock.getCartProducts(),cartResult.getCartProducts());
        Assertions.assertEquals(15L,this.generalProductRepository.getByDescription("Manzanas").get().getAmount());
    }
}
