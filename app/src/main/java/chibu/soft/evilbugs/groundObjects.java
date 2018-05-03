package chibu.soft.evilbugs;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class groundObjects extends Sprite
{
	 public int name;
     public Bitmap Myname;
     public Vector2 Position;

     //private int Speed;
     //private Vector2 Jleft; //floorPanel
     public Boolean gapTouch, Jump = false, fall = false;
    // private Vector2 PfloorTouched, PleftEdge, PRightEdge;
     public int floorY;
     private int gapFloor = 80; //this ditermines the gap on the floor



     public groundObjects(Resources game,Vector2 icanvas, int Gname, Vector2 GPosition,int Yfloor)         
     {
    	 super (game,icanvas, Gname);
         //Note the base means and inheriting from the main class sprite and call the contructor that takes these parameters
         //And also i can access all the values that are public in this class sprite

         this.name = Gname;
         this.Position = GPosition;
         this.floorY = Yfloor;
       
     }

     //public groundObjects(Game game, Texture2D Gname, Vector2 GPosition, float Yfloor,string texturename)
     //    : base(game, Gname)
     //{
     //    //Note the base means and inheriting from the main class sprite and call the contructor that takes these parameters
     //    //And also i can access all the values that are public in this class sprite

     //    this.Myname = Gname;
     //    this.name = texturename;
     //    this.Position = GPosition;
     //    this.floorY = Yfloor;

     //}

     public void updateGround(Double gameTime, int GSpeed, Vector2 explorer, int explorerWidth, int explorerheight)
     {
         Position.X -= (int)(gameTime * GSpeed);//GSpeed

         //i have to come back here this is supposed to know when the character can fall down
         //if (name.contains("L") || name.contains("I"))
         //{
             if(name == R.drawable.l || name == R.drawable.l1 || name == R.drawable.l2 || name == R.drawable.l3 
            		 || name == R.drawable.l4 || name == R.drawable.l5 || name == R.drawable.i || name == R.drawable.i4 || name == R.drawable.i5
            		 || name == R.drawable.li || name == R.drawable.li4 || name == R.drawable.li5 ||
            		 name == R.drawable.ni4 || name == R.drawable.ni5 || name == R.drawable.nl1 || name == R.drawable.nl2 
            		 || name == R.drawable.nl3 || name == R.drawable.nl4 || name == R.drawable.nl5 )
             {
            	 falling(explorer, explorerWidth, explorerheight);
             }

             //  falling(explorer, explorerWidth, explorerheight);
         //}

      
       
     }

     public void Draw(Canvas spritebatch, int viewY)
     {
         Draw(spritebatch, Position.X, Position.Y - viewY);

     }

     //Jump Touch censor 
     private void TouchedExplorer(Vector2 explorerPosition, int explorerWidth, int explorerHeight)
     {

         if (explorerPosition.X + explorerWidth >= Position.X + Texture2DWidth && 
                 explorerPosition.X <= (Position.X + Texture2DWidth + 40) &&
                 (explorerPosition.Y + explorerHeight >= floorY &&
                         explorerPosition.Y <= floorY - 20 ))
         {
             Jump = true;
         }

         //When dont use this code for touch detection reason while its abit tricky
         //is that texture2dWidth is the ryming position for the ground touch sensor

         //Note 40 and 40 is height and width of inner box while 765(X) and 9(Y) is the rythming positions for box to detect jump
         //Note explorerHeight and explorerWidth is height and width of explorer while .(X) , .(Y) is the position of the explorer

     }

     //code is to know when explorer is falling
     private void falling(Vector2 explorerPosition, int explorerWidth, int explorerHeight)
     {
         //note 200 is ground gap and there subject to change
         if (Position.X + Texture2DWidth + gapFloor >= explorerPosition.X &&
            Position.X + Texture2DWidth <= explorerPosition.X  + explorerWidth &&
             (floorY + 50 >= explorerPosition.Y  &&
                     floorY <= explorerPosition.Y + explorerHeight))
         {
             fall = true;
         }

        //if (Position.X + Texture2DWidth + 150 >= explorerPosition.X  &&
        //    Position.X + Texture2DWidth <= (explorerPosition.X + (explorerWidth)) &&
        //     (716 + 1 >= explorerPosition.Y &&
        //             716  <= explorerPosition.Y + explorerHeight))
     }

     private void RemovegapItems(Vector2 itemPosition, int itemWidth, int itemHeight)
     {

         if (itemPosition.X + itemWidth >= Position.X + 800 &&
                  itemPosition.X <= (Position.X + 150 + 800) &&
                  (itemPosition.Y + itemHeight >= Position.Y - 20 &&
                          itemPosition.Y <= Position.Y + 20 - 20))
         {
             gapTouch = true;
         }

         //Note 100(size of gap) and 150 is width and height of inner box while 800(X) and 150(Y) is the rythming positions for box to detect jump
         //Note explorerHeight and explorerWidth is height and width of explorer while .(X) , .(Y) is the position of the explorer
     }     
}
