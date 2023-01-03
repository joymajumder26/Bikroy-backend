package com.example.Bikroy.repository;

import com.example.Bikroy.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryModelRepository extends JpaRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByUuid(String uuid);
}
