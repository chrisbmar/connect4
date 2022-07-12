import java.lang.Math;

public class ComputerPlayer extends Player {
  private int maxRange;

  public ComputerPlayer(String name, char marker, int maxRange) {
    super(name, marker);
    this.maxRange = maxRange;
  }

  /**
   * Method that must be implemented to adhere to the interface PlayerControls. It
   * returns a random number within the accepted range
   *
   * @returns {int}
   */
  public int move() {
    return (int) ((Math.random() * (maxRange - 1)) + 1) - 1;
  }
}