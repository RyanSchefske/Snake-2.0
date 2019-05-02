import java.util.ArrayList;
import java.awt.Color;

public class SquareData {

  ArrayList<Color> C = new ArrayList<Color>();
  int color;
  SquarePanel square;

  public SquareData(int col) {
    C.add(Color.blue); // Snake color
    C.add(Color.green); // Food color
    C.add(Color.white);  // Empty squares color
    C.add(Color.red);  // Obstacle color
    color = col;
    square = new SquarePanel(C.get(color));
  }

  public void newColor(int c) {
    square.ChangeColor(C.get(c));
  }

}
