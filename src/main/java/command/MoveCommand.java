package command;

import environment.Environment;
import gameboard.GameBoard;

public class MoveCommand implements Command {


  private boolean executed = false;

  /**
   * executes the command.
   */
  public void execute() {
    Environment environment = Environment.getInstance();
    GameBoard gameboard = GameBoard.getInstance();
    if (environment.getLifeForm(environment.focusRow, environment.focusCol) != null) {
      environment.moveLifeForm(gameboard.getLifeform());
      executed = true;
      //System.out.println("Moved " + gameboard.getLifeform().getName());
    } else {
      System.out.println("No LifeForm to move.");
    }
  }


  public boolean isExecuted() {
    return executed;
  }

  @Override
  public void reset() {
    executed = false;
  }
}
