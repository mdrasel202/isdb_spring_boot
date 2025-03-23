package com.rasel.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasel.student_management.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
