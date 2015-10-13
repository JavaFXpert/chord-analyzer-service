package com.culturedear.chord;

/**
 * Created by jamesweaver on 10/13/15.
 */
public class MusicChord {
  private final String name;
  private final String root;
  private final int inversion;
  private final boolean isMajor;
  private final boolean isMinor;

  public MusicChord(String name, String root, int inversion, boolean isMajor, boolean isMinor) {
    this.name = name;
    this.root = root;
    this.inversion = inversion;
    this.isMajor = isMajor;
    this.isMinor = isMinor;
  }

  public String getName() {
    return name;
  }

  public String getRoot() {
    return root;
  }

  public int getInversion() {
    return inversion;
  }

  public boolean isMajor() {
    return isMajor;
  }

  public boolean isMinor() {
    return isMinor;
  }
}
