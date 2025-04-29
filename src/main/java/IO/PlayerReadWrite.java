package IO;

import java.util.HashMap;
import java.util.Map;
import models.Player;

import java.io.*;

/**
 * Responsible for reading and writing player-related data to local file.
 * Opening and closing of the fileWriter is done automatically by this class.
 *
 * @author Erlend Sundsdal
 * @since 0.1.0
 * @version 0.1.0
 */
public class PlayerReadWrite {
  String pathName = "src/main/resources/data/players.csv";
  char delimiter = ',';

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
        + player.getColor()
        + "\n";
    char[] playerAsChar = stringToWrite.toCharArray();

    // Closes writer automatically
    try (FileWriter writer = new FileWriter(pathName, true)) {
      writer.write(playerAsChar,0, playerAsChar.length);
    } catch (IOException e) {
      throw new IOException("Could not write Player to file: " + pathName);
    }
  }

  /**
   * Reads player data from a local file, where each line contains player name and game piece,
   * separated by a delimiter, and returns it as a map.
   *
   * @return a map where the key is the player's name and the value is their game piece
   * @throws IOException if the file cannot be read
   */
  public Map<String, String> getPlayers() throws IOException {
    Map<String, String> playerMap = new HashMap<>();

    // Opens a BufferedReader
    try(BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
      String line;
      // Reads all lines, and adds them to the HashMap
      while ((line = reader.readLine()) != null) {
        String[] readValues = line.split(",");
        playerMap.put(readValues[0], readValues[1]);
      }

      return playerMap;

    } catch (IOException e) {
      throw new IOException("File could not be read: " + pathName);
    }
  }
}