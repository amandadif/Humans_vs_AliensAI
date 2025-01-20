package command;

import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;

public class AcquireWeaponCommand implements Command {

  Human bob = new Human("bob", 10, 10);
  private boolean executed = false;

  /**
   * executes the command
   */
  public void execute() {
    Environment e = Environment.getInstance();
    LifeForm lifeform = null;
    if (e.getLifeForm(e.focusRow, e.focusCol) != null) {
      lifeform = e.getLifeForm(e.focusRow, e.focusCol);
    } else {
      System.out.println("No lifeform to pick up weapon");
    }

    if (e.getWeapons(e.focusRow, e.focusCol) != null && lifeform != null) {
      Weapon[] weapons = (e.getWeapons(e.focusRow, e.focusCol));
      lifeform.pickUpWeapon(weapons[0]);
      e.removeWeapon(weapons[0], e.focusRow, e.focusCol);
      System.out.println("picked up: " + weapons[0]);
    }
    e.notifyObservers(e.focusRow,e.focusCol, e.getLifeForm(e.focusRow, e.focusCol),
            lifeform.getWeapon(), lifeform.getWeapon2());
  }

  @Override
  public boolean isExecuted() {
    return executed;
  }

  @Override
  public void reset() {

  }
}
