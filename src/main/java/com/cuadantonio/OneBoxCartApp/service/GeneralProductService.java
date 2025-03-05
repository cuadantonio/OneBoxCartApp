package com.cuadantonio.OneBoxCartApp.service;

import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralProductService {

    private final GeneralProductRepository generalProductRepository;

    public GeneralProductService(GeneralProductRepository generalProductRepository) {
        this.generalProductRepository = generalProductRepository;
    }

    public GeneralProduct createProduct(GeneralProduct generalProduct){
        return this.generalProductRepository.save(generalProduct);
    }
}
