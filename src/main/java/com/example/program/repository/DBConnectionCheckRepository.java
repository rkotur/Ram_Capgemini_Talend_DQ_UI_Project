package com.example.program.repository;

import com.example.program.models.DBConnectionCheckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DBConnectionCheckRepository extends JpaRepository<DBConnectionCheckModel, Long> {

    @Query(value = "SELECT id, db_name,db_connection_name,db_hostname,db_port,db_database,db_username,db_password FROM dqs.dq_db_connction_check_det WHERE  id=:id", nativeQuery = true)
    DBConnectionCheckModel findByName(@Param("id") Long id);

    @Query(value = "SELECT * FROM dqs.dq_db_connction_check_det  WHERE db_name <> '' AND db_database <> ''  ORDER BY db_name", nativeQuery = true)
    List<DBConnectionCheckModel> getDB_Name();
}
