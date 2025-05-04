package com.rasel.bank_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasel.bank_management.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(name = "birth_day", nullable = false)
    private Date birthDay;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccounts;

    public User(List<BankAccount> bankAccounts, String firstName, String lastName, String email, String password, Role role, String phone, String address, Date birthDay) {
        this.bankAccounts = bankAccounts;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.birthDay = birthDay;
    }
}
