package com.example.program.Services;

import com.example.program.models.DBConnectionCheckModel;
import com.example.program.models.ETLScheduleModel;
import com.example.program.repository.DBConnectionCheckRepository;
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
