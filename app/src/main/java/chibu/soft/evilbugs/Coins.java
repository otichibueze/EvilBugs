package chibu.soft.evilbugs;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Coins extends Sprite
{
	  
    public float Mx;
    public float My;
    public int type;
    public Boolean touched = false;
    private int coinframe, coincounter = 0;


    public Boolean coinMagnet = false;
    public Boolean imagnet = false;
    Boolean MoveX, MoveY;
    int Speed;
    
    
    //in heritance in java you use extent then after you call method();{ super();  you pass values
    public Coins(Vector2 canvas,int Type,Bitmap Myname,int spriteWidth,int spriteHeight, float X, float Y){
        super (canvas, Myname, spriteWidth, spriteHeight);
    
        this.Mx = X;
        this.My = Y;
        this.type = Type;


        Speed = 18;//9
        //  value = 
    }

    public void Update(Double gameTime, float bgspeed, int bY, Vector2 explorerPosition, int explorerWidth, int explorerHeight)
    {
        Mx -= (int)(gameTime* bgspeed);  //Update coin
        
        //= new ArrayList<Bullet>();i removed from method

        //CoinTouched(pointer, bY);// check if it touches gun

        //i remove this code dont see need why you sued be shoting coins with bullets
       // mShotDown(mbullets); //This is used to check if bullet touches coin

        CoinExplorer(explorerPosition, explorerWidth, explorerHeight, bY); // check if it touches explorer

        coincounter += 1;
        if (coincounter == 4)//8
        {
            if (coinframe < 3) coinframe += 1;
            else if (coinframe == 3) coinframe = 0;
            coincounter = 0;
        }

        if (coinMagnet == true)
        {
            MagnetExplorer(explorerPosition, bY);
        }

        if (imagnet == true)
        {

            Magnet(explorerPosition, new Vector2(Mx,My,false));
        }
    }

    private void mShotDown(List<Bullet> mybullet)
    {
        for (int i = 0; i < mybullet.size(); i++)
        {
            if (type == 2)
            {
                if (mybullet.get(i).Bulletx + iWidth(15) >= Mx &&
                    mybullet.get(i).Bulletx <= (Mx + iWidth(65)) &&
                    (mybullet.get(i).Bullety + iHeight(14) >= My &&
                            mybullet.get(i).Bullety <= My + iHeight(65)))
                {
                    touched = true;
                }
            }
            else
            {

                if (mybullet.get(i).Bulletx + iWidth(15) >= Mx &&
                   mybullet.get(i).Bulletx <= (Mx + iWidth(35)) &&
                   (mybullet.get(i).Bullety + iHeight(14) >= My &&
                           mybullet.get(i).Bullety <= My + iHeight(35)))
                {
                    touched = true;
                }
            }

            //note create a type to know damage value so bug checks type of gun and minus damage value
        }
    }

    private void CoinTouched(Vector2 pointer, int bY)
    {
        float mbY = My - bY;  //We do this to get postion based on the ground
        if (pointer.X + iWidth(36 + 18) >= Mx &&
                 pointer.X + iWidth(18) <= Mx + iWidth(35) &&
                 (pointer.Y + iHeight(32 + 18) >= mbY  &&
                         pointer.Y + iHeight(18) <= mbY + iHeight(35)))
        {
            touched = true;
        }

        //note 35 is width and height of the coin
        //Note 36 and 32 is height and width of inner box while 18 is the rythming positions for Pointer
        //Note 90 and 14 is height and width of inner box while X + 30, Y + 65 is the rythming positions for Pointer

    }


    public void CoinExplorer(Vector2 explorerPosition, int explorerWidth, int explorerHeight, int bY)
    {
        float mbY = My - bY;  //We do this to get postion based on the ground
        if (explorerPosition.X + (explorerWidth / 2)  >= Mx &&
                explorerPosition.X  <= Mx + iWidth(35) &&  //the 50 is to make we add sure the coin coming from behind touches explorer
                (explorerPosition.Y + explorerHeight - bY >= mbY &&
                        explorerPosition.Y - bY <= mbY + iHeight(35)))
        {
            touched = true;

        }

    }


    private void MagnetExplorer(Vector2 explorerPosition, int bY)
    {
        float mbY = My - bY;  //We do this to get postion based on the ground
        int magnetwidth = iWidth(340), magnetheight = iHeight(340);  //340  //this is the size of frame of the magnet 
       // - 250   - 6 this is the corresponding X and Y

        if (explorerPosition.X - iWidth(6) + magnetwidth  >= Mx &&
                explorerPosition.X - iWidth(6) <= Mx + iWidth(35) &&
                (explorerPosition.Y - iHeight(250) + magnetheight - bY >= mbY &&
                        explorerPosition.Y - iHeight(250) - bY <= mbY + iHeight(35)))
        {
            imagnet = true;
        }

    }

    public void CoinDraw(Canvas spritebatch, int bgY)
    {
        Draw(spritebatch, (int)Mx, (int)My - bgY, coinframe);
    }


    private void Magnet(Vector2 targetPos, Vector2 CoinPos)
    {
        if (targetPos.X < CoinPos.X)
        {

            if (CoinPos.X - targetPos.X >= Speed) CoinPos.X -= Speed;
            else if (CoinPos.X - targetPos.X < Speed)
            {
                CoinPos.X = targetPos.X;
                MoveX = false;
            }
        }

        else if (targetPos.X > CoinPos.X)
        {

            if (targetPos.X - CoinPos.X >= Speed) CoinPos.X += Speed;
            else if (targetPos.X - CoinPos.X < Speed)
            {
                CoinPos.X = targetPos.X;
                MoveX = false;
            }

        }


        if (targetPos.Y < CoinPos.Y)
        {

            if (CoinPos.Y - targetPos.Y >= Speed) CoinPos.Y -= Speed;
            else if (CoinPos.Y - targetPos.Y < Speed)
            {
                CoinPos.Y = targetPos.Y;
                MoveY = false;
            }


        }
        else if (targetPos.Y > CoinPos.Y)
        {


            if (targetPos.Y - CoinPos.Y >= Speed) CoinPos.Y += Speed;
            else if (targetPos.Y - CoinPos.Y < Speed)
            {

                CoinPos.Y = targetPos.Y;
                MoveY = false;
            }
        }


        if (targetPos.Y == CoinPos.Y || CoinPos.Y + 2 == targetPos.Y || CoinPos.Y - 2 == targetPos.Y) MoveY = false;
        if (targetPos.X == CoinPos.X || CoinPos.X + 2 == targetPos.X || CoinPos.X - 2 == targetPos.X) MoveX = false;

        Mx = CoinPos.X; My = CoinPos.Y;

        //if (MoveX == false && MoveY == false)
        //{
        //    touched = true;
        //}

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
