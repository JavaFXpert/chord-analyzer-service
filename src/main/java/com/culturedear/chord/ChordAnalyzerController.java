package com.culturedear.chord;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Intervals;
import org.jfugue.theory.Note;
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

  // TODO: Remove this static initializer when power chords are supported by JFugue
  static {
    Chord.chordMap.put("POW", new Intervals("1 5"));
  }

  private MusicChord musicChord;
  @RequestMapping("/analyze")
  public ResponseEntity<Object> identifyChordByNotes(@RequestParam(value = "notes") String notes) {
    Chord jfugueChord = null;
    musicChord = null;
    try {
      jfugueChord = Chord.fromNotes(notes);
      musicChord = new MusicChord(Note.getToneStringWithoutOctave(jfugueChord.getRoot().getValue()),
                                  jfugueChord.getChordType().toLowerCase(),
                                  Note.getToneStringWithoutOctave(jfugueChord.getBassNote().getValue()),
                                  jfugueChord.getInversion(),
                                  jfugueChord.isMajor(),
                                  jfugueChord.isMinor(),
                                  jfugueChord.toString());
    }
    catch (Exception e) {
      System.out.println("Exception encountered in ChordAnalyzerController#identifyChordByNotes: " + e);
    }
    return Optional.ofNullable(musicChord)
        .map(mc -> new ResponseEntity<>((Object)mc, HttpStatus.OK))
        .orElse(new ResponseEntity<>("Could not analyze the chord", HttpStatus.INTERNAL_SERVER_ERROR));

  }
}

