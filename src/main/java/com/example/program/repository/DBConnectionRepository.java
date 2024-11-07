package com.example.program.repository;



import com.example.program.models.MetaDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBConnectionRepository extends JpaRepository<MetaDataModel, Long> {
}