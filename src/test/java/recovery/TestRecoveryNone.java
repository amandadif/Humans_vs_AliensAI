package recovery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryNone {
  @Test
  public void testNoRecovery() {
    RecoveryNone gary;
    gary = new RecoveryNone();
    int curr = 50;
    int maxLife = 50;
    gary.calculateRecovery(curr, maxLife);
    assertEquals(curr, gary.calculateRecovery(curr, maxLife));
  }

  @Test
  public void testNoRecovery2() {
    RecoveryNone gary;
    gary = new RecoveryNone();
    int curr = 50;
    int maxLife = 100;
    gary.calculateRecovery(curr, maxLife);
    assertEquals(curr, gary.calculateRecovery(curr, maxLife));
  }
}
