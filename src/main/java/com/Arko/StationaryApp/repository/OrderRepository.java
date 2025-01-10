package com.Arko.StationaryApp.repository;

import com.Arko.StationaryApp.model.Orders;
import com.Arko.StationaryApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);

    Optional<Orders> findById(Long id);
}   
