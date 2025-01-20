package command;

import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.Pistol;
import weapon.Weapon;

import static org.junit.Assert.*;

public class TestCommand {

  private LifeForm life;
  private Weapon wp;

  @Before
  public void setup() {
    life = new Human("Bob",30,5);
    wp = new Pistol();
    Environment.resetSingleton();
  }

  @Test
  public void testMoveSelectedLF() {
    Environment e = Environment.getEnvironment(20, 20);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("West");
    e.moveLifeForm(human);
    assertEquals(human, e.getLifeForm(5,2));
  }

  @Test
  public void testReloadPlayerWeapon() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    wp = new Pistol();
    e.addLifeForm(human, 5,5);
    human.pickUpWeapon(wp);
    human.attack(life,5);
    human.getWeapon().reload();
    assertEquals(10, wp.getCurrentAmmo());
  }

  @Test
  public void testTurnNorth() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("North");
    assertEquals("North", human.getDirection());
  }

  @Test
  public void testTurnSouth() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("South");
    assertEquals("South", human.getDirection());
  }

  @Test
  public void testTurnEast() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("East");
    assertEquals("East", human.getDirection());
  }

  @Test
  public void testTurnWest() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("West");
    assertEquals("West", human.getDirection());
  }

  @Test
  public void testDropWeaponWithSpace() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("North");
    human.pickUpWeapon(wp);
    assertEquals(wp, human.getWeapon());
    human.dropWeapon();
    assertNull(human.getWeapon());
  }
//
  @Test
  public void testDropWeaponWithoutSpace() {
    Environment e = Environment.getEnvironment(1, 1);
    Human human = new Human("Bobby", 5,5);
    Weapon w = new Pistol();
    Weapon w2 = new Pistol();
    Weapon w3 = new Pistol();
    e.addLifeForm(human, 0,0);
    e.addWeapon(w,0,0);
    e.addWeapon(w2,0,0);
    human.pickUpWeapon(w3);
    assertEquals(w3, human.getWeapon());
    human.dropWeapon();
    assertNull(human.getWeapon());
  }

  @Test
  public void testPickUpWeapon() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.pickUpWeapon(wp);
    assertEquals(wp, human.getWeapon());
  }
//
  @Test
  public void testPickUpSecondWeapon() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    Weapon w2 = new Pistol();
    e.addLifeForm(human, 5,5);
    human.pickUpWeapon(wp);
    assertEquals(wp, human.getWeapon());
    human.pickUpWeapon(w2);
    assertEquals(w2, human.getWeapon());
  }

}

