package com.example.program.repository;

import com.example.program.models.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SchemaRepository extends JpaRepository<Schema, Long>  {

    @Query(value="SELECT ROW_NUMBER() OVER() as id, upper(schema_name) as name FROM information_schema.schemata Order by schema_name", nativeQuery = true)
    List<Schema> getSchemas();
    @Query(value="SELECT ROW_NUMBER() OVER() as id, upper(table_name) as name FROM information_schema.tables Order by table_name", nativeQuery = true)
    List<Schema> getTables();
    @Query(value="SELECT ROW_NUMBER() OVER(Order by Column_Name ) as id, upper(Column_Name) as name FROM information_schema.columns Group by Column_Name", nativeQuery = true)
    List<Schema> getColumns();

}

