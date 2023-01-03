package com.example.Bikroy.repository;

import com.example.Bikroy.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderModelRepository extends JpaRepository<OrderModel,Long>{
        Optional<OrderModel>findByUuid(String Uuid);
}
