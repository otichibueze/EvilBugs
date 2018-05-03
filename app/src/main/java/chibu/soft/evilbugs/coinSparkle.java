package chibu.soft.evilbugs;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class coinSparkle extends Sprite
{
	  public float SparkX;
      public float SparkY;
      private int sparkleframe = 0;
     //private int counter = 0;
      public boolean removeMe = false;

      public coinSparkle(Vector2 canvas, Bitmap sparkleImage, float X, float Y, int frameX, int frameY)          
      {
    	  super(canvas, sparkleImage, frameX, frameY);
    	  
          this.SparkX = X; //this is the rymthing position for the dust
          this.SparkY = Y;

      }

      public void Update(double gameTime, float speed)
        {
            SparkX -= (int)gameTime * speed;

            
          counter += 1;

          if (counter == 2)
          {
              sparkleframe += 1;
              counter = 0;
          }
            //if (sparkleframe == 2)
            //{
            //    removeMe = true;
            //}
            
        }

        public void CoinSparkleDraw(Canvas spriteBatch, int by)
        {
            if (sparkleframe  < 3) Draw(spriteBatch, (int)(SparkX), (int)(SparkY) - by, sparkleframe);
            else 
                removeMe = true;

           // sparkleframe += 1;
        }
      

}
