package environment;

import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;

import lifeform.MockLifeForm;

import org.junit.Before;

import org.junit.Test;

import weapon.MockWeapon;

import static org.junit.Assert.*;

/**
 * Author Madison
 */
public class TestEnvironment {

  @Test
  public void testMoveLifeFormNorth() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 5,5);
    alien.setDirection("North");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(3,5));
  }

  @Test
  public void testMoveLifeFormSouth() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 5,5);
    alien.setDirection("South");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(7,5));
  }

  @Test
  public void testMoveLifeFormWest() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 5,5);
    alien.setDirection("West");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(5,3));

    alien.setDirection("East");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(5,5));
    e.moveLifeForm(alien);
    e.moveLifeForm(alien);
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(5,9));
  }

  @Test
  public void testMoveLifeFormEast() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 5,5);
    alien.setDirection("East");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(5,7));
  }

  @Test
  public void testMoveLifeFormToEdge() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 9,9);
    alien.setDirection("East");
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(9,9));
  }

  @Test
  public void testMove1() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Bobby", 5);
    e.addLifeForm(alien, 5,5);
    e.moveLifeForm(alien);
    assertEquals(alien, e.getLifeForm(3,5));
  }

  @Test
  public void testMove() {
    Environment e = Environment.getEnvironment(10, 10);
    Human human = new Human("Bobby", 5,5);
    e.addLifeForm(human, 5,5);
    human.setDirection("West");
    e.moveLifeForm(human);
    assertEquals(human, e.getLifeForm(5,2));
    human.setDirection("East");
    e.moveLifeForm(human);
    assertEquals(human, e.getLifeForm(5,5));
    e.moveLifeForm(human);
    e.moveLifeForm(human);
    //assertEquals(human, e.getLifeForm(5,8));
    human.setDirection("South");
    e.moveLifeForm(human);
    e.moveLifeForm(human);
    //assertEquals(human, e.getLifeForm(8,8));
    human.setDirection("North");
    e.moveLifeForm(human);
    e.moveLifeForm(human);
    e.moveLifeForm(human);
    //assertEquals(human, e.getLifeForm(2,8));
  }

  //lab5
  @Before
  public void setUp() throws Exception {
    Environment.resetSingleton();
  }

  @Test
  public void testInitializeSingleton() {
    Environment e = Environment.getEnvironment(1, 1);
    assertNull(e.getLifeForm(0, 0));
  }

  @Test
  public void testAddWeaponToLocation() {
    Environment e = Environment.getEnvironment(3, 4);
    MockWeapon weapon = new MockWeapon();
    e.addWeapon(weapon,1,1);
    assertNotNull(e.getWeapons(1,1));
  }

  @Test
  public void testRemoveWeapon() {
    Environment e = Environment.getEnvironment(3, 4);
    MockWeapon weapon = new MockWeapon();
    e.addWeapon(weapon,1,1);
    assertNotNull(e.getWeapons(1,1));
    assertEquals(weapon, e.removeWeapon(weapon,1,1));
  }

  @Test(expected = EnvironmentException.class)
  public void testGetDistanceDiffCol() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 4);
    double distance = e.getDistance(1, 2, 1, 3);
    assertEquals(5.0, distance, .01);
    LifeForm bob = new MockLifeForm("Bob", 30);
    bob.setLocation(1, 2);
    LifeForm steve = new MockLifeForm("Steve", 30);
    steve.setLocation(1, 3);
    assertEquals(5.0, e.getDistance(bob, steve), .01);
    assertEquals(5.0, e.getDistance(steve, bob), .01);
    LifeForm billy = new MockLifeForm("Billy", 30);
    e.getDistance(billy, bob);
  }

  @Test(expected = EnvironmentException.class)
  public void testGetDistanceDiffRow() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 4);
    assertEquals(10.0, e.getDistance(1, 1, 3, 1), .01);
    LifeForm bob = new MockLifeForm("Bob", 30);
    bob.setLocation(1, 1);
    LifeForm steve = new MockLifeForm("Steve", 30);
    steve.setLocation(3, 1);
    assertEquals(10.0, e.getDistance(bob, steve), .01);
    assertEquals(10.0, e.getDistance(steve, bob), .01);
    e.getDistance(4, 4, 3, 4);
  }

  @Test(expected = EnvironmentException.class)
  public void testGetDistanceDiffColAndRow() throws EnvironmentException {
    Environment e = Environment.getEnvironment(3, 4);
    assertEquals(14.1421, e.getDistance(1, 1, 3, 3), .01);
    LifeForm bob = new MockLifeForm("Bob", 30);
    bob.setLocation(1, 1);
    LifeForm steve = new MockLifeForm("Steve", 30);
    steve.setLocation(3, 3);
    assertEquals(14.1421, e.getDistance(bob, steve), .01);
    assertEquals(14.1421, e.getDistance(steve, bob), .01);
    e.getDistance(3, 5, 3, 4);
    e.getDistance(0, 0, -1, 0);
    e.getDistance(0, -1, 0, 0);
  }

  /**
   * test for Decorator pattern start here
   */
  @Test
  public void testCellInitialization() {
    Environment e = Environment.getEnvironment(1,1);
    assertNull(e.getLifeForm(0, 0));
  }

  @Test
  public void testCellExists2() {
    Environment c1 = Environment.getEnvironment(1, 1);
    assertNull(c1.getLifeForm(0, 0));
  }

  @Test
  public void testCellAddLifeForm() {
    Environment c2 = Environment.getEnvironment(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 30);
    assertTrue(c2.addLifeForm(bob, 1, 2));
    //assertEquals(bob, c2.getLifeForm(1, 2));
  }

  @Test
  public void testCellAddLifeForm2() {
    Environment c3 = Environment.getEnvironment(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 30);
    LifeForm steve = new MockLifeForm("Bob", 30);
    c3.addLifeForm(bob, 0, 0);
    assertEquals(bob, c3.getLifeForm(0, 0));
    c3.addLifeForm(steve, 1, 1);
    assertEquals(steve, c3.getLifeForm(1, 1));

  }

  @Test
  public void testCellRemoveLifeForm() {
    Environment c2 = Environment.getEnvironment(2, 3);
    LifeForm bob = new MockLifeForm("Bob", 30);
    c2.addLifeForm(bob, 1, 2);
    assertEquals(bob, c2.getLifeForm(1, 2));
    c2.removeLifeForm(1, 2);
    assertNull(c2.getLifeForm(1, 2));
  }


}
