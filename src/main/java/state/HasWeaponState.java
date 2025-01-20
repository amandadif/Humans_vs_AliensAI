package state;

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;

import java.util.Random;

public class HasWeaponState extends ActionState {
  Environment environment = context.getEnvironment();

  public HasWeaponState(AiContext context, LifeForm lifeform) {
    super(context, lifeform);
  }

  void attackTarget() {
    if (lifeform.getWeapon().getShotsLeft() > 0) {
      if (findTarget() != null) {
        if ((findTarget() instanceof Human
                && lifeform instanceof Alien) || (findTarget() instanceof Alien
            && lifeform instanceof Human)) {
          lifeform.attack(findTarget(), lifeform.getWeapon().getMaxRange());
          if (findTarget() != null) {
            environment.notifyObservers(findTarget().getRow(), findTarget().getCol(),
                    findTarget(), findTarget().getWeapon(), findTarget().getWeapon2());
            if (findTarget().getCurrentLifePoints() == 0) {
              context.setCurrentState(context.getDeadState());
            }
          }
        }
      } else {
        search();
      }
    } else {
      context.setCurrentState(context.getOutOfAmmoState());
    }
  }

  /**
   *
   * @return
   */
  public LifeForm findTarget() {

    if (lifeform == null) {
      System.out.println("No life form found at focus.");
      return null;
    }

    String direction = lifeform.getDirection();
    LifeForm target = null;
    double distance = 0;

    switch (direction) {
      case "North":
        for (int i = lifeform.getRow() - 1; i >= 0; i--) { // Move northward (decreasing rows)
          if (environment.getLifeForm(i, lifeform.getCol()) != null) {
            target = environment.getLifeForm(i, lifeform.getCol());
            distance = environment.getDistance(lifeform, target);
            break; // Stop at the first target
          }
        }
        break;

      case "East":
        for (int i = lifeform.getCol() + 1;
             i < environment.getNumCols(); i++) { // Move eastward (increasing cols)
          if (environment.getLifeForm(lifeform.getRow(), i) != null) {
            target = environment.getLifeForm(lifeform.getRow(), i);
            distance = environment.getDistance(lifeform, target);
            break; // Stop at the first target
          }
        }
        break;

      case "South":
        for (int i = lifeform.getRow() + 1;
             i < environment.getNumRows(); i++) { // Move southward (increasing rows)
          if (environment.getLifeForm(i, lifeform.getCol()) != null) {
            target = environment.getLifeForm(i, lifeform.getCol());
            distance = environment.getDistance(lifeform, target);
            break; // Stop at the first target
          }
        }
        break;

      case "West":
        for (int i = lifeform.getCol() - 1; i >= 0; i--) { // Move westward (decreasing cols)
          if (environment.getLifeForm(lifeform.getRow(), i) != null) {
            target = environment.getLifeForm(lifeform.getRow(), i);
            distance = environment.getDistance(lifeform, target);
            break; // Stop at the first target
          }
        }
        break;

      default:
        System.out.println("Invalid direction: " + direction);
        return null;
    }

    /*
    if (target == null) {
      System.out.println("No target found in the direction: " + direction);
      return null;
    } else {
      System.out.println("Found target: " + target);
    }

     */
    if (target != null && target.getCurrentLifePoints() > 0) {
      return target;
    }
    return null;
  }


  void search() {
    Random random = new Random();
    String newDirection = lifeform.getDirection();
    while (newDirection.equals(lifeform.getDirection())) {
      int randomInt = random.nextInt(1, 5);
      switch (randomInt) {
        case 1:
          lifeform.setDirection("North");
          break;
        case 2:
          lifeform.setDirection("East");
          break;
        case 3:
          lifeform.setDirection("South");
          break;
        case 4:
          lifeform.setDirection("West");
          break;
        default: lifeform.setDirection("North");
      }
    }
    Random randomMove = new Random();
    int randomM = randomMove.nextInt(1,3);
    switch (randomM) {
      case 1: environment.moveLifeForm(lifeform);
        break;
      case 2:
        break;
      default: environment.moveLifeForm(lifeform);
    }
  }

  @Override
  void executeAction() {
    if (lifeform.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      attackTarget();
      if (findTarget() != null) {
        environment.notifyObservers(findTarget().getRow(), findTarget().getCol(),
                findTarget(), findTarget().getWeapon(), findTarget().getWeapon2());
      }
    }
    environment.notifyObservers(lifeform.getRow(),
            lifeform.getCol(), lifeform, lifeform.getWeapon(), lifeform.getWeapon2());

  }

}
