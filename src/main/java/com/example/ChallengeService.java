package com.example;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChallengeService {

  @Autowired
  ChallengeRepository challengeRepository;

  public boolean isValidBody(Challenge challenge) {
    if (challenge.getDescription() == null || challenge.getMonth() == null) {
      return false;
    }

    return true;
  }

  public List<Challenge> getAllChallenges() {
    return challengeRepository.findAll();
  }

  public boolean addChallenges(Challenge challenge) {

    if (isValidBody(challenge)) {
      challengeRepository.save(challenge);
      return true;
    }
    return false;
  }

  public Challenge getChallenge(Long id) {

    Optional<Challenge> challenge = challengeRepository.findById(id);
    return challenge.orElse(null);
  }

  public boolean updateChallenge(Long id, Challenge updatedChallenge) {

    if (isValidBody(updatedChallenge)) {
      Optional<Challenge> challenge = challengeRepository.findById(id);
      if (challenge.isPresent()) {
        Challenge challengeToUpdate = challenge.get();
        challengeToUpdate.setMonth(updatedChallenge.getMonth());
        challengeToUpdate.setDescription(updatedChallenge.getDescription());
        challengeRepository.save(challengeToUpdate);
        return true;

      }
    }
    return false;

  }

  public boolean deleteChallenge(Long id) {
    challengeRepository.deleteById(id);
    Optional<Challenge> challenge = challengeRepository.findById(id);
    return challenge.isEmpty();

  }

}
