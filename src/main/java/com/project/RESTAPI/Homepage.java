package com.project.RESTAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homepage
{
    @GetMapping("/api")
    public String welcome()
    {
        return "welcome bro";
    }
    
}
