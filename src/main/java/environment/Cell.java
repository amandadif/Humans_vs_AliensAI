package environment;

import lifeform.LifeForm;
import weapon.Weapon;

import java.util.Arrays;

public class Cell {
  LifeForm entity;
  Weapon[] weapons;
  private int weaponCount;

  public Cell() {
    this.weapons = new Weapon[2];
  }

  /**
   * @param lf the lifeform
   * @return true if lifeform added
   */
  public boolean addLifeForm(LifeForm lf) {
    if (entity == null) {
      entity = lf;
      return true;
    }
    System.out.println("Can not add Life Form");
    return false;
  }


  void removeLifeForm() {
    if (entity != null) {
      entity = null;
    }
  }

  /**
   * @return lifeform
   */
  public LifeForm getLifeForm() {
    if (entity == null) {
      return null;
    }
    return entity;
  }


  /**
    Lead Author: Amanda DiFalco
   **/
  public int getWeaponsCount() {
    return weaponCount;
  }

  /**
   *
   * @return Weapon 1
   */
  public Weapon getWeapon1() {
    if (weapons[0] != null) {
      return weapons[0];
    }
    return null;
  }

  /**
   *
   * @return Weapon 2
   */
  public Weapon getWeapon2() {
    if (weapons[1] != null) {
      return weapons[1];
    }
    return null;
  }

  /**
   *
   * @param addWeapon
   * @return If the weapon was added or not
   */
  public boolean addWeapon(Weapon addWeapon) {
    for (int i = 0; i < weapons.length; i++) {
      if (weapons[i] == addWeapon) {
        return false;
      }
      if (weapons[i] == null) {
        weapons[i] = addWeapon;
        weaponCount++;
        //System.out.println("Weapon added at slot " + i);
        return true;
      }
    }
    return false;
  }

  /**
   *
   * @param removeWeapon
   * @return the removed weapon
   */
  public Weapon removeWeapon(Weapon removeWeapon) {
    for (int i = 0; i < weaponCount; i++) {
      if (weapons[i] == removeWeapon) {
        Weapon removedWeapon = weapons[i];
        weapons[i] = weapons[--weaponCount];
        weapons[weaponCount] = null;
        return removedWeapon;
      }
    }
    return null;
  }

  /**
   * clears all weapons from the weapons array.
   */
  public void clearWeapons() {

    for (int i = 0; i < weapons.length; i++) {
      weapons[i] = null;
    }
  }

  /**
   * @return an array with all the weapons in it.
   */
  public Weapon[] getWeapons() {
    Weapon[] weaponArray = new Weapon[weapons.length];

    for (int i = 0; i < weapons.length; i++) {
      weaponArray[i] = weapons[i];
    }
    return weaponArray;
  }
}
