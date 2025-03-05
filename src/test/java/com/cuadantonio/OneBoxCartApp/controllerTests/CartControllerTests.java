package com.cuadantonio.OneBoxCartApp.controllerTests;

import com.cuadantonio.OneBoxCartApp.model.CartProduct;
import com.cuadantonio.OneBoxCartApp.model.GeneralProduct;
import com.cuadantonio.OneBoxCartApp.repository.GeneralProductRepository;
import com.cuadantonio.OneBoxCartApp.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartService cartService;

    @Autowired
    private GeneralProductRepository generalProductRepository;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateCartEndpoint() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/carts"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddProductToCartEndpoint() throws Exception {
        this.cartService.createCart();
        CartProduct cartProduct = new CartProduct("Manzanas",5L);
        this.generalProductRepository.save(new GeneralProduct(null,"Manzanas",20L));

        String cartProductJson = this.objectMapper.writeValueAsString(cartProduct);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/carts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartProductJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
