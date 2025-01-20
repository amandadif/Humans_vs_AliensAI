package state;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.Random;

public class NoWeaponState extends ActionState {
  Environment environment = context.getEnvironment();

  public NoWeaponState(AiContext context, LifeForm lifeform) {
    super(context, lifeform);
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
      if (lifeform.getRow() > -1) {
        Weapon[] weapons = environment.getWeapons(lifeform.getRow(), lifeform.getCol());
        if (weapons != null && weapons.length > 0 && weapons[0] != null) {
          Weapon[] wep = environment.getWeapons(lifeform.getRow(), lifeform.getCol());
          lifeform.pickUpWeapon(wep[0]);
          context.setCurrentState(context.getHasWeaponState());
        } else {
          search();
        }
      }
    }
    environment.notifyObservers(lifeform.getRow(), lifeform.getCol(),
        lifeform, lifeform.getWeapon(), lifeform.getWeapon2());
  }

}
