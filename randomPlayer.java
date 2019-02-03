package connect4;

import java.util.*;

public class randomPlayer extends classBot{

  public randomPlayer(Game board) {
    super(board);
  }

  @Override
  public int play() {
    Random rand = new Random();
    int n = rand.nextInt(board.getMatrix()[0].length) + 0;
    while(board.validateInput(n, board.actions(board.getMatrix())) == false) {
      n = rand.nextInt(board.getMatrix()[0].length) + 0;
    }
    return n;
  } //end play

} //end class
