package com.example.program.Services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


        /*
        String url =
                "jdbc:mysql://" +
                        connectionRequest.getHostname() + ":" +
                        connectionRequest.getPort() + "/" +
                        connectionRequest.getDbName();
        */
        //System.out.println("Ram..... URL:.."+url);

        return dbConnectionUrl.equals(url)
                && dbConnectionUserName.equals(connectionRequest.getUsername()) &&
                dbConnectionPassword.equals(connectionRequest.getPassword());
    }
}
