package state;

import environment.Environment;
import lifeform.Human;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestOutOfAmmoState {
  @Test
  public void testInitialize() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    ai.setCurrentState(ai.getOutOfAmmoState());
    assertEquals(ai.getOutOfAmmoState(), ai.getCurrentState());
  }

  @Test
  public void testWeaponReload() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    Weapon w = new MockWeapon();
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    ai.setCurrentState(ai.getOutOfAmmoState());
    ai.execute();
    assertEquals(3, w.getCurrentAmmo());
  }

  @Test
  public void testCorrectState() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    Weapon w = new MockWeapon();
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    ai.setCurrentState(ai.getOutOfAmmoState());
    ai.execute();
    assertEquals(ai.getHasWeaponState(), ai.getCurrentState());
  }

  @Test
  public void testIfDead() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    Weapon w = new MockWeapon();
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    ai.setCurrentState(ai.getOutOfAmmoState());
    human.takeHit(20);
    ai.execute();
    assertEquals(ai.getDeadState(), ai.getCurrentState());
  }
}
