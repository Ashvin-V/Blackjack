import java.util.Scanner;

/**
* The main Game class made for the classic game of Blackjack. Uses Standard Rules.
*
* @author Ashvin Vaseeaharan
* @since 2019-06-10
* @version 1.0
*/
public class Game
{

  /**
  * The main method used to run the game.
  */
  public static void main(String[] args)
  {
    System.out.println("");
    System.out.println("");

    // Variables and Objects needed throughout the game.
    String input;
    double bet = 0;
    Scanner in = new Scanner(System.in);
    Deck deck = new Deck();
    deck.shuffle();
    deck.shuffle();
    deck.shuffle();
    Player dealer = new Player(deck);

    // Introduce User
    System.out.println("_________________________");
    System.out.println("Welcome to Blackjack! ");
    System.out.println("_________________________");
    System.out.println("Standard Rules of Blackjack Applies");
    System.out.println("_________________________");

    // Collect name and create player object
    System.out.print("Please enter your name: ");
    input = in.nextLine();
    Player p1 = new Player(input, deck);

    // Keep on playing the game until player runs out of money.
    while (p1.getMoney()>0)
    {
      // Display balance and get bet from user.
      System.out.println("_________________________");
      try
      {
        System.out.println(p1.getName()+ ", your balance is: "+ p1.getMoney());
        System.out.print(p1.getName()+ ", please enter your bet (0 or negative to exit): ");
        bet = in.nextDouble();
        in.nextLine();
      }
      catch (Exception e)
      {
        System.out.println("Please enter a valid number. Eg: 12.50 ");
        in.next();
        continue;
      }

      // Checks if user enters bet greater than balance
      if (bet > p1.getMoney())
      {
        System.out.println("You entered value greater than balance, try again. ");
        continue;
      }
      // Exit the game if user enters 0 or negative.
      else if (bet <= 0)
      {
        break;
      }
      p1.setBet(bet);

      // Dealer gets delt a card and that card is shown to user.
      System.out.println("");
      dealer.draw(deck.deal());
      System.out.println("Dealers Hand: ");
      System.out.println(dealer.getHand());

      // Player gets delt 2 cards.
      p1.draw(deck.deal());
      p1.draw(deck.deal());
      System.out.println(p1.getName()+"s Hand: ");
      System.out.println(p1.getHand());

      // Asks player if they wish to hit or stand
      System.out.println("");
      System.out.print("Would you like to hit or stand? ");
      input = in.nextLine();
      while (!input.toLowerCase().equals("hit") && !input.toLowerCase().equals("stand"))
      {
        System.out.println("Please enter either 'hit' or 'stand'! ");
        System.out.print("Would you like to hit or stand? ");
        input = in.nextLine();
      }

      // If hit, deal card and ask again.
      while (input.toLowerCase().equals("hit"))
      {
        p1.draw(deck.deal());
        System.out.println("");
        System.out.println("Dealers Hand: ");
        System.out.println(dealer.getHand());
        System.out.println(p1.getName()+"s Hand: ");
        System.out.println(p1.getHand());
        // If player busts, ends the round.
        if (p1.getSum() > 21)
        {
          break;
        }
        System.out.println("");
        System.out.print("Would you like to hit or stand? ");
        input = in.nextLine();
        while (!input.toLowerCase().equals("hit") && !input.toLowerCase().equals("stand"))
        {
          System.out.println("Please enter either 'hit' or 'stand'! ");
          System.out.print("Would you like to hit or stand? ");
          input = in.nextLine();
        }
      }

      // If player has sum greater than 21, looses bet and returns cards to deck.
      if (p1.getSum() > 21)
      {
        System.out.println("You BUSTED! ");
        p1.changeMoney(p1.getBet()*-1);
        p1.returnCardsToDeck();
        dealer.returnCardsToDeck();
        deck.shuffle();
        // Start again from asking for a bet.
        continue;
      }

      // Dealer continues to hit until greater than player if player is not over 21.
      while(dealer.getSum() < p1.getSum())
      {
        System.out.println("");
        dealer.draw(deck.deal());
        System.out.println("Dealers Hand: ");
        System.out.println(dealer.getHand());
        System.out.println(p1.getName()+"s Hand: ");
        System.out.println(p1.getHand());
      }

      // If sums equal, money is returned to user.
      if (p1.getSum() == dealer.getSum())
      {
        System.out.println("TIE! ");
        p1.returnCardsToDeck();
      }
      // If Blackjack, user gets paid 3:2 of original bet.
      else if (p1.getSum()==21)
      {
        System.out.println("Blackjack! (3:2 paid)");
        p1.changeMoney(p1.getBet()*1.5);
      }
      // Dealer goes over 21, user gets the value of his bet.
      else if (dealer.getSum() > 21)
      {
        System.out.println("Dealer BUSTED! ");
        p1.changeMoney(p1.getBet());
      }
      // Dealer is closer to 21, user looses his bet.
      else if (dealer.getSum()>p1.getSum())
      {
        System.out.println("You Lost! ");
        p1.changeMoney(p1.getBet()*-1);
      }
      // Player is closer to 21, user wins his bet.
      else
      {
        System.out.println("You Win! ");
        p1.changeMoney(p1.getBet());
      }

      // Return all cards to deck and shuffles the deck again.
      p1.returnCardsToDeck();
      dealer.returnCardsToDeck();
      deck.shuffle();
    }

    // Tells user his final balance and signals end of game.
    System.out.println("_________________________");
    System.out.println(p1.getName()+", has left the table with $"+p1.getMoney());
    System.out.println("_________________________");
    System.out.println("Done! ");
    System.out.println("_________________________");
    System.out.println("");
    System.out.println("");
  }

}
