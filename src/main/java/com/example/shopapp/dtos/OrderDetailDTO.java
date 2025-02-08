package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be > 0")
    private Long orderID;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be > 0")
    private Long productID;

    @Min(value = 0, message = "Price must be > 0")
    private Float price;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "numberOfProducts must be > 0")
    private int numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total price must be > 0")
    private Float totalMoney;

    private String color;
}
