package com.culturedear.chord;

import org.jfugue.theory.Chord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jamesweaver on 10/13/15.
 */
@RestController
public class ChordAnalyzerController {
  private MusicChord musicChord;
  @RequestMapping("/analyze")
  public MusicChord identifyChordByNotes(@RequestParam(value = "notes") String notes) {
    Chord jfugueChord = null;
    try {
      jfugueChord = Chord.fromNotes(notes);
      musicChord = new MusicChord(jfugueChord.toString(),
                                  jfugueChord.getRoot().toString(),
                                  jfugueChord.getInversion(),
                                  jfugueChord.isMajor(),
                                  jfugueChord.isMinor());
    }
    catch (Exception e) {
      System.out.println("Exception encountered: " + e);
      musicChord = new MusicChord("", "", 0, false, false);
    }
    return musicChord;
  }
}
