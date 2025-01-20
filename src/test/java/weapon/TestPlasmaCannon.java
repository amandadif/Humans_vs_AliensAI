package weapon;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestPlasmaCannon {

  @Test
  public void testForDamagePlasma() {
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    assertEquals(50, plasmaCannon.fire(40));
    plasmaCannon.reload();
    assertEquals(50, plasmaCannon.fire(1));
  }


  @Test
  public void testWhenOutOfRangePlasma() {
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    assertEquals(0, plasmaCannon.fire(41));
  }

  @Test
  public void testUpdatesAmmoPlasma1() {
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    plasmaCannon.fire(40);
    assertEquals(3, plasmaCannon.getCurrentAmmo());
  }

  @Test
  public void testUpdatesAmmoPlasma2() {
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    plasmaCannon.fire(41);
    assertEquals(3, plasmaCannon.getCurrentAmmo());
  }

  @Test
  public void testReloadPlasma() {
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    plasmaCannon.fire(40);
    assertEquals(3, plasmaCannon.getCurrentAmmo());
    plasmaCannon.reload();
    assertEquals(4, plasmaCannon.getCurrentAmmo());
  }

  @Test
  public void testSimpleTimerPlasmaCannon() {
    SimpleTimer timer = new SimpleTimer();
    PlasmaCannon plasmacannon = new PlasmaCannon();
    timer.addTimeObserver(plasmacannon);
    plasmacannon.fire(40);
    plasmacannon.fire(40);
    assertEquals(0, plasmacannon.getShotsLeft());
    timer.timeChanged();
    assertEquals(1, plasmacannon.getShotsLeft());
  }
}
