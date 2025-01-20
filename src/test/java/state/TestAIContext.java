package state;

import environment.Environment;
import lifeform.Human;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAIContext {
  @Test
  public void testActiveState() {
    Environment e = Environment.getEnvironment(12,12);
    Human human = new Human("Bob", 5,5);
    AiContext ai = new AiContext(human,e);
    ai.setCurrentState(ai.getHasWeaponState());
    assertEquals(ai.getHasWeaponState(), ai.getCurrentState());
    ai.setCurrentState(ai.getDeadState());
    assertEquals(ai.getDeadState(), ai.getCurrentState());
  }

  @Test
  public void testGetState() {
    Environment e = Environment.getEnvironment(12,12);
    Human human = new Human("Bob", 5,5);
    AiContext ai = new AiContext(human,e);
    ai.setCurrentState(ai.getHasWeaponState());
    assertEquals(ai.getHasWeaponState(), ai.getCurrentState());
    ai.setCurrentState(ai.getDeadState());
    assertEquals(ai.getDeadState(), ai.getCurrentState());
    ai.setCurrentState(ai.getOutOfAmmoState());
    assertEquals(ai.getOutOfAmmoState(), ai.getCurrentState());
    ai.setCurrentState(ai.getNoWeaponState());
    assertEquals(ai.getNoWeaponState(), ai.getCurrentState());
  }
}
