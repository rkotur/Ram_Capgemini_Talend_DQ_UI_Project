package com.example.program.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

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

    @Column(name = "campaign_name", nullable=false , unique=true)
    private String campaignName;


    @Column(nullable=false)
    private String schedule_name;

    @Column(nullable=false)
    private LocalDateTime select_date;

    @Transient // This will not be persisted in the database
    private String formattedEventDate;

    @Column(nullable=false)
    private String is_used;

}
