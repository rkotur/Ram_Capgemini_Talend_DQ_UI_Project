package com.example.program.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static freemarker.template.utility.StringUtil.replace;

@Entity
@Table(name="DQ_Table_Metadata")
public class MetaDataModel {

    @jakarta.persistence.Id
    private Integer Id;
    private String dbschema;
    private String dbtable;
    private String dbcolumn;
    private String dbcheck;

    private String updatedBy;

    //@Column(nullable = false, columnDefinition = "Date default 'YourDefaultValue'")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedOn;






    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDbschema() {
        return dbschema;
    }

    public void setDbschema(String dbschema) {
        this.dbschema = dbschema;
    }

    public String getDbtable() {
        return dbtable;
    }

    public void setDbtable(String dbtable) {
        this.dbtable = dbtable;
    }

    public String getDbcolumn() {
        return dbcolumn;
    }

    public void setDbcolumn(String dbcolumn) {
        this.dbcolumn = dbcolumn;
    }

    public String getDbcheck() {
        return dbcheck;
    }

    public void setDbcheck(String dbcheck) {
        this.dbcheck = dbcheck;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }



    public MetaDataModel() {

    }

    public MetaDataModel(Integer id, String dbschema, String dbtable,
                         String dbcolumn,String dbcheck,
                         String updatedBy,
                         LocalDateTime updatedOn) {
        super();
        Id = id;
        this.dbschema = dbschema;
        this.dbtable = dbtable;
        this.dbcolumn = dbcolumn;
        this.dbcheck = dbcheck;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "MetaDataModel [Id=" + Id + ", dbschema=" + dbschema + ", dbtable=" + dbtable + ", "
                + ", dbcolumn=" + dbcolumn + ", "
                + ", dbcheck=" + dbcheck+ ", "
                + "updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
    }

    // Set default value , if it is null
    @PreUpdate
    public void updateDefaultValueIfNull() {
        if (this.updatedBy == null) {
            this.updatedBy = "Admin";
        }
    }
    @PrePersist
    public void prePersist() {
        this.updatedOn = LocalDateTime.now();
        this.updatedBy = "Admin";
    }

}