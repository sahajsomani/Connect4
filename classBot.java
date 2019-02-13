package connect4;

public abstract class classBot {//this is an abstract class representation for our bots. it has all of the methods that bots will need.
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
  
  public long getCounter() {//counts the amount of states visited
	  return counter;
  }

  public int flip(int player) {
	  if(player == 1) {
		  return 2;
	  } else {
		  return 1;
	  }
  }
  
  public abstract int play();
}
