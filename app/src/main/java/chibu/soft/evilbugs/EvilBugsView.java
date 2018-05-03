package chibu.soft.evilbugs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
//import android.R.integer;
//import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
//import android.content.ActivityNotFoundException;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
//import android.content.Intent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.widget.EditText;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
//import android.os.Looper;
import android.os.Message;


import com.android.vending.billing.IInAppBillingService;
import chibu.soft.evilbugs.util.IabHelper;
import chibu.soft.evilbugs.util.IabResult;
import chibu.soft.evilbugs.util.Purchase;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.game.Game;


public class EvilBugsView extends SurfaceView implements Callback,
AsyncApp42ServiceApi.App42ScoreBoardServiceListener {
	
    private Context mContext;
    public static EvilBugsThread thread;
	//public static SharedPreferences settings;   //This is basically for load loading data key and value
	public static boolean backbuttonpressed = false;
    private static boolean gamerunning = false;
	public static SurfaceHolder holder;
	//public static Editor editor;   //This is basically for saving data with key and value
	public Resources icontent ;//= mContext.getResources();
    public static Settings settings;

    public static double PositionfactorX, PositionfactorY;
    //public static boolean started;
	
	//in app purchase stuff
		//public IInAppBillingService mService;  //This is in app purchase stuff
		public static IabHelper mHelper;
		public static Activity activity;
		
		 static final String coins15000 = "coins_15000"; //0.99
		 static final String coins57000 = "coins_57000"; //1.99
		 static final String coins120000 = "coins_120000"; //2.99
		 static final String coins270000 = "coins_270000"; //3.99
		 static final String coins600000 = "coins_600000"; //4.99	 
		

	     public boolean igetInventory = false;
	     public static boolean _isStoreEnabled;
	     public int Coins  = 0;
	     public String istatus ="loading.store.";
	     public boolean result = false;
	     public boolean showboard;
	     
	 	// (arbitrary) request code for the purchase flow
		    static final int RC_REQUEST = 10001;

        //this is for payload random string generation used in-app purchase
        private static final char[] symbols = new char[36];
        private final Random random = new Random();
        String Payload = "";

	     
	//leader board stuffs
	private AsyncApp42ServiceApi asyncService;  //this leaderboards works with http://www.shephertz.com/  //http://api.shephertz.com/
	
	private AlertDialog.Builder alert = null;  //this works with the get keypad
	//Resources iContent;
	 //public Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
	
	public static int deviceWidth, deviceHeight;
	
	//Windows advert features
	// DrawableAd drawableAd; //Add stuff
     boolean adEnabled = true;
    // string adunit = "82064";
     //string error = "no.error.yet.";
     //Letters aderror;
	
	  Paint p = new Paint();//(Paint.FILTER_BITMAP_FLAG);
      
      Paint color = new Paint(Paint.FILTER_BITMAP_FLAG);
      
      Paint mpaint = new Paint();//(Paint.FILTER_BITMAP_FLAG);
  	
   
     Vector2 ScreenSize;// = new Vector2(deviceWidth, deviceHeight);

     //bool Exit = false; //i want to use this to control exit
     //Boolean backclick = false;  //i want to use this to control exit

     ///public Settings settings;

    // public purchase Purchase = new purchase();

    public static int GameState = 0;

     int groundcounter = 0;

     //This is used for menu presentation beofre game starts 
     public static final int gameLoading = 0,gamewall = 1,gameStart = 2,gameInplay = 3, gamecredit = 4,gameGuns = 5,gameOver = 6;
//     public final int gamewall = 1;
//     public final int gameStart = 2;
//     public final int gameInplay = 3;
//     public final int gamecredit = 4;
//     public final int gameGuns = 5;
//     public final int gameOver = 6;
  

     Sprite Menus;
     Bitmap[] MenuItem;
     int menuCounter = 0, loaderCounter = 0, iload = 0, iloadframe = 0;
     Sprite Loading;

     int StartMode = 0, StartModeA = 1, StartModeB = 2;

     Sprite fb, arrow, blackbg, cautionsign;//,
     Vector2 startbtn; //This is used to draw start stuffs
     Sprite storebtn, creditbtn, soundenabled, sounddisabled, musicEnabled, musicDisabled, infobtn;

     //Sprite paused, resume, restart, quit, evilbuglogo;//controllerA, controllerB, 
     Sprite pausedScreen, CoinNum, boostNum;
     Vector2 QuitPos, RestartPos, ResumePos, btnSize;

     public static boolean Dpaused = false;
     public static boolean sound = true;

     //bool gameload = false; //This is used to load the game in play

     int cautionframe = 0, cautioncounter = 0;  //This is used to control the frame of the little caution

     Boolean MoveLittleMenu = false;  //This is used to control the little menu on start screen

     //Game in play static
     //Sprite coins;
     Sprite iMeters;//use CoinNum
     Integer metersRan = 0, PreMetre = 0, coinnum = 0, killnum = 0, coincounter = 0, coinframe = 0; //note kill number should be zero in game over we
     //should have total kills since player started playing this game too
     //end 

     Letters aletter, bletter, cletter, mletter, Comletter;
     Sprite mState, Mboard;
     // Sprite valueTex; //this is used to draw value
     int CompletedCounter = 0; //this used to monitor how long completed mission complete will stay on screen

     //Guns Stuff
     public static Guns MyGun;
     Sprite explosion;
     Sprite Reload;
     //End 

     Sprite Guncoins;

     //My guns relatedg
     //Sprite Gunpaper; //This is used while game in play for gun background
     Boolean showGuns = false;
     
     static final int run = 0, fly = 1, flypaddling = 2;
     static int movement = 0;

     //Bullet Stuff
     Sprite GunsHand, GunHandFire;//Gun hand is the gun in the explorers hands and gun fire is another image with fire on his hand

     // int GHframe = 0;
     Vector2 PositionTouched, BulletDirection, TurentPostion; //Pbullet, //know where touch and get distance in striaght line
     Boolean BulletRelease = false;        //Know when to release bullet or not
     Boolean fireshot = false; //This is to know if we would draw the fire icon 
     int BulletCounter = 0;
     float AngleRadians;
     List<Bullet> MyBullets = new ArrayList<Bullet>();// List<Bullet>();
     List<Bullet> mybullets = new ArrayList<Bullet>();

     List<Dust> iDust =  new ArrayList<Dust>();
     List<coinSparkle> icoinsparkle =  new ArrayList<coinSparkle>();

     int magnetframe = 0;
     int magnetCounter = 0;
     Sprite magnetSparkle;
     Boolean showmagnet = false;


     static Sprite explorer;
    // Sprite explorer2;
     Sprite explorerShout;
     int shouting = 0;
     Sprite Jumpbtn;
     //Boolean explorerhG, explorerFall;
     //Sprite ishort, ilong, Mjump; //to be removed later
     //Sprite Mosquitoes;


     Sprite blood, Wblood, Gblood;
     //Boolean m_Hit = false;

     static List<Mosquitoes> myMosquitoes = new ArrayList<Mosquitoes>();

     List<Integer> GroundNames =  new ArrayList<Integer>();

     static List<Integer> floorlevel = new ArrayList<Integer>();
     float gap = 0; Integer floorName ;

     static List<groundObjects> Mground = new ArrayList<groundObjects>();

     Vector2[] Dground;
     Vector2[] GroundSize;

     Boolean jump = false;// Ground = true;
     float jumpVelocity = iHeight(18); //Jump mangitude 18
     float Gravity =  iHeight(1);   //Jump gravity 1
     float fallvelocity =  iHeight(2);

     Sprite black;//Edot, Mfloor;
     //float DayTime = 0f;
     //int time = 0;

    
     //Progress bar
     //int Pframe;
     Sprite PBar, explorerEnergy, Aexclamation, Cexclamation;//Dust,   //Pbar is used for life bar for mosquitoes same as the explorer energy 
     int energycounter = 0;

     int explorerlife = 100, eFrame = 0;
     Boolean Attacked = false; //This boolean is to determine when the explorere is being attacked 

     int frame = 0, counter = 0;//, bframe = 0, b_Counter = 0; // mframe = 0,

     //Pointer
     //Sprite pointer;  //This is done purposely to renovate this game

     //This is used to know the background we are in right now
     static int village = 1;
     static final int bugForest = 1, bugVillage = 2, nBugForest = 3;
     int Time = 0;
     final int Day = 3, Night = 4;

     //This draws main background 
     Sprite bg;
     //List<Sprite> backgrounds;

     //Sprite groundloop;
     Boolean MoveGround = false;

     static int bg_X = 0, bgyGap;  //bgygap is used to fill up the space that was used to jump
     float gd_Y = iHeight(656); // gd_X2 = 0,
     float bg_Y = iHeight(320);

     List<Sprite> forebg_Objects;//bg_Objects,

     // int bgObject = 0;
     //Random Object_Picker = new Random();
     int bgSpeed = iWidth(220);

     // Accelerometer accelerometer;// = new Accelerometer();
     int bugnumber = 1;

     //
     static List<Coins> GCoins;
     //const int GroupA = 0, GroupB = 1, GroupC = 2, GroupD = 3, GroupE = 4, GroupF = 5, GroupG = 6, GroupH = 7, GroupI = 8;
     int CoinGroup = 0;

     Sprite gmOver;  //this is for gameover image
     Vector2 GmOverBtns, HomeBtn, StoreBtn, Playbtn, fbBtn, fbbtnSize;

     Boolean Fgexplorer;

    public static Achievements achievements;

     //transit background
     static Sprite transit;
     static Boolean Intransit = false, bgChanged = false;
     //Boolean bgChanged = false;
     static int startTransit = 700;
     static int nearIntransit = 0;


     //Boost Stuffs
     Boolean BoostClick = true;
     int BoostAvail = 0;
     Boolean Coindoubler = false, CoinMagnet = false, thunder = false;
     static int ACoindoubler = 1, ACoinMagnet = 1, Athunder = 1; //these used to know available in what numbers    //note save these values when writing settings code
     int thundercounter = 0;
     int PCoindoubler = 300, PCoinMagnet = 300, Pthunder = 500;  //price for item
     Sprite icoindouble, iMagnet, ithunder, thunderbtn, SthunderA, SthunderB;
     int fader = 220;

     //Show More Coins
     public boolean morecoins = false;
     Sprite Morepage;
     Sprite Advise;
     //int adviseCounter = 0;
     Vector2 buyBtnSize;
     Vector2[] buyBtnPosition;// = new Vector2[5];
    // int[] CostCoins = new int[5];

     //Memory Management approach
     Bitmap Mosquitoe, wasp, bettle;
     Bitmap Mosquitoefliped, waspfliped, bettlefliped;
     
     Bitmap bigcoin, smallcoin;
     Bitmap dust, dust2;
     Bitmap Spark;
     Bitmap bullet1, bullet2, bullet3;

     //fore background day
     Bitmap Bigtree, tree, trunk;
     //fore background night 
     Bitmap NBigtree, Ntree, Ntrunk;

     //BugVillage forebackground
     Bitmap[] bugForebg;

       //I have not worked on sounds yet ooh
   //Song song;
     private static SoundPool soundPool;
     private static HashMap soundPoolMap ;
     public static float mvolume;
    
     //public static final int Crash = R.raw.kaboom;
     
      static MediaPlayer mp;
     //Sounds
     //Song jungle, BugsBuzz, SingleBuzz;
     public static final int Scoin = R.raw.coin; //sound that plays when the explorer touches a coin
     public static final int Sjump = R.raw.jump;
     public static final int Sland = R.raw.land;//this play when explorer jumps or lands
     public static final int Sbite = R.raw.ahh;
     public static final int Sfall = R.raw.fall; //for falling sound effect
     public static final int Srun = R.raw.run;  //for running footstep sound effect
     public static final int Sthunder = R.raw.thunderstrike;  //thunder sound
    // public static final int Sjungle = R.raw.jungle;
     public static final int Sfwd = R.raw.btnforward;
     public static final int Sback = R.raw.btnback; 
     public static final int Sbuy = R.raw.btnbuy;
     public static final int Mosbuzz = R.raw.mosbuzz;
     public static final int bugbuzz = R.raw.bugbuzz;
     public static final int biteDeath = R.raw.bitedeath;
     public static final int magAttraction = R.raw.magattraction;
     public static final int jethruter = R.raw.air;
     public static final int jetjump = R.raw.jetthruter;
     int Moscount, OtherbugsCount;  //this is used to know when to keep playing buzzing sound effect

     public static final int Sgun0 =  R.raw.g0 ;
     public static final int Sgun1 = R.raw.g1 ;
     public static final int Sgun2 = R.raw.g2 ;
     public static final int Sgun3 = R.raw.g3 ;
     public static final int Sgun4 = R.raw.g4 ;
     public static final int Sgun5 = R.raw.g5 ;
     public static final int Sgun6 = R.raw.g6 ;
     public static final int Sgun7 = R.raw.g7 ;
     public static final int Sgun8 = R.raw.g8 ;
     public static final int Sgun9 = R.raw.g9 ;
     public static final int Sgun10 = R.raw.g10 ;
     public static final int Sgun11 = R.raw.g11 ;
     public static final int Sgun12 = R.raw.g12 ;
     public static final int Sgun13 = R.raw.g13 ;
     public static final int Sgun14 = R.raw.g14 ;
     public static final int Sgun15 = R.raw.g15 ;
     public static final int Sgun16 = R.raw.g16 ;
     public static final int Sgun17 = R.raw.g17 ;
     
     public static final int Sexplode = R.raw.explode;
     public static final int Sreload = R.raw.reload;
     public static final int Sbughit = R.raw.insectscream2;
     public static final int Sbugscream = R.raw.insectghit1;
//     
 //    public SoundEffectInstance
//
//Srun, jethruter,bugbuzz, Mosbuzz ,Sjungle
////     //this is used to loop soundeffects like media

       //The boolean and the static int are used to create sound effect
       //instance
       public static boolean running = true, buzz1 = true, buzz2 = true,thruter = true;
//       public boolean buzz1 = true;
//       public boolean buzz2 = true;
//       public boolean thruter = true;
      // public boolean jungle = true;

     public static int runningID;
     public static int buzz1ID;
     public static int buzz2ID;
     public static int thruterID;
     //public static int jungleID;
//
     public static final int song = R.raw.soundtrack;
     static boolean mediaEnabled = true;

    // Leaderboards leaderboards;
    // Activity activity;// = new Activity();
     final int isavingscore = 0, ieditscore = 1, iTopRank = 2;
     int leadermode = 0;

     public static String apiKey = "634d3439ea36ddd74edf549b96c10b2f86f5bc661390f98e1156d7fc9ba41da6";
     public static String secretKey = "2bdb8d5787e7f01763e1021ef2b54ca312871c2cbc5f901ff1b68ed44b3e5aaf";
     public String gameName = "1";  //"EvilBugsPro";
     public static String userName;
     public static String scoreID; //score ID for saving score


     Sprite btnPrevious, btnNext;
     Vector2[] names;   //this is used to draw name and score
     iLetter leaderwriter;  //for writing highscore name
     int ipage = 8;  //this will determine the numbers per page


     int leaderpage = 0; //this is used to control how we flip the page on highscore
     
   //when we retrive 60 data at once and keep them in verious list below for a particular stage
     //lets just continue test purpose
     List<Integer> lstage;
     List<String> lname;
     List<Double> lscore;
     List<Integer> lid;


     List<leaderboards> myleaderboards; //where we save paramenters

     Sprite btnGlobal;

     //private float deviceHeight, deviceWidth;

     //info stuff
     boolean igetname = false;    		 
     Boolean showInfo = false;
     Boolean failed = false;
     Sprite statusboard;// btnDismmiss;
     String infostatus;
     Letters infoLetters;
     int infocounter = 0;


    //Game Tutor
//     boolean tutor = false, tutorOn = true;
//    int tutormode = 1, tutorbug = 1, tutorjump = 2, tutorthunder = 3, tutorgameInplay = 4;
//    int tutorBugCount = 3, tutorwait = 101, helphandframe = 0;
//    Sprite helphand;
//    Vector2 bugbox, bugboxSize ;
//    float tutorgap = 0;
//    int crazywait = 0;

     boolean tutor = true;
     int tutorPage = 0, tutorPage1 = 0, tutorPage2 = 1;  //old code
     int tutorCounter = 0;  //old code
     Sprite tutorLeft, tutorRight, tutorScreen1, tutorScreen2, btnDismmiss; //old code


     //Rate Us Stuff
     Boolean Rating = false;  //Used disable rating showing
     Boolean rating_Showing = false;  //Used to know when to show or when not to show rate us
     public static Boolean rating_Never = false;
     Sprite rateusbtn;
     Sprite gameOverRateUs;
     Vector2[] gameOverRateUsBtn; 
     
     //New stuffs
     static Sprite flyingShuttle, ShuttleEnergy;
     float ShuttleAngleRadian;
     int ShuttleVelocity ,Shuttlegravity , shuttlehandX , shuttlehandY ;
     static int Shuttleimageindex = 0, Energyframe = 0, EnergyCount = 0;

     static Sprite flyingpad;
     static int padhandX , padhandY ; //these are the ref position of the gun image
     float padAngleRadians, padVelocity, padgravity ;   //velcoity that is gotten when image is flicked and used to move map
     float Friction ;   // apply friction to the velocity to slow the map movement down //0.5
     int padimageindex = 0;


     Sprite woodencrate;
     int crateshow = 50;
     Boolean getcrate = false, cratetouched = false;
     
     

	
	@SuppressLint("NewApi")
	protected void ContentLoad() throws Exception
	    {
		   try{
			   
			   loadSound(mContext); //This is used to load sound into memory
			   
			 //  loadInApp(); //this loads in application purchase
			  
			   icontent = mContext.getResources();

	     
	     deviceHeight = mContext.getResources().getDisplayMetrics().heightPixels;
	     deviceWidth = mContext.getResources().getDisplayMetrics().widthPixels;




            //deviceWidth = display.getWidth();
	     //deviceHeight = display.getHeight();
	     
	     ScreenSize = new Vector2((double)deviceWidth, (double)deviceHeight);

            double widht = deviceWidth;
            double height = deviceHeight;
            PositionfactorY = height / 480; //Note here 480 is the original design screen size Y axis

            PositionfactorX = widht / 800; //Note here 800 is the original design screen size X axis
	     
	   //  activity = new Activity();
	     
	     
	     iLoadContent();
	    //WindowManager mWinMgr = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
	    
	     
	     
		   }
		   catch(Exception ex)
			{
				ex.getMessage().toString();
				System.exit(0); 
			}
	    }

           String generatePayload (int length)
           {
            if (length < 1)
             throw new IllegalArgumentException("length < 1: " + length);

            //here we set the lenght of the payload string
            final char[] buf = new char[length];

            for (int idx = 0; idx < buf.length; ++idx)
             buf[idx] = symbols[random.nextInt(symbols.length)];
            return new String(buf);
           }
	   
	     void iLoadContent()
       {
	    	 
	    	 
	    	 //Set Blue Color
	         color.setColor(Color.WHITE);
	         
	         //Set Blue Color
	         p.setColor(Color.WHITE);
	         
	         //Set Color
	           mpaint.setColor(Color.WHITE);
	        	mpaint.setAlpha(fader);



        //this is for payload random string generation used in-app purchase
        for (int idx = 0; idx < 10; ++idx)
         symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
         symbols[idx] = (char) ('a' + idx - 10);



        //bg_Y = iHeight(320);
//
  //i remove cause we call load once so here we load achievement once//
           //i remove//if (achievements == null)
         //i remove// {
               achievements = new Achievements();
             //i remove//}
         

             //i remove// if (MyGun == null)
             //i remove// {
               MyGun = new Guns(icontent, ScreenSize);
             //i remove// }

           //More coins page stuffs
             //i remove//  if (Morepage == null)
             //i remove//  {
               Morepage = new Sprite(icontent, ScreenSize, R.drawable.morecoins, 0f, 0f);
               iMeters = new Sprite(icontent, ScreenSize, R.drawable.mrannum, 30, 38);

               Advise = new Sprite(icontent, ScreenSize, R.drawable.advise, 118f, 436f);

               buyBtnSize = new Vector2(110, 50); //note this is button size

               buyBtnPosition = new Vector2[5];
               buyBtnPosition[0] = new Vector2(568, 68);
               buyBtnPosition[1] = new Vector2(568, 114);
               buyBtnPosition[2] = new Vector2(568, 212);
               buyBtnPosition[3] = new Vector2(568, 284);
               buyBtnPosition[4] = new Vector2(568, 358);

               black = new Sprite(icontent, ScreenSize, R.drawable.black, 0f, 0f);

               statusboard = new Sprite(icontent, ScreenSize, R.drawable.bigboard2, 140f, 200f);

               infoLetters = new Letters(icontent, ScreenSize, " ", 312, 216, R.drawable.alphabet2, 18, 24);

               //CostCoins[0] = 15000;
               //CostCoins[1] = 57000;
               //CostCoins[2] = 120000;
               //CostCoins[3] = 270000;
               //CostCoins[4] = 600000;
               //i remove// }

        

           
             //i remove// if (GameState == gameLoading || GameState == gamewall || GameState == gameStart)
             //i remove//{
               
               //sound stuff
               //Srun = iContent.Load<SoundEffect>("Sounds\\run");//running sound
               //Sjungle = iContent.Load<SoundEffect>("Sounds\\jungle");//jungle background sound

               //jungle = Sjungle.CreateInstance(); // this is used to create background instance so we can loop the soundeffect

               //if (running == null)
               //{
               //    running = Srun.CreateInstance();
               //    running.IsLooped = true;
              // }

               StartMode = StartModeA;

               bgSpeed = iWidth(300);
               //This here is all about loading the menu
               //Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
              
               
                MenuItem = new Bitmap[4];
                MenuItem[0] = loadsprite(icontent, R.drawable.a);
                MenuItem[1] = loadsprite(icontent, R.drawable.b);
                MenuItem[2] = loadsprite(icontent,  R.drawable.c3a);
                MenuItem[3] = loadsprite(icontent, R.drawable.credits);
//               MenuItem[0] =  BitmapFactory.decodeResource(icontent,  R.drawable.a);
//               MenuItem[1] =  BitmapFactory.decodeResource(icontent, R.drawable.b); 
//               MenuItem[2] =  BitmapFactory.decodeResource(icontent, R.drawable.c3a);
//               MenuItem[3] =  BitmapFactory.decodeResource(icontent, R.drawable.credits);

               rateusbtn = new Sprite(icontent,ScreenSize, R.drawable.rateus , 10f, 370f);

               Menus = new Sprite(ScreenSize, MenuItem, 3, 0, 0, 0);

               Loading = new Sprite(icontent, ScreenSize, R.drawable.loadingicon, 210, 25);
               iload = 250 + (int)(Math.random() * ((500 - 250))); //Object_Picker.Next(250, 500);

               fb = new Sprite(icontent, ScreenSize, R.drawable.facebookicon, 751f, 49f);
               arrow = new Sprite(icontent, ScreenSize, R.drawable.menuarrow, 755f, 388f);
               blackbg = new Sprite(icontent, ScreenSize, R.drawable.blackbg2, 750f, 382f);

               cautionsign = new Sprite(icontent, ScreenSize, R.drawable.startcaution, 62, 55);
               cautionsign.setLocation(new Vector2(728, 350));

               storebtn = new Sprite(icontent, ScreenSize, R.drawable.storebtn, 812f, 384f);
               soundenabled = new Sprite(icontent, ScreenSize, R.drawable.soundenabledbtn, 912f, 384f);
               sounddisabled = new Sprite(icontent, ScreenSize, R.drawable.sounddisabledbtn, 912f, 384f);
               musicEnabled  = new Sprite(icontent, ScreenSize, R.drawable.music, 1012f, 384f);
               musicDisabled  = new Sprite(icontent, ScreenSize, R.drawable.music2, 1012f, 384f);
//               infobtn = new Sprite(icontent, ScreenSize, R.drawable.info, 1112f, 384f);
               creditbtn = new Sprite(icontent, ScreenSize, R.drawable.creditbtn, 1112f, 384f);

               frame = 0;
               explorer = new Sprite(icontent, ScreenSize, R.drawable.full4, R.drawable.full4fliped, 196, 200);  //This loads the explorer sprite
               explorer.setLocation(new Vector2(-200, 210)); //162 (viewportX / 0.2f )
               explorer.SetFrame(frame);
               
               Mosquitoe = loadsprite(icontent, R.drawable.mosquitospritesfull);
               wasp = loadsprite(icontent, R.drawable.waspspritesfull);
               bettle = loadsprite(icontent, R.drawable.beetlespritesfull);
               
               Mosquitoefliped = loadsprite(icontent, R.drawable.mosquitospritesfullfliped);
               waspfliped = loadsprite(icontent, R.drawable.waspspritesfullfliped);
               bettlefliped = loadsprite(icontent, R.drawable.beetlespritesfulfliped);
               
//               Mosquitoeflip = loadsprite(icontent, R.drawable.mosquitospritesfullflip);
//               waspflip = loadsprite(icontent, R.drawable.waspspritesfullflip);
//               bettleflip = loadsprite(icontent, R.drawable.beetlespritesfulflipl);
//               Mosquitoe = BitmapFactory.decodeResource(icontent,R.drawable.mosquitospritesfull);
//               wasp = BitmapFactory.decodeResource(icontent, R.drawable.waspspritesfull);
//               bettle = BitmapFactory.decodeResource(icontent,R.drawable.beetlespritesfull);

               myMosquitoes = new ArrayList<Mosquitoes>();

             
             //i remove// }

             //i remove// else if (GameState == gameGuns)
             //i remove// {
             //i remove//    if (MyGun == null)
             //i remove//   {

             //i remove//      MyGun = new Guns(icontent, ScreenSize);

             //i remove// }
             //i remove// }
           //else
        	//   if (GameState == gameOver)
          // {


               
               gameOverRateUs = new Sprite(icontent, ScreenSize, R.drawable.rateusreminder, 268f, 145f);
 //              black = new Sprite(icontent, ScreenSize,R.drawable.black, 0f, 0f);
               gameOverRateUsBtn = new Vector2[3];
 //              gameOverRateUsBtn[0] = new Vector2(272,150);
 //              gameOverRateUsBtn[1] = new Vector2(272, 220);
 //              gameOverRateUsBtn[2] = new Vector2(272, 290);
//
//              

//
//               if (MyGun == null)
//               {
//                   MyGun = new Guns(icontent, ScreenSize);
//
//               }
//
//                 achievements.SelectStage();
               

//               mState = new Sprite(icontent, ScreenSize, R.drawable.iscompleted, 64, 64);  //this to draw if mission is complete or not
//               //Mboard = new Sprite(this, "bigboard"); 
//
//               aletter = new Letters(icontent, ScreenSize, achievements.istages[0], 120, 162, R.drawable.alphabet1, 22, 26);//162
//               bletter = new Letters(icontent, ScreenSize, achievements.istages[1], 120, 240, R.drawable.alphabet1, 22, 26);//240
//               cletter = new Letters(icontent, ScreenSize, achievements.istages[2], 120, 316, R.drawable.alphabet1, 22, 26);//316
//               //mletter = new Letters(this, "Misson.LeveL." + achievements.missionlevel, 320, 132, "alphabet2", 18, 24);
//               //Comletter = new Letters(this, achievements.mLevels[0], 250, 194, "alphabet1", 22, 26);
//
               gmOver = new Sprite(icontent, ScreenSize, R.drawable.gameoverscreen2);
               
               btnPrevious = new Sprite(icontent, ScreenSize, R.drawable.previousbutton, 544f, 430f);

               btnNext = new Sprite(icontent, ScreenSize, R.drawable.nextbutton, 680f, 425f);
               
               leaderwriter = new iLetter(icontent, ScreenSize, "", 0, 0, R.drawable.alphabet2, 18, 24);
               
               btnGlobal = new Sprite(icontent, ScreenSize,  R.drawable.globalscore, 650f, 104f);//580,92

              // GmOverBtns = new Vector2(150,60);
              // HomeBtn = new Vector2(30, 400);
              // StoreBtn = new Vector2(204, 400);
               //Playbtn = new Vector2(376, 400);
               //fbBtn = new Vector2(54, 32);
              // fbbtnSize = new Vector2(89, 89);

              
     //      }
// i remove//   else
        	// i remove//   {
               //for loading guns in store
        	 //i remove// if (MyGun == null)
        	 //i remove// {
        	 //i remove//    MyGun = new Guns(icontent, ScreenSize);

        	 //i remove// }

        	 achievements.SelectStage();  //this is used to select missions
        	 
        	 iMagnet = new Sprite(icontent, ScreenSize, R.drawable.magneticon, 162f, 68f);
             icoindouble = new Sprite(icontent, ScreenSize, R.drawable.coindoublericon, 354f, 68f);
             ithunder = new Sprite(icontent, ScreenSize, R.drawable.thundericon, 545f, 68f);
             thunderbtn = new Sprite(icontent, ScreenSize, R.drawable.thunderbtn, 16f, 60f);
             SthunderA = new Sprite(icontent, ScreenSize, R.drawable.thunder1, 200f, 0f);
             SthunderB = new Sprite(icontent, ScreenSize, R.drawable.thunder2, 200f, 0f);
               
               startbtn = new Vector2(288, 232);

               //TutorStuffs
               tutorPage = tutorPage1;
               tutorScreen1 = new Sprite(icontent,ScreenSize, R.drawable.tutor1);
               tutorScreen2 = new Sprite(icontent, ScreenSize,  R.drawable.tutor2);

               tutorLeft = new Sprite(icontent, ScreenSize,  R.drawable.left,12f,400f);
               tutorRight = new Sprite(icontent, ScreenSize,  R.drawable.right, 734f, 400f);
               btnDismmiss = new Sprite(icontent, ScreenSize,  R.drawable.dismissbtn, 336f, 426f);

               //   helphand = new Sprite(icontent, ScreenSize,  R.drawable.helphand, 110, 120);


               mState = new Sprite(icontent, ScreenSize,  R.drawable.iscompleted, 64, 64);  //this is used to draw if mission is completed
               Mboard = new Sprite(icontent, ScreenSize,  R.drawable.bigboard);   //this is used to draw completed missions

               aletter = new Letters(icontent, ScreenSize, achievements.istages[0], 250, 180,  R.drawable.alphabet1, 22, 26);  //This draws mission 1//194
               bletter = new Letters(icontent, ScreenSize, achievements.istages[1], 250, 260, R.drawable.alphabet1, 22, 26);  //This draws mission 2//272
               cletter = new Letters(icontent, ScreenSize, achievements.istages[2], 250, 340, R.drawable.alphabet1, 22, 26);  //This draws mission 3// 345
               Integer value = new Integer(achievements.Mission) ;
               mletter = new Letters(icontent, ScreenSize, "Misson." + value.toString() + ".", 320, 132, R.drawable.alphabet2, 18, 24);  //this used to write mission level
               Comletter = new Letters(icontent, ScreenSize, achievements.istages[0], 250, 194, R.drawable.alphabet1, 22, 26);  //this used to write completed mission while playing game

               //paused stuffs
               pausedScreen = new Sprite(icontent, ScreenSize, R.drawable.pausedscreen2);
               QuitPos = new Vector2(62, 404);
               RestartPos = new Vector2(328, 404);
               ResumePos = new Vector2(592, 404);
               btnSize = new Vector2(160, 64);
               CoinNum = new Sprite(icontent, ScreenSize, R.drawable.coinnumbers, 28, 28);
               boostNum = new Sprite(icontent, ScreenSize, R.drawable.boostnum, 20, 28);
               CoinNum.setLocation(638, 28);
              
//               musicEnabled = new Sprite(icontent, ScreenSize, R.drawable.music, 10f, 108f);
//               musicDisabled = new Sprite(icontent, ScreenSize, R.drawable.music2, 10f, 108f);
//               storebtn = new Sprite(icontent, ScreenSize, R.drawable.storebtn, 10f, 202f);  //store button
//               soundenabled = new Sprite(icontent, ScreenSize, R.drawable.soundenabledbtn, 10f, 14f);
//               sounddisabled = new Sprite(icontent, ScreenSize, R.drawable.sounddisabledbtn, 10f, 14f);

             


               Guncoins = new Sprite(icontent, ScreenSize, R.drawable.coin, 50, 50);  //spinning coins
               Guncoins.SetFrame(coinframe);

               
               iMeters.setLocation(new Vector2(614, 8));

             
               //Coins list
               GCoins = new ArrayList<Coins>();

              // bgSpeed = iWidth(220);


               //Boost Stuff
               // icoindouble, iMagnet, ithunder;
//               iMagnet = new Sprite(icontent, ScreenSize, R.drawable.magneticon, 162f, 68f);
//               icoindouble = new Sprite(icontent, ScreenSize, R.drawable.coindoublericon, 354f, 68f);
//               ithunder = new Sprite(icontent, ScreenSize, R.drawable.thundericon, 545f, 68f);
//               thunderbtn = new Sprite(icontent, ScreenSize, R.drawable.thunderbtn, 16f, 60f);
//               SthunderA = new Sprite(icontent, ScreenSize, R.drawable.thunder1, 200f, 0f);
//               SthunderB = new Sprite(icontent, ScreenSize, R.drawable.thunder2, 200f, 0f);

             //  explorer = new Sprite(icontent, ScreenSize, R.drawable.full5, 196, 200);  //This loads the explorer sprite
              // explorer.setLocation(new Vector2(-200, 540)); //550
              // explorer.SetFrame(frame);
               
               flyingShuttle = new Sprite(icontent, ScreenSize, R.drawable.flyingshuttle, 384, 222);
               flyingShuttle.location = new Vector2(48, 468);

               woodencrate = new Sprite(icontent, ScreenSize, R.drawable.crate, 900f, 350f);
               //Loading pad
               flyingpad = new Sprite(icontent, ScreenSize, R.drawable.flyingpad, 384, 222);
               flyingpad.location = new Vector2(28, 478);

               ShuttleEnergy = new Sprite(icontent, new Vector2(800, 480), R.drawable.explorerenergy, 204, 28);
               ShuttleEnergy.setLocation(48, 259);

               explorerShout = new Sprite(icontent, ScreenSize, R.drawable.shout, 0f, 0f);

               magnetSparkle = new Sprite(icontent, ScreenSize, R.drawable.magnetsparkle, 327, 327);


               //black = new Sprite(icontent, ScreenSize, R.drawable.black, 0f, 0f);

               Jumpbtn = new Sprite(icontent, ScreenSize, R.drawable.jumpleaf, 2f, 352f);

               Reload = new Sprite(icontent, ScreenSize, R.drawable.reloadbtn, 20f, 222f);

               GunsHand = new Sprite(icontent, ScreenSize, R.drawable.gunsinhand, 230, 92);  //load hands in explorer hand sprite
               GunsHand.setLocation(new Vector2(explorer.location.X + iWidth(12), explorer.location.Y + iHeight(48),false));
               GunsHand.SetFrame(MyGun.PresentID);

               GunHandFire = new Sprite(icontent, ScreenSize, R.drawable.gunsinhandfire, 230, 92);  //load hands in explorer hand sprite
               GunsHand.setLocation(new Vector2(explorer.location.X + iWidth(12), explorer.location.Y + iHeight(48),false));
               GunsHand.SetFrame(MyGun.PresentID);

               explosion = new Sprite(icontent, ScreenSize, R.drawable.explosionsprite, 128, 128);

               bgyGap = (int)(explorer.location.Y - bg_Y);

               blood = new Sprite(icontent, ScreenSize, R.drawable.bloodsprite1, 160, 120);
               Wblood = new Sprite(icontent, ScreenSize, R.drawable.bloodsprite2, 160, 120);
               Gblood = new Sprite(icontent, ScreenSize, R.drawable.bloodsprite3, 160, 120);


               PBar = new Sprite(icontent, ScreenSize, R.drawable.energybarm, 54, 14);

               Aexclamation = new Sprite(icontent, ScreenSize, R.drawable.exclamationsprites, 31, 58);
               Cexclamation = new Sprite(icontent, ScreenSize, R.drawable.exclamationsprites2, 31, 58);

               explorerEnergy = new Sprite(icontent, ScreenSize, R.drawable.explorerenergy, 204, 28);
               explorerEnergy.setLocation(10, 5);



               //startTransit = iWidth(800)+ iWidth((int)(Math.random() * ((300 - 100)))); // Object_Picker.Next(100, 300);
               //nearIntransit = startTransit - iWidth(340);//it takes about 23 metres to pass one tile
               
               

               if (village == bugForest) bg = new Sprite(icontent, ScreenSize, R.drawable.backgroundloop1);
               else if (village == nBugForest) bg = new Sprite(icontent, ScreenSize, R.drawable.nbgloop);
               else if (village == bugVillage) bg = new Sprite(icontent, ScreenSize, R.drawable.bg1);

               transit = new Sprite(icontent, ScreenSize, R.drawable.treetrunktransition2);//, deviceWidth, deviceHeight
               transit.setLocation( new Vector2(10 + deviceWidth,0,false));
               


               forebg_Objects = new ArrayList<Sprite>();// List<Sprite>();

               myMosquitoes = new ArrayList<Mosquitoes>();

               //Memory Managment approach
               // Texture2D Mosquitoe, wasp, bettle;
               // Texture2D bigcoin, smallcoin;
             //i remove//  Mosquitoe =BitmapFactory.decodeResource(icontent,R.drawable.mosquitospritesfull);// .Load<Texture2D>("mosquito_sprites_full");
               //i remove//   wasp = BitmapFactory.decodeResource(icontent,R.drawable.waspspritesfull);
               //i remove//  bettle = BitmapFactory.decodeResource(icontent,R.drawable.beetlespritesfull);

               bigcoin = loadsprite(icontent, R.drawable.bigcoin);
               smallcoin = loadsprite(icontent, R.drawable.coin);
//               bigcoin = BitmapFactory.decodeResource(icontent,R.drawable.bigcoin);
//               smallcoin = BitmapFactory.decodeResource(icontent,R.drawable.coin);

               dust = loadsprite(icontent, R.drawable.idust2);
               dust2 = loadsprite(icontent, R.drawable.idust);
               Spark = loadsprite(icontent, R.drawable.coinsparkle);
//               dust = BitmapFactory.decodeResource(icontent,R.drawable.idust2);
//               dust2 = BitmapFactory.decodeResource(icontent,R.drawable.idust);
//               Spark = BitmapFactory.decodeResource(icontent,R.drawable.coinsparkle);

               bullet1  = loadsprite(icontent, R.drawable.bullet1);
               bullet2 = loadsprite(icontent, R.drawable.bullet2);
               bullet3 = loadsprite(icontent, R.drawable.bullet3);
//               bullet1 = BitmapFactory.decodeResource(icontent,R.drawable.bullet1);
//               bullet2 = BitmapFactory.decodeResource(icontent,R.drawable.bullet2);
//               bullet3 = BitmapFactory.decodeResource(icontent,R.drawable.bullet3);

               //day forebackground loading
               Bigtree = loadsprite(icontent, R.drawable.bigtree1);
               tree = loadsprite(icontent, R.drawable.tree1);
               trunk  = loadsprite(icontent, R.drawable.treetrunk1);
//               Bigtree = BitmapFactory.decodeResource(icontent,R.drawable.bigtree1);
//               tree = BitmapFactory.decodeResource(icontent,R.drawable.tree1);
//               trunk = BitmapFactory.decodeResource(icontent,R.drawable.treetrunk1);

               //night forebackground loading
               NBigtree = loadsprite(icontent, R.drawable.nbigtree);
               Ntree = loadsprite(icontent, R.drawable.ntree1);
               Ntrunk = loadsprite(icontent, R.drawable.ntreetrunk);
//               NBigtree = BitmapFactory.decodeResource(icontent,R.drawable.nbigtree);
//               Ntree = BitmapFactory.decodeResource(icontent,R.drawable.ntree1);
//               Ntrunk = BitmapFactory.decodeResource(icontent,R.drawable.ntreetrunk);

               //Bug village fore background
               bugForebg = new Bitmap[8];
               bugForebg[0] = loadsprite(icontent, R.drawable.ready1);
               bugForebg[1] = loadsprite(icontent, R.drawable.ready2);
               bugForebg[2] = loadsprite(icontent, R.drawable.ready3);
               bugForebg[3] = loadsprite(icontent, R.drawable.ready4);
               bugForebg[4] = loadsprite(icontent, R.drawable.ready5);
               bugForebg[5] = loadsprite(icontent, R.drawable.ready6);
               bugForebg[6] = loadsprite(icontent, R.drawable.ready7);
               bugForebg[7] = loadsprite(icontent, R.drawable.ready8);



       }
	        
	     public static Bitmap loadsprite(Resources content, int image)
	     {
	    	   BitmapFactory.Options options = new BitmapFactory.Options();
	           options.inJustDecodeBounds = true;
	           BitmapFactory.decodeResource(content, image, options);
	           int imageHeight = options.outHeight;
	           int imageWidth = options.outWidth;
	           //String imageType = options.outMimeType;
	           
	           double width = deviceWidth / 800; //DefaultDeviceWidth
	      	   width = width * imageWidth;
	      	 
	      	 
	      	 double height = deviceHeight / 480; //DefaultDeviceheight
	      	 height = height * imageHeight;

          return decodeSampledBitmapFromResource(content, image, (int)width, (int)height);
	     }
	     
	     public static int calculateInSampleSize(
		            BitmapFactory.Options options, int reqWidth, int reqHeight) {
		    /* Raw height and width of image */
		    final int height = options.outHeight;
		    final int width = options.outWidth;
		    int inSampleSize = 1;

		    if (height > reqHeight || width > reqWidth) {

		        final int halfHeight = height / 2;
		        final int halfWidth = width / 2;

		        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
		        // height and width larger than the requested height and width.
		        while ((halfHeight / inSampleSize) > reqHeight
		                && (halfWidth / inSampleSize) > reqWidth) {
		            inSampleSize *= 2;
		        }
		    }

		    return inSampleSize;
		}
		 
		 public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			        int reqWidth, int reqHeight) {

			    // First decode with inJustDecodeBounds=true to check dimensions
			    final BitmapFactory.Options options = new BitmapFactory.Options();
			    options.inJustDecodeBounds = true;
			    BitmapFactory.decodeResource(res, resId, options);

			    // Calculate inSampleSize
			    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

			    // Decode bitmap with inSampleSize set
			    options.inJustDecodeBounds = false;
			    return BitmapFactory.decodeResource(res, resId, options);
			}
	     
		  @SuppressWarnings("unchecked")
		  public static void initSounds(Context context){
	         soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);//first value is the maximum number of sound 
	        
	         //we can play at once 20
	    	// soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
	         
	         soundPoolMap = new HashMap(39);

	         soundPoolMap.put( Scoin, soundPool.load(context, Scoin, 1) );
	         soundPoolMap.put( Sjump, soundPool.load(context, Sjump, 1) );
	         soundPoolMap.put( Sland, soundPool.load(context, Sland, 1) );
	         soundPoolMap.put( Sbite, soundPool.load(context, Sbite, 1) );
	         soundPoolMap.put( Sfall, soundPool.load(context, Sfall, 1) );
	         soundPoolMap.put( Srun, soundPool.load(context, Srun, 1) );
	         soundPoolMap.put( Sthunder, soundPool.load(context, Sthunder, 1) );
	        // soundPoolMap.put( Sjungle, soundPool.load(context, Sjungle, 1) );
	         soundPoolMap.put( Sfwd, soundPool.load(context, Sfwd, 1) );
	         soundPoolMap.put( Sback, soundPool.load(context, Sback, 1) );
	         soundPoolMap.put( Sbuy, soundPool.load(context, Sbuy, 1) );
	         soundPoolMap.put( Mosbuzz, soundPool.load(context, Mosbuzz, 1) );
	         soundPoolMap.put( bugbuzz, soundPool.load(context, bugbuzz, 1) );
	         soundPoolMap.put( biteDeath, soundPool.load(context, biteDeath, 1) );
	         soundPoolMap.put( magAttraction, soundPool.load(context, magAttraction, 1) );
	         soundPoolMap.put( Sgun0, soundPool.load(context, Sgun0, 1) );
	         soundPoolMap.put( Sgun1, soundPool.load(context, Sgun1, 1) );
	         soundPoolMap.put( Sgun2, soundPool.load(context, Sgun2, 1) );
	         soundPoolMap.put( Sgun3, soundPool.load(context, Sgun3, 1) );
	         soundPoolMap.put( Sgun4, soundPool.load(context, Sgun4, 1) );
	         soundPoolMap.put( Sgun5, soundPool.load(context, Sgun5, 1) );
	         soundPoolMap.put( Sgun6, soundPool.load(context, Sgun6, 1) );
	         soundPoolMap.put( Sgun7, soundPool.load(context, Sgun7, 1) );
	         soundPoolMap.put( Sgun8, soundPool.load(context, Sgun8, 1) );
	         soundPoolMap.put( Sgun9, soundPool.load(context, Sgun9, 1) );
	         soundPoolMap.put( Sgun10, soundPool.load(context, Sgun10, 1) );
	         soundPoolMap.put( Sgun11, soundPool.load(context, Sgun11, 1) );
	         soundPoolMap.put( Sgun12, soundPool.load(context, Sgun12, 1) );
	         soundPoolMap.put( Sgun13, soundPool.load(context, Sgun13, 1) );
	         soundPoolMap.put( Sgun14, soundPool.load(context, Sgun14, 1) );
	         soundPoolMap.put( Sgun15, soundPool.load(context, Sgun15, 1) );
	         soundPoolMap.put( Sgun16, soundPool.load(context, Sgun16, 1) );
	         soundPoolMap.put( Sgun17, soundPool.load(context, Sgun17, 1) );
	         soundPoolMap.put( Sexplode, soundPool.load(context, Sexplode, 1));
	         soundPoolMap.put( Sbughit, soundPool.load(context, Sbughit, 1));
	         soundPoolMap.put( Sbugscream, soundPool.load(context, Sbugscream, 1));
	         soundPoolMap.put( Sreload, soundPool.load(context, Sreload, 1));
	         soundPoolMap.put(jetjump, soundPool.load(context, jetjump, 1));
	         soundPoolMap.put(jethruter, soundPool.load(context, jethruter, 1));
           soundPoolMap.put(song, soundPool.load(context, song, 1));


	         }
		  
		  public void playMedia(boolean MediaEnabled)
		     {
		    	 if(mp == null)
		    	 {
		    	  mp = MediaPlayer.create(mContext, song);
		         mp.setVolume(0.2f, 0.2f);
			     mp.setLooping(true);
		    	 }
			     if(MediaEnabled )
			     {
			     mp.start();
			     }
//                if(MediaEnabled && mode == 1)
//                {
//                 mp.pause();
//                }
			     else
			     {
                     mp.stop();
			    	 mp.release();
			    	 mp = null;

			     }
		     }


           public static int playSoundEffectInstance(int soundID,boolean playing, boolean sound,int ID)
           {

            if (sound && playing)
            {
             ID = soundPool.play((Integer) soundPoolMap.get(soundID), mvolume, mvolume, 1, -1, 1f);//1
             //i used 2 instead of 1 n prority so that in sound pool when limit is reach it wont remove
             //these sound but it will remove those with lower prority
            }
            else if(!playing) {
            // soundPool.pause(soundID);
             soundPool.pause(ID);
            }
            else if(!sound) {
             soundPool.stop(ID);
            }
            //stop(soundID);
            return ID;
           }


		     public static void playSound(int soundID,int repeat,boolean sound)
		     {
		 	  
		    	// the sound will play for ever if we put the loop parameter -1

		 	  // float volume = 0.8f; //for setting volume
		 	   
		 	   if(sound == true)
		 	   {
		 		  soundPool.play((Integer) soundPoolMap.get(soundID), mvolume, mvolume, 1, repeat, 1f);
		 	   }
		 	   else  soundPool.stop(soundID);
		 	  
		 	  
		     }
		     
		     public static void loadSound(Context context)
		     {
		 	    if(soundPool == null || soundPoolMap == null)
		 	    {
		 	    initSounds(context);
		 	    }
		     }
		 
		 
		 
	     void Preset()
	     {
		
           if (GameState == gameLoading || GameState == gamewall || GameState == gameStart )
         
           {

            StartMode = StartModeA;
               bgSpeed = iWidth(300);
               
               arrow.location = new Vector2(755f, 388f);
               blackbg.location = new Vector2(750f, 382f);

               cautionsign.location = new Vector2(728, 350);
               
               storebtn.location =  new Vector2(812f, 384f);
               soundenabled.location =  new Vector2( 912f, 384f);
               sounddisabled.location =  new Vector2( 912f, 384f);
               musicEnabled.location =  new Vector2(1012, 384);
               musicDisabled.location =  new Vector2(1012f, 384f);
               creditbtn.location =  new Vector2(1112f, 384f);

               frame = 0;
               explorer = new Sprite(icontent, ScreenSize, R.drawable.full4, R.drawable.full4fliped, 196, 200);  //This loads the explorer sprite
               explorer.setLocation(new Vector2(-200, 210)); //162 (viewportX / 0.2f )
               explorer.SetFrame(frame);

               
               myMosquitoes = new ArrayList<Mosquitoes>();
               
               
	   }
       else if(GameState == gameOver)
       {
    	   achievements.SelectStage();
    	   
    	   //gameOverRateUsBtn = new Vector2[3];
           gameOverRateUsBtn[0] = new Vector2(272,150);
           gameOverRateUsBtn[1] = new Vector2(272, 220);
           gameOverRateUsBtn[2] = new Vector2(272, 290);
           
           
    	   GmOverBtns = new Vector2(150,60);
           HomeBtn = new Vector2(30, 400);
           StoreBtn = new Vector2(204, 400);
           Playbtn = new Vector2(376, 400);
           fbBtn = new Vector2(54, 32);
           fbbtnSize = new Vector2(89, 89);
           
           aletter.startX = iWidth(120);
           aletter.startY = iHeight(162);
           
           bletter.startX = iWidth(120);
           bletter.startY = iHeight(240);
           
           cletter.startX = iWidth(120);
           cletter.startY = iHeight(316);
           
         
           names = new Vector2[8];//4
           names[0] = new Vector2(578, 192);
           names[1] = new Vector2(578, 222);
           names[2] = new Vector2(578, 252);
           names[3] = new Vector2(578, 282);
           names[4] = new Vector2(578, 312);
           names[5] = new Vector2(578, 342);
           names[6] = new Vector2(578, 372);
           names[7] = new Vector2(578, 402);


           leaderwriter.XSpace = iWidth(14);
           leaderwriter.Gap = iWidth(15);
           leaderwriter.MaxLine = 3000;

           lstage = new ArrayList<Integer>();
           lname = new ArrayList<String>();
           lscore = new ArrayList<Double>();
           lid = new ArrayList<Integer>();


           myleaderboards = new ArrayList<leaderboards>();
      
       }
       else if(GameState == gameInplay)
      {
       //help bug catcher box for help screen mode
      // bugbox = new Vector2(300,90);
      // bugboxSize = new Vector2(400,390);

    	   shuttlehandX = iWidth(150);
           shuttlehandY = iHeight(58);
           Shuttlegravity = iHeight(2);
           
           padhandX = iWidth(106);
           padhandY = iHeight(60);
           Friction = iHeight(5);
           padgravity = iHeight(4);
           
            jumpVelocity = iHeight(18); //Jump mangitude 18
            Gravity =  iHeight(1);   //Jump gravity 1
            fallvelocity =  iHeight(2);
           
	  musicEnabled.location = new Vector2(10f, 108f);
      musicDisabled.location = new Vector2( 10f, 108f);
      storebtn.location = new Vector2(10f, 202f);  //store button
      soundenabled.location = new Vector2( 10f, 14f);
      sounddisabled.location = new Vector2(10f, 14f);


      forebg_Objects = new ArrayList<Sprite>();// List<Sprite>();

      myMosquitoes = new ArrayList<Mosquitoes>();

      bgSpeed = iWidth(220);



      explorer = new Sprite(icontent, ScreenSize, R.drawable.full5, 196, 200);  //This loads the explorer sprite
      explorer.setLocation(new Vector2(-200, 540)); //550
      explorer.SetFrame(frame);
      
      aletter.X = iWidth(250);
      aletter.Y = iHeight(180);
      
      bletter.X = iWidth(250);
      bletter.Y = iHeight(260);
      
      cletter.X = iWidth(250);
      cletter.Y = iHeight(340);
      }
        	 
                         
	   }
	   
	     public int iHeight(int y)
	     {
	    	 // Use 960 * 60 = 57600 / 480 = 120
	    	 
	       	// int olddeviceheight = 480;
	    	 
	    	// y = (deviceHeight * y ) / olddeviceheight;
          y = (int)(y * PositionfactorY);
	    	    
	    	 return y; 	 
	     }
	     
	     public float iHeight(float y)
	     {
	    	 // Use 960 * 60 = 57600 / 480 = 120
	    	 
	       	// float olddeviceheight = 480;
	    	 
	    	// y = (deviceHeight * y ) / olddeviceheight;
          y = (float)(y * PositionfactorY);
	    	    
	    	 return y; 	 
	     }
	     	     
	     public int iWidth(int x)
	     {
	    	// Use 960 * 60 = 57600 / 480 = 120
	    	
	    	 //int olddevicewidth = 800;
	    	
	    	// x =  (deviceWidth * x) / olddevicewidth;//Math.round(value)
          x = (int)(x * PositionfactorX);
	    			 
	    	return x;
	     }
	     
	     public float iWidth(float x)
	     {
	    	// Use 960 * 60 = 57600 / 480 = 120
	    	
	    	// float olddevicewidth = 800;
	    	
	    	// x = (deviceWidth * x) / olddevicewidth;
          x = (float)(x * PositionfactorX);
	    			 
	    	return x;
	     }
	     
	     public void loadInApp()
	     {
	    	 // Start setup. This is asynchronous and the specified listener
	         // will be called once setup completes.
	         mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
	             public void onIabSetupFinished(IabResult result) {
	                 //Log.d(TAG, "Setup finished.");

	                 if (!result.isSuccess()) {
	                     // Oh noes, there was a problem.
	                     //complain("Problem setting up in-app billing: " + result);
	                 	istatus = "Please.check.your.internet.connection.or.try.again.later.";
	                 	showboard = true;
	                     return;
	                 }
	                 else if(result.isSuccess())
	                 {
	                 	_isStoreEnabled = true;
	                 }

	                 // Hooray, IAB is fully set up. Now, let's get an inventory of stuff we own.
	                // Log.d(TAG, "Setup successful. Querying inventory.");
	                // mHelper.queryInventoryAsync(mGotInventoryListener);
	             }
	         });

	     }

	     // Callback for when a purchase is finished thats after the button click event
	     IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
	         public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

	             if (result.isFailure()) {
	            	 istatus = "Error purchasing ";
	            	 showboard = true;
	                 return;
	             }
	             if (!verifyDeveloperPayload(purchase)) {
	            	 istatus = "Error purchasing. Authenticity verification failed.";
	            	 showboard = true;
	                 return;
	             }



	             if (purchase.getSku().equals(coins15000)) {

	            	 Coins = 15000;
	                 mHelper.consumeAsync(purchase, mConsumeFinishedListener);
	             }
	            else if (purchase.getSku().equals(coins57000))
	                   {
	            	Coins = 57000;
	                 mHelper.consumeAsync(purchase, mConsumeFinishedListener);
				       }
	            else if (purchase.getSku().equals(coins120000))
                {
	            	Coins = 120000;
	                 mHelper.consumeAsync(purchase, mConsumeFinishedListener);
			       }
	            else if (purchase.getSku().equals(coins270000))
                {
	            	Coins = 270000;
	                 mHelper.consumeAsync(purchase, mConsumeFinishedListener);

			       }
	            else if (purchase.getSku().equals(coins600000))
                {
	            	Coins = 6000000;
	                 mHelper.consumeAsync(purchase, mConsumeFinishedListener);
			       }
	         }
	     };

	     /** Verifies the developer payload of a purchase. */
	     boolean verifyDeveloperPayload(Purchase p) {
	         String payload = p.getDeveloperPayload();

	         /*
	          * TODO: verify that the developer payload of the purchase is correct. It will be
	          * the same one that you sent when initiating the purchase.
	          *
	          * WARNING: Locally generating a random string when starting a purchase and
	          * verifying it here might seem like a good approach, but this will fail in the
	          * case where the user purchases an item on one device and then uses your app on
	          * a different device, because on the other device you will not have access to the
	          * random string you originally generated.
	          *
	          * So a good developer payload has these characteristics:
	          *
	          * 1. If two different users purchase an item, the payload is different between them,
	          *    so that one user's purchase can't be replayed to another user.
	          *
	          * 2. The payload must be such that you can verify it even when the app wasn't the
	          *    one who initiated the purchase flow (so that items purchased by the user on
	          *    one device work on other devices owned by the user).
	          *
	          * Using your own server to store and verify developer payloads across app
	          * installations is recommended.
	          */

	         return true;
	     }


	     // Called when consumption is complete after puchase finished this is called
	     IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
	         public void onConsumeFinished(Purchase purchase, IabResult result) {

	             // We know this is the "gas" sku because it's the only one we consume,
	             // so we don't check which sku was consumed. If you have more than one
	             // sku, you probably should check...
	             if (result.isSuccess()) {
	                 // successfully consumed, so we apply the effects of the item in our
	                 // game world's logic, which in our case means filling the gas tank a bit

	            	playSound(Sbuy, 0, sound);
	                MyGun.TotalCoins += Coins;  //here we add coins to total coin and set coins to zero for future use
	                Coins = 0;
	                //save game at this point always remeber

	             }
	             else {
	            	 istatus = "Error while consuming: ";
	            	 showboard = true;
	             }

	         }
	     };
	     
	     void flyShuttle(Double gameTime)
	        {
	            //here we know what todo 
	            if (bgSpeed < iWidth(600))
	            {
	                bgSpeed += gameTime * iWidth(60);
	            }

	            //here we know the action to take when the character jumps
	            if (jump )
	            {
	                ShuttleVelocity = iHeight(500);
	                Shuttlegravity = iHeight(4);
	                jump = false;
	            }


	            double ivelocity;
	            ivelocity = ShuttleVelocity * gameTime;// move the sprite based on the velocity//- iHeight(10)
	            if (flyingShuttle.location.Y - ivelocity > - iHeight(10) && flyingShuttle.location.Y - bg_Y + ivelocity + flyingShuttle.Texture2DHeight < iHeight(800)) //
	            {
	                flyingShuttle.location.Y -= ivelocity;
	                ShuttleAngleRadian = -0.253791153f;  //here we have a precalculated angle for we use to fly the ship

	                if (bg_Y - ivelocity < 1)
	                {
	                    bg_Y = 0;
	                }
	                else
	                {
	                    bg_Y -= ivelocity;
	                }

	                if (ivelocity < 0.9 || flyingShuttle.location.Y < 0)
	                {
	                    flyingShuttle.location.Y += Shuttlegravity;
	                    ShuttleAngleRadian = 0;

	                    if (bg_Y + Shuttlegravity > iHeight(320))
	                    {
	                        bg_Y = iHeight(320);
	                    }
	                    else
	                    {
	                        bg_Y += Shuttlegravity;
	                    }
	                }

	            }

	            //// apply friction to the velocity to slow the sprite down
	            ShuttleVelocity *= 1 - (Friction * gameTime);	           


	            //reset parameters is it flys under
	            if (flyingShuttle.location.Y + flyingShuttle.Texture2DHeight >= iHeight(750))
	            {
	                flyingShuttle.location.Y = iHeight(514);
	                ShuttleVelocity = 0;
	                Shuttlegravity = 0;
	                ShuttleAngleRadian = 0;
	            }
	        }

	        void DrawEnergyBar(Canvas spritebatch, float mangle, int mode)
	        {
	        	  float angle = (float)Math.toDegrees(mangle);
	        	  
	            if (mode == 1)
	            {
	                if (Energyframe < 9) EnergyCount += 1;
	                else if (Energyframe == 9 && Shuttleimageindex == 5)
	                {
	                    //here we reposition the explorer to ref the plane
	                    explorer.location.X = flyingShuttle.location.X + iWidth(32);
	                    explorer.location.Y = flyingShuttle.location.Y + iHeight(22);
	                    Fgexplorer = true;
	                    jumpVelocity = iHeight(18);
	                    Energyframe = 0;

	                    //load create stuff
	                    crateshow = iWidth(50) + iWidth((int)(Math.random() *(100 - 50)));
	                    crateshow += metersRan;
	                    cratetouched = false;
	                    getcrate = false;
	                   //i remove// thruter.Stop();
                     if(!thruter) {
                      playSoundEffectInstance(jethruter, false, sound, thruterID);
                      thruter = true;
                     }
	                    movement = run;
	                    Shuttleimageindex = 0;
	                }

	                if (EnergyCount == 50)//50
	                {
	                    Energyframe += 1;
	                    EnergyCount = 0;
	                }

	              

	                ShuttleEnergy.Draw(spritebatch, Energyframe, 1, angle,
	                    new Vector2(flyingShuttle.location.X + iWidth(80),
	                    		flyingShuttle.location.Y + iWidth(180) - bg_Y,false), new Vector2(0, 0), 0);//SpriteEffects.None
	            }
	            else if (mode == 2)
	            {
	                if (Energyframe < 9) EnergyCount += 1;
	                else if (Energyframe == 9 && padimageindex == 5)
	                {
	                    //here we reposition the explorer to ref the plane
	                    explorer.location.X = flyingpad.location.X + iWidth(32);
	                    explorer.location.Y = flyingpad.location.Y + iHeight(22);
	                    Fgexplorer = true;
	                    jumpVelocity = iHeight(18);
	                    Energyframe = 0;

	                    //load create stuff
	                    crateshow = iWidth(50) + iWidth((int)(Math.random() * (100 - 50)));
	                    crateshow += metersRan;
	                    cratetouched = false;
	                    getcrate = false;
	                   //i remove// thruter.Stop();
                     //here we stop thruter
                     if(!thruter) {
                      playSoundEffectInstance(jethruter, false, sound, thruterID);
                      thruter = true;
                     }
	                    movement = run;
	                    padimageindex = 0;
	                }

	                if (EnergyCount == 50)//50
	                {
	                    Energyframe += 1;
	                    EnergyCount = 0;

	                    //frame equal 10 stop draing
	                }

	                ShuttleEnergy.Draw(spritebatch, Energyframe, 1, angle,
	                   new Vector2(flyingpad.location.X + iWidth(80),
	                		   flyingpad.location.Y + iWidth(200) - bg_Y, false), new Vector2(0, 0), 0);//SpriteEffects.None
	            }
	        }

	        void DrawShuttle(Canvas spritebatch)
	        {
	            //this is used to know the image index to draw  when drawing sprites
	            if (Energyframe < 9)
	            {
	                if (Shuttleimageindex < 2) Shuttleimageindex += 1;
	                else if (Shuttleimageindex == 2) Shuttleimageindex = 0;
	            }
	            else if (Energyframe == 9)
	            {
	                if (Shuttleimageindex < 5) Shuttleimageindex += 1;
	                else if (Shuttleimageindex == 5) Shuttleimageindex = 0;
	            }

	           float shuttleangle = (float)Math.toDegrees(ShuttleAngleRadian);

	            flyingShuttle.Draw(spritebatch, Shuttleimageindex, 1, shuttleangle,
	                new Vector2(flyingShuttle.location.X, flyingShuttle.location.Y - bg_Y,false),
	                new Vector2(0, 0), 0);//SpriteEffects.None

	            //this code is supposed to make explorer seem as if his shout when beaten by a buy
	            if (shouting > 0 && ShuttleAngleRadian == 0)
	            {

	                explorerShout.Draw(spritebatch, flyingShuttle.location.X + iWidth(254),
	                		flyingShuttle.location.Y + iHeight(64) - bg_Y);

	                shouting -= 1;

	                if (shouting < 0 || shouting == 0) shouting = 0;
	            }
	            
	            GunsHand.location.X = flyingShuttle.location.X + shuttlehandX;//test
	            GunsHand.location.Y = flyingShuttle.location.Y + shuttlehandY;

	            //This height code is done to position the explorer hand gun based on the explorer frame
	            int height = 17;//17..52

	            if (frame == 0) height = iHeight(17);
	            else if (frame == 1) height = iHeight(16);
	            else if (frame == 2) height = iHeight(16);
	            else if (frame == 3) height = iHeight(16);
	            else if (frame == 4) height = iHeight(19);
	            else if (frame == 5) height = iHeight(17);
	            else if (frame == 6) height = iHeight(14);
	            else if (frame == 7) height = iHeight(15);
	            else if (frame == 8) height = iHeight(17);
	            else if (frame == 9) height = iHeight(19);
	            else if (frame == 10) height = iHeight(13);
	            else if (frame == 11) height = iHeight(10);
	            else if (frame == 12) height = iHeight(7);
	            else if (frame == 13) height = iHeight(16);
	            else if (frame == 15) height = iHeight(15);


	            //32, 6 these is the rotation point in reference to the gun hand image position
	            //so in other words anywhere anywhere the gun hand image is if you add 32 X and 6 Y you will get the rotation point
	            // hand.Draw(spriteBatch, 1, AngleRadians3, new Vector2(hand.x + 32, hand.y + 6), new Vector2(32, 6), SpriteEffects.None);

	            float angle = (float)Math.toDegrees(AngleRadians);//we do this cause andriod works in degrees
	            
	            if (ShuttleAngleRadian == 0)
	            {
	                if (fireshot  && BulletRelease)
	                {
	                    //This draws gun with fire
	                    //+65 +17 this is the position i want it to rotate from on the gun hand sprite
	                    GunHandFire.Draw(spritebatch, MyGun.PresentID, 1, angle,
	                   new Vector2(GunsHand.location.X , GunsHand.location.Y  - bg_Y,false),
	                   new Vector2(65, 17,false), 0);//SpriteEffects.None
	                    fireshot = false;
	                }
	                else
	                {
	                    //this draws gun without fire
	                    GunsHand.Draw(spritebatch, MyGun.PresentID, 1, angle,
	                        new Vector2(GunsHand.location.X , GunsHand.location.Y  - bg_Y,false),
	                        new Vector2(65, 17,false), 0);//SpriteEffects.None

	                }
	            }
	            else
	            {
	                GunsHand.Draw(spritebatch, MyGun.PresentID, 1, shuttleangle,
	                      new Vector2(GunsHand.location.X + iWidth(12), GunsHand.location.Y - iHeight(38) - bg_Y,false),  new Vector2(0,0), 0);//SpriteEffects.None
// new Vector2(-18, 38,false)
	            }
	        }

	        void flypad(Double gameTime)
	        {
	            //here we know what todo 
	            if (bgSpeed < iWidth(600))
	            {
	                bgSpeed += gameTime * iWidth(60);
	            }

	            //here we know the action to take when the character jumps
	            if (jump )
	            {
	                padVelocity = iHeight(600);
	                padgravity = iHeight(6);
	                jump = false;

	            }

	            Double ivelocity;
	            ivelocity = padVelocity * gameTime;// move the sprite based on the velocity
	            if (flyingpad.location.Y - ivelocity > -20 && flyingpad.location.Y - bg_Y + ivelocity + flyingpad.Texture2DHeight < iHeight(800)) //
	            {
	                flyingpad.location.Y -= ivelocity;
	                padAngleRadians = -0.253791153f;  //here we have a precalculated angle for we use to fly the ship

	                if (bg_Y - ivelocity < 1)
	                {
	                    bg_Y = 0;
	                }
	                else
	                {
	                    bg_Y -= ivelocity;
	                }

	                if (ivelocity < 0.9 || flyingpad.location.Y < 0)
	                {
	                    flyingpad.location.Y += padgravity;
	                    padAngleRadians = 0;

	                    if (bg_Y + padgravity > iHeight(320))
	                    {
	                        bg_Y = iHeight(320);
	                    }
	                    else
	                    {
	                        bg_Y += padgravity;
	                    }
	                }

	            }


	            //// apply friction to the velocity to slow the sprite down
	            padVelocity *= 1 - (Friction * gameTime);


	            //reset parameters is it flys under
	            if (flyingpad.location.Y + flyingpad.Texture2DHeight >= iHeight(710))//750
	            {
	                flyingpad.location.Y = iHeight(492);//514
	                padVelocity = 0;
	                padgravity = 0;

	                padAngleRadians = 0;
	            }

	        }

	        void Drawpad(Canvas spriteBatch)
	        {
	            //this is used to know the image index to draw  when drawing sprites
	            if (Energyframe < 9)
	            {
	                if (padimageindex < 2) padimageindex += 1;
	                else if (padimageindex == 2) padimageindex = 0;
	            }
	            else if (Energyframe == 9)
	            {
	                if (padimageindex < 5) padimageindex += 1;
	                else if (padimageindex == 5) padimageindex = 0;
	            }
	            
	            float padangle = (float)Math.toDegrees(padAngleRadians);//we do this cause andriod works in degrees

	            flyingpad.Draw(spriteBatch, padimageindex, 1, padangle,
	                new Vector2(flyingpad.location.X, flyingpad.location.Y - bg_Y,false), 
	                new Vector2(0, 0), 0);//SpriteEffects.None

	            if (shouting > 0 && padAngleRadians == 0)
	            {

	                explorerShout.Draw(spriteBatch, flyingpad.location.X + iWidth(212), flyingpad.location.Y + iHeight(64) - bg_Y);

	                shouting -= 1;

	                if (shouting < 0 || shouting == 0) shouting = 0;
	            }


	            GunsHand.location.X = flyingpad.location.X + padhandX;//test
	            GunsHand.location.Y = flyingpad.location.Y + padhandY;

	            //This height code is done to position the explorer hand gun based on the explorer frame
	            int height = 17;//17..52

	            if (frame == 0) height = iHeight(17);
	            else if (frame == 1) height = iHeight(16);
	            else if (frame == 2) height = iHeight(16);
	            else if (frame == 3) height = iHeight(16);
	            else if (frame == 4) height = iHeight(19);
	            else if (frame == 5) height = iHeight(17);
	            else if (frame == 6) height = iHeight(14);
	            else if (frame == 7) height = iHeight(15);
	            else if (frame == 8) height = iHeight(17);
	            else if (frame == 9) height = iHeight(19);
	            else if (frame == 10) height = iHeight(13);
	            else if (frame == 11) height = iHeight(10);
	            else if (frame == 12) height = iHeight(7);
	            else if (frame == 13) height = iHeight(16);
	            else if (frame == 15) height = iHeight(15);


	            //32, 6 these is the rotation point in reference to the gun hand image position
	            //so in other words anywhere anywhere the gun hand image is if you add 32 X and 6 Y you will get the rotation point
	            // hand.Draw(spriteBatch, 1, AngleRadians3, new Vector2(hand.x + 32, hand.y + 6), new Vector2(32, 6), SpriteEffects.None);

	            float angle = (float)Math.toDegrees(AngleRadians);//we do this cause andriod works in degrees
	            
	            if (padAngleRadians == 0)
	            {
	                if (fireshot == true && BulletRelease == true)
	                {
	                    //This draws gun with fire
	                    //+65 +17 this is the position i want it to rotate from on the gun hand sprite
	                    GunHandFire.Draw(spriteBatch, MyGun.PresentID, 1, angle, 
	                    		new Vector2(GunsHand.location.X , GunsHand.location.Y - bg_Y,false),
	                    		new Vector2(65, 17,false), 0);//SpriteEffects.None
	                    fireshot = false;
	                }
	                else
	                {
	                    //this draws gun without fire
	                    GunsHand.Draw(spriteBatch, MyGun.PresentID, 1, angle,
	                        new Vector2(GunsHand.location.X , (GunsHand.location.Y ) - bg_Y,false), 
	                        new Vector2(65, 17,false),0);// SpriteEffects.None

	                }
	            }
	            else
	            {
	                GunsHand.Draw(spriteBatch, MyGun.PresentID, 1, padangle,
	                      new Vector2(GunsHand.location.X + iWidth(12), GunsHand.location.Y - iHeight(28) - bg_Y,false), 
	                      new Vector2(0, 0), 0);//SpriteEffects.None

	            }

	        }

	        void DrawExplorer(Canvas spriteBatch)
	        {

	            explorer.Draw(spriteBatch, (int)explorer.location.X - bg_X, (int)(explorer.location.Y - bg_Y), frame);
	            
	            float angle = (float)Math.toDegrees(AngleRadians);

	            //this code is supposed to make explorer seem as if his h
	            if (shouting > 0)
	            {
	                //This is done because they have different frames have different heights and widths
	                int height = iHeight(52);

	                if (frame == 0) height = iHeight(52);
	                else if (frame == 1) height = iHeight(51);
	                else if (frame == 2) height = iHeight(51);
	                else if (frame == 3) height = iHeight(52);
	                else if (frame == 4) height = iHeight(54);
	                else if (frame == 5) height = iHeight(52);
	                else if (frame == 6) height = iHeight(49);
	                else if (frame == 7) height = iHeight(50);
	                else if (frame == 8) height = iHeight(52);
	                else if (frame == 9) height = iHeight(54);
	                else if (frame == 10) height = iHeight(48);
	                else if (frame == 11) height = iHeight(45);
	                else if (frame == 12) height = iHeight(42);
	                else if (frame == 13) height = iHeight(51);


	                explorerShout.Draw(spriteBatch, explorer.location.X + iWidth(115),
	                		explorer.location.Y + height - bg_Y);

	                shouting -= 1;

	                if (shouting < 0 || shouting == 0) shouting = 0;
	            }

	            //draw hand gun
	            //This draws the fire shot in front of the bullet
	            if (fireshot && BulletRelease )
	            {

	                //This height code is done to position the explorer hand gun based on the explorer frame
//	                int height = 17;//17..52
//
//
//		            if (frame == 0) height = iHeight(17);
//		            else if (frame == 1) height = iHeight(16);
//		            else if (frame == 2) height = iHeight(16);
//		            else if (frame == 3) height = iHeight(16);
//		            else if (frame == 4) height = iHeight(19);
//		            else if (frame == 5) height = iHeight(17);
//		            else if (frame == 6) height = iHeight(14);
//		            else if (frame == 7) height = iHeight(15);
//		            else if (frame == 8) height = iHeight(17);
//		            else if (frame == 9) height = iHeight(19);
//		            else if (frame == 10) height = iHeight(13);
//		            else if (frame == 11) height = iHeight(10);
//		            else if (frame == 12) height = iHeight(7);
//		            else if (frame == 13) height = iHeight(16);
//		            else if (frame == 15) height = iHeight(15);



		           
	                //This draws gun with fire
	                //+65 +17 this is the position i want it to rotate from on the gun hand sprite
	                GunHandFire.Draw(spriteBatch, MyGun.PresentID, 1, angle,
	                		new Vector2(GunsHand.location.X , (GunsHand.location.Y ) - bg_Y,false),
	                		new Vector2(65, 17,false), 0);//SpriteEffects.None// + iWidth(65) + height
	                //i used the old 65 17 because it the rythming with the orignal image 
	                //note always use the original dimension for this
	                fireshot = false;
	            }
	            else
	            {
	                //This height code is done to position the explorer hand gun based on the explorer frame
                 int height = 2;//17

                 if (frame == 0) height = 2;
                 else if (frame == 1) height = 1;
                 else if (frame == 2) height = 1;
                 else if (frame == 3) height = 1;
                 else if (frame == 4) height = 2;
                 else if (frame == 5) height = 2;
                 else if (frame == 6) height = 1;
                 else if (frame == 7) height = 1;
                 else if (frame == 8) height = 2;
                 else if (frame == 9) height = 2;
                 else if (frame == 10) height = 1;
                 else if (frame == 11) height = 1;
                 else if (frame == 12) height = 2;
                 else if (frame == 13) height = 2;
                 else if (frame == 15) height = 1;

	                //this draws gun without fire
	                GunsHand.Draw(spriteBatch, MyGun.PresentID, 1, angle, 
	                		new Vector2(GunsHand.location.X, (GunsHand.location.Y - height ) - bg_Y,false),
	                    new Vector2(65, 17,false), 0);//SpriteEffects.None// + iWidth(65) + height//AngleRadians
	                //i used the old 65 17 because it the rythming with the orignal image 
	            }

	        }

	        void getWoodencrate()
	        {
            // Log.i("getWoodencrate", "Method called");

	            for (int i = 0; i < Dground.length; i++)
	            {
                 if(Dground[i] != null) {
                  // float a = Mground[i].Position.X;
                  if (Dground[i].X > iWidth(800)) {
                   woodencrate.location.X = Dground[i].X;
                   woodencrate.location.Y = Dground[i].Y - iHeight(200);
                   getcrate = true;
                  }
                 }
	            }

	        }

	        void updateWoodencrate(Double gameTime)
	        {
	            if (getcrate && woodencrate.location.X > - iWidth(200))
	            {
	                woodencrate.location.X -= (int)(gameTime * bgSpeed);

	                float crateX = iWidth(202), crateY = iHeight(152);
	                if (woodencrate.location.X + crateX >= explorer.location.X &&  //49 is rythming postion on inner box 106 is width
	                        woodencrate.location.X <= explorer.location.X + iWidth(60) &&//20
	                       woodencrate.location.Y + crateY >= explorer.location.Y &&   //180 is the rythming position of inner box y and 30 hieght
	                          woodencrate.location.Y <= explorer.location.Y + iHeight(60))
	                {
	                    cratetouched = true;
	                    jump = false;
	                   //i remove// if (sound == true) jetjump.Play();
	                    playSound(jetjump,0,sound);

	                    int chooser = 1 + (int)(Math.random() * (101 - 1));

	                    if (chooser > 50)
	                    {
	                        flyingShuttle.location.X = explorer.location.X - iWidth(32);
	                        flyingShuttle.location.Y = explorer.location.Y - iHeight(72);
	                        movement = fly;
	                    }
	                    else
	                    {
	                        flyingpad.location.X = explorer.location.X - iWidth(52);
	                        flyingpad.location.Y = explorer.location.Y - iHeight(62);
	                        movement = flypaddling;
	                    }

	                   //i remove// if (sound == true) thruter.Play();
	                    //playSound(thruter,0,sound);
                     if(thruter)
                     {
                      //runningID i used this to get the pointer to the sound address so i can
                      //stop the sound else the sound file wont stop
                      thruterID = playSoundEffectInstance(jethruter, true,sound,thruterID);
                      thruter = false;
                     }
	                }
	            }

	            if (metersRan > crateshow && getcrate == false)
	            {
	                getWoodencrate();
	            }
	        }
	   
	        void DrawCrate(Canvas spritebatch)
	        {
	            if (woodencrate.location.X > - iWidth(210) && woodencrate.location.X < iWidth(800) 
	            		&& getcrate == true && cratetouched == false)
	            {
                 woodencrate.Draw(spritebatch, woodencrate.location.X, woodencrate.location.Y - bg_Y);
	            }
	        }
	     
	        
	        
	     void ReloadGun()
	        {
	            if (MyGun.TotalCoins >= MyGun.CostOfAmmoID[MyGun.PresentID])
	            {
	              //i remove//  if (sound == true) Sreload.Play();
                     playSound(Sreload, 0, sound);
	                MyGun.TotalCoins -= MyGun.CostOfAmmoID[MyGun.PresentID];
	                MyGun.AmmoLevel[MyGun.PresentID] = MyGun.MaxAmmoID[MyGun.PresentID];
	                MyGun.SetGunRounds(MyGun.PresentID);

	                //Play gun reload sound
	            }
	            else if (MyGun.TotalCoins < MyGun.CostOfAmmoID[MyGun.PresentID])
	            {
	                //Load more coins, buy coins or use free gun
	                Dpaused = true;
	                morecoins = true;
	              //i remove// Purchase.igetInventory = false;

	                
	            }
	        }
	   
	     void PreStart(Double gameTime)
	      {
	    	 
	    	 if (GameState == gameLoading)
	            {
	                menuCounter += 1;
	                if (menuCounter >= 250)
	                {
	                    menuCounter = 0;
	                    GameState = gamewall;
	                }

	            }
	            else if (GameState == gamewall)
	            {
	                loaderCounter += 1;
	                if (loaderCounter >= 10)
	                {
	                    loaderCounter = 0;
	                    if (iloadframe < 9) iloadframe += 1;
	                    else iloadframe = 0;
	                }

	                menuCounter += 1;

	                if (menuCounter >= iload)
	                {
	                    menuCounter = 0;
	                    GameState = gameStart;
	                    iload = 100 + (int)(Math.random() * ((300 - 100))); 
	                }

	            }
	            else if (GameState == gameStart)
	            {
	                //This is used to control little caution sign on start menu
	                cautioncounter += 1;
	                if (cautioncounter == 10)
	                {
	                    if (cautionframe == 0) cautionframe += 1;
	                    else if (cautionframe == 1) cautionframe = 0;
	                    cautioncounter = 0;
	                }
	                //end 

	                //This code here is used for the little menu on start screen
	                if (MoveLittleMenu )
	                {
	                    if (arrow.location.X > iWidth(360))//260
	                    {
	                        arrow.location.X -= gameTime * iWidth(350); //After conversion 840
	                        blackbg.location.X -= gameTime * iWidth(350);
	                        cautionsign.location.X -= gameTime  * iWidth(350);
	                        storebtn.location.X -= gameTime * iWidth(350);
	                        soundenabled.location.X -= gameTime * iWidth(350);
	                        sounddisabled.location.X -= gameTime * iWidth(350);
	                        musicEnabled.location.X -= gameTime * iWidth(350);
	                        musicDisabled.location.X -= gameTime * iWidth(350);
	                        creditbtn.location.X -= gameTime * iWidth(350);

	                    }

	                }
	                else if (!MoveLittleMenu )
	                {
	                    if (arrow.location.X < iWidth(755))
	                    {
	                    	
	                        arrow.location.X += gameTime * iWidth(350); //After conversion 840
	                        blackbg.location.X += gameTime * iWidth(350);
	                        cautionsign.location.X += gameTime  * iWidth(350);
	                        storebtn.location.X += gameTime * iWidth(350);
	                        soundenabled.location.X += gameTime * iWidth(350);
	                        sounddisabled.location.X += gameTime * iWidth(350);
	                        musicEnabled.location.X += gameTime * iWidth(350);
	                        musicDisabled.location.X += gameTime * iWidth(350);
	                        creditbtn.location.X += gameTime * iWidth(350);
	                    }

	                }
	                //end

	                //This is used to randomize when explorer would run accross the screen
	                if (menuCounter < iload) menuCounter += 1;
	                else if (menuCounter >= iload)
	                {
	                    counter += 1;

	                    if (counter == 2)
	                    {
	                        if (frame < 9) frame += 1;
	                        else if (frame == 9) frame = 0;

	                    }
	                    else if (counter > 2) { counter = 0; }
	                    //end 


	                    ///This is used to add mosquitoes on screen 
	                    AddMosquitoes(gameTime);

                     //here we will add alitte control to play sound effect for mosquitoes buzz
                     if (OtherbugsCount > 0 && buzz2 )
                     {
                      buzz2ID = playSoundEffectInstance(bugbuzz, true,sound,buzz2ID);
                      buzz2 = false;
                     }
                     else if (OtherbugsCount == 0 && !buzz2)
                     {
                      playSoundEffectInstance(bugbuzz, false, sound,buzz2ID);
                      buzz2 = true;
                     }

                     if (Moscount > 0 && buzz1)
                     {
                      buzz1ID = playSoundEffectInstance(Mosbuzz, true,sound,buzz1ID);
                      buzz1 = false;
                     }
                     else if (Moscount == 0 && !buzz1)
                     {
                      playSoundEffectInstance(Mosbuzz, false,sound,buzz1ID);
                      buzz1 = true;
                     }

                     //here we reset values to zero
                     Moscount = 0;
                     OtherbugsCount = 0;


//                     if (jungle)
//                     {
//                      jungleID = playSoundEffectInstance(Sjungle, true,sound,jungleID);
//                      jungle = false;
//                     }

	                    //ModeA is when the bug and explorer runs across left to right..ModeB opposite
	                    if (StartMode == StartModeA)
	                    {
	                        if (explorer.location.X < iWidth(900))
	                        {
	                            explorer.location.X += gameTime * bgSpeed;//bgSpeed
	                        }
	                                        
	                    }
	                    else if (StartMode == StartModeB)
	                    {
	                        if (explorer.location.X > - iWidth(200))
                            {
                             explorer.location.X -= gameTime * bgSpeed;//bgSpeed
                            }
	                    }

	                    //plays sound in start screen
	                    if (explorer.location.X  > - iWidth(50) && explorer.location.X  < deviceWidth + iWidth(50) )
	                    {

                         if(running)
                         {
                          //runningID i used this to get the pointer to the sound address so i can
                          //stop the sound else the sound file wont stop
                          runningID = playSoundEffectInstance(Srun, true,sound,runningID);
                          running = false;
                         }
	                      // if (sound == true) running.Play();
	                      //  else if (sound == false && running.State == SoundState.Playing) running.Stop();
	                    }
	                    else
                        {
                         if(!running) {
                          playSoundEffectInstance(Srun, false, sound,runningID);
                          running = true;
                         }

                        }


	                
	                }
	            }
	       }
	     
	     Handler  getName = new Handler()
	 		{
	 		    public void handleMessage(Message msg)
	 		    {
	 		       //Display Alert
	 		       alert = new AlertDialog.Builder(mContext);
	 	      	   alert.setTitle("ENTER NAME!!!");
	 	      	   alert.setMessage("Insert your name (maximum characters 8)");

	 	      	   // Set an EditText view to get user input 
	 	      	   final EditText input = new EditText(mContext);
	 	      	   alert.setView(input);
	 	      	 
	 	      	   
	 	      	   //Use this to choose the type of input you want to collect
	 	      	   //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
	 	      	   //builder.setView(input);
                    igetname = true;
	 	      	  
	 	      	   alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	 	      	   public void onClick(DialogInterface dialog, int whichButton) {
	 	      		userName = input.getText().toString();
	 	      		igetname = false;
	 	      	    // Do something with value!
	 	      	 if (userName != null)
	             {
	                 if (userName.length() > 8)
	                 {
	                	 userName = userName.substring(0, 8);
	                  //here i should save user name   //editor.putString("highscoreName" + String.valueOf(scoredetails), newhighscorename);
	                     
	                 }
	                 else
	                 {
	                   
	                	 //here i should save user name   //  //editor.putString("highscoreName" + String.valueOf(scoredetails), newhighscorename);
	                 }
	             }
	 	      	 
	 	      	// editor.commit();
	 	      	    }
	 	      	   });
	 	      	   
	 	      	   
	 	      	   alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 	      	    public void onClick(DialogInterface dialog, int whichButton) {
	 	      	        // Canceled.
	 	      	     igetname = false;
	 	      	   }
	 	      	   });

	 	      	    alert.show();
	 		    }
	 		};
	     
	     void SaveUserScore()
	        {
	    	 
	    	 //this is done to convert int to bigdecimal
	    	 BigDecimal mScore = new BigDecimal(metersRan.toString());  //metersRan; 
	    	 
	    	 asyncService.saveScoreForUser(gameName, userName, mScore, this);
	    	
	           // scoreBoardService.SaveUserScore(gameName, gameName, metersRan, this);

	            leadermode = isavingscore;
	        }
	     
	       void EditScore(String scoreId)
	        {
	    	 BigDecimal mScore = new BigDecimal(metersRan.toString());  //metersRan;
	    	 
	    	 asyncService.editScoreForUser(scoreId, mScore, this);

	          leadermode = ieditscore;
	        }
	       
	       void TopRanking()
	        {

	    	  int max = 40;
	    	   
	    	  

	    		  asyncService.getLeaderBoard(gameName, max, this); //good
	    		



	            // scoreBoardService.GetTopNRankers(gameName, max, this);

	            leadermode = iTopRank;
	        }
	        
	     @Override
	 	public void onSuccess(Game response) {
	 		// TODO Auto-generated method stub
	 		//createAlertDialog("Score SuccessFully Saved, For UserName : "+ response.getScoreList().get(0).getUserName()
	 		//		+  " With Score : " + response.getScoreList().get(0).getValue());
	 		
	 		//String gameinfo = "gameName is " + response.getScoreList().get(0).getUserName().toString();
	 		
	 		String gameinfo = "gameName is " + response.getName().toString();

            for (int i = 0; i < response.getScoreList().size(); i++)
            {
                String gameusername = "userName is : " + response.getScoreList().get(i).getUserName().toString();
                String gamescore = "score is : " + response.getScoreList().get(i).getValue().toString();
                String gamescoreID = "scoreId is : " + response.getScoreList().get(i).getScoreId().toString();

                if (leadermode == iTopRank)
                {
                    lid.add(i + 1);   //ID
                    lname.add(response.getScoreList().get(i).getUserName().toString() + " ");   //player name
                    lscore.add(response.getScoreList().get(i).getValue().doubleValue());      //playersccore
                }

                if (leadermode == isavingscore || leadermode == ieditscore)
                {
                    scoreID = response.getScoreList().get(i).getScoreId().toString();
                }
            }
            //this will call load forward method
            if (leadermode == iTopRank)
            {
                leaderpage = 0;
                loadforward();

                //here we save score
                if (metersRan >= achievements.LastRecord)
                {
                    if (scoreID != null)
                    {
                        //call edit if we already have score 
                        EditScore(scoreID);
                    }
                    else
                    {
                        //call else save score 
                    	if(userName != null )
                    	{
                    		if(userName.length() > 2)
                    		{
                              SaveUserScore();
                    		}
                    	}
                    }

                }
            }
            
          //  activity.finish();
	 	}

	 	@Override
	 	public void OnException(App42Exception ex)
	 	{ 	
	 		//createAlertDialog("Exception Occurred : "+ ex.getMessage());
	 		String error = "Exception Message" + ex.getMessage();
	 		
	 		//activity.finish();
	 	}
	 	
	 	  void loadforward()//4
	        {

	            //note we do this wen lid.Count > 0

	            myleaderboards = new ArrayList<leaderboards>();  //here we create an empty list and we add only 4 items to it which we will draw

	            int end = leaderpage + ipage;  //here leaderpage is zero we add 4 to it cause we only want to pick the top 4 to display
	            if (end >= lid.size()) end = lid.size();

	            int start = leaderpage;  //we start from zero



	            for (int i = start; i < end; i++)
	            {
	               
	                myleaderboards.add(new leaderboards(lname.get(i), lscore.get(i), lid.get(i)));

	                // if(i > 0) 
	                leaderpage += 1;

	            }

	        }

	        void loadback()
	        {

	            int start;

	            //note we do this wen lid.Count > 0

	            //here we are using 4 cause we want to display 4 items at once
	            if (leaderpage - ipage - myleaderboards.size() >= 0) { leaderpage = leaderpage - ipage - myleaderboards.size(); }
	            else { leaderpage = 0; } //if we cant go back we set it to zero

	            myleaderboards = new ArrayList<leaderboards>(); //here we clear board items so we can take the first 4 which we will draw

	            start = leaderpage;

	            int end = leaderpage + ipage;//4  //here we assume the lid.Count > 4 
	            if (end >= lid.size()) end = lid.size();  //else we set it to lid count


	            for (int i = start; i < end; i++)
	            {
	              
	                myleaderboards.add(new leaderboards(lname.get(i), lscore.get(i), lid.get(i)));

	                leaderpage += 1;

	            }

	        }
	        
	        
	        void drawleader(Canvas spritebatch)
	        {
	            for (int i = 0; i < myleaderboards.size(); i++)
	            {
	            	Integer mscore = myleaderboards.get(i).score.intValue();
	                leaderwriter.Words = myleaderboards.get(i).name + " " + "M" + mscore.toString();
	                leaderwriter.X = names[i].X;
	                leaderwriter.Y = names[i].Y;
	                leaderwriter.Draw(spritebatch);

	                // DrawScore(leaderscore, (int)myleaderboards[i].score, 10, namesScores[i].X, namesScores[i].Y);
	            }

	            if (leaderpage < lid.size()) //we take lid count to be what we downloaded from internet database
	            {
	                //this means we can still move to the right 
	                btnNext.Draw(spritebatch, btnNext.location);
	            }

	            if (leaderpage > ipage)//
	            {
	                //this means we can still move to the left   
	                btnPrevious.Draw(spritebatch, btnPrevious.location);
	            }
	        }


	     
	     void addBackground(Double gameTime)
	        {
	            //X = 380
	            if (metersRan >= startTransit && !Intransit )
	            {
	                Intransit = true; //this is done to start transition in background
	                bgChanged = true;

	                if (village <= 2) village += 1;
	                else village = 1;

	            }


	            if (transit.location.X <= - iWidth(380) && bgChanged )
	            {


	                //this is used to know when we want to changed background
	                if (village == bugForest) bg = new Sprite(icontent,ScreenSize, R.drawable.backgroundloop1);
	                else if (village == nBugForest) bg = new Sprite(icontent, ScreenSize,  R.drawable.nbgloop);
	                else if (village == bugVillage) bg = new Sprite(icontent, ScreenSize,  R.drawable.bg1);


	                bgChanged = false;

	                startTransit = metersRan + 700 + (int)(Math.random() * ((300 - 100))); // Object_Picker.Next(100, 300);
                     nearIntransit = startTransit - 340;//it takes about 23 metres to pass one tile
                    // startTransit = 100 + (int)(Math.random() * (100 - 50));
	                //nearIntransit = startTransit - 50;//it takes about 23 metres to pass one tile


	               // Object_Picker = new Random();
	                Mground = new ArrayList<groundObjects>();
	                floorlevel = new ArrayList<Integer>();
	                GroundNames = new ArrayList<Integer>();
	                myMosquitoes = new ArrayList<Mosquitoes>();
	                forebg_Objects = new ArrayList<Sprite>();
	                iDust = new ArrayList<Dust>();
	                bg_X = 0; bg_Y = iHeight(320);  //bgygap is used to fill up the space that was used to jump
	                gd_Y = iHeight(656); // gd_X2 = 0,
	               // explorer.location.X = - iWidth(200);
	               // explorer.location.Y = iHeight(540);
	               // setLocation(new Vector2(-200, 540)); //550


	            }

	            //this controls the transit background
	            if (transit.location.X + (2 * transit.Texture2DWidth) > 0 && Intransit == true)
	            {
	                transit.location.X -= gameTime * bgSpeed;
	            }
	            else if (transit.location.X + transit.Texture2DWidth < 0 - deviceWidth)
	            {
	                Intransit = false;
	                transit.location.X = deviceWidth;
	            }

	         
	        }

	     void addObjectForeGround(Double gameTime)
	        {
	           // while (forebg_Objects.size() < 3)
	    	    if(forebg_Objects.size() < 3)
	            {
	                int foreObject;
	                if (village == bugVillage) foreObject =1 + (int)(Math.random() * ((9 - 1))); // Object_Picker.Next(1, 9);
	                else foreObject = 1 + (int)(Math.random() * ((4 - 1))); //Object_Picker.Next(1, 4);


	                float Y_Position = 0, X_Position = deviceWidth;
	                int rand_Y = 0;

	                if (village == bugForest || village == nBugForest)
	                {

	                    switch (foreObject)
	                    {
	                        case 1: //Big Tree 1
	                            rand_Y = 1 + (int)(Math.random() * ((3 - 1))); //Object_Picker.Next(1, 3);

	                            if (rand_Y == 1) Y_Position = iHeight(356);// 238;
	                            else if (rand_Y == 2) Y_Position = iHeight(414); //286;

	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);
	                                // X_Position += forebg_Objects[a].Texture2DWidth / 2 + Object_Picker.Next(100, 300);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((300 - 50)))); //Object_Picker.Next(50, 300);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }

	                            }

	                            if (village == bugForest) forebg_Objects.add(new Sprite(ScreenSize, Bigtree, X_Position, Y_Position, false));
	                            else if (village == nBugForest) forebg_Objects.add(new Sprite(ScreenSize, NBigtree,  X_Position, Y_Position, false));
	                            break;

	                        case 2:
	                            rand_Y = 1 + (int)(Math.random() * ((4 - 1))); //Object_Picker.Next(1, 4);

	                            if (rand_Y == 1) Y_Position = iHeight(206);//88;
	                            else if (rand_Y == 2) Y_Position = iHeight(334);//188;
	                            else if (rand_Y == 3) Y_Position = iHeight(376);//304;

	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);
	                                // X_Position += forebg_Objects[a].Texture2DWidth / 2 + Object_Picker.Next(100, 300);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((300 - 50)))); //Object_Picker.Next(50, 300);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }
	                            }

	                            if (village == bugForest) forebg_Objects.add(new Sprite(ScreenSize, tree,  X_Position, Y_Position, false));
	                            else if (village == nBugForest) forebg_Objects.add(new Sprite(ScreenSize, Ntree,  X_Position, Y_Position, false));
	                            break;

	                        case 3:
	                            rand_Y = 1 + (int)(Math.random() * ((3 - 1))); //Object_Picker.Next(1, 3);

	                            if (rand_Y == 1) Y_Position = iHeight(485);// 520;
	                            else if (rand_Y == 2) Y_Position = iHeight(658);//455;

	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);
	                                // X_Position += forebg_Objects[a].Texture2DWidth / 2 + Object_Picker.Next(100, 300);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((300 - 50)))); //Object_Picker.Next(50, 300);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }

	                            }

	                            if (village == bugForest) forebg_Objects.add(new Sprite(ScreenSize, trunk, X_Position, Y_Position, false));
	                            else if (village == nBugForest) forebg_Objects.add(new Sprite(ScreenSize, Ntrunk,  X_Position, Y_Position, false));
	                            break;
	                    }
	                }
	                else if (village == bugVillage)
	                {
	                    switch (foreObject)
	                    {
	                        case 1: //Big Tree 1
	                            rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); // Object_Picker.Next(205, 251);

	                            Y_Position = rand_Y;


	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);
	                                // X_Position += forebg_Objects[a].Texture2DWidth / 2 + Object_Picker.Next(100, 300);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)//use viewport of screen better code
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }

	                            }

	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[0],  X_Position, Y_Position, false));

	                            break;
	                        case 2:
	                            rand_Y =  iHeight(205 + (int)(Math.random() * ((251 - 205)))); // Object_Picker.Next(205, 251);

	                            Y_Position = rand_Y;


	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }
	                            }

	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[1],  X_Position, Y_Position, false));
	                            break;

	                        case 3:
	                            rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

	                            Y_Position = rand_Y;

	                            if (forebg_Objects.size() > 0)
	                            {
	                                int a = forebg_Objects.size() - 1;
	                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

	                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
	                                {
	                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
	                                }
	                                else
	                                {
	                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
	                                }
	                            }

	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[2], X_Position, Y_Position, false));
	                            break;
	                        case 4:
	                        	  rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

		                            Y_Position = rand_Y;

		                            if (forebg_Objects.size() > 0)
		                            {
		                                int a = forebg_Objects.size() - 1;
		                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

		                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
		                                {
		                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
		                                }
		                                else
		                                {
		                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
		                                }
		                            }


	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[3],  X_Position, Y_Position, false));
	                            break;

	                        case 5:
	                        	  rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

		                            Y_Position = rand_Y;

		                            if (forebg_Objects.size() > 0)
		                            {
		                                int a = forebg_Objects.size() - 1;
		                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

		                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
		                                {
		                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
		                                }
		                                else
		                                {
		                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
		                                }
		                            }


	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[4],  X_Position, Y_Position, false));
	                            break;

	                        case 6:
	                        	  rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

		                            Y_Position = rand_Y;

		                            if (forebg_Objects.size() > 0)
		                            {
		                                int a = forebg_Objects.size() - 1;
		                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

		                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
		                                {
		                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
		                                }
		                                else
		                                {
		                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
		                                }
		                            }


	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[5],  X_Position, Y_Position, false));
	                            break;

	                        case 7:
	                        	  rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

		                            Y_Position = rand_Y;

		                            if (forebg_Objects.size() > 0)
		                            {
		                                int a = forebg_Objects.size() - 1;
		                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

		                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
		                                {
		                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
		                                }
		                                else
		                                {
		                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
		                                }
		                            }


	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[6], X_Position, Y_Position, false));
	                            break;

	                        case 8:
	                        	  rand_Y = iHeight(205 + (int)(Math.random() * ((251 - 205)))); //  Object_Picker.Next(205, 251);

		                            Y_Position = rand_Y;

		                            if (forebg_Objects.size() > 0)
		                            {
		                                int a = forebg_Objects.size() - 1;
		                                X_Position = (float) (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth);

		                                if (forebg_Objects.get(a).location.X + forebg_Objects.get(a).Texture2DWidth > deviceWidth)
		                                {
		                                    X_Position += iWidth(50 + (int)(Math.random() * ((200 - 50)))); //  Object_Picker.Next(50, 200);
		                                }
		                                else
		                                {
		                                    X_Position = deviceWidth + iWidth((int)(Math.random() * ((200)))); //Object_Picker.Next(viewportX, viewportX + 200);
		                                }
		                            }


	                            forebg_Objects.add(new Sprite(ScreenSize, bugForebg[7], X_Position, Y_Position, false));
	                            break;
	                    }


	                }
	            }

	            int removeObject = 100;  //this number is used to know when to remove object we use 100 cause it ll never be 100
	          
	               for (Sprite E : forebg_Objects)
	               {
	                 	int value = iWidth(30);
	                    if (movement == run) value = iWidth(30);
	                    else if (movement == fly || movement == flypaddling) value = iWidth(60);
	                    if (MoveGround == true) 
	                    	{
	                    	E.location.X -= (int)(gameTime * value);//15 , 30
	                    	}

	                    if (E.location.X + E.Texture2DWidth < 0 ) //- deviceWidth viewport better code //-800
	                    {
	                    	removeObject = forebg_Objects.indexOf(E);                     
	                    }
	               }
	               
	               if(removeObject != 100)
	               {
	                //we use this to remove objects cause if we remove object in loop in java it ll throw exception
 	                forebg_Objects.remove(removeObject);
	               }
	               
	        }
	     
	     void rategame()
	        {
	            //Stuff to know if to show rating or not
	            if (rating_Showing == false && rating_Never == false)
	            {
	                int ShowRate = 0;
	                ShowRate = (int)(Math.random() * 101);//Object_Picker.Next(0, 101);
	                if (ShowRate >= 40 && ShowRate < 70)
	                {
	                    Rating = true;
	                    rating_Showing = true;
	                }
	                else
	                {
	                    Rating = false;
	                    rating_Showing = false;
	                }
	            }
	        }
	     
	     void DrawScore(Canvas spritebatch, Sprite numbers, Integer score, Integer Spacing, float Mx, float My)
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
	                    numbers.Draw(spritebatch, (int)(Mx + (Spacing * i)), (int)My, scoreint);
	                }
	                else
	                {

	                    numbers.Draw(spritebatch, (int)Mx, (int)My, scoreint);

	                }
	            }
	        }
	        
	     void DrawScore(Canvas spritebatch, Sprite numbers, Integer score, Integer Spacing)
	        {

	            String stScore = score.toString(); // Converts the score to a string
	            Integer scoreint;
	            

	            for (int i = 0; i < stScore.length(); i++)
	            {

	          	   scoreint = Integer.parseInt(stScore.substring(i,i + 1));
	                numbers.SetFrame(scoreint);

	                if (i > 0)
	                {

	                    numbers.Draw(spritebatch, (int)(numbers.location.X + (Spacing * i)), (int)numbers.location.Y, scoreint);

	                }
	                else
	                {
	                    numbers.Draw(spritebatch, (int)numbers.location.X, (int)numbers.location.Y, scoreint);
	                }
	            }
	        }

	     void updateExplorer(Double gameTime)
	        {

//             if(tutorOn && tutormode == tutorjump && tutorgap > explorer.location.X)
//             {
//              if(explorer.location.X >= tutorgap)
//              {
//               tutor = true;
//              }
//             }

	            //Use to control the speed of the explorer
	    	   if (bgSpeed < iWidth(220))
	            {
	                bgSpeed += gameTime * iWidth(40);
	            }
	            else if (bgSpeed > iWidth(225))
	            {
	                //// apply friction to the velocity to slow the sprite down
	                bgSpeed *= 1 - (Friction * gameTime);
	            }

	            if (explorer.location.X < iWidth(80))
	            {
	            	//Log.d("explorer.location.X", "Inside UpdateExplorer method");
	            	explorer.location.X += gameTime * bgSpeed;
	            }
	            else
	            {
	                MoveGround = true;
	            }
	            
	            counter += 1;

	            if (jump == false)
	            {
	               // if (counter == 1)
	               // {
	                    //This is used to control and changes explorer frames
	                    if (frame < 9) frame += 1;
	                    else if (frame == 9 || frame > 9) frame = 0;

	               // }
	               // else if (counter > 1) { counter = 0; }
	            }
	            else if (jump == true)
	            {
	                
	                if (bg_Y <= iHeight(320) && Intransit == false)
	                {
	                	//Log.d("bg_Y <= iHeight(320)", "Inside UpdateExplorer method");
	                    if (bg_Y - jumpVelocity > iHeight(320)) bg_Y =  iHeight(320);
	                    else bg_Y -= jumpVelocity;

	                }

	                //if (Intransit == true) bg_Y = iHeight(320); //new code


	                explorer.location.Y -= jumpVelocity;
	                jumpVelocity -= Gravity;

	               // Log.d("jump frames", "Inside UpdateExplorer method");
	                //THese code controls the jump frames
	                if (jumpVelocity > 0 && frame < 10) frame = 10;
	                else if (frame == 10 && jumpVelocity > 0) frame += 1;
	                else if (jumpVelocity < 0 && frame < 13) frame += 1;


	                for (int i = 0; i < Dground.length; i++)
	                {
	                	if(Dground[i] != null)
	                	{
	                	// Log.d("loop Dground", "Inside UpdateExplorer method"
                              //  + "Dground.length" + Dground.length + "GroundSize" + GroundSize.length );
	                    if (Dground[i].X + GroundSize[i].X >= explorer.location.X + iWidth(90) &&  //49 is rythming postion on inner box 106 is width
	                         Dground[i].X <= (explorer.location.X + iWidth(90 + 20)) &&//20
	                        (Dground[i].Y + GroundSize[i].Y >= (explorer.location.Y + iHeight(180)) &&   //180 is the rythming position of inner box y and 30 hieght
	                           Dground[i].Y <= (explorer.location.Y + iHeight(180 + 30))))
	                    {
	                    //	Log.d("loop inside", "Inside UpdateExplorer method loop jump false");
	                        Fgexplorer = false;
	                        jump = false;
	                        jumpVelocity = iHeight(18);
	                       //i remove// if (sound == true) Sland.Play(); //this plays landing sound
	                        playSound(Sland, 0, sound);

	                        explorer.location.Y = (int)(Mground.get(i).floorY - explorer.Texture2DHeight - iHeight(10));// - iHeight(10)
	                        iDust.add(new Dust(ScreenSize, dust2, explorer.location.X - iWidth(60), explorer.location.Y + iHeight(114), 202, 100));//this adds a new dust frame;

	                        break;
	                    }
	                	}
	                    

	                }

	            }

	            ////used to control running footsteep sound
	            if (GameState == gameInplay)
	            {
	                if (!jump && !Fgexplorer )
	                {
	                  //i remove//  if (sound == true) running.Play();
	                	 //i remove// else if (sound == false && running.State == SoundState.Playing) running.Stop();
                     //sound control for explorer
                     if(running)
                     {
                      //runningID i used this to get the pointer to the sound address so i can
                      //stop the sound else the sound file wont stop
                      runningID = playSoundEffectInstance(Srun, true,sound,runningID);
                      running = false;
                     }

                    }
	                else
	                {
	                	 //i remove// if (sound == true) running.Pause();
	                	 //i remove// else if (sound == false && running.State == SoundState.Playing) running.Stop();
                      if(!running)
                      {
                     playSoundEffectInstance(Srun, false, sound,runningID);
                     running = true;
                    }
	                }
	            }


	            //Note 12f and 48f is the rythming position of the gun to the explorer's body            
	            GunsHand.location.X = explorer.location.X + iWidth(12); //
	            GunsHand.location.Y = explorer.location.Y + iHeight(48);// 

	        }
	     
	     void UpdateDust(Double gameTime, Integer dustSpeed)
	        {

	            for (int i = 0; i < iDust.size(); i++)
	            {
	                iDust.get(i).Update(gameTime, dustSpeed);

	                if (iDust.get(i).removeMe == true)
	                {

	                    iDust.set(i, null);
	                    iDust.remove(i);
	                }
	            }
	        }
	     
	     void UpdateCoinsparkle(Double gameTime, Integer SparkleSpeed)
	        {

	            for (int i = 0; i < icoinsparkle.size(); i++)
	            {
	                icoinsparkle.get(i).Update(gameTime, SparkleSpeed);
	                if (icoinsparkle.get(i).removeMe == true)
	                {                   
	                    icoinsparkle.remove(i);
	                }
	            }
	        }
	     
	     void UpdateMagentSparkle()
	        {
	            if (showmagnet == true)
	            {
	                magnetCounter += 1;

	                if (magnetCounter == 4)//8
	                {
	                    magnetframe += 1;
	                    magnetCounter = 0;
	                }



	                if (magnetframe == 9)
	                {
	                    showmagnet = false;
	                    magnetframe = 0;
	                    magnetCounter = 0;
	                }
	            }

	        }
	     
	     void gunAngle()
	        {

	            //We get the difference in angles
	            //float DistX = PositionTouched.X - GunsHand.x + 65;
	            //float DistY = PositionTouched.Y - (GunsHand.y + 46 - bg_Y);

	            //This is the start position of the bullet where the turent(thats the hole the bullet passes) starts 
	            TurentPostion = new Vector2(GunsHand.location.X + iWidth(82), GunsHand.location.Y + iHeight(38), false);  //84, 38 we add 84 and 38 to get the start position of bullet in the barrel


	            //We do alittle calculation to get the touched position 
	            float DistX = PositionTouched.X - TurentPostion.X;
	            float DistY = PositionTouched.Y - (TurentPostion.Y - bg_Y);

	            double MM = DistY / DistX;

	            AngleRadians = (float)Math.atan(MM);//This is done to get angle in degrees
	            

	            //X = cos(angle) * barrelLength + turret.Postion.X;
	            //Y = sin(angle) * barrelLength + turret.Position.Y;

	            //118 is the lenght of the barrel in pixels
	            //This code is to get the position of the bullet coming out of the gun turent after the angle has changed
	            float X = (float)(Math.cos(AngleRadians) * MyGun.BarrelLenght[MyGun.PresentID]) + GunsHand.location.X + iWidth(82);//84, 38 we add 84 and 38 to get the start position of bullet in the barrel

	            float Y = (float)(Math.sin(AngleRadians) * MyGun.BarrelLenght[MyGun.PresentID]) + GunsHand.location.Y + iHeight(38);//118 is barrel lenght used before

	            //new code
	            if(MyGun.PresentID == 1 || MyGun.PresentID == 10 || MyGun.PresentID == 13 || MyGun.PresentID == 15 
            			|| MyGun.PresentID == 16)
            	{
      		
            		Y -= iHeight(10);
            	}
            	
	            BulletDirection = new Vector2(X, Y,false); //This is the bullet direction gotten from the angle of the gun


	        }
	     
	     void angletouched(Vector2 touchPosition)
	        {

	            PositionTouched = touchPosition; //this is to get angle touched on screen
	            BulletRelease = true;  //shows bullet has been released
	            fireshot = true;   //show the fire icon will show
	            gunAngle();

	            if (MyBullets.size() < MyGun.RateOfFireID[MyGun.PresentID])
	            {
	            	
	                if (MyGun.Bullets[MyGun.PresentID] == 1) MyBullets.add(new Bullet(ScreenSize, bullet1, AngleRadians, BulletDirection.X, BulletDirection.Y));//54,24//blackdot
	                else if (MyGun.Bullets[MyGun.PresentID] == 2) MyBullets.add(new Bullet(ScreenSize, bullet2, AngleRadians, BulletDirection.X, BulletDirection.Y));
	                else MyBullets.add(new Bullet(ScreenSize, bullet3, AngleRadians, BulletDirection.X, BulletDirection.Y));

	              //i remove//  if (sound == true) Sgun[MyGun.PresentID].Play(); //play a sound based on selected gun
	               if(MyGun.PresentID == 0) playSound(Sgun0, 0, sound);
	               else if(MyGun.PresentID == 1) playSound(Sgun1, 0, sound);
	               else if(MyGun.PresentID == 2) playSound(Sgun2, 0, sound);
	               else if(MyGun.PresentID == 3) playSound(Sgun3, 0, sound);
	               else if(MyGun.PresentID == 4) playSound(Sgun4, 0, sound);
	               else if(MyGun.PresentID == 5) playSound(Sgun5, 0, sound);
	               else if(MyGun.PresentID == 6) playSound(Sgun6, 0, sound);
	               else if(MyGun.PresentID == 7) playSound(Sgun7, 0, sound);
	               else if(MyGun.PresentID == 8) playSound(Sgun8, 0, sound);
	               else if(MyGun.PresentID == 9) playSound(Sgun9, 0, sound);
	               else if(MyGun.PresentID == 10) playSound(Sgun10, 0, sound);
	               else if(MyGun.PresentID == 11) playSound(Sgun11, 0, sound);
	               else if(MyGun.PresentID == 12) playSound(Sgun12, 0, sound);
	               else if(MyGun.PresentID == 13) playSound(Sgun13, 0, sound);
	               else if(MyGun.PresentID == 14) playSound(Sgun14, 0, sound);
	               else if(MyGun.PresentID == 15) playSound(Sgun15, 0, sound);
	               else if(MyGun.PresentID == 16) playSound(Sgun16, 0, sound);
	               else if(MyGun.PresentID == 17) playSound(Sgun17, 0, sound);
	               
	                MyGun.UpdateRound();
	            }


	            

	        }
	     
	     void AddBullet(Double gameTime)
	        {

	            for (int i = 0; i < MyBullets.size(); i++)
	            {
	            	if(MyBullets.get(i) != null )
	            	{
	                MyBullets.get(i).Update(gameTime, deviceWidth);//800

	                if (MyBullets.get(i).Bulletx > (deviceWidth + iWidth(50)) || 
	                MyBullets.get(i).Bullety < (bg_Y - iHeight(300)) || MyBullets.get(i).Bullety > (bg_Y + deviceHeight + iHeight(100))
	                   || MyBullets.get(i).Bulletx < -iWidth(100))//
	                {

	                    MyBullets.remove(i);
	                }
	                else if (MyBullets.get(i).Hit  && MyGun.PresentID != 10 && MyGun.PresentID != 11 && MyGun.PresentID != 12 && MyGun.PresentID != 13)
	                {
	                    MyBullets.remove(i);
	                }
	            	}
	            }
	        }
	     
	     void AddMosquitoes(Double gameTime)
	        {
	            if (GameState == gameStart)
	            {
	                while (myMosquitoes.size() < 4 && StartMode == StartModeA)//
	                {
	                    float X = 0, Y = 0;

	                    if (StartMode == StartModeA) //This is for game intro bug chasing explorer across the screen
	                    {
	                        X = iWidth(230) + (int)(Math.random() * iWidth(((1000 - 230))));  //random.Next(230, 1000);
	                        Y = iHeight(60) + (int)(Math.random() * iHeight(((260 - 60)))); //random.Next(60, 260);

	                        if (myMosquitoes.size() == 3)
	                            X = iWidth(999);

	                        X = 0 - X;
	                    }


	                    int picked = 1 + (int)(Math.random() * ((100 - 1)));  //Object_Picker.Next(1, 100);

	                    if (picked < 33)
	                    {
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, wasp, waspfliped, 2, 160, 120, gd_Y,X, Y, bgyGap,true,0));

	                        //  myMosquitoes.Add(new Mosquitoes(this, "wasp_sprites_full", 2, 160, 120, gd_Y, new Vector2(explorer.x - bg_X, explorer.y - bg_Y), X, Y, random, bgyGap));
	                        // random = new Random();
	                    }
	                    else if (picked >= 33 && picked < 66)
	                    {
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, bettle,bettlefliped, 3, 160, 120, gd_Y, X, Y, bgyGap, true, 0));

	                        // myMosquitoes.Add(new Mosquitoes(this, "beetle_sprites_full", 3, 160, 120, gd_Y, new Vector2(explorer.x - bg_X, explorer.y - bg_Y), X, Y, random, bgyGap));
	                        //random = new Random();
	                    }
	                    else if (picked >= 66 && picked < 99)
	                    {
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, Mosquitoe, Mosquitoefliped, 1, 160, 120, gd_Y,X, Y, bgyGap, true, 0));

	                        //myMosquitoes.Add(new Mosquitoes(this, "mosquito_sprites_full", 1, 160, 120, gd_Y, new Vector2(explorer.x - bg_X, explorer.y - bg_Y), X, Y, random, bgyGap));
	                        //random = new Random();
	                    }

	                }



	                for (int i = 0; i < myMosquitoes.size(); i++)
	                {
                       if(myMosquitoes.get(i) != null)
                       {
	                    //sound stuff
	                    if (myMosquitoes.get(i).MX + myMosquitoes.get(i).Texture2DWidth > 0 && myMosquitoes.get(i).MX < deviceWidth &&
	                      myMosquitoes.get(i).MY + myMosquitoes.get(i).Texture2DHeight > 0 && myMosquitoes.get(i).MY  < deviceHeight) //code is to make sure we play only whats visible
	                    {
	                    	
	                        if (myMosquitoes.get(i).Type == 1)
	                        {
	                           Moscount += 1;
	                        }
	                        else 
	                        {
                             OtherbugsCount += 1;
	                        }
	                    }
	                  


	                    if (myMosquitoes.get(i).MX < iWidth(1000) && StartMode == StartModeA)
	                    {
	                        myMosquitoes.get(i).ScreenUpdate(gameTime, bgSpeed,1);// SpriteEffects.FlipHorizontally
	                    }
	                    else if (myMosquitoes.get(myMosquitoes.size() - 1).MX > deviceWidth + iWidth(200) && StartMode == StartModeA) //checks if last bug is more than 1000 During game start screen
	                    {

//	                        myMosquitoes.get(0).MX = iWidth(1000) + (int)(Math.random() * ((iWidth(1200) - iWidth(1000)))); //Object_Picker.Next(1000, 1200);
//	                        myMosquitoes.get(1).MX = iWidth(1200) + (int)(Math.random() * ((iWidth(1400) - iWidth(1200)))); //Object_Picker.Next(1200, 1400);
//	                        myMosquitoes.get(2).MX = iWidth(1400) + (int)(Math.random() * ((iWidth(1600) - iWidth(1400)))); //Object_Picker.Next(1400, 1600);
//	                        myMosquitoes.get(3).MX = iWidth(1799);
	                    	
	                    	   myMosquitoes.get(0).MX = deviceWidth + iWidth(200) + (int)(Math.random() * (iWidth(200))); //Object_Picker.Next(1000, 1200);
		                        myMosquitoes.get(1).MX = deviceWidth + iWidth(200) + (int)(Math.random() * (iWidth(200))); //Object_Picker.Next(1200, 1400);
		                        myMosquitoes.get(2).MX = deviceWidth + iWidth(200) + (int)(Math.random() * (iWidth(200))); //Object_Picker.Next(1400, 1600);
		                        myMosquitoes.get(3).MX = deviceWidth * 2;

	                        menuCounter = 0;
	                        iload = 100 + (int)(Math.random() * ((300 - 100))); //Object_Picker.Next(100, 300);
	                        StartMode = StartModeB;
	                    }

	                    if (StartMode == StartModeB && myMosquitoes.get(i).MX > - iWidth(300)) //This is for game intro bug chasing explorer across the screen
	                    {
	                        myMosquitoes.get(i).ScreenUpdate(gameTime, bgSpeed, 0);//SpriteEffects.None
	                    }
	                    else if (StartMode == StartModeB && myMosquitoes.get(i).MX <  - iWidth(300))
	                    {
	                    	//myMosquitoes.set(i, null);
	                        myMosquitoes.remove(i);

	                        if (myMosquitoes.size() < 1)
	                        {
	                            menuCounter = 0;
	                            iload = 100 + (int)(Math.random() * ((300 - 100))); //Object_Picker.Next(100, 300);
	                            StartMode = StartModeA;
	                        }

	                    }
                       }

	                }

	            }
	            else if (GameState == gameInplay)
	            {
	            	//This code is used to decide how many bugs will come out on screen
	                if (metersRan < 100) bugnumber = 3;
	                else if (metersRan >= 100 && metersRan < 400) bugnumber = 4; 
	                else if (metersRan >= 400 && metersRan < 800)  bugnumber = 5;
	                else if (metersRan >= 800 && metersRan < 1400)  bugnumber = 6; 
	                else if (metersRan >= 1400 && metersRan < 2000)  bugnumber = 6; 
	                else if (metersRan >= 2000 && metersRan < 3000)  bugnumber = 7; 
	                else if (metersRan >= 3000 && metersRan < 4000)  bugnumber = 7; 
	                else if (metersRan > 4000)  bugnumber = 8; 



	                if (myMosquitoes.size() < bugnumber && BoostClick == false)
	                {
	                   
	                    float X;  //This determines the sie the mosquitoes will come from
	                    X = deviceWidth + (int)(Math.random() * ((deviceWidth + iWidth(200) - deviceWidth)));



	                    float Y = bg_Y - iHeight(200) + (int)(Math.random() * ((explorer.location.Y + iHeight(200) - bg_Y - iHeight(200))));//Object_Picker.Next((int)bg_Y - 200, (int)(explorer.y + 200));
	                   
	                    int picked;
	                   
	                    picked = 1 + (int)(Math.random() * ((100 - 1))); //Object_Picker.Next(1, 100);

	                    if (picked < 33)
	                    {
	                        //wasp 2,bettle, 3,Mosquitoe, 1
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, Mosquitoe, Mosquitoefliped, 1, 160, 120, gd_Y,  X, Y, bgyGap, true, 0));
	                    }
	                    else if (picked >= 33 && picked < 66)
	                    {
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, bettle,  bettlefliped,3, 160, 120, gd_Y, X, Y, bgyGap, true, 0));
	                    }
	                    else if (picked >= 66 && picked < 99)
	                    {
	                        myMosquitoes.add(new Mosquitoes(ScreenSize, wasp,waspfliped, 2, 160, 120, gd_Y, X, Y, bgyGap, true, 0));
	                    }
	                }

	                // List<Bullet>
	                mybullets = MyBullets;

	                for (int i = 0; i < myMosquitoes.size(); i++)
	                {

//                     if(tutorOn && tutormode == tutorbug && tutorwait > 100 && tutorBugCount > 1 && !tutor)
//                     {
//                     if (myMosquitoes.get(i).MX + myMosquitoes.get(i).Texture2DWidth >= bugbox.X  &&  //15
//                             myMosquitoes.get(i).MX <= bugbox.X + bugboxSize.X &&
//                             myMosquitoes.get(i).MY - bg_Y + myMosquitoes.get(i).Texture2DHeight >= bugbox.Y &&  //14
//                             myMosquitoes.get(i).MY <= bugbox.Y + bugboxSize.Y) {
//
//                      tutor = true;
//                      helphand.location = new Vector2(myMosquitoes.get(i).MX + (myMosquitoes.get(i).Texture2DWidth / 2) - (helphand.Texture2DWidth / 2),
//                              myMosquitoes.get(i).MY + (myMosquitoes.get(i).Texture2DHeight / 2) - (helphand.Texture2DHeight / 2) );
//                     }
//                     }



	                	if(myMosquitoes.get(i) != null)
	                	{
	                    //sound stuff
	                    if (myMosquitoes.get(i).MX + myMosquitoes.get(i).Texture2DWidth > 0 && myMosquitoes.get(i).MX < deviceWidth &&
	                     myMosquitoes.get(i).MY - bg_Y + myMosquitoes.get(i).Texture2DHeight > 0 && myMosquitoes.get(i).MY - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
	                    {
	                        Moscount = 0;
	                        OtherbugsCount = 0;

	                        if (myMosquitoes.get(i).Type == 1)
	                        {
	                            Moscount += 1;
	                        }
	                        else
	                        {
	                            OtherbugsCount += 1;
	                        }
	                    }

	                    //sound stuff for ground and bullet hit
	                    if (myMosquitoes.get(i).Bhit == true)
	                    {
	                       // Sbugscream.Play();
	                      //i remove//  if (sound == true) Sbughit.Play();
	                    	playSound(Sbughit, 0, sound);
	                        myMosquitoes.get(i).Bhit = false;
	                    }
	                    if (myMosquitoes.get(i).Ghit )
	                    {
	                       // Sbughit.Play();
	                       //i remove// if (sound == true) Sbugscream.Play();
	                    	playSound(Sbugscream, 0, sound);
	                        myMosquitoes.get(i).Ghit = false;
	                    }

	                    if (myMosquitoes.get(i).soundExplode)
	                    {
	                       //i remove// if (sound == true) Sexplode.Play();
	                    	playSound(Sexplode, 0, sound);
	                        myMosquitoes.get(i).soundExplode = false;
	                        //play explode sound and set flag to false
	                    }



	                    //this code is to make thunder strike bugs
	                    if (thunder )
	                    {
	                        myMosquitoes.get(i).thunder = true;
	                        if (myMosquitoes.get(i).isLife ) myMosquitoes.get(i).mframe = 8;   //set frame for thunder strike if bug is alife
	                    }
	                    
	                    if (movement == run)
	                    {
	                        //pass in bullet in list to check positions in code....
	                    	 myMosquitoes.get(i).update(gameTime, mybullets,  (int)bg_Y, Dground, GroundSize, bgSpeed,
	                            new Vector2(explorer.location.X, explorer.location.Y,false), metersRan,
	                            MyGun.DamagedID[MyGun.PresentID], MyGun.Bullets[MyGun.PresentID]);
	                    }
	                    else if (movement == fly)
	                    {
	                        //X=144 Y=22 this is the reference position of the explorer in the flying shuttle
	                    	 myMosquitoes.get(i).update(gameTime, mybullets, (int)bg_Y, Dground, GroundSize, bgSpeed,
	                             new Vector2(flyingShuttle.location.X + iWidth(144), flyingShuttle.location.Y + iHeight(22),false),
	                             metersRan, MyGun.DamagedID[MyGun.PresentID], MyGun.Bullets[MyGun.PresentID]);
	                    }
	                    else if (movement == flypaddling)
	                    {
	                        //X=100 Y=22 this is the reference position of the explorer in the flying shuttle
	                    	myMosquitoes.get(i).update(gameTime, mybullets, (int)bg_Y, Dground, GroundSize, bgSpeed,
	                             new Vector2(flyingpad.location.X + iWidth(100), flyingpad.location.Y + iHeight(22),false),
	                             metersRan, MyGun.DamagedID[MyGun.PresentID], MyGun.Bullets[MyGun.PresentID]);
	                    }

	                    //pass in bullet in list to check positions in code....
	                  //  myMosquitoes.get(i).update(gameTime, mybullets, (int)bg_Y, Dground, GroundSize, bgSpeed, 
	                    //new Vector2(explorer.location.X, explorer.location.Y,false),
	                      //  metersRan, MyGun.DamagedID[MyGun.PresentID], MyGun.Bullets[MyGun.PresentID]);

	                    if (myMosquitoes.get(i).eAttacked == 1 && Intransit == false)  //this code is to know when explorer is attacked
	                    {
	                        explorerlife -= myMosquitoes.get(i).damageImapct;
	                        shouting = 3;
	                        //play shouting sound

	                        Attacked = true;

	                         if (myMosquitoes.get(i).damageImapct > 0) playSound(Sbite, 0, sound);
	                         //i remove //   if (sound == true) Sbite.Play();//explorer ahhhh sound

	                        myMosquitoes.get(i).eAttacked = 2;

	                        myMosquitoes.get(i).damageImapct = 0; 

	                    }

	                    //this is used to recored number of bug deaths 
	                    if (myMosquitoes.get(i).isLife == false && myMosquitoes.get(i).killrecord == false)
	                    {


	                        killnum += 1;
	                        myMosquitoes.get(i).killrecord = true;

	                        if (myMosquitoes.get(i).Type == 1)
	                        {
	                            achievements.MosKilled += 1; //keep track of mosquitoes killed in one run

	                            achievements.MosGuns[MyGun.PresentID] += 1; //keep track of mosquitoes killed in one run with a type of gun

	                            //keep track of total mosquitoes killed with a type of gun
	                            if (achievements.TFMosGuns )
	                            {
	                                achievements.TMosGuns[MyGun.PresentID] += 1;
	                            }

	                            //keep track of total mosquitoes killed
	                            if (achievements.TMkilled )
	                            {
	                                achievements.TMosKilled += 1;
	                            }

	                            //keep track of whats killed in what background
	                            //Note 0 is mosquitoes, 1 is wasp and 2 is bettle example (BugForest[0])
	                            if (village == bugForest)
	                            {
	                                achievements.BugForest[0] += 1;

	                                if (achievements.TFBugForest)
	                                {
	                                    achievements.TBugForest[0] += 1;
	                                }
	                            }
	                            else if (village == nBugForest)
	                            {
	                                achievements.NBugForest[0] += 1;

	                                if (achievements.TFNBugForest )
	                                {
	                                    achievements.TNBugForest[0] += 1;
	                                }
	                            }
	                            else if (village == bugVillage)
	                            {
	                                achievements.BugVillage[0] += 1;

	                                if (achievements.TFBugVillage )
	                                {
	                                    achievements.TBugVillage[0] += 1;
	                                }
	                            }
	                        }
	                        else if (myMosquitoes.get(i).Type == 2)
	                        {
	                            achievements.WaspKilled += 1;

	                            achievements.WaspGuns[MyGun.PresentID] += 1;

	                            //keep track of total wasp killed with a type of gun
	                            if (achievements.TFWaspGuns == true)
	                            {
	                                achievements.TWaspGuns[MyGun.PresentID] += 1;
	                            }

	                            //keep track of total wasp killed
	                            if (achievements.TWkilled == true)
	                            {
	                                achievements.TWaspKilled += 1;
	                            }

	                            //keep track of whats killed in what background
	                            //Note 0 is mosquitoes, 1 is wasp and 2 is bettle example (BugForest[1] is wasp cause its an array)
	                            if (village == bugForest)
	                            {
	                                achievements.BugForest[1] += 1;

	                                if (achievements.TFBugForest )
	                                {
	                                    achievements.TBugForest[1] += 1;
	                                }
	                            }
	                            else if (village == nBugForest)
	                            {
	                                achievements.NBugForest[1] += 1;

	                                if (achievements.TFNBugForest )
	                                {
	                                    achievements.TNBugForest[1] += 1;
	                                }
	                            }
	                            else if (village == bugVillage)
	                            {
	                                achievements.BugVillage[1] += 1;

	                                if (achievements.TFBugVillage )
	                                {
	                                    achievements.TBugVillage[1] += 1;
	                                }
	                            }
	                        }
	                        else if (myMosquitoes.get(i).Type == 3)
	                        {
	                            achievements.BeeKilled += 1;

	                            achievements.BeeGuns[MyGun.PresentID] += 1;

	                            //keep track of total bee killed with a type of gun
	                            if (achievements.TFBeeGuns )
	                            {
	                                achievements.TBeeGuns[MyGun.PresentID] += 1;
	                            }

	                            //keep track of total Bee killed
	                            if (achievements.TBkilled )
	                            {
	                                achievements.TBeeKilled += 1;
	                            }

	                            //keep track of whats killed in what background
	                            //Note 0 is mosquitoes, 1 is wasp and 2 is bettle example (BugForest[1] is wasp cause its an array)
	                            if (village == bugForest)
	                            {
	                                achievements.BugForest[2] += 1;

	                                if (achievements.TFBugForest )
	                                {
	                                    achievements.TBugForest[2] += 1;
	                                }
	                            }
	                            else if (village == nBugForest)
	                            {
	                                achievements.NBugForest[2] += 1;

	                                if (achievements.TFNBugForest )
	                                {
	                                    achievements.TNBugForest[2] += 1;
	                                }
	                            }
	                            else if (village == bugVillage)
	                            {
	                                achievements.BugVillage[2] += 1;

	                                if (achievements.TFBugVillage )
	                                {
	                                    achievements.TBugVillage[2] += 1;
	                                }
	                            }
	                        }
	                    }

	                    if (myMosquitoes.get(i).dust )
	                    {
	                        iDust.add(new Dust(ScreenSize, dust, (int)myMosquitoes.get(i).MX - iWidth(18), (int)myMosquitoes.get(i).MY + iHeight(50), 202, 100));
	                        myMosquitoes.get(i).dust = false;
	                    }

	                    if (myMosquitoes.get(i).MX < -iWidth(150) && myMosquitoes.get(i).isLife == false)
	                    {

	                    	//myMosquitoes.set(i, null);
	                        myMosquitoes.remove(i);
	                    }

	                    else if (myMosquitoes.get(i).MY < bg_Y - iHeight(100) && myMosquitoes.get(i).isLife == false)
	                    {

	                        //myMosquitoes.set(i, null);
	                        myMosquitoes.remove(i);

	                    }

	                }
	                }

	                MyBullets = mybullets;

                 //here we will add alitte control to play sound effect for mosquitoes buzz
                 if (OtherbugsCount > 0 && buzz2 )
                 {
                  buzz2ID = playSoundEffectInstance(bugbuzz, true,sound,buzz2ID);
                  buzz2 = false;
                 }
                 else if (OtherbugsCount == 0 && !buzz2)
                 {
                  playSoundEffectInstance(bugbuzz, false, sound,buzz2ID);
                  buzz2 = true;
                 }

                 if (Moscount > 0 && buzz1)
                 {
                  buzz1ID = playSoundEffectInstance(Mosbuzz, true,sound,buzz1ID);
                  buzz1 = false;
                 }
                 else if (Moscount == 0 && !buzz1)
                 {
                  playSoundEffectInstance(Mosbuzz, false,sound,buzz1ID);
                  buzz1 = true;
                 }

                 //here we reset values to zero
                 Moscount = 0;
                 OtherbugsCount = 0;



                }
	                
	        }
	     
	     void Groundloop2(Double gameTime, int gSpeed)
	        {
	            //int floorL1 = 716, floorL2 = 563, floorL3 = 413;
	            float gX1;
	          
	            if (GroundNames.size() < 3)//GroundNames.size() < 3
	            {
	                //this refills the ground when its less than 3 items
	                GroundList();
	            }

	            if (GroundNames.size() > 0)
	            {
	                //this is used to set floor name to the first ground name on the list
	                //and floor name is used to check if the ground is supposed to have a gap
	                floorName = GroundNames.get(0);///removed
	            }

	            if (Mground.size() == 0)
	            {
	                gX1 = 0;
	                //Reason why ground Y is 320 is because the images of ground are full screen so when you minus bgY ref it will be zero

	                groundcounter += 1;
	                // Mground.Add(new groundObjects(this, Ground, new Vector2(gX1, 320), floorlevel[0], GroundNames.First<string>())); 

	                Mground.add(new groundObjects(icontent, ScreenSize, GroundNames.get(0), new Vector2(gX1, iHeight(320),false), floorlevel.get(0)));  //removed               
	                GroundNames.remove(0); //removed

	                floorlevel.remove(0);
	                gap = 0;
	                if(floorName == R.drawable.l || floorName == R.drawable.l1 || floorName == R.drawable.l2 || floorName == R.drawable.l3 
	               		 || floorName == R.drawable.l4 || floorName == R.drawable.l5 || floorName == R.drawable.i || floorName == R.drawable.i4 || floorName == R.drawable.i5
	               		 || floorName == R.drawable.li || floorName == R.drawable.li4 || floorName == R.drawable.li5 ||
	               		 floorName == R.drawable.ni4 || floorName == R.drawable.ni5 || floorName == R.drawable.nl1 || floorName == R.drawable.nl2 
	               		 || floorName == R.drawable.nl3 || floorName == R.drawable.nl4 || floorName == R.drawable.nl5
	               		 || floorName == R.drawable.nli4 || floorName == R.drawable.nli5) gap = iWidth(80); //removed
	                floorName = GroundNames.get(0); //removed
	            }
	            else if (Mground.get(Mground.size() - 1).Position.X < iWidth(380))
	            {
	                groundcounter += 1;
	                //Mground.get(Mground.size() - 1).Texture2DWidth
	                //here we use 800 cause it our original design widht
	                gX1 = (float) (Mground.get(Mground.size() - 1).Position.X  + Mground.get(Mground.size() - 1).Texture2DWidth + gap);

//                 if(gap > 0 && tutorgap == 0 && tutorOn)
//                 {
//                  //this is used to caputure help position for jump
//                  tutorgap = gX1 - iWidth(100);
//                 }
	                Mground.add(new groundObjects(icontent, ScreenSize, GroundNames.get(0), new Vector2(gX1, iHeight(324),false), floorlevel.get(0)));  //removed         
	                GroundNames.remove(0);  //removed
	                floorlevel.remove(0);
	                gap = 0;
	                if(floorName == R.drawable.l || floorName == R.drawable.l1 || floorName == R.drawable.l2 || floorName == R.drawable.l3 
		               		 || floorName == R.drawable.l4 || floorName == R.drawable.l5 || floorName == R.drawable.i || floorName == R.drawable.i4 || floorName == R.drawable.i5
		               		 || floorName == R.drawable.li || floorName == R.drawable.li4 || floorName == R.drawable.li5 ||
		               		 floorName == R.drawable.ni4 || floorName == R.drawable.ni5 || floorName == R.drawable.nl1 || floorName == R.drawable.nl2 
		               		 || floorName == R.drawable.nl3 || floorName == R.drawable.nl4 || floorName == R.drawable.nl5
		               		 || floorName == R.drawable.nli4 || floorName == R.drawable.nli5) gap = iWidth(80); //removed
	                floorName = GroundNames.get(0); //removed
	            }




	            Dground = new Vector2[Mground.size()];
	            GroundSize = new Vector2[Mground.size()];

	            if (MoveGround )
	            {

	                int speed = gSpeed;


	                for (int i = 0; i < Mground.size(); i++)
	                {

	                    //i remove//Dground[i].X = Mground.get(i).Position.X;
	                	//i remove//Dground[i].Y = (int)Mground.get(i).floorY;
	                    
	                    Dground[i] = new Vector2( Mground.get(i).Position.X,Mground.get(i).floorY,false);

	                    GroundSize[i] = new Vector2(Mground.get(i).Texture2DWidth,iHeight(20),false);
	                    


                          //speed
	                    Mground.get(i).updateGround(gameTime, gSpeed, new Vector2(explorer.location.X + iWidth(90), explorer.location.Y + iHeight(180),false), iWidth(20), iHeight(30));//90= 49 20 = 86




	                    if (Mground.get(i).fall  && !Intransit )
	                    {
	                        Fgexplorer = true;
	                        Mground.get(i).fall = false;

	                    }



	                    if (Mground.get(i).Position.X + Mground.get(i).Texture2DWidth < -20)
	                    {
	                      // Mground.set(i, null);
	                        Mground.remove(i);
	                    }
	                }


	            }
	        }

	     void GroundList()
	        {
	            //this code is used to add names of grounds images to a list of string called GroundNames
	            int group = 0;
	            int floorL1 = iHeight(740), floorL2 = iHeight(620);//+ 30//620

	            if (GroundNames.size() == 0)
	            {
	                group = 0;
	            }
	            else
	            {
	                if (metersRan >= nearIntransit)
	                {
	                  
	                    group = 0;
	                }
	                else
	                {
	                    group =1 + (int)(Math.random() * ((20 - 1))); // Mran.Next(1, 20);
	                }
	            }

	            if (group == 0)
	            {
	                //Added the first group of grounds i want to have when the game starts 
	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);
	            }

	            if (group == 1)//&& GroundNames.Count > 1
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }
	            else if (group == 2)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }
	            else if (group == 3)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }
	            else if (group == 4)
	            {
	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }
	            else if (group == 5)
	            {
	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 6)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 7)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 8)
	            {
	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 9)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 10)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 11)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 12)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 13)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 14)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 15)
	            {
	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 16)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 17)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(middleGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 18)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);

	            }

	            else if (group == 19)
	            {
	                GroundNames.add(leftGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(lislandGround());
	                floorlevel.add(floorL1);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(islandGround());
	                floorlevel.add(floorL2);

	                GroundNames.add(rightGround());
	                floorlevel.add(floorL1);


	            }




	        }

	     int leftGround()
	        {
	            //this is used to generate a radom left ground
	            int value =1 + (int)(Math.random() * ((6 - 1)));  //Mran.Next(1, 6);
	            int name;

	            if (village == bugForest)
	            {
	                if (value == 1) name = R.drawable.l1;// "L1";
	                else if (value == 2) name =  R.drawable.l2;
	                else if (value == 3) name =  R.drawable.l3;
	                else if (value == 4) name =  R.drawable.l4;
	                else if (value == 5) name =  R.drawable.l5;
	                else name =  R.drawable.l5;
	            }
	            else if (village == nBugForest)
	            {
	                if (value == 1) name =  R.drawable.nl1;
	                else if (value == 2) name = R.drawable.nl2;
	                else if (value == 3) name = R.drawable.nl3;
	                else if (value == 4) name = R.drawable.nl4;
	                else if (value == 5) name = R.drawable.nl5;
	                else  name = R.drawable.nl5;

	            }
	            else //if (village == bugVillage)
	            {
	                name = R.drawable.l;
	            }

	            return name;
	        }

	     int rightGround()
	        {
	            //this is used to generate a random right ground
	            int value =1 + (int)(Math.random() * ((6 - 1)));  //Mran.Next(1, 6);
	            int name;

	            if (village == bugForest)
	            {
	                if (value == 1) name = R.drawable.r6;
	                else if (value == 2) name = R.drawable.r7;
	                else if (value == 3) name = R.drawable.r8;
	                else if (value == 4) name = R.drawable.r9;
	                else if (value == 5) name = R.drawable.r10;
	                else name = R.drawable.r10;
	            }
	            else if (village == nBugForest)
	            {
	                if (value == 1) name = R.drawable.nr6;
	                else if (value == 2) name = R.drawable.nr7;
	                else if (value == 3) name = R.drawable.nr8;
	                else if (value == 4) name = R.drawable.nr9;
	                else if (value == 5) name = R.drawable.nr10;
	                else name = R.drawable.nr10;

	            }
	            else //if (village == bugVillage)
	            {
	                name = R.drawable.r;
	            }

	            return name;
	        }

	     int middleGround()
	        {
	            //this is used to generate a random middle ground
	            int value =1 + (int)(Math.random() * ((7 - 1))); // Mran.Next(1, 7);
	            int name;

	            if (village == bugForest)
	            {
	                if (value == 1) name =  R.drawable.m1;
	                else if (value == 2) name =  R.drawable.m2;
	                else if (value == 3) name =  R.drawable.m3;
	                else if (value == 4) name =  R.drawable.m4;
	                else if (value == 5) name = R.drawable.bday;
	                else if (value == 6) name =  R.drawable.m5;
	                else name =  R.drawable.m5;
	            }
	            else if (village == nBugForest)
	            {
	                if (value == 1) name = R.drawable.nm1;
	                else if (value == 2) name = R.drawable.nm2;
	                else if (value == 3) name = R.drawable.nm3;
	                else if (value == 4) name = R.drawable.nm4;
	                else if (value == 5) name = R.drawable.bnight;
	                else if (value == 6) name = R.drawable.nm5;
	                else name = R.drawable.nm5;

	            }
	            else //if (village == bugVillage)
	            {
	                if (value < 11) name = R.drawable.m;
	                else name = R.drawable.bhut;

	            }

	            return name;
	        }

	     int islandGround()
	        {
	            //this is used to generate a random island ground
	            int value = 1 + (int)(Math.random() * ((3 - 1)));  //Mran.Next(1, 3);
	            int name;

	            if (village == bugForest)
	            {

	                if (value == 1) name = R.drawable.i4;
	                else if (value == 2) name = R.drawable.i5;
	                else name = R.drawable.i5;

	            }
	            else if (village == nBugForest)
	            {
	                if (value == 1) name = R.drawable.ni4;
	                else if (value == 2) name = R.drawable.ni5;
	                else name = R.drawable.ni5;

	            }
	            else //if (village == bugVillage)
	            {
	                name = R.drawable.i;
	            }

	            return name;
	        }

	     int lislandGround()
	        {
	            int value = 1 + (int)(Math.random() * ((3 - 1))); // Mran.Next(1, 3);
	            int name;

	            if (village == bugForest)
	            {

	                if (value == 1) name = R.drawable.li4;
	                else if (value == 2) name = R.drawable.li5;
	                else name = R.drawable.li5;
	            }
	            else if (village == nBugForest)
	            {

	                if (value == 1) name = R.drawable.nli4;
	                else if (value == 2) name = R.drawable.nli5;
	                else name = R.drawable.nli5;

	            }
	            else //if (village == bugVillage)
	            {
	                name = R.drawable.li;
	            }


	            return name;
	        }

	      void AddCoins(Double gameTime)
	        {
	            //Width of coin 
	            int Widht = iWidth(35 + 5); //Note 5 is the gap space we decieved to used and 35 the original width of coin
	            int height = iHeight(35 + 5);//

	            CoinGroup = (int) (Math.random() * ((10))); // Object_Picker.Next(0, 10);
	            // Group = GroupG;

	            //so coins wwont come when boost is show
	            if (!BoostClick && GCoins.size() < 20)
	            {

	                if (CoinGroup == 1)
	                {
	                    float X;

	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }


	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));


	                    float X2, X3;

	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y));

	                }
	                else if (CoinGroup == 2)
	                {
	                    float X;

	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3, X4, Y3;

	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2;
	                    Y3 = Y - height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y3));
	                    X4 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X4, Y));


	                }
	                else if (CoinGroup == 3)
	                {
	                    float X;

	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }


	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3, X4, X5, X6;
	                    float Y4, Y5, Y6;

	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y));
	                    X4 = X;
	                    Y4 = Y + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X4, Y4));
	                    X5 = X2;
	                    Y5 = Y4;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X5, Y5));
	                    X6 = X3;
	                    Y6 = Y4;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X6, Y6));



	                }
	                else if (CoinGroup == 4)
	                {
	                    float X;


	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3, X4, X5;
	                    float Y4, Y5;

	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y));
	                    X4 = X2;
	                    Y4 = Y + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X4, Y4));
	                    X5 = X4;
	                    Y5 = Y4 + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X5, Y5));

	                }
	                else if (CoinGroup == 5)
	                {
	                    float X;


	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3, X4, X5;

	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y));
	                    X4 = X3 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X4, Y));
	                    X5 = X4 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X5, Y));

	                }

	                else if (CoinGroup == 6)
	                {
	                    float X;


	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    
	                    float X2, X3, X4, X5, X6;
	                    float Y2, Y3, Y5, Y6;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X;
	                    Y2 = Y + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y2));
	                    X3 = X2;
	                    Y3 = Y2 + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y3));
	                    X4 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X4, Y));
	                    X5 = X4;
	                    Y5 = Y + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X5, Y5));
	                    X6 = X5;
	                    Y6 = Y5 + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X6, Y6));
	                }
	                else if (CoinGroup == 7)
	                {
	                    float X;


	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3;
	                    float Y2, Y3;
	                    GCoins.add(new Coins(ScreenSize, 2, bigcoin, 100, 100, X, Y));
	                    X2 = X + iWidth(25);
	                    Y2 = Y + iHeight(75);
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y2));
	                    X3 = X2;
	                    Y3 = Y2 + height;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y3));

	                }
	                else if (CoinGroup == 8)
	                {
	                    float X;

	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }

	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));

	                    float X2, X3;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X, Y));
	                    X2 = X + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X2, Y));
	                    X3 = X2 + Widht;
	                    GCoins.add(new Coins(ScreenSize, 1, smallcoin, 50, 50, X3, Y));

	                }
	                else if (CoinGroup == 9)
	                {
	                    float X;


	                    if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx > deviceWidth)
	                    {
	                        X = iWidth(400) + GCoins.get(GCoins.size() - 1).Mx;
	                    }
	                    else if (GCoins.size() > 0 && GCoins.get(GCoins.size() - 1).Mx < deviceWidth)
	                    {
	                        X = deviceWidth + iWidth(500) + GCoins.get(GCoins.size() - 1).Mx;  //This code is to make coin come where its is not visiable
	                    }
	                    else
	                    {
	                        X =deviceWidth + iWidth(100) + (int)(Math.random() * ((deviceWidth + iWidth(300) - deviceWidth + iWidth(100))));  //Object_Picker.Next(viewportX + 100, viewportX + 300); //picks a random value 300 after screen size
	                    }


	                    float Y = bg_Y + (int)(Math.random() * (( deviceHeight / 2))); //Object_Picker.Next((int)bg_Y, (int)bg_Y + (viewportY / 2));


	                    GCoins.add(new Coins(ScreenSize, 2, bigcoin, 100, 100, X, Y));


	                }
	            }

	            //Here i pass the list of guns to save space 
	            //List<Bullet> imybullets = MyBullets;


	            for (int i = 0; i < GCoins.size(); i++)
	            {
	                if (CoinMagnet == true)
	                {
	                    GCoins.get(i).coinMagnet = true;
	                }

	                //this is to know its attracting something to it
	                if (GCoins.get(i).imagnet == true)
	                {
	                    if (showmagnet == false) showmagnet = true;
	                   // magAttraction.Play();
	                   // playSound(magAttraction, 0, sound);
	                    
	                }
	                
	                //pass in bullet in position in code....new Vector2(pointer.x, pointer.y)...(10,10)
	                if (movement == run)
	                {
	                    GCoins.get(i).Update(gameTime, bgSpeed,  (int)bg_Y,
	                    		new Vector2(explorer.location.X, explorer.location.Y,false),
	                    		(int)explorer.Texture2DWidth, (int)explorer.Texture2DHeight);
	                }
	                else if (movement == fly)
	                {
	                    //X=144 Y=22 this is the reference position of the explorer in the flying shuttle
	                	  GCoins.get(i).Update(gameTime, bgSpeed, (int)bg_Y,
	                    new Vector2(flyingShuttle.location.X + iWidth(144), flyingShuttle.location.Y + iHeight(22),false),
	                    (int)explorer.Texture2DWidth, (int)explorer.Texture2DHeight);
	                }
	                else if (movement == flypaddling)
	                {
	                    //X=100 Y=22 this is the reference position of the explorer in the flying shuttle
	                	  GCoins.get(i).Update(gameTime, bgSpeed,  (int)bg_Y,
	                    new Vector2(flyingpad.location.X + iWidth(100), flyingpad.location.Y + iHeight(22),false),
	                    (int)explorer.Texture2DWidth, (int)explorer.Texture2DHeight);
	                }


	             
	                if (GCoins.get(i).touched == true)
	                {
	                	 //here we add a sparkle to indicated the coin has been touched and we do -45 to centre the sparkle on the coin
	                    if (movement == run)
	                    {
	                        icoinsparkle.add(new coinSparkle(ScreenSize, Spark, GCoins.get(i).Mx - iWidth(35),
	                        		GCoins.get(i).My - iHeight(45), 140, 140));
	                    }
	                    else if (movement == fly)
	                    {
	                        icoinsparkle.add(new coinSparkle(ScreenSize, Spark,
	                        		GCoins.get(i).Mx, GCoins.get(i).My, 140, 140));
	                    }
	                    else if (movement == flypaddling)
	                    {
	                        icoinsparkle.add(new coinSparkle(ScreenSize, Spark, GCoins.get(i).Mx, 
	                        		GCoins.get(i).My, 140, 140));
	                    }
	                  //i remove//  if (sound == true & sound == true) Scoin.Play();
	                    playSound(Scoin, 0, sound);

	                    if (GCoins.get(i).type == 2)
	                    {
	                        int coinvalue = 5;

	                        if (Coindoubler == true) coinvalue = coinvalue * 2; //if coin doubler equal true it doubles

	                        if (achievements.TCoin == true)
	                        {
	                            achievements.TotalCoinsColl += coinvalue;
	                        }

	                        achievements.CoinsColl += coinvalue;

	                        //big coins are type 2 and add  5 coin
	                        coinnum += coinvalue;
	                        MyGun.TotalCoins += coinvalue;

	                        // GCoins[i].Dispose();
	                        //GCoins.set(i, null);
	                        GCoins.remove(i);
	                    }
	                    else
	                    {
	                        int coinvalue = 1;

	                        if (Coindoubler ) coinvalue = coinvalue * 2; //if coin doubler equal true it doubles

	                        if (achievements.TCoin )
	                        {
	                            achievements.TotalCoinsColl += coinvalue;
	                        }

	                        achievements.CoinsColl += coinvalue;

	                        //small coin are type 1 and add plus 1
	                        coinnum += coinvalue;
	                        MyGun.TotalCoins += coinvalue;

	                        GCoins.remove(i);
	                    }
	                    //Play coin touched sound

	                }
	                //note coin should have sparkling frame when touched
	                else if (GCoins.get(i).Mx + GCoins.get(i).Texture2DWidth < 0)
	                {
	                    GCoins.remove(i);
	                }

	            }
	        }
	        
	        @Override
		public boolean  onTouchEvent(MotionEvent event)
		     {
		    	Vector2 touchPosition = new Vector2((double)event.getX(),(double)event.getY()); //get coordinated
		    	 int action = event.getAction(); //Get types of gesture
		    	 
		    	 if (action == event.ACTION_DOWN)
		    	 {
		    		 MenuTouch(touchPosition);
		    		 
		    	
		         }
		    	  if(action == event.ACTION_UP )
		    	  {
		    		
			     		 
		    	  }
		    	
		    	
		    	 return true;
		     }

	        void MenuTouch(Vector2 touchPosition)
	        {
	            //TouchCollection touchLocations = TouchPanel.GetState();
	            //foreach (TouchLocation touchLocation in touchLocations)
	           // {
	               // if (touchLocation.State == TouchLocationState.Pressed)
	               // {
	                 //   Vector2 touchPosition = touchLocation.Position;
	                    //Before Start Screen

	                    if (morecoins == false)
	                    {
	                        if (GameState == gameStart)
	                        {
	                            if (StartTouch(touchPosition) == true)
	                            {
	                                return;
	                            }
	                        }
	                        else if (GameState == gameOver)
	                        {
	                            if (showGuns == false && Rating == false && GameOverTouch(touchPosition) == true)
	                            {
	                                return;
	                            }
	                            else if (showGuns == true)
	                            {
	                                MyGun.update(GameState, showGuns, touchPosition);
	                            }
	                            else if (Rating == true)
	                            {
	                            	int gameoverWidth = iWidth(238), gameoverHeight = iHeight(55);
	                                //Game Over Rate us Touch 238 55
	                                if (touchPosition.X >= gameOverRateUsBtn[0].X &&
	                                    touchPosition.X < gameOverRateUsBtn[0].X + gameoverWidth &&
	                                    touchPosition.Y >= gameOverRateUsBtn[0].Y &&
	                                    touchPosition.Y < gameOverRateUsBtn[0].Y + gameoverHeight)
	                                {
	                                    Rating = false;
	                                    //these code shows how to rate app
//	                                    Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
//	                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//	                                    try {
//	                                    	mContext.startActivity(goToMarket);
//	                                    } catch (ActivityNotFoundException e) {
//	                                    	mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
//	                                    }
	                                    
	                                    
	                                }

	                                //Game Over Never Rate us Touch 238 55
	                                if (touchPosition.X >= gameOverRateUsBtn[1].X &&
	                                    touchPosition.X < gameOverRateUsBtn[1].X + gameoverWidth &&
	                                    touchPosition.Y >= gameOverRateUsBtn[1].Y &&
	                                    touchPosition.Y < gameOverRateUsBtn[1].Y + gameoverHeight)
	                                {
	                                    Rating = false;
	                                    rating_Never = true;
	                                }


	                                //Game Over Remind me later Rate us Touch 238 55
	                                if (touchPosition.X >= gameOverRateUsBtn[2].X &&
	                                    touchPosition.X < gameOverRateUsBtn[2].X + gameoverWidth &&
	                                    touchPosition.Y >= gameOverRateUsBtn[2].Y &&
	                                    touchPosition.Y < gameOverRateUsBtn[2].Y + gameoverHeight)
	                                {
	                                    Rating = false;
	                                }
	                            }


	                        }

	                        else if (GameState == gameInplay && !showGuns)
	                        {
	                            if (Dpaused )
	                            {
	                                if (PausedTouch(touchPosition) ) return;
	                            }
	                            else if (!Dpaused  && !tutor )
	                            {
	                                if (BoostClick )
	                                {
	                                    if (BoostTouch(touchPosition))
	                                    	{
	                                    	 if( Dpaused )
	                                         {
	                                         	    achievements.Updatestage();
	                                                 achievements.valueProcess();

	                                                 aletter.update(achievements.istages[0], iWidth(250), iHeight(180)); //Get ready to draw strings
	                                                 bletter.update(achievements.istages[1], iWidth(250), iHeight(260));
	                                                 cletter.update(achievements.istages[2], iWidth(250), iHeight(340));
	                                                 Integer value = new Integer(achievements.Mission) ;
	                                                 mletter.update("Misson." + value.toString() + ".", iWidth(320), iHeight(132));                    	
	                                         }
	                                    	return;  //captures boost touch
	                                    	}
	                                }
	                                else
	                                {
	                                	 //this is to make sure u cant shoot backwards and you cant shoot when bullet is empty
	                                    //this code is also to know the angle and direction to shoot 
	                                    if (movement == run)
	                                    {
	                                        if (touchPosition.X > (explorer.location.X + iWidth(100)) && MyGun.bulletEmpty == false) angletouched(touchPosition);
	                                    }
	                                    else if (movement == fly)
	                                    {
	                                        if (ShuttleAngleRadian == 0 && touchPosition.X > flyingShuttle.location.X + iWidth(240)) angletouched(touchPosition);
	                                    }
	                                    else if (movement == flypaddling)
	                                    {
	                                        if (padAngleRadians == 0 && touchPosition.X > flyingpad.location.X + iWidth(200)) angletouched(touchPosition);
	                                    }
	                                }
	                                //this code is to know when jump button is touched 
	                                if (touchPosition.X >= Jumpbtn.location.X &&
	                                    touchPosition.X < Jumpbtn.location.X + Jumpbtn.Texture2DWidth &&
	                                    touchPosition.Y >= Jumpbtn.location.Y &&
	                                    touchPosition.Y < Jumpbtn.location.Y + Jumpbtn.Texture2DHeight && !jump)
	                                {
	                                   
	                                    jump = true;
	                                    if (sound  && movement == run) playSound(Sjump, 0, sound);// Sjump.Play();//this play jump sound
	                                     else if (sound  && movement != run) playSound(jetjump, 0, sound);//jetjump.Play(); //this plays when flying craft
	                                }

	                            }
	                            else if (!Dpaused  && tutor  && !igetname )
	                            {

//                                 if (touchPosition.X >=  helphand.location.X &&
//	                                    touchPosition.X < helphand.location.X + helphand.Texture2DWidth &&
//	                                    touchPosition.Y >= helphand.location.Y &&
//	                                    touchPosition.Y < helphand.location.Y + helphand.Texture2DHeight && tutor)
//	                                {
//
//
//                                     if(tutormode == tutorbug)
//                                     {
//                                      tutor = false;
//                                      angletouched(touchPosition);
//
//                                      tutorwait = 0;
//
//                                      if(tutorBugCount > 1) tutorBugCount -= 1;
//                                      else if(tutorBugCount == 0)
//                                      {
//                                       tutormode = tutorthunder;
//                                       helphand.location = new Vector2(Jumpbtn.location.X + (Jumpbtn.Texture2DWidth / 2) - (helphand.Texture2DWidth /2),
//                                               Jumpbtn.location.Y + (Jumpbtn.Texture2DHeight / 2) - (helphand.Texture2DHeight /2));
//                                      }
//                                      if(tutormode == tutorjump)
//                                      {
//                                       tutor = false;
//                                       jump = true;
//                                       tutormode = tutorthunder;
//                                       helphand.location = new Vector2(thunderbtn.location.X + (thunderbtn.Texture2DWidth / 2) - (helphand.Texture2DWidth /2),
//                                               thunderbtn.location.Y + (thunderbtn.Texture2DHeight / 2) - (helphand.Texture2DHeight /2));
//                                      }
//                                      if(tutormode == tutorthunder)
//                                      {
//                                       tutor = false;
//                                       if(Athunder > 1)thunder = true;
//                                       tutormode = tutorgameInplay;
//                                       tutorOn = false;
//                                      }
//
//                                     }
//
//                                }

//	                                //this code is to know when left button is touched
	                                if (touchPosition.X >= tutorLeft.location.X &&
	                                    touchPosition.X < tutorLeft.location.X + tutorLeft.Texture2DWidth &&
	                                    touchPosition.Y >= tutorLeft.location.Y &&
	                                    touchPosition.Y < tutorLeft.location.Y + tutorLeft.Texture2DHeight)
	                                {
	                                    if (tutorPage == tutorPage2)
	                                    {
	                                        tutorPage = tutorPage1;
	                                        //i remove//    if (sound == true) Sjump.Play();//this play jump sound
	                                        playSound(Sjump, 0, sound);
	                                    }



	                                }

	                                //this code is to know when Right button is touched
	                                else if (touchPosition.X >= tutorRight.location.X &&
	                                    touchPosition.X < tutorRight.location.X + tutorRight.Texture2DWidth &&
	                                    touchPosition.Y >= tutorRight.location.Y &&
	                                    touchPosition.Y < tutorRight.location.Y + tutorRight.Texture2DHeight)
	                                {
	                                    if (tutorPage == tutorPage2)
	                                    {
	                                        tutor = false;
	                                    }
	                                    else if (tutorPage == tutorPage1)
	                                    {

	                                        tutorPage = tutorPage2;
	                                    }


	                                    //i remove// if (sound == true) Sjump.Play();//this play jump sound
	                                    playSound(Sjump, 0, sound);

	                                }
	                                else if (touchPosition.X >= btnDismmiss.location.X &&
	                                  touchPosition.X < btnDismmiss.location.X + btnDismmiss.Texture2DWidth &&
	                                  touchPosition.Y >= btnDismmiss.location.Y &&
	                                  touchPosition.Y < btnDismmiss.location.Y + btnDismmiss.Texture2DHeight)
	                                {

	                                        tutor = false;

	                                        //i remove//  if (sound == true) Sjump.Play();//this play jump sound
	                                        playSound(Sjump, 0, sound);

	                                }

	                            }

	                            //this to make sure we have tunder before we can press it
	                            if (Athunder > 0 && Dpaused == false)
	                            {

	                                if (touchPosition.X >= thunderbtn.location.X &&
	                                touchPosition.X < thunderbtn.location.X + ithunder.Texture2DWidth &&
	                               touchPosition.Y >= thunderbtn.location.Y &&
	                                touchPosition.Y < thunderbtn.location.Y + thunderbtn.Texture2DHeight)
	                                {
	                                	 //i remove//   if (sound == true) Sthunder.Play(); //plays the thunder strike sound
	                                	playSound(Sthunder, 0, sound);
	                                    thunder = true;
	                                    Athunder -= 1;                              
	                                }

	                            }

	                        }
	                        //end of game in play

	                        else if (GameState == gameGuns || showGuns)
	                        {
	                            // if (GunsTouch(touchPosition) == true) return;
	                            MyGun.update(GameState, showGuns, touchPosition);
	                        }
	                    }
	                    //More Coins touch info
	                    else if (morecoins == true && _isStoreEnabled == true && showboard == false)//i removed// && Purchase._isStoreEnabled == true && Purchase.showboard == false
	                    {
	                        if (touchPosition.X >= buyBtnPosition[0].X &&
	                                 touchPosition.X < buyBtnPosition[0].X + buyBtnSize.X && //buy button size widht
	                                 touchPosition.Y >= buyBtnPosition[0].Y &&
	                                 touchPosition.Y < buyBtnPosition[0].Y + buyBtnSize.Y ) //i removed //&& Purchase.BuyBtn[0] == true
	                        {
	                           // Purchase.result = true;
	                            //Purchase.coins = 15000;
	                          //i removed//  if (sound == true) Sfwd.Play();

	                        	playSound(Sfwd, 0, sound);
	                        	 /* TODO: for security, generate your c here for verification. See the comments on
	                             *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
	                             *        an empty string, but on a production app you should carefully generate this. */
                             // "";

                             if(!mHelper.mAsyncInProgress) {
                              Payload = generatePayload(36);
                              mHelper.launchPurchaseFlow(activity,
                                      coins15000, IabHelper.ITEM_TYPE_SUBS,
                                      RC_REQUEST, mPurchaseFinishedListener, Payload);
                              // boolean  mAsyncInProgress = mHelper.
                             }
                             else if(mHelper.mAsyncInProgress)
                             {
                              try {
                               mHelper.flagEndAsync();
                              }
                              catch (Exception e)
                              {
                                String error = e.getMessage().toString();
                              }

                             }

	                        	 //i removed//  Purchase.purchaseItem("Coins_15000"); //removed
	                            //buy button one  0.99$
	                           // TotalCoins += CostCoins[0];
	                        }

	                        else if (touchPosition.X >= buyBtnPosition[1].X &&
	                                 touchPosition.X < buyBtnPosition[1].X + buyBtnSize.X && //buy button size widht
	                                 touchPosition.Y >= buyBtnPosition[1].Y &&
	                                 touchPosition.Y < buyBtnPosition[1].Y + buyBtnSize.Y )  //i removed//&& Purchase.BuyBtn[1] == true
	                        {
	                        	playSound(Sfwd, 0, sound);


                             if(!mHelper.mAsyncInProgress) {
                              Payload = generatePayload(36);
                              mHelper.launchPurchaseFlow(activity,
                                      coins57000, IabHelper.ITEM_TYPE_SUBS,
                                      RC_REQUEST, mPurchaseFinishedListener, Payload);
                             }
                             else if(mHelper.mAsyncInProgress)
                             {
                              try {
                               mHelper.flagEndAsync();
                              }
                              catch (Exception e)
                              {
                               String error = e.getMessage().toString();
                              }

                             }
	                             //ProductID = coins_57000
	                            //buy button two  2.99$

	                        }

	                        else if (touchPosition.X >= buyBtnPosition[2].X &&
	                                 touchPosition.X < buyBtnPosition[2].X + buyBtnSize.X && //buy button size widht
	                                 touchPosition.Y >= buyBtnPosition[2].Y &&
	                                 touchPosition.Y < buyBtnPosition[2].Y + buyBtnSize.Y ) //i removed// && Purchase.BuyBtn[2] == true
	                        {
	                        	playSound(Sfwd, 0, sound);

                             if(!mHelper.mAsyncInProgress) {
                              Payload = generatePayload(36);
                              mHelper.launchPurchaseFlow(activity,
                                      coins120000, IabHelper.ITEM_TYPE_SUBS,
                                      RC_REQUEST, mPurchaseFinishedListener, Payload);
                             }
                             else if(mHelper.mAsyncInProgress)
                             {
                              try {
                               mHelper.flagEndAsync();
                              }
                              catch (Exception e)
                              {
                               String error = e.getMessage().toString();
                              }

                             }

	                            //ProductID = coins_120000
	                            //buy button three  4.99$
	                            // TotalCoins += CostCoins[2];
	                        }

	                        else if (touchPosition.X >= buyBtnPosition[3].X &&
	                              touchPosition.X < buyBtnPosition[3].X + buyBtnSize.X && //buy button size widht
	                              touchPosition.Y >= buyBtnPosition[3].Y &&
	                              touchPosition.Y < buyBtnPosition[3].Y + buyBtnSize.Y ) // i removed// && Purchase.BuyBtn[3] == true
	                        {
	                        	playSound(Sfwd, 0, sound);


                             if(!mHelper.mAsyncInProgress) {
                              Payload = generatePayload(36);
                              mHelper.launchPurchaseFlow(activity,
                                      coins270000, IabHelper.ITEM_TYPE_SUBS,
                                      RC_REQUEST, mPurchaseFinishedListener, Payload);
                             }
                             else if(mHelper.mAsyncInProgress)
                             {
                              try {
                               mHelper.flagEndAsync();
                              }
                              catch (Exception e)
                              {
                               String error = e.getMessage().toString();
                              }

                             }

	                             //ProductID = coins_270000
	                            //buy button four  9.99$
	                            // TotalCoins += CostCoins[3];
	                        }

	                        else if (touchPosition.X >= buyBtnPosition[4].X &&
	                              touchPosition.X < buyBtnPosition[4].X + buyBtnSize.X && //buy button size widht
	                              touchPosition.Y >= buyBtnPosition[4].Y &&
	                              touchPosition.Y < buyBtnPosition[4].Y + buyBtnSize.Y )  // i removed //&& Purchase.BuyBtn[4] == true
	                        {
	                        	playSound(Sfwd, 0, sound);


                             if(!mHelper.mAsyncInProgress) {
                              Payload = generatePayload(36);
                              mHelper.launchPurchaseFlow(activity,
                                      coins600000, IabHelper.ITEM_TYPE_SUBS,
                                      RC_REQUEST, mPurchaseFinishedListener, Payload);
                             }
                             else if(mHelper.mAsyncInProgress)
                             {
                              try {
                               mHelper.flagEndAsync();
                              }
                              catch (Exception e)
                              {
                               String error = e.getMessage().toString();
                              }

                             }

	                            //ProductID = coins_600000
	                            //buy button five  19.99$
	                            // TotalCoins += CostCoins[4];
	                        }


	                    }

	                }

