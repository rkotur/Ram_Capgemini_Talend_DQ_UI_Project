package com.cap_talend.program.repository;



import com.cap_talend.program.models.MetaDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBConnectionRepository extends JpaRepository<MetaDataModel, Long> {
}