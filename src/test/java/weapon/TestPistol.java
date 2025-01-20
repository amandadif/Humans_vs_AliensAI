package weapon;

import exceptions.WeaponException;
import gameplay.MockSimpleTimerObserver;
import gameplay.SimpleTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestPistol {
  @Test
  public void testForDamagePistol() {
    Pistol pistol = new Pistol();
    assertEquals(2, pistol.fire(50));
    assertEquals(11, pistol.fire(1));

  }

  @Test
  public void testWhenOutOfRangePistol() {
    Pistol pistol = new Pistol();
    assertEquals(0, pistol.fire(51));
  }

  @Test
  public void testUpdatesAmmoPistol1() {
    Pistol pistol = new Pistol();
    pistol.fire(50);
    assertEquals(9, pistol.getCurrentAmmo());
  }

  @Test
  public void testUpdatesAmmoPistol2() {
    Pistol pistol = new Pistol();
    pistol.fire(51);
    assertEquals(9, pistol.getCurrentAmmo());
  }

  @Test
  public void testReloadPistol() {
    Pistol pistol = new Pistol();
    pistol.fire(51);
    assertEquals(9, pistol.getCurrentAmmo());
    pistol.reload();
    assertEquals(10, pistol.getCurrentAmmo());
  }

  @Test
  public void testSimpleTimerPistol() {
    SimpleTimer timer = new SimpleTimer();
    Pistol pistol = new Pistol();
    timer.addTimeObserver(pistol);
    pistol.fire(50);
    pistol.fire(50);
    pistol.fire(50);
    assertEquals(0, pistol.getShotsLeft());
    timer.timeChanged();
    pistol.fire(50);
    assertEquals(1, pistol.getShotsLeft());
  }
}
