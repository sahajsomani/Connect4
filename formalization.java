package connect4;

public interface formalization{

		//no abstraction of states as the states are implemented directly in the game class
	  public boolean[] actions(int[][] matrix); //the actions 

	  public int[][] result(int[][] state, int action, int player);//the result

	  public int terminalTest(int [][] matrix); //this is the utility function

}
