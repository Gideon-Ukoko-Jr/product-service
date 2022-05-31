package com.project.productservice.controllers;


import com.project.productservice.requests.AddToCartRequest;
import com.project.productservice.requests.UpdateCartRequest;
import com.project.productservice.responses.CartItemResponse;
import com.project.productservice.responses.GetCartItemResponse;
import com.project.productservice.responses.GetCartItemResponseWithTotalAmount;
import com.project.productservice.services.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Api(tags = "Cart Endpoints", description = "Handles User Cart Operations")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/add-to-cart")
    @ApiOperation(value = "Adds an item to user cart using the productId and required quantity")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<CartItemResponse> addToCart(@RequestBody AddToCartRequest request){
        CartItemResponse cartItemResponse = cartService.addToCart(request, Interceptor.getUsername());
        return new ResponseEntity<>(cartItemResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    @ApiOperation(value = "Returns all items in the user cart with each item quantity, item unit-price and sub-total per item")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<List<GetCartItemResponse>> getAllItemsInCart(){
        List<GetCartItemResponse> getCartItemResponses = cartService.listCartItems(Interceptor.getUsername());
        return new ResponseEntity<>(getCartItemResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/total")
    @ApiOperation(value = "Returns all items in the user cart with each item quantity, item unit-price and sub-total per item and the total-amount that needs to be paid at checkout")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<GetCartItemResponseWithTotalAmount> getAllItemsInCartWithTotalAmount(){
        GetCartItemResponseWithTotalAmount response = cartService.listCartItemsWithTotalAmount(Interceptor.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/update-cart-item")
    @ApiOperation(value = "Updates quantity of cart item")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<CartItemResponse> updateCartItem(@RequestBody UpdateCartRequest request){
        CartItemResponse cartItemResponse = cartService.updateCart(request);
        return new ResponseEntity<>(cartItemResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{uniqueId}/cart-item-count")
    @ApiOperation(value = "Returns number of items in user cart")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<Long> getCustomerCartCount(@PathVariable("uniqueId") String uniqueId){
        long count = cartService.getNumberOfItemsInCartCount(uniqueId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-cart-item/{itemId}")
    @ApiOperation(value = "Deletes cart item")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<String> deleteCartItem(@PathVariable("itemId") long cartItemId){
        String response = cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/clear-cart")
    @ApiOperation(value = "Deletes all items in user cart")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<String> clearCart(){
        String response = cartService.clearCart(Interceptor.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
