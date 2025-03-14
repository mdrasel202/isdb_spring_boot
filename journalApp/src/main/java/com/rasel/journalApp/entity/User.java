package com.rasel.journalApp.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "user_sl")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate id
    private long id;

    //@Indexed(unique = true) mongoBD
    @Column(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    //@BDRef--mongoBd
    @OneToMany(mappedBy = "user") //relationship journalEntry
    private List<JournalEntry> journalEntry = new ArrayList<>();

    public User() {
    }

    public User( String userName, String password) {
        this.userName = userName;
        this.password = password;
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
