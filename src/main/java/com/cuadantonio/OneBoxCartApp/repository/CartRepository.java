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

    private final Map<Long, Cart> carts = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Cart> getAll(){
        return new ArrayList<>(this.carts.values());
    }

    public Optional<Cart> getById(Long id){
        return Optional.ofNullable(this.carts.get(id));
    }

    public Cart save(Cart cart){
        if(cart.getId() == null){
            cart.setId(this.counter.getAndIncrement());
        }
        this.carts.put(cart.getId(), cart);
        return cart;
    }
    public Cart deleteById(Long id){
        return this.carts.remove(id);
    }
}
