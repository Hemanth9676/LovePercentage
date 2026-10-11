package com.hemanth.love_percentage.Love_Percentage.Service;


import com.hemanth.love_percentage.Love_Percentage.entity.LoveResult;
import com.hemanth.love_percentage.Love_Percentage.repository.LoveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class LoveService {

    private final LoveRepository loveRepository;

    public LoveService(LoveRepository loveRepository) {
        this.loveRepository = loveRepository;
    }

    public LoveResult calculateLove(
            String firstName, String secondName) {

        firstName = firstName.trim();
        secondName = secondName.trim();

        LoveResult result = loveRepository
                .findExistingPair(firstName, secondName)
                .orElse(new LoveResult());

        int percentage =
                ThreadLocalRandom.current().nextInt(1, 101);

        result.setFirstName(firstName);
        result.setSecondName(secondName);
        result.setPercentage(percentage);
        result.setCreatedAt(LocalDateTime.now());

        return loveRepository.save(result);
    }
}
