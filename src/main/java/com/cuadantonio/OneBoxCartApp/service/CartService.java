package com.cuadantonio.OneBoxCartApp.service;

import com.cuadantonio.OneBoxCartApp.model.Cart;
import com.cuadantonio.OneBoxCartApp.model.CartProduct;
import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.CartRepository;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final GeneralProductRepository generalProductRepository;

    public CartService(CartRepository cartRepository, GeneralProductRepository generalProductRepository) {
        this.cartRepository = cartRepository;
        this.generalProductRepository = generalProductRepository;
    }

    public Cart createCart(){
        Cart cart = new Cart();
        cart.setCartProducts(new ArrayList<>());
        cart.setCreatedAt(LocalDateTime.now());
        return this.cartRepository.save(cart);
    }

    public Cart addProductToCart(Long cartId, CartProduct cartProduct){
        Optional<Cart> cartOptional = this.cartRepository.getById(cartId);
        Optional<GeneralProduct> generalProductOptional = this.generalProductRepository.getByDescription(cartProduct.getDescription());
        if(cartOptional.isPresent() && generalProductOptional.isPresent()){
            Cart cart = cartOptional.get();
            GeneralProduct generalProduct = generalProductOptional.get();
            Long generalProductAmount = generalProduct.getAmount();
            Long cartProductAmount = cartProduct.getAmount();

            if(cartProductAmount > generalProductAmount){
                throw new RuntimeException("Not enough amount in stock");
            }else{
                generalProduct.setAmount(generalProductAmount - cartProductAmount);
                this.generalProductRepository.save(generalProduct);
                List<CartProduct> cartProducts = cart.getCartProducts();
                cartProducts.add(cartProduct);
                cart.setCartProducts(cartProducts);
                return this.cartRepository.save(cart);
            }
        }else if(cartOptional.isEmpty()){
            throw new RuntimeException("Cart not found");
        } else{
            throw new RuntimeException("General product not found");
        }
    }

    @Scheduled(fixedRate = 60000)
    public void deleteCartsAfter10Minutes(){
        LocalDateTime dateTimeTenMinitesAgo = LocalDateTime.now().minusMinutes(10);
        List<Cart> carts = this.cartRepository.getAll();
        List<Cart> cartsCreatedTenMinutesAgoOrMore = carts.stream().filter(cart -> cart.getCreatedAt().isBefore(dateTimeTenMinitesAgo)).toList();
        for (Cart cart : cartsCreatedTenMinutesAgoOrMore){
            Long cartId = cart.getId();
            this.cartRepository.deleteById(cartId);
        }
    }

}
