package com.example.program.repository;

import com.example.program.models.DQ_RulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
//public interface DQ_RulesRepository extends JpaRepository<DQ_RulesModel, Long>  {
public interface DQ_RulesRepository extends JpaRepository<DQ_RulesModel, Long>  {


        @Query(value = "SELECT ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name FROM public.dq_table_check_val  group by rule_name Order by rule_name", nativeQuery = true)
        //@Query(value = "SELECT 1 as id, 'Ram' as name", nativeQuery = true)
        List<DQ_RulesModel> getRules ();

}

