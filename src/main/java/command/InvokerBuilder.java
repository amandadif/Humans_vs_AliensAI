package command;

import javax.swing.JFrame;

public class InvokerBuilder extends JFrame {

  private Invoker controller;

  public InvokerBuilder() {
    controller = new Invoker();
  }

  public InvokerBuilder setButton(int button, Command cmd) {
    controller.setCommand(button, cmd);
    return this;
  }

  public Invoker build() {
    return controller;
  }

  /*
      button Index mapping
      0 - West
      1 - East
      2 - North
      3 - South
      4 - Move
      5 - Pick Up
      6 - Reload
      7 - Drop
      8 - Attack
   */

  /**
   *
   * @return the invoker with the commands loaded.
   */
  public Invoker loadCommands() {
    this.setButton(0, new TurnCommand(0));
    this.setButton(1, new TurnCommand(1));
    this.setButton(2, new TurnCommand(2));
    this.setButton(3, new TurnCommand(3));
    this.setButton(4, new MoveCommand());
    this.setButton(5, new AcquireWeaponCommand());
    this.setButton(6, new ReloadCommand());
    this.setButton(7, new DropWeaponCommand());
    this.setButton(8, new AttackCommand());
    return controller;
  }
}

/*
//frame with grid layout for buttons
setTitle("Controller");
setLayout(new GridLayout(3,3));

        //initialize commands for each button
        commands.add(new reloadCommand());
        commands.add(new turnNorthCommand());
        commands.add(new turnEastCommand());
        commands.add(new turnSouthCommand());
        commands.add(new turnWestCommand());
        commands.add(new moveCommand());
        commands.add(new attackCommand());
        commands.add(new dropCommand());
        commands.add(new acquireCommand());

 */