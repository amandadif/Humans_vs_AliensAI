package lifeform;

import exceptions.RecoveryRateException;
import org.junit.Test;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;

import static org.junit.Assert.assertEquals;

public class TestHuman {

  @Test
  public void testDefaultGetMaxSpeed() {
    Human bob = new Human("bob",30,5);
    int m = bob.getMaxSpeed();
    assertEquals(3, m);
  }


  @Test
  public void testInitialization() {
    Human gary;
    gary = new Human("Gary", 200, 100);
    assertEquals("Gary", gary.getName());
    assertEquals(200, gary.getCurrentLifePoints());
    assertEquals(100, gary.getArmorPoints());
  }

  @Test
  public void testSetArmor() {
    Human gary;
    gary = new Human("Gary", 200, 100);
    gary.setArmorPoints(101);
    assertEquals(101, gary.getArmorPoints());
  }

  @Test
  public void testGetArmor() {
    Human gary;
    gary = new Human("Gary", 200, 100);
    assertEquals(100, gary.getArmorPoints());
  }

  @Test
  public void testPositive() {
    Human gary;
    gary = new Human("Gary", 200, -5);
    assertEquals(0, gary.getArmorPoints());
    gary.setArmorPoints(-1000);
    assertEquals(0, gary.getArmorPoints());
  }

  @Test
  public void testHumanStrength() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 10);
    assertEquals(5, gary.getAttackStrength());
  }

  @Test
  public void testDamageArmorZero() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 0);
    gary.takeHit(10);
    assertEquals(20, gary.getCurrentLifePoints());
  }

  @Test
  public void testWhenArmorOverDamage() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 20);
    gary.takeHit(15);
    assertEquals(30, gary.getCurrentLifePoints());
  }

  @Test
  public void testWhenArmorUnderDamage() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 10);
    gary.takeHit(15);
    assertEquals(25, gary.getCurrentLifePoints());
  }

  @Test
  public void testWhenArmorEqualsDamage() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 20);
    gary.takeHit(20);
    assertEquals(30, gary.getCurrentLifePoints());
  }

  @Test
  public void testArmorDead() throws RecoveryRateException {
    Human gary;
    gary = new Human("Gary", 30, 20);
    gary.takeHit(50);
    assertEquals(0, gary.getCurrentLifePoints());
  }
}
