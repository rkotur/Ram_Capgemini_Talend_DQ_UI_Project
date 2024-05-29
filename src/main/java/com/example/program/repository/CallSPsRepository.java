package com.example.program.repository;

import com.example.program.models.DQ_RulesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CallSPsRepository extends JpaRepository<DQ_RulesModel, Long>  {

        //   Using @Query annotation
        @Query(value ="select * from  dq_pro_update_schema(:P_source, :P_trans_type,:P_dbschema,:P_dbtable,:P_dbcolumn)",nativeQuery = true)
        List<String> get_pro_update_schema(@Param("P_source") String P_source, @Param("P_trans_type") String P_trans_type, @Param("P_dbschema") String P_dbschema, @Param("P_dbtable") String P_dbtable, @Param("P_dbcolumn") String P_dbcolumn);

}

