package com.example.Bikroy.repository;

import com.example.Bikroy.model.OrderModel;
import com.example.Bikroy.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Long> {
    Optional<ProductModel> findByUuid(String Uuid);
}
