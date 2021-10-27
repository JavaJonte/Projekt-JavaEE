package com.example.users.model;

import lombok.*;

import javax.persistence.*;

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
    }

    public User(boolean admin, String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 45, nullable = false, name = "first_name")
    private String firstName;
    @Column(length = 45, nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, unique = true, length = 45)
    private String userName;

    @Column(length = 15, nullable = false)
    private String password;

    private String rePassword;

    private String oldPassword;
    private String secret;

    private boolean active = true; // TODO Hårdkodad för tillfället, skall ställas in av admin eller via bekräftelse mail.
    @Column(length = 15, nullable = false)
    private String roles = "ROLE_ADMIN"; // TODO Hårdkodad för tillfället, denna skall senare endast kunna ställas in av admin rollen.
    private boolean admin;
}
