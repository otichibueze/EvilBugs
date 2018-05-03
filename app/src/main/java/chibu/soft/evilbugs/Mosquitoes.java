package chibu.soft.evilbugs;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Mosquitoes extends Sprite
{
	public int Speed;     
    public float MX = 0, MY = 0;
    public int Type;

    public boolean isLife;
    public int life; 
    public int Pframe;
    public float groundY = 0;
    public boolean m_Hit = false;
    private int b_Counter = 0;
    public int bframe = 0;  //this is used to draw blood on bugs and change frame

    //used for explosion
   public int explodeframe = 0;
   public float explodeX = 0, explodeY = 0;
   public boolean explode = false, soundExplode = false;

    public int mframe;

    private boolean hitGround = false;
    private boolean FallMove = false;

    public boolean dust = false;

    private boolean MosMoveX = false, MosMoveY = false;
    private boolean flyingM = false;
    public int myflip;
    private int WaitMove = 0;// counter = 0;
    private Vector2 targetPos;
    private int W_Counter;
    private Vector2 explorer;
    public int bgyGap;  //This used to know the max height bugs can fly to
    //private int akRange;
    private int RangeCounter;
    public boolean caution, Attack;  //eattacked is to show explorer was hit by bug
    public int damageImapct = 0; //This will determine the amount damage the bug will have on the explorer
    public int exframe = 0, eAttacked = 0; //This is used to for exclamation frame ready to show attack
    public boolean killrecord = false;
    private float attackX = 0, attackY = 0;
    private int cautionMeter = 0, AttackerMeter = 0;

    public boolean thunder = false;

    private boolean leftfall, rightfall, middlefall, hitleft, hitright;

    //public Boolean ExplorerHit = false;

    public boolean Ghit, Bhit; //this is used to kow when bug hits bullet or ground


//    public Mosquitoes(Vector2 canvas, Bitmap image, int type, int spriteWidth, int spriteHeight, float gY,
//        Vector2 explorer, float X, float Y,int groundgap,boolean isAlife,int frame)
    public Mosquitoes(Vector2 canvas, Bitmap image,Bitmap imagefliped, int type, int spriteWidth, int spriteHeight, float gY,
             float X, float Y,int groundgap,boolean isAlife,int frame)
        
    {
    	super (canvas, image,imagefliped, spriteWidth, spriteHeight);
        //Note the base means and inheriting from the main class sprite and call the contructor that takes these parameters
        //And also i can access all the values that are public in this class sprite

        this.mframe = frame;
        this.isLife = isAlife;  //when you add a bug is alife datss why its true
        this.life = 100;      //the bugs life bar is 100
       this.Type = type;     //this tell you the type of bug added type 1 mosquitoes 2 wasp 3 bettle
       //this.explorer = explorer;
       this.MX = X;
       this.MY = Y;
       this.groundY = gY;
       //this.bg_speed = bgspeed;
       setLocation(X, Y);

        this.targetPos = new Vector2();
        this.W_Counter = 30 + (int)(Math.random() * ((50 - 30))); //mrand.Next(30, 90);
        bgyGap = groundgap;
        this.Speed = iWidth(15) + iWidth((int)(Math.random() * ((21 - 15))));// mrand.Next(15, 21);

                   //int randomInt = randomGenerator.nextInt(100);
        // int type = tStart + (int)(Math.random() * ((tStop - tStart)));
    }

    public void MosquitoesDraw(Canvas spritebatch, int bgY)
    {
       // Draw(spritebatch, (int)MX , (int)MY - bgY, mframe, myflip);
    	 float angle = (float)Math.toDegrees(1.571f);
    	 
        if (hitleft == true)
        {
        	
            Draw(spritebatch, mframe, 1, angle, new Vector2(MX  , MY  - bgY,false), new Vector2(80, 60,false), 1 );//SpriteEffects.FlipVertically

            //new Vector2((double)MX + (Texture2DWidth / 2), MY + Texture2DHeight - bgY,false)
        }
        else if (hitright == true)
        {
            Draw(spritebatch, mframe, 1, angle, new Vector2(MX  , MY - bgY,false), new Vector2(80, 60,false), 0);//SpriteEffects.None
            //1.571f,,,,,,,,,,,4.712f
        }
        else
        {
            Draw(spritebatch, (int)MX, (int)MY - bgY, mframe, myflip);
        }
        //   GunsHand.Draw(spriteBatch, MyGun.PresentID, 1, AngleRadians, new Vector2(GunsHand.x + 65 , (GunsHand.y + 17 ) - bg_Y), new Vector2(65, 17), SpriteEffects.None);
    }

    public void ScreenUpdate(double gameTime, float bgspeed, int MySpriteEffect)
    {
       
        myflip = MySpriteEffect;
        
        float a = MY;

        if (myflip == 1)// SpriteEffects.FlipHorizontally
        {
            MX += gameTime * bgspeed;
           
        }
        else if (myflip == 0)//SpriteEffects.None
        {
            MX -= gameTime * bgspeed;
               
        }

        counter += 1;

        if (counter == 2)
        {
            if (mframe < 1) mframe += 1;
            else if (mframe >= 1 || mframe == 4) mframe = 0;
        }
        else if (counter > 2) { counter = 0; }
    }

    public void update(Double gameTime, List<Bullet> mbullets, int bgY, Vector2[] Myground, 
        Vector2[] groundsize, float bgspeed, Vector2 Explorer, int meters, int Damage,int BulletType)
    {
       // W_Counter = mrand.Next(30, 90);
       // Speed = mrand.Next(2, 8);

        //gWidth = groundWidth;
        //gHeight = groundHeight;
        this.explorer = Explorer;

        if (thunder == false)
        {
            counter += 1;

           // if (counter == 1)
           // {
                if (isLife == true)
                {
                    if (Attack == false)
                    {
                        if (mframe < 1) mframe += 1;
                        else if (mframe >= 1 || mframe == 4) mframe = 0; //frame 4 is the frame shown went shot
                    }
                    else if (Attack == true)
                    {
                        if (mframe <= 2) mframe += 1;
                        else if (mframe == 3 || mframe == 4) mframe = 2; //frame 4 is the frame shown went shot
                    }
                }
                else if (explode == true)
                {
                    mframe = 6; //when its shot with exploding bullets the bugs looks burnt
                }
                else mframe = 5;//3  This changes the frameto the dead frame
           // }
           // else if (counter > 1) { counter = 0; }

    

            if (m_Hit == true && isLife == true)//
            {
                //junk code but it is used to change fame back when bug is hit by bullet
                b_Counter += 1;
                if (bframe < 2 && b_Counter == 2)//5
                {
                    bframe += 1;
                    b_Counter = 0;
                }
                else if (bframe == 2 && b_Counter == 2)//5
                {
                    bframe = 0;
                    m_Hit = false;
                    b_Counter = 0;
                }

                mframe = 4;//2  //This changes the frame to the shoot frame

                MY -= iHeight(3); //this makes the bug look as if his falling down

                if (MY <= groundY) MY += iHeight(5);

               


            }
        }
        else if(isLife == true)
        {
            //THUNDER STRIKE
           
                counter += 1;

                if (counter == 2)
                {
                    MY -= 3; //this makes the bug look as if his falling down and being shocked

                    if (MY <= groundY) MY += iHeight(5);

                    mframe -= 1;

                    if (mframe == 6)
                    {
                        isLife = false;
                        life = 0;
                    }
                }
                else if (counter > 2) { counter = 0; }
            
           

        }

      if(isLife == true)  mShotDown(mbullets, Damage, BulletType); //This is used to check if the mosquitoes is shot using list of bullets 

        if (explode == true)
        {
            //if (explodeframe < 10) iexplode += 1;
            //if (iexplode == 5)
            //{
                if (explodeframe < 10)//i choose 10 so that wg=hen it get to 10 it will stop drawing the frame in game1
                {
                    explodeframe += 1;
                }

            //    iexplode = 0;
            //}
        }

        //This is used to know decide how life bar would go down
        if (life < 100 && life >= 80)
        	Pframe = 0;
        else if (life < 80 && life >= 60)
        	Pframe = 1;
        else if (life < 60 && life >= 40)
        	Pframe = 2;
        else if (life < 40 && life >= 20)
        	Pframe = 3;
        else if (life <= 20) 
        	Pframe = 4;
       


        if (isLife == true)
        {
            
            if (WaitMove < W_Counter && flyingM == false) WaitMove += 1;

            else if (flyingM == false && WaitMove >= W_Counter)
            {
                flyingM = true;
                //targetPos.Y = mrand.Next(320, 520); 

                if (Attack == false)
                {
                    targetPos.Y = bgY + (int)(Math.random() * (((bgY - bgyGap) + EvilBugsView.deviceHeight - bgY)));// mrand.Next(bgY, (bgY - bgyGap) + EvilBugsView.deviceHeight);   //This choose the random hieght the bugs would fly to
                    targetPos.X = (Explorer.X + iWidth(130)) + (int)(Math.random() * ((EvilBugsView.deviceWidth + iWidth(30) - (Explorer.X + iWidth(130))))); //mrand.Next((int)(Explorer.X + 130), EvilBugsView.deviceWidth + 30);    //This choose the random width the bugs would fly to
                    MosMoveX = true; MosMoveY = true;
                } 
                else if(Attack == true)
                {
                    targetPos.Y = explorer.Y + (int)attackY; // -30;   //This choose the random hieght the bugs would fly to
                    targetPos.X = explorer.X + (int)attackX; // 80;    //This choose the random width the bugs would fly to
                    MosMoveX = true; MosMoveY = true;

                }
               // MosPos = new Vector2(MX, MY);
            }
             if (flyingM == true)
            {
                flyingShip(targetPos, new Vector2(MX, MY,false));
            }
        
        }
        else if (isLife == false)
        {
           // Dead(gameTime);
           
            if (hitGround == false )
            {
                
                MY += iHeight(15);  //this controls fall speed when the bug is dead
                MX -= gameTime * bgspeed;// 4;
                

                if (hitGround == false)
                {
                    for (int i = 0; i < Myground.length; i++)
                    {
                    	if(Myground[i] != null)
                    	{
                        GroundCheck(Myground[i], groundsize[i].X, groundsize[i].Y);
                    	}

                    }
                    calfall();
                }
                
               
            }

            else if (hitGround == true )// || FallMove == true
            {
              //  calfall();
                if (hitleft == false || hitright == false)
                {
                    MX -= gameTime * bgspeed;// bg_speed;
                }
                else
                {
                    MY += iHeight(5);  //this controls fall speed when the bug is dead
                    MX -= gameTime * bgspeed;// 4;
                }

                
                //explorer.y = Mground[i].floorY - explorer.Texture2DHeight - 10;
                
            }
          
        }

        if (life < 0) isLife = false;

        BugAttack(meters);  //This codes choose when the bug would attack....Meter is used to know how nugs would attack

         if (Attack == true) Attacked(); //This method is called when attack is true to know when explorer is touched
    }

    private void BugAttack(int Meters)
    {
        if (AttackerMeter == 0)
        {
            //This is used to know when bugs would attack based on meters ran
            if (Meters < 100)
            {
                AttackerMeter =200 + (int)(Math.random() * ((400 - 100))); //mRan.Next(200, 600);
                cautionMeter = AttackerMeter - 200;
                damageImapct = 6;
                Speed = iWidth(20) + iWidth((int)(Math.random() * ((20 - 15)))); //mRan.Next(15, 20);//6, 14
            }
            else if(Meters >= 100 && Meters < 400)
            {
                AttackerMeter =100 + (int)(Math.random() * ((300 - 200))); // mRan.Next(200, 500);
                cautionMeter = AttackerMeter - 200;
                damageImapct = 8;
                Speed = iWidth(25) + iWidth((int)(Math.random() * ((10)))); // mRan.Next(16, 20);
            }
            else if (Meters >= 400 && Meters < 800)
            {
                AttackerMeter =100 + (int)(Math.random() * ((200 - 100))); // mRan.Next(200, 400);
                cautionMeter = AttackerMeter - 200;
                damageImapct = 8;
                Speed = iWidth(25) + iWidth((int)(Math.random() * ((12)))); //mRan.Next(17, 20);
            }
            else if (Meters >= 800 && Meters < 1400)
            {
                AttackerMeter =100 + (int)(Math.random() * ((200 - 100))); // mRan.Next(100, 300);
                cautionMeter = AttackerMeter - 100;
                damageImapct = 8;
                Speed = iWidth(30) + iWidth((int)(Math.random() * ((12)))); // mRan.Next(15, 25);
            }
            else if (Meters >= 1400 && Meters < 2000)
            {
                AttackerMeter =100 + (int)(Math.random() * ((200 - 100)));// mRan.Next(100, 300);
                cautionMeter = AttackerMeter - 100;
                damageImapct = 8;
                Speed = iWidth(30) + iWidth((int)(Math.random() * ((15))));//mRan.Next(15, 25);
            }
            else if (Meters >= 2000 && Meters < 3000)
            {
                AttackerMeter = 100 + (int)(Math.random() * ((200 - 100)));//mRan.Next(100, 300);
                cautionMeter = AttackerMeter - 100;
                damageImapct = 8;
                Speed = iWidth(30) + iWidth((int)(Math.random() * ((15))));//mRan.Next(17, 25);
            }
            else if (Meters >= 3000 && Meters < 4000)
            {
                AttackerMeter = 50 + (int)(Math.random() * ((150 - 50)));//mRan.Next(50, 250);
                cautionMeter = AttackerMeter - 50;
                damageImapct = 8;
                Speed = iWidth(30) + iWidth((int)(Math.random() * ((18))));//mRan.Next(20, 25);
            }
            else if (Meters >= 4000)
            {
                AttackerMeter = 50 + (int)(Math.random() * ((100 - 50)));//mRan.Next(50, 200);
                cautionMeter = AttackerMeter - 20;
                damageImapct = 10;
                Speed = iWidth(30) + iWidth((int)(Math.random() * ((20))));//mRan.Next(20, 31);//14,20
            }
            
          


            //new code to choose explorer attack position

            //int attackPosition = mRan.Next(1, 5);
            int attackheight = - iHeight(50) + iHeight((int)(Math.random() * ((94 + 50))));//mRan.Next(-50, 94);

            attackX = iWidth(90);
            attackY = attackheight;

            //if (attackPosition == 1)
            //{
            //    attackX = 100; //Note 100 is half of explorer height
            //    attackY = -72; //72
            //}
            //else if (attackPosition == 2)
            //{
            //    attackX = 100;//111
            //    attackY = -42; //-42
            //}
            //else if (attackPosition == 3)
            //{
            //    attackX = 100;
            //    attackY = 12;
            //}
            //else if (attackPosition == 4)
            //{
            //    attackX = 100;
            //    attackY = 64;
            //}
          
        }


        else if (life > 0 && AttackerMeter > 1)
        {
          
                RangeCounter += 1;//some coding here

                if (RangeCounter >= cautionMeter && RangeCounter < AttackerMeter)
                {
                    caution = true;
                   // damageImapct = 10;  //might remove code

                    //This is used to know exclamation frame to be used out of 3 frames
                    if (RangeCounter > cautionMeter + 40) exframe = 1;
                    else if (RangeCounter > cautionMeter + 80) exframe = 2;
                }

                if (RangeCounter >= AttackerMeter)
                {
                    caution = false;
                    Attack = true;
                    eAttacked = 0;
                   // Speed = 10;                     


                    //This is used to know exclamation frame to be used out of 3 frames
                    if (RangeCounter > AttackerMeter + 40) exframe = 2;
                    else if (RangeCounter - 40 > AttackerMeter + 80) exframe = 1;
                    else exframe = 0;
                }
            }


            if (Attack == true && RangeCounter > (AttackerMeter + 100))
            {
                Attack = false;
               // Speed = mRan.Next(2, 8); //this chooses a random speed after the bug has attacked
                RangeCounter = 0;
                AttackerMeter = 0;

            }
        
        else if (life == 0 || life < 0)
        {
            Attack = false;
            caution = false;
        }
      
    }

    private void flyingShip(Vector2 targetPos, Vector2 MosquitoesPos)
    {
        if (targetPos.X < MosquitoesPos.X)
        {
            myflip = 0;///SpriteEffects.None

            if (MosquitoesPos.X - targetPos.X >= Speed) MosquitoesPos.X -= Speed;
            else if (MosquitoesPos.X - targetPos.X < Speed)
            {
                MosquitoesPos.X = targetPos.X;
                MosMoveX = false;
            }
        }

        else if (targetPos.X > MosquitoesPos.X)
        {
            myflip = 1;//SpriteEffects.FlipHorizontally

            if (targetPos.X - MosquitoesPos.X >= Speed) MosquitoesPos.X += Speed;
            else if (targetPos.X - MosquitoesPos.X < Speed)
            {
                MosquitoesPos.X = targetPos.X;
                MosMoveX = false;
            }

        }


        if (targetPos.Y < MosquitoesPos.Y)
        {

            if (MosquitoesPos.Y - targetPos.Y >= Speed) MosquitoesPos.Y -= Speed;
            else if (MosquitoesPos.Y - targetPos.Y < Speed)
            {
                MosquitoesPos.Y = targetPos.Y;
                MosMoveY = false;
            }


        }
        else if (targetPos.Y > MosquitoesPos.Y)
        {


            if (targetPos.Y - MosquitoesPos.Y >= Speed) MosquitoesPos.Y += Speed;
            else if (targetPos.Y - MosquitoesPos.Y < Speed)
            {

                MosquitoesPos.Y = targetPos.Y;
                MosMoveY = false;
            }
        }


        if (targetPos.Y == MosquitoesPos.Y || MosquitoesPos.Y + 2 == targetPos.Y || MosquitoesPos.Y - 2 == targetPos.Y) MosMoveY = false;
        if (targetPos.X == MosquitoesPos.X || MosquitoesPos.X + 2 == targetPos.X || MosquitoesPos.X - 2 == targetPos.X) MosMoveX = false;

        MX = MosquitoesPos.X; MY = MosquitoesPos.Y;

        if (MosMoveX == false && MosMoveY == false)
        {
            flyingM = false;
            WaitMove = 0;

            //To make Mosquitoes face Running explorer
            if (MX > explorer.X) myflip = 0;//SpriteEffects.None
            else if (MX < explorer.X) myflip = 1;//SpriteEffects.FlipHorizontally
            //
        }
    }

    private void mShotDown(List<Bullet> mybullet,int Damage,int bulletType)
    {
        for (int i = 0; i < mybullet.size(); i++)
        {
           if(mybullet.get(i) != null)
           {
            if (mybullet.get(i).Bulletx + iWidth(20) >= MX + iWidth(30) &&  //15
                mybullet.get(i).Bulletx <= (MX + iWidth(30 + 82)) &&
                (mybullet.get(i).Bullety + iHeight(5) >= MY  + iHeight(42) &&  //14
                        mybullet.get(i).Bullety <= MY + iHeight(42 + 62)))
            {
                m_Hit = true;
                Bhit = true; //this is used to know when to play sound when bug hits bullet
                if (isLife == true) life -= Damage;
                if (life < 0)
                	isLife = false;

                mybullet.get(i).Hit = true;

                if (bulletType == 1)
                {
                    explode = true;
                    soundExplode = true;
                    explodeX = MX;
                    explodeY = MY;
                }
            }

            //note create a type to know damage value so bug checks type of gun and minus damage value
           }
        }
           



        //Note 15 and 14 is height and width of of the bullets 
        //Note 82 and 42 is height and width of inner box while X + 36, Y + 58 is the rythming positions for the bugs

       
    }

    //private void TouchedExplorer(Vector2 Explorer)
    //{
    //    if (Explorer.X + 98 >= MX &&
    //           Explorer.X <= (MX + Texture2DWidth) &&
    //           (Explorer.Y + 300 >= MY &&
    //                   Explorer.Y <= MY + Texture2DHeight))
    //    {

    //        ExplorerHit = true;
    //    }
    //}


    private void checkGround(Vector2 myground,float Mwidht,float Mheight)
    {

        //ground = myground;
    	
        if (myground.X + Mwidht - iWidth(20)>= MX + iWidth(60) &&   // + 70
                myground.X <= (MX + iWidth(60 + 20)) &&  //36
                (myground.Y - iHeight(20) + Mheight >= MY + iHeight(70) && // + 70
                        myground.Y - iHeight(20) <= MY + iHeight(70 + 14)))//14
        {
            if (MY > myground.Y + iHeight(5))
            {
                FallMove = true;
            }
            else
            {
               // MY = myground.Y - 20;
                hitGround = true;
                dust = true;
                Ghit = true; //this is used to know when to play sound when bug hits ground
                //play a sound to show bug hit ground
           }
            
        }
        //Note 36 and 14 is width and height of inner box while X + 70, Y + 70 is the rythming positions 
    }

 
    private void GroundCheck(Vector2 myground, float Mwidht, float Mheight)
    {
         //size 18, 94  //left + 24 + 12  //middle + 64 + 12  //Right + 104 + 12

        //left
        if (myground.X + Mwidht >= MX + iWidth(20) &&   // 24 rythming position
                myground.X <= (MX + iWidth(20 + 18)) &&  //18 width
                (myground.Y + Mheight >= MY + iHeight(76) && // 12 rythming position
                        myground.Y - iHeight(20) <= MY + iHeight(76 + 30)))//94 height 
        {
           leftfall = true;

        }

         //middle
        if (myground.X + Mwidht >= MX + iWidth(64) &&   // 24 rythming position
                myground.X <= (MX + iWidth(64 + 18)) &&  //18 width
                (myground.Y + Mheight >= MY + iHeight(76) && // 12 rythming position
                        myground.Y - iHeight(20) <= MY + iHeight(76 + 30)))//94 height 
        {
           middlefall = true;
           //hitGround = true;

        }

        //Right
        if (myground.X + Mwidht >= MX + iWidth(105) &&   // 24 rythming position
                myground.X <= (MX + iWidth(105 + 18)) &&  //18 width
                (myground.Y + Mheight >= MY + 76 && // 12 rythming position
                        myground.Y - iHeight(20) <= MY + iHeight(76 + 30)))//94 height 
        {
           rightfall = true;

        }
       
    }

    private void calfall()
    {
        if (leftfall == true && middlefall == true && rightfall == true)
        {
            hitGround = true;
            dust = true;
            Ghit = true;

        }
        else if (leftfall == true && middlefall == true && rightfall == false || leftfall == true && middlefall == false && rightfall == false)
        {
            hitright = true;

        }
        else if (leftfall == false && middlefall == true && rightfall == true || leftfall == false && middlefall == false && rightfall == true)
        {
            hitleft = true;

        }

    }
     
    private void Attacked()
    {
      // 196, 200  //98,300
        if (explorer.X + iWidth(196) >= MX &&
                 explorer.X <= MX + Texture2DWidth &&
                 (explorer.Y + iHeight(200) >= MY &&
                         explorer.Y <= MY + Texture2DHeight))
        {
           if(eAttacked == 0) eAttacked = 1;
        }

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
