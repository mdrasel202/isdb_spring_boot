package com.rasel.student_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 50)
    private String publisher;

    // @Transient database hide obostai takbe coloum dekabe na 
    @OneToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    private StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "student", nullable = false)
    private Student student;
    
}
