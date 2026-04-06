package sn.isi.l3gl.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.service.ProductService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void shouldListProducts() throws Exception {
        Product product = Product.builder()
                .id(1L)
                .name("Laptop")
                .description("Portable")
                .price(500000.0)
                .quantity(3)
                .build();

        when(productService.listProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product input = Product.builder()
                .name("Mouse")
                .description("Wireless")
                .price(15000.0)
                .quantity(10)
                .build();

        Product created = Product.builder()
                .id(2L)
                .name("Mouse")
                .description("Wireless")
                .price(15000.0)
                .quantity(10)
                .build();

        when(productService.createProduct(any(Product.class))).thenReturn(created);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Mouse"));
    }
}
