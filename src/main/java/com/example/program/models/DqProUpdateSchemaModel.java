package com.example.program.models;

import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "DqProUpdateSchema1.callProcedure",
        procedureName = "public.dq_pro_update_schema",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_schema_name", type = String.class)
                }
)
public class DqProUpdateSchemaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
