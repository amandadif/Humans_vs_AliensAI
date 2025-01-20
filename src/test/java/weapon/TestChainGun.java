package weapon;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Lead Author: Amanda DiFalco
 */
public class TestChainGun {
  @Test
  public void testForDamageChain() {
    ChainGun chaingun = new ChainGun();
    assertEquals(15, chaingun.fire(60));
    assertEquals(2, chaingun.fire(10));
  }

  @Test
  public void testWhenOutOfRangeChain() {
    ChainGun chaingun = new ChainGun();
    assertEquals(0, chaingun.fire(61));
  }

  @Test
  public void testUpdatesAmmoChain1() {
    ChainGun chaingun = new ChainGun();
    chaingun.fire(60);
    assertEquals(39, chaingun.getCurrentAmmo());
  }

  @Test
  public void testUpdatesAmmoChain2() {
    ChainGun chaingun = new ChainGun();
    chaingun.fire(61);
    assertEquals(39, chaingun.getCurrentAmmo());
  }

  @Test
  public void testReloadChain() {
    ChainGun chaingun = new ChainGun();
    chaingun.fire(60);
    assertEquals(39, chaingun.getCurrentAmmo());
    chaingun.reload();
    assertEquals(40, chaingun.getCurrentAmmo());
  }

  @Test
  public void testSimpleTimerChainGun() {
    SimpleTimer timer = new SimpleTimer();
    ChainGun chaingun = new ChainGun();
    timer.addTimeObserver(chaingun);
    chaingun.fire(60);
    chaingun.fire(60);
    chaingun.fire(60);
    chaingun.fire(60);
    chaingun.fire(60);
    assertEquals(0, chaingun.getShotsLeft());
    timer.timeChanged();
    chaingun.fire(60);
    assertEquals(3, chaingun.getShotsLeft());
  }
}
