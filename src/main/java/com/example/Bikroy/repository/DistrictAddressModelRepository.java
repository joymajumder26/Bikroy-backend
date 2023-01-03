package com.example.Bikroy.repository;

import com.example.Bikroy.model.DistrictAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DistrictAddressModelRepository extends JpaRepository<DistrictAddressModel,Long> {

    Optional<DistrictAddressModel> findByUuid(String Uuid);
}
