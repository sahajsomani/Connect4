package connect4;

import java.util.*;

public class Game {
  private int[][] matrix;
  private int player;
  private int len;
  public static void main(String[] args)  {
	  Game test = new Game(3);
	  classBot bot = new heuristic(test);
	  test.printMatrix(test.getMatrix());
	  while(true) {
		  test.setMatrix(test.result(test.getMatrix(), test.takeInput(test.actions(test.getMatrix())), test.getPlayer()));
		  test.turn();
		  test.printMatrix(test.getMatrix());
		  
		  int num = bot.play();
		  System.out.println("Bot played " + num);
		  System.out.println("States visited " + bot.getCounter());
		  test.setMatrix(test.result(test.getMatrix(), num, test.getPlayer()));
		  test.turn();

		  test.printMatrix(test.getMatrix());
		  
		  System.out.println("\n\n");
	  }

	  
//	  test.printMatrix(test.getMatrix());
//	  test.setMatrix(test.result(test.getMatrix(), 0, test.getPlayer()));
//	  test.setMatrix(test.result(test.getMatrix(), 0, test.getPlayer()));
////	  test.setMatrix(test.result(test.getMatrix(), 2, 2));
////	  test.setMatrix(test.result(test.getMatrix(), 2, 2));
//	  test.setMatrix(test.result(test.getMatrix(), 1, 2));
//	  test.setMatrix(test.result(test.getMatrix(), 1, 2));
//
//
//	  
//	  test.printMatrix(test.getMatrix());
//	  System.out.println(test.nonTerminal(test.getMatrix()));
  } //end main

  public Game(int input){
    this.player = 1; // player 1 = human, 2 = computer
    int a = 6;
    int b = 7;
    len = 4;
    if(input == 1){
      a = 3;
      b = 3;
      len = 3;
    } else if(input == 2) {
      a = 3;
      b = 5;
      len = 3;
    } else if(input == 4) {
    	a = 4;
    	b = 4;
    	len = 4;
    }
    this.matrix = new int[a][b];
    for(int i = 0; i < a; i++) {
      for(int j = 0; j < b; j++) {
        this.matrix[i][j] = 0;
      }
    }
  } //end constructor

  public int[][] getMatrix() {
    return this.matrix;
  } //end getMatrix

  public void setMatrix(int[][] matrix) {
    this.matrix = matrix;
  } //end setMatrix

  public int getLen() {
    return this.len;
  }

  public void setLen(int len) {
    this.len = len;
  }

  public int getPlayer() {
    return this.player;
  }

  public void setPlayer(int player) {
    this.player = player;
  }
  
  public void turn() {
	  if(this.player == 1) {
		  this.player = 2;
	  } else {
		  this.player = 1;
	  }
  }

  public boolean[] actions(int[][] state) { //checks for valid actions, true means action will be valid, false action is illegal
    int width = state[0].length;
    boolean[] list = new boolean[width];
    for(int i = 0; i < width; i++) {
      if(state[0][i] == 0) {
        list[i] = true;
      } else {
        list[i] = false;
      }
    }

    return list;
  } //end actions

  public double nonTerminal(int[][] state) {
	  double pos = 0;
	  double neg = 0;
	  int width = state[0].length;
	    int height = state.length;
	    ArrayList<Integer> temp = new ArrayList<Integer>();

	    for(int i = 0; i <= height - this.len; i++) {
	    	for(int j = 0; j < width; j++) {
	    		for(int k = 0; k < this.len; k++) {
	    			temp.add(state[i + k][j]);
	    		}
//	    		System.out.println(temp);
	    		double value = nonTerminalHelp(temp);
//	    		System.out.println(value);
	    		if(value > 0) {
	    			pos += value;
	    		} else {
	    			neg += value;
	    		}
	    		temp.clear();
	    	}
	    }
	    
	    for(int i = 0; i < height; i++) {
	    	for(int j = 0; j <= width - this.len; j++) {
	    		for(int k = 0; k < this.len; k++) {
	    			temp.add(state[i][j + k]);
	    		}
//	    		System.out.println(temp);
	    		double value = nonTerminalHelp(temp);
//	    		System.out.println(value);
	    		if(value > 0) {
	    			pos += value;
	    		} else {
	    			neg += value;
	    		}
	    		temp.clear();
	    	}
	    }
	    
	    for(int i = 0; i <= height - this.len; i++) {
	    	for(int j = 0; j <= width - this.len; j++) {
	    		for(int k = 0; k < this.len; k++) {
	    			temp.add(state[i + k][j + k]);
	    		}
//	    		System.out.println(temp);
	    		double value = nonTerminalHelp(temp);
//	    		System.out.println(value);
	    		if(value > 0) {
	    			pos += value;
	    		} else {
	    			neg += value;
	    		}
	    		temp.clear();
	    	}
	    }
	    
	    for(int i = 0; i <= height - this.len; i++) {
	    	for(int j = width - 1; j >= this.len - 1; j--) {
	    		for(int k = 0; k < this.len; k++) {
	    			temp.add(state[i + k][j - k]);
	    		}
//	    		System.out.println(temp);
	    		double value = nonTerminalHelp(temp);
//	    		System.out.println(value);
	    		if(value > 0) {
	    			pos += value;
	    		} else {
	    			neg += value;
	    		}
	    		temp.clear();
	    	}
	    }
	    
	    double total = Math.abs(neg) + pos;
//	    System.out.println("\n\n" + neg + " " + pos + " " + total);
	    if(total == 0) {
	    	return 0;
	    } else {
	    	return (pos + neg) / (1.5 * total);
	    }
  }
  
