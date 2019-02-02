import java.util.*;

public class randomPlayer {
  private Game board;
  public randomPlayer(Game board) {
    this.board = board;
  }

  public int play() {
    Random rand = new Random();
    int n = rand.nextInt(board[0].length) + 0;
    while(board.validateCompInput(n) == false) {
      n = rand.nextInt(board[0].length) + 0;
    }
    return n;
  } //end play 

} //end class
