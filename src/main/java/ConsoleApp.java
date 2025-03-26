import controllers.BoardGame;
import controllers.Dice;
import java.util.Scanner;
import models.Player;

public class ConsoleApp {

  private final BoardGame boardGame = new BoardGame();
  private final Player p1 = new Player("Ola", boardGame);
  private final Player p2 = new Player("Per", boardGame);
  private final Dice dice = new Dice(2);

  public static void main(String[] args) {
    ConsoleApp app = new ConsoleApp();
    app.run();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    String command;

    boardGame.createBoard(10);
    boardGame.addPlayer(p1);
    boardGame.addPlayer(p2);

    System.out.println("Welcome to the Board Game!");
    System.out.println("Enter 'roll' to roll the dice, 'exit' to quit.");

    System.out.println(p1.getName() + " " + p1.getCurrentTile().getTileId());

    while (true) {
      System.out.println("Enter 1 to roll the dice, 2 to exit:");
      command = scanner.nextLine();
      switch (command) {
        case "1":
          int result = dice.rollAllDice();
          p1.move(result);
          System.out.println(p1.getName() + " " + p1.getCurrentTile().getTileId());
          break;
        case "2":
          System.out.println("Exiting the game.");
          return;
        default:
          System.out.println("Invalid command. Please enter 1 or 2.");
      }
    }
  }
}
