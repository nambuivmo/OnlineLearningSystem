package com.onlinelearningsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private long idAccount;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "is_banned", nullable = false)
    private boolean isBanned;

    @ManyToOne(fetch = FetchType.LAZY) // Adding fetch type to optimize loading strategy
    @JoinColumn(name = "role_id" , nullable = false, unique = true) // Explicitly specify join column
    private RoleAccount roleAccount;
}
