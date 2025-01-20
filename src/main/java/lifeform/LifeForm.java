package lifeform;

import environment.Environment;
import weapon.Weapon;

import static gui.Gui.e;


public abstract class LifeForm {
  private String myName;
  protected int currentLifePoints;
  protected int maxLifePoints;
  int attackStrength;
  Weapon primaryWeapon;
  Weapon secondaryWeapon;
  int maxSpeed;
  String currentDirection;
  protected int row = -1;
  protected int col = -1;

  /**
   * Create an instance
   * @param name   the name
   * @param points the life points
   */
  public LifeForm(String name, int points) {
    this(name, points, 5);
  }

  /**
   * @param name
   * @param points
   * @param attack
   */
  public LifeForm(String name, int points, int attack) {
    myName = name;
    currentLifePoints = points;
    attackStrength = attack;
    maxLifePoints = points;
    currentDirection = "North";
  }

  public void respawnHealth() {
    currentLifePoints = maxLifePoints;
  }

  /**
   * @return the name
   */
  public String getName() {
    return myName;
  }

  /**
   * Madison
   * @return max life pts.
   */
  public int getMaxLifePoints() {

    return maxLifePoints;
  }

  /**
   * @return amount of life points
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * @param damage
   */
  void takeHit(int damage) {
    if (currentLifePoints - damage > 0) {
      currentLifePoints -= damage;
    } else {
      currentLifePoints = 0;
    }
  }

  /**
   * @return
   */
  public int getAttackStrength() {
    if (currentLifePoints <= 0) {
      return 0;
    }
    return attackStrength;
  }

  /**
   *
   * @param form
   * @param distance
   *
   */
  public void attack(LifeForm form, int distance) {
    if (hasWeapon() && primaryWeapon.getShotsLeft() > 0) {
      alreadyZeroHealth();
      form.takeHit(primaryWeapon.fire(distance));
    } else if (hasWeapon() && primaryWeapon.getShotsLeft() == 0) {
      if (distance <= 5) {
        alreadyZeroHealth();
        form.takeHit(this.getAttackStrength());
      }
    } else if (!hasWeapon()) {
      if (distance <= 5) {
        alreadyZeroHealth();
        form.takeHit(this.getAttackStrength());
      }
    }
  }

  /**
   *
   * @param w
   * @return
   *
   */
  public boolean pickUpWeapon(Weapon w) {
    if (!hasWeapon()) {
      primaryWeapon = w;
      return true;
    } else if (secondaryWeapon == null) {
      secondaryWeapon = w;
      dropWeapon();
      primaryWeapon = secondaryWeapon;
      secondaryWeapon =  null;
    }
    return false;
  }

  /**
   * Drops the weapon.
   * @return the dropped weapon.
   */
  public Weapon dropWeapon() {
    if (hasWeapon()) {
      Weapon droppedWeapon = primaryWeapon; // Store the weapon being dropped
      primaryWeapon = null; // Clear the current weapon
      return droppedWeapon; // Return the dropped weapon
    }
    return null; // Return null if there was no weapon to drop
  }

  /**
   * If lf has a weapon or not.
   * @return true if lf has weapon, false if not.
   */
  public boolean hasWeapon() {
    if (primaryWeapon != null) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * If the lf is already zero health current life points is made zero.
   */
  public void alreadyZeroHealth() {
    if (currentLifePoints <= 0) {
      currentLifePoints = 0;
    }
  }

  /**
   * @param row you want the lf to be at.
   * @param col you want the lf to be at.
   */
  public void setLocation(int row, int col) {
    if (row < 0 || col < 0) {
      return;
    }
    this.row = row;
    this.col = col;
  }

  /**
   * @return the row.
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the column.
   */
  public int getCol() {
    return col;
  }


  /**
   * @return the weapon the LF currently has
   */
  public Weapon getWeapon() {
    if (hasWeapon()) {
      return primaryWeapon;
    }
    return null;
  }

  /**
   *
   * @return the second weapon
   */
  public Weapon getWeapon2() {
    if (hasWeapon()) {
      return secondaryWeapon;
    }
    return null;
  }

  /**
   *
   * @return lf max speed.
   */
  public int getMaxSpeed() {
    return maxSpeed;
  }

  /**
   *
   * @return the current direction as a string.
   */
  public String getDirection() {
    return currentDirection;
  }

  public void setDirection(String direction) {
    //Environment env = Environment.getInstance();
    currentDirection = direction;
  }

}


