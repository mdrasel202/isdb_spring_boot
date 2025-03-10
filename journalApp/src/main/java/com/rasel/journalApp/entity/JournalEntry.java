package com.rasel.journalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.lang.annotation.Documented;
import java.time.LocalDate;

@Entity(name = "student_journal")
public class JournalEntry {

    @Id
    private long id;
    private String title;
    private String content;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

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
