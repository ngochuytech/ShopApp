package com.example.shopapp.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.dtos.ProductImageDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.exceptions.InvalidParamException;
import com.example.shopapp.models.Category;
import com.example.shopapp.models.Product;
import com.example.shopapp.models.ProductImage;
import com.example.shopapp.repositories.CategoryRepository;
import com.example.shopapp.repositories.ProductImageRepository;
import com.example.shopapp.repositories.ProductRepository;
import com.example.shopapp.responses.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; 
    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new DataNotFoundException("Cannot find category with id " + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                            .name(productDTO.getName())
                            .price(productDTO.getPrice())
                            .thumbnail(productDTO.getThumbnail())
                            .description(productDTO.getDescription())
                            .category(existingCategory)
                            .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long productId) throws Exception {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id = " + productId));
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable papeRequest) {
        // Lấy danh sách sản phẩm theo trang (page) và giới hạn (limit)
        return productRepository.findAll(papeRequest).map(ProductResponse :: fromProduct);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if(existingProduct != null){
            // copy cac thuoc tinh tu DTO -> Product
            // Có thể sử dụng ModelMapper
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new DataNotFoundException("Cannot find category with id " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.delete(optionalProduct.get());
        }
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    
    @Override
    public ProductImage createProductImage(Long productId,
    ProductImageDTO productImageDTO) throws Exception{
        Product existingProduct = productRepository.findById(productId)
            .orElseThrow(() -> new DataNotFoundException("Cannot find product with id " + productId));
        ProductImage newProductImage = new ProductImage().builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        // Khong cho insert qua 5 anh cho 1 san pham
        int size =productImageRepository.findByProductId(productId).size();
        if(size > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new InvalidParamException("Number of image must be <= 5");
        }
        return productImageRepository.save(newProductImage);
    }
}
