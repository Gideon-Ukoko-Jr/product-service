package com.project.productservice.controllers;

import com.project.productservice.requests.CreateProductRequest;
import com.project.productservice.responses.ProductResponse;
import com.project.productservice.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/p")
@Api(tags = "Product Endpoints", description = "Handles Product Operations")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create-product")
    @ApiOperation(value = "Creates a new Product. User needs to be a MERCHANT OR ADMIN")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request){

        if (Interceptor.authority.equals("ADMIN") || Interceptor.authority.equals("MERCHANT")){
            ProductResponse productResponse = productService.createProduct(request, Interceptor.getUsername());
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-all-products")
    @ApiOperation(value = "Returns all Products currently available")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productResponseList = productService.getAllProducts();
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/get-product/{id}")
    @ApiOperation(value = "Returns specific Product by id")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable long id){
        ProductResponse productResponse = productService.findProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
