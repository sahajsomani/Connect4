public abstract class classBot {
  public Game board;
  public int turn;

  public classBot(Game board) {
    this.board = board;
    this.turn = board.getPlayer();
  }

  public Game getBoard() {
    return this.board;
  }

  public void setBoard(Game board) {
    this.board = board;
  }

  public abstract int play();
}
