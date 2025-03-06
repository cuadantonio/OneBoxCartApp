package com.cuadantonio.OneBoxCartApp.repository;

import com.cuadantonio.OneBoxCartApp.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CartRepository {

    //Used for carts storage when the app is being used
    private final Map<Long, Cart> carts = new ConcurrentHashMap<>();
    //Used to assign an id to the carts
    private final AtomicLong counter = new AtomicLong(1);

    //Gives back all the carts
    public List<Cart> getAll(){
        return new ArrayList<>(this.carts.values());
    }

    //Gives back a single cart by its id if it exists
    public Optional<Cart> getById(Long id){
        return Optional.ofNullable(this.carts.get(id));
    }

    //Saves a new cart or an already existing one if it is being updated
    public Cart save(Cart cart){
        if(cart.getId() == null){
            cart.setId(this.counter.getAndIncrement());
        }
        this.carts.put(cart.getId(), cart);
        return cart;
    }

    //Deletes the cart according to the id given
    public Cart deleteById(Long id){
        if(carts.containsKey(id)){
            return this.carts.remove(id);
        } else{
            throw new RuntimeException("The cart does not exist");
        }

    }
}
