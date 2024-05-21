package com.example.program.models;

import java.util.Date;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;

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

    public MetaDataModel(Integer id, String dbschema, String dbtable,
                         String dbcolumn,String dbcheck,
                         String updatedBy,
                         Date updatedOn) {
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

}