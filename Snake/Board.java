import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;

class Board extends JFrame {
  public static ArrayList <ArrayList<SquareData>> Grid;
  public static int width = 20;
  public static int height = 20;

  public Board() {
    Grid = new ArrayList <ArrayList<SquareData>>();
    ArrayList<SquareData> data;

    for (int i = 0; i < width; i++) {
      data = new ArrayList<SquareData>();
      for (int j = 0; j < height; j++) {
        SquareData c = new SquareData(2);
        data.add(c);
      }
      Grid.add(data);
    }

    getContentPane().setLayout(new GridLayout(20,20,0,0));

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        getContentPane().add(Grid.get(i).get(j).square);
      }
    }

    Position position = new Position(10,10);

    Threads c = new Threads(position);

    c.start();

    this.addKeyListener((KeyListener) new Controls());
  }
}
