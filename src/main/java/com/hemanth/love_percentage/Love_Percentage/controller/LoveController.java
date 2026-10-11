package com.hemanth.love_percentage.Love_Percentage.controller;

import com.hemanth.love_percentage.Love_Percentage.Service.LoveService;
import com.hemanth.love_percentage.Love_Percentage.entity.LoveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/love")
public class LoveController {

    @Autowired
    private LoveService loveService;

    @PostMapping("/calculate")
    public LoveResult calculateLove(@RequestParam String firstName, @RequestParam String secondName) {

        if (firstName.isBlank() || secondName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both names are required");
        }

        if (firstName.length() > 100 || secondName.length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Names must be 100 characters or fewer");
        }

        return loveService.calculateLove(firstName, secondName);
    }

}
