package com.cap_talend.program.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaData_AddDTO {
    private String schema_name;
    private String table_name;
    private String columns_Names;
    private String campaign_name;
    private String dbcheck;
}