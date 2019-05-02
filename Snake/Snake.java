import javax.swing.JFrame;

public class Snake {
  public static void main(String[] args) {
    Board board = new Board();

    board.setTitle("Snake");
    board.setSize(500,500);
    board.setVisible(true);
    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
