public class Card
{
   String description, place;
   int fee, hotel;
   public Card(String d, int f)
   {
      description=d;
      fee=f;
   }
   public Card(String d, int f, int h)
   {
      description=d;
      fee=f;
      hotel=h;
   }
   public Card(String d, String p)
   {
      description=d;
      place=p;
   }
}