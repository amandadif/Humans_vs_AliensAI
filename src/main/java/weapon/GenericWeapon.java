package weapon;

import exceptions.WeaponException;

/**
 * Lead Author: Amanda DiFalco
 */
abstract class GenericWeapon implements Weapon {

  protected int baseDamage;
  protected int maxAmmo;
  protected int maxRange;
  protected int currentAmmo;
  protected int rateOfFire;
  protected int shotsLeft;

  public GenericWeapon(int base, int range, int rate, int ammo) {
    this.baseDamage = base;
    this.maxAmmo = ammo;
    this.maxRange = range;
    this.currentAmmo = ammo;
    this.rateOfFire = rate;
    this.shotsLeft = rate;
  }

  public GenericWeapon() throws WeaponException {
  }

  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Invalid Distance Value");
    }
    if (shotsLeft > 0 && currentAmmo > 0) {
      currentAmmo = currentAmmo - 1;
      shotsLeft = shotsLeft - 1;
      if (0 < distance && distance < maxRange) {
        return baseDamage;
      }
    }
    return 0;
  }

  public int getBaseDamage() {
    return baseDamage;
  }

  public int getMaxAmmo() {
    return maxAmmo;
  }

  public int getMaxRange() {
    return maxRange;
  }

  public int getNumAttachments() {
    return 0;
  }

  public int getRateOfFire() {
    return rateOfFire;
  }

  public int getShotsLeft() {
    return shotsLeft;
  }

  public int getCurrentAmmo() {
    return currentAmmo;
  }

  public void reload() {
    currentAmmo = maxAmmo;
    updateTime(1);
  }

  public void updateTime(int time) {
    shotsLeft = rateOfFire;
  }
}
