package environment;

import exceptions.EnvironmentException;
import gameplay.EnvironmentObserver;
import gameplay.EnvironmentSubject;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Environment implements EnvironmentSubject {

  private List<EnvironmentObserver> observers = new ArrayList<>();
  //private EnvironmentObserver observer;
  public int focusRow;
  public int focusCol;
  private Cell[][] cells;
  private int lastRow;
  private int lastCol;
  private int newRow;
  private int newCol;
  private static Environment singleton;

  private Environment(int rows, int col) {
    lastRow = rows;
    lastCol = col;
    //observer = null;

    cells = new Cell[rows][col];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < col; j++) {
        cells[i][j] = new Cell();
      }
    }
  }

  /**
   * @param rows number of rows.
   * @param col  number of columns.
   * @return a single instance of an Environment.
   */
  public static Environment getEnvironment(int rows, int col) {
    if (singleton == null) {
      singleton = new Environment(rows, col);
    }
    return singleton;
  }

  /**
   * @return one Environment instance
   */
  public static Environment getInstance() {
    if (singleton == null) {
      throw new EnvironmentException("Environment has not been initialized");
    }
    return singleton;
  }

  /**
   * @param lf  the life form added.
   * @param row the row you want to add it to.
   * @param col the column you want to add it to.
   * @return true if the lf is added, false if not.
   */
  public boolean addLifeForm(LifeForm lf, int row, int col) {
    if (lastRow > row && lastCol > col && cells[row][col].getLifeForm() == null) {
      cells[row][col].addLifeForm(lf);
      lf.setLocation(row, col);
      notifyObservers(row,col, lf, lf.getWeapon(), lf.getWeapon2());
      return true;
    }
    return false;
  }


  /**
   * @param row the row you want to remove lf from.
   * @param col the column you want to remove lf from.
   */
  public void removeLifeForm(int row, int col) {
    if (lastRow > row && lastCol > col) {
      cells[row][col].removeLifeForm();
    }
  }

  /**
   * @param row row number.
   * @param col column number.
   * @return the life form or null if there is no life form.
   */
  public LifeForm getLifeForm(int row, int col) {
    if (lastRow > row && lastCol > col) {
      return cells[row][col].getLifeForm();
    }
    return null;
  }

  /**
   * reset Environment to null.
   */
  public static void resetSingleton() {
    singleton = null;
  }

  /**
   *
   * @return the number of rows in the Environment.
   */
  public int getNumRows() {
    return lastRow;
  }

  /**
   *
   * @return the number of columns in the Environment.
   */
  public int getNumCols() {
    return lastCol;
  }

  /**
   *
   * @param weapon you want added.
   * @param row    you added to.
   * @param col    you added to.
   * @return true if the weapon is added, false if not.
   */
  public boolean addWeapon(Weapon weapon, int row, int col) {
    if (lastRow > row && lastCol > col) {
      return cells[row][col].addWeapon(weapon);
    }
    return false;
  }

  /**
   *
   * @param weapon you want removed.
   * @param row    it's in.
   * @param col    it's in.
   * @return the weapon that was removed, null if there is no weapon.
   */
  public Weapon removeWeapon(Weapon weapon, int row, int col) {
    if (lastRow > row && lastCol > col) {
      return cells[row][col].removeWeapon(weapon);
    }
    return null;
  }

  /**
   * clears the environment by removing life forms and weapons.
   */
  public void clearBoard() {
    for (int i = 0; i < lastRow; i++) {
      for (int j = 0; j < lastCol; j++) {
        cells[i][j].removeLifeForm();
        cells[i][j].clearWeapons();
      }
    }
  }

  /**
   *
   * @param row the weapon is in.
   * @param col the weapon is in.
   * @return the weapons in an array, null if there are no weapons.
   */
  public Weapon[] getWeapons(int row, int col) {
    if (lastRow > row && lastCol > col) {
      return cells[row][col].getWeapons();
    }
    return null;
  }

  /**
   *
   * @param row1 row one
   * @param col1 col one
   * @param row2 row two
   * @param col2 col two
   * @return the distance between life forms.
   * @throws EnvironmentException if out of bounds.
   */
  public double getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException {
    if (row1 < 0 || row2 < 0 || row1 > lastRow || row2 > lastRow) {
      throw new EnvironmentException("out of bounds");
    }
    if (col1 < 0 || col2 < 0 || col1 > lastCol || col2 > lastCol) {
      throw new EnvironmentException("out of bounds");
    }
    double distance;
    if (row1 == row2) {
      distance = Math.abs(col1 - col2) * 5;
    } else if (col1 == col2) {
      distance = Math.abs(row1 - row2) * 5;
    } else {
      distance = Math.sqrt(Math.pow(Math.abs(row1 - row2) * 5, 2)
              + Math.pow(Math.abs(col1 - col2) * 5, 2));
    }
    return distance;
  }

  /**
   *
   * @param lf1 life form
   * @param lf2 other life form
   * @return the distance between life forms.
   */
  public double getDistance(LifeForm lf1, LifeForm lf2) {
    return getDistance(lf1.getRow(), lf1.getCol(), lf2.getRow(), lf2.getCol());
  }


  @Override
  public void addObserver(EnvironmentObserver observer) {

    observers.add(observer);

  }

  @Override
  public void removeObserver(EnvironmentObserver observer) {

    observers.remove(observer);
  }

  @Override
  public void notifyObservers(int row, int col, LifeForm lf, Weapon weapon1, Weapon weapon2) {
    /*
    for (EnvironmentObserver observer : observers) {
      observer.updateCell(row, col, lf, weapon1, weapon2);
    }
    */


    for (EnvironmentObserver observer : observers) {
      for (int i = 0; i <= row; i++) {
        for (int j = 0; j <= col; j++) {
          LifeForm lifeform = getLifeForm(row, col);
          Weapon[] weapons = getWeapons(row, col);
          observer.updateCell(row, col, lifeform, weapons[0], weapons[1]);
        }
      }
    }


  }

  public void setGameBoardState(int row, int col, LifeForm lf, Weapon weapon1, Weapon weapon2) {

    notifyObservers(row, col, lf, weapon1, weapon2);
  }

  /**
   * moves the lf based on the direction.
   *
   * @param lf
   */
  public void moveLifeForm(LifeForm lf) {
    int row = lf.getRow();
    int col = lf.getCol();
    int targetRow = row;
    int targetCol = col;

    switch (lf.getDirection()) {
      case "North":
        targetRow -= lf.getMaxSpeed();
        break;
      case "South":
        targetRow += lf.getMaxSpeed();
        break;
      case "East":
        targetCol += lf.getMaxSpeed();
        break;
      case "West":
        targetCol -= lf.getMaxSpeed();
        break;
      default:
        targetCol += lf.getMaxSpeed();
        break;
    }

    if (targetRow < 0) {
      targetRow = 0;
    }

    if (targetCol < 0) {
      targetCol = 0;
    }

    if (targetRow >= lastRow) {
      targetRow = lastRow - 1;
    }

    if (targetCol >= lastCol) {
      targetCol = lastCol - 1;
    }

    if ((cells[targetRow][targetCol].getLifeForm() == null)) {
      if (cells[targetRow][targetCol].getLifeForm() == null) {
        removeLifeForm(row, col);
        notifyObservers(row, col, lf, lf.getWeapon(), lf.getWeapon());
        addLifeForm(lf, targetRow, targetCol);
        notifyObservers(targetRow, targetCol, lf, lf.getWeapon(), lf.getWeapon());
      }
    }

  }
}
