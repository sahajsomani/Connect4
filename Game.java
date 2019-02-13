package connect4;

import java.util.*;

public class Game {
  private int[][] matrix;
  private int player;
  private int len;
  public static void main(String[] args)  {
	    int[] inputs = introduction(); // user inputs
		  Game test = new Game(inputs[0]);
	    classBot bot = new heuristic(test);
	    if(inputs[1] == 1) {//choosing the bot
	      bot = new randomPlayer(test);
	    } else if(inputs[1] == 2) {
	      bot = new minimax(test);
	    } else if(inputs[1] == 3) {
	      bot = new abpruning(test);
	    }
	    System.out.println("\n\n");
	    if(inputs[2] == 1) {
	    	test.printMatrix(test.getMatrix());
	    }
		  while(true) {//where the actual game takes place
			  if(inputs[2] == 1) {
				  System.out.println("Your move.");
				  test.setMatrix(test.result(test.getMatrix(), test.takeInput(test.actions(test.getMatrix())), test.getPlayer()));
				  test.printMatrix(test.getMatrix());
				  if(test.terminalTest(test.getMatrix()) == test.getPlayer()) {
					  System.out.println("Congratulations! You won the game.");
					  break;
				  }
				  test.turn();
			  }


			  System.out.println("\n\n");
			  long start = new Date().getTime();
			  int num = bot.play();
			  long end = new Date().getTime() - start;
			  System.out.print("Bot played " + num + ".");
			  System.out.println(" States visited " + bot.getCounter() + ". Time elapsed: " + end + " miliseconds.");
			  test.setMatrix(test.result(test.getMatrix(), num, test.getPlayer()));
			  test.printMatrix(test.getMatrix());
			  if(test.terminalTest(test.getMatrix()) == 1 || test.terminalTest(test.getMatrix()) == 2) {
				  System.out.println("You lost!");
				  break;
			  }
			  test.turn();
			  System.out.println("\n\n");
			  
			  if(inputs[2] == 2) {
				  System.out.println("Your move.");
				  test.setMatrix(test.result(test.getMatrix(), test.takeInput(test.actions(test.getMatrix())), test.getPlayer()));
				  test.printMatrix(test.getMatrix());
				  if(test.terminalTest(test.getMatrix()) == test.getPlayer()) {
					  System.out.println("Congratulations! You won the game.");
					  break;
				  }
				  test.turn();
			  }
		  }
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

  public int getLen() {//get the number of required stones to win
    return this.len;
  }

  public void setLen(int len) {//setting the length
    this.len = len;
  }

  public int getPlayer() {//get current player
    return this.player;
  }

  public void setPlayer(int player) {//setting the current player
    this.player = player;
  }
  
  public void turn() {//flip the players
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

  public double nonTerminal(int[][] state) { //nonterminal utility function for the heuristic method.
	  //this method scans all rows, columns and diagonals. If it finds that some player is missing one more tile to win the game it gives 
	  //some positive value. if player X has 3 X's and one empty cell, gives a value of 0.6. Summs all these up and gives an average for a state. 
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
  }//non terminal end
  
  private double nonTerminalHelp(ArrayList<Integer> array) {//help method for the nonterminal utility
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
  }//terminal end

  private boolean terminalHelp(ArrayList<Integer> array) {//help method for terminal utility function
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
  
  public void printMatrix(int[][] state) {//print matrix method
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

  public int takeInput(boolean[] validActions) {//taking player input method
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

  public int[][] result(int[][] state, int action, int player) {//result method. Takes matrix, the player and the action and outputs the result
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

  public static int[] introduction() {//intro method. 
	    while(true) {
	      System.out.println("Welcome to Connect 4 by Sahaj and Nikita");
	      System.out.println("Choose your game:");
	      System.out.println("1. Tiny 3x3x3 Connect-Three");
	      System.out.println("2. Wider 3x5x3 Connect-Three");
	      System.out.println("3. Standard 6x7x4 Connect-Four");
	      Scanner scan = new Scanner(System.in);
	      System.out.print("Your choice? ");
	      int gameType = scan.nextInt();

	      System.out.println("");
	      System.out.println("Choose your opponent:");
	      System.out.println("1. An agent that plays randomly");
	      System.out.println("2. An agent that uses MINIMAX");
	      System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
	      System.out.println("4. An agent that uses H-MINIMAX");
	      System.out.print("Your choice? ");
	      int playerType = scan.nextInt();

	      System.out.println("");
	      System.out.println("Do you want to play X(enter 1) or O(enter 2). X goes first.");
	      System.out.print("Your choice? ");
	      int color = scan.nextInt();
	      int[] inputs = {gameType, playerType, color};
	      if(gameType >= 1 && gameType <= 3) {
	        if(playerType >= 1 && playerType <= 4) {
	          if(color >= 1 && color <= 2) {
	            return inputs;
	          }
	        }
	      }
	      System.out.println("There was a problem with your inputs, please try again");
	    }

	  }
  
} //end class
