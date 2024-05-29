package com.example.program.repository;

import com.example.program.models.DQ_RulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
//public interface DQ_RulesRepository extends JpaRepository<DQ_RulesModel, Long>  {
public interface DQ_RulesRepository extends JpaRepository<DQ_RulesModel, Long>  {


        @Query(value = "SELECT ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name FROM public.dq_table_check_val where dq_flag_column <> '' and dq_id_column <> '' and rule_type = :p_rule_type group by rule_name Order by rule_name", nativeQuery = true)
        List<DQ_RulesModel> getRules (@Param("p_rule_type") String p_rule_type);

}

