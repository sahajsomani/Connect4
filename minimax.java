public class minimax extends classBot {

  public minimax(Game board) {
    super(board);
  }

  @Override
  public int play() { //copying the game board matrix
    int[][] mat = new int[board.getMatrix().length][board.getMatrix()[0].length];
    for(int i = 0; i < board.getMatrix().length; i++) {
      for(int j = 0; j < board.getMatrix()[0].length; j++) {
        mat[i][j] = board.getMatrix()[i][j];
      }
    }
    return 0;
  } //end play

  private int minimaxDecision(int[][] state) {
    int[] actions = new int[state[0].length];
    int count = Integer.MIN_VALUE;
    int action = -1; //initialised to -1 so that it throws an error if count does not change
    int temp = count;
    for(int i = 0; i < actions.length; i++) {
      count = Math.max(count, minValue(board.result(state, i)));
      if(count > temp) {
        action = i;
        temp = count;
      }
    }
    return action;
  } //end minimaxDecision

  public int maxValue(int[][] state) {
    if(board.terminalTest(state, board.getLen()) != 3) {
      if(board.terminalTest(state, board.getLen()) == 2) {
        return -1;
      } else {
        return board.terminalTest(state, board.getLen());
      }
    }
    int v = Integer.MIN_VALUE;
    boolean[] validActions = board.actions(state);
    for(int i = 0; i < validActions.length; i++) {
      if(board.validateInput(i,validActions)) {
        v = Math.max(v,minValue(board.result(state, i)));
      }
    }
    return v;
  } //end maxValue

  public int minValue(int[][] state) {
    if(board.terminalTest(state, board.getLen()) != 3) {
      if(board.terminalTest(state, board.getLen()) == 1) {
        return -1;
      } else if(board.terminalTest(state, board.getLen()) == 2){
        return 1;
      } else {
        return board.terminalTest(state, board.getLen());
      }
    }
    int v = Integer.MAX_VALUE;
    boolean[] validActions = board.actions(state);
    for(int i = 0; i < validActions.length; i++) {
      if(board.validateInput(i,validActions)) {
        v = Math.min(v,maxValue(board.result(state, i)));
      }
    }
    return v;
  } //end minValue

} //end class
