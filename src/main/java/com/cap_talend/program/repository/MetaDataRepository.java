package com.cap_talend.program.repository;

import com.cap_talend.program.models.MetaDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;



public interface MetaDataRepository extends CrudRepository<MetaDataModel, Integer>{

//    @Query("SELECT m FROM MetaDataModel m WHERE m.campaign_name = :campaign_name")
//    List<MetaDataModel> findByCampaignName(@Param("campaign_name") String campaign_name);

    @Query("SELECT m FROM MetaDataModel m WHERE :campaign_name = 'All' OR m.campaign_name = :campaign_name")
    Page<MetaDataModel> findByCampaignName(@Param("campaign_name") String campaignName, Pageable pageable);

    Page<MetaDataModel> findAll(Pageable pageable);

    //Page<MetaDataModel> findByCampaignName(String campaignName, Pageable pageable);
}