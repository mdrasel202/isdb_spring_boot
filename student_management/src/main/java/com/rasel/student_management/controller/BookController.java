package com.rasel.student_management.controller;

import com.rasel.student_management.dto.BookDTO;
import com.rasel.student_management.model.Book;
import com.rasel.student_management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //post
    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO){
        Book saved = bookService.saveBook(bookDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id){
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookService.getAllBook();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //put
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book){
        Book updated = bookService.updateBook(id, book);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
