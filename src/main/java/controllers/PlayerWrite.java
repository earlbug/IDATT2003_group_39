package controllers;

import models.Player;
import java.io.*;

/**
 * Responsible for reading and writing player-related data to local file.
 */
public class PlayerWrite extends Writer {
  String pathName = "src/main/resources/data/players.csv";
  String DELIMITER = ",";
  File playerFile = new File(pathName);

  public PlayerWrite() {
  }

  public void addPlayer (Player player) throws Exception{
    try {
      FileWriter writer = new FileWriter(pathName);
      writer.write(player.getName() + DELIMITER + );

    }
    writer.close();
  }




  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

  }

  @Override
  public void flush() throws IOException {

  }

  @Override
  public void close() throws IOException {

  }



}