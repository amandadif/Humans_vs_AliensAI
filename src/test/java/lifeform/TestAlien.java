package lifeform;

import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import org.junit.Test;
import recovery.RecoveryBehavior;
import recovery.RecoveryLinear;
import recovery.RecoveryFractional;

import static org.junit.Assert.assertEquals;

public class TestAlien {

  @Test
  public void testDefaultGetMaxSpeed2() throws RecoveryRateException {
    Alien al = new Alien("al",5);
    int m = al.getMaxSpeed();
    assertEquals(2, m);
  }

  @Test
  public void testInitialization() throws RecoveryRateException {
    Alien gary;
    gary = new Alien("Gary", 100);
    assertEquals("Gary", gary.getName());
    assertEquals(100, gary.getCurrentLifePoints());
  }

  @Test
  public void testAlienLinear() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior linear = new RecoveryLinear(10);
    gary = new Alien("Gary", 30, linear);
    gary.takeHit(15);
    gary.recover();
    assertEquals(25, gary.getCurrentLifePoints());
  }

  @Test
  public void testAlienFractional() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior fraction = new RecoveryFractional((double) 1 / 10);
    gary = new Alien("Gary", 30, fraction);
    gary.takeHit(15);
    gary.recover();
    assertEquals(17, gary.getCurrentLifePoints());
  }

  @Test
  public void testAlienStrength() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior fraction = new RecoveryFractional((double) 1 / 10);
    gary = new Alien("Gary", 30, fraction);
    assertEquals(10, gary.getAttackStrength());
  }

  @Test
  public void testAlienRecoveryRate() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior fraction = new RecoveryFractional((double) 1 / 10);
    gary = new Alien("Gary", 30, fraction, 15);
    assertEquals(15, gary.getRecoveryRate());
  }

  @Test
  public void testRecoverZero() throws RecoveryRateException {
    Alien gary;
    SimpleTimer time = new SimpleTimer();
    RecoveryBehavior linear = new RecoveryLinear(10);
    gary = new Alien("Gary", 30, linear, 0);
    time.addTimeObserver(gary);
    gary.takeHit(15);
    time.timeChanged();
    assertEquals(15, gary.getCurrentLifePoints());
  }

  @Test
  public void testRecoverOverZero() throws RecoveryRateException {
    Alien gary;
    SimpleTimer time = new SimpleTimer();
    RecoveryBehavior linear = new RecoveryLinear(10);
    gary = new Alien("Gary", 30, linear, 1);
    time.addTimeObserver(gary);
    gary.takeHit(15);
    time.timeChanged();
    assertEquals(25, gary.getCurrentLifePoints());
  }

  @Test
  public void testRecoverOverZero2() throws RecoveryRateException {
    Alien gary;
    SimpleTimer time = new SimpleTimer();
    RecoveryBehavior linear = new RecoveryLinear(10);
    gary = new Alien("Gary", 30, linear, 5);
    time.addTimeObserver(gary);
    gary.takeHit(15);
    time.timeChanged();
    time.timeChanged();
    time.timeChanged();
    time.timeChanged();
    time.timeChanged();
    assertEquals(25, gary.getCurrentLifePoints());
  }

  @Test
  public void testTimePassage() throws RecoveryRateException {
    Alien gary;
    SimpleTimer time = new SimpleTimer();
    RecoveryBehavior linear = new RecoveryLinear(10);
    gary = new Alien("Gary", 30, linear, 5);
    time.addTimeObserver(gary);
    gary.takeHit(15);
    time.timeChanged();
    time.timeChanged();
    assertEquals(2, time.getRound());
  }

  @Test
  public void testCombatRecovery() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior linear = new RecoveryLinear(10);
    SimpleTimer time = new SimpleTimer();
    gary = new Alien("Gary", 30, linear, 2);
    time.addTimeObserver(gary);
    gary.takeHit(15);

    time.timeChanged();
    assertEquals(15, gary.getCurrentLifePoints());

    time.timeChanged();
    assertEquals(25, gary.getCurrentLifePoints());
  }

  @Test
  public void testRemoveNoRecovery() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior linear = new RecoveryLinear(10);
    SimpleTimer time = new SimpleTimer();
    gary = new Alien("Gary", 30, linear, 2);
    time.addTimeObserver(gary);
    gary.takeHit(15);

    time.removeTimeObserver(gary);
    time.timeChanged();
    time.timeChanged();
    assertEquals(15, gary.getCurrentLifePoints());
  }

  @Test(expected = RecoveryRateException.class)
  public void testRecoveryRateException() throws RecoveryRateException {
    Alien gary;
    RecoveryBehavior linear = new RecoveryLinear(10);
    SimpleTimer time = new SimpleTimer();
    gary = new Alien("Gary", 30, linear, -2);
  }
}
