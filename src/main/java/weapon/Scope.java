package weapon;

import exceptions.AttachmentException;

public class Scope extends Attachment {
  /**
   *
   * @param baseWeapon
   * @throws AttachmentException
   */
  public Scope(Weapon baseWeapon) throws AttachmentException {
    if (baseWeapon.getNumAttachments() >= 2) {
      throw new AttachmentException("Too many Attachments");
    }
    base = baseWeapon;
  }

  @Override
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }

  public int ampedRange() {
    return base.getMaxRange() + 10;
  }

  @Override
  public int fire(int distance) {
    double damage;

    double scopeDamage = (1.00 + ((double) (getMaxRange() - distance) / (double) getMaxRange()));
    if (base.getCurrentAmmo() == 0) {
      return 0;
    }
    if (base.getMaxRange() < distance && distance <= base.getMaxRange() + 10) {
      damage = base.fire(base.getMaxRange()) + 5;
      return (int) damage;
    } else {
      damage = base.fire(distance);
      return (int) (damage * scopeDamage);
    }
  }

  public int getNumAttachments() {
    return (base.getNumAttachments() + 1);
  }

  @Override
  public String toString() {
    return base.toString() + " +Scope";
  }
}
