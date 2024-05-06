package com.example.program.repository;

import org.springframework.data.repository.CrudRepository;

//import org.springframework.stereotype.Repository;

import com.example.program.models.MetaDataModel;

public interface MetaDataRepository extends CrudRepository<MetaDataModel, Integer>{

}