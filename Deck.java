import java.util.ArrayList;
import java.util.Random;

/**
* The Deck class made for the classic game of Blackjack. Uses Standard Rules.
*
* @author Ashvin Vaseeaharan
* @since 2019-06-10
* @version 1.0
*/
public class Deck
{
  // Instance Variables
  private ArrayList<Card> deck = new ArrayList<Card>();

  /**
  * Default Constructor used to create a standard deck object with 52 cards.
  */
  public Deck()
  {
    for (int i = 1; i < 5; i++)
    {
      for (int j = 2; j < 15; j++)
      {
        deck.add(new Card(j, i));
      }
    }
  }

  /**
  * Method used to put a card back into the deck.
  *
  * @param c The card to be placed back into the deck.
  */
  public void collectCard(Card c)
  {
    deck.add(c);
  }

  /**
  * Method used to obtain a card from the deck.
  *
  * @return The first card in the deck.
  */
  public Card deal()
  {
    Card tempCard = deck.get(0);
    deck.remove(tempCard);
    return tempCard;
  }

  /**
  * Method used to get a String representing the deck.
  *
  * @return a String representation of the deck object.
  */
  public String toString()
  {
    String output = "";
    for (Card c: deck)
    {
      output += c.toString();
    }
    return output;
  }

  /**
  * Method used to shuffle/ randomly position the cards in the deck.
  */
  public void shuffle()
  {
    Random rand = new Random();
    Card tempCard;
    int randPosition;
    for (int i = 0; i < deck.size(); i++)
    {
      randPosition = rand.nextInt(deck.size());
      tempCard = deck.get(i);
      deck.set(i, deck.get(randPosition));
      deck.set(randPosition, tempCard);
    }
  }

}
