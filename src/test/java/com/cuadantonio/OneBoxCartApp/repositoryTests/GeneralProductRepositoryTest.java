package com.cuadantonio.OneBoxCartApp.repositoryTests;

import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneralProductRepositoryTest {

    @Autowired
    private GeneralProductRepository generalProductRepository;

    @Test
    public void testSaveProduct(){
        Long id = 1L;
        GeneralProduct generalProductMock = new GeneralProduct(id,"Manzanas",20L);

        GeneralProduct generalProductResult = this.generalProductRepository.save(new GeneralProduct(null,"Manzanas",20L));

        Assertions.assertNotNull(generalProductResult);
        Assertions.assertEquals(id,generalProductResult.getId());
        Assertions.assertEquals(generalProductMock.getDescription(),generalProductResult.getDescription());
        Assertions.assertEquals(generalProductMock.getAmount(),generalProductResult.getAmount());

    }
}
