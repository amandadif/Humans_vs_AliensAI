package weapon;

import exceptions.AttachmentException;

public class PowerBooster extends Attachment {

  /**
   *
   * @param baseWeapon
   * @throws AttachmentException
   */
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    if (baseWeapon.getNumAttachments() >= 2) {
      throw new AttachmentException("Too many Attachments");
    }

    base = baseWeapon;
  }

  @Override
  public int fire(int distance) {
    double damage;

    double damageBoost = (1 + ((double) base.getCurrentAmmo() / (double) base.getMaxAmmo()));
    if (base.getCurrentAmmo() == 0) {
      damage = (double) base.fire(distance);
      return (int) damage;
    }
    damage = base.fire(distance);
    return (int) (damage * damageBoost);
  }

  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }

  @Override
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}