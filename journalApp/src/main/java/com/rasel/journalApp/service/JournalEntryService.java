package com.rasel.journalApp.service;

import com.rasel.journalApp.entity.JournalEntry;
import com.rasel.journalApp.entity.User;
import com.rasel.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setUser(user);
        journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntry().add(saved);
//        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
      return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findId(Long id){
        return journalEntryRepository.findById(id);
    }

    public void deleteId(Long id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
