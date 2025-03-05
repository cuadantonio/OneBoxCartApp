package com.cuadantonio.OneBoxCartApp.controller;

import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import com.cuadantonio.OneBoxCartApp.service.GeneralProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generalProducts")
public class GeneralProductController {

    private final GeneralProductRepository generalProductRepository;
    private final GeneralProductService generalProductService;

    public GeneralProductController(GeneralProductRepository generalProductRepository, GeneralProductService generalProductService) {
        this.generalProductRepository = generalProductRepository;
        this.generalProductService = generalProductService;
    }

    @GetMapping("")
    public ResponseEntity<List<GeneralProduct>> getAll(){
        return ResponseEntity.ok(this.generalProductRepository.getAll());
    }

    @PostMapping("")
    public ResponseEntity<GeneralProduct> createProduct(@RequestBody GeneralProduct generalProduct){
        return ResponseEntity.ok(this.generalProductService.createProduct(generalProduct));
    }

    @DeleteMapping("/{generalProductDescription}")
    public ResponseEntity<GeneralProduct> deleteProductByDescription(@PathVariable String generalProductDescription){
        return ResponseEntity.ok(this.generalProductRepository.deleteByDescription(generalProductDescription));
    }
}
