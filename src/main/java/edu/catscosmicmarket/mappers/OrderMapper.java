package edu.catscosmicmarket.mappers;

import edu.catscosmicmarket.DTO.OrderDTO;
import edu.catscosmicmarket.domain.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    Order toOrderEntity(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOList(List<Order> ordersList);
    List<Order> toOrderEntityList(List<OrderDTO> ordersDTOList);


}

