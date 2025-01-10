package com.Arko.StationaryApp.service;

import com.Arko.StationaryApp.model.Orders;
import com.Arko.StationaryApp.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Orders findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
