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

public class TestStabilizer {
  @Test
  public void testNumberOfAttachments() throws AttachmentException {
    Weapon gun = new MockWeapon() {
      public int fire(int distance) {
        return 0;
      }
    };
    Stabilizer still = new Stabilizer(gun);
    assertEquals(1, still.getNumAttachments());
  }

  @Test
  public void testNumberOfRounds() throws AttachmentException {
    Weapon gun = new MockWeapon();
    Stabilizer still = new Stabilizer(gun);
    still.fire(10);
    still.fire(10);
    still.fire(10);
    still.fire(10);
    still.fire(10);
    assertEquals(1, still.getCurrentAmmo());
  }

  @Test
  public void testString() throws AttachmentException {
    Weapon gun = new ChainGun();
    Stabilizer still = new Stabilizer(gun);
    System.out.printf("%s", still.toString());
  }

  @Test
  public void testDamage() throws AttachmentException {
    Weapon gun = new PlasmaCannon();
    Stabilizer still = new Stabilizer(gun);
    assertEquals(62, still.fire(10));
  }

  @Test
  public void testPlasmaStabilizer() throws AttachmentException {
    PlasmaCannon p1 = new PlasmaCannon();
    Stabilizer plasmaStabilizer = new Stabilizer(p1);
    assertEquals(62, plasmaStabilizer.fire(30));
    assertEquals(40, plasmaStabilizer.getMaxRange());
  }

  @Test
  public void testPlasmaStabilizerStabilizer() throws AttachmentException {
    PlasmaCannon p1 = new PlasmaCannon();
    Stabilizer s1 = new Stabilizer(p1);
    Stabilizer plasmaStabilizerStabilizer = new Stabilizer(s1);
    assertEquals(77, plasmaStabilizerStabilizer.fire(30));
    assertEquals(40, plasmaStabilizerStabilizer.getMaxRange());
  }

  @Test
  public void testPistolScopeStabilizer() throws AttachmentException {
    Pistol p1 = new Pistol();
    Scope s1 = new Scope(p1);
    Stabilizer pistolScopeStabilizer = new Stabilizer(s1);
    assertEquals(11, pistolScopeStabilizer.fire(30));
    assertEquals(60, pistolScopeStabilizer.getMaxRange());
  }

  @Test
  public void testChainPowerStabilizer() throws AttachmentException {
    ChainGun cg1 = new ChainGun();
    PowerBooster pb1 = new PowerBooster(cg1);
    Stabilizer chainPowerStabilizer = new Stabilizer(pb1);
    assertEquals(17, chainPowerStabilizer.fire(30));
    assertEquals(60, chainPowerStabilizer.getMaxRange());
  }
}
