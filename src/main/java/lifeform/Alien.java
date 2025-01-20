package lifeform;

import exceptions.RecoveryRateException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;
import recovery.RecoveryLinear;
import recovery.RecoveryFractional;
import recovery.RecoveryNone;

public class Alien extends LifeForm implements TimerObserver {
  RecoveryBehavior recoveryBehavior;
  private int maxLifePoints;
  private int recoveryRate;

  public Alien(String name, int life) throws RecoveryRateException {
    this(name, life, null, 0);
  }

  public Alien(String name, int life, RecoveryBehavior rb) throws RecoveryRateException {
    this(name, life, rb, 0);
  }

  protected void recover() {
    currentLifePoints = recoveryBehavior.calculateRecovery(getCurrentLifePoints(), maxLifePoints);
  }

  /**
   * constructor
   * @param name
   * @param life
   * @param rb
   * @param rate
   * @throws RecoveryRateException
   */
  public Alien(String name, int life, RecoveryBehavior rb, int rate) throws RecoveryRateException {
    super(name, life, 10);
    recoveryRate = rate;
    this.maxLifePoints = life;
    this.recoveryBehavior = rb;
    maxSpeed = 2;
    if (recoveryRate < 0) {
      throw new RecoveryRateException("recoveryRate is negative");
    }
  }


  void setMaxLifePoints(int maxLife) {
    maxLifePoints = maxLife;
  }

  public int getMaxLifePoints() {
    return maxLifePoints;
  }

  void setRecoveryRate(int rate) {
    recoveryRate = rate;
  }

  public int getRecoveryRate() {
    return recoveryRate;
  }

  @Override
  public void updateTime(int time) {
    if (currentLifePoints > 0 && recoveryRate > 0 && time % recoveryRate == 0) {
      recover();
    }
  }
}

