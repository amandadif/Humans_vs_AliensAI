package state;

import environment.Environment;
import lifeform.Human;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestDeadState {
  @Before
  public void setUp() throws Exception {
    Environment.resetSingleton();
  }
  @Test
  public void testWithWeapon() {
    Environment e = Environment.getEnvironment(12,12);
    Human human = new Human("Bob", 20, 5);
    AiContext ai = new AiContext(human, e);
    Weapon w = new MockWeapon();
    e.addLifeForm(human, 5,5);
    e.addWeapon(w, 5,5);
    human.pickUpWeapon(w);
    human.takeHit(30);
    System.out.println(human.getCurrentLifePoints());
    ai.setCurrentState(ai.getDeadState());
    ai.execute();
    assertEquals(ai.getNoWeaponState(), ai.getCurrentState());
    assertEquals(20, human.getCurrentLifePoints());
  }

  @Test
  public void testWithoutWeapon() {
    Environment e = Environment.getEnvironment(12,12);
    Human human = new Human("Bob", 20, 5);
    AiContext ai = new AiContext(human, e);
    ai.execute();
    human.takeHit(30);
    System.out.println(human.getCurrentLifePoints());
    ai.setCurrentState(ai.getDeadState());
    ai.execute();
    assertEquals(ai.getNoWeaponState(), ai.getCurrentState());
    assertEquals(20, human.getCurrentLifePoints());
  }

  @Test
  public void testy() {
    Environment e = Environment.getEnvironment(12,12);
    Human human = new Human("Bob", 20, 5);
    AiContext ai = new AiContext(human, e);
  }
}
