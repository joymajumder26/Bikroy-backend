package com.example.Bikroy.repository;

import com.example.Bikroy.model.SendMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SendMessageModelRepository extends JpaRepository<SendMessageModel,Long> {
    Optional<SendMessageModel>findByUuid(String Uuid);
}
