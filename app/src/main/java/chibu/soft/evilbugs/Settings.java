package chibu.soft.evilbugs;

import android.content.SharedPreferences;

/**
 * Created by MrWinston on 7/27/2015.
 */
public class Settings {
    public static SharedPreferences mysettings;   //This is basically for loading data key and value
    public static SharedPreferences.Editor editor;  //And it works with this editor

    public boolean idata = false;  //this is used to know if we have anydata previously saved

    public String userName;

    public  int higestMetre;// from game one

    public boolean Paused;

    public String scoreID;

    public boolean sound;

    public boolean mediaEnabled;

    //GameInPlay parameters
    public String Mosquitoes = " ";

    public String ground = " ";

    public String coins = " ";

    public float explorerX = - iWidth(200);

    public float explorerY = iHeight(540);

    public int movement;

    public int EnergyCount, Energyframe;

    public float shuttleX, shuttleY, padX, padY;

    public float bg_y;

    public int thunderAvail, coindoublerAvail, magnetAvail; //from game one


    public int TotalCoins; //from Guns

    public boolean[] PurchasedID;

    public boolean[] EquipedID;//to know last gun equiped

    public int PresentID = 0;//to know gun present ID

    public int[] AmmoLevel;//to know level of ammo on gun

    public boolean[] UnLimitedAmmo; //to know guns with unlimited bullets

    //Achievement Stuffs
    public int Mission;//from Achievements

    public int LeveCount = 0;

    //string activeStage = string.Empty;

    //F stuff Processes
    public int CoinsColl, TotalCoinsColl;   //this is used to keep track of coins collect during game play
    public boolean TCoin;


    public String[] istages;  //this will be the listing of all stages in writing
    public String[] stages;          //listing in stages
    public String[] istagesMirror;  //these a mirror of the stages used to know what stage to reload

    public boolean[] varLevels;   //know which stage has been done and which one to process

    //E stuff processes
    public int[] MosGuns, WaspGuns, BeeGuns;  //This is used to know the weapon that killed the bug
    public int[] TMosGuns, TWaspGuns, TBeeGuns;  //This is used to keep total count of bug killed with weapon
    public boolean TFMosGuns, TFWaspGuns, TFBeeGuns;  //This is used to set a flag until we get total count of bug killed with weapon

    //B stuff processes
    public int[] BugForest, NBugForest, BugVillage;  //This the weather or background the bug was killed
    public int[] TBugForest, TNBugForest, TBugVillage;
    public boolean TFBugForest, TFNBugForest, TFBugVillage;
    //B.10.1.1.true.false

    //A stuff Processes
    public int MosKilled, WaspKilled, BeeKilled;//, AKilled; //This is used to keep track of all bugs killed
    public boolean TMkilled, TWkilled, TBkilled;//, TAkilled; //this is used to activate flag to know we are looking for total count
    public int TMosKilled, TWaspKilled, TBeeKilled;//, TAbugKilled; //This is used to keep track of total mosquitoes killed

    //C stuff Process
    public int LastRecord;//this is used to save record

    public boolean A, B, C, D, E, F; //to make we dont have 2 of same mission

    //Advert
    public boolean adEnabled = true;


    //leaderboard
    public String  playerName = "";
   // public String playerEmail = "";


    //Rate Us Never settings
    public boolean rating_Never = false;

    //saving transit parameters
    public int village;
    public float transitX, transitY;
    public int startTransit, nearIntransit;
    public boolean Intransit, bgChanged;

    public void Reset()
    {
        CreatePresentAmmo(); //creating default instance

        //Boost start up
        thunderAvail = 1;
        coindoublerAvail = 1;
        magnetAvail = 1;

        //GameInplay Parameters
        Mosquitoes = " ";
        ground = " ";
        coins = " ";
        explorerX = -200;
        explorerY = 540;


        //achievement stuffs
        MosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun killed which bug
        WaspGuns = new int[18];
        BeeGuns = new int[18];

        TMosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun total of which bug
        TWaspGuns = new int[18];
        TBeeGuns = new int[18];

        //Background creating
        BugForest = new int[3]; TBugForest = new int[3];
        NBugForest = new int[3]; TNBugForest = new int[3];
        BugVillage = new int[3]; TBugVillage = new int[3];


        //This is used to add achievements as a string
        stages = new String[3];//15
        istages = new String[3];//15

        istagesMirror = new String[3];
        istagesMirror[0] = "empty";
        istagesMirror[1] = "empty";
        istagesMirror[2] = "empty";

        varLevels = new boolean[3];

    }


