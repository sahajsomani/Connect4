
public class minimax extends classBot {
	private long count = 0;
  public minimax(Game board) {
    super(board);
  }

  @Override
  public int play() { //copying the game board matrix
    int[][] state = new int[board.getMatrix().length][board.getMatrix()[0].length];

    for(int i = 0; i < board.getMatrix().length; i++) {
      for(int j = 0; j < board.getMatrix()[0].length; j++) {
        state[i][j] = board.getMatrix()[i][j];
      }
    }
    boolean[] actions = board.actions(state);
    int player = board.getPlayer();

    int move = -1;
    int v = Integer.MIN_VALUE;
    System.out.println(board.getLen());
    for(int i = 0; i < actions.length; i++) {
    	if(actions[i]) {
    		int temp = minValue(board.result(state, i, player));
    		if(temp >= v) {
    			v = temp;
    			move = i;
    		}
    	}
    }

    return move;
  } //end play

  public int maxValue(int[][] state) {
	int util = board.terminalTest(state);

	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
	    if(util == board.getPlayer()) {
	    	return 1;
	    } else {
	    	return -1;
	    }
	  }
	}
	int v = Integer.MIN_VALUE;
	boolean[] actions = board.actions(state);
	for(int i = 0; i < actions.length; i++) {
	  if(actions[i]) {
	    int temp = minValue(board.result(state, i, board.getPlayer()));
	    if(temp >= v) {
	    	v = temp;
	    }
	  }
	}
	return v;
  } //end maxValue

  public int minValue(int[][] state) {
	  int util = board.terminalTest(state);

	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
		  if(util == board.getPlayer()) {
			  return 1;
		  } else {
			  return -1;
		  }
	  }
	}
	int v = Integer.MAX_VALUE;
	boolean[] actions = board.actions(state);
	for(int i = 0; i < actions.length; i++) {
	  if(actions[i]) {
	    int temp = maxValue(board.result(state, i, flip(board.getPlayer())));
	    if(temp <= v) {
	    	v = temp;
	    }
	  }
	}
	return v;
  } //end minValue

  private int flip(int player) {
	  if(player == 1) {
		  return 2;
	  } else {
		  return 1;
	  }
  }
} //end class
