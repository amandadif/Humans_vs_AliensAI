package state;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.Random;

public class DeadState extends ActionState {

  Environment environment = context.getEnvironment();

  public DeadState(AiContext context, LifeForm lifeform) {
    super(context, lifeform);
  }

  void respawn() {
    /*
    LifeForm life = lifeform;
    if (lifeform.hasWeapon()) {
      Weapon wep = lifeform.getWeapon();
      Random randomI = new Random();
      int randomRow = randomI.nextInt(0, 13);
      int randomCol = randomI.nextInt(0, 13);
      lifeform.dropWeapon();
      if (wep != null) {
        environment.removeWeapon(wep, lifeform.getRow(), lifeform.getCol());
        environment.addWeapon(wep, randomRow, randomCol);
      }
    }
    if (lifeform.getRow() < 0) {
      environment.removeLifeForm(0, 0);
    } else {
      environment.removeLifeForm(lifeform.getRow(), lifeform.getCol());
    }
    if (lifeform.getRow() < 0) {
      environment.addLifeForm(life, 0,0);
    } else {
      environment.addLifeForm(life, life.getRow(),life.getCol());
    }
    environment.addLifeForm(life, life.getRow(),life.getCol());
    lifeform.respawnHealth();
    context.setCurrentState(context.getNoWeaponState());

     */
    LifeForm life = lifeform;
    Random randomI = new Random();

    // Handle weapon if present
    if (lifeform.hasWeapon()) {
      Weapon wep = lifeform.getWeapon();
      int randomRow = randomI.nextInt(environment.getNumRows()); // Include 0
      int randomCol = randomI.nextInt(environment.getNumCols()); // Include 0
      lifeform.dropWeapon();
      environment.removeWeapon(wep, lifeform.getRow(), lifeform.getCol());
      environment.addWeapon(wep, randomRow, randomCol);
    }

    // Remove the lifeform from its current position
    if (lifeform.getRow() < 0) {
      environment.removeLifeForm(0, 0);
    } else {
      environment.removeLifeForm(lifeform.getRow(), lifeform.getCol());
    }

    // Add the lifeform to the environment and reset position if needed
    if (lifeform.getRow() < 0 || lifeform.getCol() < 0) {
      environment.addLifeForm(lifeform, 0, 0);
      lifeform.setLocation(0, 0);
    } else {
      environment.addLifeForm(lifeform, lifeform.getRow(), lifeform.getCol());
    }

    // Reset health
    lifeform.respawnHealth();

    // Set state to NoWeaponState
    context.setCurrentState(context.getNoWeaponState());
  }

  @Override
  void executeAction() {
    /*
    if (lifeform.getCurrentLifePoints() == 0) {
      respawn();
      context.updateTime(1);
      System.out.println("Respawning...");
    }

     */
    respawn();
    System.out.println("Current state is: " + context.getCurrentState());
    environment.notifyObservers(lifeform.getRow(),
            lifeform.getCol(), lifeform, lifeform.getWeapon(), lifeform.getWeapon2());
  }
}
