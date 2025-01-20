package weapon;

import exceptions.WeaponException;

/**
 * Lead Author: Amanda DiFalco
 * Co-Author: Donovan Yaukey
 */
public class MockWeapon extends GenericWeapon {

  public MockWeapon() {
    super(10, 50, 4, 3);
  }

  /**
   * @param distance
   * @return
   */
  public int fire(int distance) {
    if (distance < 0) {
      throw new WeaponException("Invalid Distance Value");
    }
    if (shotsLeft > 0 && currentAmmo > 0) {
      currentAmmo = currentAmmo - 1;
      shotsLeft = shotsLeft - 1;
      if (maxRange >= distance) {
        double damage = getBaseDamage();
        return (int) damage;
      } else {
        return 0;
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    return "MockWeapon";
  }
}
