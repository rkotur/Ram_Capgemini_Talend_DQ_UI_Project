package com.cap_talend.program.repository;

import com.cap_talend.program.models.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SchemaRepository extends JpaRepository<Schema, Long>  {


    @Query(value="SELECT ROW_NUMBER() OVER() as id, lower(schema_name) as name FROM information_schema.schemata where lower(schema_name) not in ('dqs')   group by schema_name Order by schema_name", nativeQuery = true)
    List<Schema> getSchemas();

    @Query(value="SELECT ROW_NUMBER() OVER() as id, lower(table_name) as name FROM information_schema.tables where lower(table_schema) = lower(:TableSchema) Order by table_name", nativeQuery = true)
    List<Schema> getTables(@Param("TableSchema") String TableSchema);

    @Query(value="SELECT ROW_NUMBER() OVER(Order by Column_Name ) as id, lower(Column_Name) as name FROM information_schema.columns where lower(Table_schema) = :TableSchema and lower(Table_Name) = :TableName Group by Column_Name", nativeQuery = true)
    List<Schema> getColumns(@Param("TableSchema") String TableSchema, @Param("TableName") String TableName);




//    @Query(value = "SELECT "+
//            " ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name " +
//            " from (SELECT " +
//            " upper(rule_name) as rule_name " +
//            " FROM " +
//            " information_schema.columns as col1," +
//            " dqs.dq_db_check_mst as chk," +
//            " public.dq_table_check_val as chk_val" +
//            " where 1=1 " +
//            " and chk_val.rule_type = 'DQ Profiling' "+
//            " and chk_val.rule_type = :p_rule_type "+
//            " and col1.table_schema = :p_schema " +
//            " and col1.table_name = :p_table " +
//            " and col1.column_name = :p_column " +
//            " and col1.data_type = chk.column_value_type" +
//            " and chk.rule_id = chk_val.rule_id" +
//            " and dq_flag_column <> '' " +
//            " and dq_id_column <> '' " +
//            " Union " +
//            " SELECT upper(rule_name) as rule_name " +
//            " FROM " +
//            " public.dq_table_check_val as chk_val" +
//            " where 1=1 " +
//            " and chk_val.rule_type = 'DQ Custom' "+
//            " and chk_val.rule_type = :p_rule_type "+
//            " and dq_flag_column <> '' " +
//            " and dq_id_column <> '' " +
//            "  ) as tab1 group by rule_name Order by rule_name "
//            , nativeQuery = true)
//    List<Schema> getRules(@Param("p_rule_type") String p_rule_type,
//                          @Param("p_schema") String p_schema,
//                          @Param("p_table") String p_table,
//                          @Param("p_column") String p_column);


//    @Query(value = "SELECT " +
//            " ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name " +
//            " FROM " +
//            " information_schema.columns as col1," +
//            " dqs.dq_db_check_mst as chk," +
//            " public.dq_table_check_val as chk_val" +
//            " where " +
//            " col1.table_schema = :p_schema " +
//            " and col1.table_name = :p_table " +
//            " and col1.column_name = :p_column " +
//            " and col1.data_type = chk.column_value_type" +
//            " and chk.rule_id = chk_val.rule_id" +
//            " and dq_flag_column <> '' " +
//            " and dq_id_column <> '' " +
//            " and chk_val.rule_type = :p_rule_type " +
//            " group by rule_name Order by rule_name", nativeQuery = true)
//    List<Schema> getRules(@Param("p_rule_type") String p_rule_type,
//                          @Param("p_schema") String p_schema,
//                          @Param("p_table") String p_table,
//                          @Param("p_column") String p_column);


    @Query(value = "SELECT " +
            " ROW_NUMBER() OVER(Order by rule_name) as id, upper(rule_name) as name " +
            " FROM " +
            " information_schema.columns as col1," +
            " public.dq_table_check_val as chk_val" +
            " where " +
            " col1.table_schema = :p_schema " +
            " and col1.table_name = :p_table " +
            " and col1.column_name = :p_column " +
            " and dq_flag_column <> '' " +
            " and dq_id_column <> '' " +
            " and chk_val.rule_type = :p_rule_type " +
            " group by rule_name Order by rule_name", nativeQuery = true)
    List<Schema> getRules(@Param("p_rule_type") String p_rule_type,
                          @Param("p_schema") String p_schema,
                          @Param("p_table") String p_table,
                          @Param("p_column") String p_column);

}

