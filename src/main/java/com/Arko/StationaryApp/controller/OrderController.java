package com.Arko.StationaryApp.controller;

import com.Arko.StationaryApp.model.Orders;
import com.Arko.StationaryApp.model.Product;
import com.Arko.StationaryApp.model.User;
import com.Arko.StationaryApp.service.OrderService;
import com.Arko.StationaryApp.service.ProductService;
import com.Arko.StationaryApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
//API's used for placing order, viewing and updating order
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;
    public OrderController(OrderService orderService, ProductService productService, UserService userService){
        this.orderService=orderService;
        this.productService=productService;
        this.userService=userService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity, @RequestParam String paymentMethod){
        User user=userService.findUserById(userId)
                .orElseThrow(()-> new RuntimeException("User not found !!!"));
        Product product=productService.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found !!!"));
        //Checking the stock
        if(product.getStockQuantity()<quantity){
            return ResponseEntity.ok("Product out of stock");
        }
        //Now creating order
        product.setStockQuantity(product.getStockQuantity()-quantity);
        productService.saveProduct(product);

        Orders order=Orders.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .paymentType(paymentMethod)
                .status("Order Placed")
                .orderDate(java.time.LocalDateTime.now())
                .build();
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }

    //Get orders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getOrdersbyUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }

    //Update order status
    @PutMapping("/update/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status){
        Orders order=orderService.findById(orderId);

        order.setStatus(status);
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order status updated successfully");
    }
}
