package com.example.kjkspringblog.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    @Size(min=4,max=10)
    @Pattern(regexp="^[a-z0-9]*$")
    private String username;

    @Column(nullable = false)
    @Size(min=8,max=15)
    @Pattern(regexp="^[a-zA-z0-9]*$")
    private String password;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