// public final class SessionIdentifierGenerator {
//
//  private SecureRandom random = new SecureRandom();
//
//  public String nextSessionId() {
//   return new BigInteger(130, random).toString(32);
//  }
//
// }

           public void stopSoundInstance()
            {
             playSoundEffectInstance(Srun, false, sound, runningID);
             running = true;
             playSoundEffectInstance(jethruter, false, sound, thruterID);
             thruter = true;
             playSoundEffectInstance(Mosbuzz, false, sound, buzz1ID);
             buzz1 = true;
             playSoundEffectInstance(bugbuzz, false, sound, buzz2ID);
             buzz2 = true;

             playMedia(false);
            }

	        Boolean StartTouch(Vector2 touchPosition)
	        {
	            int startWidth = iWidth(215);
	            int startHeight = iHeight(85);

	            if (showInfo == false)
	            {
	                if (touchPosition.X >= startbtn.X &&
	                   touchPosition.X < startbtn.X + startWidth &&
	                   touchPosition.Y >= startbtn.Y &&
	                   touchPosition.Y < startbtn.Y + startHeight)
	                {
	                	
//	                	 GameState = gameOver;
//                         Preset();
//                         TopRanking(40);
//                         rating_Showing = false;


	                    GameState = gameInplay;
                     //playSoundEffectInstance(Sjungle, false,sound,jungleID);
                     //jungle = true;
                     stopSoundInstance();
                     playMedia(mediaEnabled);

	                
	                    if (userName == null)
	                    {
	                    	getName.sendEmptyMessage(0);  //this is used to get player name
	                    }
	                    
	                    gameRestart();
	                    MyGun.SetGunRounds(MyGun.PresentID);
	                    if (MyGun.PresentID == 20) MyGun.PresentID = 0;  //note load this from settings change this code	                  
	                     Preset();
	                    
	              
	                  //  if (sound == true) Sfwd.Play();
	                     playSound(Sfwd,0,sound);
	                    return true;
	                }
	                    //rate us touch
	                else if (touchPosition.X >= rateusbtn.location.X &&
	                   touchPosition.X < rateusbtn.location.X + rateusbtn.Texture2DWidth &&
	                   touchPosition.Y >= rateusbtn.location.Y &&
	                   touchPosition.Y < rateusbtn.location.Y + rateusbtn.Texture2DHeight)
	                {
	                    if (Rating == false)
	                    {
                         stopSoundInstance();
                            Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
                            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                            try {
                            	mContext.startActivity(goToMarket);
                            } catch (ActivityNotFoundException e) {
                            	mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
                            }
                            
	                    }

	                  //i removed//  if (sound == true) Sfwd.Play();
	                    playSound(Sfwd,0,sound);
	                    return true;
	                }

	                 //else if (touchPosition.X >= tweeter.location.X &&
	                // touchPosition.X < tweeter.location.X + tweeter.Texture2DWidth &&
	                // touchPosition.Y >= tweeter.location.Y &&
	                // touchPosition.Y < tweeter.location.Y + tweeter.Texture2DHeight)
	                //{
	                //    //what to invoke when tweeter button is clicked
	                //    return true;
	                //}

	                else if (touchPosition.X >= arrow.location.X &&
	                 touchPosition.X < arrow.location.X + arrow.Texture2DWidth &&
	                 touchPosition.Y >= arrow.location.Y &&
	                 touchPosition.Y < arrow.location.Y + arrow.Texture2DHeight)
	                {
	                    if (!MoveLittleMenu ) { MoveLittleMenu = true; if (sound ) playSound(Sfwd, 0, sound); return true;  }//i removed// Sfwd.Play();
	                    else if (MoveLittleMenu ) { MoveLittleMenu = false; if (sound ) playSound(Sfwd, 0, sound); return true;  }//i removed// Sfwd.Play();
	                    return true;
	                }
	                else if (touchPosition.X >= cautionsign.location.X &&
	            touchPosition.X < cautionsign.location.X + cautionsign.Texture2DWidth &&
	            touchPosition.Y >= cautionsign.location.Y &&
	            touchPosition.Y < cautionsign.location.Y + cautionsign.Texture2DHeight)
	                {
	                    if (!MoveLittleMenu) { MoveLittleMenu = true; if (sound )playSound(Sfwd, 0, sound); return true;  }//i removed// Sfwd.Play();
	                    else if (MoveLittleMenu ) { MoveLittleMenu = false; if (sound ) playSound(Sback, 0, sound); return true; }//i removed// Sfwd.Play();
	                    return true;
	                }

	                else if (touchPosition.X >= storebtn.location.X &&
	                touchPosition.X < storebtn.location.X + storebtn.Texture2DWidth &&
	                touchPosition.Y >= storebtn.location.Y &&
	                touchPosition.Y < storebtn.location.Y + storebtn.Texture2DHeight)
	                {
	                    //this when store is clicked
	                    GameState = gameGuns;
                     stopSoundInstance();
	                   //i reomved// if (sound == true) Sfwd.Play();
	                    playSound(Sfwd,0,sound);
	                   // iLoadContent();
	                    return true;
	                }


	                else if (touchPosition.X >= soundenabled.location.X &&
	                touchPosition.X < soundenabled.location.X + soundenabled.Texture2DWidth &&
	                touchPosition.Y >= soundenabled.location.Y &&
	                touchPosition.Y < soundenabled.location.Y + soundenabled.Texture2DHeight)
	                {
	                     //Purchase.result = true;
	                     //Purchase.coins = 15000;
	                    if (sound == false) { sound = true; if (sound == true) playSound(Sfwd,0,sound); return true; }//i removed //Sfwd.Play();
	                    else if (sound == true) { sound = false; return true; }
	                    return true;
	                }
	                else if (touchPosition.X >= musicEnabled.location.X &&
	                touchPosition.X < musicEnabled.location.X + musicEnabled.Texture2DWidth &&
	                touchPosition.Y >= musicEnabled.location.Y &&
	                touchPosition.Y < musicEnabled.location.Y + musicEnabled.Texture2DHeight)
	                {
	                    if (mediaEnabled == false) { mediaEnabled = true; if (sound == true)playSound(Sfwd,0,sound); return true;  }//i removed //Sfwd.Play();
	                    else if (mediaEnabled == true) { mediaEnabled = false; return true; }
	                    return true;

	                }
//	                else if (touchPosition.X >= infobtn.location.X &&
//	                touchPosition.X < infobtn.location.X + infobtn.Texture2DWidth &&
//	                touchPosition.Y >= infobtn.location.Y &&
//	                touchPosition.Y < infobtn.location.Y + infobtn.Texture2DHeight)
//	                {
//	                   //i removed// if (sound == true) Sfwd.Play();
//	                	 //i removed// GetName();
//	                    return true;
//	                }

	                else if (touchPosition.X >= creditbtn.location.X &&
	                touchPosition.X < creditbtn.location.X + creditbtn.Texture2DWidth &&
	                touchPosition.Y >= creditbtn.location.Y &&
	                touchPosition.Y < creditbtn.location.Y + creditbtn.Texture2DHeight)
	                {
	                    GameState = gamecredit;
                        stopSoundInstance();
	                    //i removed//  if (sound == true) Sfwd.Play();
	                    playSound(Sfwd,0,sound);
	                    return true;
	             
	                }
	            }
	            //else if (showInfo == true)
	            //{
	            //    //this used to control dismissed button
	            //    if (touchPosition.X >= btnDismmiss.location.X &&
	            //    touchPosition.X < btnDismmiss.location.X + btnDismmiss.Texture2DWidth &&
	            //    touchPosition.Y >= btnDismmiss.location.Y &&
	            //    touchPosition.Y < btnDismmiss.location.Y + btnDismmiss.Texture2DHeight)
	            //    {
	            //        showInfo = false;
	            //        return true;

	            //    }
	            //}


	            return false;
	        }

	        Boolean PausedTouch(Vector2 touchPosition)
	        {
	            if (touchPosition.X >= soundenabled.location.X &&
	            touchPosition.X < soundenabled.location.X + soundenabled.Texture2DWidth &&
	            touchPosition.Y >= soundenabled.location.Y &&
	            touchPosition.Y < soundenabled.location.Y + soundenabled.Texture2DHeight)
	            {
	                if (sound == false) { sound = true; if (sound == true)  return true; playSound(Sfwd, 0, sound);}//i removed //Sfwd.Play();
	                else if (sound == true) { sound = false; return true; }

	            }
	            else if (touchPosition.X >= musicEnabled.location.X &&
	          touchPosition.X < musicEnabled.location.X + musicEnabled.Texture2DWidth &&
	           touchPosition.Y >= musicEnabled.location.Y &&
	           touchPosition.Y < musicEnabled.location.Y + musicEnabled.Texture2DHeight)
	            {
	                if (mediaEnabled == false) { mediaEnabled = true; if (sound == true)  return true; playSound(Sfwd, 0, sound); }//i removed //Sfwd.Play();
	                else if (mediaEnabled == true) { mediaEnabled = false; return true; }
	                return true;

	            }

	            else if (touchPosition.X >= storebtn.location.X &&
	            touchPosition.X < storebtn.location.X + storebtn.Texture2DWidth &&
	            touchPosition.Y >= storebtn.location.Y &&
	            touchPosition.Y < storebtn.location.Y + storebtn.Texture2DHeight)
	            {
	                showGuns = true;
	              //i removed//  if (sound == true) Sfwd.Play();
                 playSound(Sfwd, 0, sound);
	                return true;
	            }

	            else if (touchPosition.X >= ResumePos.X &&
	          touchPosition.X < ResumePos.X + btnSize.X &&
	          touchPosition.Y >= ResumePos.Y &&
	          touchPosition.Y < ResumePos.Y + btnSize.Y)
	            {
                 //here we set ads to be invisible
               //  EvilBugsActivity.mAdView.setVisibility(View.INVISIBLE);
	                Dpaused = false;
	              //i removed//  if (sound == true) Sfwd.Play();
	                playSound(Sfwd, 0, sound);
                 playMedia(mediaEnabled);
	            }

	            else if (touchPosition.X >= RestartPos.X &&
	         touchPosition.X < RestartPos.X + btnSize.X &&
	         touchPosition.Y >= RestartPos.Y &&
	         touchPosition.Y < RestartPos.Y + btnSize.Y)
	            {
	                gameRestart();
                 Preset();
	              //i removed//  if (sound == true) Sfwd.Play();
                 playSound(Sfwd, 0, sound);
	                return true;
	            }

	            else if (touchPosition.X >= QuitPos.X &&
	        touchPosition.X < QuitPos.X + btnSize.X &&
	        touchPosition.Y >= QuitPos.Y &&
	        touchPosition.Y < QuitPos.Y + btnSize.Y)
	            {
	                gameQuit();
                 Preset();
	              //i removed//   if (sound == true) Sfwd.Play();
                 playSound(Sfwd, 0, sound);

	            }

	            return false;
	        }

	        Boolean GameOverTouch(Vector2 touchPosition)
	        {
	            if (touchPosition.X >= HomeBtn.X &&
	            touchPosition.X < HomeBtn.X + GmOverBtns.X &&
	            touchPosition.Y >= HomeBtn.Y &&
	            touchPosition.Y < HomeBtn.Y + GmOverBtns.Y)
	            {
	                gameQuit();
	                Preset();
	               //i remove// if (sound == true) Sfwd.Play();
	                playSound(Sfwd, 0, sound);
	                return true;
	            }

	            else if (touchPosition.X >= StoreBtn.X &&
	          touchPosition.X < StoreBtn.X + GmOverBtns.X &&
	          touchPosition.Y >= StoreBtn.Y &&
	          touchPosition.Y < StoreBtn.Y + GmOverBtns.Y)
	            {

	                showGuns = true;
	                //i remove// if (sound == true) Sfwd.Play();
	                playSound(Sfwd, 0, sound);
	                return true;
	            }

	            else if (touchPosition.X >= Playbtn.X &&
	         touchPosition.X < Playbtn.X + GmOverBtns.X &&
	         touchPosition.Y >= Playbtn.Y &&
	         touchPosition.Y < Playbtn.Y + GmOverBtns.Y)
	            {

	                //i remove// if (sound == true) Sfwd.Play();
	                playSound(Sfwd, 0, sound);
                 gameRestart();
                 Preset();
                 GameState = gameInplay;
	                return true;
	            }
	            else if (touchPosition.X >= fbBtn.X &&
	        touchPosition.X < fbBtn.X + fbbtnSize.X &&
	        touchPosition.Y >= fbBtn.Y &&
	        touchPosition.Y < fbBtn.Y + fbbtnSize.Y)
	            {
	                //do facebook post of how meters he ran
	               // Submit(SocialNetwork.Facebook);
	                return true;
	            }
	        
	            else if (touchPosition.X >= btnPrevious.x &&
	     touchPosition.X < btnPrevious.x + btnPrevious.Texture2DWidth &&
	     touchPosition.Y >= btnPrevious.y &&
	     touchPosition.Y < btnPrevious.y + btnPrevious.Texture2DHeight )//i removed //&& leaderboards.hasPrevious == true
	            {
	            	//if (sound == true) Sfwd.Play();
	            	playSound(Sfwd, 0, sound);
	                loadback();
	                return true;
	            }
	            else if (touchPosition.X >= btnNext.x &&
	      touchPosition.X < btnNext.x + btnNext.Texture2DWidth &&
	      touchPosition.Y >= btnNext.y &&
	      touchPosition.Y < btnNext.y + btnNext.Texture2DHeight )//i removed //&& leaderboards.hasNext == true
	            { 
	            	//if (sound == true) Sfwd.Play();
	            	playSound(Sfwd, 0, sound);
	                loadforward();
	                return true;
	            }
	            return false;
	        }

	        Boolean BoostTouch(Vector2 touchPosition)
	        {

	            if (!CoinMagnet )
	            {
	                if (touchPosition.X >= iMagnet.x &&
	                touchPosition.X < iMagnet.x + iMagnet.Texture2DWidth &&
	                touchPosition.Y >= iMagnet.y &&
	                touchPosition.Y < iMagnet.y + iMagnet.Texture2DHeight)
	                {
	                    if (MyGun.TotalCoins >= PCoinMagnet && ACoinMagnet == 0)
	                    {
	                        MyGun.TotalCoins -= PCoinMagnet;
	                        CoinMagnet = true;
	                     //i remove//   if (sound == true) Sbuy.Play();
	                        playSound(Sfwd, 0, sound);
	                        //Play sound to show magnet have been activated
	                    }
	                    else if (ACoinMagnet > 0)
	                    {
	                        ACoinMagnet -= 1;
	                        CoinMagnet = true;
	                      //i remove//   if (sound == true) Sfwd.Play();
	                        playSound(Sfwd, 0, sound);
	                        //Play sound to show magnet have been activated
	                    }
	                    else if (MyGun.TotalCoins < PCoinMagnet)
	                    {
	                        //play a reject sound 
	                    	//i remove//   if (sound == true) Sback.Play();
	                    	playSound(Sfwd, 0, sound);
	                        Dpaused = true;
	                        morecoins = true;
	                      //i remove//   Purchase.igetInventory = false;
	                       // buyClick = true;
	                    }

	                    return true;
	                }
	            }
	            if (Coindoubler == false)
	            {
	                if (touchPosition.X >= icoindouble.x &&
	                   touchPosition.X < icoindouble.x + icoindouble.Texture2DWidth &&
	                   touchPosition.Y >= icoindouble.y &&
	                   touchPosition.Y < icoindouble.y + icoindouble.Texture2DHeight)
	                {

	                    if (MyGun.TotalCoins >= PCoindoubler && ACoindoubler == 0)
	                    {
	                        MyGun.TotalCoins -= PCoindoubler;
	                        Coindoubler = true;
	                      //i remove//    if (sound == true) Sbuy.Play();
	                        playSound(Sfwd, 0, sound);
	                        //Play sound to show magnet have been activated
	                    }
	                    else if (ACoindoubler > 0)
	                    {
	                        ACoindoubler -= 1;
	                        Coindoubler = true;
	                      //i remove//   if (sound == true) Sfwd.Play();
	                        playSound(Sfwd, 0, sound);
	                        //Play sound to show magnet have been activated
	                    }
	                    else if (MyGun.TotalCoins < PCoindoubler)
	                    {
	                        //play a reject sound 
	                    	//i remove//   if (sound == true) Sback.Play();
	                    	playSound(Sback, 0, sound);
	                        Dpaused = true;
	                        morecoins = true;
	                      //i remove//   Purchase.igetInventory = false;
	                       // buyClick = true;
	                    }
	                    
	                    return true;
	                }
	            }


	            if (touchPosition.X >= ithunder.x &&
	        touchPosition.X < ithunder.x + ithunder.Texture2DWidth &&
	        touchPosition.Y >= ithunder.y &&
	        touchPosition.Y < ithunder.y + ithunder.Texture2DHeight)
	            {
	                if (MyGun.TotalCoins >= Pthunder)
	                {
	                    Athunder += 1;
	                    MyGun.TotalCoins -= Pthunder;
	                  //i remove//       if (sound == true) Sbuy.Play();
	                    playSound(Sbuy, 0, sound);
	                }
	                else if (MyGun.TotalCoins < Pthunder)
	                {
	                    //play reject sound
	                	//i remove//    if (sound == true) Sback.Play();
	                	playSound(Sback, 0, sound);
	                    Dpaused = true;
	                    morecoins = true;
	                   // buyClick = true;
	                }
	                return true;
	            }
	            

	            return false;
	        }

	        void gameQuit()
	        {
	            //quit
	            GameState = gameStart;

	           
	            coinnum = 0;

	            achievements.GameOver();
	           
	           // iLoadContent();
	            Preset();
	            Dpaused = false;
	        }

	        void gameRestart()
	        {
             //here we set ads to be invisible
           //  EvilBugsActivity.mAdView.setVisibility(View.INVISIBLE);

	        	// new 
	        	achievements.GameOver();

             //new
             explorer.setLocation(new Vector2(-200, 540));

             //make boost items to show
	            BoostClick = true;
	            BoostAvail = 0;
	            fader = 220;
	            
	            coinnum = 0;	           

	            //retart game when clicked
	            CoinMagnet = false;
	            Coindoubler = false;
	            GCoins = new ArrayList<Coins>();
	            
	            explorerlife = 100;
	            frame = 0;
	            eFrame = 0;
	            //  bgSpeed = 220;
	            bgSpeed = 1;
	            bg_X = 0; bg_Y = iHeight(320);  //bgygap is used to fill up the space that was used to jump
	            gd_Y = iHeight(656); // gd_X2 = 0,
	            iDust = new ArrayList<Dust>();;
	            jump = false;
	            jumpVelocity = iHeight(18);//Jump mangitude
	            Gravity =  iHeight(1);
	            fallvelocity =  iHeight(2);
	            Mground = new ArrayList<groundObjects>();
	            floorlevel = new ArrayList<Integer>();
	            GroundNames = new ArrayList<Integer>();//removed       
	            myMosquitoes = new ArrayList<Mosquitoes>();
	            forebg_Objects = new ArrayList<Sprite>();
	            MyBullets = new ArrayList<Bullet>();
	            MoveGround = false;
	            Fgexplorer = false;

	            metersRan = 0; //this is used to reset how long you ran


	            GameState = gameInplay;
	            Dpaused = false;

	            // UnloadContent();
	            //LoadContent();
	            achievements.SelectStage();


	            //Reset parameters for transit
	            village = 1 + (int)(Math.random() * ((4 - 1)));
	            if (village == bugForest) bg = new Sprite(icontent, ScreenSize, R.drawable.backgroundloop1);
	            else if (village == nBugForest) bg = new Sprite(icontent, ScreenSize, R.drawable.nbgloop);
	            else if (village == bugVillage) bg = new Sprite(icontent, ScreenSize, R.drawable.bg1);
	            Intransit = false; //this is done to start transition in background
	            bgChanged = false;
               // startTransit = 100 + (int)(Math.random() * (100 - 50));
               // nearIntransit = startTransit - 50;//it takes about 23 metres to pass one tile
              startTransit = 700 + (int)(Math.random() * (300 - 100)); // Object_Picker.Next(100, 300);
	            nearIntransit = startTransit - 340;//it takes about 23 metres to pass one tile
	            transit.location.X = iWidth(10) + deviceWidth;
	            transit.location.Y = 0;
	           

	            //load create stuff
	            crateshow = iWidth(50) + iWidth((int)(Math.random() * (100 - 50)));
	            crateshow += metersRan;
	            cratetouched = false;
	            getcrate = false;
	            movement = run;
	           // movement = fly;
	           // movement = flypaddling;

	            //reset movement frame
	            Shuttleimageindex = 0;
	            padimageindex = 0;
	        	
	        	
	        }



 //When deativatiing
 public static void SaveGame()
 {
  if (settings != null)//&& GameState != gameLoading && GameState != gamewall && GameState != gameStart
  {
   if (MyGun != null)
   {
    settings.TotalCoins = MyGun.TotalCoins;

    settings.PurchasedID = MyGun.PurchasedID;

    settings.EquipedID = MyGun.EquipedID;

    settings.UnLimitedAmmo = MyGun.UnLimitedAmmo;

    settings.AmmoLevel = MyGun.AmmoLevel;

    settings.PresentID = MyGun.PresentID;
   }

   //Adverst settings
   //settings.adEnabled = adEnabled;

   //save user name
   settings.userName = userName;

   //Never rating app settings
   settings.rating_Never = rating_Never;

   //Boost and power Ups
   settings.thunderAvail = Athunder;

   settings.coindoublerAvail = ACoindoubler;

   settings.magnetAvail = ACoinMagnet;

   settings.sound = sound; //saving if sound efffect enabled

   settings.mediaEnabled = mediaEnabled; //saving if song enabled

   if (GameState == gameInplay) {
    saveBugs();
    saveGrounds();
    saveCoins();
    Dpaused = true;

    settings.explorerX = explorer.location.X;

    settings.explorerY = explorer.location.Y;

    settings.village = village;

    //saving new stuff
    settings.movement = movement;

    settings.EnergyCount = EnergyCount;

    settings.Energyframe = Energyframe;

    settings.shuttleX = flyingShuttle.location.X;

    settings.shuttleY = flyingShuttle.location.Y;

    settings.padX = flyingpad.location.X;

    settings.padY = flyingpad.location.Y;

   //i removed so i can work on u later// settings.bg_y = bg_Y; //save background camera location

    //saveing transit parameters
    settings.transitX = transit.location.X;
    settings.transitY = transit.location.Y;
    settings.startTransit = startTransit;
    settings.nearIntransit = nearIntransit;
    settings.Intransit = Intransit;
    settings.bgChanged = bgChanged;
   }
   // else GameState = gameStart;

   //loading achievements
   if (achievements != null)
   {
    saveAchievements();
   }
  }
  //Note this class has an auto save for values but it cannot save list of classes so am doing it manually
  settings.Save();
 }

 private static void saveBugs()
 {
  settings.Mosquitoes = "";

  for(int i = 0; i < myMosquitoes.size(); i++)
  {

   if (settings.Mosquitoes == "")
   {

    settings.Mosquitoes = String.valueOf(myMosquitoes.get(i).Type);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).Texture2DWidth);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).Texture2DHeight);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).groundY);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).MX);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).MY);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).bgyGap);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).isLife);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).mframe);
   }
   else if (settings.Mosquitoes.endsWith("&"))
   {
    settings.Mosquitoes += String.valueOf(myMosquitoes.get(i).Type);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).Texture2DWidth);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).Texture2DHeight);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).groundY);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).MX);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).MY);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).bgyGap);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).isLife);
    settings.Mosquitoes += "," + String.valueOf(myMosquitoes.get(i).mframe);

   }
   if (i < myMosquitoes.size() - 1) settings.Mosquitoes += "&"; //We use this symbol to seperate classes

   //( int type, int spriteWidth, int spriteHeight, float gY, Vector2 explorer, float X, float Y, Random mrand,int groundgap)
  }
 }

 private static void saveGrounds()
 {
  settings.ground = "";

  for (int i = 0; i < Mground.size(); i++)
  {
   if (settings.ground == "")
   {

    settings.ground = String.valueOf(Mground.get(i).name);
    settings.ground += "," + String.valueOf(Mground.get(i).Position.X);
    settings.ground += "," + String.valueOf(Mground.get(i).Position.Y);
    settings.ground += "," + String.valueOf(Mground.get(i).floorY);
   }
   else if (settings.ground.endsWith("&"))
   {
    settings.ground += String.valueOf(Mground.get(i).name);
    settings.ground += "," + String.valueOf(Mground.get(i).Position.X);
    settings.ground += "," + String.valueOf(Mground.get(i).Position.Y);
    settings.ground += "," + String.valueOf(Mground.get(i).floorY);

   }
   if (i < Mground.size() - 1) settings.ground += "&";////We use this symbol to seperate classes
   // string Gname, Vector2 GPosition,float Yfloor
  }
 }

 private static void saveCoins()
 {
  settings.coins = "";

  for (int i = 0; i < GCoins.size(); i++)
  {
   if (settings.coins == "")
   {
    settings.coins = String.valueOf(GCoins.get(i).type);
    settings.coins += "," + String.valueOf(GCoins.get(i).Texture2DWidth);
    settings.coins += "," + String.valueOf(GCoins.get(i).Texture2DHeight);
    settings.coins += "," + String.valueOf(GCoins.get(i).Mx);
    settings.coins += "," + String.valueOf(GCoins.get(i).My);
   }
   else if (settings.coins.endsWith("&"))
   {
    settings.coins += String.valueOf(GCoins.get(i).type);
    settings.coins += "," + String.valueOf(GCoins.get(i).Texture2DWidth);
    settings.coins += "," + String.valueOf(GCoins.get(i).Texture2DHeight);
    settings.coins += "," + String.valueOf(GCoins.get(i).Mx);
    settings.coins += "," + String.valueOf(GCoins.get(i).My);

   }
   if (i < GCoins.size() - 1) settings.coins += "&"; //We use this symbol to seperate classes
   // int Type,Texture2D Myname,int spriteWidth,int spriteHeight, float X, float Y)
  }
 }

 private static void saveAchievements()
 {
  //mission level
  settings.Mission = achievements.Mission;
  settings.LeveCount = achievements.LeveCount;

  //F stuff saving
  settings.CoinsColl = achievements.CoinsColl;
  settings.TotalCoinsColl = achievements.TotalCoinsColl;
  settings.TCoin = achievements.TCoin;

  //Mission stuffs
  settings.istages = achievements.istages;
  settings.stages = achievements.stages;
  settings.istagesMirror = achievements.istagesMirror;
  settings.varLevels = achievements.varLevels;
  settings.A = achievements.A;
  settings.B = achievements.B;
  settings.C = achievements.C;
  settings.D = achievements.D;
  settings.E = achievements.E;
  settings.F = achievements.F;

  //Mission E stuff
  settings.MosGuns = achievements.MosGuns;
  settings.WaspGuns = achievements.WaspGuns;
  settings.BeeGuns = achievements.BeeGuns;
  settings.TMosGuns = achievements.TMosGuns;
  settings.TWaspGuns = achievements.TWaspGuns;
  settings.TBeeGuns = achievements.TBeeGuns;
  settings.TFMosGuns = achievements.TFMosGuns;
  settings.TFWaspGuns = achievements.TFWaspGuns;
  settings.TFBeeGuns = achievements.TFBeeGuns;

  //Mission B Stuff
  settings.BugForest = achievements.BugForest;
  settings.NBugForest = achievements.NBugForest;
  settings.BugVillage = achievements.BugVillage;
  settings.TBugForest = achievements.TBugForest;
  settings.TNBugForest = achievements.TNBugForest;
  settings.TBugVillage = achievements.TBugVillage;
  settings.TFBugForest = achievements.TFBugForest;
  settings.TFNBugForest = achievements.TFNBugForest;
  settings.TFBugVillage = achievements.TFBugVillage;

  //Misson A stuff
  settings.MosKilled = achievements.MosKilled;
  settings.WaspKilled = achievements.WaspKilled;
  settings.BeeKilled = achievements.BeeKilled;
  settings.TMkilled = achievements.TMkilled;
  settings.TWkilled = achievements.TWkilled;
  settings.TBkilled = achievements.TBkilled;
  settings.TMosKilled = achievements.TMosKilled;
  settings.TWaspKilled = achievements.TWaspKilled;
  settings.TBeeKilled = achievements.TBeeKilled;

  //Mission C stuff
  settings.LastRecord = achievements.LastRecord;

  scoreID = settings.scoreID;  //save score ID so we can always update the same score from the same player

 }

 //When activating
 public void LoadGame()
 {
  if (settings != null)
  {
   //Ad settings
   adEnabled = settings.adEnabled;

   sound = settings.sound; //saving if sound efffect enabled

   mediaEnabled = settings.mediaEnabled ; //saving if song enabled

   //save user name
   userName = settings.userName;

   //Never Rating
   rating_Never = settings.rating_Never;

   MyGun.TotalCoins = settings.TotalCoins;

   MyGun.PurchasedID = settings.PurchasedID;

   MyGun.EquipedID = settings.EquipedID;

   MyGun.UnLimitedAmmo = settings.UnLimitedAmmo;

   MyGun.AmmoLevel = settings.AmmoLevel;

   MyGun.PresentID = settings.PresentID;


   //Boost and power Ups
   Athunder = settings.thunderAvail;

   ACoindoubler = settings.coindoublerAvail;

   ACoinMagnet = settings.magnetAvail;


   //loading achievements
   if (achievements != null)
   {
    loadAchievements();
   }

  }
 }

 void loadBugs()
 {
  if (settings.Mosquitoes != "")
  {
   myMosquitoes = new ArrayList<Mosquitoes>();

   String[] mosquitoesClass = settings.Mosquitoes.split("&");

   for (int i = 0; i < mosquitoesClass.length; ++i)
   {
    String[] mosquitoes = mosquitoesClass[i].split(",");

    //this is used to know if to add mosquitoes wasp or bettle
    if ( Integer.parseInt(mosquitoes[0]) == 1)
    {
     myMosquitoes.add(new Mosquitoes(ScreenSize, Mosquitoe,Mosquitoefliped, 1, 160,
             120, Integer.parseInt(mosquitoes[3]), Float.parseFloat(mosquitoes[4]),
             Float.parseFloat(mosquitoes[5]), Integer.parseInt(mosquitoes[6]), Boolean.parseBoolean(mosquitoes[7]), Integer.parseInt(mosquitoes[8])));
    }
    else if (Integer.parseInt(mosquitoes[0]) == 2)
    {
     myMosquitoes.add(new Mosquitoes(ScreenSize, wasp, waspfliped,
             2,  160, 120,  Integer.parseInt(mosquitoes[3]),Float.parseFloat(mosquitoes[4]),
             Float.parseFloat(mosquitoes[5]), Integer.parseInt(mosquitoes[6]), Boolean.parseBoolean(mosquitoes[7]), Integer.parseInt(mosquitoes[8])));
    }
    else if (Integer.parseInt(mosquitoes[0]) == 3)
    {
     myMosquitoes.add(new Mosquitoes(ScreenSize, bettle,bettlefliped, 3, 160,
             120, Integer.parseInt(mosquitoes[3]),Float.parseFloat(mosquitoes[4]),
             Float.parseFloat(mosquitoes[5]), Integer.parseInt(mosquitoes[6]),Boolean.parseBoolean(mosquitoes[7]),Integer.parseInt(mosquitoes[8])));
    }
   }

  }
  //(Vector2 canvas, Texture2D image, int type, int spriteWidth, int spriteHeight,
  //float gY, Vector2 explorer, float X, float Y, Random mrand,int groundgap)
 }


 void LoadGrounds()
 {
  if (settings.ground != "")
  {
   Mground = new ArrayList<groundObjects>();

   String[] groundClass = settings.ground.split("&");
   for (int i = 0; i < groundClass.length; ++i)
   {
    String[] grounds = groundClass[i].split(",");

    //Adds Ground elements
    Mground.add(new groundObjects(icontent, ScreenSize, Integer.parseInt(grounds[0]), new Vector2(Float.parseFloat(grounds[1]), Float.parseFloat(grounds[2]), false), Integer.parseInt(grounds[3])));
   }
  }
  //ContentManager game,Vector2 canvas, string Gname, Vector2 GPosition,float Yfloor)//expected class values
 }


 void loadCoins()
 {
  if (settings.coins != "")
  {
   GCoins = new ArrayList<Coins>();
   String[] coinClass = settings.coins.split("&");
   for (int i = 0; i < coinClass.length; ++i)
   {
    String[] coins = coinClass[i].split(",");

    //Adds small coins
    if (Integer.parseInt(coins[0]) == 2)
    {
     GCoins.add(new Coins(ScreenSize, Integer.parseInt(coins[0]), bigcoin, Integer.parseInt(coins[1]), Integer.parseInt(coins[2]), Float.parseFloat(coins[3]), Float.parseFloat(coins[4])));
    }
    //Add big coin
    else
    {
     GCoins.add(new Coins(ScreenSize, Integer.parseInt(coins[0]), smallcoin, Integer.parseInt(coins[1]), Integer.parseInt(coins[2]), Float.parseFloat(coins[3]), Float.parseFloat(coins[4])));
    }

    //(Vector2 canvas,int Type,Texture2D Myname,int spriteWidth,int spriteHeight, float X, float Y) //values
   }
  }
 }


 void loadAchievements()
 {
  if (settings.stages[0] != null)
  {
   //mission level
   achievements.Mission = settings.Mission;
   achievements.LeveCount = settings.LeveCount;

   //F stuff saving
   achievements.CoinsColl = settings.CoinsColl;
   achievements.TotalCoinsColl = settings.TotalCoinsColl;
   achievements.TCoin = settings.TCoin;

   //Mission stuffs
   achievements.istages = settings.istages;
   achievements.stages = settings.stages;
   achievements.istagesMirror = settings.istagesMirror;
   achievements.varLevels = settings.varLevels;
   achievements.A = settings.A;
   achievements.B = settings.B;
   achievements.C = settings.C;
   achievements.D = settings.D;
   achievements.E = settings.E;
   achievements.F = settings.F;

   //Mission E stuff
   achievements.MosGuns = settings.MosGuns;
   achievements.WaspGuns = settings.WaspGuns;
   achievements.BeeGuns = settings.BeeGuns;
   achievements.TMosGuns = settings.TMosGuns;
   achievements.TWaspGuns = settings.TWaspGuns;
   achievements.TBeeGuns = settings.TBeeGuns;
   achievements.TFMosGuns = settings.TFMosGuns;
   achievements.TFWaspGuns = settings.TFWaspGuns;
   achievements.TFBeeGuns = settings.TFBeeGuns;

   //Mission B Stuff
   achievements.BugForest = settings.BugForest;
   achievements.NBugForest = settings.NBugForest;
   achievements.BugVillage = settings.BugVillage;
   achievements.TBugForest = settings.TBugForest;
   achievements.TNBugForest = settings.TNBugForest;
   achievements.TBugVillage = settings.TBugVillage;
   achievements.TFBugForest = settings.TFBugForest;
   achievements.TFNBugForest = settings.TFNBugForest;
   achievements.TFBugVillage = settings.TFBugVillage;

   //Misson A stuff
   achievements.MosKilled = settings.MosKilled;
   achievements.WaspKilled = settings.WaspKilled;
   achievements.BeeKilled = settings.BeeKilled;
   achievements.TMkilled = settings.TMkilled;
   achievements.TWkilled = settings.TWkilled;
   achievements.TBkilled = settings.TBkilled;
   achievements.TMosKilled = settings.TMosKilled;
   achievements.TWaspKilled = settings.TWaspKilled;
   achievements.TBeeKilled = settings.TBeeKilled;

   //Mission C stuff
   achievements.LastRecord = settings.LastRecord;

   scoreID = settings.scoreID;
  }


 }




	   public EvilBugsView(Context context)//, AttributeSet attrs
       {
	 		super(context);//, attrs
	 		// TODO Auto-generated constructor stub
	 		//Note the attribute here is to help save thing in xml
	 		holder = getHolder();
	 		holder.addCallback(this);
	 		mContext = context;
	 		setFocusable(true);
	 		asyncService = AsyncApp42ServiceApi.instance(); //this is leaderboard stuff

       }
	     
	     @Override
	     public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	 		// TODO Auto-generated method stub

	 	}
	  
	 	@Override
	 	public void surfaceCreated(SurfaceHolder holder) {
	 		// TODO Auto-generated method stub

         try{

          //Here we do this so that if game goes into background we dont need to restart it again
          if(Menus == null ) {
           ContentLoad();
          }
				//ContentLoad();
				//dont call loadcontent more than once in an andriod game because it loads everything at once

          settings = new Settings();
          if(settings.mysettings.getBoolean("idata", false)) {
           settings.Load();
           LoadGame();
          }
          else
          {
           settings = new Settings();
          }

          //here we handle thread if game goes into background or comes back from background and
          //we use gamerunning to know if we will draw canvas or not
          if(thread == null) {
           thread = new EvilBugsThread(holder);
           thread.start();
           gamerunning = true;
          }
          else if(!thread.isAlive()) {
           thread = new EvilBugsThread(holder);
           thread.start();
           gamerunning = true;
          }
			}
			catch(Exception ex)
			{
				ex.getMessage();
					System.exit(0); 
			}



	 	}

	 	@Override
	 	public void surfaceDestroyed(SurfaceHolder holder) {
	 		// TODO Auto-generated method stub
	 		//thread.destroy();
         stopSoundInstance();
         gamerunning = false;
         //started = false;
         boolean retry = true;
         while (retry) {
          try {
           thread.join();
                   retry = false;
          } catch (InterruptedException e) {
           // try again shutting down the thread
          }
         }

          // very important:
	 		 if (mHelper != null) mHelper.dispose();
	         mHelper = null;
	 	}

	 	
	 	  //these creates the surface or takes over the surface of the phone
	     private SurfaceHolder mSurfaceHolder;
	         
	      //This is the thread that calls update and draw method in your games
	  	public class EvilBugsThread extends Thread{
	  		
	 		public EvilBugsThread(SurfaceHolder surfaceHolder){
	  			mSurfaceHolder = surfaceHolder;
	  		} 
	  		
	 		 //new 


           @Override
	  	public void run() {
	  		Canvas c;
	  		//Log.d(TAG, "Starting game loop");
            long lastLoopTime = System.nanoTime();
            final int TARGET_FPS = 40;//60
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;//1000000000
	  	     //new
         // work out how long its been since the last update, this
         // will be used to calculate how far the entities should
         // move this loop



            while (gamerunning) {

	  			c = null;
	  			// try locking the canvas for exclusive pixel editing
	  			// in the surface
	  			try {
                 long now = System.nanoTime();
                 long updateLength = now - lastLoopTime;
                 lastLoopTime = now;
                 double delta = updateLength / ((double)OPTIMAL_TIME);
                 double aa = 0;

	  				c = mSurfaceHolder.lockCanvas();
                 if(c != null) {
                  synchronized (mSurfaceHolder) {

                   delta = 0.033333;  //0.033333299;
                   // aa =  delta /30 ;
                   //Log.i("delta" , String.valueOf(delta));
                  // Log.i("new delta" , String.valueOf(aa));
                   // update game state

                   //Log.i("smoothedDeltaRealTime_ms" , smoothedDeltaRealTime_ms)
                  // delta = smoothedDeltaRealTime_ms / 1000;//1000

                   try {
                     OnUpdate(delta);

                   } catch (Exception e) {
                    String errorString = e.getMessage().toString();
                    System.exit(0);
                   }


                   //if( ActionDown == true)
                   // {
                   //	 LeftRight(touchPosition);
                   // }
                   // render state to the screen
                   // draws the canvas on the panel

                   try {
                    doDraw(c);

                   } catch (Exception e) {
                    String errorString = e.getMessage().toString();
                    System.exit(0);
                   }
                   //	doDraw(c);




//	  					try{
//	 				    	  Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);
//	 				    	  }
//	 				    	  catch (Exception e) {
//	 				    		  String msg = e.getMessage().toString();
//	 				    		  
//								// TODO: handle exception
//							}
                  }
                 }
	  			} finally {
	  				// in case of an exception the surface is not left in
	  				// an inconsistent state
	  				if (c != null) {
	  					mSurfaceHolder.unlockCanvasAndPost(c);
	  				}


	  			}	// end finally
             // we want each frame to take 10 milliseconds, to do this
             // we've recorded when we started the frame. We add 10 milliseconds
             // to this and then factor in the current time to give
             // us our final value to wait for
             // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
             try{ Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);}
             catch (Exception e) {}

	  		}
	  	}

	  		
	  		private void OnUpdate(double gameTime)
	  		{
             //just testing
//             if(GameState == gameInplay && crazywait < 15)
//             {
//              crazywait += 1;
//             }

	  		  // Allows the game to exit
                if (backbuttonpressed == true)
                {
                	 if (GameState == gamecredit)
                     {
                         GameState = gameStart;
                        // Preset();

                     }
                     else if (GameState == gameGuns)
                     {
                         if (MyGun.selID == 20)
                         {
                             GameState = gameStart;
                         }
                         else if (MyGun.moreCoins == true || morecoins == true)
                         {
                             MyGun.moreCoins = false; 
                             morecoins = false; 
                            //i remove// Purchase.igetInventory = false;
                           showboard = false;
                         }
                         else MyGun.selID = 20;

                         //When selID , when is 20 the game gun info would show else it wont show

                     }

                     else if (GameState == gameInplay && Dpaused == true && showGuns == true)
                     {

                         if (MyGun.moreCoins == true || morecoins == true)
                         {
                        	 MyGun.moreCoins = false;
                        	 morecoins = false;
                        	 //i remove//  Purchase.igetInventory = false; 
                        	 showboard = false;
                        	 }

                         else if (MyGun.selID != 20)
                         {
                             MyGun.selID = 20;
                         }
                         else
                         {
                             showGuns = false;
                         }

                     }
                     else if (GameState == gameInplay && Dpaused == false && tutor == true)
                     {
                         tutor = false;
                     }
                     else if (GameState == gameInplay && Dpaused == false)
                     {
                         Dpaused = true;
                      //here we set ads to be invisible
                     // EvilBugsActivity.mAdView.setVisibility(View.VISIBLE);

                     }
                     else if (GameState == gameInplay && Dpaused == true)
                     {
                         if (MyGun.moreCoins == true || morecoins == true)
                         {
                        	 MyGun.moreCoins = false;
                        	 morecoins = false;
                        	 //i remove//  Purchase.igetInventory = false;
                        	 showboard = false;
                        	 }
                         else
                         {
                          Dpaused = false;
                          //here we set ads to be invisible
                        //  EvilBugsActivity.mAdView.setVisibility(View.INVISIBLE);

                         }

                     }
                     else if (GameState == gameLoading || GameState == gamewall || GameState == gameStart)
                     {
                         if (showInfo == true) showInfo = false;
                         else GameState = gameLoading;

                      if(EvilBugsView.settings != null)
                      {
                       EvilBugsView.SaveGame();
                      }
                      stopSoundInstance();
                      System.exit(0);


                      //these code is to exit game
                     // ((Activity)(mContext)).finish();

                     // mContext.
                     // finish();
                         // this.Exit();
                         //Exit = true;

                     }
                     else if (GameState == gameOver)
                     {
                       
                         if (MyGun.moreCoins == true || morecoins == true)
                         { 
                        	 MyGun.moreCoins = false;
                        	 morecoins = false; 
                        	 //i remove//  Purchase.igetInventory = false; 
                        	showboard = false;
                        	 }
                         else if (MyGun.selID != 20)
                         {
                             MyGun.selID = 20;
                         }
                         else if (showGuns )
                         {
                             showGuns = false;
                         }
                         else
                         {
                             GameState = gameStart;
                             Preset();
                             gameQuit();

                         }

                     }

                     if (morecoins  && showboard)
                     {
                    	 morecoins = false;
                         showboard = false;
                     }

                   //i remove//if (sound == true) Sback.Play();// play back sound
                     playSound(Sback, 0, sound);

                     backbuttonpressed = false;
                }

	                



	                //This Method controls all sounds in this game
	             // i remove // Sounds();

	                if (GameState == gameLoading || GameState == gamewall || GameState == gameStart)
	                {
	                	//Log.i("Prestart", "Method called");
	                    PreStart(gameTime);

	                   //i remove// MenuTouch();//This is for menu touch recognition

	                 // i remove // if (adEnabled == true)
	                 // i remove // {
	                        // Update the ad control.
	                 // i remove //      AdComponent.Current.Update(gameTime.ElapsedTime);
	                 // i remove //  }

	                    //aderror.update(error, 0, 0);

	                    if (showInfo == true)
	                    {


	                        if (infostatus != null)
	                        {
	                            int startX; //this and code below is used to control the line for writing letters on screen
	                            if (infostatus.length() < 25) startX = iWidth(275);
	                            else startX = iWidth(224);
	                            if (infostatus.length() < 1) infostatus = "connecting";
	                            infoLetters.update(infostatus, startX, iHeight(212));
	                            infoLetters.MaxLine = iWidth(460);
	                        }
	                        
	                    }
	                }
	                else if (GameState == gameGuns || showGuns == true)
	                {
	                	 //i remove//   MenuTouch();

	                    if (MyGun.fwdClick == true)
	                    {
	                        //play forward sound when clicked
	                    	// i remove // if (sound == true) Sfwd.Play();
	                    	playSound(Sfwd,0,sound);
	                        MyGun.fwdClick = false;
	                    }

	                    if (MyGun.buyClick == true)
	                    {
	                        //play back sound when clicked
	                    	// i remove //  if (sound == true) Sbuy.Play();
	                    	playSound(Sbuy,0,sound);



	                        MyGun.buyClick = false;
	                    }

	                    if (MyGun.moreCoins == true)
	                    {
	                    	morecoins = true;
	                       
	                    }


	                }
	                else if (GameState == gameInplay  )//&& crazywait >= 15
	                {

	                	 //i remove//   MenuTouch();
	                    //Use to control the speed of the explorer
	                	//Log.i("achievements.Updatestage", "Method called");
	                    achievements.Updatestage();  //this is used to keep track of what player has achieved
	                	//tutor = false;

	                    if (MyGun.bulletEmpty)
	                    {
	                    	//Log.i(" ReloadGun()", "Method called");
	                        ReloadGun();
	                    }

	                    if (MyGun.reload == true)
	                    {
	                      //iremove//  if (sound == true) Sreload.Play();//plays reload sound
	                    	playSound(Sreload,0,sound);

	                        MyGun.reload = false;
	                    }

	                    coincounter += 1;
	                    if (coincounter == 3)//5
	                    {
	                        if (coinframe < 3) coinframe += 1;
	                        else if (coinframe == 3) coinframe = 0;
	                        coincounter = 0;
	                    }
	                    
	                    if (Dpaused)
	                    {
	                        achievements.Updatestage();
	                        achievements.valueProcess();

	                        aletter.update(achievements.istages[0], iWidth(250), iHeight(180)); //Get ready to draw strings
	                        bletter.update(achievements.istages[1], iWidth(250), iHeight(260));
	                        cletter.update(achievements.istages[2], iWidth(250), iHeight(340));
	                        Integer value = new Integer(achievements.Mission) ;
	                        mletter.update("Misson." + value.toString() + ".", iWidth(320), iHeight(132));


                         //here we stop sounds when game is paused
                         stopSoundInstance();

	                     // i remove //  running.Stop();  //stop playing footsteep sound when game is paused
                         if(movement == run) {
                          if (!running) {
                           playSoundEffectInstance(Srun, false, sound, runningID);
                           running = true;
                          }
                         }
                         else
                         {
                          if(!thruter) {
                           playSoundEffectInstance(jethruter, false, sound, thruterID);
                           thruter = true;
                          }
                         }
	                    }
	                    //When Dpaused is false is when we update game properly 
	                    else if (!Dpaused && !tutor  && !igetname )//&& !tutor  && !igetname
	                    {
	                    	//i remove// if (adEnabled == true )
	                    	//i remove// {
	                             // Update the ad control.
	                    	//i remove//   AdComponent.Current.Update(gameTime.ElapsedTime);
	                    	//i remove//}

                        // if(tutorwait < 100 && tutormode == tutorbug)tutorwait += 1;

	                         MoveGround = true;
	                         //explorerlife = 100;

	                       //test code

	                         if (!cratetouched )
	                        	 {
	                        	// Log.i("updateWoodencrate", "Method called");
	                        	 updateWoodencrate(gameTime);
	                        	 }


                          if (movement == run) {
                           // Log.i("updateExplorer", "Method called");
                           updateExplorer(gameTime);
                          } else if (movement == fly) {
                           // Log.i("flyShuttle", "Method called");
                           flyShuttle(gameTime);
                          } else if (movement == flypaddling) {
                           // Log.i("flypad", "Method called");
                           flypad(gameTime);
                          }


	                         //This code updates the metre Ran
	                         if (MoveGround == true)
	                         {
	                             PreMetre += 1;

	                             int value = 7;//7
	                             if (movement == run) value = 7;//7
	                             else if (movement == fly) value = 2;//2
	                             else if (movement == flypaddling) value = 2;//2

	                             if (PreMetre >= value)
	                             {
	                                 metersRan += 1; //adding total number
	                                 achievements.newRecord = metersRan; //keep track of new record
	                                 PreMetre = 0;
	                             }
	                         }

	                         //This is for life bar
	                         if (explorerlife < 100 && explorerlife >= 90) eFrame = 0;
	                         else if (explorerlife < 90 && explorerlife >= 80) eFrame = 1;
	                         else if (explorerlife < 80 && explorerlife >= 70) eFrame = 2;
	                         else if (explorerlife < 70 && explorerlife >= 60) eFrame = 3;
	                         else if (explorerlife < 60 && explorerlife >= 50) eFrame = 4;
	                         else if (explorerlife < 50 && explorerlife >= 40) eFrame = 5;
	                         else if (explorerlife < 40 && explorerlife >= 30) eFrame = 6;
	                         else if (explorerlife < 30 && explorerlife >= 20) eFrame = 7;
	                         else if (explorerlife < 20 && explorerlife >= 10) eFrame = 8;
	                         else if (explorerlife <= 10) eFrame = 9;

	                        // Log.i("UpdateMagentSparkle", "Method called");
	                         UpdateMagentSparkle();//this updates the magnet


	                        if (BulletRelease)
	                        {
	                            //this code is used to reset the gun to normal position after 25 milli seconds
	                            BulletCounter += 1;
	                            if (BulletCounter >= 25) BulletRelease = false;
	                        }
	                        else if (!BulletRelease)
	                        {
	                            AngleRadians = 0;
	                            BulletCounter = 0;
	                        }


	                        AddBullet(gameTime);//This code updates and removes bullets  based on conditions



	                        //Updates background and checks if a new back ground was added
	                        //Log.i("addBackground", "Method called");
	                        addBackground(gameTime);

	                        //Log.i("Groundloop2", "Method called");
	                        Groundloop2(gameTime, bgSpeed);//bgspeed

	                        //updates dustes
	                        if (iDust.size() > 0)
	                        {
	                            UpdateDust(gameTime, bgSpeed);
	                        }

	                        if (movement == 0)
	                        {
	                            if (Fgexplorer )
	                            {
	                                for (int i = 0; i < Dground.length; i++)
	                                {
	                                	if(Dground[i] != null)
	            	                	{
	                                    if (Dground[i].X + GroundSize[i].X >= explorer.location.X + iWidth(90) &&  //49 is rythming postion on inner box 106 is width
	                                         Dground[i].X <= (explorer.location.X + iWidth(90 + 20)) &&//20
	                                        (Dground[i].Y + GroundSize[i].Y >= (explorer.location.Y + iHeight(180)) &&   //180 is the rythming position of inner box y and 30 hieght
	                                           Dground[i].Y <= (explorer.location.Y + iHeight(180 + 30))))
	                                    {
	                                        Fgexplorer = false;
	                                        jump = false;
	                                        jumpVelocity = iHeight(18);
	                                        fallvelocity = iHeight(1);

	                                       //i remove//if (sound == true) Sland.Play(); //this plays landing sound
	                                        playSound(Sland,0,sound);
	                                        //frame = 0;

	                                        explorer.location.Y = (int)(Mground.get(i).floorY - explorer.Texture2DHeight - iHeight(10));	//
	                                        break;
	                                    }
	            	                	}

	                                }

	                                if (Fgexplorer == false)
	                                {
	                                    return;
	                                }



	                                if (bg_Y <= iHeight(320))
	                                {
	                                    if (bg_Y + fallvelocity > iHeight(320)) bg_Y = iHeight(320);
	                                    else bg_Y += fallvelocity;

	                                }

	                                explorer.location.Y += fallvelocity;
	                                frame = 15;

	                                fallvelocity += Gravity;
	                            }




	                            if (explorer.location.Y > iHeight(1000))//1200
	                            {
	                            	//i remove//  if (sound == true) Sfall.Play();
	                            	 playSound(Sfall,0,sound);
	                                GameState = gameOver;
                                    stopSoundInstance();
	                                Preset();

                                 //here we set ads to be invisible
                                // EvilBugsActivity.mAdView.setVisibility(View.VISIBLE);

	                                //we did this because we cannot call a thread on another thread
	                                //so we call another handler to take care of it
	                                Handler mainhandlerHandler = new Handler(mContext.getMainLooper());
	                                Runnable myRunable =  new Runnable() {
										public void run() {
											TopRanking();
										}
									} ;
									mainhandlerHandler.post(myRunable);


	                                rating_Showing = false;
	                               // LoadContent();
	                                rategame();
	                            }

	                            if (explorerlife < - 10)// metersRan > 20
	                            {
	                              //i remove//  if (sound == true) biteDeath.Play();
	                            	 playSound(biteDeath,0,sound);
	                                shouting = 3;
	                                GameState = gameOver;
	                                Preset();
                                    stopSoundInstance();

                                 //here we set ads to be invisible
                               //  EvilBugsActivity.mAdView.setVisibility(View.VISIBLE);

	                              //we did this because we cannot call a thread on another thread
	                                //so we call another handler on the mainthread to take care of it
	                                Handler mainhandlerHandler = new Handler(mContext.getMainLooper());
	                                Runnable myRunable =  new Runnable() {
										public void run() {
											TopRanking();
										}
									} ;
									mainhandlerHandler.post(myRunable);


	                                rating_Showing = false;
	                                //LoadContent();
	                                rategame();
	                            }
	                        }



                         try
                         {
	                        AddCoins(gameTime); //adds coins

	                        if (icoinsparkle.size() > 0)
	                        {
	                            UpdateCoinsparkle(gameTime, bgSpeed);
	                        }
                         }
                         catch (Exception e) {
                          String msg = "AddCoins" +  e.getMessage().toString();
                         }

                         try
                         {
                          AddMosquitoes(gameTime);  //Adds mosquitoes
                         }
                         catch (Exception e) {
	 				    		  String msg = e.getMessage().toString();
                         }


                         try
                         {
	                        addObjectForeGround(gameTime); //Fore ground Object Update
                         }
                         catch (Exception e) {
                          String msg = "Error from addObjectforeGround" +  e.getMessage().toString();
                         }


	                    }
//                     else if(tutor && !Dpaused && !igetname)
//                        {
//                         if(helphandframe < 2) helphandframe += 1;
//                         else if(helphandframe == 2) helphandframe = 0;
//                        }

	                }

	                if (GameState == gameOver)
	                {
	                	// Log.i("Gameover ", "Game state");
	                	 //i remove//  MenuTouch(); //
	                    // achievements.Updatestage();
	                    achievements.valueProcess();

	                    //Stuff to know if to show rating or not
//	                    if (rating_Showing == false && rating_Never == false)
//	                    {
//	                        int ShowRate = 0;
//	                        ShowRate = 0 + (int)(Math.random() * ((101 - 0))); // Object_Picker.Next(0, 101);
//	                        if (ShowRate >= 50 && ShowRate < 60)
//	                        {
//	                        Rating = true;
//	                        rating_Showing = true;
//	                        }
//	                    }


	                    aletter.update(); //Get ready to draw strings
	                    bletter.update();
	                    cletter.update();
	                    //mletter.update();

	                    coincounter += 1;
	                    if (coincounter == 5)//5
	                    {
	                        if (coinframe < 3) coinframe += 1;
	                        else if (coinframe == 3) coinframe = 0;
	                        coincounter = 0;
	                    }

	                  
	                    }

	                   


	              //  }

	                if (morecoins == true )
	                {
	                	  int startX; //this and code below is used to control the line for writing letters on screen
	                       if (istatus.length() < iWidth(25)) startX = iWidth(275);
	                        else startX = iWidth(224);
	                     if (istatus.length() < 1) istatus = "connecting";
	                        infoLetters.update(istatus, startX, iHeight(212));
	                        infoLetters.MaxLine = iWidth(460);

	                }

	              
	              
	          //  }
	            //catch (Exception e)
	            //{
	                //Guide.BeginShowMessageBox("Opps Error", "Unhandle Exception Has occured on your device, game restarting", new List<string> { "Ok" },
	                //    0, MessageBoxIcon.Warning, new AsyncCallback(ErrorRestart), null);
	           // }

	            // TODO: Add your update logic here
	            // TODO: Add your update logic here
		  	}
	  		
	  		private void doDraw(Canvas spriteBatch){
				deviceWidth = spriteBatch.getWidth();
				deviceHeight = spriteBatch.getHeight();
				
				mpaint.setAlpha(fader); 
				
				  if (GameState == gameLoading)
		            {
		                Menus.DrawFrame(spriteBatch, 0, 0, 0);  //This draws the chibusoft logo
		            }
		            else if (GameState == gamewall)
		            {
		                Menus.DrawFrame(spriteBatch, 1, 0, 0);  //This draws the game wallpaper
		               // Loading.Draw(spriteBatch, 0, 0, iloadframe);//iloadframe
		                Loading.Draw(spriteBatch, iWidth(584), iHeight(444), iloadframe);  //Dont forget to draw your game loading sprite 
		            }
		            else if (GameState == gameStart)
		            {
		                Menus.DrawFrame(spriteBatch, 2, 0, 0);  //This draws the game wallpaper

		                rateusbtn.Draw(spriteBatch, rateusbtn.location);

		                if (StartMode == StartModeA)
		                {
		                    //This is used to draw explorer when running in one direction
		                    explorer.Draw(spriteBatch, explorer.location.X, explorer.location.Y, frame,0);// SpriteEffects.None
		                }
		                else if (StartMode == StartModeB)
		                {
		                    explorer.Draw(spriteBatch,explorer.location.X, explorer.location.Y, frame, 1);//SpriteEffects.FlipHorizontally
		                }

//		                for (int i = 0; i < myMosquitoes.size(); i++)
//		                {		                			                	
//		                    myMosquitoes.get(i).MosquitoesDraw(spriteBatch, 0); //This 0 is because the ground is normal
//		                }
		                
		                for(Mosquitoes E : myMosquitoes )
		                {
		                	E.MosquitoesDraw(spriteBatch, 0);     
		                }

		               //i remove//  if (adEnabled == true)
		               //i remove//{
		                    // Draw the ad control
		               //i remove//    AdComponent.Current.Draw();
		               //i remove//}

		                //aderror.Draw(spriteBatch);

		               // fb.Draw(spriteBatch, fb.location);//draws the facebook fan icon
		                //tweeter.Draw(spriteBatch, tweeter.location);
		                //startbtn.Draw(spriteBatch, startbtn.location);  //just removed the start
		                
		                //Set transparency roughly at 50%		       
			              p.setAlpha(90);
		                blackbg.Draw(spriteBatch, blackbg.location, p);

		                //Check to see which side of arrow to draw
		                if (arrow.location.X > iWidth(700))
		                	arrow.Draw(spriteBatch, arrow.location, 1);//SpriteEffects.FlipHorizontally
		                else 
		                	arrow.Draw(spriteBatch, arrow.location,0);// SpriteEffects.None


		                storebtn.Draw(spriteBatch, storebtn.location);

		                if (sound == true) soundenabled.Draw(spriteBatch, soundenabled.location);
		                else if (sound == false) sounddisabled.Draw(spriteBatch, sounddisabled.location);

		                if (mediaEnabled ) musicEnabled.Draw(spriteBatch, musicEnabled.location);
		                else if (!mediaEnabled ) musicDisabled.Draw(spriteBatch, musicDisabled.location);

		              //  infobtn.Draw(spriteBatch, infobtn.location);

		                creditbtn.Draw(spriteBatch, creditbtn.location);

		                cautionsign.Draw(spriteBatch, cautionsign.location.X, cautionsign.location.Y, cautionframe);

		                if (showInfo)
		                {
		                    //status
		                	 //Set transparency roughly at 80%
		  	                 p.setAlpha(90); // p.setAlpha(204);
		                     black.Draw(spriteBatch,black.location, p);

		                    statusboard.Draw(spriteBatch,statusboard.location);

		                    infoLetters.Draw(spriteBatch);

		                    //playCreated is used to know if method call has results
		                    //if (leaderboards.playCreated == true)
		                    //{
		                    //    btnDismmiss.Draw(spriteBatch, btnDismmiss.location);
		                    //}
		                }
		            }
		            else if (GameState == gamecredit)
		            {
		                Menus.DrawFrame(spriteBatch, 3, 0, 0);  //This draws the credits

		            }
		            else if (GameState == gameGuns)
		            {

		                // DrawGuns();
		                MyGun.Draw(spriteBatch);

		            }
		            else if (GameState == gameInplay )
		            {

		                if (!showGuns && !tutor)//&& !tutor
		                {

		                	// Log.i("bg", "Draw Method called");
		                    //Used to draw the big background
		                    bg.Draw(spriteBatch, 1f, 0, bg_X, (int)bg_Y);//draw needs work

		                   // Log.i("forebg_Objects", "Draw Method called");
		                    //Used to draw objects on the foreground
		                    for (Sprite E : forebg_Objects)  //Here we use for each element in list in java		                   
		                    {//this is to make sure we draw object visible to back ground only
		                        if (E.location.X + E.Texture2DWidth > 0 && E.location.X < deviceWidth &&
		                       E.location.Y - bg_Y + E.Texture2DHeight > 0 && E.location.Y - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            E.Draw(spriteBatch, E.location.X, E.location.Y - bg_Y);
		                        }
		                    }

		                    for (int i = 0; i < Mground.size(); i++)
		                    {//this is to make sure we draw object visible to back ground only
		                        if (Mground.get(i).Position.X + Mground.get(i).Texture2DWidth > 0 && Mground.get(i).Position.X < deviceWidth &&
		                      Mground.get(i).Position.Y - bg_Y + Mground.get(i).Texture2DHeight > 0 && Mground.get(i).Position.Y - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            Mground.get(i).Draw(spriteBatch, (int)bg_Y);
		                        }
		                    }

//		                    //i remove //if (adEnabled == true)
//		                    //i remove //  {
//		                        // Draw the ad control
//		                    	  //i remove //    AdComponent.Current.Draw(); 
//		                    //i remove //}
//
//		                       
//
//
//		                    //Drawing Boost Stuffs
		                    if (BoostClick)
		                    {
		                    	//Log.i("Boost", "Draw Method called");
		                        if (!CoinMagnet)
		                        {
		                            //drawing coin and price for magnet	          	              
		                            iMagnet.Draw(spriteBatch, iMagnet.location,mpaint);
		                            Guncoins.Draw(spriteBatch, iWidth(145), iHeight(170), 0);
		                            DrawScore(spriteBatch, CoinNum, PCoinMagnet,  iWidth(18), iWidth(188),  iHeight(180));
		                            DrawScore(spriteBatch, boostNum, ACoinMagnet, iWidth(18), iWidth(186),  iHeight(72));  //this draw number of coin magnet available
		                        }

		                        if (!Coindoubler)
		                        {
		                            //drawing coin and price for coin doubler
		                            icoindouble.Draw(spriteBatch, icoindouble.location,mpaint);
		                            Guncoins.Draw(spriteBatch, iWidth(352), iHeight(170), 0);
		                            DrawScore(spriteBatch,CoinNum, PCoindoubler,  iWidth(18), iWidth(396),  iHeight(180));
		                            DrawScore(spriteBatch,boostNum, ACoindoubler, iWidth(18), iWidth(378), iHeight(72));  //this draw number of coin doubler available
		                        }


		                        //drawing coin and price for thunder
		                        ithunder.Draw(spriteBatch, ithunder.location, mpaint);
		                        Guncoins.Draw(spriteBatch, iWidth(538), iHeight(170), 0);
		                        DrawScore(spriteBatch,CoinNum, Pthunder, iWidth(18), iWidth(580), iHeight(180));
		                        DrawScore(spriteBatch,boostNum, Athunder, iWidth(18), iWidth(568), iHeight(72));  //this draw number of thunder available

		                        //when magnet is click magnet sound played
		                        //when tunder is clicked thunder sound
		                        //when coin doubler clicked a click sound played

		                      
		                            if (BoostAvail == 50)
		                                fader = 200;
		                            else if (BoostAvail == 60)
		                                fader = 160;
		                            else if (BoostAvail == 70)
		                                fader = 130;
		                            else if (BoostAvail == 80)
		                                fader = 100;
		                            else if (BoostAvail == 90)
		                                fader = 60;

		                            if (BoostAvail >= 110)
		                            {
		                                BoostClick = false;
		                                BoostAvail = 0;
		                                //fader = 220;
		                            }
		                            else if (Dpaused == false)
		                            {
		                                BoostAvail += 1;
		                            }
		                       
		                    }

		                    if (Athunder > 0)
		                    {
		                        thunderbtn.Draw(spriteBatch, thunderbtn.location, color);
		                        DrawScore(spriteBatch,boostNum, Athunder, iWidth(18), iWidth(16), iHeight(68));  //this draw number of thunder available
		                    }
		                    if (thunder )
		                    {
		                        thundercounter += 1;
		                        if (thundercounter <= 5)//5
		                        {
		                            SthunderA.Draw(spriteBatch, SthunderA.location, color);
		                            //play sound
		                        }
		                        else if (thundercounter > 5)//5
		                        {
		                            SthunderB.Draw(spriteBatch, SthunderB.location, color);
		                            //play sound
		                        }

		                        if (thundercounter > 10)//10
		                        {
		                            thundercounter = 0;
		                            thunder = false;
		                        }
		                    }

		                    if (movement == run)
		                    {
		                        DrawExplorer(spriteBatch);

		                    }
		                    else if (movement == fly)
		                    {
		                        DrawShuttle(spriteBatch);

		                        //these draw the energy bar for your energy bar 
		                        DrawEnergyBar(spriteBatch, ShuttleAngleRadian, 1);
		                    }
		                    else if (movement == flypaddling)
		                    {
		                        Drawpad(spriteBatch);

		                        //these draw the energy bar for your energy bar 
		                        DrawEnergyBar(spriteBatch, padAngleRadians, 2);

		                        //these draw the energy bar for your energy bar 
		                        //DrawEnergyBar(spriteBatch, padAngleRadians, 2);
		                    }

		                    //Draw crate when nessecary
		                    DrawCrate(spriteBatch);

		                    if (showmagnet )
		                    {
		                        if (movement == run)
		                        {
		                            //here we draw the magnet attraction frame and -68 is drawn to centre it 
		                            magnetSparkle.Draw(spriteBatch, (int)explorer.location.X - bg_X - iWidth(68), (int)(explorer.location.Y - iHeight(68) - bg_Y), magnetframe);
		                        }
		                        else if (movement == fly)
		                        {
		                            //here we draw the magnet attraction frame and -68 is drawn to centre it 
		                            magnetSparkle.Draw(spriteBatch, (int)(flyingShuttle.location.X - bg_X + iWidth(68)),
		                                (int)(flyingShuttle.location.Y - iHeight(68) - bg_Y), magnetframe);
		                        }
		                        else if (movement == flypaddling)
		                        {
		                            //here we draw the magnet attraction frame and -68 is drawn to centre it 
		                            magnetSparkle.Draw(spriteBatch, (int)(flyingpad.location.X - bg_X + iWidth(68)),
		                                (int)(flyingpad.location.Y - iHeight(68) - bg_Y), magnetframe);
		                        }

		                    }


		                    //bulllets
		                    for (int i = 0; i < MyBullets.size(); i++)
		                    {
		                    	if(MyBullets.get(i) != null)
		                    	{
		                        if (MyBullets.get(i).Bulletx + MyBullets.get(i).Texture2DWidth > 0 && MyBullets.get(i).Bulletx < deviceWidth &&
		                      MyBullets.get(i).Bullety - bg_Y + MyBullets.get(i).Texture2DHeight > 0 && MyBullets.get(i).Bullety - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            MyBullets.get(i).Draw(spriteBatch, (int)bg_Y);
		                        }
		                        
		                    	}
		                    }


		                    // drawing code here for dust
		                    for (int i = 0; i < iDust.size(); i++)
		                    {
		                        iDust.get(i).DrawDust(spriteBatch, (int)bg_Y);
		                    }

		                    //Code for drawing mosquitoes
		                    for (int i = 0; i < myMosquitoes.size(); i++)
		                    {
		                    	if(myMosquitoes.get(i) != null)
		                    	{
		                        if (myMosquitoes.get(i).MX + myMosquitoes.get(i).Texture2DWidth > 0 && myMosquitoes.get(i).MX < deviceWidth &&
		                     myMosquitoes.get(i).MY - bg_Y + myMosquitoes.get(i).Texture2DHeight > 0 && myMosquitoes.get(i).MY - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            myMosquitoes.get(i).MosquitoesDraw(spriteBatch, (int)bg_Y);
		                        }

		                        //This draws life bar based on mosquitoes postion
		                        // if (myMosquitoes[i].isLife == true) PBar.Draw(spriteBatch, (int)myMosquitoes[i].MX + 42, (int)myMosquitoes[i].MY + 40, myMosquitoes[i].Pframe);
		                        if (myMosquitoes.get(i).isLife == true) 
		                        	PBar.Draw(spriteBatch, (int)myMosquitoes.get(i).MX + iWidth(54), ((int)myMosquitoes.get(i).MY + iHeight(37)) - (int)bg_Y, myMosquitoes.get(i).Pframe, myMosquitoes.get(i).myflip);

		                        if (myMosquitoes.get(i).m_Hit == true && myMosquitoes.get(i).isLife == true)//
		                        {
		                            //This draws mosquitoes life hit frame when hit is true
		                            if (myMosquitoes.get(i).Type == 1)
		                            {
		                                blood.Draw(spriteBatch, (int)myMosquitoes.get(i).MX, (int)(myMosquitoes.get(i).MY - bg_Y), myMosquitoes.get(i).bframe);
		                            }
		                            else
		                            {
		                                Wblood.Draw(spriteBatch, (int)myMosquitoes.get(i).MX, (int)(myMosquitoes.get(i).MY - bg_Y), myMosquitoes.get(i).bframe);
		                            }

		                        }

		                        //used to know when to draw explode sprite
		                        if (myMosquitoes.get(i).explode  && myMosquitoes.get(i).explodeframe <= 9)
		                        {
		                            explosion.Draw(spriteBatch, (int)myMosquitoes.get(i).explodeX, (int)(myMosquitoes.get(i).explodeY - bg_Y), myMosquitoes.get(i).explodeframe);
		                           
		                        }

		                        if (myMosquitoes.get(i).Attack )
		                        {
		                            Aexclamation.Draw(spriteBatch, (int)myMosquitoes.get(i).MX + iWidth(67), (int)(myMosquitoes.get(i).MY - bg_Y - iHeight(10)), myMosquitoes.get(i).exframe);
		                        }
		                        else if (myMosquitoes.get(i).caution )
		                        {
		                            Cexclamation.Draw(spriteBatch, (int)myMosquitoes.get(i).MX + iWidth(67), (int)(myMosquitoes.get(i).MY - bg_Y - iHeight(10)), myMosquitoes.get(i).exframe);
		                        }
		                    	}
		                    }


		                    
//		                    //draw coins
		                    for (int i = 0; i < GCoins.size(); i++)
		                    {
		                        if (GCoins.get(i).Mx + GCoins.get(i).Texture2DWidth > 0 && GCoins.get(i).Mx < deviceWidth &&
		                    GCoins.get(i).My - bg_Y + GCoins.get(i).Texture2DHeight > 0 && GCoins.get(i).My - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            GCoins.get(i).CoinDraw(spriteBatch, (int)bg_Y);
		                        }
		                    }

		                    //Draw sparkle after coin is touched
		                    for (int i = 0; i < icoinsparkle.size(); i++)
		                    {
		                        if (icoinsparkle.get(i).SparkX + icoinsparkle.get(i).Texture2DWidth > 0 && icoinsparkle.get(i).SparkX < deviceWidth &&
		                  icoinsparkle.get(i).SparkY - bg_Y + icoinsparkle.get(i).Texture2DHeight > 0 && icoinsparkle.get(i).SparkY - bg_Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            icoinsparkle.get(i).CoinSparkleDraw(spriteBatch, (int)bg_Y);
		                        }
		                    }




		                    if (Attacked )
		                        Attacked = false;





		                    //this draw transition background
		                    if (Intransit)
		                    {
		                        if (transit.location.X + transit.Texture2DWidth > 0 && transit.location.X < deviceWidth &&
		                        transit.location.Y + transit.Texture2DHeight > 0 && transit.location.Y < deviceHeight) //code is to make sure we draw only whats visible
		                        {
		                            transit.Draw(spriteBatch, transit.location);//.X, (int)transit.location.Y, 0
		                        }
//		                        if (transit.location.X + deviceWidth + transit.Texture2DWidth > 0 && transit.location.X + deviceWidth < deviceWidth &&
//		                        transit.location.Y + transit.Texture2DHeight > 0 && transit.location.Y < deviceHeight) //code is to make sure we draw only whats visible
//		                        {
//		                            transit.Draw(spriteBatch, (int)transit.location.X + deviceWidth, (int)transit.location.Y, 1);
//		                        }

		                    }


		                    Guncoins.Draw(spriteBatch, iWidth(608), iHeight(38), coinframe);

		                    iMeters.Draw(spriteBatch, iWidth(614), iHeight(8), 10); //this is used to draw M sign 
		                    DrawScore(spriteBatch,iMeters, metersRan, iWidth(25), iWidth(644), iHeight(8));//note 25 is the spacing meters ran
		                    //DrawScore(ikills, killnum, 25);//note 25 is the spacing meters ran
		                    DrawScore(spriteBatch,CoinNum, MyGun.TotalCoins, iWidth(20), iWidth(648), iHeight(48));//note 25 is the spacing bugs killed

		                    //This draws gun rounds when bullet is not empty
		                    if (MyGun.bulletEmpty == false)
		                    {
		                        //Drawing rounds
		                        MyGun.Rounds.Draw(spriteBatch, MyGun.Rounds.location.X, MyGun.Rounds.location.Y);

		                        MyGun.RoundsNum.setLocation(248, 2);
		                        DrawScore(spriteBatch,MyGun.RoundsNum, MyGun.GunRounds, iWidth(20));

		                        MyGun.RoundsNum.setLocation(321, 2);
		                        DrawScore(spriteBatch,MyGun.RoundsNum, MyGun.LoadedBullets, iWidth(25));

		                      // Reload.Draw(spriteBatch, Reload.location, Color.White * 0.4f);
		                    }
		                    else if (MyGun.bulletEmpty )//This draws gun rounds when bullet is empty
		                    {
		                        MyGun.emptyCounter += 1;

		                        if (MyGun.emptyCounter >= 15 && MyGun.emptyCounter <= 30)
		                        	MyGun.Empty.Draw(spriteBatch, MyGun.Empty.location.X, MyGun.Empty.location.Y);
		                        else if (MyGun.emptyCounter > 30) MyGun.emptyCounter = 0;

		                     //   Reload.Draw(spriteBatch, Reload.location, p);
		                    }

		                    if (explorerlife < 20)
		                    {
		                        energycounter += 1;
		                        //explorer energy bar
		                        if(energycounter > 6)//6
		                        explorerEnergy.Draw(spriteBatch, explorerEnergy.location.X, explorerEnergy.location.Y, eFrame);

		                        //14
		                        if (energycounter >= 28) energycounter = 0;
		                    }
		                    else
		                    {
		                        explorerEnergy.Draw(spriteBatch, explorerEnergy.location.X, explorerEnergy.location.Y, eFrame);
		                    }


		                    //jump button
		                    p.setAlpha(160); //these controls how transperent image will be
		                    Jumpbtn.Draw(spriteBatch, Jumpbtn.location, p);//Color.White * 0.7f

		                   


		                    if (Dpaused )
		                    {
		                        pausedScreen.Draw(spriteBatch, true);//this draws paused background
		                        QuitPos = new Vector2(62, 404);
		                        RestartPos = new Vector2(328, 404);
		                        ResumePos = new Vector2(592, 404);

		                        Guncoins.Draw(spriteBatch, iWidth(600), iHeight(14), coinframe); //this draws coin
		                        DrawScore(spriteBatch,CoinNum, MyGun.TotalCoins, iWidth(18));

		                        storebtn.Draw(spriteBatch, storebtn.location);


		                        if (sound) soundenabled.Draw(spriteBatch, soundenabled.location);
		                        else if (!sound) sounddisabled.Draw(spriteBatch, soundenabled.location);

		                        if (mediaEnabled) musicEnabled.Draw(spriteBatch, musicEnabled.location);
		                        else if (!mediaEnabled) musicDisabled.Draw(spriteBatch, musicDisabled.location);


		                        aletter.Draw(spriteBatch);
		                        bletter.Draw(spriteBatch);
		                        cletter.Draw(spriteBatch);
		                        mletter.Draw(spriteBatch);

		                        //this is used to keep coin values (X & Y)
		                        float CoinX = CoinNum.location.X; float CoinY = CoinNum.location.Y;

		                        //this is used to draw mission state if progress has been made on the mission
		                        //varLevels is a flag that is used to tell which has been done or not 
		                        //note out of 3 thats why its an array
		                        if (achievements.varLevels[0])
		                        {
		                            mState.Draw(spriteBatch, iWidth(584), iHeight(178), 1);

		                            //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT
		                            CoinNum.location.X = iWidth(186); CoinNum.location.Y = iHeight(198);
		                            DrawScore(spriteBatch,CoinNum, achievements.values[0], iWidth(16));
		                        }
		                        else //else it just tells you the mission to be completed on the board
		                        {
		                            mState.Draw(spriteBatch,iWidth(584), iHeight(178), 0);
		                        }

		                        if (achievements.varLevels[1])
		                        {
		                            mState.Draw(spriteBatch, iWidth(584), iHeight(256), 1);

		                            //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT
		                            CoinNum.location.X = iWidth(186); CoinNum.location.Y = iHeight(274);
		                            DrawScore(spriteBatch,CoinNum, achievements.values[1], iWidth(16));
		                        }
		                        else
		                        {
		                            mState.Draw(spriteBatch, iWidth(584), iHeight(256), 0);
		                        }

		                        if (achievements.varLevels[2])
		                        {
		                            mState.Draw(spriteBatch, iWidth(584), iHeight(336), 1);

		                            //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT
		                            CoinNum.location.X = iWidth(186); CoinNum.location.Y = iHeight(350);
		                            DrawScore(spriteBatch,CoinNum, achievements.values[2], iWidth(16));
		                        }
		                        else
		                        {
		                            mState.Draw(spriteBatch, iWidth(584), iHeight(336), 0);
		                        }

		                        //this is used to set back coin values (X & Y)
		                        CoinNum.location.X = (int)CoinX; CoinNum.location.Y = (int)CoinY;
		                    }
		                    else if (!Dpaused)
		                    {
		                        //this is used to draw on the board while playing a completed task and 
		                        //intcompleted is used to know which was completed and missioncompleted flags up when 
		                        //a mission is completed
		                        if (achievements.MissionComplete )
		                        {
		                            Mboard.Draw(spriteBatch, iWidth(278), iHeight(402));
		                            Comletter.update(achievements.istages[achievements.intcompleted], iWidth(380), iHeight(414));
		                            Comletter.Draw(spriteBatch);
		                            CompletedCounter += 1;
		                        }
		                        if (CompletedCounter >= 40)//40
		                        {
		                            achievements.MissionComplete = false;
		                            CompletedCounter = 0;
		                            achievements.intcompleted = 0;
		                        }

		                    }
		                }

		                else if(showGuns )
		                {
		                    //This would draw guns

		                    MyGun.Draw(spriteBatch);
		                }
		               else  if (tutor)
		                {

                       //  helphand.Draw(spriteBatch, helphand.location.X, helphand.location.Y, 1);
		                    if (tutorPage == tutorPage1)
		                    {
		                        tutorScreen1.Draw(spriteBatch, true);


		                        if (tutorCounter <= 20)//20
		                        {
		                            tutorRight.Draw(spriteBatch, tutorRight.location);
		                        }


		                    }
		                    else if (tutorPage == tutorPage2)
		                    {
		                        tutorScreen2.Draw(spriteBatch, true);

		                        if (tutorCounter <= 40)//20
		                        {
		                            tutorRight.Draw(spriteBatch, tutorRight.location);
		                            tutorLeft.Draw(spriteBatch, tutorLeft.location);
		                        }
		                    }

		                    tutorCounter += 1;
		                    if (tutorCounter <= 40)//20
		                    {
		                        btnDismmiss.Draw(spriteBatch, btnDismmiss.location);
		                    }
		                    else if (tutorCounter >= 60)//30
		                    {
		                        tutorCounter = 0;
		                    }


		                }
		              
		            }
		            else if (GameState == gameOver)
		            {
		                if (showGuns == true)
		                {
		                    MyGun.Draw(spriteBatch);
		                }
		                else
		                {
		                    gmOver.Draw(spriteBatch, true);

		                    aletter.Draw(spriteBatch);
		                    bletter.Draw(spriteBatch);
		                    cletter.Draw(spriteBatch);

		                    //this is used to draw mission state
		                    if (achievements.varLevels[0] == true)
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(164), 1);

		                        //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT
		                        DrawScore(spriteBatch,CoinNum, achievements.values[0], iWidth(20), iWidth(58), iHeight(174));
		                    }
		                    else
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(164), 0);
		                    }

		                    if (achievements.varLevels[1] == true)
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(242), 1);

		                        //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT
		                        DrawScore(spriteBatch,CoinNum, achievements.values[1], iWidth(16), iWidth(58), iHeight(252));
		                    }
		                    else
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(242), 0);
		                    }

		                    if (achievements.varLevels[2] == true)
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(314), 1);

		                        //THIS IS USED TO SET PERCENTAGE COMPLETE AND DRAW IT

		                        DrawScore(spriteBatch,CoinNum, achievements.values[2], iWidth(16), iWidth(58), iHeight(328));
		                    }
		                    else
		                    {
		                        mState.Draw(spriteBatch, iWidth(448), iHeight(314), 0);
		                    }

		                    //for drawing available coin
		                    Guncoins.Draw(spriteBatch, iWidth(600), iHeight(14), coinframe);
		                    DrawScore(spriteBatch,CoinNum, MyGun.TotalCoins, iWidth(20));


		     
		                    DrawScore(spriteBatch,iMeters, metersRan, iWidth(25), iWidth(314), iHeight(28));//note 25 is the spacing meters ran

		                    //this is used to draw the M sign after meters ran
		                    String SMeter = metersRan.toString();
		                    int lenght = SMeter.length();
		                    int position = iWidth(314) + iWidth((lenght * 25));

		                    iMeters.Draw(spriteBatch, position, iHeight(28), 10); //this is used to draw M sign 


		                    DrawScore(spriteBatch,CoinNum, coinnum, iWidth(20), iWidth(394), iHeight(88));//note 20 is the spacing bugs killed

		                    //this is used to draw the coin sign after coins colllected ran
		                    SMeter = coinnum.toString();
		                    lenght = SMeter.length();
		                    position = iWidth(394) + iWidth((lenght * 20));

		                    Guncoins.Draw(spriteBatch, position, iHeight(76), coinframe);

		                    //Drawing global leaderboard buttons
		                    btnGlobal.Draw(spriteBatch,btnGlobal.location , p);

		                    drawleader(spriteBatch);

		               

		                    if (Rating == true)
		                    {
		                    	p.setAlpha(120); //these controls how transperent image will be
		                        black.Draw(spriteBatch, black.location, p);
		                        gameOverRateUs.Draw(spriteBatch, gameOverRateUs.location);
		                    }
		                    
		                }
		            }


		            if (morecoins == true)
		            {
		            
		            
		                Morepage.Draw(spriteBatch, 0f, 0f);

		                DrawScore(spriteBatch,iMeters, 15000, iWidth(25), iWidth(200), iHeight(66));
		                DrawScore(spriteBatch,iMeters, 57000, iWidth(25),  iWidth(200),  iHeight(136));
		                DrawScore(spriteBatch,iMeters, 120000, iWidth(25),  iWidth(200),  iHeight(206));
		                DrawScore(spriteBatch,iMeters, 270000, iWidth(25),  iWidth(200),  iHeight(276));
		                DrawScore(spriteBatch,iMeters, 600000, iWidth(25),  iWidth(200),  iHeight(346));
		                
		            
		                 //   Advise.Draw(spriteBatch, Advise.location);
		               //adviseCounter = 0;


		                 if (showboard == true)
		                 {
		                    //status
		                     black.Draw(spriteBatch, black.location, p);

		                     statusboard.Draw(spriteBatch, statusboard.location);

		                     infoLetters.Draw(spriteBatch);
		                 }	               

		            }

		         }
	  		}
	 
	  	}
	  	
	  	

