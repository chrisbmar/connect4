public class Game {
  private int rows;
  private int cols;
  private int winningAmount;
  private int count = 0;
  private static String[] welcomeMessages = new String[] { "Welcome to Connect 4", "There are 2 players red and yellow",
      "Player 1 is Red, Player 2 is Yellow",
      "To play the game type in the number of the column you want to drop you counter in",
      "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally", "", };

  public Game(int rows, int cols, int winningAmount) {
    this.rows = rows;
    this.cols = cols;
    this.winningAmount = winningAmount;

    // print out all welcome messages
    for (int i = 0; i < welcomeMessages.length; i++) {
      System.out.println(welcomeMessages[i]);
    }

    playGame();
  }

  private void playGame() {
    Board board = new Board(rows, cols, winningAmount);
    HumanPlayer p1 = new HumanPlayer("Player 1", 'r');
    ComputerPlayer p2 = new ComputerPlayer("Computer 1", 'y', rows);

    // print initial board state
    System.out.print(board.getBoardState());

    // player who will play first
    Player currentPlayer = p1;

    // loop until there is a winner
    while (!board.hasWon()) {
      count++;

      // check if the board is full
      if (count == 42) {
        System.out.print("Board full.");
        return;
      }
      
      int move;

      // loop until the input is valid
      do {
        move = currentPlayer.move();

        // only show error messages if it's a human error
        if (!board.isValidColumn(move) && currentPlayer.equals(p1)) {
          if (!board.isWithinBounds(move)) {
            System.out.println("Column out of bounds! Please enter another column.");
          } else {
            System.out.println("Column full! Please enter another column");
          }
        }
      } while (!board.isValidColumn(move));

      // place counter
      board.placeCounter(currentPlayer.getPlayerMarker(), move);

      // print message with player name and chosen column
      System.out.println(currentPlayer.getPlayerName() + " chose column " + (move + 1));
      // print current board state
      System.out.print(board.getBoardState());
      // check if the current player has won
      if (board.hasWon()) {
        System.out.println(currentPlayer.getPlayerName() + " wins!");
      }

      // change to the next player
      if (currentPlayer.equals(p1)) {
        currentPlayer = p2;
      } else {
        currentPlayer = p1;
      } 
    }
  }
}
