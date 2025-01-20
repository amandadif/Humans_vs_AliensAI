package recovery;

public class RecoveryLinear implements RecoveryBehavior {
  int recoveryStep;

  public RecoveryLinear(int step) {
    recoveryStep = step;
  }

  /**
   * @param currentLife the current lifePoints
   * @param maxLife     the max lifePoints
   * @return new lifePoints
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }
    if ((currentLife + recoveryStep) <= maxLife) {
      return currentLife + recoveryStep;
    } else {
      return maxLife;
    }
  }
}
