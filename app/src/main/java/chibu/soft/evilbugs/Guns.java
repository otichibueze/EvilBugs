package chibu.soft.evilbugs;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Guns 
{
	  private final int gameGuns = 5;

      public boolean[] PurchasedID;
      public boolean[] EquipedID;
      public int[] DamagedID;
      public int[] RateOfFireID;
      public int[] MaxAmmoID;
      public int[] AmmoLevel;
      public int[] CostOfAmmoID;
      public int[] GunCostID;
      public int[] FrameID;
      public int[] BulletRound;
      public boolean[] UnLimitedAmmo;
      public int[] unlimitedCost;
      public Vector2[] leftButtons;
      public Vector2[] RightButtons;
      public String[] GunNames;
      public int[] BarrelLenght;
      public int[] Bullets;


      private Sprite GunsBg;
      private Sprite GunsImage;
      private Vector2[] GunPositions;
      private Sprite GunButtons;
      private Sprite Arrows;
      private Vector2 leftArrow, RightArrow;


      //Gun info stuffs
      private Sprite Guninfo;
      private Sprite ProgressBars;
      private Sprite CoinNumbers;
      private Sprite coins;
      private Sprite buybtn, buyAmmo, buyUnlimitedAmmo, MoreCoins;

     public int TotalCoins = 2000;// 100750;

      //for Gun Ammo on screen Update
     public int GunRounds;
     public  int    LoadedBullets;
     public boolean bulletEmpty = false;
     public Sprite Rounds, RoundsNum, Empty;
     public int emptyCounter = 0;

     Letters iletter;

     public boolean fwdClick, buyClick, reload;  //used to know when forward or back click is pressed

      int page = 0;
      final int page1 = 0, page2 = 6, page3 = 12;


      public int selID = 20, PresentID = 0;

      private int coincounter = 0, coinframe = 0;


      //Show More Coins
      public boolean moreCoins = false;

      private Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
     // private int arrowCounter = 0;
      

      public Guns(Resources	 game,Vector2 mCanvas)            
      {

          page = page1;
      
          CreatePurchasedID(20, true); //this to create new values 
          CreateEquipedID(20, true);
          CreateDamagedID();
          CreateRateOfFireID();
          CreatePresentAmmo();
          CreateMaxAmmoID();
          CreateBulletRounds();
          CreateCostOfAmmoID();
          CreateGunCostID();
          CreateFrameID();
          CreateLeftButtons();
          CreateRightButtons();
          CreateGunPositions();
          CreatedUnlimitedAmmo();
          CreateUnlimitedCost();
          CreateGunNames();
          CreateBarrelLenght();
          CreateBullets();

         

          GunsBg = new Sprite(game,mCanvas, R.drawable.gunshopscreen, 0f, 0f);
          GunsImage = new Sprite(game, mCanvas,  R.drawable.gunssprites, 264, 140);//264 by 140 is the sprite image dimension
          GunButtons = new Sprite(game, mCanvas,  R.drawable.gunstatic2, 110, 50);
          Arrows = new Sprite(game, mCanvas,  R.drawable.arrows, 58, 58);

          leftArrow = new Vector2(2, 420);
          RightArrow = new Vector2(740, 420);

          //Info stuff
          Guninfo = new Sprite(game, mCanvas,  R.drawable.guninfo, 0f, 0f);  //this is the info backgroundg
          ProgressBars = new Sprite(game, mCanvas,  R.drawable.levelbar5, 104, 20);
          CoinNumbers = new Sprite(game, mCanvas,  R.drawable.coinnumbers, 28, 28);  //PosAvail(634,72) Price (334,292)
          coins = new Sprite(game, mCanvas,  R.drawable.coin, 50, 50); //pos1 (300,288) Pos2 (600, 72)

          buyAmmo = new Sprite(game, mCanvas,  R.drawable.buyammobutton, 310f, 355f);
          MoreCoins = new Sprite(game, mCanvas,  R.drawable.morecoinsbutton, 594f, 22f);
          buybtn = new Sprite(game, mCanvas,  R.drawable.buybutton, 344f, 338f);
          buyUnlimitedAmmo = new Sprite(game, mCanvas,  R.drawable.unlimitedammo, 254f, 402f);
          //

          Rounds = new Sprite(game, mCanvas,  R.drawable.rbullet, 220f, 5f);
          Empty = new Sprite(game, mCanvas,  R.drawable.empty, 220f, 5f);
          RoundsNum = new Sprite(game, mCanvas,  R.drawable.roundsnumb, 30, 30);//36 34
         SetGunRounds(PresentID);

          iletter = new Letters(game, mCanvas, "abcd", 300f, 50f,  R.drawable.alphabet2, 18, 24); //Loading for the letter class for writing Gun Names


          
      }

      public void update(int GameState,boolean showGun, Vector2 touchPosition)
      {
          if (GameState == gameGuns || showGun == true)
          {
              TouchGuns(touchPosition);             
          }

          if (GameState == gameGuns && selID != 20)
          {
              //This updates the with the name to write
              iletter.update(GunNames[selID], iWidth(300f), iHeight(50f));

          }
        
      }

      public void Draw(Canvas spriteBatch)
      {
          GunsBg.Draw(spriteBatch, GunsBg.location, paint);//* 0.8f

          for (int i = 0; i < leftButtons.length; i++)
          {
              int b = i + page;


              GunsImage.Draw(spriteBatch, (int)GunPositions[i].X, (int)GunPositions[i].Y, FrameID[b]);


              //This is to know wheater to draw gun equiped or not
              if (PurchasedID[b] == true && EquipedID[b] == true)
              {
                  GunButtons.Draw(spriteBatch, (int)RightButtons[i].X, (int)RightButtons[i].Y, 0);
              }
              else if (PurchasedID[b] == true && EquipedID[b] == false)
              {
                  GunButtons.Draw(spriteBatch, (int)RightButtons[i].X, (int)RightButtons[i].Y, 1);
              }
              else
              {
                  GunButtons.Draw(spriteBatch, (int)RightButtons[i].X, (int)RightButtons[i].Y, 2);
              }

              //This is used to draw the static buttons on the left
              GunButtons.Draw(spriteBatch, (int)leftButtons[i].X, (int)leftButtons[i].Y, 3);

              //arrowCounter += 1;
              //if (arrowCounter <= 20)
              //{
                  if (page == page1)
                  {
                      Arrows.Draw(spriteBatch, (int)RightArrow.X, (int)RightArrow.Y, 1);
                  }
                  else if (page == page2)
                  {
                      Arrows.Draw(spriteBatch, (int)leftArrow.X, (int)leftArrow.Y, 0);

                      Arrows.Draw(spriteBatch, (int)RightArrow.X, (int)RightArrow.Y, 1);
                  }
                  else if (page == page3)
                  {
                      Arrows.Draw(spriteBatch, (int)leftArrow.X, (int)leftArrow.Y, 0);

                      // Arrows.Draw(spriteBatch, (int)RightArrow.X, (int)RightArrow.Y, 1);
                  }
              //}
              //else if (arrowCounter >= 30)
              //{
              //    arrowCounter = 0;
              //}
              //arrowCounter = 0;

            

          }

          if (selID != 20)
          {
             
                  coincounter += 1;
                  if (coincounter == 5)//5
                  {
                      if (coinframe < 3) coinframe += 1;
                      else if (coinframe == 3) coinframe = 0;
                      coincounter = 0;
                  }
            

              Guninfo.Draw(spriteBatch, Guninfo.location); //Draws the gun frame
              //GunNames.Draw(spriteBatch, (int)GunNames.x, (int)GunNames.y, selID); //Draws the name of the gun

              GunsImage.Draw(spriteBatch, iWidth(260), iHeight(56), FrameID[selID]);
             

              //GunPics.Draw(spriteBatch, (int)GunPics.x, (int)GunPics.y, selID);// Draws the image of the gun

              //Drawing gun indicator
              int Dx = iWidth(400);
              if (DamagedID[selID] <= 20) ProgressBars.Draw(spriteBatch, Dx, iHeight(180), 0);
              else if (DamagedID[selID] > 20 && DamagedID[selID] < 40) ProgressBars.Draw(spriteBatch, Dx, iHeight(180), 1);
              else if (DamagedID[selID] >= 40 && DamagedID[selID] < 60) ProgressBars.Draw(spriteBatch, Dx, iHeight(180), 2);
              else if (DamagedID[selID] >= 60 && DamagedID[selID] < 80) ProgressBars.Draw(spriteBatch, Dx, iHeight(180), 3);
              else if (DamagedID[selID] >= 80) ProgressBars.Draw(spriteBatch, Dx, iHeight(180), 4);


              //Drawing gun indicator for rate of fire
              if (RateOfFireID[selID] <= 0) ProgressBars.Draw(spriteBatch, Dx, iHeight(205), 5);
              else if (RateOfFireID[selID] > 0 && RateOfFireID[selID] < 5) ProgressBars.Draw(spriteBatch, Dx, iHeight(205), 6);
              else if (RateOfFireID[selID] >= 5 && RateOfFireID[selID] < 10) ProgressBars.Draw(spriteBatch, Dx, iHeight(205), 7);
              else if (RateOfFireID[selID] >= 10 && RateOfFireID[selID] < 15) ProgressBars.Draw(spriteBatch, Dx, iHeight(205), 8);
              else if (RateOfFireID[selID] >= 15) ProgressBars.Draw(spriteBatch, Dx,iHeight(205), 9);


              //Drawing gun indicator for rate of fire
              if (MaxAmmoID[selID] <= 20) ProgressBars.Draw(spriteBatch, Dx, iHeight(230), 10);
              else if (MaxAmmoID[selID] >= 20 && MaxAmmoID[selID] < 40) ProgressBars.Draw(spriteBatch, Dx,iHeight(230), 11);
              else if (MaxAmmoID[selID] >= 40 && MaxAmmoID[selID] < 60) ProgressBars.Draw(spriteBatch, Dx,iHeight(230), 12);
              else if (MaxAmmoID[selID] >= 60 && MaxAmmoID[selID] < 80) ProgressBars.Draw(spriteBatch, Dx, iHeight(230), 13);
              else if (MaxAmmoID[selID] >= 80) ProgressBars.Draw(spriteBatch, Dx, iHeight(230), 14);

              //Ammo Level
              if (AmmoLevel[selID] <= MaxAmmoID[selID] * 0.2) ProgressBars.Draw(spriteBatch, Dx, iHeight(255), 15);
              else if (AmmoLevel[selID] > MaxAmmoID[selID] * 0.2 && AmmoLevel[selID] < MaxAmmoID[selID] * 0.4) ProgressBars.Draw(spriteBatch, Dx,iHeight(255), 16);
              else if (AmmoLevel[selID] >= MaxAmmoID[selID] * 0.4 && AmmoLevel[selID] < MaxAmmoID[selID] * 0.6) ProgressBars.Draw(spriteBatch, Dx,iHeight(255), 17);
              else if (AmmoLevel[selID] >= MaxAmmoID[selID] * 0.6 && AmmoLevel[selID] < MaxAmmoID[selID] * 0.8) ProgressBars.Draw(spriteBatch, Dx, iHeight(255), 18);
              else if (AmmoLevel[selID] >= MaxAmmoID[selID] * 0.8) ProgressBars.Draw(spriteBatch, Dx, iHeight(255), 19);


              if (PurchasedID[selID] == false)
              {
                  CoinNumbers.setLocation(332, 292);
                  DrawScore(CoinNumbers,spriteBatch, GunCostID[selID], iWidth(18)); //Draw price for gun cost

                  buybtn.Draw(spriteBatch, buybtn.location);

                  coins.Draw(spriteBatch, iWidth(290), iHeight(282), coinframe);
              }
              else if (PurchasedID[selID] == true)
              {  
              
                  // Draw buy ammo button when ammo is less than 10percent
                  if ((int)MaxAmmoID[selID] * 0.2 > AmmoLevel[selID] && UnLimitedAmmo[selID] == false)
                  {
                      buyAmmo.Draw(spriteBatch, buyAmmo.location);
                      coins.Draw(spriteBatch, iWidth(290), iHeight(282), coinframe); //Spinning coin

                      CoinNumbers.setLocation(332, 292);
                      DrawScore(CoinNumbers, spriteBatch, CostOfAmmoID[selID], iWidth(18)); //Draw price for gun cost
                  }
                  else if ((int)MaxAmmoID[selID] * 0.2  < AmmoLevel[selID] && UnLimitedAmmo[selID] == false)
                  {
                      //draw buy unlimited ammo and price
                      buyUnlimitedAmmo.Draw(spriteBatch, buyUnlimitedAmmo.location);
                      coins.Draw(spriteBatch, iWidth(290), iHeight(282), coinframe); //Spinning coin

                      CoinNumbers.setLocation(332, 292);
                      DrawScore(CoinNumbers, spriteBatch, unlimitedCost[selID], iWidth(18)); //Draw price for unlimited Ammo
                  }
                
              }

              //This draws the gun name
              iletter.update(GunNames[selID], iWidth(300f), iHeight(50f));
              iletter.Draw(spriteBatch);
             
              MoreCoins.Draw(spriteBatch, MoreCoins.location);
              CoinNumbers.setLocation(628, 70);
              DrawScore(CoinNumbers,spriteBatch, TotalCoins, iWidth(18)); //Draw total coins Guncoins
              coins.Draw(spriteBatch, iWidth(588), iHeight(60), coinframe);  //Top coin frame


              //if (moreCoins == true)
              //{
              //    Morepage.Draw(spriteBatch, 0f, 0f);

              //    DrawScore(spriteBatch, iMetre, CostCoins[0], 25, 200f, 66f);
              //    DrawScore(spriteBatch, iMetre, CostCoins[1], 25, 200f, 136f);
              //    DrawScore(spriteBatch, iMetre, CostCoins[2], 25, 200f, 206f);
              //    DrawScore(spriteBatch, iMetre, CostCoins[3], 25, 200f, 276f);
              //    DrawScore(spriteBatch, iMetre, CostCoins[4], 25, 200f, 346f);

              //}

          }
      }

      private void TouchGuns(Vector2 touchPosition)
      {
          if (moreCoins == false)
          {
              if (selID == 20)
              {
                  for (int i = 0; i < leftButtons.length; i++)
                  {
                      int j = i + page;// We used this because for other pages

                      if (touchPosition.X >= leftButtons[i].X &&
                        touchPosition.X < leftButtons[i].X + iWidth(110) &&
                        touchPosition.Y >= leftButtons[i].Y &&
                        touchPosition.Y < leftButtons[i].Y + iHeight(50)) //Note 28 and 28 is the height and width of the sprite button
                      {
                          if (selID == 20)
                          {
                              selID = j;  //this tells when statics is touched

                              fwdClick = true;
                              break;

                          }
                          //   return;
                      }

                      else if (touchPosition.X >= RightButtons[i].X &&
                         touchPosition.X < RightButtons[i].X + iWidth(110) &&
                         touchPosition.Y >= RightButtons[i].Y &&
                         touchPosition.Y < RightButtons[i].Y + iHeight(50)) //Note 27 and 80 is the height and width of the sprite button
                      {
                          if (EquipedID[j] == true && PurchasedID[j] == true)
                          {
                              if (selID == 20)
                              {
                                  CreateEquipedID(j, false);  //This tells when equiped is false
                                  //MyGun.EquipedID[i] = false;
                                  fwdClick = true;
                                  break;

                              }

                          }
                          else if (EquipedID[j] == false && PurchasedID[j] == true)
                          {
                              if (selID == 20)
                              {
                                  CreateEquipedID(j, true);
                                  PresentID = j;  //This code is used to equip gun
                                  SetGunRounds(PresentID);
                                  fwdClick = true; //used to know when to play click sound
                                  break;
                                  // return ;
                              }

                          }
                          else if (PurchasedID[j] == false)
                          {
                              if (selID == 20)
                              {
                                  selID = j;
                                  fwdClick = true; //used to know when to play click sound
                                  break;
                                  //return ;
                              }
                          }

                          // return true;
                      }
                      //   return;
                  }
              }

              //When selid is = 20 its show we are still in the first background
              if (selID == 20)
              {
                  if (page == page2 || page == page3)
                  {
                      if (touchPosition.X >= leftArrow.X &&
                               touchPosition.X < leftArrow.X + Arrows.Texture2DWidth &&
                               touchPosition.Y >= leftArrow.Y &&
                               touchPosition.Y < leftArrow.Y + Arrows.Texture2DHeight)
                      {
                          if (page == page2)
                          {
                              page = page1;
                          }
                          else if (page == page3)
                          {
                              page = page2;
                          }

                          fwdClick = true; //used to know when to play click sound
                      }
                  }


                  if (page == page1 || page == page2)
                  {
                      if (touchPosition.X >= RightArrow.X &&
                              touchPosition.X < RightArrow.X + Arrows.Texture2DWidth &&
                              touchPosition.Y >= RightArrow.Y &&
                              touchPosition.Y < RightArrow.Y + Arrows.Texture2DHeight)
                      {
                          if (page == page1)
                          {
                              page = page2;
                          }
                          else if (page == page2)
                          {
                              page = page3;
                          }
                          fwdClick = true; //used to know when to play click sound
                      }
                  }
              }


              if (selID != 20 && PurchasedID[selID] == false)
              {
                  if (touchPosition.X >= buybtn.location.X &&
                        touchPosition.X < buybtn.location.X + buybtn.Texture2DWidth &&
                        touchPosition.Y >= buybtn.location.Y &&
                        touchPosition.Y < buybtn.location.Y + buybtn.Texture2DHeight)
                  {
                      if (TotalCoins >= GunCostID[selID])
                      {
                          buyClick = true;
                          TotalCoins -= GunCostID[selID];
                          PurchasedID[selID] = true;
                          CreateEquipedID(selID, true);
                          PresentID = selID;
                          SetGunRounds(PresentID);
                      }
                      else
                      {
                          //get more coins if you do not have coins to buy gun
                          moreCoins = true;
                      }
                      //  return ;

                  }
              }

              else if (selID != 20 && PurchasedID[selID] == true)
              {
                  if (touchPosition.X >= buyAmmo.location.X &&
                        touchPosition.X < buyAmmo.location.X + buyAmmo.Texture2DWidth &&
                        touchPosition.Y >= buyAmmo.location.Y &&
                        touchPosition.Y < buyAmmo.location.Y + buyAmmo.Texture2DHeight)
                  {
                      if ((int)MaxAmmoID[selID] * 0.2 > AmmoLevel[selID] && TotalCoins >= CostOfAmmoID[selID] && UnLimitedAmmo[selID] == false)
                      {
                          buyClick = true;
                          TotalCoins -= CostOfAmmoID[selID];
                          AmmoLevel[selID] = MaxAmmoID[selID];
                          SetGunRounds(PresentID);
                      }
                      else if (TotalCoins < CostOfAmmoID[selID] && UnLimitedAmmo[selID] == false)
                      {
                          //Ammo full
                          //Get More coin plus internet or not internet
                          moreCoins = true;
                      }

                  }
                  else if (touchPosition.X >= buyUnlimitedAmmo.location.X &&
                       touchPosition.X < buyUnlimitedAmmo.location.X + buyUnlimitedAmmo.Texture2DWidth &&
                       touchPosition.Y >= buyUnlimitedAmmo.location.Y &&
                       touchPosition.Y < buyUnlimitedAmmo.location.Y + buyUnlimitedAmmo.Texture2DHeight)
                  {
                      if ((int)MaxAmmoID[selID] * 0.2 < AmmoLevel[selID] && TotalCoins >= unlimitedCost[selID] && UnLimitedAmmo[selID] == false)
                      {
                          buyClick = true;
                          TotalCoins -= unlimitedCost[selID];
                          AmmoLevel[selID] = MaxAmmoID[selID];
                          UnLimitedAmmo[selID] = true;
                          //SetGunRounds(PresentID);
                      }
                      else if (TotalCoins < (CostOfAmmoID[selID] * 25))
                      {
                          //Ammo full
                          //Get More coin plus internet or not internet
                          moreCoins = true;
                      }

                  }

              }


              if (touchPosition.X >= MoreCoins.location.X &&
                        touchPosition.X < MoreCoins.location.X + MoreCoins.Texture2DWidth &&
                        touchPosition.Y >= MoreCoins.location.Y &&
                        touchPosition.Y < MoreCoins.location.Y + MoreCoins.Texture2DHeight)
              {
                  moreCoins = true;
                  
              }

          
          }

          ////When game in play and you want to see guns
          //if (showGuns == false)
          //{
            
          //}
      
         //return false;
      }

      public void SetGunRounds(int ID)
      {
        double  iGunRounds;
        LoadedBullets = 0;
        if (MaxAmmoID[ID] == AmmoLevel[ID]) iGunRounds = (AmmoLevel[ID] / BulletRound[ID]) - 1;
        else iGunRounds = (AmmoLevel[ID] / BulletRound[ID]);

        iGunRounds = (int)(iGunRounds);
        if (iGunRounds <= 0)
        {
            GunRounds = 0;
            if (AmmoLevel[ID] % BulletRound[ID] > 0)
            {
                LoadedBullets = AmmoLevel[ID] % BulletRound[ID];
            }
            else if (AmmoLevel[ID] > 0)
            {
                LoadedBullets = BulletRound[ID];
            }
            AmmoLevel[ID] = LoadedBullets;
        }
        else 
        {
            GunRounds = (int)iGunRounds;
            if (AmmoLevel[ID] % BulletRound[ID] > 0)
            {
                LoadedBullets = AmmoLevel[ID] % BulletRound[ID];
            }
            else if(AmmoLevel[ID] > 0)
            {
                LoadedBullets = BulletRound[ID];
            }
           // LoadedBullets = BulletRound[ID]; //Amount of bullets loaded @ a time is called rounds
        }
       
          if (AmmoLevel[ID] > 1) bulletEmpty = false;  //This makes the shooting enabled when player buys ammo

          if (AmmoLevel[PresentID] == 0 || AmmoLevel[PresentID] < 0) bulletEmpty = true;
      }

      public void UpdateRound()
      {
          int ID = PresentID;

          //this is for bullets
          //GunRounds = MyGun.MaxAmmoID[ID] / MyGun.BulletRound[ID];
          //LoadedBullets = MyGun.BulletRound[ID]; //Amount of bullets loaded @ a time is called rounds

          if (UnLimitedAmmo[ID] == false && AmmoLevel[PresentID] > 0)
          {
              if (LoadedBullets > 0)
              {
                  LoadedBullets -= 1;  //bullet minus one
                  AmmoLevel[PresentID] -= 1;
              }
              else if (LoadedBullets == 0)
              {
                  if (GunRounds > 0)//|| && AmmoLevel[PresentID] > 0
                  {
                      GunRounds -= 1;
                      LoadedBullets = BulletRound[PresentID];
                      reload = true;
                  }
                  else if (GunRounds == 0 && AmmoLevel[PresentID] > 0)
                  {
                      GunRounds = 0;

                      if (AmmoLevel[ID] % BulletRound[ID] > 0)
                      {
                          LoadedBullets = AmmoLevel[ID] % BulletRound[ID];
                      }
                      else if (AmmoLevel[ID] > 0)
                      {
                          LoadedBullets = BulletRound[ID];
                      }
                      // LoadedBullets = AmmoLevel[PresentID];
                  }     
              }
          }

        
          if (AmmoLevel[PresentID] == 0 || AmmoLevel[PresentID] < 0) bulletEmpty = true;
      }

      private void DrawScore(Sprite numbers, Canvas spriteBatch, Integer score, Integer Spacing)
      {

          String stScore = score.toString();
          Integer scoreint;
          // Converts the score to a string

          for (int i = 0; i < stScore.length(); i++)
          {
        	  
        	  scoreint = Integer.parseInt(stScore.substring(i, i + 1));

              numbers.SetFrame(scoreint);

              if (i > 0)
              {

                  numbers.Draw(spriteBatch, (int)(numbers.location.X + (Spacing * i)), (int)numbers.location.Y, scoreint);
              }
              else
              {

                  numbers.Draw(spriteBatch, (int)numbers.location.X, (int)numbers.location.Y, scoreint);
              }
          }
      }

      void DrawScore(Canvas spriteBatch, Sprite numbers, Integer score, int Spacing, float Mx, float My)
      {

          String stScore = score.toString();
          Integer scoreint;
          // Converts the score to a string

          for (int i = 0; i < stScore.length(); i++)
          {

        	   scoreint = Integer.parseInt(stScore.substring(i,i + 1));

              numbers.SetFrame(scoreint);

              if (i > 0)
              {
                  numbers.Draw(spriteBatch, (int)(Mx + (Spacing * i)), (int)My, scoreint);
              }
              else
              {

                  numbers.Draw(spriteBatch, (int)Mx, (int)My, scoreint);

              }
          }
      }

      public void CreatePurchasedID(int ID,boolean value)
      {
          if (ID > 18)
          {
              PurchasedID = new boolean[18];

              PurchasedID[0] = true;
              PurchasedID[1] = true;
              PurchasedID[2] = false;
              PurchasedID[3] = false;
              PurchasedID[4] = false;
              PurchasedID[5] = false;
              PurchasedID[6] = false;
              PurchasedID[7] = false;
              PurchasedID[8] = false;
              PurchasedID[9] = false;
              PurchasedID[10] = false;
              PurchasedID[11] = false;
              PurchasedID[12] = false;
              PurchasedID[13] = false;
              PurchasedID[14] = false;
              PurchasedID[15] = false;
              PurchasedID[16] = false;
              PurchasedID[17] = false;
          }
          else
          {
              PurchasedID[ID] = value;
          }
      }

      public void CreateEquipedID(int ID, boolean value)
      {
          if (ID > 18)
          {
              EquipedID = new boolean[18];

              EquipedID[0] = true;
              EquipedID[1] = false;
              EquipedID[2] = false;
              EquipedID[3] = false;
              EquipedID[4] = false;
              EquipedID[5] = false;
              EquipedID[6] = false;
              EquipedID[7] = false;
              EquipedID[8] = false;
              EquipedID[9] = false;
              EquipedID[10] = false;
              EquipedID[11] = false;
              EquipedID[12] = false;
              EquipedID[13] = false;
              EquipedID[14] = false;
              EquipedID[15] = false;
              EquipedID[16] = false;
              EquipedID[17] = false;
          }
          else
          {
              for (int i = 0; i < EquipedID.length; i++)
              {
                  EquipedID[i] = false;
              }
              EquipedID[ID] = value;
          }
      }

      private void CreateDamagedID()
      {
          DamagedID = new int[18];

          DamagedID[0] = 20;
          DamagedID[1] = 20;
          DamagedID[2] = 30;
          DamagedID[3] = 35;
          DamagedID[4] = 30;
          DamagedID[5] = 40;
          DamagedID[6] = 55;
          DamagedID[7] = 55;
          DamagedID[8] = 55;
          DamagedID[9] = 55;
          DamagedID[10] = 65;
          DamagedID[11] = 55;
          DamagedID[12] = 60;
          DamagedID[13] = 65;
          DamagedID[14] = 60;
          DamagedID[15] = 110;
          DamagedID[16] = 110;
          DamagedID[17] = 110;
      }

      private void CreateRateOfFireID()
      {
          RateOfFireID = new int[18];

          RateOfFireID[0] = 6;
          RateOfFireID[1] = 1;
          RateOfFireID[2] = 1;
          RateOfFireID[3] = 1;
          RateOfFireID[4] = 9;
          RateOfFireID[5] = 8;
          RateOfFireID[6] = 10;
          RateOfFireID[7] = 8;
          RateOfFireID[8] = 12;
          RateOfFireID[9] = 14;
          RateOfFireID[10] = 8;
          RateOfFireID[11] = 12;
          RateOfFireID[12] = 10;
          RateOfFireID[13] = 8;
          RateOfFireID[14] = 8;
          RateOfFireID[15] = 8;
          RateOfFireID[16] = 8;
          RateOfFireID[17] = 8;
      }

      private void CreateBulletRounds()
      {
          BulletRound = new int[18];

          BulletRound[0] = 6;
          BulletRound[1] = 8;
          BulletRound[2] = 9;
          BulletRound[3] = 10;
          BulletRound[4] = 18;
          BulletRound[5] = 7;
          BulletRound[6] = 16;
          BulletRound[7] = 20;
          BulletRound[8] = 8;
          BulletRound[9] = 5;
          BulletRound[10] = 9;
          BulletRound[11] = 7;
          BulletRound[12] = 20;
          BulletRound[13] = 20;
          BulletRound[14] = 10;
          BulletRound[15] = 1;
          BulletRound[16] = 1;
          BulletRound[17] = 1;

          //note you divide max ammo by rounds to get the number of rounds then
          //then rounds is the number of bullets loaded before a reload
          //note reload should have an image expression note this for your artist
      }

      private void CreatePresentAmmo()
      {
          AmmoLevel = new int[18];

          AmmoLevel[0] = 48;
          AmmoLevel[1] = 24;
          AmmoLevel[2] = 36;
          AmmoLevel[3] = 40;
          AmmoLevel[4] = 72;
          AmmoLevel[5] = 28;
          AmmoLevel[6] = 98;
          AmmoLevel[7] = 80;
          AmmoLevel[8] = 64;
          AmmoLevel[9] = 45;
          AmmoLevel[10] = 72;
          AmmoLevel[11] = 63;
          AmmoLevel[12] = 100;
          AmmoLevel[13] = 100;
          AmmoLevel[14] = 70;
          AmmoLevel[15] = 18;
          AmmoLevel[16] = 10;
          AmmoLevel[17] = 24;

      }

      private void CreateMaxAmmoID()
      {
          MaxAmmoID = new int[18];

          MaxAmmoID[0] = 48;
          MaxAmmoID[1] = 24;
          MaxAmmoID[2] = 36;
          MaxAmmoID[3] = 40;
          MaxAmmoID[4] = 72;
          MaxAmmoID[5] = 28;
          MaxAmmoID[6] = 98;
          MaxAmmoID[7] = 80;
          MaxAmmoID[8] = 64;
          MaxAmmoID[9] = 45;
          MaxAmmoID[10] = 72;
          MaxAmmoID[11] = 63;
          MaxAmmoID[12] = 100;
          MaxAmmoID[13] = 100;
          MaxAmmoID[14] = 70;
          MaxAmmoID[15] = 18;
          MaxAmmoID[16] = 10;
          MaxAmmoID[17] = 24;
      }

      private void CreateCostOfAmmoID()
      {
          CostOfAmmoID = new int[18];

          CostOfAmmoID[0] = 10;
          CostOfAmmoID[1] = 15;
          CostOfAmmoID[2] = 20;
          CostOfAmmoID[3] = 30;
          CostOfAmmoID[4] = 40;
          CostOfAmmoID[5] = 50;
          CostOfAmmoID[6] = 75;
          CostOfAmmoID[7] = 90;
          CostOfAmmoID[8] = 80;
          CostOfAmmoID[9] = 110;
          CostOfAmmoID[10] = 310;
          CostOfAmmoID[11] = 310;
          CostOfAmmoID[12] = 299;
          CostOfAmmoID[13] = 299;
          CostOfAmmoID[14] = 180;
          CostOfAmmoID[15] = 230;
          CostOfAmmoID[16] = 285;
          CostOfAmmoID[17] = 270;
      }

      private void CreateGunCostID()
      {
          GunCostID = new int[18];

          GunCostID[0] = 7000;
          GunCostID[1] = 7500;
          GunCostID[2] = 10250;
          GunCostID[3] = 15000;
          GunCostID[4] = 18050;
          GunCostID[5] = 13900;
          GunCostID[6] = 10500;
          GunCostID[7] = 13500;
          GunCostID[8] = 9200;
          GunCostID[9] = 11300;
          GunCostID[10] = 12050;
          GunCostID[11] = 9600;
          GunCostID[12] = 13400;
          GunCostID[13] = 14900;
          GunCostID[14] = 16500;
          GunCostID[15] = 18900;
          GunCostID[16] = 17800;
          GunCostID[17] = 19200;
      }

      private void CreateFrameID()
      {
          FrameID = new int[18];

          FrameID[0] = 0;
          FrameID[1] = 1;
          FrameID[2] = 2;
          FrameID[3] = 3;
          FrameID[4] = 4;
          FrameID[5] = 5;
          FrameID[6] = 6;
          FrameID[7] = 7;
          FrameID[8] = 8;
          FrameID[9] = 9;
          FrameID[10] = 10;
          FrameID[11] = 11;
          FrameID[12] = 12;
          FrameID[13] = 13;
          FrameID[14] = 14;
          FrameID[15] = 15;
          FrameID[16] = 16;
          FrameID[17] = 17;
      }

      private void CreateLeftButtons()
      {
          leftButtons = new Vector2[6];

          leftButtons[0] = new Vector2(46, 235);
          leftButtons[1] = new Vector2(282, 235);
          leftButtons[2] = new Vector2(526, 235);
          leftButtons[3] = new Vector2(46, 386);
          leftButtons[4] = new Vector2(282, 386);
          leftButtons[5] = new Vector2(526, 386);

          //leftButtons = new Vector2[18];

          //leftButtons[0] = new Vector2(2, 50);
          //leftButtons[3] = new Vector2(2, 130);
          //leftButtons[6] = new Vector2(2, 210);
          //leftButtons[9] = new Vector2(2, 290);
          //leftButtons[12] = new Vector2(2, 370);
          //leftButtons[15] = new Vector2(2, 450);
          //leftButtons[1] = new Vector2(262, 50);
          //leftButtons[4] = new Vector2(262, 130);
          //leftButtons[7] = new Vector2(262, 210);
          //leftButtons[10] = new Vector2(262, 290);
          //leftButtons[13] = new Vector2(262, 370);
          //leftButtons[16] = new Vector2(262, 450);
          //leftButtons[2] = new Vector2(522, 50);
          //leftButtons[5] = new Vector2(522, 130);
          //leftButtons[8] = new Vector2(522, 210);
          //leftButtons[11] = new Vector2(522, 290);
          //leftButtons[14] = new Vector2(522, 370);
          //leftButtons[17] = new Vector2(522, 450);
      }

      private void CreateRightButtons()
      {
          RightButtons = new Vector2[6];

          RightButtons[0] = new Vector2(168, 235);
          RightButtons[1] = new Vector2(408, 235);
          RightButtons[2] = new Vector2(648, 235);
          RightButtons[3] = new Vector2(168, 386);
          RightButtons[4] = new Vector2(408, 386);
          RightButtons[5] = new Vector2(648, 386);



          //RightButtons = new Vector2[18];

          //RightButtons[0] = new Vector2(170, 50);
          //RightButtons[3] = new Vector2(170, 130);
          //RightButtons[6] = new Vector2(170, 210);
          //RightButtons[9] = new Vector2(170, 290);
          //RightButtons[12] = new Vector2(170, 370);
          //RightButtons[15] = new Vector2(170, 450);
          //RightButtons[1] = new Vector2(430, 50);
          //RightButtons[4] = new Vector2(430, 130);
          //RightButtons[7] = new Vector2(430, 210);
          //RightButtons[10] = new Vector2(430, 290);
          //RightButtons[13] = new Vector2(430, 370);
          //RightButtons[16] = new Vector2(430, 450);
          //RightButtons[2] = new Vector2(690, 50);
          //RightButtons[5] = new Vector2(690, 130);
          //RightButtons[8] = new Vector2(690, 210);
          //RightButtons[11] = new Vector2(690, 290);
          //RightButtons[14] = new Vector2(690, 370);
          //RightButtons[17] = new Vector2(690, 450);
      }

      private void CreateGunPositions()
      {
          GunPositions = new Vector2[6];

          GunPositions[0] = new Vector2(32,137);
          GunPositions[1] = new Vector2(272,137);
          GunPositions[2] = new Vector2(513,137);
          GunPositions[3] = new Vector2(32,272);
          GunPositions[4] = new Vector2(272,272);
          GunPositions[5] = new Vector2(513,272);

      }

      private void CreatedUnlimitedAmmo()
      {
          UnLimitedAmmo = new boolean[18];

          UnLimitedAmmo[0] = false;
          UnLimitedAmmo[1] = true;
          UnLimitedAmmo[2] = false;
          UnLimitedAmmo[3] = false;
          UnLimitedAmmo[4] = false;
          UnLimitedAmmo[5] = false;
          UnLimitedAmmo[6] = false;
          UnLimitedAmmo[7] = false;
          UnLimitedAmmo[8] = false;
          UnLimitedAmmo[9] = false;
          UnLimitedAmmo[10] = false;
          UnLimitedAmmo[11] = false;
          UnLimitedAmmo[12] = false;
          UnLimitedAmmo[13] = false;
          UnLimitedAmmo[14] = false;
          UnLimitedAmmo[15] = false;
          UnLimitedAmmo[16] = false;
          UnLimitedAmmo[17] = false;
      }

      private void CreateUnlimitedCost()
      {
          unlimitedCost = new int[18];

          unlimitedCost[0] = 11200;
          unlimitedCost[1] = 12000;
          unlimitedCost[2] = 16400;
          unlimitedCost[3] = 24000;
          unlimitedCost[4] = 28880;
          unlimitedCost[5] = 22240;
          unlimitedCost[6] = 16800;
          unlimitedCost[7] = 21600;
          unlimitedCost[8] = 14720;
          unlimitedCost[9] = 18080;
          unlimitedCost[10] = 19280;
          unlimitedCost[11] = 15360;
          unlimitedCost[12] = 21440;
          unlimitedCost[13] = 23840;
          unlimitedCost[14] = 26400;
          unlimitedCost[15] = 30240;
          unlimitedCost[16] = 28480;
          unlimitedCost[17] = 30720;

          //Divife by 10 times 16 we get value
      }

      private void CreateGunNames()
      {
          GunNames = new String[18];

          GunNames[0] = "Light.Machine.Gun.";
          GunNames[1] = "Revolver.";
          GunNames[2] = "10.guage.SuperShot.";
          GunNames[3] = "12.guage.SuperShot.";
          GunNames[4] = "ZRU.12.Advanced.";
          GunNames[5] = "Police.Model.500.";
          GunNames[6] = "C.16.Auto.";
          GunNames[7] = "AR.19.Basic.";
          GunNames[8] = "MP.924.";
          GunNames[9] = "Desert.Hawk.";
          GunNames[10] = "B.19.";
          GunNames[11] = "Hp.k5.";
          GunNames[12] = "M30.schmeizer.";
          GunNames[13] = "k7.yugoslavian.";
          GunNames[14] = "Browning.M1919.";
          GunNames[15] = "M31.Devastator.";
          GunNames[16] = "Mortara.M75.";
          GunNames[17] = "kasa.55.";


//          1..Light Machine Gun
//2..Revolver
//3..10 guage SuperShot
//4..12 guage SuperShot
//5..ZRU 12 Advanced
//6..Police Model 500
//7..C 16 Auto
//8..AR 19 Basic
//9..MP 924
//10..Desert Hawk
//11..B 19
//12..Hp k5
//13..M30 schmeizer
//14..k7 yugoslavian
//15..Browning M1919
//16..M31 Devastator
//17..Mortara M75
//18..kasa 55

      }

      private void CreateBarrelLenght()
      {
          //We create and store the barrel lenght of each gun
          BarrelLenght = new int[18];

          BarrelLenght[0] = iWidth(118);
          BarrelLenght[1] = iWidth(22);
          BarrelLenght[2] = iWidth(110);
          BarrelLenght[3] = iWidth(68);
          BarrelLenght[4] = iWidth(66);
          BarrelLenght[5] = iWidth(60);
          BarrelLenght[6] = iWidth(122);
          BarrelLenght[7] = iWidth(86);
          BarrelLenght[8] = iWidth(84);
          BarrelLenght[9] = iWidth(114);
          BarrelLenght[10] = iWidth(124);
          BarrelLenght[11] = iWidth(118);
          BarrelLenght[12] = iWidth(110);
          BarrelLenght[13] = iWidth(114);
          BarrelLenght[14] = iWidth(112);
          BarrelLenght[15] = iWidth(98);
          BarrelLenght[16] = iWidth(98);
          BarrelLenght[17] = iWidth(112);


      }

      private void CreateBullets()
      {
          Bullets = new int[18];

          Bullets[0] = 3;// ;// "bullet3";
          Bullets[1] = 2;// "bullet2";
          Bullets[2] = 3;//"bullet3";
          Bullets[3] = 3;//"bullet3";
          Bullets[4] = 2;//"bullet2";
          Bullets[5] = 2;//"bullet2";
          Bullets[6] = 3;//"bullet3";
          Bullets[7] = 2;//"bullet2";
          Bullets[8] = 2;//"bullet2";
          Bullets[9] = 3;//"bullet3";
          Bullets[10] = 3;//"bullet3";
          Bullets[11] = 2;//"bullet2";
          Bullets[12] = 2;//"bullet2";
          Bullets[13] = 2;//"bullet2";
          Bullets[14] = 1;// "bullet1";
          Bullets[15] = 1;// "bullet1";
          Bullets[16] = 1;// "bullet1";
          Bullets[17] = 3;//"bullet3";

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
