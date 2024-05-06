package com.example.program.Services;

import java.util.List;

import com.example.program.models.MetaDataModel;
import com.example.program.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MetaDataService {
    @Autowired(required=true)
    MetaDataRepository metasatarepository;

    public List<MetaDataModel> getAll() {
        return (List<MetaDataModel>) metasatarepository.findAll();
    }

    public MetaDataModel getMetaDataModel(Integer id) {
        return metasatarepository.findById(id).get();
    }

    public void insert(MetaDataModel metadatamodel) {
        int max = 0;
        Iterable<MetaDataModel> list = metasatarepository.findAll();
        for(MetaDataModel stud : list){
            if(stud.getId() > max) max = stud.getId();
        }
        metadatamodel.setId(max + 1);
        metasatarepository.save(metadatamodel);
    }

    public void update(int id, MetaDataModel newmetadatamodel) {
        MetaDataModel metadatamodel =  metasatarepository.findById(id).get();

        metadatamodel.setDbschema(newmetadatamodel.getDbschema());
        metadatamodel.setDbtable(newmetadatamodel.getDbtable());
        metadatamodel.setDbcolumn(newmetadatamodel.getDbcolumn());
        metadatamodel.setDbcheck(newmetadatamodel.getDbcheck());

        metadatamodel.setUpdatedBy(newmetadatamodel.getUpdatedBy());
        metadatamodel.setUpdatedOn(newmetadatamodel.getUpdatedOn());
        //if(newmetadatamodel.getContent().length!=0)
        //    metadatamodel.setContent(newmetadatamodel.getContent());
        metasatarepository.save(metadatamodel);
    }

    public void delete(int id) {
        MetaDataModel metadatamodel =  metasatarepository.findById(id).get();
        metasatarepository.delete(metadatamodel);
    }
}
