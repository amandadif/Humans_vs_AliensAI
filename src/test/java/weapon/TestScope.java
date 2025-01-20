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

public class TestScope {
  @Test
  public void testNumberOfAttachments() throws AttachmentException {
    Weapon gun = new MockWeapon() {
      public int fire(int distance) {
        return 0;
      }
    };
    Scope soup = new Scope(gun);
    assertEquals(1, soup.getNumAttachments());
  }

  @Test
  public void testNumberOfRounds() throws AttachmentException {
    Weapon gun = new MockWeapon();
    Scope soup = new Scope(gun);
    soup.fire(10);
    soup.fire(10);
    assertEquals(1, soup.getCurrentAmmo());
  }

  @Test
  public void testString() throws AttachmentException {
    Weapon gun = new ChainGun();
    Scope soup = new Scope(gun);
    System.out.printf("%s", soup.toString());
  }

  @Test
  public void testDamage() throws AttachmentException {
    Weapon gun = new PlasmaCannon();
    Scope soup = new Scope(gun);
    assertEquals(90, soup.fire(10));
  }

  @Test
  public void testNoAmmoDamage() throws AttachmentException {
    Weapon gun = new MockWeapon();
    Scope soup = new Scope(gun);
    soup.fire(10);
    soup.fire(10);
    soup.fire(10);
    assertEquals(0, soup.fire(50));
  }

  @Test
  public void testPistolScope() throws AttachmentException {
    Pistol p1 = new Pistol();
    Scope scopePistol = new Scope(p1);
    assertEquals(9, scopePistol.fire(30));
    assertEquals(60, scopePistol.getMaxRange());
  }

  @Test
  public void testScopeScopePistol() throws AttachmentException, WeaponException {
    Scope scopePistol = new Scope(new Pistol());
    Scope scopeScopePistol = new Scope(scopePistol);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(scopeScopePistol);
    timer.timeChanged();
    Assert.assertEquals(2L, (long)scopeScopePistol.getNumAttachments());
    Assert.assertEquals(70L, (long)scopeScopePistol.getMaxRange());
    Assert.assertEquals("Pistol +Scope +Scope", scopeScopePistol.toString());
    Assert.assertEquals(14L, (long)scopeScopePistol.fire(30));
    timer.timeChanged();
    Assert.assertEquals(2L, (long)scopeScopePistol.fire(50));
    timer.timeChanged();
    Assert.assertEquals(12L, (long)scopeScopePistol.fire(65));
    timer.timeChanged();
    Assert.assertEquals(12L, (long)scopeScopePistol.fire(70));
    timer.timeChanged();
    Assert.assertEquals(0L, (long)scopeScopePistol.fire(80));
    timer.timeChanged();
  }

  @Test
  public void testChainPowerScope() throws AttachmentException {
    Weapon cg = new ChainGun();
    PowerBooster powerBoosterWeapon = new PowerBooster(cg);
    Scope chainPowerScope = new Scope(powerBoosterWeapon);
    assertEquals(22, chainPowerScope.fire(30));
    assertEquals(70, chainPowerScope.getMaxRange());
  }

  @Test
  public void testPlasmaStabilizerScope() throws AttachmentException {
    Weapon p1 = new PlasmaCannon();
    Stabilizer s1 = new Stabilizer(p1);
    Scope plasmaStabilizerScope = new Scope(s1);
    assertEquals(86, plasmaStabilizerScope.fire(30));
    assertEquals(50, plasmaStabilizerScope.getMaxRange());
  }
}
