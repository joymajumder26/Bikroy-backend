package com.example.Bikroy.repository;


import com.example.Bikroy.model.SubCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryModelRepository extends JpaRepository<SubCategoryModel,Long> {
    Optional<SubCategoryModel>findByUuid(String Uuid);
}
