package com.project.productservice.services;


import com.project.productservice.requests.CreateProductRequest;
import com.project.productservice.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request, String email);

    List<ProductResponse> getAllProducts();

    ProductResponse findProductById(long id);
}
