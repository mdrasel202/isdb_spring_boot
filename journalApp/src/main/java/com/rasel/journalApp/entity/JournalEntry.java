package com.rasel.journalApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity(name = "student_journal")
public class JournalEntry {

    @Id
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
