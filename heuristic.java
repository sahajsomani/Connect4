
public class heuristic extends classBot {
	private int depth = 4;
	public heuristic(Game board) {
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

	      double v = Double.MIN_VALUE;
	      double alpha = Double.MIN_VALUE;
	      double beta = Double.MAX_VALUE;
	      counter = 0;
	      for(int i = 0; i < actions.length; i++) {
	      	if(actions[i]) {
	      		double temp = minValue(board.result(state, i, player), alpha, beta, board.depth(state));
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

	public double maxValue(int[][] state, double alpha, double beta, int initial) {
		counter++;
		double util = board.terminalTest(state);
		System.out.println("\n\n");
		board.printMatrix(state);

		if(util != 3) {
		  if(util == 0) {
			  return 0;
		  } else {
		    if(util == board.getPlayer()) {
		    	return 1.0;
		    } else {
		    	return -1.0;
		    }
		  }
		} else if(board.depth(state) - initial >= depth) {
			util = board.nonTerminal(state);

			if(board.getPlayer() == 1) {
				System.out.println(util);
				return util;
			} else {
				System.out.println(-util);
				return -1 * util;
			}
		}

		double v = Double.MIN_VALUE;
		boolean[] actions = board.actions(state);
		for(int i = 0; i < actions.length; i++) {
		  if(actions[i]) {
		    double temp = minValue(board.result(state, i, board.getPlayer()), alpha, beta, initial);
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

	public double minValue(int[][] state, double alpha, double beta, int initial) {
		counter++;
		  double util = board.terminalTest(state);
			System.out.println("\n\n");
			board.printMatrix(state);
		if(util != 3) {
		  if(util == 0) {
			  return 0;
		  } else {
			  if(util == board.getPlayer()) {
				  return 1.0;
			  } else {
				  return -1.0;
			  }
		  }
		} else if(board.depth(state) - initial >= depth) {

			util = board.nonTerminal(state);
			if(board.getPlayer() == 1) {
				System.out.println(util);
				return util;
			} else {
				System.out.println(-util);
				return -1 * util;
			}
		}
		double v = Double.MAX_VALUE;
		boolean[] actions = board.actions(state);
		for(int i = 0; i < actions.length; i++) {
		  if(actions[i]) {
		    double temp = maxValue(board.result(state, i, flip(board.getPlayer())), alpha, beta, initial);
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
