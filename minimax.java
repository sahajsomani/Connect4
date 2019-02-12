
public class minimax extends classBot {
  public minimax(Game board) {
    super(board);
  }

  @Override
  public int play() { //copying the game board matrix
    int[][] state = new int[board.getMatrix().length][board.getMatrix()[0].length];
    int move = -1;

    for(int i = 0; i < board.getMatrix().length; i++) {
      for(int j = 0; j < board.getMatrix()[0].length; j++) {
        state[i][j] = board.getMatrix()[i][j];
      }
    }
    boolean[] actions = board.actions(state);
    int player = board.getPlayer();
    int depth = board.depth(state);

    counter = 0;
    int[] v = {Integer.MIN_VALUE, depth};
    for(int i = 0; i < actions.length; i++) {
    	if(actions[i]) {
    		int[] temp = minValue(board.result(state, i, player));
    		if(temp[0] > v[0]) {
    			v = temp;
    			move = i;
    		} else if(temp[0] == v[0]) {
    	    	if(temp[0] <= 0) {
    	    		if(temp[1] > v[1]) {
    	    			v = temp;
    	    			move = i;
    	    		}
    	    	} else {
    	    		if(temp[1] < v[1]) {
    	    			v = temp;
    	    			move = i;
    	    		}
    	    	}
    	    }
    	}
    }

    return move;
  } //end play

  public int[] maxValue(int[][] state) {
	  counter++;
	int util = board.terminalTest(state);
	int depth = board.depth(state);
	int[] result = {0, depth};

	if(util != 3) {
	  if(util == 0) {
		  return result;
	  } else {
	    if(util == board.getPlayer()) {
	    	result[0] = 1;
	    	return result;
	    } else {
	    	result[0] = -1;
	    	return result;
	    }
	  }
	}
	int[] v = {Integer.MIN_VALUE, depth};
	boolean[] actions = board.actions(state);
	for(int i = 0; i < actions.length; i++) {
	  if(actions[i]) {
	    int[] temp = minValue(board.result(state, i, board.getPlayer()));
	    if(temp[0] > v[0]) {
	    	v = temp;
	    } else if(temp[0] == v[0]) {
	    	if(temp[0] <= 0) {
	    		if(temp[1] > v[1]) {
	    			v = temp;
	    		}
	    	} else {
	    		if(temp[1] < v[1]) {
	    			v = temp;
	    		}
	    	}
	    }
	  }
	}
	result = v;
	return result;
  } //end maxValue

  public int[] minValue(int[][] state) {
	  counter++;
	  int util = board.terminalTest(state);
	  int depth = board.depth(state);
	  int[] result = {0, depth};

	if(util != 3) {
	  if(util == 0) {
		  return result;
	  } else {
		  if(util == board.getPlayer()) {
			  result[0] = 1;
			  return result;
		  } else {
			  result[0] = -1;
			  return result;
		  }
	  }
	}
	int[] v = {Integer.MAX_VALUE, depth};
	boolean[] actions = board.actions(state);
	for(int i = 0; i < actions.length; i++) {
	  if(actions[i]) {
	    int[] temp = maxValue(board.result(state, i, flip(board.getPlayer())));
	    if(temp[0] < v[0]) {
	    	v = temp;
	    } else if(temp[0] == v[0]) {
	    	if(temp[0] <= 0) {
	    		if(temp[1] < v[1]) {
	    			v = temp;
	    		}
	    	} else {
	    		if(temp[1] > v[1]) {
	    			v = temp;
	    		}
	    	}
	    }
	  }
	}
	result = v;
	return result;
  } //end minValue

  private int flip(int player) {
	  if(player == 1) {
		  return 2;
	  } else {
		  return 1;
	  }
  }
} //end class
