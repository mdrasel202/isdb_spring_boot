package com.rasel.first_project.controller;

import org.springframework.web.bind.annotation.*;

@RestController //REST(json), SOAP(xml)
@RequestMapping
@CrossOrigin(origins = "*")
public class DummyController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/square")
    public int square(@RequestParam int number){
        return number * number;
    }

    @GetMapping("/rasel")
    public String rasel(@RequestParam (name="xy") String me,
                       @RequestParam (required = false) String father,
                        @RequestParam (name="bb") int age){
                         return "i'm "+me+father+". age" + age;
    }


}
