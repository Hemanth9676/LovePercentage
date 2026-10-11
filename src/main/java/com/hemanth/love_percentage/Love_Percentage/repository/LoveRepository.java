package com.hemanth.love_percentage.Love_Percentage.repository;


import com.hemanth.love_percentage.Love_Percentage.entity.LoveResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoveRepository
        extends JpaRepository<LoveResult, Long> {

    @Query("""
        SELECT l FROM LoveResult l
        WHERE
        (LOWER(l.firstName) = LOWER(:firstName)
         AND LOWER(l.secondName) = LOWER(:secondName))
        OR
        (LOWER(l.firstName) = LOWER(:secondName)
         AND LOWER(l.secondName) = LOWER(:firstName))
""")
    Optional<LoveResult> findExistingPair(
            @Param("firstName") String firstName,
            @Param("secondName") String secondName);
}