  private double nonTerminalHelp(ArrayList<Integer> array) {
	  int X = 0;
	  int O = 0;
	  int zero = 0;
	  for(int i = 0; i < array.size(); i++) {
		  if(array.get(i) == 1) {
			  X++;
		  } else if(array.get(i) == 0) {
			  zero++;
		  } else {
			  O++;
		  }
	  }
	  if(X == array.size() - 1 && zero == 1) {
		  return (double)(array.size() - 1) / (double)(array.size());
	  } else if(array.size() > 3 && X == array.size() - 2 && zero == 2) {
		  return (double)(array.size() - 2) / (double)(array.size());
	  } else if(O == array.size() - 1 && zero == 1) {
		  return -(double)(array.size() - 1) / (double)(array.size());
	  } else if(array.size() > 3 && O == array.size() - 2 && zero == 2) {
		  return -(double)(array.size() - 2) / (double)(array.size());
	  }
	  return 0;
  }
  
  public int terminalTest(int[][] state) { // to check if we are at a terminal state
    int width = state[0].length;
    int height = state.length;
    boolean full = true;
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for(int i = 0; i < width; i++) {
      if(state[0][i] == 0) {
        full = false;
        i = width;
      }
    }

    for(int i = 0; i <= height - this.len; i++) {
    	for(int j = 0; j < width; j++) {
    		for(int k = 0; k < this.len; k++) {
    			temp.add(state[i + k][j]);
    		}
    		if(terminalHelp(temp)) {
    			i = height;
    			j = width;
    			break;
    		}
    		temp.clear();
    	}
    }
    if(!temp.isEmpty()) {
    	return temp.get(0);
    }
    
    for(int i = 0; i < height; i++) {
    	for(int j = 0; j <= width - this.len; j++) {
    		for(int k = 0; k < this.len; k++) {
    			temp.add(state[i][j + k]);
    		}
    		if(terminalHelp(temp)) {
    			i = height;
    			j = width;
    			break;
    		}
    		temp.clear();
    	}
    }
    if(!temp.isEmpty()) {
    	return temp.get(0);
    }
    
    for(int i = 0; i <= height - this.len; i++) {
    	for(int j = 0; j <= width - this.len; j++) {
    		for(int k = 0; k < this.len; k++) {
    			temp.add(state[i + k][j + k]);
    		}
    		if(terminalHelp(temp)) {
    			i = height - this.len;
    			j = width;
    			break;
    		}
    		temp.clear();
    	}
    }
    if(!temp.isEmpty()) {
    	return temp.get(0);
    }
    
    for(int i = 0; i <= height - this.len; i++) {
    	for(int j = width - 1; j >= this.len - 1; j--) {
    		for(int k = 0; k < this.len; k++) {
    			temp.add(state[i + k][j - k]);
    		}
    		if(terminalHelp(temp)) {
    			i = height;
    			j = 0;
    			break;
    		}
    		temp.clear();
    	}
    }
    if(!temp.isEmpty()) {
    	return temp.get(0);
    }

    if(full) {
      return 0;
    }

    return 3;
  }

  private boolean terminalHelp(ArrayList<Integer> array) {
    for(int i = 0; i < array.size() - 1; i++) {
      if(array.get(i) != array.get(i + 1) || array.get(i) == 0) {
        return false;
      }
    }
    return true;
  }

  public int depth(int[][] state) {
	  int count = 0;
	  for(int i = 0; i < state.length; i++) {
		  for(int j = 0; j < state[i].length; j++) {
			  if(state[i][j] == 1 || state[i][j] == 2) {
				  count++;
			  }
		  }
	  }
	  return count;
  }
  
  public void printMatrix(int[][] state) {
    int rows = state.length;
    int cols = state[0].length;

    // prints the state using the 2d array
    for(int i = -1; i < rows+1; i++) {
      if((i == -1) || (i == rows)) {
        for(int k = -1; k < cols+1; k++) { //printing the outline
          if((k == -1) || (k == cols)) {
            System.out.print("  ");
          } else {
            System.out.print(Integer.toString(k) + " ");
          }
        }
        System.out.println();
        continue;
      }
      for(int j = -1; j < cols+1; j++) {
        if((j == -1) || (j == cols)) {
          System.out.print(Integer.toString(i) + " ");
          continue;
        }
        if(state[i][j] == 0) {
          System.out.print("  ");
        } else {
          if(state[i][j] == 1) {
            System.out.print("X ");
          } else {
            System.out.print("O ");
          }
          //System.out.print(Integer.toString(state[i][j]) + " ");
        }

      } //end j for loop
      System.out.println();
    } //end i for loop
  } //end printMatrix

  public int takeInput(boolean[] validActions) {
    Scanner scan = new Scanner(System.in);
    boolean check = true;
    int input = 0;
    while(check) {
      System.out.print("User input: ");
      input = scan.nextInt();
      if(input >= validActions.length) {
        System.out.println("Invalid input. Column does not exist");
        continue;
      }
      if(validActions[input] != true) {
        System.out.println("Invalid input. Column is already full");
        continue;
      }
      check = false;
    }
//    scan.close();
    return input;
  } //end takeInput

  public boolean validateInput(int input, boolean[] validActions) {
    if(input >= validActions.length) {
      return false;
    }
    if(validActions[input] != true) {
      return false;
    }
    return true;
  } //end validateHumanInput

  public int[][] result(int[][] state, int action, int player) {
	  int[][] temp = new int[state.length][state[0].length];
	  for(int i = 0; i < state.length; i++) {
		  for(int j = 0; j < state[0].length; j++) {
			  temp[i][j] = state[i][j];
		  }
	  }
	  
	  for(int i = temp.length - 1; i >= 0; i--) {
		  if(temp[i][action] == 0) {
			  temp[i][action] = player;
	          break;
		  }
	  }
	  return temp;
  } //end result

} //end class
