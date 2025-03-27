package com.cap_talend.program.repository;

import com.cap_talend.program.models.DQ_RulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DQ_RulesRepository extends JpaRepository<DQ_RulesModel, Long>  {


    @Query(value = "SELECT " +
            " ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name " +
            " FROM " +
            " information_schema.columns as col1," +
            " dqs.dq_db_check_mst as chk," +
            " public.dq_table_check_val as chk_val" +
            " where " +
            " col1.table_schema = :p_schema " +
            " and col1.table_name = :p_table " +
            " and col1.column_name = :p_column " +
            " and col1.data_type = chk.column_value_type" +
            " and chk.rule_id = chk_val.rule_id" +
            " and dq_flag_column <> '' " +
            " and dq_id_column <> '' " +
            " and chk_val.rule_type = :p_rule_type " +
            " group by rule_name Order by rule_name", nativeQuery = true)
     List<DQ_RulesModel> getRules(@Param("p_rule_type") String p_rule_type,
                                         @Param("p_schema") String p_schema,
                                         @Param("p_table") String p_table,
                                         @Param("p_column") String p_column);

}

