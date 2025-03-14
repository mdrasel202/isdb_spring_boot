package com.rasel.journalApp.service;

import com.rasel.journalApp.entity.JournalEntry;
import com.rasel.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
      return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findId(long id){
        return journalEntryRepository.findById(id);
    }

    public void deleteId(long id){
        journalEntryRepository.deleteById(id);
    }
}
