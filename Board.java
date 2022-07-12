public class Board {
  private char[][] board;
  private boolean hasWon = false;
  private int winningAmount;

  public Board(int rows, int cols, int winningAmount) {
    board = new char[rows][cols];
    this.winningAmount = winningAmount;
  }

  /**
   * Method that returns the current board state
   *
   * @returns {String}
   */
  public String getBoardState() {
    String s = "";

    for (int i = 0; i <= board.length - 1; i++) {
      for (int j = 0; j <= board[i].length - 1; j++) {
        if (board[i][j] == 'r') {
          s += "| r ";
        } else if (board[i][j] == 'y') {
          s += "| y ";
        } else {
          s += "|   ";
        }
      }
      s += "|\n";
    }

    // column numbers
    for (int i = 0; i < board[0].length; i++) {
      s += "  " + (i + 1) + " ";
    }

    s += "\n";

    return s;
  }

  private void checkHorizontalWinner(char marker) {
    int count = 0;
    // check horizontal
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == marker) {
          count = count + 1;
          if (count >= winningAmount) {
            hasWon = true;
          }
        } else {
          count = 0;
        }
      }
    }
  }

  private void checkVerticalWinner(char marker) {
    int count = 0;
    // check vertical
    for (int i = 0; i < board[0].length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[j][i] == marker) {
          count = count + 1;
          if (count >= winningAmount) {
            hasWon = true;
          }
        } else {
          count = 0;
        }
      }
    }
  }

  private void checkDiagonalLeftWinner(char marker) {
    int count = 0;
    // check diagonal right to left
    for (int i = 0; i < board.length - 3; i++) { // row stars from 0
      for (int j = board[0].length - 1; j > 2; j--) { // cols start from 7
        for (int k = 0; k < winningAmount; k++) {
          if (board[i + k][j - k] == marker) {
            count = count + 1;
            if (count >= winningAmount) {
              hasWon = true;
            }
          } else {
            count = 0;
          }
        }
      }
    }
  };

  private void checkDiagonalRightWinner(char marker) {
    int count = 0;
    // check diagonal left to right
    for (int i = 0; i < board.length - 3; i++) {
      for (int j = 0; j < board[0].length - 3; j++) {
        for (int k = 0; k < winningAmount; k++) {
          if (board[i + k][j + k] == marker) {
            count = count + 1;
            if (count >= winningAmount) {
              hasWon = true;
            }
          } else {
            count = 0;
          }
        }
      }
    }
  }

  /**
   * Method that checks whether the current player has won
   *
   * @returns void
   */
  private void checkWinner(char marker) {
    checkHorizontalWinner(marker);
    checkVerticalWinner(marker);
    checkDiagonalRightWinner(marker);
    checkDiagonalLeftWinner(marker);
  }

  /**
   * Method that updates the board to reflect a counter being put in a specific
   * position
   *
   * @returns void
   */
  public void placeCounter(char marker, int position) {
    boolean placed = false;

    for (int i = board.length - 1; i >= 0; i--) {
      if (!placed) {
        if (board[i][position] != 'y' && board[i][position] != 'r') { // adjust this for blue player as well
          board[i][position] = marker;
          placed = true;
        }
      }
    }

    checkWinner(marker);
  }

  /**
   * Method that checks whether a column is valid I.e. whether a marker can be
   * placed in that column
   *
   * @returns {boolean}
   */
  public boolean isValidColumn(int position) {
    boolean isFull = true;
    boolean isWithinRange = isWithinBounds(position);

    if (isWithinRange) {
      for (int i = board.length - 1; i >= 0; i--) {
        if (board[i][position] != 'y' && board[i][position] != 'r') {
          isFull = false;
        }
      }
    }

    return !isFull && isWithinRange;
  }

  /**
   * Method that checks whether the user input is within the column max range
   *
   * @returns {boolean}
   */
  public boolean isWithinBounds(int num) {
    return num >= 0 && num < board[0].length;
  }

  /**
   * Method that returns the internal field {hasWon}
   *
   * @returns {boolean}
   */
  public boolean hasWon() {
    return hasWon;
  }
}