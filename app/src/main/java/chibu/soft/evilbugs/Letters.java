package chibu.soft.evilbugs;

import android.content.res.Resources;
import android.graphics.Canvas;

public class Letters 
{
	 private Sprite ABCD;
     public String Words;
     private String[] letter;
     private int[] frame ;
     public float startX, startY;
     public float X, Y;
     private int XSpace = iWidth(13), YSpace = iHeight(20);//maxline = 600
     public int MaxLine = iWidth(340);
     public int Gap = iWidth(13);
     
    
     public Letters(Resources game,Vector2 canvas, String sentence,float x, float y, int abcd,int spriteWidth,int spriteHeight)
     {
         this.Words = sentence;
         ABCD = new Sprite(game,canvas, abcd, spriteWidth, spriteHeight);
         
       
         this.X = iWidth(x);
         this.Y = iHeight(y);

         startX = X;
         startY = Y;


     }

    public void update()
     {
         processSentence();
         SentenceFrame();
     }

    public void update(String words,float x, float y)
    {
        this.Words = words;
        this.X = x;
        this.Y = y;

        startX = X;
        startY = Y;

        processSentence();
        SentenceFrame();
    }


     private void processSentence()
     {
         letter = new String[Words.length()];

         for (int i = 0; i < Words.length(); i++)
         {
             letter[i] = Words.substring(i,i + 1);
         }

     }

     private void SentenceFrame()
     {
         frame = new int[letter.length];

         for (int i = 0; i < frame.length; i++)
         {
             if (letter[i].equalsIgnoreCase("A") )
             {
                 frame[i] = 0;
             }
             else if (letter[i].equalsIgnoreCase("B") )
             {
                 frame[i] = 1;
             }
             else if (letter[i].equalsIgnoreCase("C") )
             {
                 frame[i] = 2;
             }
             else if (letter[i].equalsIgnoreCase("D") )
             {
                 frame[i] = 3;
             }
             else if (letter[i].equalsIgnoreCase("E") )
             {
                 frame[i] = 4;
             }
             else if (letter[i].equalsIgnoreCase("F") )
             {
                 frame[i] = 5;
             }
             else if (letter[i].equalsIgnoreCase("G") )
             {
                 frame[i] = 6;
             }
             else if (letter[i].equalsIgnoreCase("H") )
             {
                 frame[i] = 7;
             }
             else if (letter[i].equalsIgnoreCase("I"))
             {
                 frame[i] = 8;
             }
             else if (letter[i].equalsIgnoreCase("J") )
             {
                 frame[i] = 9;
             }
             else if (letter[i].equalsIgnoreCase("K") )
             {
                 frame[i] = 10;
             }
             else if (letter[i].equalsIgnoreCase("L") )
             {
                 frame[i] = 11;
             }
             else if (letter[i].equalsIgnoreCase("M") )
             {
                 frame[i] = 12;
             }
             else if (letter[i].equalsIgnoreCase("N") )
             {
                 frame[i] = 13;
             }
             else if (letter[i].equalsIgnoreCase("O") )
             {
                 frame[i] = 14;
             }
             else if (letter[i].equalsIgnoreCase("P") )
             {
                 frame[i] = 15;
             }
             else if (letter[i].equalsIgnoreCase("Q") )
             {
                 frame[i] = 16;
             }
             else if (letter[i].equalsIgnoreCase("R") )
             {
                 frame[i] = 17;
             }
             else if (letter[i].equalsIgnoreCase("S") )
             {
                 frame[i] = 18;
             }
             else if (letter[i].equalsIgnoreCase("T") )
             {
                 frame[i] = 19;
             }
             else if (letter[i].equalsIgnoreCase("U") )
             {
                 frame[i] = 20;
             }
             else if (letter[i].equalsIgnoreCase("V") )
             {
                 frame[i] = 21;
             }
             else if (letter[i].equalsIgnoreCase("W") )
             {
                 frame[i] = 22;
             }
             else if (letter[i].equalsIgnoreCase("X") )
             {
                 frame[i] = 23;
             }
             else if (letter[i].equalsIgnoreCase("Y") )
             {
                 frame[i] = 24;
             }
             else if (letter[i].equalsIgnoreCase("Z") )
             {
                 frame[i] = 25;
             }
             else if (letter[i].equalsIgnoreCase(".") || letter[i].equalsIgnoreCase(",") || letter[i].equalsIgnoreCase(" ")) 
             {
                 frame[i] = 26;
             }
             else if (letter[i].equalsIgnoreCase("0"))
             {
                 frame[i] = 30;
             }
             else if (letter[i].equalsIgnoreCase("1"))
             {
                 frame[i] = 31;
             }
             else if (letter[i].equalsIgnoreCase("2"))
             {
                 frame[i] = 32;
             }
             else if (letter[i].equalsIgnoreCase("3"))
             {
                 frame[i] = 33;
             }
             else if (letter[i].equalsIgnoreCase("4"))
             {
                 frame[i] = 34;
             }
             else if (letter[i].equalsIgnoreCase("5"))
             {
                 frame[i] = 35;
             }
             else if (letter[i].equalsIgnoreCase("6"))
             {
                 frame[i] = 36;
             }
             else if (letter[i].equalsIgnoreCase("7"))
             {
                 frame[i] = 37;
             }
             else if (letter[i].equalsIgnoreCase("8"))
             {
                 frame[i] = 38;
             }
             else if (letter[i].equalsIgnoreCase("9"))
             {
                 frame[i] = 39;
             }
            
         }

         //kill 10 Mosquitoes in one run
     }

     public void Draw(Canvas spriteBatch)
     {
         for (int i = 0; i < frame.length; i++)
         {
             //Doing other processing stuffs
             ABCD.Draw(spriteBatch, (int)X, (int)Y, frame[i]);


             //When we get to a space we check if the next word wont be too long for the line
             if (frame[i] == 26)
             {
                 int wordlenght = 1; //THIS IS USED TO KNOW THE LENGHT WE ALL READY HAVE
                 int wordsSpace = iWidth(13);
                 //int MaxLine this is to know the max sentence we want on a line
                 // GAP character spacing
                 //we are checking for the next 26 in the line
                 for (int j = i + 1; j < frame.length; j++)
                 {
                     

                     if (frame[j] == 26)
                     {
                         if ((wordlenght * wordsSpace) +(X + Gap) - startX > MaxLine) //this to make sure we dont exceed the maximum line
                         {
                             X = startX;
                             Y += YSpace;
                         }
                         else
                         {
                             X += Gap;

                         }
                         break;
                     }
                     wordlenght += 1;
                 }

             }
             else
             {
                 X += XSpace;

             }
         }

         X = startX;
         Y = startY;
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
