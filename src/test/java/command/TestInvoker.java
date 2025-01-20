package command;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestInvoker {

  /**
   * Assigns commands to buttons
   * Checks if they were assigned correctly
   */

  @Test
  public void testAssignCommands() {
    Invoker controller = new Invoker();
    Command cmd = new MoveCommand();
    Command cmd2 = new TurnCommand(0);

    controller.setCommand(0, cmd);
    controller.setCommand(1, cmd2);

    assertNotNull(controller.getCommand(0));
    assertNotNull(controller.getCommand(1));
  }

  /**
   * Assign command to button 0
   * Press invalid button at index 10;
   * ensure command for button 0 was not executed.
   */
  @Test
  public void testInvalidButton() {
    Invoker controller = new Invoker();
    Command cmd = new TurnCommand(0);

    controller.setCommand(0, cmd);
    controller.pressButton(10);

    assertFalse(cmd.isExecuted());
  }


  /**
   * Don't assign a command to button 0
   * Presses button 0 and verifies no command is executed.
   */
  @Test
  public void testCommandNotAssigned() {
    Invoker controller = new Invoker();
    Command mockCommand1 = new MoveCommand();

    controller.pressButton(0);
    assertFalse(mockCommand1.isExecuted());
  }


}
