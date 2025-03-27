package com.cap_talend.program.Services;

import com.cap_talend.program.repository.DQ_RulesRepository;
import com.cap_talend.program.models.DQ_RulesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DQ_RulesService {
    @Autowired(required=true)
    DQ_RulesRepository dq_rulesrepository;

    public List<DQ_RulesModel> getAll() {

        return (List<DQ_RulesModel>) dq_rulesrepository.findAll();
    }

    public DQ_RulesModel getMetaDataModel(Integer id) {

        return dq_rulesrepository.findById(Long.valueOf(id)).get();
    }


}
