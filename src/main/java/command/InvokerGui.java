package command;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingUtilities;

public class InvokerGui {

  private Invoker controller;

  public InvokerGui(Invoker controller) {
    this.controller = controller; // pass the build controller to the GUI
  }

  /**
   * creates the controller
   */
  public void createControllerGui() {
    JFrame frame = new JFrame("Game Controller");
    frame.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(5,5,5,5);

    c.gridx = 1;
    c.gridy = 0;
    frame.add(createButton("North", 2), c);

    c.gridx = 0;
    c.gridy = 1;
    frame.add(createButton("West", 0), c);

    c.gridx = 1;
    c.gridy = 2;
    frame.add(createButton("South", 3), c);

    c.gridx = 2;
    c.gridy = 1;
    frame.add(createButton("East", 1), c);

    c.gridx = 1;
    c.gridy = 1;
    frame.add(createButton("Move", 4), c);

    c.gridx = 3;
    c.gridy = 1;
    frame.add(createButton("PickUp", 5), c);

    c.gridx = 4;
    c.gridy = 0;
    frame.add(createButton("Reload", 6), c);

    c.gridx = 5;
    c.gridy = 1;
    frame.add(createButton("Drop", 7), c);

    c.gridx = 4;
    c.gridy = 2;
    frame.add(createButton("Attack", 8), c);

    frame.setSize(500,400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  private JButton createButton(String label, int commandIndex) {
    JButton button = new JButton(label);
    button.setActionCommand(String.valueOf(commandIndex));
    button.addActionListener(e -> controller.pressButton(commandIndex));
    return button;
  }

  public void startGui() {
    SwingUtilities.invokeLater(this::createControllerGui);
  }
}
