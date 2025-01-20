package command;

public interface Command {

  public void execute();

  boolean isExecuted();

  public void reset();
  
}
