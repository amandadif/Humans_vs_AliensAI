package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Lead Author: Vladimeer Browning
 * Co Author: Amanda DiFalco
 * Co Author: Donovan Yaukey
 * Co Author: Logan Wilt
 */

public class TestPowerBooster {
  @Test
  public void testNumberOfAttachments() throws AttachmentException {
    Weapon gun = new MockWeapon() {
      public int fire(int distance) {
        return 0;
      }
    };
    PowerBooster power = new PowerBooster(gun);
    assertEquals(1, power.getNumAttachments());
  }

  @Test
  public void testNumberOfRounds() throws AttachmentException {
    Weapon gun = new MockWeapon();
    PowerBooster power = new PowerBooster(gun);
    power.fire(10);
    power.fire(10);
    assertEquals(1, power.getCurrentAmmo());
  }

  @Test
  public void testString() throws AttachmentException {
    Weapon gun = new ChainGun();
    PowerBooster power = new PowerBooster(gun);
    System.out.printf("%s", power.toString());
  }

  @Test
  public void testDamage() throws AttachmentException {
    Weapon gun = new MockWeapon();
    PowerBooster power = new PowerBooster(gun);
    assertEquals(20, power.fire(50));
  }

  @Test
  public void testNoAmmoDamage() throws AttachmentException {
    Weapon gun = new MockWeapon();
    PowerBooster power = new PowerBooster(gun);
    power.fire(10);
    power.fire(10);
    power.fire(10);
    assertEquals(0, power.fire(50));
  }

  @Test
  public void testPowerBoosterChainGun() throws AttachmentException, WeaponException {
    PowerBooster powerChain = new PowerBooster(new ChainGun());
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(powerChain);
    timer.timeChanged();
    Assert.assertEquals(1L, (long)powerChain.getNumAttachments());
    Assert.assertEquals("ChainGun +PowerBooster", powerChain.toString());
    Assert.assertEquals(14L, (long)powerChain.fire(30));
    timer.timeChanged();
    Assert.assertEquals(29L, (long)powerChain.fire(60));
    timer.timeChanged();
    Assert.assertEquals(0L, (long)powerChain.fire(90));
    timer.timeChanged();

    int i;
    for (i = 0; i < 15; ++i) {
      powerChain.fire(30);
      timer.timeChanged();
    }

    Assert.assertEquals(23L, (long)powerChain.fire(60));
    timer.timeChanged();

    for (i = 0; i < 15; ++i) {
      powerChain.fire(30);
      timer.timeChanged();
    }

    Assert.assertEquals(17L, (long)powerChain.fire(60));
    timer.timeChanged();
  }

  @Test
  public void testChainPower() throws AttachmentException {
    ChainGun cg1 = new ChainGun();
    PowerBooster chainPower = new PowerBooster(cg1);
    assertEquals(14, chainPower.fire(30));
    assertEquals(60, chainPower.getMaxRange());
  }

  @Test
  public void testChainPowerPower() throws AttachmentException {
    ChainGun cg1 = new ChainGun();
    PowerBooster p1 = new PowerBooster(cg1);
    PowerBooster chainPowerPower = new PowerBooster(p1);
    assertEquals(28, chainPowerPower.fire(30));
    assertEquals(60, chainPowerPower.getMaxRange());
  }

  @Test
  public void testPistolScopePower() throws AttachmentException {
    PowerBooster pistolScopePower = new PowerBooster(new Scope(new Pistol()));
    assertEquals(18, pistolScopePower.fire(30));
    assertEquals(60, pistolScopePower.getMaxRange());
  }

  @Test
  public void testPlasmaStabilizerPower() throws AttachmentException {
    PowerBooster plasmaStabilizerPower = new PowerBooster(new Stabilizer(new PlasmaCannon()));
    assertEquals(124, plasmaStabilizerPower.fire(30));
    assertEquals(40, plasmaStabilizerPower.getMaxRange());
  }
}
