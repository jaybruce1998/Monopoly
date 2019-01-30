import java.util.ArrayList;
import javax.swing.*;
public class Monopoly
{
   static ArrayList<Card> communityChest;
   static ArrayList<Card> chance;
   static ArrayList<Card> usedCC;
   static ArrayList<Card> usedC;
   static Space[] board;
   static Player[] players;
   static String typed;
   static int randomI, numPlayers, alive, die1, die2, doubles;
   public static void setupGame()
   {
      communityChest=new ArrayList<Card>();
      chance=new ArrayList<Card>();
      usedCC=new ArrayList<Card>();
      usedC=new ArrayList<Card>();
      board=new Space[40];
      usedCC.add(new Card("It is your birthday. Collect $10 from every player.", -10));
      usedCC.add(new Card("You are assessed for street repairs: $40 per house, $115 for hotel.", 40, 115));
      usedCC.add(new Card("Get out of jail free. This card may be kept until needed or traded.", 0));
      usedCC.add(new Card("Go to jail. Go directly to jail, do not pass Go, do not collect $200.", "Jail"));
      usedCC.add(new Card("Life insurance matures. Collect $100.", -100));
      usedCC.add(new Card("Advance to Go. (Collect $200)", "Go"));
      usedCC.add(new Card("Doctor's fees. Pay $50.", 50));
      usedCC.add(new Card("Receive $25 consultancy fee.", -25));
      usedCC.add(new Card("You have won second prize in a beauty contest. Collect $10.", -10));
      usedCC.add(new Card("You inherit $100.", -100));
      usedCC.add(new Card("Bank error in your favor. Collect $200.", -200));
      usedCC.add(new Card("From sale of stock you get $50.", -50));
      usedCC.add(new Card("Income tax refund. Collect $20.", -20));
      usedCC.add(new Card("Holiday fund matures. Receive $100.", -100));
      usedCC.add(new Card("Pay school fees of $50.", 50));
      usedCC.add(new Card("Pay hospital fees of $100.", 100));
      usedC.add(new Card("Make general repairs on all your property: for each house pay $25, for each hotel pay $100.", 25, 100));
      usedC.add(new Card("Bank pays you dividend of $50.", -50));
      usedC.add(new Card("Advance to the nearest railroad. If unowned, you may buy it from the bank. If owned, pay owner twice the rental to which they are otherwise entitled.", "Railroad"));
      usedC.add(new Card("Advance to the nearest railroad. If unowned, you may buy it from the bank. If owned, pay owner twice the rental to which they are otherwise entitled.", "Railroad"));
      usedC.add(new Card("Go to jail. Go directly to jail, do not pass Go, do not collect $200.", "Jail"));
      usedC.add(new Card("Advance to the nearest utility. If unowned, you may buy it from the bank. If owned, throw dice and pay owner a total ten times amount thrown.", "Utility"));
      usedC.add(new Card("Advance to Go. (Collect $200)", "Go"));
      usedC.add(new Card("Take a trip to reading railroad. If you pass Go, collect $200.", "Go"));
      usedC.add(new Card("Speeding fine $15.", 15));
      usedC.add(new Card("You building loan matures. Collect $150.", -150));
      usedC.add(new Card("Advance to Boardwalk.", "Boardwalk"));
      usedC.add(new Card("Advance to Illinois Avenue. If you pass Go, collect $200.", "Illinois Avenue"));
      usedC.add(new Card("Go back three board", 0));
      usedC.add(new Card("You have been elected charman of the board. Pay each player $50.", 50));
      usedC.add(new Card("Get out of jail free. This card may be kept until needed or traded.", 0));
      usedC.add(new Card("Advance to St. Charles Place. If you pass Go collect $200.", "St. Charles Place"));
      board[0]=new Space("Go");
      board[1]=new Space("Mediterranean Avenue", 60, 50, 2, 10, 30, 90, 160, 250);
      board[2]=new Space("Community Chest");
      board[3]=new Space("Baltic Avenue", 60, 50, 4, 20, 60, 180, 320, 450);
      board[4]=new Space("Income Tax", 200);
      board[5]=new Space("Reading Railroad", 200, 25);
      board[6]=new Space("Oriental Avenue", 100, 50, 6, 30, 90, 270, 400, 550);
      board[7]=new Space("Chance");
      board[8]=new Space("Vermont Avenue", 100, 50, 6, 30, 90, 270, 400, 550);
      board[9]=new Space("Connecticut Avenue", 120, 50, 8, 40, 100, 300, 450, 600);
      board[10]=new Space("Jail");
      board[11]=new Space("St. Charles Place", 140, 100, 10, 50, 150, 450, 625, 750);
      board[12]=new Space("Electric Company", 150, 4, 10);
      board[13]=new Space("States Avenue", 140, 100, 10, 50, 150, 450, 625, 750);
      board[14]=new Space("Virginia Avenue", 160, 100, 12, 60, 180, 500, 700, 900);
      board[15]=new Space("Pennsylvania Railroad", 200, 25);
      board[16]=new Space("St. James Place", 180, 100, 14, 70, 200, 550, 750, 950);
      board[17]=new Space("Community Chest");
      board[18]=new Space("Tennessee Avenue", 180, 100, 14, 70, 200, 550, 750, 950);
      board[19]=new Space("New York Avenue", 200, 100, 16, 80, 220, 600, 800, 1000);
      board[20]=new Space("Free Parking");
      board[21]=new Space("Kentucky Avenue", 220, 150, 18, 90, 250, 700, 875, 1050);
      board[22]=new Space("Chance");
      board[23]=new Space("Indiana Avenue", 220, 150, 18, 90, 250, 700, 875, 1050);
      board[24]=new Space("Illinois Avenue", 240, 150, 20, 100, 300, 750, 925, 1100);
      board[25]=new Space("B. & O. Railroad", 200, 25);
      board[26]=new Space("Atlantic Avenue", 260, 150, 22, 110, 330, 800, 975, 1150);
      board[27]=new Space("Ventnor Avenue", 260, 150, 22, 110, 330, 800, 975, 1150);
      board[28]=new Space("Water Works", 150, 4, 10);
      board[29]=new Space("Marvin Gardens", 280, 150, 24, 120, 360, 850, 1025, 1200);
      board[30]=new Space("Go To Jail");
      board[31]=new Space("Pacific Avenue", 300, 200, 26, 130, 390, 900, 1100, 1275);
      board[32]=new Space("North Carolina Avenue", 300, 200, 26, 130, 390, 900, 1100, 1275);
      board[33]=new Space("Community Chest");
      board[34]=new Space("Pennsylvania Avenue", 320, 200, 28, 150, 450, 1000, 1200, 1400);
      board[35]=new Space("Short Line", 200, 25);
      board[36]=new Space("Chance");
      board[37]=new Space("Park Place", 350, 200, 35, 175, 500, 1100, 1300, 1500);
      board[38]=new Space("Luxury Tax", 100);
      board[39]=new Space("Boardwalk", 400, 200, 50, 200, 600, 1400, 1700, 2000);
   }
   public static void shuffleCards()
   {
      while(usedCC.size()>0)
      {
         randomI=(int)(Math.random()*usedCC.size());
         communityChest.add(usedCC.get(randomI));
         usedCC.remove(randomI);
      }
      while(usedC.size()>0)
      {
         randomI=(int)(Math.random()*usedC.size());
         chance.add(usedC.get(randomI));
         usedC.remove(randomI);
      }
   }
   public static void resetGame()
   {
      shuffleCards();
      typed=JOptionPane.showInputDialog("How many players would you like?");
      numPlayers=Integer.parseInt(typed);
      alive=numPlayers;
      players=new Player[9];
      for(int i=0; i<numPlayers; i++)
         players[i]=new Player(JOptionPane.showInputDialog("What is player "+(i+1)+"'s name?"));
      players[8]=new Player("The Middle", 500, 40);
   }
   public static void rollDice()
   {
      die1=(int)(Math.random()*6)+1;
      die2=(int)(Math.random()*6)+1;
   }
   public static void move(int p)
   {
      if(players[p].passedGo(die1+die2))
         System.out.println("You passed Go and collected $200!");
      if(board[players[p].space()].owner()<8)
      {
         typed=JOptionPane.showInputDialog(board[players[p].space()]+" is unowned. Would you like to buy it for $"+board[players[p].space()].value());
         if(typed.contains("y"))
            if(players[p].money()>=board[players[p].space()].value())
            {
               players[p].give(-1*board[players[p].space()].value());
               board[players[p].space()].giveTo(p);
            }
            else
               System.out.println("You do not have enough money.");
      }
      else
      {
         System.out.println(board[players[p].space()]+" is owned by "+board[players[p].space()].owner()+".");
         players[p].give("kek");
      }
   }
   public static void doTurn(int p)
   {
      rollDice();
      System.out.println("It is "+players[p].name()+"'s turn. You rolled a "+die1+" and a "+die2+".");
      move(p);
   }
   public static void playGame()
   {
      resetGame();
      while(alive>1)
         for(int p=0; p<numPlayers; p++)
            if(players[p].money()>=0)
               doTurn(p);
   }
   public static void main(String[] args)
   {
      setupGame();
      playGame();
   }
}