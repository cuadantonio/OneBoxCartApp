package com.cuadantonio.OneBoxCartApp.repository;

import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GeneralProductRepository {
    //Used for products storage when the app is being used
    private final Map<String, GeneralProduct> generalProducts = new ConcurrentHashMap<>();
    //Used to assign an id to the carts
    private final AtomicLong counter = new AtomicLong(1);

    //Gives back all the products
    public List<GeneralProduct> getAll(){
        return new ArrayList<>(this.generalProducts.values());
    }

    //Gives back a product according to its description
    public Optional<GeneralProduct> getByDescription(String description){
        return Optional.ofNullable(this.generalProducts.get(description));
    }

    //Stores a new product or an already existing one when updating it
    public GeneralProduct save(GeneralProduct generalProduct){
        if(generalProduct.getId() == null && !this.generalProducts.containsKey(generalProduct.getDescription())){
            generalProduct.setId(this.counter.getAndIncrement());
        } else {
            generalProduct.setId(this.generalProducts.get(generalProduct.getDescription()).getId());
        }
        this.generalProducts.put(generalProduct.getDescription(), generalProduct);
        return generalProduct;
    }

    public GeneralProduct deleteByDescription(String description){
        if (this.generalProducts.containsKey(description)){
            return this.generalProducts.remove(description);
        }else {
            throw new RuntimeException("The product does not exist");
        }

    }

}
