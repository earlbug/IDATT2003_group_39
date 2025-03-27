import IO.BoardFileReaderJson;
import IO.BoardFileWriterJson;
import controllers.Board;
import controllers.BoardGame;
import IO.PlayerReadWrite;
import java.io.IOException;
import javafx.application.Application;
import models.GamePiece;
import models.LadderAction;
import models.Player;
import models.Tile;
import views.MainApp;

public class BoardGameApp {

  public static void main(String[] args) {

    BoardFileReaderJson boardReader = new BoardFileReaderJson();
    Board board;

    try {
      board = boardReader.getBoard("laddergame_1.json");
      Tile[] tiles = board.getTiles();
      for(Tile tile : tiles){
        System.out.println(tile.getTileId());
        if(tile.getLandAction() != null) {
          System.out.println(tile.getLandAction().toString());
        }
      }

    } catch (IOException e) {
      System.out.println("ioecxeption: " + e.getMessage());
    }





//    Board board = new Board();
//    for (int i = 0; i < 10; i++) {
//      board.addTile(new Tile(i));
//    }
//    board.getTile(5).setLandAction(new LadderAction(8, "Move player to tile 5"));
//
//    BoardFileWriterJson boardWriter = new BoardFileWriterJson();
//    try {
//      boardWriter.writeBoard(board, "ladderGame_1.json");
//    } catch (IOException e) {
//      System.out.println("ioexception: \n" + e.getMessage());
//    }
//
//    Application.launch(MainApp.class, args);





//    PlayerReadWrite playerIO = new PlayerReadWrite();
//    Player player = new Player("erlendPlayer2", new BoardGame(), GamePiece.THIMBLE);
//    try {
//      playerIO.addPlayer(player);
//    } catch (IOException e) {
//      System.out.println(e.getMessage());
//    }

  }
}
