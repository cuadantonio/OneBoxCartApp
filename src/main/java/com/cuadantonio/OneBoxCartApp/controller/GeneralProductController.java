package com.cuadantonio.OneBoxCartApp.controller;

import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/generalProducts")
public class GeneralProductController {

    private final GeneralProductRepository generalProductRepository;

    public GeneralProductController(GeneralProductRepository generalProductRepository) {
        this.generalProductRepository = generalProductRepository;
    }

    //Endpoint to get all products
    @GetMapping("")
    public ResponseEntity<List<GeneralProduct>> getAll(){
        return ResponseEntity.ok(this.generalProductRepository.getAll());
    }

    //Endpoint to get a single product by its description
    @GetMapping("/{generalProductDescription}")
    public ResponseEntity<GeneralProduct> getByDescription(@PathVariable String generalProductDescription){
        Optional<GeneralProduct> generalProductOptional = this.generalProductRepository.getByDescription(generalProductDescription);
        if (generalProductOptional.isPresent()){
            return ResponseEntity.ok(generalProductOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Endpoint to create a new product
    @PostMapping("")
    public ResponseEntity<GeneralProduct> createProduct(@RequestBody GeneralProduct generalProduct){
        return ResponseEntity.ok(this.generalProductRepository.save(generalProduct));
    }

    //Endpoint to delete a product by its description
    @DeleteMapping("/{generalProductDescription}")
    public ResponseEntity<GeneralProduct> deleteProductByDescription(@PathVariable String generalProductDescription){
        Optional<GeneralProduct> generalProductOptional = this.generalProductRepository.getByDescription(generalProductDescription);
        if (generalProductOptional.isPresent()){
            return ResponseEntity.ok(this.generalProductRepository.deleteByDescription(generalProductDescription));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
