import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

enum SuitEnum
{
	Spade ("S"),
	Heart ("H"),
	Diamond ("D"),
	Club ("C");
	
	private String name;
	
	SuitEnum(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
}

enum RankEnum
{
	Two ("2"), 
	Three ("3"), 
	Four ("4"), 
	Five ("5"), 
	Six ("6"), 
	Seven ("7"), 
	Eight ("8"), 
	Nine ("9"), 
	Ten ("T"),
	Jack ("J"), 
	Queen ("Q"), 
	King ("K"), 
	Ace ("A");
	
	private String rank;
	
	RankEnum(String rank)
	{
		this.rank = rank;
	}
	
	public String getRank()
	{
		return rank;
	}

}

class PlayingCard
{
	// Definitions
	// enums from SuitEnum and RankEnum classes
	private SuitEnum suit;
	private RankEnum rank;
	
	// Constructors
	public PlayingCard(SuitEnum suit, RankEnum rank)
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	public PlayingCard(PlayingCard pc)
	{
		this(pc.suit, pc.rank);
	}
	
	// Accessors
	public SuitEnum getSuit()
	{
		return suit;
	}
	
	public RankEnum getRank()
	{
		return rank;
	}
	
	// Mutators
	public void setCard(SuitEnum suit, RankEnum rank)
	{
		this.suit = suit;
		this.rank = rank;
	}
	
	// toString method
	// in order to obtain parameters from the enum classes
	public String toString()
	{
		return String.format ("%s%s", suit.getName(), rank.getRank());
	}
	
}

class CardDisplay
{
	// Definitions
	private final int MAXC = 13;		// Max cards per suit
	private final int MAXD = 52;		// Cards per deck
	private final String LINE = "------------------------------------" +
								"--------------";
	
	// Print deck for PlayingCard ArrayList
	private void printDeck(ArrayList <PlayingCard> values)
	{
		System.out.println("Printing from ArrayList\n");
		
		int k = 0;
		
		for (PlayingCard p : values)
		{
			System.out.printf("%-4s", p);
			k++;
			
			if (k == MAXC)
			{
				System.out.println();
				k = 0;
			}
		}
		System.out.println(LINE + "\n");
		
	}
	
	// Overload of print deck, for PlayingCard array
	private void printDeck(PlayingCard [] valuesArray)
	{
		System.out.println("Printing from Array\n");
		
		int k = 0;
		
		for (PlayingCard p : valuesArray)
		{
			System.out.printf("%-4s", p);
			k++;
			
			if (k == MAXC)
			{
				System.out.println();
				k = 0;
			}
		}
		System.out.println(LINE + "\n");

	}
	
	// Copy PlayingCard ArrayList to PlayingCard array
	private void listToArray(ArrayList <PlayingCard> values, 
									PlayingCard [] valuesArray)
	{
		int i = 0;
		
		for (PlayingCard p : values)
		{
			valuesArray[i] = p;
			i++;
		}
		printDeck(valuesArray);	
		shuffle(valuesArray);
		
	}
	
	// Creation of deck of cards into PlayingCard ArrayList
	// To start the rest of the methods
	private void deckOfCards(ArrayList <PlayingCard> values)
	{
		PlayingCard [] valuesArray = new PlayingCard[MAXD];
		
		for (SuitEnum s : SuitEnum.values())
		{
			for (RankEnum r : RankEnum.values())
			{
				values.add(new PlayingCard(s, r));
			}
		}
		printDeck(values);
		listToArray(values, valuesArray);
		
	}
	
	// Shuffle of cards in PlayingCard array
	private void shuffle(PlayingCard [] valuesArray)
	{
		// Definitions
		String [] strArray = new String [MAXD];		// New String array to store copy after shuffle
		PlayingCard [] temp = new PlayingCard[1];
		Random rand = new Random();
		
		// To shuffle deck 10 to 30 times
		int k = rand.nextInt(21)+10;
		
		while (k > 0)
		{
			int i = rand.nextInt(MAXD);
			int j = rand.nextInt(MAXD);
			
			temp [0] =  valuesArray[i];
			valuesArray[i] = valuesArray[j];
			valuesArray[j] = temp [0];
			
			k--;
		}
		// Printing of deck after shuffle
		System.out.println("Shuffle the cards - Array version");
		printDeck(valuesArray);
		
		// Copy of deck to String array
		transfer(valuesArray, strArray);
		
		// Sort method from Arrays class
		Arrays.sort(strArray);
		
		// Swapping of rows
		swap(strArray, 0, 39);
		swap(strArray, 13, 26);
		// Display String array of cards
		displayString(strArray);
		
	}
	
	// Copy of PlayingCard array into String array
	private void transfer(PlayingCard [] valuesArray, String [] strArray)
	{
		int i = 0;
		String s;
		
		for (PlayingCard p  : valuesArray)
		{
			s = p.toString();
			strArray [i] = s;
			i++;
		}
		
	}
	
	// Rearranging of String array to be in order
	// Integer i and j being first array in chosen rows
	private void swap(String [] str, int i, int j)
	{
		// Definitions
		String temp;
		int swapMAXC = i + MAXC;
		int k = i + 12;
		int l = i + 8;
		int o = j + 12;
		int p = j + 8;
		int m = i + 10;
		int n = i + 11;
		int y = j + 10;
		int u = j + 11;
		
		// Swapping of rows
		while (i < swapMAXC)
		{
			temp = str[i];
			str[i] = str[j];
			str[j] = temp;
			i++;
			j++;
		}
		
		// Swapping of Jack and Ace
		temp = str[k];
		str[k] = str[l];
		str[l] = temp;
		
		temp = str[o];
		str[o] = str[p];
		str[p] = temp;
		
		// Swapping of Queen and King
		temp = str[m];
		str[m] = str[n];
		str[n] = temp;
		
		temp = str[y];
		str[y] = str[u];
		str[u] = temp;
		
	}
	
	// Printing of String array after the swaps
	private void displayString(String [] strArray)
	{
		System.out.println("Re-arrange the cards\n");
		
		int k = 0;
		
		for (String s : strArray)
		{
			System.out.printf("%-4s", s);
			k++;
			
			if (k == MAXC)
			{
				System.out.println();
				k = 0;
			}
		}
		System.out.println(LINE + "\n");
		
	}
	
	public static void main (String [] args)
	{
		CardDisplay b = new CardDisplay();
		
		ArrayList <PlayingCard> values = new ArrayList <PlayingCard> ();
		
		b.deckOfCards(values);
		
	}
	
}
 
