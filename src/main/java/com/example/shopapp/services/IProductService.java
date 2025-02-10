package com.example.shopapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.dtos.ProductImageDTO;
import com.example.shopapp.models.Product;
import com.example.shopapp.models.ProductImage;
import com.example.shopapp.responses.ProductResponse;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;

    Product getProductById(Long id) throws Exception;

    Page<ProductResponse> getAllProducts(Pageable papeRequest);

    Product updateProduct(Long id, ProductDTO productDTO) throws Exception;

    void deleteProduct(Long id);

    boolean existsByName(String name);  

    public ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception;
}
