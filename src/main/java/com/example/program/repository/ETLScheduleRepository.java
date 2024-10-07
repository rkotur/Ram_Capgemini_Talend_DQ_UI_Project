package com.example.program.repository;

import com.example.program.models.ETLScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ETLScheduleRepository extends  JpaRepository<ETLScheduleModel, Long> {

    @Query(value = "SELECT ROW_NUMBER() OVER(Order by campaign_name) as id, upper(campaign_name) as name FROM dq_app_etl_schedule where campaign_name <> '' and id <> '' and campaign_name = :p_campaign_name group by campaign_name Order by campaign_name", nativeQuery = true)
    List<ETLScheduleModel> getRules (@Param("p_campaign_name") String p_campaign_name);




}
