
public class minimax extends classBot {

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
	player = flip(player);

	int util = board.terminalTest(state);

	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
//	    if((util == 1 && player == 1) || (util == 2 && player == 2)) {
//	    	return 1;
//	    } else if((util == 1 && player == 2) || (util == 2 && player == 1)){
//	    	return -1;
//	    }

		  if(util == 1) {
			  return -1;
		  } else if(util == 2) {
			  return 1;
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
	  player = flip(player);

	  int util = board.terminalTest(state);

	if(util != 3) {
	  if(util == 0) {
		  return 0;
	  } else {
//		  if((util == 1 && player == 1) || (util == 2 && player == 2)) {
//			  return -1;
//		  } else if((util == 1 && player == 2) || (util == 2 && player == 1)){
//			  return 1;
//		  }
		  if(util == 1) {
			  return -1;
		  } else if(util == 2) {
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

  private int flip(int player) {
	  if(player == 1) {
		  return 2;
	  } else {
		  return 1;
	  }
  }
} //end class
