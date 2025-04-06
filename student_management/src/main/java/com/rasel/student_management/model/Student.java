package com.rasel.student_management.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @OneToOne
    @JoinColumn(name = "clazz", nullable = false, referencedColumnName = "id")
    private StudentClass studentClass;

    @Column(nullable = false, length = 30, unique = true)
    private Integer roll;

    @OneToMany(mappedBy = "student")
    // @JoinColumn(name = "book", nullable = false, referencedColumnName = "id")
    private List<Book> book;

    @Column(nullable = false)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(nullable = false)
    private String gender;

    private Instant dob;
}


