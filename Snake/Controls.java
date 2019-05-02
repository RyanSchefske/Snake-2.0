import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 37) {
      if (Threads.direction != 1) {
        Threads.direction = 2;
      }
    }
    if (e.getKeyCode() == 38) {
      if (Threads.direction != 4) {
        Threads.direction = 3;
      }
    }
    if (e.getKeyCode() == 39) {
      if (Threads.direction != 2) {
        Threads.direction = 1;
      }
    }
    if (e.getKeyCode() == 40) {
      if (Threads.direction != 3) {
        Threads.direction = 4;
      }
    }
  }
}
