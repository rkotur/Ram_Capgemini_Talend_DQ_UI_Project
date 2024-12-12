package com.example.program.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DQ_APP_Users")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    //@Column(nullable=false)
    private String role_name;

    /*
    @Column(nullable=true,name = "expire_date")
    @JsonFormat(pattern="DD-MM-YYYY")
    @Temporal(TemporalType.DATE)
    private LocalDate expire_date;
    */

    //@NotNull
    @Column(name = "expiredate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiredate;


    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="DQ_APP_Users_Roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();


    public boolean isExpired() {
        System.out.println("----------User... isExpired....."+LocalDate.now());
        return expiredate != null && expiredate.isBefore(LocalDate.now());
    }


}

