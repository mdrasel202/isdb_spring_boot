package com.rasel.journalApp.entity;

import jakarta.persistence.*;

import java.lang.annotation.Documented;
import java.time.LocalDate;


@Entity(name = "student_journal")
public class JournalEntry {

    @Id
    private long id;
    private String title;
    private String content;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
