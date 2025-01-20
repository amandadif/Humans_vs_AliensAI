package weapon;

import exceptions.AttachmentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Lead Author: Vladimeer Browning
 */
public class TestAttachment {
  @Test
  public void testMock() throws AttachmentException {
    Weapon gun = new GenericWeapon(1, 1, 1, 1) {
      public int fire(int distance) {
        return 0;
      }
    };
    Attachment mock = new MockAttachment(gun);
    assertEquals(1, mock.getNumAttachments());
  }
}
