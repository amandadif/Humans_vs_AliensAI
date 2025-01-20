package gameplay;

import lifeform.LifeForm;
import weapon.Weapon;

import java.util.Observer;

public interface EnvironmentSubject {

  public void addObserver(EnvironmentObserver observer);

  public void removeObserver(EnvironmentObserver observer);

  public void notifyObservers(int row, int col, LifeForm lf, Weapon weapon1, Weapon weapon2);
}
