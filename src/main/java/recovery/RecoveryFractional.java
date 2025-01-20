package recovery;

public class RecoveryFractional implements RecoveryBehavior {
  double percentRecovery;

  public RecoveryFractional(double percent) {
    this.percentRecovery = percent;

  }

  /**
   * @param currentLife the current lifePoints
   * @param maxLife     the max lifePoints
   * @return new lifePoints
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife <= 0) {
      return 0;
    }
    double recoveryAmount = percentRecovery * currentLife;
    int rounded = (int) Math.ceil(recoveryAmount);
    int life = currentLife + rounded;
    if (life <= maxLife) {
      return life;
    }
    return maxLife;
  }


}
