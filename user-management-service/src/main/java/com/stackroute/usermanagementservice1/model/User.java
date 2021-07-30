package com.stackroute.usermanagementservice1.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "usertable")
public class User {
    @Id
    @Column(name = "email", length = 50)
    private String email;

    @Column (name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}
