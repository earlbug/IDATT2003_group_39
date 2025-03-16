package controllers;

import models.Player;

import java.io.*;

/**
 * Responsible for reading and writing player-related data to local file.
 * Opening and closing of the fileWriter is done automatically by this class.
 */
public class PlayerReadWrite {
  String pathName = "src/main/resources/data/players.csv";
  char delimiter = ',';
  File playerFile = new File(pathName);


  public PlayerReadWrite() {
  }

  /**
   * Writes a player to local storage. The method automatically opens and closes the stream.
   * If a lot of players shall be written, another method with manual opening and closing of the stream
   * would be preferable to avoid opening and closing for every Player.
   *
   * @param player Player object to be written to local storage.
   * @throws IOException if file could not be written to
   */
  public void addPlayer (Player player) throws IOException {

    String stringToWrite = player.getName()
        + delimiter
        + player.getGamePiece()
        + "\n";
    char[] playerAsChar = stringToWrite.toCharArray();

    // Closes writer automatically
    try (FileWriter writer = new FileWriter(pathName, true)) {
      writer.write(playerAsChar,0, playerAsChar.length);
    } catch (IOException e) {
      throw new IOException("Could not write Player to file: " + pathName);
    }
  }

}