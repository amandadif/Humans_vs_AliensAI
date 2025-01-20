package weapon;

import exceptions.WeaponException;

/**
 * Lead Author: Amanda DiFalco
 */
public class Pistol extends GenericWeapon {

  public Pistol() {
    super(10, 50, 2, 10);
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
        double damage = baseDamage * ((maxRange - distance + 10) / (double) maxRange);
        return (int) damage;
      } else {
        return 0;
      }
    }
    return 0;
  }

  public String toString() {
    return "Pistol";
  }

}
