package edu.catscosmicmarket.service;

import edu.catscosmicmarket.DTO.ProductDTO;
import edu.catscosmicmarket.domain.Product;
import edu.catscosmicmarket.mappers.ProductMapper;
import edu.catscosmicmarket.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        productDTO = ProductDTO.builder()
                .name("Sample Product")
                .price(BigDecimal.valueOf(100.0))
                .characteristics("Sample Description")
                .categoryId(1L)
                .build();

        product = Product.builder()
                .id(1L)
                .name("Sample Product")
                .price(BigDecimal.valueOf(100.0))
                .characteristics("Sample Description")
                .categoryId(1L)
                .build();

        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);
    }

    @Test
    void shouldCreateProduct() {
        ProductDTO result = productService.createProduct(productDTO);

        assertNotNull(result);
        assertEquals("Sample Product", result.getName());
        verify(productMapper).toEntity(productDTO);
        verify(productMapper).toDTO(product);
    }

    @Test
    void shouldRetrieveAllProducts() {
        when(productMapper.toProductDTOList(anyList())).thenReturn(List.of(productDTO));

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Sample Product", result.get(0).getName());
        verify(productMapper).toProductDTOList(anyList());
    }

    @Test
    void shouldFindProductById() {
        productService.createProduct(productDTO);

        ProductDTO foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals("Sample Product", foundProduct.getName());
        verify(productMapper, times(2)).toDTO(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        productService.createProduct(productDTO);

        productDTO = productDTO.toBuilder().name("Updated Product").price(BigDecimal.valueOf(150.0)).build();

        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        ProductDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(BigDecimal.valueOf(150.0), updatedProduct.getPrice());
        verify(productMapper, times(2)).toDTO(any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        productService.createProduct(productDTO);

        assertDoesNotThrow(() -> productService.deleteProduct(1L));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
        assertEquals("Product not found", exception.getMessage());
    }
}
