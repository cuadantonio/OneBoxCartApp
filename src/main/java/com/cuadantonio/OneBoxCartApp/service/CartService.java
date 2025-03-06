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

    //Creates a new empty cart
    public Cart createCart(){
        Cart cart = new Cart();
        cart.setCartProducts(new ArrayList<>());
        cart.setCreatedAndUpdatedAt(LocalDateTime.now());
        return this.cartRepository.save(cart);
    }

    //Adds a product to a cart and updates its stock
    //First checks if the cart and the product exists
    //Then it check if there is enough amount
    //And then if the product is already in cart or not
    //If it is already in cart it updates its amount
    //If not it gets added like a new product
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
                CartProduct checkedCartProduct = this.checkProductInCart(cartProducts,cartProduct);
                if(checkedCartProduct == null){
                    cartProducts.add(cartProduct);
                    cart.setCartProducts(cartProducts);
                    cart.setCreatedAndUpdatedAt(LocalDateTime.now());
                    return this.cartRepository.save(cart);
                } else{
                    int productIndex = cartProducts.indexOf(checkedCartProduct);
                    cartProducts.get(productIndex).setAmount(checkedCartProduct.getAmount() + cartProductAmount);
                    cart.setCartProducts(cartProducts);
                    cart.setCreatedAndUpdatedAt(LocalDateTime.now());
                    return this.cartRepository.save(cart);
                }

            }
        }else if(cartOptional.isEmpty()){
            throw new RuntimeException("Cart not found");
        } else{
            throw new RuntimeException("General product not found");
        }
    }

    //Deletes a cart after 10 minutes of inactivity
    //It happens every 1 minute
    @Scheduled(fixedRate = 60000)
    public void deleteCartsAfter10Minutes(){
        LocalDateTime dateTimeTenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        List<Cart> carts = this.cartRepository.getAll();
        List<Cart> cartsCreatedTenMinutesAgoOrMore = carts.stream().filter(cart -> cart.getCreatedAndUpdatedAt().isBefore(dateTimeTenMinutesAgo)).toList();
        for (Cart cart : cartsCreatedTenMinutesAgoOrMore){
            Long cartId = cart.getId();
            this.cartRepository.deleteById(cartId);
        }
    }

    //Checks if a product already exists in a cart so it needs to update the amount
    public CartProduct checkProductInCart(List<CartProduct> cartProducts, CartProduct cartProduct){
        List<CartProduct> filteredCart = cartProducts.stream().filter(product -> product.getDescription().equals(cartProduct.getDescription())).toList();
        if (filteredCart.isEmpty()){
            return null;
        } else {
            return filteredCart.get(0);
        }
    }

}
