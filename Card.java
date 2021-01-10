/**
* The Card class made for the classic game of Blackjack. Uses Standard Rules.
*
* @author Ashvin Vaseeaharan
* @since 2019-06-10
* @version 1.0
*/
public class Card
{
  // Instance Variables
  private int value;
  private int suit;
  private static final String[] VALUES = {"0", "0", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};
  private static final char[] SUITS = {'x', '\u2663', '\u2666', '\u2665', '\u2660'};

  /**
  * Default Constructor for a card.
  */
  public Card()
  {
    value = 0;
    suit = 0;
  }

  /**
  * Main Constructor used to create a card object.
  *
  * @param value The integer/face value of the card.
  * @param suit The suit of the card.
  */
  public Card(int value, int suit)
  {
    this.value = value;
    this.suit = suit;
  }

  /**
  * Method to get the value of the card as an integer
  *
  * @return An integer representing the value of the card.
  */
  public int getIntValue()
  {
    if (value <= 11)
    {
      return value;
    }
    else
    {
      return 10;
    }
  }

  /**
  * Method to get the value of the card as a String
  *
  * @return A String representing the value of the card.
  */
  public String getStringValue()
  {
    return VALUES[value];
  }

  /**
  * Method to get the suit of the card.
  *
  * @return A Character representing the suit
  */
  public char getSuit()
  {
    return SUITS[suit];
  }

  /**
  * A method to get a string representation of the card object.
  *
  * @return A String representing the card object.
  */
  public String toString()
  {
    String output = "";
    output += VALUES[value];
    output += SUITS[suit];
    output += "; ";
    return output;
  }

}
