package com.example.Bikroy.repository;

import com.example.Bikroy.model.AreaAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaAddressRepository extends JpaRepository<AreaAddressModel,Long> {
    Optional<AreaAddressModel>findByUuid(String Uuid);
}
