package edu.catscosmicmarket.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class OrderDTO {
    Long id;
    LocalDateTime orderTime;
    List<ProductDTO> products;
    @NotNull
    @Past
    String orderDetails;
}
