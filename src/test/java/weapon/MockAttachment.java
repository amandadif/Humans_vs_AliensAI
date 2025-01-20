package weapon;

import exceptions.AttachmentException;

/**
 * Lead Author: Vladimeer Browning
 * Co Author: Donovan Yaukey
 */

public class MockAttachment extends Attachment {
  public MockAttachment(Weapon baseWeapon) throws AttachmentException {
    base = baseWeapon;
  }

  @Override
  public int fire(int distance) {
    return base.fire(distance);
  }

  @Override
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  @Override
  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }
}