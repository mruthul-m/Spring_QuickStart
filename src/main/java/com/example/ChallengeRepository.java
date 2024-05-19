package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

// provide the enitity table and its primary key in JpaRepository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {



}
