package edu.catscosmicmarket.service;

import edu.catscosmicmarket.DTO.ProductDTO;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(@Parameter Long id, @Parameter ProductDTO productDTO);
    void deleteProduct(Long id);
}