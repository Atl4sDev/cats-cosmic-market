package edu.catscosmicmarket.domain;


import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class Product {
    Long id;
    String name;
    BigDecimal price;
    Long categoryId;
    String characteristics;
}
