package state;

import environment.Environment;
import lifeform.Human;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Weapon;

import static org.junit.Assert.*;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestNoWeaponState {
  @Before
  public void setUp() {
    Environment.resetSingleton();
  }
  @Test
  public void testForWeapon() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    Weapon a = new MockWeapon();
    e.addWeapon(a, 5,5);
    e.addLifeForm(human, 5,5);
    ai.setCurrentState(ai.getNoWeaponState());
    ai.execute();
    assertEquals(ai.getHasWeaponState() ,ai.getCurrentState());
    assertEquals(a, human.getWeapon());
  }

  @Test
  public void testNoWeapon() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    String direction = human.getDirection();
    ai.setCurrentState(ai.getNoWeaponState());
    ai.execute();
    assertEquals(ai.getNoWeaponState() ,ai.getCurrentState());
    assertNull(human.getWeapon());
    assertNotEquals(direction, human.getDirection());
    human.setDirection("West");
    ai.execute();
    assertNotEquals("West", human.getDirection());
    System.out.println(human.getDirection());
  }

  @Test
  public void testIsDead() {
    Environment e = Environment.getEnvironment(12, 12);
    Human human = new Human("Bob", 5, 5);
    AiContext ai = new AiContext(human,e);
    e.addLifeForm(human, 5,5);
    ai.setCurrentState(ai.getNoWeaponState());
    human.takeHit(10);
    ai.execute();
    assertEquals(ai.getDeadState() ,ai.getCurrentState());
  }
}
