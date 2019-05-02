import java.util.ArrayList;

public class Threads extends Thread {
  ArrayList<ArrayList<SquareData>> Squares = new ArrayList<ArrayList<SquareData>>();
  Position headPos;
  int snakeSize = 3;
  long speed = 100;
  public static int direction;

  ArrayList<Position> positions = new ArrayList<Position>();
  Position foodPos;
  Position obsPos;
  Position obsPos2;
  Position obsPos3;

  Threads(Position startPos) {
    Squares = Board.Grid;

    headPos = new Position(startPos.x, startPos.y);
    direction = 1;

    Position head = new Position(headPos.getX(), headPos.getY());
    positions.add(head);

    foodPos = new Position(Board.height - 3, Board.width - 3);
    spawnFood(foodPos);

    obsPos = new Position(Board.height - 5, Board.width - 8);
    spawnObs(obsPos);
    obsPos2 = new Position(Board.height - 15, Board.width - 2);
    spawnObs(obsPos2);
    obsPos3 = new Position(Board.height - 1, Board.width - 19);
    spawnObs(obsPos3);
  }

  public void run() {
    while (true) {
      moveIn(direction);
      collision();
      moveEx();
      deleteTail();
      nextMove();
    }
  }

  private void nextMove() {
    try {
      sleep(speed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void collision() {
    Position newPos = positions.get(positions.size() - 1);
    for (int i = 0; i <= positions.size() - 2; i++) {
      boolean biteSnake = newPos.getX() == positions.get(i).getX() && newPos.getY() == positions.get(i).getY();
      if (biteSnake) {
        System.out.println("You ate yourself!");
        gameOver();
      }
    }

    boolean hitObs = newPos.getX() == obsPos.y && newPos.getY() == obsPos.x;
    if (hitObs) {
      System.out.println("Obstacle hit!");
      gameOver();
    }
    boolean hitObs2 = newPos.getX() == obsPos2.y && newPos.getY() == obsPos2.x;
    if (hitObs2) {
      System.out.println("Obstacle hit!");
      gameOver();
    }
    boolean hitObs3 = newPos.getX() == obsPos3.y && newPos.getY() == obsPos3.x;
    if (hitObs3) {
      System.out.println("Obstacle hit!");
      gameOver();
    }

    boolean eatFood = newPos.getX() == foodPos.y && newPos.getY() == foodPos.x;
    if (eatFood) {
      removeObs(obsPos);
      removeObs(obsPos2);
      removeObs(obsPos3);
      snakeSize = snakeSize + 1;
      foodPos = newFoodPos();
      obsPos = newObsPos();
      obsPos2 = newObsPos2();
      obsPos3 = newObsPos3();
      spawnFood(foodPos);
      spawnObs(obsPos);
      spawnObs2(obsPos2);
      spawnObs3(obsPos3);
    }
  }

  private void gameOver() {
    while (true) {
      nextMove();
    }
  }

  private void removeObs(Position obsPosIn) {
    Squares.get(obsPosIn.x).get(obsPosIn.y).newColor(2);
  }

  private void spawnObs(Position obsPosIn) {
    Squares.get(obsPosIn.x).get(obsPosIn.y).newColor(3);
  }
  private void spawnObs2(Position obsPosIn) {
    Squares.get(obsPosIn.x).get(obsPosIn.y).newColor(3);
  }
  private void spawnObs3(Position obsPosIn) {
    Squares.get(obsPosIn.x).get(obsPosIn.y).newColor(3);
  }

  private Position newObsPos() {
    Position p;
    int ranX = 0 + (int)(Math.random()*19);
    int ranY = 0 + (int)(Math.random()*19);
    p = new Position(ranX, ranY);
    for (int i = 0; i <= positions.size() - 1; i++) {
      if (p.getY() == positions.get(i).getXb() && p.getX() == positions.get(i).getYb()) {
        ranX = 0 + (int)(Math.random()*19);
        ranY = 0 + (int)(Math.random()*19);
        p = new Position(ranX, ranY);
        i = 0;
      }
    }
    return p;
  }

  private Position newObsPos2() {
    Position p;
    int ranX = 0 + (int)(Math.random()*19);
    int ranY = 0 + (int)(Math.random()*19);
    p = new Position(ranX, ranY);
    for (int i = 0; i <= positions.size() - 1; i++) {
      if (p.getY() == positions.get(i).getXb2() && p.getX() == positions.get(i).getYb2()) {
        ranX = 0 + (int)(Math.random()*19);
        ranY = 0 + (int)(Math.random()*19);
        p = new Position(ranX, ranY);
        i = 0;
      }
    }
    return p;
  }

  private Position newObsPos3() {
    Position p;
    int ranX = 0 + (int)(Math.random()*19);
    int ranY = 0 + (int)(Math.random()*19);
    p = new Position(ranX, ranY);
    for (int i = 0; i <= positions.size() - 1; i++) {
      if (p.getY() == positions.get(i).getXb3() && p.getX() == positions.get(i).getYb3()) {
        ranX = 0 + (int)(Math.random()*19);
        ranY = 0 + (int)(Math.random()*19);
        p = new Position(ranX, ranY);
        i = 0;
      }
    }
    return p;
  }

  private void spawnFood(Position foodPosIn) {
    Squares.get(foodPosIn.x).get(foodPosIn.y).newColor(1);
  }

  private Position newFoodPos() {
    Position p;
    int ranX = 0 + (int)(Math.random()*19);
    int ranY = 0 + (int)(Math.random()*19);
    p = new Position(ranX, ranY);
    for (int i = 0; i <= positions.size() - 1; i++) {
      if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
        ranX = 0 + (int)(Math.random()*19);
        ranY = 0 + (int)(Math.random()*19);
        p = new Position(ranX, ranY);
        i = 0;
      }
    }
    return p;
  }

  private void moveIn(int dir) {
    switch(dir) {
      case 4:
        headPos.ChangeData(headPos.x, (headPos.y + 1) % 20);
        positions.add(new Position(headPos.x, headPos.y));
        break;
      case 3:
        if (headPos.y - 1 < 0) {
          headPos.ChangeData(headPos.x, 19);
        } else {
          headPos.ChangeData(headPos.x, Math.abs(headPos.y - 1) %20);
        }
        positions.add(new Position(headPos.x, headPos.y));
        break;
      case 2:
        if (headPos.x - 1 < 0) {
          headPos.ChangeData(19, headPos.y);
        } else {
          headPos.ChangeData(Math.abs(headPos.x - 1) % 20, headPos.y);
        }
        positions.add(new Position(headPos.x, headPos.y));
        break;
      case 1:
        headPos.ChangeData(Math.abs(headPos.x + 1) % 20, headPos.y);
        positions.add(new Position(headPos.x, headPos.y));
        break;
    }
  }

  private void moveEx() {
    for (Position p : positions) {
      int y = p.getX();
      int x = p.getY();
      Squares.get(x).get(y).newColor(0);
    }
  }

  private void deleteTail() {
    int size = snakeSize;
    for (int i = positions.size() - 1; i >= 0; i--) {
      if (size == 0) {
        Position p = positions.get(i);
        Squares.get(p.y).get(p.x).newColor(2);
      } else {
        size--;
      }
    }
    size = snakeSize;
    for (int i = positions.size() - 1; i >= 0; i--) {
      if (size == 0) {
        positions.remove(i);
      } else {
        size--;
      }
    }
  }

}
