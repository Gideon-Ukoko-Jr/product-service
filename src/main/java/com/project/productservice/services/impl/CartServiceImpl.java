package com.project.productservice.services.impl;
import com.project.productservice.controllers.Interceptor;
import com.project.productservice.entities.CartItemEntity;
import com.project.productservice.entities.CustomerEntity;
import com.project.productservice.entities.ProductEntity;
import com.project.productservice.exceptions.BadRequestException;
import com.project.productservice.repositories.CartItemRepository;
import com.project.productservice.repositories.CustomerRepository;
import com.project.productservice.repositories.ProductRepository;
import com.project.productservice.requests.AddToCartRequest;
import com.project.productservice.requests.UpdateCartRequest;
import com.project.productservice.responses.CartItemResponse;
import com.project.productservice.responses.GetCartItemResponse;
import com.project.productservice.responses.GetCartItemResponseWithTotalAmount;
import com.project.productservice.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    @Override
    public CartItemResponse addToCart(AddToCartRequest request, String customerEmail) {
        long id = request.getProductId();
        int quantity = request.getQuantity();

        if (quantity == 0){
            quantity = 1;
        }
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found. Product with id: " + id));

        if (quantity > product.getAvailableQuantity()){
            throw new BadRequestException("Your Current order quantity is more than the available stock");
        }

        CustomerEntity customer = customerRepository.findByEmail(customerEmail);

        CartItemEntity cartItemEntity = CartItemEntity.builder()
                .product(product)
                .quantity(quantity)
                .customer(customer)
                .email(Interceptor.getUsername())
                .dateTimeCreated(LocalDateTime.now())
                .dateTimeModified(LocalDateTime.now())
                .build();

        cartItemRepository.save(cartItemEntity);

        cartItemEntity.setTotalPrice(cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity())));
        cartItemRepository.save(cartItemEntity);
        return fromCartEntityToCartItemResponse(cartItemEntity);
    }

    @Override
    public List<GetCartItemResponse> listCartItems(String customerEmail) {

        CustomerEntity customer = customerRepository.findByEmail(customerEmail);

        List<CartItemEntity> cartItems = cartItemRepository.getAllCartItemsByCustomerId(customer.getId());

        return cartItems.stream().map(this::fromCartItemEntityToGetCartItemResponse).collect(Collectors.toList());

    }

    @Override
    public GetCartItemResponseWithTotalAmount listCartItemsWithTotalAmount(String customerEmail) {

        CustomerEntity customer = customerRepository.findByEmail(customerEmail);
        List<CartItemEntity> cartItems = cartItemRepository.getAllCartItemsByCustomerId(customer.getId());
        List<GetCartItemResponse> responseList = cartItems.stream().map(this::fromCartItemEntityToGetCartItemResponse).collect(Collectors.toList());

        double sum = responseList.stream().mapToDouble(t -> t.getTotalPrice().doubleValue()).sum();

        BigDecimal totalAmount = new BigDecimal(sum);
        return GetCartItemResponseWithTotalAmount.builder()
                .itemResponses(responseList)
                .totalAmount(totalAmount)
                .build();
    }

    @Override
    public CartItemResponse updateCart(UpdateCartRequest request) {
        long cartItemId = request.getId();
        int quantity = request.getQuantity();

        CartItemEntity cartItemEntity = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Not Found. Cart Item with id: " + cartItemId));

        cartItemEntity.setQuantity(quantity);
        cartItemEntity.setTotalPrice(cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity())));
        cartItemEntity.setDateTimeModified(LocalDateTime.now());

        cartItemRepository.save(cartItemEntity);

        return fromCartEntityToCartItemResponse(cartItemEntity);
    }

    @Override
    public long getNumberOfItemsInCartCount(String uniqueId) {
//        CustomerEntity customer = customerRepository.findByEmail(Interceptor.getUsername());
        CustomerEntity customer = customerRepository.findByUniqueUserId(uniqueId);
        return cartItemRepository.getAllCartItemsByCustomerId(customer.getId()).size();
    }

    @Override
    public String deleteCartItem(long cartItemId) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Not Found. Cart Item with id: " + cartItemId));
        cartItemRepository.delete(cartItemEntity);
        return "Cart Item with Id: " + cartItemId + "has been deleted";
    }

    @Override
    public String clearCart(String email) {
        CustomerEntity customer = customerRepository.findByEmail(email);
        List<CartItemEntity> cartItems = cartItemRepository.getAllCartItemsByCustomerId(customer.getId());
        cartItemRepository.deleteAll(cartItems);
        return "Cart for user : " + customer.getId() + "has been cleared";
    }

    public CartItemResponse fromCartEntityToCartItemResponse(CartItemEntity cartItemEntity){

        return CartItemResponse.builder()
                .id(cartItemEntity.getId())
                .quantity(cartItemEntity.getQuantity())
                .productId(cartItemEntity.getProduct().getId())
                .customerId(cartItemEntity.getCustomer().getId())
                .customerEmail(cartItemEntity.getEmail())
                .productName(cartItemEntity.getProduct().getProductName())
                .quantity(cartItemEntity.getQuantity())
                .unitPrice(cartItemEntity.getProduct().getPrice())
                .totalPrice(cartItemEntity.getTotalPrice())
                .build();
    }

    public GetCartItemResponse fromCartItemEntityToGetCartItemResponse(CartItemEntity cartItemEntity){
        return GetCartItemResponse.builder()
                .id(cartItemEntity.getId())
                .customerId(cartItemEntity.getCustomer().getId())
                .customerEmail(cartItemEntity.getEmail())
                .productId(cartItemEntity.getProduct().getId())
                .productName(cartItemEntity.getProduct().getProductName())
                .quantity(cartItemEntity.getQuantity())
                .unitPrice(cartItemEntity.getProduct().getPrice())
                .totalPrice(cartItemEntity.getTotalPrice())
                .build();
    }

}
