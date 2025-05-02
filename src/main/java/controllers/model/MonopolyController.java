package controllers.model;

import interfaces.TileAction;
import models.BoardGame;
import models.Player;
import models.actions.WinAction;
import org.slf4j.Logger;

public class MonopolyController extends GameController {

  private final Logger logger = org.slf4j.LoggerFactory.getLogger(MonopolyController.class);

  public MonopolyController(BoardGame boardGame) {
    super(boardGame);
  }

  @Override
  public void handlePlayerLooseCheck() {
    Player currentPlayer = boardGame.getCurrentPlayer();
    logger.debug("Player {} has a balance of {}$.", currentPlayer.getName(), currentPlayer.getMoney());
    if (boardGame.getCurrentPlayer().getMoney() < 0) {
      boardGame.removePlayer(currentPlayer);
      logger.debug("Player " + currentPlayer.getName() + " has gone bankrupt and has been removed from the game.");
    }
  }

  @Override
  public boolean isWinConditionMet(Player player) {
    logger.debug("Checking win condition for player {} on tile {}", player.getName(),
        player.getCurrentTile().getTileId());
    if (boardGame.getPlayers().size() == 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void handlePlayerWinCheck() {
    if (isWinConditionMet(boardGame.getCurrentPlayer())) {
      Player victoryousPlayer = boardGame.getPlayers().getFirst();
      notifyWinnerDetermined(victoryousPlayer);
    }
  }

}
