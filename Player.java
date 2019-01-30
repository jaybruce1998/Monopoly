public class Player
{
   String name;
   boolean inJail;
   int money, space;
   public Player(String n)
   {
      name=n;
      inJail=false;
      money=1500;
      space=0;
   }
   public Player(String n, int m, int s)
   {
      name=n;
      inJail=true;
      money=m;
      space=s;
   }
   public String name()
   {
      return name;
   }
   public int money()
   {
      return money;
   }
   public int space()
   {
      return space;
   }
   public void give(int m)
   {
      money+=m;
   }
   public boolean passedGo(int s)
   {
      space+=s;
      if(space>39)
      {
         space-=39;
         money+=200;
         return true;
      }
      return false;
   }
}