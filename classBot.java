public abstract class classBot {
  private Game board;

  public classBot(Game board) {
    this.board = board;
  }

  public Game getBoard() {
    return this.board;
  }

  public void setBoard(Game board) {
    this.board = board;
  }

  public abstract int play();
}
