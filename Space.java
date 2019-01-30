public class Space
{
   int[] houses=new int[6];
   String name;
   int value, houseCost, owner, numHouses;
   public Space(String n)
   {
      name=n;
   }
   public Space(String n, int r)
   {
      name=n;
      houses[0]=r;
      owner=8;
   }
   public Space(String n, int v, int r)
   {
      name=n;
      value=v;
      for(int i=1; i<5; i++)
         houses[i]=r*i;
   }
   public Space(String n, int v, int r1, int r2)
   {
      name=n;
      value=v;
      houses[1]=r1;
      houses[2]=r2;
   }
   public Space(String n, int v, int hc, int r, int one, int two, int three, int four, int h)
   {
      name=n;
      value=v;
      owner=9;
      numHouses=0;
      houseCost=hc;
      houses[0]=r;
      houses[1]=one;
      houses[2]=two;
      houses[3]=three;
      houses[4]=four;
      houses[5]=h;
   }
   public void giveTo(int p)
   {
      owner=p;
   }
   public int value()
   {
      return value;
   }
   public int rent()
   {
      return houses[numHouses];
   }
   public int owner()
   {
      return owner;
   }
   public boolean is(String n)
   {
      return name.equals(n);
   }
   public boolean isA(String n)
   {
      return name.contains(n);
   }
   public String toString()
   {
      return name+" "+value+" "+houses[numHouses];
   }
}