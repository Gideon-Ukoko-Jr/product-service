package com.project.productservice.services;

import com.project.productservice.requests.AddToCartRequest;
import com.project.productservice.requests.UpdateCartRequest;
import com.project.productservice.responses.CartItemResponse;
import com.project.productservice.responses.GetCartItemResponse;
import com.project.productservice.responses.GetCartItemResponseWithTotalAmount;

import java.util.List;

public interface CartService {

    CartItemResponse addToCart(AddToCartRequest request, String customerEmail);

    List<GetCartItemResponse> listCartItems(String customerEmail);

    GetCartItemResponseWithTotalAmount listCartItemsWithTotalAmount(String customerEmail);

    CartItemResponse updateCart(UpdateCartRequest request);

    long getNumberOfItemsInCartCount(String uniqueId);

    String deleteCartItem(long cartItemId);

    String clearCart(String email);

}
