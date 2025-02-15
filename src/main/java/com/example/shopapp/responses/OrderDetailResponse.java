package com.example.shopapp.responses;

import com.example.shopapp.models.OrderDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderID;

    @JsonProperty("product_id")
    private Long productID;

    private Float price;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;

    public static OrderDetailResponse fromOrderDetail(OrderDetail orderDetail){
        return OrderDetailResponse.builder()
            .id(orderDetail.getId())
            .orderID(orderDetail.getOrder().getId())
            .productID(orderDetail.getProduct().getId())
            .price(orderDetail.getPrice())
            .numberOfProducts(orderDetail.getNumberOfProducts())
            .totalMoney(orderDetail.getTotalMoney())
            .color(orderDetail.getColor())
            .build();
    }
}
