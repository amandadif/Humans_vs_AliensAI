package gameplay;

import java.util.Observer;

public class MockSimpleTimerObserver implements TimerObserver {

  public int myTime = 0;

  public void updateTime(int time) {
    myTime = time;
  }
}
