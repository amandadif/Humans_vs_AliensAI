package command;

import environment.Environment;
import gameboard.GameBoard;
import lifeform.LifeForm;
import weapon.Weapon;

public class DropWeaponCommand implements Command {

  private boolean executed = false;

  @Override
  public void execute() {
    Environment e = Environment.getInstance();
    GameBoard g = GameBoard.getInstance();
    LifeForm l = null;
    if (g.getLifeform() != null) {
      l = g.getLifeform();
    } else {
      System.out.println("No life form to drop weapon");
    }

    if (l != null && l.hasWeapon()) {
      Weapon weapon  = l.getWeapon();
      e.addWeapon(weapon, e.focusRow, e.focusCol);
      l.dropWeapon();
      e.notifyObservers(l.getRow(),l.getCol(),l, l.getWeapon(), l.getWeapon2());
    } else {
      System.out.println("No weapon to drop");
    }
    System.out.println("Weapon dropped");
    executed = true;
  }

  @Override
  public boolean isExecuted() {
    return executed;
  }

  @Override
  public void reset() {
    executed = false;
  }
}
