package com.example;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

  ChallengeService challengeService;

  public ChallengeController(ChallengeService challengeService) {
    this.challengeService = challengeService;
  }

  @GetMapping
  public ResponseEntity<List<Challenge>> getAllChallenges() {
    List<Challenge> result = challengeService.getAllChallenges();
    return new ResponseEntity<List<Challenge>>(result, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Challenge> getChallenges(@PathVariable Long id) {

    Challenge result = challengeService.getChallenge(id);
    if (result == null)
      return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<Challenge>(result, HttpStatus.OK);


  }

  @PostMapping
  public ResponseEntity<HttpStatus> addChallenge(@RequestBody Challenge challenge) {
    boolean isAdded = challengeService.addChallenges(challenge);
    if (isAdded)
      return new ResponseEntity<>(HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);


  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateChallenge(@PathVariable Long id,
      @RequestBody Challenge updatedChallenge) {

    boolean isUpdated = challengeService.updateChallenge(id, updatedChallenge);
    if (isUpdated) {
      return new ResponseEntity<String>("updated", HttpStatus.CREATED);
    }
    return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteChallenge(@PathVariable Long id) {
    boolean isDeleted = challengeService.deleteChallenge(id);
    if (isDeleted)
      return new ResponseEntity<HttpStatus>(HttpStatus.valueOf(202));
    return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
  }

}
