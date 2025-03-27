package com.cap_talend.program.Services;

import com.cap_talend.program.repository.ETLScheduleRepository;
import com.cap_talend.program.models.ETLScheduleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ETLScheduleService {

    @Autowired
    private ETLScheduleRepository etlScheduleRepository;

    public Page<ETLScheduleModel> getAllSchedules(Pageable pageable) {
        return etlScheduleRepository.findAll(pageable); // Fetch all records with pagination
    }

    public List<ETLScheduleModel> findAll() {
        return etlScheduleRepository.findAll();
    }

    public Optional<ETLScheduleModel> findById(Long id) {
        return etlScheduleRepository.findById(id);
    }

    public ETLScheduleModel save(ETLScheduleModel etlScheduleModel) {
        return etlScheduleRepository.save(etlScheduleModel);
    }

    public void deleteById(Long id) {
        etlScheduleRepository.deleteById(id);
    }

    public ETLScheduleModel findByCampaignName(String campaign_name) {
        return etlScheduleRepository.findByCampaignName(campaign_name);
    }

}