package com.cap_talend.program.Services;

import com.cap_talend.program.repository.DBConnectionCheckRepository;
import com.cap_talend.program.models.DBConnectionCheckModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
public class DBConnectionCheckService {

    @Autowired
    private DBConnectionCheckRepository connectionRepository;

    public Optional<DBConnectionCheckModel> findById(Long id) {
        return connectionRepository.findById(id);
    }

    public DBConnectionCheckModel save(DBConnectionCheckModel connection) {
        return connectionRepository.save(connection);
    }

    public void deleteById(Long id) {
        connectionRepository.deleteById(id);
    }
/*
    public DBConnectionCheckModel findByDB_Name(String db_name) {
        return connectionRepository.findByDB_Name(db_name);
    }
*/

    public List<DBConnectionCheckModel> getAllDbNames() {
        return connectionRepository.getDB_Name();
    }

}
