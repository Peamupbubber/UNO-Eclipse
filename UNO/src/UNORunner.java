import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UNORunner {

	static Deck deck = new Deck();
	static List<Hand> hands = new ArrayList<Hand>();
	static Card topCard = new Card();
	
	static Scanner scan = new Scanner(System.in);
	
	static boolean isGameActive = true;
	
	public static void main(String[] args) 
	{
		StartGame();
	}
	
	//The act of drawing cards
	public static void drawPhase(Hand hand)
	{
		while(!CanPlayACard(hand)  && isGameActive)
		{
			hand.drawCard();
		}
		
		if(isGameActive)
		{
			System.out.println();
			mainPhase(hand);
		}
	}
	
	//The act of playing cards
	public static void mainPhase(Hand hand)
	{
		if(hand.getHand().size() == 0)
		{
			isGameActive = false;
			System.out.println("Player " + hand.getPlayerNum() + " Wins!");
		}
		
		hand.DisplayHand();
		
		while(CanPlayACard(hand))
		{
			System.out.println("\nChoose a card to play. Top card: " + topCard);
			PlaceCard(hand);
			if(hands.indexOf(hand) == hands.size() - 1)
			{
				mainPhase(hands.get(0));
			}
			else
			{
				mainPhase(hands.get(hands.indexOf(hand) + 1));
			}
		}
		
		if(isGameActive)
			drawPhase(hand);
	}
	
	//All the preliminary stuff
	public static void StartGame()
	{
		topCard = deck.getDeck().get(deck.getDeckSize() - 1);
		deck.removeTopCard();
		
		System.out.println("How many players?");
		int numPlayers = scan.nextInt();
		for(int i = 0; i < numPlayers; i++)
		{
			hands.add(new Hand(deck, i));
		}

		mainPhase(hands.get(0));
	}
	
	//Places the card if all conditions are met, accounts for wild cards
	public static void PlaceCard(Hand hand)
	{
		int input = scan.nextInt();
		input--;
		
		//Makes sure it's a valid input
		while(input > hand.getHand().size())
		{
			System.out.println("Enter a valid option");
			input = scan.nextInt();
			input--;
		}
		
		Card choosenCard = hand.getHand().get(input);
		
		//The wild card situation
		if(choosenCard.getColor().equals(""))
		{
			hand.remove(input);
			System.out.println("Enter 1 for Yellow, 2 for Blue, 3 for Green, 4 for Red.");
			input = scan.nextInt();
			
			//Makes sure it's a valid input
			while(input < 1 || input > 4)
			{
				System.out.println("Enter a vaild number");
				input = scan.nextInt();
			}
			input--;
			String[] colors = {"Yellow", "Blue", "Green", "Red"};
			choosenCard.setColor(colors[input]);
			topCard = choosenCard;
			
		}
		//Other situations
		else if(CanPlayCard(choosenCard))
		{
			topCard = choosenCard;
			hand.remove(input);
		}
	}
	
	//Tests is any card in the hand can be played
	public static boolean CanPlayACard(Hand hand)
	{
		for(Card c : hand.getHand())
		{
			if(CanPlayCard(c))
				return true;
		}
		return false;
	}
	
	//Tests if that card can be played
	public static boolean CanPlayCard(Card c)
	{
		return c.getColor().contentEquals(topCard.getColor()) ||
					c.getType().contentEquals(topCard.getType()) ||
					c.getColor().equals("") ||
					topCard.getColor().contentEquals("");
	}
}
