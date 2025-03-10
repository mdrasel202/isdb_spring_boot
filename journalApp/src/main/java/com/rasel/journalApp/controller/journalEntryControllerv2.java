package com.rasel.journalApp.controller;

import com.rasel.journalApp.entity.JournalEntry;
import com.rasel.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDate.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalId(@PathVariable Long myId){
        return journalEntryService.findId(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean  deleteJournalId(@PathVariable Long myId){
        journalEntryService.deleteId(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalId(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        JournalEntry old = journalEntryService.findId(id).orElse(null);
        if(old != null){
            old.setTitle(myEntry.getTitle() != null && myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && myEntry.getContent().equals("")? myEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
