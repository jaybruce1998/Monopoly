import java.util.*;
public class Monopoly
{
	private class Square
	{
		private String name, color, owner;
		private int price, house, houses, monop;
		private int[] rent;
		private Square(String name)
		{
			this.name=name;
			owner="";
		}
		private Square(String name, int price)
		{
			this.name=name;
			this.price=price;
			owner="";
		}
		private Square(String name, String color, int price)
		{
			this.name=name;
			this.color=color;
			this.price=price;
		}
		private Square(String name, String color, int price, int monop, int house, int[] rent)
		{
			this.name=name;
			this.color=color;
			this.price=price;
			this.monop=monop;
			this.house=house;
			this.rent=rent;
		}
		public String toString()
		{
			return name;
		}
	}
	private class Card
	{
		String des;
		int num;
		private Card(String des)
		{
			this.des=des;
		}
		private Card(String des, int num)
		{
			this.des=des;
			this.num=num;
		}
		private boolean startsWith(String s)
		{
			return des.startsWith(s);
		}
		private boolean endsWith(String s)
		{
			return des.endsWith(s);
		}
		private boolean includes(String s)
		{
			return des.indexOf(s)>=0;
		}
		public String toString()
		{
			return des;
		}
	}
	private class Player
	{
		private List<Square> prop, mort;
		private String name;
		private boolean human, jail;
		private int loc, money, turns, out;
		private Scanner input;
		private Player(String name, boolean human)
		{
			this.name=name;
			this.human=human;
			prop=new ArrayList<>();
			mort=new ArrayList<>();
			input=new Scanner(System.in);
			money=1500;
		}
		private int nearest(int[] spaces)
		{
			for(int i=0; i<spaces.length; i++)
				if(loc<spaces[i])
					return spaces[i];
			return spaces[0];
		}
		private void advanceTo(int i)
		{
			if(i<loc)
			{
				money+=200;
				if(i!=0)
					System.out.println("You passed Go!");
			}
			loc=i;
		}
		private void jail()
		{
			loc=10;
			jail=true;
			turns=0;
			System.out.println("You have been sent to Jail!");
		}
		private boolean repair(int house, int hotel)
		{
			int cost=0;
			for(Square p: prop)
				cost+=p.houses==5?hotel:p.houses*house;
			return pay(null, cost);
		}
		private void printProperties()
		{
			System.out.println("Properties owned: ");
			for(int i=0; i<prop.size(); i++)
				System.out.println(i+1+": "+prop.get(i));
		}
		private boolean monopoly(String color)
		{
			int r=0, m=0;
			for(Square p: prop)
				if(p.color.equals(color))
				{
					m=p.monop;
					r++;
				}
			return r==m;
		}
		private void buy()
		{
			System.out.println("Which color would you like to buy houses for?");
			printProperties();
			String color=input.nextLine().toLowerCase();
			if(monopoly(color))
				for(Square p: prop)
					if(p.color.equals(color))
					{
						if(p.houses==5)
							System.out.println("You already have "+color+" hotels!");
						else
						{
							int price=p.monop*p.house;
							if(money<price)
								System.out.println("You only have $"+money+"! You need $"+price+"!");
							else
							{
								money-=price;
								for(Square s: prop)
									if(s.color.equals(color))
										s.houses++;
								System.out.println("You bought "+color+" houses! You have $"+money+" left!");
							}
						}
						break;
					}
		}
		private void sell()
		{
			System.out.println("What color houses would you like to sell?");
			printProperties();
			int n=0;
			String s=input.nextLine().toLowerCase();
			for(Square p: prop)
				if(p.color.equals(s))
					if(p.houses>0)
					{
						n++;
						p.houses--;
						money+=p.house/2;
						System.out.println("You sold a house on "+p+"!");
					}
					else
						System.out.println("You have no houses on "+p+"!");
			System.out.println("You sold "+n+" houses!");
		}
		private void mortgage()
		{
			System.out.println("Which property would you like to mortgage? Type the number associated with it.");
			printProperties();
			try
			{
				int i=Integer.parseInt(input.nextLine())-1;
				Square p=prop.get(i);
				if(p.houses==0)
				{
					prop.remove(i);
					mort.add(p);
					money+=p.price/2;
					System.out.println("You mortgaged "+p+"!");
				}
				else
					System.out.println("You still have houses on "+p+"!");
			}
			catch(Exception e)
			{
				System.out.println("You decided not to mortgage any properties.");
			}
		}
		private void unmortgage()
		{
			System.out.println("Which property would you like to un-mortgage? Type the number associated with it.");
			printProperties();
			for(int i=0; i<mort.size(); i++)
				System.out.println(i+1+mort.get(i).toString());
			try
			{
				int i=Integer.parseInt(input.nextLine())-1;
				Square p=mort.get(i);
				int price=p.price*11/20;
				if(money<price)
					System.out.println("It costs $"+price+"! You only have $"+money+"!");
				else
				{
					money-=price;
					mort.remove(i);
					prop.add(p);
					System.out.println("You un-mortgaged "+p+"!");
				}
			}
			catch(Exception e)
			{
				System.out.println("You decided not to un-mortgage any properties.");
			}
		}
		private boolean pay(Player player, int amount)
		{
			while(money<amount&&prop.size()>0)
			{
				int o=0;
				System.out.println(name+", you have $"+money+" but need to pay $"+amount+"! What would you like to do?");
				System.out.println("1) Sell houses/hotels.");
				System.out.println("2) Mortgage a property.");
				while(o!=1&&o!=2)
					try
					{
						o=Integer.parseInt(input.nextLine());
					}
					catch(Exception e)
					{
						System.out.println("You must type the number for a valid option!");
					}
				if(o==1)
					sell();
				else
					mortgage();
			}
			if(money<amount)
			{
				if(player!=null)
					player.money+=money;
				return true;
			}
			money-=amount;
			if(player!=null)
				player.money+=amount;
			return false;
		}
		public String toString()
		{
			return name;
		}
	}
	private Square[] board=new Square[]{new Square("Go"),
		new Square("Mediterranean Avenue", "purple", 60, 2, 50, new int[]{2, 10, 30, 90, 160, 250}),
		new Square("Community Chest"),
		new Square("Baltic Avenue", "purple", 60, 2, 50, new int[]{4, 20, 60, 180, 320, 450}),
		new Square("Income Tax", 200),
		new Square("Reading Railroad", "railroad", 200),
		new Square("Oriental Avenue", "teal", 100, 3, 50, new int[]{6, 30, 90, 270, 400, 550}),
		new Square("Chance"),
		new Square("Vermont Avenue", "teal", 100, 3, 50, new int[]{6, 30, 90, 270, 400, 550}),
		new Square("Connecticut Avenue", "teal", 120, 3, 50, new int[]{8, 40, 100, 300, 450, 600}),
		new Square("Jail"),
		new Square("St. Charles Place", "pink", 140, 3, 100, new int[]{10, 50, 150, 450, 625, 750}),
		new Square("Electric Company", "utility", 150),
		new Square("States Avenue", "pink", 140, 3, 100, new int[]{10, 50, 150, 450, 625, 750}),
		new Square("Virginia Avenue", "pink", 160, 3, 100, new int[]{12, 60, 180, 500, 700, 900}),
		new Square("Pennsylvania Railroad", "railroad", 200),
		new Square("St. James Place", "orange", 180, 3, 100, new int[]{14, 70, 200, 550, 750, 950}),
		new Square("Community Chest"),
		new Square("Tennessee Avenue", "orange", 180, 3, 100, new int[]{14, 70, 200, 550, 750, 950}),
		new Square("New York Avenue", "orange", 200, 3, 100, new int[]{16, 80, 220, 600, 800, 1000}),
		new Square("Free Parking"),
		new Square("Kentucky Avenue", "red", 220, 3, 150, new int[]{18, 90, 250, 700, 875, 1050}),
		new Square("Chance"),
		new Square("Indiana Avenue", "red", 220, 3, 150, new int[]{18, 90, 250, 700, 875, 1050}),
		new Square("Illinois Avenue", "red", 240, 3, 150, new int[]{20, 100, 300, 750, 925, 1100}),
		new Square("B&O Railroad", "railroad", 200),
		new Square("Atlantic Avenue", "yellow", 260, 3, 150, new int[]{22, 110, 330, 800, 975, 1150}),
		new Square("Ventnor Avenue", "yellow", 260, 3, 150, new int[]{22, 110, 330, 800, 975, 1150}),
		new Square("Water Works", "utility", 150),
		new Square("Marvin Gardens", "yellow", 280, 3, 150, new int[]{24, 120, 360, 850, 1025, 1200}),
		new Square("Go to Jail"),
		new Square("Pacific Avenue", "green", 300, 3, 200, new int[]{26, 130, 390, 900, 1100, 1275}),
		new Square("North Carolina Avenue", "green", 300, 3, 200, new int[]{26, 130, 390, 900, 1100, 1275}),
		new Square("Community Chest"),
		new Square("Pennsylvania Avenue", "green", 320, 3, 200, new int[]{28, 150, 450, 1000, 1200, 1400}),
		new Square("Short Line", "railroad", 200),
		new Square("Chance"),
		new Square("Park Place", "blue", 350, 3, 200, new int[]{35, 175, 500, 1100, 1300, 1500}),
		new Square("Luxury Tax", 100),
		new Square("Boardwalk", "blue", 400, 3, 200, new int[]{50, 200, 600, 1400, 1700, 2000})};
	private Card[] chance=new Card[]{new Card("Advance to \"Go\".", 0),
		new Card("Advance to Illinois Ave.", 24),
		new Card("Advance to St. Charles Place.", 11),
		new Card("Advance token to nearest Utility."),
		new Card("Advance token to nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled."),
		new Card("Bank pays you dividend of $50.", 50),
		new Card("Get out of Jail free."),
		new Card("Go back three spaces."),
		new Card("Go to Jail."),
		new Card("Make general repairs on all your property: for each house pay $25, for each hotel pay $100."),
		new Card("Pay poor tax of $15.", -15),
		new Card("Advance to Reading Railroad.", 5),
		new Card("Advance to Boardwalk.", 39),
		new Card("You have been elected Chairman of the Board. Pay each player $50."),
		new Card("Your building loan matures. Collect $150.", 150),
		new Card("You have won a crossword competition. Collect $100.", 100)};
	private Card[] community=new Card[]{new Card("Advance to \"Go\".", 0),
		new Card("A Bank error in your favor. Collect $200.", 200),
		new Card("Doctor's fees. Pay $50.", -50),
		new Card("From sale of stock you get $50.", 50),
		new Card("Get out of Jail free."),
		new Card("Go to Jail."),
		new Card("Grand Opera Night. Collect $50 from every player for opening night seats.", 50),
		new Card("Holidy Fund matures. Collect $100.", 100),
		new Card("Income tax refund. Collect $20.", 20),
		new Card("It is your birthday. Collect $10 from every player.", 10),
		new Card("Life insurance matures - collect $100.", 100),
		new Card("Hospital fees. Pay $50.", -50),
		new Card("School fees. Pay $50.", -50),
		new Card("Receive $25 consultancy fee.", 25),
		new Card("You are assessed for street repairs: pay $40 per house and $115 per hotel you own."),
		new Card("You have won second prize in a beauty contest. Collect $10.", 10),
		new Card("You inherit $100.", 100)};
	private List<Player> players;
	private List<Card> chanceCards=shuffleCards(chance), communityCards=shuffleCards(community);
	private Scanner input=new Scanner(System.in);
	private void bankrupt(Player player)
	{
		players.remove(player);
		for(Square prop: player.mort)
			prop.owner="";
		System.out.println(player+" has gone bankrupt!");
	}
	private void drawCard(Player player, List<Card> cards)
	{
		Card card=cards.remove(0);
		System.out.println("You landed on "+board[player.loc]+"!");
		System.out.println(card);
		if(card.endsWith("Utility."))
			player.advanceTo(player.nearest(new int[]{12, 28}));
		else if(card.endsWith("entitled."))
			player.advanceTo(player.nearest(new int[]{5, 15, 25, 35}));
		else if(card.startsWith("Advance"))
			player.advanceTo(card.num);
		else if(card.startsWith("Get"))
			player.out++;
		else if(card.endsWith("spaces."))
			player.loc-=3;
		else if(card.endsWith("Jail."))
			player.jail();
		else if(card.startsWith("Make"))
			player.repair(25, 100);
		else if(card.endsWith("own."))
			player.repair(40, 115);
		else if(card.includes("each"))
		{
			for(Player p: players)
				if(player!=p)
					if(player.pay(p, 50))
						bankrupt(player);
		}
		else if(card.includes("every"))
		{
			for(Player p: players)
				if(player!=p)
					if(p.pay(player, card.num))
						bankrupt(p);
		}
		else if(card.num<0)
		{
			if(player.pay(null, 0-card.num))
				bankrupt(player);
		}
		else
			player.money+=card.num;
		cards.add(card);
	}
	private List<Card> shuffleCards(Card[] cards)
	{
		List<Card> unshuffled=new ArrayList<>(), shuffled=new ArrayList<>();
		for(Card card: cards)
			unshuffled.add(card);
		for(int i=cards.length; i>0; i--)
			shuffled.add(unshuffled.remove((int)(Math.random()*i)));
		return shuffled;
	}
	private boolean usedName(List<Player> players, String name)
	{
		for(Player p: players)
			if(p.name.equals(name))
				return true;
		return false;
	}
	private void setPlayers()
	{
		List<Player> p=new ArrayList<>();
		String name="n";
		for(int i=0; i<2||i<8&&!name.isEmpty(); i++)
		{
			do
			{
				System.out.println("Enter player "+(i+1)+"'s name:");
				name=input.nextLine();
			}while(i<2&&name.isEmpty()||usedName(p, name));
			if(!name.isEmpty())
			{
				System.out.println("Is this a human?");
				p.add(new Player(name, input.nextLine().toUpperCase().contains("Y")));
			}
		}
		players=new ArrayList<>();
		for(int i=p.size(); i>0; i--)
			players.add(p.remove((int)(Math.random()*i)));
	}
	private int roll()
	{
		return (int)(Math.random()*6)+1;
	}
	private int rent(Player p2, Square square)
	{
		if(square.color.equals("utility"))
		{
			int r=0;
			for(Square p: p2.prop)
				if(p.color.equals("utility"))
					r++;
			return (r==1?4:10)*(roll()+roll());
		}
		else if(square.color.equals("railroad"))
		{
			int r=0;
			for(Square p: p2.prop)
				if(p.color.equals("railroad"))
					r++;
			return r*50;
		}
		return square.houses==0&&p2.monopoly(square.color)?square.rent[0]*2:square.rent[square.houses];
	}
	private void move(Player player, int r1, int r2)
	{
		player.loc+=r1+r2;
		if(player.loc>39)
			player.advanceTo(player.loc-40);
		if(board[player.loc].name.equals("Chance"))
			drawCard(player, chanceCards);
		else if(board[player.loc].name.equals("Community Chest"))
			drawCard(player, communityCards);
		else
			System.out.println("You landed on "+board[player.loc]+"!");
		if(player.loc==30)
		{
			player.jail();
			return;
		}
		Square square=board[player.loc];
		if(square.owner==null)
		{
			if(player.money>=square.price)
			{
				System.out.println("Would you like to purchase "+square+"?");
				if(input.nextLine().toUpperCase().indexOf("Y")>=0)
				{
					player.money-=square.price;
					player.prop.add(square);
					square.owner=player.name;
					System.out.println("You purchased "+square+"!");
				}
			}
			else
				System.out.println("You don't have enough money to purchase "+square+"!");
		}
		else if(square.owner.equals(""))
		{
			if(player.pay(null, square.price))
				bankrupt(player);
			else if(square.price>0)
				System.out.println("You paid the bank what you owed!");
		}
		else
		{
			Player p2=null;
			for(Player p: players)
				if(p.name.equals(square.owner))
					p2=p;
			if(player==p2)
				System.out.println("You own this property!");
			else if(player.pay(p2, rent(p2, square)))
				bankrupt(player);
			else
				System.out.println("You paid rent to "+p2+"!");
		}
	}
	private void manageProperties(Player player)
	{
		int o=0;
		if(players.indexOf(player)>=0)
			while(o!=5)
			{
				System.out.println("What would you like to do?");
				System.out.println("1) Buy houses/hotels.");
				System.out.println("2) Un-mortgage a property.");
				System.out.println("3) Sell houses/hotels.");
				System.out.println("4) Mortgage a property.");
				System.out.println("5) End your turn.");
				try
				{
					o=Integer.parseInt(input.nextLine());
				}
				catch(Exception e)
				{
					System.out.println("You must type the number of the option you wish to choose!");
				}
				if(o==1)
					player.buy();
				else if(o==2)
					player.unmortgage();
				else if(o==3)
					player.sell();
				else if(o==4)
					player.mortgage();
			}
	}
	private void doNormalTurn(Player player)
	{
		for(int i=0, r1=0, r2=0; !player.jail&&r1==r2; i++)
		{
			r1=roll();
			r2=roll();
			System.out.println("You rolled a "+r1+" and a "+r2+"!");
			if(r1==r2&&i==2)
			{
				System.out.println("You rolled doubles three times in a row.");
				player.jail();
			}
			else
				move(player, r1, r2);
		}
	}
	private void doTurn(Player player)
	{
		int r1=0, r2=0, o=0;
		System.out.println("It is "+player+"'s turn!");
		if(player.jail)
		{
			boolean b=true;
			System.out.println("You are in jail! What would you like to do?");
			System.out.println("1) Use a get out of jail free card!");
			System.out.println("2) Pay $50!");
			System.out.println("3) Roll to get out!");
			while(b)
			{
				try
				{
					o=Integer.parseInt(input.nextLine());
				}
				catch(Exception e)
				{
					System.out.println("You must type the number of a valid option!");
				}
				if(o==1)
				{
					if(player.out>0)
					{
						player.out--;
						player.jail=false;
						doNormalTurn(player);
						b=false;
					}
					else
						System.out.println("You don't have any cards!");
				}
				else if(o==2)
					if(player.money>49)
					{
						player.money-=50;
						doNormalTurn(player);
						b=true;
					}
					else
						System.out.println("You only have $"+player.money+"!");
				else if(o==3)
				{
					r1=roll();
					r2=roll();
					System.out.println("You rolled a "+r1+" and a "+r2+"!");
					if(r1==r2)
					{
						System.out.println("You escaped Jail!");
						move(player, r1, r2);
						b=true;
					}
					else if(++player.turns==3)
						if(player.pay(null, 50))
							bankrupt(player);
						else
						{
							doNormalTurn(player);
							b=true;
						}
					else
					{
						b=true;
						System.out.println("You failed to roll doubles!");
					}
				}
				else
					System.out.println("Valid options are between 1 and 3...");
			}
		}
		else
			doNormalTurn(player);
		manageProperties(player);
	}
	public void play()
	{
		setPlayers();
		while(players.size()>1)
			for(int i=players.size()-1; i>=0; i--)
				doTurn(players.get(i));
		System.out.println(players.get(0)+" won!");
	}
	public static void main(String[] a)
	{
		new Monopoly().play();
	}
}