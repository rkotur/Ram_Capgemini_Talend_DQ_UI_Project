package com.example.program.repository;

import com.example.program.models.MetaDataModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface MetaDataRepository extends CrudRepository<MetaDataModel, Integer>{


}