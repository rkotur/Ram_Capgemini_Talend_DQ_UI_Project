package com.example.program.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="DQ_Table_Metadata")
public class MetaDataModel {

    @jakarta.persistence.Id
    private Integer Id;
    private String dbname;

    public String getdbname() {
        return dbname;
    }

    public void setdbname(String dbname) {
        this.dbname = dbname;
    }

    private String dbschema;
    private String dbtable;
    private String dbcolumn;
    private String dbcheck;

    private String dbsource;

    private String campaign_name;

    public String getDbsource() {
        return dbsource;
    }

    public void setDbsource(String dbsource) {
        this.dbsource = dbsource;
    }



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


    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }



    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;

    public MetaDataModel() {

    }

    public MetaDataModel(Integer id, String dbname,String dbschema, String dbtable,
                         String dbcolumn,String dbcheck,String dbsource,
                         String updatedBy,
                         Date updatedOn) {
        super();
        Id = id;
        this.dbname = dbname;
        this.dbschema = dbschema;
        this.dbtable = dbtable;
        this.dbcolumn = dbcolumn;
        this.dbcheck = dbcheck;
        this.dbsource = dbsource;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "MetaDataModel [Id=" + Id + ", dbname=" + dbname + ", dbschema=" + dbschema + ", dbtable=" + dbtable + ", "
                + ", dbcolumn=" + dbcolumn + ", "
                + ", dbcheck=" + dbcheck + ", "
                + ", dbsource=" + dbsource + ", "
                + "updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
    }

}