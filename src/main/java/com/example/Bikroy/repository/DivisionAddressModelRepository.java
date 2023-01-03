package com.example.Bikroy.repository;

import com.example.Bikroy.model.DivisionAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DivisionAddressModelRepository extends JpaRepository<DivisionAddressModel,Long> {
    Optional<DivisionAddressModel> findByUuid(String Uuid);
}
