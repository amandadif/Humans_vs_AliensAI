package gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class SimpleTimer extends Thread implements Timer {
  private List<TimerObserver> theObservers = new ArrayList<>();
  private int round;
  private int sleep;

  public SimpleTimer() {
  }

  public SimpleTimer(int sleep) {
    this.sleep = sleep;
  }

  @Override
  public void run() {
    boolean running = true;
    while (round <= 50 && running) {
      try {
        Thread.sleep(sleep);
        timeChanged();
      } catch (InterruptedException e) {
        running = false;
      }
    }
  }


  public void addTimeObserver(TimerObserver o) {
    theObservers.add(o);
  }

  public void timeChanged() {
    round = round + 1;
    notifyObservers();
  }

  public void removeTimeObserver(TimerObserver o) {
    theObservers.remove(o);
  }

  public int getNumObservers() {
    return theObservers.size();
  }

  public int getRound() {
    return round;
  }

  /**
   *
   */
  public void notifyObservers() {
    for (TimerObserver o : theObservers) {
      o.updateTime(round);
    }
  }
}
