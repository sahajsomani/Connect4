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
	      
	      double v = -10000;
	      double alpha = -10000;
	      double beta = 10000;
	      counter = 0;
	      for(int i = 0; i < actions.length; i++) {
	      	if(actions[i]) {
	      		double temp = minValue(board.result(state, i, player), alpha, beta);
	      		if(temp >= v) {
	      			v = temp;
	      			move = i;
	      		} if(v > beta) {
	      			beta = v;
	      		} 
	      	}
	      }
//	      System.out.println(v);
		return move;
	}
	
	public double maxValue(int[][] state, double alpha, double beta) {	
		counter++;
		int util = board.terminalTest(state);
		
		if(util != 3) {
		  if(util == 0) {
			  return 0;
		  } else {
//			  board.printMatrix(state);
//			  System.out.println(board.depth(state));
		    if(util == board.getPlayer()) {
//		    	System.out.println((1.0 / (double)board.depth(state)) + "\n\n");
		    	return (1.0 / (double)board.depth(state));
		    } else {
//		    	System.out.println((-1.0 / (double)board.depth(state)) + "\n\n");
		    	return (-1.0 / (double)board.depth(state));
		    }
		  }
		}
		double v = -10000;
		boolean[] actions = board.actions(state);
		for(int i = 0; i < actions.length; i++) {
		  if(actions[i]) {
		    double temp = minValue(board.result(state, i, board.getPlayer()), alpha, beta);
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
	
	public double minValue(int[][] state, double alpha, double beta) {
		counter++;
		  int util = board.terminalTest(state);
	  
		if(util != 3) {
		  if(util == 0) {
			  return 0;
		  } else {
//			  board.printMatrix(state);
//			  System.out.println(board.depth(state));
			  if(util == board.getPlayer()) {
//				  System.out.println((1.0 / (double)board.depth(state)) + "\n\n");
				  return (1.0 / (double)board.depth(state));
			  } else {
//				  System.out.println((-1.0 / (double)board.depth(state)) + "\n\n");
				  return (-1.0 / (double)board.depth(state));
			  }
		  }
		}
		double v = 10000;
		boolean[] actions = board.actions(state);
		for(int i = 0; i < actions.length; i++) {
		  if(actions[i]) {
		    double temp = maxValue(board.result(state, i, flip(board.getPlayer())), alpha, beta);
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
