import java.util.*;

public class Game implements formalization  {
  private int[][] matrix;
  private boolean player;
  private int len;
  public static void main(String[] args)  {
    int[][] test = {{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
    printMatrix(test);

  } //end main

  public Game(int input){
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
    }
    matrix = new int[a][b];
    for(int i = 0; i < a; i++) {
      for(int j = 0; j < b; j++) {
        matrix[a][b] = 0;
      }
    }
  } //end constructor

  public int[][] getMatrix() {
    return this.matrix;
  } //end getMatrix

  public void setMatrix(int[][] matrix) {
    this.matrix = matrix;
  } //end setMatrix

  public boolean currentPlayer() {
    return this.player;
  }

  public boolean[] actions() {
    int width = this.matrix[0].length;
    boolean[] list = new boolean[width];
    for(int i = 0; i < width; i++) {
      if(this.matrix[0][i] == 0) {
        list[i] = true;
      } else {
        list[i] = false;
      }
    }

    return list;
  } //end actions

  public void result() {
    printMatrix(this.matrix);

  } //end result

  public int terminalTest() {
    int width = matrix[0].length;
    int height = matrix.length;
    boolean full = true;
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for(int i = 0; i < width; i++) {
      if(matrix[0][i] == 0) {
        full = false;
      }
    }

    for(int i = 0; i <= height - len; i++) {
      for(int j = 0; j <= width - len; j++) {
        for(int k = 0; k < len; k++) {
          temp.add(matrix[i + k][j]);
        }
        if(termianlHelp(temp)) {
          i = height - len;
          j = width - len;
          break;
        }
        temp = new ArrayList<Integer>();

        for(int k = 0; k < len; k++) {
          temp.add(matrix[i][j + k]);
        }
        if(termianlHelp(temp)) {
          i = height - len;
          j = width - len;
          break;
        }
        temp = new ArrayList<Integer>();

        for(int k = 0; k < len; k++) {
          temp.add(matrix[i + k][j + k]);
        }
        if(termianlHelp(temp)) {
          i = height - len + 1;
          j = width - len + 1;
          break;
        }
        temp = new ArrayList<Integer>();
      }
    }

    for(int i = 0; i <= height - len; i++) {
      for(int j = width - 1; j >= len; j--) {
        for(int k = 0; k < len; k++) {
          temp.add(matrix[i + k][j + k]);
        }
        if(termianlHelp(temp)) {
          i = height - len + 1;
          j = 0;
          break;
        }
        temp = new ArrayList<Integer>();
      }
    }
    //for(int i = 0; i < width)


    return 0;
  }

  private boolean terminalHelp(ArrayList<Integer> array) {
    for(int i = 0; i < array.size() - 1; i++) {
      if(array.get(i) != array.get(i + 1)) {
        return false;
      }
    }
    return true;
  }

  public int utility() {
    return 0;
  }

  public static void printMatrix(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    // prints the matrix using the 2d array
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
        if(matrix[i][j] == 0) {
          System.out.print("  ");
        } else {
          System.out.print(Integer.toString(matrix[i][j]) + " ");
        }

      } //end j for loop
      System.out.println();
    } //end i for loop
  } //end printMatrix

  public void takeInput(boolean[] validActions) {
    Scanner scan = new Scanner(System.in);
    boolean check = true;
    while(check) {
      System.out.println("User input: ");
      int input = scan.nextInt();
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
  } //end takeInput

  public boolean validateCompInput(int compInput, boolean[] validActions) {
    if(compInput >= validActions.length) {
      return false;
    }
    else if(validActions[input] != true) {
      return false;
    }
    return true;
  } //end validateCompInput

} //end class
