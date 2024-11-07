package com.example.program.repository;

import com.example.program.models.DBConnectionCheckModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DBConnectionCheckRepository extends JpaRepository<DBConnectionCheckModel, Long> {

   // DBConnectionCheckModel findByDB_Name(String db_name);

    //@Query(value = "SELECT id,upper(db_name) as DB_Name FROM dqs.dq_db_connction_check_det  WHERE db_name <> '' AND db_database <> ''  GROUP BY id, db_name  ORDER BY db_name", nativeQuery = true)
    //List<DBConnectionCheckModel> getDB_Name();
    @Query(value = "SELECT * FROM dqs.dq_db_connction_check_det  WHERE db_name <> '' AND db_database <> ''  ORDER BY db_name", nativeQuery = true)
    List<DBConnectionCheckModel> getDB_Name();


}
