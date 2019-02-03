
public class minimax extends classBot {

  public minimax(Game board) {
    super(board);
  }

  @Override
  public int play() { //copying the game board matrix
    int[][] state = new int[board.getMatrix().length][board.getMatrix()[0].length];
    int player = board.getPlayer();
    boolean[] actions = board.actions(state);
    int move = -1;
    
    for(int i = 0; i < board.getMatrix().length; i++) {
      for(int j = 0; j < board.getMatrix()[0].length; j++) {
        state[i][j] = board.getMatrix()[i][j];
      }
    }
    int v = Integer.MIN_VALUE;
    
    for(int i = 0; i < actions.length; i++) {
    	if(actions[i]) {
    		int temp = minValue(board.result(state, i, player), player);
    		if(temp > v) {
    			v = temp;
    			move = i;
    		}
    	}
    }
    
    return move;
  } //end play

  public int maxValue(int[][] state, int player) {
	if(player == 1) {
		player = 2;
	} else {
		player = 1;
	}
	
	int util = board.terminalTest(state, board.getLen());
	
	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
	    if(util == player) {
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
	    int temp = minValue(board.result(state, i, player), player);
	    if(temp > v) {
	    	v = temp;
	    }
	  }
	}
	return v;
  } //end maxValue

  public int minValue(int[][] state, int player) {
  if(player == 1) {
	  player = 2;
  } else {
	  player = 1;
  }
  
  int util = board.terminalTest(state, board.getLen());
  
	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
		  if(util == player) {
			  return -1;
		  } else {
			  return 1;
		  }
	  }
	}
	int v = Integer.MAX_VALUE;
	boolean[] actions = board.actions(state);
	for(int i = 0; i < actions.length; i++) {
	  if(actions[i]) {
	    int temp = maxValue(board.result(state, i, player), player);
	    if(temp < v) {
	    	v = temp;
	    }
	  }
	}
	return v;
  } //end minValue

} //end class
