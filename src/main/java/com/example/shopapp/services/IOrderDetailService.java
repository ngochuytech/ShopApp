package com.example.shopapp.services;

import java.util.List;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.models.OrderDetail;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    OrderDetail getOrderDetail(Long id) throws Exception;

    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailDTO) throws Exception;

    void deleteById(Long id);

    List<OrderDetail> findByOrderID(Long orderId);
}
