package com.example.program.repository;

import com.example.program.models.DQ_RulesModel;
import com.example.program.models.ETLScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ETLScheduleRepository extends JpaRepository<ETLScheduleModel, Long> {

    ETLScheduleModel findByCampaignName(String campaignname);

    @Query(value = "SELECT id as id, upper(campaign_name) as name FROM dq_app_etl_schedule where campaign_name <> ''  group by id,campaign_name Order by campaign_name", nativeQuery = true)
    List<ETLScheduleModel> getCampaign();

}
