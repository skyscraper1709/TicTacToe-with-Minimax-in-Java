/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeminimax;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Safat
 */
public class TicTacToeMinimax {

    char board[][] = new char[3][3];
    char currentPlayer, win;
    static Scanner sc;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TicTacToeMinimax t = new TicTacToeMinimax();
        System.out.println("Choose options play urself(1), with computer(2) or design board(3)");
       sc = new Scanner(System.in);
        int usrChoice = sc.nextInt();
        for (char[] temp : t.board) {
            Arrays.fill(temp, '.');
        }
        switch (usrChoice) {
            case 1: ;
                t.currentPlayer = 'X';
                t.play();
                break;
            case 2: ;
                t.currentPlayer = 'O';
                t.play();
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        t.board[i][j] = sc.next().charAt(0);
                        //works fine if the next is my turn
                    }
                }
                System.out.println("Board built! Showing board now...\n \n You gave");
//                t.showBoard();
                t.play();

        }
       t.showBoard();
    }
    public void play() {  
//    char currentplayer = 'O';  
    for (int move = 0; move < 9; move++) {  
      if (gameOver()) {  
        return;  
      }  
      if (currentPlayer == 'O') {  
        playBestMove();  
        currentPlayer = 'X';  
      } else {  
        showBoard(); 
        System.out.print("Enter row: ");  
        int row = sc.nextInt();  
        System.out.print("Enter column: ");  
        int column = sc.nextInt();  
        board[row][column] = 'X';  
        currentPlayer = 'O';  
      }  
    }  
  }  
  
  /** Find the best move for X and play it on the board. */  
  protected void playBestMove() {  
    int score;  
    int bestScore = -10;  
    int bestRow = -1;  
    int bestColumn = -1;  
//    testing evry open space for best score
    for (int row = 0; row < 3; row++) {  
      for (int column = 0; column < 3; column++) {  
        if (board[row][column] == '.') {  
          board[row][column] = 'O';  
          score = minimaxPlayer();  
          System.out.print("showing board for a temporary move\n");
                  showBoard();
                  System.out.print("and the score is: "+score+"\n");
          if (score > bestScore) {  
            bestScore = score;  
            bestRow = row;  
            bestColumn = column;  
          }  
          board[row][column] = '.';  
        }  
      }  
    }  
    board[bestRow][bestColumn] = 'O';  
  }  
    void showBoard() {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]
                + "\n" + board[1][0] + "|" + board[1][1] + "|" + board[1][2]
                + "\n" + board[2][0] + "|" + board[2][1] + "|" + board[2][2]
        );
    }

    

    int minimaxPlayer() {
        int score = score();
        if (gameOver()) {
            return score;
        }

        int bestScore = -10;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == '.') {
                    board[row][column] = 'X';
                    score = minimaxAI();
                    if (score > bestScore) {
                        bestScore = score;
                    }
                    board[row][column] = '.';
                }
            }
        }
        return bestScore;
    }

    int minimaxAI() {
        int score = score();
        if (gameOver()) {
            return score;
        }

        int bestScore = 10;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == '.') {
                    board[row][column] = 'O';
                    score = minimaxPlayer();
                    if (score < bestScore) {
                        bestScore = score;
                    }
                    board[row][column] = '.';
                }
            }
        }
        return bestScore;
    }

    boolean gameOver() {
        if (score() != 0) {
            return true;
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public int score() {  
    int lineScore;  
    for (int i = 0; i < 3; i++) {  
      lineScore = scoreLine(board[i][0],  
                           board[i][1],  
                           board[i][2]);  
      if (lineScore != 0) {  
        return lineScore;  
      }  
      lineScore = scoreLine(board[0][i],  
                           board[1][i],  
                           board[2][i]);  
      if (lineScore != 0) {  
        return lineScore;  
      }  
    }  
    lineScore = scoreLine(board[0][0],  
                         board[1][1],  
                         board[2][2]);  
    if (lineScore != 0) {  
      return lineScore;  
    }  
    return scoreLine(board[0][2], board[1][1], board[2][0]);  
  }  
  
  /** 
   * Return 1 if all three characters are 'X', -1 if they are all 'O', 
   * and 0 otherwise. 
   */  
  protected int scoreLine(char a, char b, char c) {  
    if ((a == 'X') && (b == 'X') && (c == 'X')) { return -1; }  
    if ((a == 'O') && (b == 'O') && (c == 'O')) { return 1; }  
    return 0;  
  }  
}
