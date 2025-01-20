package gameboard;

import environment.Cell;
import environment.Environment;
import gameplay.EnvironmentObserver;
import lifeform.LifeForm;
import weapon.Weapon;

import javax.swing.JButton;

public class GameBoard implements EnvironmentObserver {


  private Cell[][] board;
  private static int row;
  private static int col;
  private static JButton[][] mapArray;
  private static Environment environment;
  private static GameBoard singleton;

  public GameBoard() {

  }

  private GameBoard(int rows, int cols, JButton[][] mapArray,
                    Environment environment) {
    this.row = rows;
    this.col = cols;
    this.mapArray = mapArray;
    this.environment = environment;
    this.board = new Cell[12][12];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        board[r][c] = new Cell();
      }
    }

  }


  /**
   *
   * @param rows
   * @param cols
   * @param mapArray
   * @param environment
   * @return the gameboard instance.
   */
  public static GameBoard getGameBoard(int rows, int cols,
                                       JButton[][] mapArray, Environment environment) {
    if (singleton == null) {
      singleton = new GameBoard(row, col, mapArray, environment);
    }
    return singleton;
  }

  /**
   *
   * @return the one instance of gameboard.
   */
  public static GameBoard getInstance() {
    if (singleton == null) {
      throw new IllegalStateException("Game board already initialized");
    }
    return singleton;
  }

  /**
   *
   * @return the lf in the environment.
   */
  public LifeForm getLifeform() {
    int focusRow = environment.focusRow;
    int focusCol = environment.focusCol;

    return Environment.getInstance().getLifeForm(focusRow, focusCol);
  }


  /**
   * updates the cell the lf is in.
   * @param row
   * @param col
   * @param lifeform
   * @param weapon1
   * @param weapon2
   */
  @Override
  public void updateCell(int row, int col, LifeForm lifeform,
                         Weapon weapon1, Weapon weapon2) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
      System.out.println("Invalid cell coordinates: (" + row + ", " + col + ")");
      return;
    }

    // Access the target cell
    Cell cell = board[row][col];

    // Update the LifeForm in the cell
    cell.addLifeForm(lifeform);

    // Update the weapons in the cell
    cell.addWeapon(weapon1);
    cell.addWeapon(weapon2);

    // Optionally log or print the changes for debugging
    System.out.println("Updated cell (" + row + ", " + col + ") with:");
    if (lifeform != null) {
      System.out.println("  LifeForm: " + lifeform.getName()
              + " (HP: " + lifeform.getCurrentLifePoints() + ")");
    } else {
      System.out.println("  LifeForm: None");
    }

    System.out.println("  Weapon 1: " + (weapon1 != null ? weapon1.toString() : "None"));
    System.out.println("  Weapon 2: " + (weapon2 != null ? weapon2.toString() : "None"));
  }

}
