package weapon;

import exceptions.WeaponException;

/**
 * Lead Author: Amanda DiFalco
 */
public class PlasmaCannon extends GenericWeapon {
  public PlasmaCannon() {
    super(50, 40, 1, 4);
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
        double damage = baseDamage * ((currentAmmo + 1) / (double) maxAmmo);
        return (int) damage;
      } else {
        return 0;
      }
    }
    return 0;
  }

  public String toString() {
    return "PlasmaCannon";
  }
}
