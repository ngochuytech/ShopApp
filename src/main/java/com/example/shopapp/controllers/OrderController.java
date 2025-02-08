package com.example.shopapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopapp.dtos.OrderDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    
    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO OrderDTO, 
        BindingResult result
    ) {
        try {
            if(result.hasErrors()){
                List<String> errorMesseages = result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
                return ResponseEntity.badRequest().body(errorMesseages);
            }
            return ResponseEntity.ok("CreteOrder successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") Long userId){
        try {
            return ResponseEntity.ok("Lay ra danh sach order to user ID" + userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable("id") Long Id,
    @Valid @RequestBody OrderDTO orderDTO
    ){
        try {
            return ResponseEntity.ok("Cap nhat thong tin 1 order");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrders(@Valid @PathVariable("id") Long id){
        try {
            // Xoa mem => Cap nhat truong active = false
            return ResponseEntity.ok("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
