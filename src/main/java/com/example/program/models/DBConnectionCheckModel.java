package com.example.program.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DQ_DB_Connction_Check_Det")
public class DBConnectionCheckModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String db_name;
    private String db_connection_name;
    private String db_hostname;
    private int db_port;
    private String db_database;
    private String db_username;
    private String db_password;
}