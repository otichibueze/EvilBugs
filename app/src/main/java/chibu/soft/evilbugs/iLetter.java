package chibu.soft.evilbugs;

import android.content.res.Resources;
import android.graphics.Canvas;

public class iLetter 
{
	private Sprite ABCD;
    public String Words;
    private String[] letter;
    private int[] frame;
    private float startX, startY;
    public float X, Y;
    public int XSpace = iWidth(13), YSpace = iHeight(20);//maxline = 600
    public int MaxLine = iWidth(340);
    public int Gap = iWidth(13);
    
    public iLetter(Resources game,Vector2 canvas, String sentence,float x, float y, int abcd,int spriteWidth,int spriteHeight)
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
       this.X = iWidth(x);
       this.Y = iHeight(y);

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
            if (letter[i].equalsIgnoreCase("A") || letter[i].equalsIgnoreCase("a"))
            {
                frame[i] = 0;
            }
            else if (letter[i].equalsIgnoreCase("B") || letter[i].equalsIgnoreCase("b"))
            {
                frame[i] = 1;
            }
            else if (letter[i].equalsIgnoreCase("C") || letter[i].equalsIgnoreCase("c"))
            {
                frame[i] = 2;
            }
            else if (letter[i].equalsIgnoreCase("D") || letter[i].equalsIgnoreCase("d"))
            {
                frame[i] = 3;
            }
            else if (letter[i].equalsIgnoreCase("E") || letter[i].equalsIgnoreCase("e"))
            {
                frame[i] = 4;
            }
            else if (letter[i].equalsIgnoreCase("F") || letter[i].equalsIgnoreCase("f"))
            {
                frame[i] = 5;
            }
            else if (letter[i].equalsIgnoreCase("G") || letter[i].equalsIgnoreCase("g"))
            {
                frame[i] = 6;
            }
            else if (letter[i].equalsIgnoreCase("H") || letter[i].equalsIgnoreCase("h"))
            {
                frame[i] = 7;
            }
            else if (letter[i].equalsIgnoreCase("I") || letter[i].equalsIgnoreCase("i"))
            {
                frame[i] = 8;
            }
            else if (letter[i].equalsIgnoreCase("J") || letter[i].equalsIgnoreCase("j"))
            {
                frame[i] = 9;
            }
            else if (letter[i].equalsIgnoreCase("K") || letter[i].equalsIgnoreCase("k"))
            {
                frame[i] = 10;
            }
            else if (letter[i].equalsIgnoreCase("L") || letter[i].equalsIgnoreCase("l"))
            {
                frame[i] = 11;
            }
            else if (letter[i].equalsIgnoreCase("M") || letter[i].equalsIgnoreCase("m"))
            {
                frame[i] = 12;
            }
            else if (letter[i].equalsIgnoreCase("N") || letter[i].equalsIgnoreCase("n"))
            {
                frame[i] = 13;
            }
            else if (letter[i].equalsIgnoreCase("O") || letter[i].equalsIgnoreCase("o"))
            {
                frame[i] = 14;
            }
            else if (letter[i].equalsIgnoreCase("P") || letter[i].equalsIgnoreCase("p"))
            {
                frame[i] = 15;
            }
            else if (letter[i].equalsIgnoreCase("Q") || letter[i].equalsIgnoreCase("q"))
            {
                frame[i] = 16;
            }
            else if (letter[i].equalsIgnoreCase("R") || letter[i].equalsIgnoreCase("r"))
            {
                frame[i] = 17;
            }
            else if (letter[i].equalsIgnoreCase("S") || letter[i].equalsIgnoreCase("s"))
            {
                frame[i] = 18;
            }
            else if (letter[i].equalsIgnoreCase("T") || letter[i].equalsIgnoreCase("t"))
            {
                frame[i] = 19;
            }
            else if (letter[i].equalsIgnoreCase("U") || letter[i].equalsIgnoreCase("u"))
            {
                frame[i] = 20;
            }
            else if (letter[i].equalsIgnoreCase("V") || letter[i].equalsIgnoreCase("v"))
            {
                frame[i] = 21;
            }
            else if (letter[i].equalsIgnoreCase("W") || letter[i].equalsIgnoreCase("w"))
            {
                frame[i] = 22;
            }
            else if (letter[i].equalsIgnoreCase("X") || letter[i].equalsIgnoreCase("x"))
            {
                frame[i] = 23;
            }
            else if (letter[i].equalsIgnoreCase("Y") || letter[i].equalsIgnoreCase("y"))
            {
                frame[i] = 24;
            }
            else if (letter[i].equalsIgnoreCase("Z") || letter[i].equalsIgnoreCase("z"))
            {
                frame[i] = 25;
            }
            else if (letter[i].equalsIgnoreCase("."))
            {
                frame[i] = 26;
            }
            else if (letter[i].equalsIgnoreCase(":"))
            {
                frame[i] = 27;
            }
            else if (letter[i].equalsIgnoreCase(" "))
            {
                frame[i] = 28;
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
            else
            {
                //if you put what we dont have on the system we draw an empty frame
                //frame[i] = 28;  
                
                //no i now decide to draw ...
            	frame[i] = 8; 
            }
          
          
        }

        //kill 10 Mosquitoes in one run
    }

    public void Draw(Canvas spriteBatch)
    {
        update();

        for (int i = 0; i < frame.length; i++)
        {
            //Doing other processing stuffs
            ABCD.Draw(spriteBatch, (int)X, (int)Y, frame[i]);


            //When we get to a space we check if the next word wont be too long for the line
            if (frame[i] == 28)//frame[i] == 26
            {
                int wordlenght = 1; //THIS IS USED TO KNOW THE LENGHT WE ALL READY HAVE
                int wordsSpace = iWidth(13);
                //int MaxLine this is to know the max sentence we want on a line
                // GAP character spacing
                //we are checking for the next 26 in the line
                for (int j = i + 1; j < frame.length; j++)
                {


                    if (frame[j] == 28 )//frame[j] == 26 //this is supposed to know when spacing 26 is space frame and so is 28
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


