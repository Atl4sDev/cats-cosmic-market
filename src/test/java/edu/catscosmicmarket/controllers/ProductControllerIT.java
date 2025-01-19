package edu.catscosmicmarket.controllers;

import edu.catscosmicmarket.DTO.ProductDTO;
import edu.catscosmicmarket.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductControllerIT {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Galaxy")
                .price(BigDecimal.valueOf(100.0))
                .categoryId(1L)
                .characteristics("Cosmic product")
                .build();

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    public void testGetAllProducts() {
        ProductDTO product1 = ProductDTO.builder()
                .id(1L)
                .name("Comet")
                .price(BigDecimal.valueOf(50.0))
                .categoryId(1L)
                .characteristics("Fast")
                .build();
        ProductDTO product2 = ProductDTO.builder()
                .id(2L)
                .name("Star")
                .price(BigDecimal.valueOf(150.0))
                .categoryId(2L)
                .characteristics("Bright")
                .build();

        List<ProductDTO> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Planet")
                .price(BigDecimal.valueOf(200.0))
                .categoryId(3L)
                .characteristics("Solid")
                .build();

        when(productService.getProductById(1L)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO updatedProductDTO = ProductDTO.builder()
                .id(1L)
                .name("Updated Star")
                .price(BigDecimal.valueOf(120.0))
                .categoryId(2L)
                .characteristics("Updated Bright")
                .build();

        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(updatedProductDTO);

        ResponseEntity<ProductDTO> response = productController.updateProduct(1L, updatedProductDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProductDTO, response.getBody());
        verify(productService, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }
}
