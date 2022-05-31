package com.project.productservice.services.impl;

import com.project.productservice.entities.CustomerEntity;
import com.project.productservice.entities.ProductEntity;
import com.project.productservice.repositories.CustomerRepository;
import com.project.productservice.repositories.ProductRepository;
import com.project.productservice.requests.CreateProductRequest;
import com.project.productservice.responses.ProductResponse;
import com.project.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request, String email){
        String name = request.getName();
        String description = request.getDescription();
        BigDecimal price = request.getPrice();
        int availableQuantity = request.getAvailableQuantity();

        CustomerEntity customer = customerRepository.findByEmail(email);

        String merchantName = customer.getFirstName() + " " + customer.getLastName();
//        String merchantName = "NA";

        ProductEntity productEntity = ProductEntity.builder()
                .productName(name)
                .description(description)
                .availableQuantity(availableQuantity)
                .price(price)
                .merchantName(merchantName)
                .dateTimeCreated(LocalDateTime.now())
                .dateTimeModified(LocalDateTime.now())
                .build();

        productRepository.save(productEntity);

        return ProductResponse.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .availableQuantity(productEntity.getAvailableQuantity())
                .merchantName(productEntity.getMerchantName())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.getAllProductsSortedByDate();

        return productEntities.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found. Product with id: " + id));
        return mapToProductResponse(productEntity);
    }

    private ProductResponse mapToProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .merchantName(product.getMerchantName())
                .build();
    }

}
