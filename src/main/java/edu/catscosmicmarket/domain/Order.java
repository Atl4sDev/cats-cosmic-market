package edu.catscosmicmarket.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
@Value
@Builder(toBuilder = true)
public class Order {
    Long id;
    List<Product> products;
    LocalDateTime orderTime;
    String orderDetails;
}
