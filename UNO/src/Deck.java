import java.util.ArrayList;
import java.util.List;

public class Deck {

	List<Card> deck = new ArrayList<Card>();
	
	public Deck()
	{
		makeDeck();
		shuffle();
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public int getDeckSize()
	{
		return deck.size();
	}
	
	public void makeDeck()
	{
		String[] colors = {"Yellow", "Blue", "Green", "Red"};
		for(int j = 0; j < 4; j++)
		{
			for(int i = 0; i < 13; i++)
			{
				for(int k = 0; k < 2; k++)
				{
					if(i == 10)
						deck.add(new Card("Skip", colors[j]));
					else if(i == 11)
						deck.add(new Card("Reverse", colors[j]));
					else if(i == 12)
						deck.add(new Card("Draw Two", colors[j]));
					else
						deck.add(new Card("" + i, colors[j]));
				}
			}
		}
		for(int i = 0; i < 4; i++)
		{
			deck.add(new Card("Wild", ""));
			deck.add(new Card("Wild: Draw Four", ""));
		}
	}
	
	public void shuffle()
	{
		List<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < deck.size(); i++)
		{
			int rand = (int)(Math.random()*deck.size());
			
			while(deck.get(rand) == null)
			{
				rand = (int)(Math.random()*deck.size());
			}
			
			temp.add(deck.get(rand));
			deck.set(rand, null);
		}
		
		deck = temp;
	}
	
	public void removeTopCard()
	{
		deck.remove(deck.size() - 1);
	}
	
	public Card getTopCard()
	{
		return deck.get(deck.size() - 1);
	}
}
