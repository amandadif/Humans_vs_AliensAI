package gameplay;

import java.util.Observer;

public interface Timer {

  public void addTimeObserver(TimerObserver o);

  public void timeChanged();

  public void removeTimeObserver(TimerObserver o);
}
