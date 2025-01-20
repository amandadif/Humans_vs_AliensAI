package command;

import environment.Environment;
import gameboard.GameBoard;
import lifeform.Human;
import lifeform.LifeForm;

public class TurnCommand implements Command {

  Human bob = new Human("Bob", 10, 10);


  boolean executed = false;

  int command;

  public TurnCommand(int button) {
    command = button;
  }

  public boolean isExecuted() {
    return executed;
  }

  Command turnWestCommand = new Command() {

    public void execute() {
      Environment env = Environment.getInstance();
      GameBoard gameBoard = GameBoard.getInstance();
      if (env.getLifeForm(env.focusRow, env.focusCol) != null) {
        //LifeForm focuslifeform = env.getLifeForm(env.focusRow, env.focusCol);
        LifeForm focuslifeform = gameBoard.getLifeform();
        focuslifeform.setDirection("West");
        env.notifyObservers(env.focusRow,env.focusCol,
                env.getLifeForm(env.focusRow, env.focusCol),
                focuslifeform.getWeapon(), focuslifeform.getWeapon2());
        executed = true;
      } else {
        System.out.println("No LifeForm to turn");
      }
    }

    public boolean isExecuted() {
      return executed;
    }

    @Override
    public void reset() {
      executed = false;
    }
  };

  Command turnEastCommand = new Command() {
    public void execute() {
      Environment env = Environment.getInstance();
      GameBoard gameBoard = GameBoard.getInstance();
      if (env.getLifeForm(env.focusRow, env.focusCol) != null) {
        //LifeForm focuslifeform = env.getLifeForm(env.focusRow, env.focusCol);
        LifeForm focuslifeform = gameBoard.getLifeform();
        focuslifeform.setDirection("East");
        env.notifyObservers(env.focusRow,env.focusCol,
                env.getLifeForm(env.focusRow, env.focusCol),
                focuslifeform.getWeapon(), focuslifeform.getWeapon2());
        executed = true;
      } else {
        System.out.println("No LifeForm to turn");
      }
    }

    public boolean isExecuted() {
      return executed;
    }

    @Override
    public void reset() {
      executed = false;
    }
  };

  Command turnNorthCommand = new Command() {
    public void execute() {
      Environment env = Environment.getInstance();
      GameBoard gameBoard = GameBoard.getInstance();
      if (env.getLifeForm(env.focusRow, env.focusCol) != null) {
        //LifeForm focuslifeform = env.getLifeForm(env.focusRow, env.focusCol);
        LifeForm focuslifeform = gameBoard.getLifeform();
        focuslifeform.setDirection("North");
        env.notifyObservers(env.focusRow,env.focusCol,
                env.getLifeForm(env.focusRow, env.focusCol),
                focuslifeform.getWeapon(), focuslifeform.getWeapon2());
        executed = true;
      } else {
        System.out.println("No LifeForm to turn");
      }
    }

    public boolean isExecuted() {
      return executed;
    }

    @Override
    public void reset() {
      executed = false;
    }
  };

  Command turnSouthCommand = new Command() {
      public void execute() {
        Environment env = Environment.getInstance();
        GameBoard gameBoard = GameBoard.getInstance();
        if (env.getLifeForm(env.focusRow, env.focusCol) != null) {
        //LifeForm focuslifeform = env.getLifeForm(env.focusRow, env.focusCol);
        LifeForm focuslifeform = gameBoard.getLifeform();
        focuslifeform.setDirection("South");
        env.notifyObservers(env.focusRow,env.focusCol,
                env.getLifeForm(env.focusRow, env.focusCol),
                focuslifeform.getWeapon(), focuslifeform.getWeapon2());
        executed = true;
      } else {
        System.out.println("No LifeForm to turn");
      }
    }

    public boolean isExecuted() {
      return executed;
    }

    @Override
    public void reset() {
      executed = false;
    }
  };

  /**
   * executes which command is set.
   */
  public void execute() {
    switch (command) {
      case 0:
        turnWestCommand.execute();
        System.out.println("Turn West");
        break;
      case 1:
        turnEastCommand.execute();
        System.out.println("Turn East");
        break;
      case 2:
        turnNorthCommand.execute();
        System.out.println("Turn North");
        break;
      case 3:
        turnSouthCommand.execute();
        System.out.println("Turn South");
        break;
      default:
        command = 2;
        break;
    }
  }

  /**
   * resets the executed variable to false.
   */
  @Override
  public void reset() {
    executed = false;
  }
}
