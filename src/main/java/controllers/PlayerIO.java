package controllers;

import java.io.File;
import models.Player;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for reading and writing player-related data to local file.
 */
public class PlayerIO {
  String pathName = "src/main/resources/data/players.csv";
  File playerFile = new File(pathName);

  public PlayerIO() {
  }

  public void addPlayer (Player player) throws Exception{
    FileWriter writer = new FileWriter(pathName);
    writer.write("first write");
    writer.close();
  }

}