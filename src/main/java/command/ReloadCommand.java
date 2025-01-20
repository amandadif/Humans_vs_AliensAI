package command;

import environment.Environment;
import gameboard.GameBoard;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;

public class ReloadCommand implements Command {


  /**
   * executes the reload command
   */
  public void execute() {
    Environment e = Environment.getInstance();
    GameBoard gameBoard = GameBoard.getInstance();
    LifeForm l = null;
    /*
    if(e.getLifeForm(e.focusRow, e.focusCol) != null) {
      l = e.getLifeForm(e.focusRow, e.focusCol);
    } else {
      System.out.println("No life form to reload");
    }

     */
    if (gameBoard.getLifeform() != null) {
      l = gameBoard.getLifeform();
    } else {
      System.out.println("No life form to reload");
    }

    if (l != null && l.hasWeapon()) {
      Weapon weapon = l.getWeapon();
      weapon.reload();
      System.out.println("Weapon Reloaded");
      e.notifyObservers(l.getRow(), l.getCol(),l,l.getWeapon(),l.getWeapon2());
    } else {
      System.out.println("No weapon to reload");
    }

  }

  @Override
  public boolean isExecuted() {
    return false;
  }

  @Override
  public void reset() {

  }
}
