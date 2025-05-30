package com.rasel.journalApp.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "user_sl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate id
    private Long id;

    //@Indexed(unique = true) mongoDB
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    //@BDRef--mongoDB
    @OneToMany(mappedBy = "user") //relationship journalEntry
    private List<JournalEntry> journalEntry = new ArrayList<>();

    public List<JournalEntry> getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(List<JournalEntry> journalEntry) {
        this.journalEntry = journalEntry;
    }

    public User() {
    }

    public User(Long id,String userName, String password, List<JournalEntry> journalEntry) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.journalEntry = journalEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
