package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestGenericWeapon {

  @Test
  public void testUpdatesAmmoMock() {
    MockWeapon weapon = new MockWeapon();
    assertEquals(3, weapon.getCurrentAmmo());
    weapon.fire(50);
    assertEquals(2, weapon.getCurrentAmmo());
  }

  @Test
  public void testRateOfFireMock() {
    SimpleTimer timer = new SimpleTimer();
    MockWeapon weapon = new MockWeapon();
    timer.addTimeObserver(weapon);
    weapon.fire(50);
    weapon.fire(50);
    weapon.fire(50);
    assertEquals(1, weapon.getShotsLeft());
    timer.timeChanged();
    weapon.fire(50);
    assertEquals(4, weapon.getShotsLeft());
    weapon.reload();
    weapon.fire(50);
    assertEquals(3, weapon.getShotsLeft());
  }

  @Test
  public void testReloadMock() {
    MockWeapon weapon = new MockWeapon();
    weapon.fire(50);
    assertEquals(2, weapon.getCurrentAmmo());
    weapon.reload();
    assertEquals(3, weapon.getCurrentAmmo());
  }

  @Test
  public void testOutOfAmmo() {
    MockWeapon weapon = new MockWeapon();
    weapon.fire(50);
    weapon.fire(50);
    weapon.fire(50);
    assertEquals(0, weapon.getCurrentAmmo());
    assertEquals(0, weapon.fire(50));
  }

  @Test
  public void testBeyondRange() {
    MockWeapon weapon = new MockWeapon();
    assertEquals(0, weapon.fire(51));
    assertEquals(2, weapon.getCurrentAmmo());
  }
}