    public Settings()
    {

        TotalCoins = 2000; //When game is set i will use 4000

        // rating_Never = false;

        sound = true;

        Paused = false;

        mediaEnabled = true;

        PurchasedID = new boolean[18];

        EquipedID = new boolean[18];

        CreatePurchasedID(); //create default instance

        CreateEquipedID(); //create default instance

        CreatedUnlimitedAmmo(); //create default instance

        CreatePresentAmmo(); //creating default instance

        //Boost start up
        thunderAvail = 1;
        coindoublerAvail = 1;
        magnetAvail = 1;

        //GameInplay Parameters
        Mosquitoes = " ";
        ground = " ";
        coins = " ";
        explorerX = - iWidth(200);
        explorerY = iHeight(540);


        //achievement stuffs
        MosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun killed which bug
        WaspGuns = new int[18];
        BeeGuns = new int[18];

        TMosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun total of which bug
        TWaspGuns = new int[18];
        TBeeGuns = new int[18];

        //Background creating
        BugForest = new int[3]; TBugForest = new int[3];
        NBugForest = new int[3]; TNBugForest = new int[3];
        BugVillage = new int[3]; TBugVillage = new int[3];


        //This is used to add achievements as a string
        stages = new String[3];//15
        istages = new String[3];//15

        istagesMirror = new String[3];
        istagesMirror[0] = "empty";
        istagesMirror[1] = "empty";
        istagesMirror[2] = "empty";

        varLevels = new boolean[3];


    }


    private void CreatePurchasedID()
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

    private void CreateEquipedID()
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

