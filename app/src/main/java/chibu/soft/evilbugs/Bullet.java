package chibu.soft.evilbugs;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet extends Sprite
{
	 private int DefaultDeviceWidth = 800, DefaultDeviceheight = 480;


     public float Bulletx;
     public float Bullety;
     private float angleRadians;
     public int Type = 0;
     public boolean Hit = false, fired = false;
     //note create a type to know damage value so bug checks type of gun and minus damage value


     public Bullet(Vector2 canvas, Bitmap bulletImage, float AngleRadians, float X, float Y)
        
      {
    	super(canvas,bulletImage, X, Y,false);
    	
          this.Bulletx = X;
          this.Bullety = Y;
          
          this.angleRadians = AngleRadians;
 
      }

      public void Update(Double gameTime, float speed)
      {
          //This code is working 
          Bulletx += (float)Math.cos(angleRadians) * gameTime * speed;
          Bullety += (float)Math.sin(angleRadians) * gameTime * speed;
      }

      public void Draw(Canvas spriteBatch,int by)
      {
        
    	  float angle = (float)Math.toDegrees(angleRadians);
    	  
          Draw(spriteBatch, 1, angle, new Vector2(Bulletx ,(Bullety ) - by,false) , 
        		  new Vector2(0,0), 0);//this last zero on this method was spriteeffect.none
          //- iHeight(5)
      }

     
      public int iHeight(int y)
      {
          y = (int)(y * EvilBugsView.PositionfactorY);
     	    
     	 return y; 	 
      }
      
      public float iHeight(float y)
      {
          y = (float)(y * EvilBugsView.PositionfactorY);
     	    
     	 return y; 	 
      }
      	     
      public int iWidth(int x)
      {
          x = (int)(x * EvilBugsView.PositionfactorX);

     	return x;
      }
      
      public float iWidth(float x)
      {
          x = (float)(x * EvilBugsView.PositionfactorX);
     			 
     	return x;
      }


}
