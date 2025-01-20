package gameplay;

import org.junit.Test;

import java.util.Observer;

import static org.junit.Assert.assertEquals;

public class TestSimpleTimer {

  @Test
  public void testInstance() {
    SimpleTimer time = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    assertEquals(0, time.getRound());
    assertEquals(0, time.getNumObservers());
  }

  @Test
  public void testTimeChanged() {
    SimpleTimer time = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    time.timeChanged();
    time.timeChanged();
    assertEquals(2, time.getRound());
  }

  @Test
  public void testAddObserver() {
    SimpleTimer time = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    time.addTimeObserver(observer);
    assertEquals(1, time.getNumObservers());
  }

  @Test
  public void testRemoveObserver() {
    SimpleTimer time = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    time.addTimeObserver(observer);
    assertEquals(1, time.getNumObservers());
    time.removeTimeObserver(observer);
    assertEquals(0, time.getNumObservers());
  }

  @Test
  public void testTimeUpdates() {
    SimpleTimer time = new SimpleTimer();
    MockSimpleTimerObserver observer = new MockSimpleTimerObserver();
    time.addTimeObserver(observer);
    time.timeChanged();
    assertEquals(1, time.getNumObservers());
    assertEquals(1, time.getRound());

  }


  @Test
  public void testSimpleTimerAsThread() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound()); //assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }
}
