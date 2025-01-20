package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;



public abstract class Attachment implements Weapon {

  protected Weapon base;

  public Attachment() {

  }

  public abstract int fire(int distance);

  public int getBaseDamage() {

    return base.getBaseDamage();
  }

  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  public int getMaxRange() {
    return base.getMaxRange();
  }

  public int getNumAttachments() {
    return base.getNumAttachments();
  }

  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  public int getShotsLeft() {

    return base.getShotsLeft();
  }

  public void reload() {
    base.reload();
  }

  public void updateTime(int time) {

    base.updateTime(time);
  }
}
