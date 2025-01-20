package command;

import gameboard.GameBoard;

import java.util.GregorianCalendar;

public class Invoker {

  private Command[] commands;


  private ReloadCommand reloadCommand;
  private Command turnNorthCommand;
  private Command turnSouthCommand;
  private Command moveCommand;
  private Command attackCommand;
  private Command dropCommand;
  private Command acquireCommand;
  private Command turnEastCommand;
  private Command turnWestCommand;





  public Invoker() {

    commands = new Command[9]; //8 buttons on the remote
  }

  /**
   * @param button assigns command to a button
   * @param cmd the command being set
   */
  public void setCommand(int button, Command cmd) {
    if (button >= 0 && button < commands.length) {
      commands[button] = cmd;
    } else {
      System.out.println("Invalid button"); // may need an exception
    }
  }

  /**
   * @param button that you want to press and executes the command
   *               associated with the button.
   */
  public void pressButton(int button) {
    if (button >= 0 && button < commands.length) {
      if (commands[button] != null) {
        commands[button].execute();
      } else {
        System.out.println("Invalid button");
      }
    } else {
      System.out.println("Invalid button index");
    }
  }

  /**
   * @param index of the button in the array
   * @return the Command.
   */
  public Command getCommand(int index) {

    if (index >= 0 && index < commands.length) {
      return commands[index];
    }
    return null;
  }
}
