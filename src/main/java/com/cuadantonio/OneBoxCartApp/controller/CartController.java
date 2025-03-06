package com.cuadantonio.OneBoxCartApp.controller;

import com.cuadantonio.OneBoxCartApp.model.Cart;
import com.cuadantonio.OneBoxCartApp.model.CartProduct;
import com.cuadantonio.OneBoxCartApp.repository.CartRepository;
import com.cuadantonio.OneBoxCartApp.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;

    public CartController(CartRepository cartRepository, CartService cartService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    //Endpoint to get all the carts
    @GetMapping("")
    public ResponseEntity<List<Cart>> getCarts(){
        return ResponseEntity.ok(this.cartRepository.getAll());
    }

    //Endpoint to get a single cart by its id
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable long cartId) {
        Optional<Cart> cartOptional = this.cartRepository.getById(cartId);
        if (cartOptional.isPresent()) {
            return ResponseEntity.ok(cartOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint to create an empty new cart
    @PostMapping("")
    public ResponseEntity<Cart> createCart() {
        return ResponseEntity.ok(this.cartService.createCart());
    }

    //Endpoint to delete a cart by its id
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Cart> deleteCart(@PathVariable long cartId) {
        Optional<Cart> cartOptional = this.cartRepository.getById(cartId);
        if (cartOptional.isPresent()) {
            return ResponseEntity.ok(this.cartRepository.deleteById(cartId));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint to add a given product to a cart by its id
    @PostMapping("/{cartId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable long cartId, @RequestBody CartProduct cartProduct) {
        return ResponseEntity.ok(this.cartService.addProductToCart(cartId, cartProduct));
    }
}