    public void Save()
    {
         editor = mysettings.edit();


        editor.putString("userName", userName);

        editor.putInt("higestMetre", higestMetre);  // from game one

        editor.putBoolean("Paused", Paused); // public boolean Paused;

        editor.putString("scoreID", scoreID);  //public String scoreID;

        editor.putBoolean("sound", sound);   //public boolean sound;

        editor.putBoolean("mediaEnabled", mediaEnabled);   //public boolean mediaEnabled;

        editor.putString("Mosquitoes", Mosquitoes);  // public String Mosquitoes = " ";

        editor.putString("ground", ground);  //public String ground = " ";

        editor.putString("coins", coins);  //public String coins = " ";

        editor.putFloat("explorerX", explorerX);  //public float explorerX = - iWidth(200);

        editor.putFloat("explorerY", explorerY);  //public float explorerY = iHeight(540);

        editor.putInt("movement", movement);  //public int movement;

        editor.putInt("EnergyCount", EnergyCount);  //public int EnergyCount, Energyframe;
        editor.putInt("Energyframe", Energyframe);

        editor.putFloat("shuttleX", shuttleX);  //public float shuttleX, shuttleY, padX, padY;
        editor.putFloat("shuttleY", shuttleY);
        editor.putFloat("padX", padX);
        editor.putFloat("padY", padY);


        editor.putFloat("bg_y", bg_y);  //public float bg_y;

        editor.putInt("thunderAvail", thunderAvail);  //public int thunderAvail, coindoublerAvail, magnetAvail; //from game one
        editor.putInt("coindoublerAvail", coindoublerAvail);
        editor.putInt("magnetAvail", magnetAvail);


        editor.putInt("TotalCoins", TotalCoins);  //public int TotalCoins; //from Guns

        //public boolean[] PurchasedID;  //HERE WE PUT ARRAY IN EDITOR
        editor.putInt("PurchasedID" +"_size", PurchasedID.length);
        for(int i=0;i<PurchasedID.length;i++)
        editor.putBoolean("PurchasedID" + "_" + i, PurchasedID[i]);


        //public boolean[] EquipedID;//to know last gun equiped
        editor.putInt("EquipedID" +"_size", EquipedID.length);
        for(int i=0;i<EquipedID.length;i++)
            editor.putBoolean("EquipedID" + "_" + i, EquipedID[i]);


        editor.putInt("PresentID", PresentID);  //public int PresentID = 0;//to know gun present ID

        //public int[] AmmoLevel;//to know level of ammo on gun
        editor.putInt("AmmoLevel" +"_size", AmmoLevel.length);
        for(int i=0;i<AmmoLevel.length;i++)
            editor.putInt("AmmoLevel" + "_" + i, AmmoLevel[i]);

        //public boolean[] UnLimitedAmmo; //to know guns with unlimited bullets
        editor.putInt("UnLimitedAmmo" +"_size", UnLimitedAmmo.length);
        for(int i=0;i<UnLimitedAmmo.length;i++)
            editor.putBoolean("UnLimitedAmmo" + "_" + i, UnLimitedAmmo[i]);

        //Achievement Stuffs
        editor.putInt("Mission", Mission);  //public int Mission;//from Achievements

        editor.putInt("LeveCount", LeveCount);  //public int LeveCount = 0;

        //string activeStage = string.Empty;

        //F stuff Processes
        editor.putInt("CoinsColl", CoinsColl);  //public int CoinsColl, TotalCoinsColl;   //this is used to keep track of coins collect during game play
        editor.putInt("TotalCoinsColl", TotalCoinsColl);


        editor.putBoolean("TCoin", TCoin);   //public boolean TCoin;


       // public String[] istages;  //this will be the listing of all stages in writing
        editor.putInt("istages" + "_size", istages.length);
        for(int i=0;i<istages.length;i++)
            editor.putString("istages" + "_" + i, istages[i]);

        //public String[] stages;          //listing in stages
        editor.putInt("stages" +"_size", stages.length);
        for(int i=0;i<stages.length;i++)
            editor.putString("stages" + "_" + i, stages[i]);

       // public String[] istagesMirror;  //these a mirror of the stages used to know what stage to reload
        editor.putInt("istagesMirror" + "_size", istagesMirror.length);
        for(int i=0;i<istagesMirror.length;i++)
            editor.putString("istagesMirror" + "_" + i, istagesMirror[i]);

        //public boolean[] varLevels;   //know which stage has been done and which one to process
        editor.putInt("varLevels" +"_size", varLevels.length);
        for(int i=0;i<varLevels.length;i++)
            editor.putBoolean("varLevels" + "_" + i, varLevels[i]);

        //E stuff processes
        //public int[] MosGuns, WaspGuns, BeeGuns;  //This is used to know the weapon that killed the bug
        editor.putInt("MosGuns" +"_size", MosGuns.length);
        for(int i=0;i<MosGuns.length;i++)
            editor.putInt("MosGuns" + "_" + i, MosGuns[i]);

        editor.putInt("WaspGuns" +"_size", WaspGuns.length);
        for(int i=0;i<WaspGuns.length;i++)
            editor.putInt("WaspGuns" + "_" + i, WaspGuns[i]);

        editor.putInt("BeeGuns" +"_size", BeeGuns.length);
        for(int i=0;i<BeeGuns.length;i++)
            editor.putInt("BeeGuns" + "_" + i, BeeGuns[i]);


        //public int[] TMosGuns, TWaspGuns, TBeeGuns;  //This is used to keep total count of bug killed with weapon
        editor.putInt("TMosGuns" +"_size", TMosGuns.length);
        for(int i=0;i<TMosGuns.length;i++)
            editor.putInt("TMosGuns" + "_" + i, TMosGuns[i]);

        editor.putInt("TWaspGuns" +"_size", TWaspGuns.length);
        for(int i=0;i<TWaspGuns.length;i++)
            editor.putInt("TWaspGuns" + "_" + i, TWaspGuns[i]);

        editor.putInt("TBeeGuns" +"_size", TBeeGuns.length);
        for(int i=0;i<TBeeGuns.length;i++)
            editor.putInt("TBeeGuns" + "_" + i, TBeeGuns[i]);


        editor.putBoolean("TFMosGuns", TFMosGuns);   //public boolean TFMosGuns, TFWaspGuns, TFBeeGuns;  //This is used to set a flag until we get total count of bug killed with weapon
        editor.putBoolean("TFWaspGuns", TFWaspGuns);
        editor.putBoolean("TFBeeGuns", TFBeeGuns);

        //B stuff processes
        //public int[] BugForest, NBugForest, BugVillage;  //This the weather or background the bug was killed
        editor.putInt("BugForest" +"_size", BugForest.length);
        for(int i=0;i<BugForest.length;i++)
            editor.putInt("BugForest" + "_" + i, BugForest[i]);

        editor.putInt("NBugForest" +"_size", NBugForest.length);
        for(int i=0;i<NBugForest.length;i++)
            editor.putInt("NBugForest" + "_" + i, NBugForest[i]);

        editor.putInt("BugVillage" +"_size", BugVillage.length);
        for(int i=0;i<BugVillage.length;i++)
            editor.putInt("BugVillage" + "_" + i, BugVillage[i]);


        //public int[] TBugForest, TNBugForest, TBugVillage;
        editor.putInt("TBugForest" +"_size", TBugForest.length);
        for(int i=0;i<TBugForest.length;i++)
            editor.putInt("TBugForest" + "_" + i, TBugForest[i]);

        editor.putInt("TNBugForest" +"_size", TNBugForest.length);
        for(int i=0;i<TNBugForest.length;i++)
            editor.putInt("TNBugForest" + "_" + i, TNBugForest[i]);

        editor.putInt("TBugVillage" +"_size", TBugVillage.length);
        for(int i=0;i<TBugVillage.length;i++)
            editor.putInt("TBugVillage" + "_" + i, TBugVillage[i]);


        editor.putBoolean("TFBugForest", TFBugForest);   //public boolean TFBugForest, TFNBugForest, TFBugVillage;
        editor.putBoolean("TFNBugForest", TFNBugForest);
        editor.putBoolean("TFBugVillage", TFBugVillage);
        //B.10.1.1.true.false

        //A stuff Processes
        editor.putInt("MosKilled", MosKilled);  //public int MosKilled, WaspKilled, BeeKilled;//, AKilled; //This is used to keep track of all bugs killed
        editor.putInt("WaspKilled", WaspKilled);
        editor.putInt("BeeKilled", BeeKilled);


        editor.putBoolean("TMkilled", TMkilled);   //public boolean TMkilled, TWkilled, TBkilled;//, TAkilled; //this is used to activate flag to know we are looking for total count
        editor.putBoolean("TWkilled", TWkilled);
        editor.putBoolean("TBkilled", TBkilled);


        editor.putInt("TMosKilled", TMosKilled);  //public int TMosKilled, TWaspKilled, TBeeKilled;//, TAbugKilled; //This is used to keep track of total mosquitoes killed
        editor.putInt("TWaspKilled", TWaspKilled);  //
        editor.putInt("TBeeKilled", TBeeKilled);  //


        //C stuff Process
        editor.putInt("LastRecord", LastRecord);  //public int LastRecord;//this is used to save record

        editor.putBoolean("A", A);   //public boolean A, B, C, D, E, F; //to make we dont have 2 of same mission
        editor.putBoolean("B", B);
        editor.putBoolean("C", C);
        editor.putBoolean("D", D);
        editor.putBoolean("E", E);
        editor.putBoolean("F", F);

        //Advert
        //public boolean adEnabled = true;
        //editor.putBoolean("adEnabled", adEnabled);  //

        //leaderboard
        editor.putString("playerName", playerName);  //public String  playerName = "";
        //public String playerEmail = "";


        //Rate Us Never settings
        editor.putBoolean("rating_Never", rating_Never);  //public boolean rating_Never = false;

        //saving transit parameters
        editor.putInt("village", village);  // public int village;
        editor.putFloat("transitX", transitX);  //public float transitX, transitY;
        editor.putFloat("transitY", transitY);

        editor.putInt("startTransit", startTransit);  //public int startTransit, nearIntransit;
        editor.putInt("nearIntransit", nearIntransit);

        editor.putBoolean("Intransit", Intransit);  // boolean Intransit, bgChanged;
        editor.putBoolean("bgChanged", bgChanged);

        editor.putBoolean("idata", true); // have we save data as true

        editor.commit();
    }

