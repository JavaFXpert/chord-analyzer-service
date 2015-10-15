package com.culturedear.chord;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.jfugue.theory.Chord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by jamesweaver on 10/13/15.
 */
@RestController
public class ChordAnalyzerController {
  private MusicChord musicChord;
  @RequestMapping("/analyze")
  public ResponseEntity<Object> identifyChordByNotes(@RequestParam(value = "notes") String notes) {
    Chord jfugueChord = null;
    musicChord = null;
    try {
      jfugueChord = Chord.fromNotes(notes);
      musicChord = new MusicChord(jfugueChord.getRoot().toString(),
                                  jfugueChord.getChordType(),
                                  jfugueChord.getBassNote().toString(),
                                  jfugueChord.getInversion(),
                                  jfugueChord.isMajor(),
                                  jfugueChord.isMinor(),
                                  jfugueChord.toString());
    }
    catch (Exception e) {
      System.out.println("Exception encountered in ChordAnalyzerController#identifyChordByNotes: " + e);
    }
    //musicChord = new MusicChord("", "", 0, false, false);
    return Optional.ofNullable(musicChord)
        .map(mc -> new ResponseEntity<>((Object)mc, HttpStatus.OK))
        .orElse(new ResponseEntity<>("Could not analyze the chord", HttpStatus.INTERNAL_SERVER_ERROR));

  }
}

