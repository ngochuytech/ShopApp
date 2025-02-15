package com.example.shopapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopapp.dtos.OrderDetailDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.OrderDetail;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.OrderDetailRepository;
import com.example.shopapp.repositories.OrderRepository;
import com.example.shopapp.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        Order order = orderRepository.findById(orderDetailDTO.getOrderID())
            .orElseThrow(() -> new DataNotFoundException("Cannot find Order with id: " + orderDetailDTO.getOrderID()));
        Product product = productRepository.findById(orderDetailDTO.getProductID())
            .orElseThrow(() -> new DataNotFoundException("Cannot find Product with id: "+ orderDetailDTO.getProductID()));

        OrderDetail orderDetail = OrderDetail.builder()
            .order(order)
            .product(product)
            .numberOfProducts(orderDetailDTO.getNumberOfProducts())
            .price(orderDetailDTO.getPrice())
            .totalMoney(orderDetailDTO.getTotalMoney())
            .color(orderDetailDTO.getColor())
            .build();

        return orderDetailRepository.save(orderDetail); 
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws Exception {
        return orderDetailRepository.findById(id).orElseThrow(() ->
            new DataNotFoundException("Cannot get orderDetail with id: "+ id));
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws Exception {
        // Tim xem order detail co ton tai khong
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("Cannot find OrderDetail with id: " + id));
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderID())
        .orElseThrow(() -> new DataNotFoundException("Cannot find Order with id: " + orderDetailDTO.getOrderID()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductID())
            .orElseThrow(() -> new DataNotFoundException("Cannot find Product with id: "+ orderDetailDTO.getProductID()));
        
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderID(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
    
}
