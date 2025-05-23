package controllers;

import static org.mockito.Mockito.*;

import observers.ButtonClickObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ButtonClickNotifierTest {

  private ButtonClickNotifier notifier;
  private ButtonClickObserver mockObserver1;
  private ButtonClickObserver mockObserver2;

  @BeforeEach
  void setUp() {
    notifier = new ButtonClickNotifier() {};
    mockObserver1 = mock(ButtonClickObserver.class);
    mockObserver2 = mock(ButtonClickObserver.class);
  }

  @Test
  void testAddAndNotifyObserver() {
    notifier.addObserver(mockObserver1);
    notifier.notifyObservers("action1");

    verify(mockObserver1, times(1)).update("action1");
  }

  @Test
  void testRemoveObserver() {
    notifier.addObserver(mockObserver1);
    notifier.removeObserver(mockObserver1);
    notifier.notifyObservers("action2");

    verify(mockObserver1, never()).update("action2");
  }
}