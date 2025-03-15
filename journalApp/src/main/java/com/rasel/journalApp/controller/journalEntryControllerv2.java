package com.rasel.journalApp.controller;

import com.rasel.journalApp.entity.JournalEntry;
import com.rasel.journalApp.entity.User;
import com.rasel.journalApp.service.JournalEntryService;
import com.rasel.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJurnalEntriesOfUsr(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntry();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
//            myEntry.setDate(LocalDate.now());

            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalId(@PathVariable Long myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findId(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalId(@PathVariable Long myId,@PathVariable String userName) {
        journalEntryService.deleteId(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateJournalId(@PathVariable Long id,
                                             @RequestBody JournalEntry myEntry,
                                             @PathVariable String userName
    ) {
        JournalEntry old = journalEntryService.findId(id).orElse(null);
        if(old != null){
            old.setTitle(myEntry.getTitle() != null && myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && myEntry.getContent().equals("")? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
