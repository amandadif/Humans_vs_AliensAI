package recovery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryLinear {
  @Test
  public void noRecoveryWhenNotHurt() {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = r1.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  @Test
  public void testBigHurt() {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 30;
    int currentLifePoints = 10;
    int result = r1.calculateRecovery(currentLifePoints, maxLifePts);
    assertEquals(13, result);
  }

  @Test
  public void testLittleHurt() {
    RecoveryLinear r1 = new RecoveryLinear(35);
    int maxLifePts = 30;
    int currentLifePoints = 10;
    int result = r1.calculateRecovery(currentLifePoints, maxLifePts);
    assertEquals(30, result);
  }

  @Test
  public void testDead() {
    RecoveryLinear r1 = new RecoveryLinear(3);
    int maxLifePts = 30;
    int currentLifePoints = 0;
    int result = r1.calculateRecovery(currentLifePoints, maxLifePts);
    assertEquals(0, result);
  }


}
