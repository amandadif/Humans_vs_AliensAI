package state;

import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestHasWeaponState {
  @Before
  public void resetEnvironment() {
    Environment.resetSingleton();
  }
  @Test
  public void testNoTarget() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    Weapon a = new MockWeapon();
    e.addLifeForm(human, 5, 5);
    e.addWeapon(a, 5,5);
    human.pickUpWeapon(a);
    ai.setCurrentState(ai.getHasWeaponState());
    System.out.println(human.getRow());
    System.out.println(human.getCol());
    ai.execute();
    System.out.println(human.getRow());//Rows and cols should be different 1/2 the time
    System.out.println(human.getCol());
    assertNotEquals("North", human.getDirection());
  }

  @Test
  public void testSameAndDiffType() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    Alien alien = new Alien("Billy", 20);
    AiContext ai = new AiContext(human,e);
    Weapon a = new MockWeapon();
    e.addLifeForm(human, 5, 5);
    e.addLifeForm(alien, 4, 5);
    e.addWeapon(a, 5,5);
    human.pickUpWeapon(a);
    ai.setCurrentState(ai.getHasWeaponState());
    ai.execute();
    assertEquals(10, alien.getCurrentLifePoints());
    e.removeLifeForm(4,5);
    Human human2 = new Human("Bobby", 20, 5);
    e.addLifeForm(human2, 4, 5);
    ai.execute();
    assertEquals(20, human2.getCurrentLifePoints());
  }

  @Test
  public void testValidOneShot() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    Weapon w = new MockWeapon();
    ai.setCurrentState(ai.getHasWeaponState());
    e.addLifeForm(human, 5, 5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    w.fire(10);
    w.fire(10);
    Alien alien = new Alien("Billy", 100);
    e.addLifeForm(alien, 4, 5);
    ai.execute();
    assertEquals(90, alien.getCurrentLifePoints());
  }

  @Test
  public void testOutOfRange() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    Weapon w = new MockWeapon();
    ai.setCurrentState(ai.getHasWeaponState());
    e.addLifeForm(human, 5, 5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    Alien alien = new Alien("Billy", 100);
    e.addLifeForm(alien, 11, 11);
    ai.execute();
    assertEquals(100, alien.getCurrentLifePoints());
  }

  @Test
  public void testIfDead() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5, 5);
    ai.setCurrentState(ai.getNoWeaponState());
    human.takeHit(100);
    ai.execute();
    assertEquals(ai.getDeadState(), ai.getCurrentState());
    assertEquals(0, human.getCurrentLifePoints());
  }
}
