package weapon;

import exceptions.WeaponException;

/**
 * Lead Author: Amanda DiFalco
 */
public class ChainGun extends GenericWeapon {
  public ChainGun() {
    super(15, 60, 4, 40);
  }

  @Override
  public int fire(int distance) {
    if (distance < 0) {
      throw new WeaponException("Invalid Distance Value");
    }
    if (shotsLeft > 0 && currentAmmo > 0) {
      currentAmmo = currentAmmo - 1;
      shotsLeft = shotsLeft - 1;
      if (maxRange >= distance) {
        double damage = baseDamage * ((distance) / (double) maxRange);
        return (int) damage;
      } else {
        return 0;
      }
    }
    return 0;
  }

  public String toString() {
    return "ChainGun";
  }

}
