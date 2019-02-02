public interface formalization{

  public boolean currentPlayer();

  public boolean[] actions(int[][] matrix);

  public void result();

  public int terminalTest(int [][] matrix, int len);

  public int utility();

}
