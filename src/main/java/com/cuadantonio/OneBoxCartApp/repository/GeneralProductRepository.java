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

    private final Map<String, GeneralProduct> generalProducts = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<GeneralProduct> getAll(){
        return new ArrayList<>(this.generalProducts.values());
    }

    public Optional<GeneralProduct> getByDescription(String description){
        return Optional.ofNullable(this.generalProducts.get(description));
    }

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
        return this.generalProducts.remove(description);
    }

}
