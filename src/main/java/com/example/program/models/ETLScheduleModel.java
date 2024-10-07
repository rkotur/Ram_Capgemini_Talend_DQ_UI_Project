package com.example.program.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DQ_APP_ETL_Schedule")
public class ETLScheduleModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false , unique=true)
    private String campaign_name;

    @Column(nullable=false)
    private String schedule_name;

    @Column(nullable=false)
    private Date select_date;

}
