package environment;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Weapon;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestCell {
  /**
   * Lead Author: Amanda DiFalco
   */
  @Test
  public void testInitializedNoWeapon() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
    assertEquals(0, cell.getWeaponsCount());
  }

  @Test
  public void testAddWeapons() {
    Cell cell = new Cell();
    Weapon weapon = new MockWeapon();
    Weapon weapon2 = new MockWeapon();
    boolean success = cell.addWeapon(weapon);
    assertTrue(success);
    assertEquals(1, cell.getWeaponsCount());
    cell.addWeapon(weapon2);
    assertEquals(2, cell.getWeaponsCount());
  }

  @Test
  public void testRemoveWeapons() {
    Cell cell = new Cell();
    Weapon weapon = new MockWeapon();
    Weapon weapon2 = new MockWeapon();
    cell.addWeapon(weapon);
    assertEquals(1, cell.getWeaponsCount());
    cell.addWeapon(weapon2);
    assertEquals(2, cell.getWeaponsCount());
    cell.removeWeapon(weapon);
    assertEquals(1, cell.getWeaponsCount());
    assertNull(cell.getWeapon2());
    cell.removeWeapon(weapon2);
    assertEquals(0, cell.getWeaponsCount());
    assertNull(cell.getWeapon1());
  }

  @Test
  public void testFullCell() {
    Cell cell = new Cell();
    Weapon weapon = new MockWeapon();
    Weapon weapon2 = new MockWeapon();
    cell.addWeapon(weapon);
    assertEquals(1, cell.getWeaponsCount());
    cell.addWeapon(weapon2);
    assertEquals(2, cell.getWeaponsCount());
    Weapon weapon3 = new MockWeapon();
    boolean fail = cell.addWeapon(weapon3);
    assertFalse(fail);
    assertEquals(2, cell.getWeaponsCount());
  }

  //Tests for Decorator Pattern start here
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }

  @Test
  public void testAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob, cell.getLifeForm());
  }

  @Test
  public void testCanNotAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    cell.addLifeForm(bob);
    cell.addLifeForm(fred);
    assertEquals(bob, cell.getLifeForm());
  }

  @Test
  public void testRemoveLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    Cell cell = new Cell();
    cell.addLifeForm(bob);
    assertEquals(bob, cell.getLifeForm());
    cell.removeLifeForm();
    assertNull(cell.getLifeForm());
  }

}
