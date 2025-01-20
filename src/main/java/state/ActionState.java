package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {

  protected AiContext context;
  protected Environment environment;
  protected LifeForm lifeform;

  public ActionState(AiContext context, LifeForm lifeform) {
    this.context = context;
    this.lifeform = lifeform;
  }

  abstract void executeAction();

}
