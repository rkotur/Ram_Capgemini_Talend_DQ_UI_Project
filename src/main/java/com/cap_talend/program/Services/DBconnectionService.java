package com.cap_talend.program.Services;

import com.cap_talend.program.models.MetaDataModel;
import com.cap_talend.program.repository.DBConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBconnectionService {

    @Value("${spring.datasource.url}")
    public String dbConnectionUrl;

    @Value("${spring.datasource.username}")
    public String dbConnectionUserName;

    @Value("${spring.datasource.password}")
    public String dbConnectionPassword;

    public boolean testConnection(DBConnectionRequest connectionRequest) {

        String url =
                "jdbc:postgresql://" +
                        connectionRequest.getHostname() + ":" +
                        connectionRequest.getPort() + "/" + connectionRequest.getDbName();


        String yam=connectionRequest.getUsername();

        return dbConnectionUrl.equals(url)
                && dbConnectionUserName.equals(yam) &&
                dbConnectionPassword.equals(connectionRequest.getPassword());
    }


    @Autowired
    private DBConnectionRepository connectionRepository;

    public Optional<MetaDataModel> findById(Long id) {
        return connectionRepository.findById(id);
    }

    public MetaDataModel save(MetaDataModel connection) {
        return connectionRepository.save(connection);
    }

    public void deleteById(Long id) {
        connectionRepository.deleteById(id);
    }
}
