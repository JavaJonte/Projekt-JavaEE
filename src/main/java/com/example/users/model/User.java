package com.example.users.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString


@Entity(name = "User")
public class User {
    public User(String firstName, String lastName, String email, String userName, String password, String secret) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.secret = secret;
        this.roles = "ROLE_ADMIN";
            }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String rePassword;
    private String oldPassword;
    private String secret;
    private boolean active = true;
    private String roles;

}
