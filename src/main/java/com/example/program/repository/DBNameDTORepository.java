package com.example.program.repository;

import com.example.program.dto.DBNameDTO;
import com.example.program.models.DBConnectionCheckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBNameDTORepository extends JpaRepository<DBConnectionCheckModel, Long> {

    @Query(value = "SELECT upper(db_name) as db_name FROM dqs.dq_db_connction_check_det  WHERE db_name <> '' AND db_database <> ''  GROUP BY id, db_name  ORDER BY db_name", nativeQuery = true)
    List<DBConnectionCheckModel> getDB_Name();
}
