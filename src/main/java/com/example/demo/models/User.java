package com.example.demo.models;

import com.example.demo.models.datatypes.SysRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SysRoleType role;

    public User() {
    }
    public User(String username, String password, SysRoleType role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