    public void Load()
    {
        //editor = mysettings.edit();

        userName = mysettings.getString("userName", "");

        higestMetre = mysettings.getInt("higestMetre", 0);  // from game one

        Paused = mysettings.getBoolean("Paused", false); // public boolean Paused;

        scoreID = mysettings.getString("scoreID", "");  //public String scoreID;

        sound = mysettings.getBoolean("sound", true);   //public boolean sound;

        mediaEnabled = mysettings.getBoolean("mediaEnabled", true);   //public boolean mediaEnabled;

        Mosquitoes =  mysettings.getString("Mosquitoes", "");  // public String Mosquitoes = " ";

        ground =  mysettings.getString("ground", "");  //public String ground = " ";

        coins =  mysettings.getString("coins", "");  //public String coins = " ";

        explorerX =  mysettings.getFloat("explorerX",  - iWidth(200));  //public float explorerX = - iWidth(200);

        explorerY =  mysettings.getFloat("explorerY", iHeight(540));  //public float explorerY = iHeight(540);

        movement =  mysettings.getInt("movement", 0);  //public int movement;

        EnergyCount =  mysettings.getInt("EnergyCount", 0);  //public int EnergyCount, Energyframe;
        Energyframe =  mysettings.getInt("Energyframe", 0);

        shuttleX =  mysettings.getFloat("shuttleX", 0);  //public float shuttleX, shuttleY, padX, padY;
        shuttleY =  mysettings.getFloat("shuttleY", 0);
        padX =  mysettings.getFloat("padX", 0);
        padY =  mysettings.getFloat("padY", 0);


        bg_y = mysettings.getFloat("bg_y", 0);  //public float bg_y;

        thunderAvail = mysettings.getInt("thunderAvail", 1);  //public int thunderAvail, coindoublerAvail, magnetAvail; //from game one
        coindoublerAvail = mysettings.getInt("coindoublerAvail", 1);
        magnetAvail = mysettings.getInt("magnetAvail", 1);


        TotalCoins = mysettings.getInt("TotalCoins", 2000);  //public int TotalCoins; //from Guns

        //public boolean[] PurchasedID;  //HERE WE LOAD ARRAY FROM EDITOR
        int size = mysettings.getInt("PurchasedID" + "_size", 0);
         PurchasedID = new boolean[size];
        for(int i=0;i<size;i++)
            PurchasedID[i] = mysettings.getBoolean("PurchasedID" + "_" + i, false);


        //public boolean[] EquipedID;//to know last gun equiped
        size = mysettings.getInt("EquipedID" + "_size", 0);
        EquipedID = new boolean[size];
        for(int i=0;i<size;i++)
            EquipedID[i] = mysettings.getBoolean("EquipedID" + "_" + i, false);


        PresentID = mysettings.getInt("PresentID", 0);  //public int PresentID = 0;//to know gun present ID

        //public int[] AmmoLevel;//to know level of ammo on gun
        size = mysettings.getInt("AmmoLevel" + "_size", 0);
         AmmoLevel = new int[size];
        for(int i=0;i<size;i++)
            AmmoLevel[i] = mysettings.getInt("AmmoLevel" + "_" + i, 0);

        //public boolean[] UnLimitedAmmo; //to know guns with unlimited bullets
        size = mysettings.getInt("UnLimitedAmmo" + "_size", 0);
        UnLimitedAmmo = new boolean[size];
        for(int i=0;i<size;i++)
            UnLimitedAmmo[i] = mysettings.getBoolean("UnLimitedAmmo" + "_" + i, false);

        //Achievement Stuffs
        Mission = mysettings.getInt("Mission", 0);  //public int Mission;//from Achievements

        LeveCount = mysettings.getInt("LeveCount", 0);  //public int LeveCount = 0;

        //string activeStage = string.Empty;

        //F stuff Processes
        CoinsColl = mysettings.getInt("CoinsColl", 0);  //public int CoinsColl, TotalCoinsColl;   //this is used to keep track of coins collect during game play
        TotalCoinsColl = mysettings.getInt("TotalCoinsColl", 0);


        TCoin = mysettings.getBoolean("TCoin", false);   //public boolean TCoin;


        // public String[] istages;  //this will be the listing of all stages in writing
        size = mysettings.getInt("istages" + "_size", 0);
        istages = new String[size];
        for(int i=0;i<size;i++)
            istages[i] = mysettings.getString("istages" + "_" + i, "");

        //public String[] stages;          //listing in stages
        size = mysettings.getInt("stages" + "_size", 0);
        stages = new String[size];
        for(int i=0;i<size;i++)
            stages[i] = mysettings.getString("stages" + "_" + i, "");

        // public String[] istagesMirror;  //these a mirror of the stages used to know what stage to reload
        size = mysettings.getInt("istagesMirror" + "_size", 0);
        istagesMirror = new String[size];
        for(int i=0;i<size;i++)
            istagesMirror[i] = mysettings.getString("istagesMirror" + "_" + i, "");

        //public boolean[] varLevels;   //know which stage has been done and which one to process
        size = mysettings.getInt("varLevels" + "_size", 0);
        varLevels = new boolean[size];
        for(int i=0;i<size;i++)
            varLevels[i] = mysettings.getBoolean("varLevels" + "_" + i, false);

        //E stuff processes
        //public int[] MosGuns, WaspGuns, BeeGuns;  //This is used to know the weapon that killed the bug
        size = mysettings.getInt("MosGuns" + "_size", 0);
        MosGuns = new int[size];
        for(int i=0;i<size;i++)
            MosGuns[i] = mysettings.getInt("MosGuns" + "_" + i, 0);

        size = mysettings.getInt("WaspGuns" + "_size", 0);
        WaspGuns = new int[size];
        for(int i=0;i<size;i++)
            WaspGuns[i] = mysettings.getInt("WaspGuns" + "_" + i, 0);

        size = mysettings.getInt("BeeGuns" + "_size", 0);
        BeeGuns = new int[size];
        for(int i=0;i<size;i++)
            BeeGuns[i] = mysettings.getInt("BeeGuns" + "_" + i, 0);


        //public int[] TMosGuns, TWaspGuns, TBeeGuns;  //This is used to keep total count of bug killed with weapon
        size = mysettings.getInt("TMosGuns" + "_size", 0);
        TMosGuns = new int[size];
        for(int i=0;i<size;i++)
            TMosGuns[i] = mysettings.getInt("TMosGuns" + "_" + i, 0);

        size = mysettings.getInt("TWaspGuns" + "_size", 0);
        TWaspGuns = new int[size];
        for(int i=0;i<size;i++)
            TWaspGuns[i] = mysettings.getInt("TWaspGuns" + "_" + i, 0);

        size = mysettings.getInt("TBeeGuns" + "_size", 0);
        TBeeGuns = new int[size];
        for(int i=0;i<size;i++)
            TBeeGuns[i] = mysettings.getInt("TBeeGuns" + "_" + i, 0);


        TFMosGuns = mysettings.getBoolean("TFMosGuns",false );   //public boolean TFMosGuns, TFWaspGuns, TFBeeGuns;  //This is used to set a flag until we get total count of bug killed with weapon
        TFWaspGuns = mysettings.getBoolean("TFWaspGuns", false);
        TFBeeGuns = mysettings.getBoolean("TFBeeGuns", false );

        //B stuff processes
        //public int[] BugForest, NBugForest, BugVillage;  //This the weather or background the bug was killed
        size = mysettings.getInt("BugForest" + "_size", 0);
        BugForest = new int[size];
        for(int i=0;i<size;i++)
            BugForest[i] = mysettings.getInt("BugForest" + "_" + i, 0);

        size = mysettings.getInt("NBugForest" + "_size", 0);
        NBugForest = new int[size];
        for(int i=0;i<size;i++)
            NBugForest[i] = mysettings.getInt("NBugForest" + "_" + i, 0);

        size = mysettings.getInt("BugVillage" + "_size", 0);
        BugVillage = new int[size];
        for(int i=0;i<size;i++)
            BugVillage[i] = mysettings.getInt("BugVillage" + "_" + i, 0);


        //public int[] TBugForest, TNBugForest, TBugVillage;
        size = mysettings.getInt("TBugForest" + "_size", 0);
        TBugForest = new int[size];
        for(int i=0;i<size;i++)
            TBugForest[i] = mysettings.getInt("TBugForest" + "_" + i, 0);

        size = mysettings.getInt("TNBugForest" + "_size", 0);
        TNBugForest = new int[size];
        for(int i=0;i<size;i++)
            TNBugForest[i] = mysettings.getInt("TNBugForest" + "_" + i, 0);

        size = mysettings.getInt("TBugVillage" + "_size", 0);
        TBugVillage = new int[size];
        for(int i=0;i<size;i++)
            TBugVillage[i] = mysettings.getInt("TBugVillage" + "_" + i, 0);


        TFBugForest = mysettings.getBoolean("TFBugForest", false);   //public boolean TFBugForest, TFNBugForest, TFBugVillage;
        TFNBugForest = mysettings.getBoolean("TFNBugForest", false);
        TFBugVillage = mysettings.getBoolean("TFBugVillage", false);
        //B.10.1.1.true.false

        //A stuff Processes
        MosKilled = mysettings.getInt("MosKilled", 0);  //public int MosKilled, WaspKilled, BeeKilled;//, AKilled; //This is used to keep track of all bugs killed
        WaspKilled = mysettings.getInt("WaspKilled", 0);
        BeeKilled = mysettings.getInt("BeeKilled", 0);


        TMkilled = mysettings.getBoolean("TMkilled", false);   //public boolean TMkilled, TWkilled, TBkilled;//, TAkilled; //this is used to activate flag to know we are looking for total count
        TWkilled = mysettings.getBoolean("TWkilled",false);
        TBkilled = mysettings.getBoolean("TBkilled", false);


        TMosKilled = mysettings.getInt("TMosKilled", 0);  //public int TMosKilled, TWaspKilled, TBeeKilled;//, TAbugKilled; //This is used to keep track of total mosquitoes killed
        TWaspKilled = mysettings.getInt("TWaspKilled", 0);  //
        TBeeKilled = mysettings.getInt("TBeeKilled", 0);  //


        //C stuff Process
        LastRecord = mysettings.getInt("LastRecord", 0);  //public int LastRecord;//this is used to save record

        A = mysettings.getBoolean("A", false);   //public boolean A, B, C, D, E, F; //to make we dont have 2 of same mission
        B = mysettings.getBoolean("B", false);
        C = mysettings.getBoolean("C", false);
        D = mysettings.getBoolean("D", false);
        E = mysettings.getBoolean("E", false);
        F = mysettings.getBoolean("F", false);

        //Advert
        //public boolean adEnabled = true;
        //editor.putBoolean("adEnabled", adEnabled);  //

        //leaderboard
        playerName = mysettings.getString("playerName", "");  //public String  playerName = "";
        //public String playerEmail = "";


        //Rate Us Never settings
        rating_Never = mysettings.getBoolean("rating_Never", false);  //public boolean rating_Never = false;

        //saving transit parameters
        village = mysettings.getInt("village", 0);  // public int village;
        transitX = mysettings.getFloat("transitX", 0);  //public float transitX, transitY;
        transitY = mysettings.getFloat("transitY", 0);

        startTransit = mysettings.getInt("startTransit", 0);  //public int startTransit, nearIntransit;
        nearIntransit = mysettings.getInt("nearIntransit", 0);

        Intransit = mysettings.getBoolean("Intransit", false);  // boolean Intransit, bgChanged;
        bgChanged = mysettings.getBoolean("bgChanged", false);

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
