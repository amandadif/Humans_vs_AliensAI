package recovery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryFractional {
  @Test
  public void testEqual() {
    RecoveryFractional r1 = new RecoveryFractional((double) 1 / 3);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  @Test
  public void testNotOver() {
    RecoveryFractional r1 = new RecoveryFractional((double) 1 / 10);
    int maxLifePts = 30;
    int currentLifePts = 29;
    int result = r1.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  @Test
  public void testFullRecovery() {
    RecoveryFractional r1 = new RecoveryFractional((double) 5 / 10);
    int maxLifePts = 30;
    int currentLifePts = 29;
    int result = r1.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(30, result);
  }

  @Test
  public void testIsLess() {
    RecoveryFractional r1 = new RecoveryFractional((double) 1 / 20);
    int maxLifePts = 30;
    int currentLifePts = 20;
    int result = r1.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(21, result);
  }

  @Test
  public void testDead() {
    RecoveryFractional r1 = new RecoveryFractional((double) 3 / 100);
    int maxLifePts = 30;
    int currentLifePoints = 0;
    int result = r1.calculateRecovery(currentLifePoints, maxLifePts);
    assertEquals(0, result);
  }
}
