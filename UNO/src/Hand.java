import java.util.ArrayList;
import java.util.List;

public class Hand {

	List<Card> hand = new ArrayList<Card>();
	Deck deck = new Deck();
	int playerNum;

	public Hand(Deck deck, int playerNum)
	{
		this.deck = deck;
		this.playerNum = playerNum + 1;
		fillHand();
	}
	
	public int getPlayerNum() {
		return playerNum;
	}

	public List<Card> getHand() {
		return hand;
	}
	

	public void DisplayHand()
	{
		int i = 1;
		System.out.println("*** Player " + playerNum + " ***");
		for(Card c : hand)
		{
			System.out.println(i + ": " + c);
			i++;
		}
	}
	
	public void fillHand()
	{
		int deckSize = deck.getDeckSize();
		
		for(int i = deckSize - 1; i >= deckSize - 7; i--)
		{
			hand.add(deck.getDeck().get(i));
			deck.removeTopCard();
		}
	}
	
	public void remove(int index)
	{
		hand.remove(index);
	}
	
	public void drawCard()
	{
		hand.add(deck.getTopCard());
		deck.removeTopCard();
	}
}
