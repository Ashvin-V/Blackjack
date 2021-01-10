import java.util.ArrayList;

/**
* The Player class made for the classic game of Blackjack. Uses Standard Rules.
*
* @author Ashvin Vaseeaharan
* @since 2019-06-10
* @version 1.0
*/
public class Player
{
  // Instance Variables
  private ArrayList<Card> hand = new ArrayList<Card>();
  private String name;
  private Deck deck;
  private double money;
  private double bet;
  private int numAces;

  /**
  *  Default constructor used to create a player object for the game.
  */
  public Player()
  {
    name = "Unnamed Player";
    deck = new Deck();
    money = 0;
    bet = 0;
    numAces = 0;
  }

  /**
  * Main constructor used to create a dealer player for the game.
  *
  * @param deck The deck object going to be used for the game.
  */
  public Player(Deck deck)
  {
    this.name = "Dealer";
    this.deck = deck;
    money = 1000000;
    bet = 0;
    numAces = 0;
  }

  /**
  *  Main contructor used to create a player for the game.
  *
  * @param name A String representing the name of the player.
  * @param deck the deck object going to be used for the game.
  */
  public Player(String name, Deck deck)
  {
    this.name = name;
    this.deck = deck;
    money = 100;
    bet = 0;
    numAces = 0;
  }

  /**
  *  Method used to return all the cards a player has in hand back to the deck.
  */
  public void returnCardsToDeck()
  {
    for (Card c: hand)
    {
      // Decrement Aces counter
      if (c.getStringValue().equals("A"))
      {
        numAces--;
      }
      // Places a pointer to card object in the deck.
      deck.collectCard(c);
    }
    int cardsInHand = hand.size();
    for (int i = 0; i < cardsInHand ;i++)
    {
      // Removes the pointer to card object in the Hand ArrayList
      hand.remove(0);
    }
  }

  /**
  * Method used to set the bet for the round.
  *
  * @param bet a double representing the bet amount.
  */
  public void setBet(double bet)
  {
    this.bet = bet;
  }

  /**
  * Method used to get the bet a player made.
  *
  * @return the double representing the bet amount.
  */
  public double getBet()
  {
    return bet;
  }

  /**
  * Method used to change the amount of money a player has.
  *
  * @param money A double representing how much to change the balance by.
  */
  public void changeMoney(double money)
  {
    this.money += money;
  }

  /**
  * Method used to get the balance of the player.
  *
  * @return A double value representing the balance of the player.
  */
  public double getMoney()
  {
    return money;
  }

  /**
  * Method used to add up the values of the cards the player has in hand.
  *
  * @return An integer representing the sum of the cards in hand.
  */
  public int getSum()
  {
    int sum = 0;
    int aces = numAces;

    // Accumalates the Int Values of the cards.
    for (Card c: hand)
    {
      sum += c.getIntValue();
    }

    // Revalue the ace cards if any if the sum is greater than 21.
    while (sum > 21 && aces > 0)
    {
      sum -= 10;
      aces--;
    }

    return sum;
  }

  /**
  * Method used to add a card to the players hand.
  *
  * @param c The card object to add to the hand.
  */
  public void draw(Card c)
  {
    // Keeps track if an Ace has been added to the hand.
    if (c.getStringValue().equals("A"))
    {
      numAces++;
    }
    hand.add(c);
  }

  /**
  * Method used to get the name of the player.
  *
  * @return A String representing the name of the player.
  */
  public String getName()
  {
    return name;
  }

  /**
  * Method used to display the hand and its sum to the user.
  *
  * @return A String representing the hands content and the sum of cards.
  */
  public String getHand()
  {
    String output = "";
    for (Card c : hand)
    {
      output += c.toString();
    }
    output += "       (sum:"+getSum()+")";
    return output;
  }

  /**
  * Method used to get a String representing the Player.
  *
  * @return A String representation of the player object.
  */
  public String toString()
  {
    String output = "";
    output += "Name: " + name;
    output += ", Hand: " + getHand();
    output += ", Sum: " + getSum();
    output += ", Money: "+ getMoney();
    return output;
  }

}
