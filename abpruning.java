package connect4;

public class abpruning extends classBot{
	public abpruning(Game board) {
		super(board);
	}
	
	public int play() {
		int move = -1;
	    int[][] state = new int[board.getMatrix().length][board.getMatrix()[0].length];

	    for(int i = 0; i < board.getMatrix().length; i++) {
	        for(int j = 0; j < board.getMatrix()[0].length; j++) {
	          state[i][j] = board.getMatrix()[i][j];
	        }
	      }
	      boolean[] actions = board.actions(state);
	      int player = board.getPlayer();
	      
	      int v = Integer.MIN_VALUE;
	      int alpha = Integer.MIN_VALUE;
	      int beta = Integer.MAX_VALUE;
	      counter = 0;
	      for(int i = 0; i < actions.length; i++) {
	      	if(actions[i]) {
	      		int temp = minValue(board.result(state, i, player), alpha, beta);
	      		if(temp >= v) {
	      			v = temp;
	      			move = i;
	      		} if(v > beta) {
	      			beta = v;
	      		} 
	      	}
	      }
		return move;
	}
	
	public int maxValue(int[][] state, int alpha, int beta) {	
		counter++;
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
		    int temp = minValue(board.result(state, i, board.getPlayer()), alpha, beta);
		    if(temp >= v) {
		    	v = temp;
		    } if(v > alpha) {
		    	alpha = v;
		    } if(alpha >= beta) {
		    	return v;
		    }

		  }
		}
		return v;
	  } //end maxValue
	
	public int minValue(int[][] state, int alpha, int beta) {
		counter++;
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
		    int temp = maxValue(board.result(state, i, flip(board.getPlayer())), alpha, beta);
		    if(temp <= v) {
		    	v = temp;
		    } if(v < beta) {
		    	beta = v;
		    } if(alpha >= beta) {
		    	return v;
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
}
