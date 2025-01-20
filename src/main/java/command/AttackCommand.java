package command;

import environment.Environment;
import gameboard.GameBoard;
import lifeform.LifeForm;

public class AttackCommand implements Command {


  /**
   * executes command
   */
  public void execute() {


    Environment e = Environment.getInstance();
    GameBoard g = GameBoard.getInstance();
    //LifeForm attacker = e.getLifeForm(e.focusRow, e.focusCol);
    LifeForm attacker = g.getLifeform();

    if (attacker == null) {
      System.out.println("No life form found at focus.");
      return;
    }

    String direction = attacker.getDirection();
    LifeForm target = null;
    double distance = 0;

    switch (direction) {
      case "North":
        for (int i = e.focusRow - 1; i >= 0; i--) { // Move northward (decreasing rows)
          if (e.getLifeForm(i, e.focusCol) != null) {
            target = e.getLifeForm(i, e.focusCol);
            distance = e.getDistance(attacker, target);
            break; // Stop at the first target
          }
        }
        break;

      case "East":
        for (int i = e.focusCol + 1; i < e.getNumCols(); i++) { // Move eastward (increasing cols)
          if (e.getLifeForm(e.focusRow, i) != null) {
            target = e.getLifeForm(e.focusRow, i);
            distance = e.getDistance(attacker, target);
            break; // Stop at the first target
          }
        }
        break;

      case "South":
        for (int i = e.focusRow + 1; i < e.getNumRows(); i++) { // Move southward (increasing rows)
          if (e.getLifeForm(i, e.focusCol) != null) {
            target = e.getLifeForm(i, e.focusCol);
            distance = e.getDistance(attacker, target);
            break; // Stop at the first target
          }
        }
        break;

      case "West":
        for (int i = e.focusCol - 1; i >= 0; i--) { // Move westward (decreasing cols)
          if (e.getLifeForm(e.focusRow, i) != null) {
            target = e.getLifeForm(e.focusRow, i);
            distance = e.getDistance(attacker, target);
            break; // Stop at the first target
          }
        }
        break;

      default:
        System.out.println("Invalid direction: " + direction);
        return;
    }

    if (target == null) {
      System.out.println("No target found in the direction: " + direction);
      return;
    }

    // Perform the attack
    attacker.attack(target, (int) distance);

    System.out.println("Attack executed: Target health now " + target.getCurrentLifePoints());
    e.notifyObservers(attacker.getRow(), attacker.getCol(),
            attacker, attacker.getWeapon(), attacker.getWeapon2());
    e.notifyObservers(target.getRow(), target.getCol(), target,
            target.getWeapon(), target.getWeapon2());
  }

  @Override
  public boolean isExecuted() {
    return false;
  }

  @Override
  public void reset() {

  }
}

