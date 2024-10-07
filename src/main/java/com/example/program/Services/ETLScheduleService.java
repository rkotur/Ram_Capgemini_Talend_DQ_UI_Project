package com.example.program.Services;

import com.example.program.models.ETLScheduleModel;
import com.example.program.repository.DQ_RulesRepository;
import com.example.program.repository.ETLScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ETLScheduleService {

    @Autowired(required=true)
    ETLScheduleRepository eltschedulerepository;
     

}
