package lifeform;

public class Human extends LifeForm {
  int armorPoints;

  /**
   * constructor
   * @param name
   * @param life
   * @param armor
   */
  public Human(String name, int life, int armor) {
    super(name, life, 5);
    this.armorPoints = armor;
    maxSpeed = 3;
  }

  /**
   *
   * @return the armor points
   */
  public int getArmorPoints() {
    if (armorPoints > 0) {
      return armorPoints;
    }
    return 0;
  }

  void setArmorPoints(int armor) {
    if (armorPoints > 0) {
      armorPoints = armor;
    } else {
      armorPoints = 0;
    }
  }

  @Override
  public void takeHit(int damage) {
    if (armorPoints < damage) {
      currentLifePoints = (currentLifePoints - damage) + armorPoints;
    }
    if (currentLifePoints <= 0) {
      currentLifePoints = 0;
    }
  }
}
