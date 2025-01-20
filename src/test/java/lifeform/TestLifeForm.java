package lifeform;

import environment.Environment;
import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import weapon.MockWeapon;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

import static org.junit.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import static junit.framework.TestCase.assertFalse;

/**
 * Author Madison
 */
public class TestLifeForm {

  @Test
  public void testDefaultCurrentDirection1() throws RecoveryRateException {
    Alien alien = new Alien("Bobby",5);
    assertEquals("North",alien.getDirection());
    Human human = new Human("Bob", 5,10);
    assertEquals("North",human.getDirection());
  }

  @Test
  public void testCurrentDirection2() throws RecoveryRateException {
    Alien alien = new Alien("Bobby", 5);
    alien.setDirection("West");
    assertEquals("West", alien.getDirection());
    alien.setDirection("East");
    assertEquals("East", alien.getDirection());
    Human human = new Human("Bob", 5,10);
    human.setDirection("South");
    assertEquals("South",human.getDirection());
    human.setDirection("North");
    assertEquals("North",human.getDirection());
  }

  @Test
  public void testDefaultGetMaxSpeed2() throws RecoveryRateException {
    Alien alien = new Alien("Bobby",5);
    assertEquals(2,alien.getMaxSpeed());
  }
  @Test
  public void testDefaultGetMaxSpeed() throws RecoveryRateException {
    Human human = new Human("Bob", 5,10);
    assertEquals(3,human.getMaxSpeed());
  }

  //lab5
  @Test
  public void testStoreRowAndCol() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    entity.setLocation(3,4);
    assertEquals(3, entity.getRow());
    assertEquals(4, entity.getCol());

  }

  @Test
  public void testInitializedToNegative() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    assertEquals(-1,entity.getRow());
    assertEquals(-1,entity.getCol());
  }

  @Test
  public void testNegativeParameter() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    entity.setLocation(-1,1);
    assertEquals(-1,entity.getRow());
    assertEquals(-1,entity.getCol());
  }

  /**
   * test for decorator pattern start here:
   */
  @Test
  public void testPoints() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals(40, entity.getCurrentLifePoints());
  }

  @Test
  public void testName() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
  }

  @Test
  public void testTakeHit1() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(10);
    assertEquals(30, entity.getCurrentLifePoints());
    entity.takeHit(100);
    assertEquals(0, entity.getCurrentLifePoints());
  }

  @Test
  public void testTakeHit2() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(10);
    assertEquals(30, entity.getCurrentLifePoints());
    entity.takeHit(15);
    assertEquals(15, entity.getCurrentLifePoints());
  }

  @Test
  public void testAttackStrength() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40, 50);
    entity.takeHit(10);
    assertEquals(50, entity.getAttackStrength());
    ;
  }

  @Test
  public void testAttackMethod() {
    LifeForm entity;
    LifeForm entity2;
    entity = new MockLifeForm("Bob", 40, 50);
    entity2 = new MockLifeForm("Dave", 150);
    entity.attack(entity2, 4);
    assertEquals(100, entity2.getCurrentLifePoints());
  }

  @Test
  public void testNoDeadAttack() {
    LifeForm entity;
    LifeForm entity2;
    entity = new MockLifeForm("Bob", 0, 50);
    entity2 = new MockLifeForm("Dave", 150);
    entity.attack(entity2, 4);
    assertEquals(150, entity2.getCurrentLifePoints());
  }

  /**
   * Lead Author: Donovan Yaukey
   * Co-Author: Logan Wilt
   */
  @Test
  public void testPickupWeapon() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon w1 = new MockWeapon();
    assertFalse(entity.hasWeapon());
    entity.pickUpWeapon(w1);
    assertTrue(entity.hasWeapon());
  }

  @Test
  public void testDropWeapon() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon w1 = new MockWeapon();
    entity.pickUpWeapon(w1);
    assertTrue(entity.hasWeapon());
    entity.dropWeapon();
    assertFalse(entity.hasWeapon());
  }

  @Test
  public void testAlreadyHasWeapon() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon w1 = new MockWeapon();
    Weapon w2 = new MockWeapon();
    entity.pickUpWeapon(w1);
    assertFalse(entity.pickUpWeapon(w2));
  }

  @Test
  public void testAttackWithMockWeapon() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon w1 = new MockWeapon();
    LifeForm entity2 = new MockLifeForm("Dave", 150);
    entity.pickUpWeapon(w1);
    entity.attack(entity2, 4);
    assertEquals(140, entity2.getCurrentLifePoints());
  }

  @Test
  public void testAttackWeaponNoAmmo() {
    Weapon w1 = new PlasmaCannon();
    w1.fire(5);
    w1.fire(5);
    w1.fire(5);
    w1.fire(5);
    assertEquals(0, w1.getShotsLeft());
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    LifeForm entity2 = new MockLifeForm("Dave", 150);
    entity.pickUpWeapon(w1);
    entity.attack(entity2, 3);
    assertEquals(100, entity2.getCurrentLifePoints());
  }

  @Test
  public void testAttackMeleeNoHurt() {
    Weapon w1 = new PlasmaCannon();
    w1.fire(5);
    w1.fire(5);
    w1.fire(5);
    w1.fire(5);
    assertEquals(0, w1.getShotsLeft());
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    LifeForm entity2 = new MockLifeForm("Dave", 150);
    entity.pickUpWeapon(w1);
    entity.attack(entity2, 7);
    assertEquals(150, entity2.getCurrentLifePoints());
  }

  @Test
  public void testReload() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon w1 = new Pistol();
    assertEquals(2, w1.getShotsLeft());
    LifeForm entity2 = new MockLifeForm("Dave", 150);
    entity.pickUpWeapon(w1);
    entity.attack(entity2, 30);
    assertEquals(1, w1.getShotsLeft());
    w1.reload();
    assertEquals(2, w1.getShotsLeft());
  }

  @Test
  public void testAttackWithPlasmaCannon() {
    LifeForm entity = new MockLifeForm("Bob", 100, 50);
    Weapon pc = new PlasmaCannon();
    LifeForm entity2 = new MockLifeForm("Dave", 150);
    entity.pickUpWeapon(pc);
    entity.attack(entity2, 4);
    assertEquals(100, entity2.getCurrentLifePoints());
  }


}
