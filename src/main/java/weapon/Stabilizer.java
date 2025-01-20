package weapon;

import exceptions.AttachmentException;

public class Stabilizer extends Attachment {

  /**
   *
   * @param baseWeapon
   * @throws AttachmentException
   */
  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    if (baseWeapon.getNumAttachments() >= 2) {
      throw new AttachmentException("Too many Attachments");
    }
    base = baseWeapon;
  }

  @Override
  public int fire(int distance) {
    double damage = base.fire(distance) * 1.25;

    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return (int) damage;

  }

  @Override
  public String toString() {
    return base.toString() + " +Stabilizer";
  }

  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }
}
