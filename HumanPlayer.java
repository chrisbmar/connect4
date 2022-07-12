import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
  private BufferedReader input;

  public HumanPlayer(String name, char marker) {
    super(name, marker); // call constructor of Player class
    input = new BufferedReader(new InputStreamReader(System.in));
  }

  /**
   * Method that must be implemented to adhere to the interface PlayerControls. It
   * gets a valid input from a user
   *
   * @returns {int}
   */
  public int move() {
    int move = 0;
    boolean isValidInput = false;

    // loop until the input is a valid number
    do {
      try {
        String userInput = getUserInput();
        move = Integer.parseInt(userInput);
        isValidInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Please enter only a number");
      }
    } while (!isValidInput);

    isValidInput = false;

    return move - 1;
  }

  /**
   * Method that gets an input from a user
   *
   * @returns {String}
   */
  private String getUserInput() {
    String toReturn = null;

    do {
      try {
        toReturn = input.readLine();
      } catch (Exception e) {
        System.out.println("Error with input. Please try again.");
      }
    } while (toReturn == null);

    return toReturn;
  }
}