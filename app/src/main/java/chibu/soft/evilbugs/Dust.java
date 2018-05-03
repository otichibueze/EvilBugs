package chibu.soft.evilbugs;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Dust extends Sprite
{
    public float Dustx;
    public float Dusty;
    private int Dustframe = 0;
    public boolean removeMe = false;

    
      public Dust(Vector2 canvas, Bitmap Dustimage,float X, float Y,int frameX, int frameY)        
    {
    	  super(canvas, Dustimage, frameX, frameY);
    	  

        this.Dustx = X; //this is the rymthing position for the dust
        this.Dusty = Y ;

    }

      public void Update(Double gameTime, float speed)
      {

          //This code is working 
          Dustx -= (int)(gameTime * speed);

         
              Dustframe += 1;
            

          //_ 60.....Y + 104  //for explorer dust
          // -18, + 60 // for bugs

      }

      public void DrawDust(Canvas spriteBatch, int by)
      {
          if (Dustframe < 7) Draw(spriteBatch, (int)(Dustx), (int)(Dusty) - by, Dustframe);
          else removeMe = true;
      }
}
