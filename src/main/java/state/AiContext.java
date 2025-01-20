package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AiContext implements TimerObserver {

  private LifeForm lifeform;
  private Environment environment;
  private ActionState currentState;
  private OutOfAmmoState outOfAmmoState;
  private HasWeaponState hasWeaponState;
  private NoWeaponState noWeaponState;
  private DeadState deadState;

  /**
   *
   * @param lf
   * @param e
   */
  public AiContext(LifeForm lf, Environment e) {
    lifeform = lf;
    environment = e;
    this.outOfAmmoState = new OutOfAmmoState(this, lf);
    this.hasWeaponState = new HasWeaponState(this, lf);
    this.noWeaponState = new NoWeaponState(this, lf);
    this.deadState = new DeadState(this, lf);
    currentState = noWeaponState;
  }

  /**
   *
   * @param time
   */
  @Override
  public void updateTime(int time) {
    if (lifeform.getCurrentLifePoints() <= 0) {
      setCurrentState(deadState);
    } else {
      currentState.executeAction();
      if (currentState == noWeaponState) {
        setCurrentState(noWeaponState);
      }
    }

  }

  /**
   *
   */
  public void execute() {
    currentState.executeAction();
    System.out.println("Current state is: " + currentState);
    //e.notifyObservers(getLifeForm().getRow(), getLifeForm().getCol(), lifeform,
    // lifeform.getWeapon(), lifeform.getWeapon2());
  }

  public OutOfAmmoState getOutOfAmmoState() {
    return this.outOfAmmoState;
  }

  public HasWeaponState getHasWeaponState() {
    return this.hasWeaponState;
  }

  public NoWeaponState getNoWeaponState() {
    return this.noWeaponState;
  }

  public DeadState getDeadState() {
    return this.deadState;
  }

  public void setCurrentState(ActionState newState) {
    this.currentState = newState;
  }

  public ActionState getCurrentState() {
    return currentState;
  }

  public LifeForm getLifeForm() {
    return lifeform;
  }

  public Environment getEnvironment() {
    return environment;
  }


}
