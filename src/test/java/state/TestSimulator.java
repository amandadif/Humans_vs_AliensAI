package state;

import environment.Environment;
import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import gameplay.Simulator;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSimulator {

  @Before
  public void setUp() {
    Environment.resetSingleton();
  }
  @Test
  public void testPopulate() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(12, 12);
    int humanCount = 0;
    int alienCount = 0;
    SimpleTimer timer = new SimpleTimer(1000);
    Simulator sim = new Simulator(e,timer,15,10);
    for (int row = 0; row < e.getNumRows(); row++) {
      for (int col = 0; col < e.getNumCols(); col++) {
        LifeForm lifeform = e.getLifeForm(row, col);
        if (lifeform instanceof Human) {
          humanCount++;
        } else if (lifeform instanceof Alien) {
          alienCount++;
        }
      }
    }


    assertEquals(15, humanCount);
    assertEquals(10, alienCount);
  }

  @Test
  public void testUpdates() throws RecoveryRateException {
    Environment e = Environment.getEnvironment(12, 12);
    Human h = new Human("Bob", 9, 9);
    AiContext ai = new AiContext(h,e);
    SimpleTimer timer = new SimpleTimer(1000);
    Simulator s = new Simulator(e,timer,15,10);
    s.updateTime(1);
    assertEquals(25, s.getTimeUpdates());
  }


}
