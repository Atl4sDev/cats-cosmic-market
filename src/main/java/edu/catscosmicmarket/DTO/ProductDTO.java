package edu.catscosmicmarket.DTO;

import edu.catscosmicmarket.validation.CosmicWordCheck;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class ProductDTO {
    Long id;
    @NotNull(message = "Product name cannot be null")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 characters")
    @CosmicWordCheck(message = "Product name must contain a cosmic term like 'planet', 'space', 'galaxy' or else")
    String name;
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0000001", message = "Price must be greater than 0")
    BigDecimal price;
    @NotNull(message = "Category ID cannot be null")
    Long categoryId;
    String characteristics;

}
