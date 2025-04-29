package models.validators;

import interfaces.TileAction;
import javafx.scene.paint.Color;
import models.Tile;

/**
 * <h3>Argument Validator</h3>
 *
 * <p>A utility class for validating arguments passed to constructors and methods. The class has
 * a private constructor to prevent instantiation.
 *
 * @author Tord Fosse
 * @since 0.1.0
 */
public class ArgumentValidator {


  public static void playerSetCurrentTileValidator(Tile tile){
    if (tile == null){
      throw new IllegalArgumentException("Current tile cannot be null");
    }
  }

  public static void playerSetColorValidator(Color color){
    if (color == null){
      throw new IllegalArgumentException("Color cannot be null");
    }
  }

  public static void playerSetNameValidator(String name){
    if (name.isEmpty()){
      throw new IllegalArgumentException("Player name cannot be empty");
    }
  }

  public static void tileSetTileIdValidator(int tileId){
    if (tileId < 0){
      throw new IllegalArgumentException("Tile id cannot be a negative number");
    }
  }

  public static void tileSetNextTileValidator(Tile nextTile){
    if (nextTile == null){
      throw new IllegalArgumentException("Next tile cannot be null");
    }
  }

  public static void tileSetLandActionValidator(TileAction tileAction){
    if (tileAction == null){
      throw new IllegalArgumentException("TileAction cannot be null");
    }
  }

  public static void playerSetId(int playerId){
    if (playerId < 0 || playerId > 3){
      throw new IllegalArgumentException("Player id cannot be a negative number and must be less than 4");
    }
  }

}
