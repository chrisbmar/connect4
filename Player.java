abstract class Player implements PlayerControls {
  private String name;
  private char marker;

  public Player(String name, char marker) {
    this.name = name;
    this.marker = marker;
  }

  /**
   * Method that the PlayerControls interface expects
   *
   * @returns {String}
   */
  public String getPlayerName() {
    return name;
  }

  /**
   * Method that the PlayerControls interface expects
   *
   * @returns {String}
   */
  public char getPlayerMarker() {
    return marker;
  }
}