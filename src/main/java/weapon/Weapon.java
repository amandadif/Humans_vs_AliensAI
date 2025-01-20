package weapon;

import gameplay.TimerObserver;


public interface Weapon extends TimerObserver {

  int fire(int distance);

  int getBaseDamage();

  int getCurrentAmmo();

  int getMaxAmmo();

  int getMaxRange();

  int getNumAttachments();

  int getRateOfFire();

  int getShotsLeft();

  void reload();

  java.lang.String toString();
}
