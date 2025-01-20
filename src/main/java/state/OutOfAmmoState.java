package state;

import environment.Environment;
import lifeform.LifeForm;

public class OutOfAmmoState extends ActionState {

  public OutOfAmmoState(AiContext context, LifeForm lifeform) {
    super(context, lifeform);
  }

  Environment environment = context.getEnvironment();

  @Override
  void executeAction() {
    if (lifeform.getCurrentLifePoints() <= 0) {
      context.setCurrentState(context.getDeadState());
    } else if (lifeform.hasWeapon()) {
      lifeform.getWeapon().reload();
      context.setCurrentState(context.getHasWeaponState());
    }
    environment.notifyObservers(lifeform.getRow(), lifeform.getCol(),
        lifeform, lifeform.getWeapon(), lifeform.getWeapon2());
  }
}
