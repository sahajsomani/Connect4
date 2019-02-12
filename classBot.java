package connect4;

public abstract class classBot {
  public Game board;
  public long counter;

  public classBot(Game board) {
    this.board = board;
  }

  public Game getBoard() {
    return this.board;
  }

  public void setBoard(Game board) {
    this.board = board;
  }
  
  public long getCounter() {
	  return counter;
  }

  public abstract int play();
}
