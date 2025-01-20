package gameplay;

import environment.Environment;
import exceptions.RecoveryRateException;

import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import state.AiContext;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.ChainGun;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.PowerBooster;
import weapon.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator implements TimerObserver {
  private Environment environment = Environment.getInstance();
  private List<AiContext> aiContexts;
  private Random random;
  private SimpleTimer timer;
  private int timeUpdates = 0;

  /**
   *
   * @param environment
   * @param timer
   * @param numHumans
   * @param numAliens
   * @throws RecoveryRateException
   */
  public Simulator(Environment environment, SimpleTimer timer,
                   int numHumans, int numAliens) throws RecoveryRateException {
    this.environment = environment;
    this.aiContexts = new ArrayList<>();
    this.random = new Random();
    this.timer = timer;
    // Populate Environment with Humans
    for (int i = 0; i < 15; i++) {
      Human human = createRandomHuman();
      AiContext aiContext = new AiContext(human, environment);
      placeLifeFormRandomly(human);
      aiContexts.add(aiContext);
    }

    // Populate Environment with Aliens
    for (int i = 0; i < 10; i++) {
      Alien alien = createRandomAlien();
      AiContext aiContext = new AiContext(alien, environment);
      placeLifeFormRandomly(alien);
      aiContexts.add(aiContext);
    }


    // Add Weapons to the Environment
    addRandomWeapons(numHumans + numAliens);
  }

  /**
   * Creates a Human with a random amount of armor.
   */
  private Human createRandomHuman() {
    int armor = random.nextInt(10); // Random armor between 0 and 9
    return new Human("Human", 30, armor);
  }

  /**
   * Creates an Alien with a random recovery behavior and rate.
   */
  private Alien createRandomAlien() throws RecoveryRateException {
    double recoveryRate = random.nextDouble(); // Random recovery rate between 0.0 and 1.0
    if (random.nextBoolean()) {
      return new Alien("Alien", 30, new RecoveryFractional(recoveryRate),
              random.nextInt(5) + 1);
    } else {
      return new Alien("Alien", 30, new RecoveryLinear((int) (recoveryRate * 10)),
              random.nextInt(5) + 1);
    }
  }

  /**
   * Places a LifeForm randomly in the Environment.
   */
  private void placeLifeFormRandomly(LifeForm lifeform) {
    boolean placed = false;
    while (!placed) {
      int row = random.nextInt(environment.getNumRows());
      int col = random.nextInt(environment.getNumCols());
      placed = environment.addLifeForm(lifeform, row, col);
      //System.out.println("Placed " + lifeform + " at: " + row + ", " + col);
    }
  }

  /**
   * Adds a weapon for each LifeForm in the Environment.
   */
  private void addRandomWeapons(int numWeapons) {
    for (int i = 0; i < numWeapons; i++) {
      Weapon weapon = createRandomWeapon();
      boolean placed = false;
      while (!placed) {
        int row = random.nextInt(environment.getNumCols());
        int col = random.nextInt(environment.getNumCols());
        placed = environment.addWeapon(weapon, row, col);
      }
    }
  }

  /**
   * Creates a random weapon, possibly with attachments.
   */
  private Weapon createRandomWeapon() {
    Weapon weapon;
    int weaponType = random.nextInt(3); // Randomly choose weapon type
    if (weaponType == 0) {
      weapon = new Pistol();
    } else if (weaponType == 1) {
      weapon = new PlasmaCannon();
    } else {
      weapon = new ChainGun();
    }

    // Randomly add up to 2 attachments
    for (int i = 0; i < 2; i++) {
      int attachmentType = random.nextInt(4); // Randomly choose attachment type
      if (attachmentType == 0) {
        weapon = new Scope(weapon);
      } else if (attachmentType == 1) {
        weapon = new Stabilizer(weapon);
      } else if (attachmentType == 2) {
        weapon = new PowerBooster(weapon);
      } else {
        break; // No more attachments
      }
    }
    return weapon;
  }

  /**
   * Starts the simulation.
   */
  public void startSimulation(SimpleTimer timer) {
    timer.addTimeObserver(this);
    timer.start();
  }

  public int getTimeUpdates() {
    return timeUpdates;
  }

  @Override
  public void updateTime(int time) {
    // Update all AIContexts for each time step
    for (AiContext aiContext : aiContexts) {
      aiContext.updateTime(time);
      timeUpdates++;
    }
  }
}
