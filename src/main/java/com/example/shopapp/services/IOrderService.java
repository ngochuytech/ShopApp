package com.example.shopapp.services;

import java.util.List;

import com.example.shopapp.dtos.OrderDTO;
import com.example.shopapp.models.Order;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO) throws Exception;

    Order getOrder(Long id);

    Order updateOrder(Long id, OrderDTO orderDTO) throws Exception;

    void deleteOrder(Long id);

    List<Order> findByUserId(Long userId);
}
