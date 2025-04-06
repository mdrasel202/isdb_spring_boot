package com.rasel.student_management.service;

import com.rasel.student_management.dto.BookDTO;
import com.rasel.student_management.model.Book;
import com.rasel.student_management.model.StudentClass;
import com.rasel.student_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final StudentClassService studentClassService;

    public BookService(BookRepository bookRepository, StudentClassService studentClassService){
        this.bookRepository = bookRepository;
        this.studentClassService = studentClassService;
    }

    //post
    public Book saveBook(BookDTO bookDTO) {
        Integer classId = bookDTO.getClazzId();
        StudentClass clazz = studentClassService.getStudentClass(classId);

        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        if(clazz != null)
            book.setStudentClass(clazz);

        return bookRepository.save(book);
    }

    //get
    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    //delete
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    //get all
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    //update
    public Book updateBook(Integer id, Book book) {
        Optional<Book> bookById = bookRepository.findById(id);

        if(bookById.isPresent()){
            Book aBook = new Book();
            if(book.getName() != null){
                aBook.setName(book.getName());
            }

            if(book.getAuthor() != null){
                aBook.setAuthor(book.getAuthor());
            }

            if(book.getPublisher() != null){
                aBook.setPublisher(book.getPublisher());
            }

            if(book.getStudentClass() != null){
                Integer classId = book.getStudentClass().getId();
                StudentClass clazz = studentClassService.getStudentClass(classId);
                if(clazz == null) {
                    throw new IllegalArgumentException("Class not found");
                }
                aBook.setStudentClass(clazz);
            }
            return bookRepository.save(aBook);
        }else{
            throw new IllegalArgumentException("Book not found");
        }
    }
}
