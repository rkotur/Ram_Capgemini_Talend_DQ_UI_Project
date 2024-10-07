package com.example.program.repository;

import com.example.program.models.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
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


}

