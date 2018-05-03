package chibu.soft.evilbugs;

//import android.R.integer;
//import android.R.String;



public class Achievements 
{
	 
	
     //public string missionlevel;
     public int Mission;
    // public int missionCount;

     public boolean MissionComplete = false;
     public int intcompleted = 0;

     public final int Mosquitoes = 1, Wasp = 2, Bettle = 3;
     public final int bugforest = 0, nbugforest = 1, bugvillage = 2;

     //public int exType = 0;

    // public string[] Levels = new string[3];
    // public string[] mLevels = new string[3]; //this is used to write saved words
    // public bool[] iLevel; //this is used to know completed levels out of 3
     public int[] values = new int[3];
     public int LeveCount = 0;

     //string activeStage = string.Empty;

     //F stuff Processes
     public int CoinsColl, TotalCoinsColl;   //this is used to keep track of coins collect during game play
     public boolean TCoin;

     
     public String[] istages;  //this will be the listing of all stages in writing
     public String[] stages;          //listing in stages
     public String[] istagesMirror;  //these a mirror of the stages used to know what stage to reload

     public boolean[] varLevels;   //know which stage has been done and which one to process
      String[] mstage;// new

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
     public int LastRecord = 500;
     public int newRecord;  //this is used to check if you have a new record


     //Random irandom;
     public boolean MissonActive, A, B, C, D, E, F;
     int selMission;
     
     //public String[] Selstage;
     
     public Achievements()
     {
         MosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun killed which bug
         WaspGuns = new int[18];
         BeeGuns = new int[18];

         TMosGuns = new int[18];// This is because we have 18 guns kill keep track of which gun total of which bug
         TWaspGuns = new int[18];
         TBeeGuns = new int[18];

         //Background creating
         BugForest = new int[3];     TBugForest = new int[3];
         NBugForest = new int[3];    TNBugForest = new int[3];
         BugVillage = new int[3];    TBugVillage = new int[3];

       
         //This is used to add achievements as a string 
         stages = new String[3];//15
         istages = new String[3];//15
         mstage = new String[2];

         istagesMirror = new String[3];
         istagesMirror[0] = "empty";
         istagesMirror[1] = "empty";
         istagesMirror[2] = "empty";


         varLevels = new boolean[3];

        // irandom = new Random();
         //note in this method type A is the type of achievement
         //followed by number required and type of bug , bool single run or total run
         //bool completed or not 
         //last stage number 

//         Collect 20 coins in one run				F.20.true.false
//Collect 30 coins in one run				F.30.true.false



         //istages[0] = "Collect.10.coins.in.one.run.";
         //stages[0] = "F.10.true.false.0";  //10    //nameoflevel, required number, requiredtype, one run or not, done or not done ,stage number
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
     }

     //note
     //when gamestate == game over select stage again to pick new levels
     //or call method during game start

     public void Pickstage()
     {
         int selected = 1 + (int)(Math.random() * ((75 - 1))); //irandom.Next(1, 75);

         selMission = selected;
         //string[] mstage;

         if (selected < 20)
         {
             mstage = CreateA();
           //  A = true; //this is used to know what has been selected so we dont do a mission twice in one stage
             
         }
         else if (selected >= 20 && selected < 30)
         {
             mstage = CreateB();
           //  B = true; //this is used to know what has been selected so we dont do a mission twice in one stage
         }
         else if (selected >= 30 && selected < 35)
         {
             mstage = CreateC();
           //  C = true;//this is used to know what has been selected so we dont do a mission twice in one stage
         }
         else if (selected >= 35 && selected < 50)
         {
             mstage = CreateD();
           //  D = true;//this is used to know what has been selected so we dont do a mission twice in one stage
         }
         else if (selected >= 50 && selected < 60)
         {
             mstage = CreateE();
           //  E = true;//this is used to know what has been selected so we dont do a mission twice in one stage
         }
         else //if (selected == 6)
         {
             mstage = CreateF();
          //   F = true;//this is used to know what has been selected so we dont do a mission twice in one stage
         }

     }

     public void SelectStage()
     {
         


         //Note i dont think its right to pass same mission category twice it will cause an error
         //imagine having A..saying kill total mosquitoes and another saying kill mosquitoes in 1 run dats a bug



         while(LeveCount <= 2)
         {
             if (istagesMirror[LeveCount].equalsIgnoreCase("empty"))//used to know mission done for next mission loading
             {
                 Pickstage();//use random to pick a stage and put into any empty slot

                 if (A == true && selMission < 20) MissonActive = true;
                 else if (B == true && selMission >= 20 && selMission < 30) MissonActive = true;
                 else if (C == true && selMission >= 30 && selMission < 35) MissonActive = true;
                 else if (D == true && selMission >= 35 && selMission < 50) MissonActive = true;
                 else if (E == true && selMission >= 50 && selMission < 60) MissonActive = true;
                 else if (F == true && selMission >= 60) MissonActive = true;


                 if (varLevels[LeveCount] == false && MissonActive == false)
                 {
                     stages[LeveCount] = mstage[0];
                     istages[LeveCount] = mstage[1];
                     varLevels[LeveCount] = true;

                 

                     //this is used to know what has been selected so we dont do a mission twice in one stage
                     if (selMission < 20) A = true;
                     else if (selMission >= 20 && selMission < 30) B = true;
                     else if (selMission >= 30 && selMission < 35) C = true;
                     else if (selMission >= 35 && selMission < 50) D = true;
                     else if (selMission >= 50 && selMission < 60) E = true;
                     else if (selMission >= 60) F = true;

                     istagesMirror[LeveCount] = "";

                     LeveCount += 1;
                 }

                 MissonActive = false;


                         
             }
             else
             {
                 LeveCount += 1;
                 continue;      
             }

            

            
         }

     }

     public void Updatestage()
     {
        // missionlevel = Mission.ToString();

    	 
         for (int i = 0; i < varLevels.length; i++)
         {
             if (varLevels[i] == true)//check value to know stage with uncompleted mission
             {
            	 String[] Selstage = stages[i].split(",");
            	        	 
                 
                 if (Selstage[0].equalsIgnoreCase("A"))
                 {
                     if (AProcessor(Selstage[0], Integer.parseInt(Selstage[1]), Integer.parseInt(Selstage[2]), Boolean.parseBoolean(Selstage[3]), Boolean.parseBoolean(Selstage[4])) == true && Boolean.parseBoolean(Selstage[4]) == false)
                     {
                         Selstage[4] = "true"; //this indicates its being done
                         
                         MissionComplete = true;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         A = false;

                         stages[i] = Selstage[0] + "." + Selstage[1] + "." + Selstage[2] + "." + Selstage[3] + "." + Selstage[4];
                         varLevels[i] = false;

                         istagesMirror[i] = "empty";//used to know mission done for next mission loading

                         Mission += 1;
                     }
                 }
                 else if (Selstage[0].equalsIgnoreCase("B"))
                 {
                     if (BProcessor(Selstage[0], Integer.parseInt(Selstage[1]), Integer.parseInt(Selstage[2]), Integer.parseInt(Selstage[3]), Boolean.parseBoolean(Selstage[4]), Boolean.parseBoolean(Selstage[5])) == true && Boolean.parseBoolean(Selstage[5]) == false)
                     {
                         Selstage[5] = "true"; //this indicates its being done
                       
                         MissionComplete = true;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         B = false;

                         stages[i] = Selstage[0] + "." + Selstage[1] + "." + Selstage[2] + "." + Selstage[3] + "." + Selstage[4] + "." + Selstage[5];
                         varLevels[i] = false;

                         istagesMirror[i] = "empty";//used to know mission done for next mission loading
                        
                         Mission += 1;
                         //B.30.1.1.false.false 
                     }
                 }
                 else if (Selstage[0].equalsIgnoreCase("C"))
                 {
                     if (CProcessor() == true && Boolean.parseBoolean(Selstage[1]) == false)
                     {
                         Selstage[1] = "true";
                         
                         MissionComplete = true;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         C = false;

                         stages[i] = Selstage[0] + "." + Selstage[1];
                         varLevels[i] = false;

                         istagesMirror[i] = "empty";
                        
                         Mission += 1;
                        
                     }
                 }
                 else if (Selstage[0].equalsIgnoreCase("D"))
                 {
                     if (DProcessor(Selstage[0], Integer.parseInt(Selstage[1]), Integer.parseInt(Selstage[2])) == true && Boolean.parseBoolean(Selstage[3]) == false)
                     {
                         Selstage[3] = "true";
                         
                         MissionComplete = true;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         D = false;

                         stages[i] = Selstage[0] + "." + Selstage[1] + "." + Selstage[2] + "." + Selstage[3];
                         varLevels[i] = false;

                         istagesMirror[i] = "empty"; //used to know mission done for next mission loading
                        
                         Mission += 1;
                     }
                 }
                 else if (Selstage[0].equalsIgnoreCase("E"))
                 {
                     if (EProcessor(Selstage[0], Integer.parseInt(Selstage[1]),Integer.parseInt(Selstage[2]), Integer.parseInt(Selstage[3]), Boolean.parseBoolean(Selstage[4]), Boolean.parseBoolean(Selstage[5])) == true && Boolean.parseBoolean(Selstage[5]) == false)
                     {
                         Selstage[4] = "true";
                         
                         MissionComplete = true;
                         varLevels[i] = false;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         E = false;

                         stages[i] = Selstage[0] + "." + Selstage[1] + "." + Selstage[2] + "." + Selstage[3] + "." + Selstage[4] + "." + Selstage[5];
                         istagesMirror[i] = "empty"; //used to know mission done for next mission loading

                         Mission += 1;
                     }
                     //Kill 15 Mosquitoes with A-Type Gun  "E.15.3.1.false.";
                 }
                 else if (Selstage[0].equalsIgnoreCase("F"))
                 {
                     if (FProcessor(Selstage[0],Integer.parseInt(Selstage[1]), Boolean.parseBoolean(Selstage[2]), Boolean.parseBoolean(Selstage[3])) == true && Boolean.parseBoolean(Selstage[3]) == false)
                     {
                         Selstage[3] = "true";
                        
                         MissionComplete = true;
                         intcompleted = i; //used to know what to write when we complete a stage on game play screen
                         F = false;

                         stages[i] = Selstage[0] + "." + Selstage[1] + "." + Selstage[2] + "." + Selstage[3];
                         varLevels[i] = false;
                         istagesMirror[i] = "empty";//used to know mission done for next mission loading
                        
                         Mission += 1;
                     }

                     //F.220.true.false
                 }
             }
         }
     }
   
     private boolean AProcessor(String EXType, int requiredNo, int requiredType, boolean singleRun,boolean Done)
     {
         if (requiredType == Mosquitoes && Done == false)
         {
             if (singleRun == false)
             {
                 //in this code if single false it activates Total mosquitoes killed(TMKilled) and it will keep
                 //adding mosquitoes killed until its up to required number
                 if (TMosKilled >= requiredNo)
                 {
                     TMosKilled = 0;
                     TMkilled = false;
                     return true;
                 }
                 else
                 {
                     TMkilled = true;
                 }
               
             }
             else if (singleRun == true)
             {
                 if (MosKilled >= requiredNo)
                 {
                    MosKilled = 0;
                     return true;
                 }
             }
         }
         else if (requiredType == Wasp && Done == false)
         {
             if (singleRun == false)
             {
                 if (TWaspKilled >= requiredNo)
                 {
                     TWaspKilled = 0;
                     TWkilled = false;
                     return true;
                 }
                 else
                 {
                     TWkilled = true;
                 }

             }
             else if (singleRun == true)
             {
                 if (WaspKilled >= requiredNo)
                 {
                     WaspKilled = 0;
                     return true;
                 }
             }
         }
         else if (requiredType == Bettle && Done == false)
         {
             if (singleRun == false)
             {
                 if (TBeeKilled >= requiredNo)
                 {
                     TBeeKilled = 0;
                     TBkilled = false;
                     return true;
                 }
                 else
                 {
                     TBkilled = true;
                 }

             }
             else if (singleRun == true)
             {
                 if (BeeKilled >= requiredNo)
                 {
                     BeeKilled = 0;
                     return true;
                 }
             }
         }
         //else if (requiredType == AnyBug && Done == false)
         //{
         //    if (singleRun == false)
         //    {
         //        if (TAbugKilled >= requiredNo)
         //        {
         //            TAbugKilled = 0;
         //            TAkilled = false;
         //            return true;
         //        }
         //        else
         //        {
         //            TAkilled = true;
         //        }

         //    }
         //    else if (singleRun == true)
         //    {
         //        if (AKilled >= requiredNo)
         //        {
         //            AKilled = 0;
         //            return true;
         //        }
         //    }

         //}

         return false;
         //kill 10 Mosquitoes in one run
     }

     private boolean BProcessor(String EXType, int requiredNo, int requiredType, int village, boolean singleRun, boolean Done)
     {
        // B.30.1.1.false.false
         if (requiredType == Mosquitoes)
         {
             if (singleRun == false)
             {
                 if (village == 0)
                 {
                     if (TBugForest[0] >= requiredNo)
                     {
                         TBugForest[0] = 0;
                         TFBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFBugForest = true;
                     }
                 }
                 else if (village == 1)
                 {
                     if (TNBugForest[0] >= requiredNo)
                     {
                         TNBugForest[0] = 0;
                         TFNBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFNBugForest = true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (TBugVillage[0] >= requiredNo)
                     {
                         TBugVillage[0] = 0;
                         TFBugVillage = false;
                         return true;
                     }
                     else
                     {
                         TFBugVillage = true;
                     }
                 }
             }
             else if (singleRun == true)
             {
                 if (village == 0)
                 {
                     if (BugForest[0] >= requiredNo)
                      {
                          BugForest[0] = 0;
                          return true;
                      }
                 }
                 else if (village == 1)
                 {
                     if (NBugForest[0] >= requiredNo)
                     {
                         NBugForest[0] = 0;
                         return true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (BugVillage[0] >= requiredNo)
                     {
                         BugVillage[0] = 0;
                         return true;
                     }
                 }
             }

         }
         else if (requiredType == Wasp)
         {
             if (singleRun == false)
             {
                 if (village == 0)
                 {
                     if (TBugForest[1] >= requiredNo)
                     {
                         TBugForest[1] = 0;
                         TFBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFBugForest = true;
                     }
                 }
                 else if (village == 1)
                 {
                     if (TNBugForest[1] >= requiredNo )
                     {
                         TNBugForest[1] = 0;
                         TFNBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFNBugForest = true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (TBugVillage[1] >= requiredNo )
                     {
                         TBugVillage[1] = 0;
                         TFBugVillage = false;
                         return true;
                     }
                     else
                     {
                         TFBugVillage = true;
                     }
                 }
             }
             else if (singleRun == true)
             {
                 if (village == 0)
                 {
                     if (BugForest[1] >= requiredNo)
                     {
                         BugForest[1] = 0;
                         return true;
                     }
                 }
                 else if (village == 1)
                 {
                     if (NBugForest[1] >= requiredNo)
                     {
                         NBugForest[1] = 0;
                         return true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (BugVillage[1] >= requiredNo)
                     {
                         BugVillage[1] = 0;
                         return true;
                     }
                 }
             }

         }
         if (requiredType == Bettle)
         {
             if (singleRun == false)
             {
                 if (village == 0)
                 {
                     if (TBugForest[2] >= requiredNo )
                     {
                         TBugForest[2] = 0;
                         TFBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFBugForest = true;
                     }
                 }
                 else if (village == 1)
                 {
                     if (TNBugForest[2] >= requiredNo)
                     {
                         TNBugForest[2] = 0;
                         TFNBugForest = false;
                         return true;
                     }
                     else
                     {
                         TFNBugForest = true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (TBugVillage[2] >= requiredNo )
                     {
                         TBugVillage[2] = 0;
                         TFBugVillage = false;
                         return true;
                     }
                     else
                     {
                         TFBugVillage = true;
                     }
                 }
             }
             else if (singleRun == true)
             {
                 if (village == 0)
                 {
                     if (BugForest[2] >= requiredNo)
                     {
                         BugForest[2] = 0;
                         return true;
                     }
                 }
                 else if (village == 1)
                 {
                     if (NBugForest[2] >= requiredNo)
                     {
                         NBugForest[2] = 0;
                         return true;
                     }
                 }
                 else if (village == 2)
                 {
                     if (BugVillage[2] >= requiredNo)
                     {
                         BugVillage[2] = 0;
                         return true;
                     }
                 }
             }

         }
         //B.10.1.1.true.false 

         return false;
     }

     private boolean CProcessor()
     {
        
             if (newRecord > LastRecord)
             {
                 LastRecord = newRecord;
                 return true;
             }
             else
             {
                 return false;
             }
        

     }

     private boolean DProcessor(String EXType, int RequiredMeters, int RequiredType)
     {
         if (RequiredType == Mosquitoes)
         {
             if (newRecord > RequiredMeters && MosKilled == 0)
             {
                 return true;
             }
         }
         else if (RequiredType == Wasp)
         {
             if (newRecord > RequiredMeters && WaspKilled == 0)
             {
                 return true;
             }
         }
         else if (RequiredType == Bettle)
         {
             if (newRecord > RequiredMeters && BeeKilled == 0)
             {
                 return true;
             }
         }
         return false;
     }

     private boolean EProcessor(String EXType, int requiredNo, int requiredType, int requiredGun,boolean singleRun, boolean Done)
     {
         //in this code if single false it activates Total mosquitoes killed(TFMosGuns) and it will keep
         //adding mosquitoes killed until its up to required number ((TMosGuns[requiredGun])
         if (requiredType == Mosquitoes && Done == false)
         {
             if (singleRun == false)
             {
                 if (TMosGuns[requiredGun] >= requiredNo)
                 {
                     TMosGuns[requiredGun] = 0;
                     TFMosGuns = false;
                     return true;
                 }
                 else
                 {
                     TFMosGuns = true;
                 }

             }
             else if (singleRun == true)
             {
                 if (MosGuns[requiredGun] >= requiredNo)
                 {
                     return true;
                 }
             }
         }
         else if (requiredType == Wasp && Done == false)
         {
             if (singleRun == false)
             {
                 if (TWaspGuns[requiredGun] >= requiredNo)
                 {
                     TWaspGuns[requiredGun] = 0;
                     TFWaspGuns = false;
                     return true;
                 }
                 else
                 {
                     TFWaspGuns = true;
                 }

             }
             else if (singleRun == true)
             {
                 if (WaspGuns[requiredGun] >= requiredNo)
                 {
                     return true;
                 }
             }

         }
         else if (requiredType == Bettle && Done == false)
         {
             if (singleRun == false)
             {
                 if (TBeeGuns[requiredGun] >= requiredNo)
                 {
                     TBeeGuns[requiredGun] = 0;
                     TFBeeGuns = false;
                     return true;
                 }
                 else
                 {
                     TFBeeGuns = true;
                 }

             }
             else if (singleRun == true)
             {
                 if (BeeGuns[requiredGun] >= requiredNo)
                 {
                     return true;
                 }
             }

         }
         //"E.15.3.1.false.false";
             
            ////////////
         //else if (requiredType == 3)
         //{
         //    if (BeeGuns[requiredGun] >= requiredNo)
         //    {
         //        return true;
         //    }
         //}
         

         return false;
     }

     private boolean FProcessor(String EXType, int requiredNo, boolean singleRun, boolean Done)
     {
          //if (singleRun == false)
          //   {
          //       if (TMosKilled == requiredNo)
          //       {
          //           TMosKilled = 0;
          //           TMkilled = false;
          //           return true;
          //       }
          //       else
          //       {
          //           TMkilled = true;
          //       }
           //else if (singleRun == true)
           //  {
           //      if (MosKilled >= requiredNo)
           //      {
           //         MosKilled = 0;
           //          return true;
           //      }
           //  }

         if (singleRun == false )
         {
             if (TotalCoinsColl >= requiredNo)
             {
                 TotalCoinsColl = 0;
                 TCoin = false;
                 return true;
             }
             else
             {
                 TCoin = true;
             }

         }
         else if( singleRun == true)
         {
             if (CoinsColl >= requiredNo)
             {
                 CoinsColl = 0;
                 return true;
             }
         }

         //F.220.true.false
         return false;
     }

     public void GameOver()
     {
          MosKilled = 0;
          WaspKilled = 0; 
          BeeKilled = 0;

         
         // istages = new string[3];
         // stages = new string[3];
          values = new int[3];
          LeveCount = 0;

          if (newRecord > LastRecord)
          {
              LastRecord = newRecord;
          }
          newRecord = 0;

          MosGuns = new int[18];
          WaspGuns = new int[18];
          BeeGuns = new int[18];

          BugForest = new int[3]; 
          NBugForest = new int[3]; 
          BugVillage = new int[3]; 

          CoinsColl = 0;
     }

     public void valueProcess()
     {
         for (int i = 0; i < varLevels.length; i++)
         {
             String[] Selstage = stages[i].split(",");

             if (Selstage[0].equalsIgnoreCase("A"))
             {
                 //this means its mosquitoes and value is in one run
                 if (Selstage[2].equalsIgnoreCase("1") && Selstage[3].equalsIgnoreCase("True"))
                 {
                     values[i] = MosKilled;

                 }
                 //this means it mosquitoes and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("1") && Selstage[3].equalsIgnoreCase("False"))
                 {

                     values[i] = TMosKilled;
                 }

                      //this means it wasp and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("2") && Selstage[3].equalsIgnoreCase("True"))
                 {

                     values[i] = WaspKilled;
                 }
                 //this means it wasp and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("2") && Selstage[3].equalsIgnoreCase("False"))
                 {

                     values[i] = TWaspKilled;
                 }

                        //this means it bettle and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("3") && Selstage[3].equalsIgnoreCase("True"))
                 {

                     values[i] = BeeKilled;
                 }
                 //this means it betlle and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("3") && Selstage[3].equalsIgnoreCase("False"))
                 {

                     values[i] = TBeeKilled;
                 }
                 
             }
             else if (Selstage[0].equalsIgnoreCase("B"))
             {
                 //B.10.1.1.true.false
                 if (Selstage[3].equalsIgnoreCase("0"))//if bugforest
                 {
                     if (Selstage[2].equalsIgnoreCase("1"))//if its a mosquitoe
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugForest[0];
                         }
                         else
                         {
                             values[i] = TBugForest[0];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("2"))//if its a wasp
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugForest[1];
                         }
                         else
                         {
                             values[i] = TBugForest[1];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("3"))//if its a bettle
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugForest[2];
                         }
                         else
                         {
                             values[i] = TBugForest[2];
                         }
                     }
                 }
                 else if (Selstage[3].equalsIgnoreCase("1"))//bugforest night
                 {
                     if (Selstage[2].equalsIgnoreCase("1"))//if its a mosquitoe
                     {
                         if (Selstage[4].equalsIgnoreCase("true"))//In one run
                         {
                             values[i] = NBugForest[0];
                         }
                         else
                         {
                             values[i] = TNBugForest[0];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("2"))//if its a wasp
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = NBugForest[1];
                         }
                         else
                         {
                             values[i] = TNBugForest[1];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("3"))//if its a bettle
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = NBugForest[2];
                         }
                         else
                         {
                             values[i] = TNBugForest[2];
                         }
                     }
                 }
                 else if (Selstage[3].equalsIgnoreCase("2"))//bugvillage
                 {
                     if (Selstage[2].equalsIgnoreCase("1"))//if its a mosquitoe
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugVillage[0];
                         }
                         else
                         {
                             values[i] = TBugVillage[0];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("2"))//if its a wasp
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugVillage[1];
                         }
                         else
                         {
                             values[i] = TBugVillage[1];
                         }
                     }
                     else if (Selstage[2].equalsIgnoreCase("3"))//if its a bettle
                     {
                         if (Selstage[4].equalsIgnoreCase("True"))//In one run
                         {
                             values[i] = BugVillage[2];
                         }
                         else
                         {
                             values[i] = TBugVillage[2];
                         }
                     }
                 }

             }
             else if (Selstage[0].equalsIgnoreCase("C"))
             {
                 if (LastRecord > 0 && LastRecord > newRecord)
                 {
                     values[i] = LastRecord;
                 }

             }
             else if (Selstage[0].equalsIgnoreCase("D"))
             {

                 if (newRecord < Integer.parseInt(Selstage[1]) && Integer.parseInt(Selstage[2]) == 1 && MosKilled == 0)
                 {
                     values[i] = newRecord;

                 }
                 else if (newRecord < Integer.parseInt(Selstage[1]) && Integer.parseInt(Selstage[2]) == 2 && WaspKilled == 0)
                 {
                     values[i] = newRecord;

                 }
                 else if (newRecord < Integer.parseInt(Selstage[1]) && Integer.parseInt(Selstage[2]) == 3 && BeeKilled == 0)
                 {
                     values[i] = newRecord;

                 }
             }

             else if (Selstage[0].equalsIgnoreCase("E"))
             {
                 if (Integer.parseInt(Selstage[2])== 1)//mosquitoes
                 {
                     if (Selstage[4].equalsIgnoreCase("True"))
                     {
                         values[i] = MosGuns[Integer.parseInt(Selstage[3])];
                     }
                     else
                     {
                         values[i] = TMosGuns[Integer.parseInt(Selstage[3])];
                     }
                 }
                 else if (Integer.parseInt(Selstage[2]) == 2)//wasp
                 {
                     if (Selstage[4].equalsIgnoreCase("True"))
                     {
                         values[i] = WaspGuns[Integer.parseInt(Selstage[3])];
                     }
                     else
                     {
                         values[i] = TWaspGuns[Integer.parseInt(Selstage[3])];
                     }
                    
                 }
                 else if (Integer.parseInt(Selstage[2]) == 3)//bettle
                 {
                     if (Selstage[4].equalsIgnoreCase("True"))
                     {
                         values[i] = BeeGuns[Integer.parseInt(Selstage[3])];
                     }
                     else
                     {
                         values[i] = TBeeGuns[Integer.parseInt(Selstage[3])];
                     }
                 }
                     // //"E.15.3.1.false.false";

             }

             else if (Selstage[0].equalsIgnoreCase("F"))
             {
                 //this means its mosquitoes and value is in one run
                 if (Selstage[2].equalsIgnoreCase("True"))
                 {
                     values[i] = CoinsColl;

                 }
                 //this means it mosquitoes and value is total killed
                 else if (Selstage[2].equalsIgnoreCase("False"))
                 {

                     values[i] = TotalCoinsColl;
                 }
                 //F.220.true.false
             }
         }

    }

     // 1..kill 10 Mosquitoes in one run				A.10.1.true.false   low 5 max 30
     //2..kill Total 100 Mosquitoes 				A.100.1.false.false     low 15 max 60
     //3..Kill 10 Mosquitoes in background A 	B.10.1.1.true.false     low 5 max  20
     //4..Kill total of 30 Mosquitoes in background A 		B.30.1.1.false.false  low 15 max 40
     //5..Make a new record 				C.false
     //6..Survive 50 Meters without killing a Mosquitoes			D.50.1.false    low 50 , max 300
     //8..Kill 15 Mosquitoes with A-Type Gun				E.15.1.1.false          low 5 max 35
     //9..Kill  60 Bugs with Revolver	E.60.6.2.false                          low 5 max 60

     //9..Collect 20 coins in one run				F.20.true.false             low 20 max 500
     //10..Collect Total of 50 coin 				F.50.false.false                low 50 max 1000

     String[] CreateA()
     {
         String[] level = new String[2];
         //level//A.10.1.true.false 
         //A..number to kill.. type to kill..in one run or not ...stage completed

         int autochooser = 1 + (int)(Math.random() * ((101 - 1))); //irandom.Next(1, 101);
         Integer expectedKills;
         int type;
         String typeName;
         boolean OneRun;



         //Picking a type based on result from randon value
         if (autochooser < 33) 
         {
             type = 1;  
             typeName = "Mosquitoes";
         }
         else if (autochooser >= 33 && autochooser < 66)
         {
             type = 2;
             typeName = "Wasp";
         }
         else
         {
             type = 3;
              typeName = "Bettle";
         }


         //Picking if you achieve value in one or not
         if (autochooser < 50)
             OneRun = true;
         else 
             OneRun = false;

         //If OneRun low 5 max 30
         //low 15 max 60
         if (Mission < 30)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 5 + (int)(Math.random() * ((10 - 5))); ////irandom.Next(5, 10);
             else expectedKills = 15 + (int)(Math.random() * ((25 - 15))); //irandom.Next(15, 25);
         }
         else if (Mission >= 30 && Mission < 60)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 10 + (int)(Math.random() * ((18 - 10))); //irandom.Next(10, 18);
             else expectedKills = 16 + (int)(Math.random() * ((28 - 16))); //irandom.Next(16, 28);
         }
         else if (Mission >= 60 & Mission < 90)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 8 + (int)(Math.random() * ((18 - 8))); //irandom.Next(8, 18);
             else expectedKills = 18 + (int)(Math.random() * ((30 - 18))); //irandom.Next(18, 30);

         }
         else if (Mission >= 90 & Mission < 120)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 10 + (int)(Math.random() * ((18 - 10))); //irandom.Next(10, 18);
             else expectedKills = 20 + (int)(Math.random() * ((35 - 20))); //irandom.Next(20, 35);

         }
         else if (Mission >= 120 & Mission < 150)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 12 + (int)(Math.random() * ((18 - 12))); //irandom.Next(12, 18);
             else expectedKills = 25 + (int)(Math.random() * ((40 - 25))); ////irandom.Next(25, 40);

         }
         else if (Mission >= 150 & Mission < 180)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 20 + (int)(Math.random() * ((20 - 14))); //irandom.Next(14, 20);
             else expectedKills = 30 + (int)(Math.random() * ((45 - 30))); //irandom.Next(30, 45);

         }
         else if (Mission >= 180 & Mission < 210)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 16 + (int)(Math.random() * ((22 - 16))); //irandom.Next(16, 22);
             else expectedKills = 35 + (int)(Math.random() * ((50 - 35))); //irandom.Next(35, 50);

         }
         else if (Mission >= 210 & Mission < 240)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 18 + (int)(Math.random() * ((25 - 18))); //irandom.Next(18, 25);
             else expectedKills = 40 + (int)(Math.random() * ((50 - 40))); //irandom.Next(40, 50);
         }
         else if (Mission >= 240 & Mission < 270)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 20 + (int)(Math.random() * ((28 - 20))); //irandom.Next(20, 28);
             else expectedKills = 40 + (int)(Math.random() * ((55 - 40))); //irandom.Next(40, 55);
         }
         else //if (Mission >= 90)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 31 + (int)(Math.random() * ((31 - 20))); //irandom.Next(20, 31);
             else expectedKills = 45 + (int)(Math.random() * ((61 - 45))); //irandom.Next(45, 61);
         }

         //"kill.20.Mosquitoes.in.one.run."	
         //A.10.1.true.false 

         level[0] = "A" + "," + expectedKills.toString() + "," + type + "," + OneRun + "," + "false"; //A.10.1.true.false 

         if (OneRun)
         {
             level[1] = "kill" + "," + expectedKills.toString() + "," + typeName + "," + "in,one,run,";
         }
         else //total run
         {
             level[1] = "kill" + "," + expectedKills.toString() + "," + typeName + "," + "in,total,";
         }

         return level;
     }

     String[] CreateB()
     {
         String[] level = new String[2];
          //B.10.1.1.true.false     low 5 max  20
          //B.30.1.1.false.false  low 15 max 40

         //Kill total of 30 Wasp  in Bugs forest 	B.30.2.1.false.false
         //Kill total of 30 Mosquitoes in Bugs forest 	B.30.1.1.false.false
         //Kill 5 Mosquitoes in the Night	B.5.1.4.true.false
         //Kill 18 Mosquitoes in the Day	B.18.1.5.true.false

         //3..Kill 10 Mosquitoes in background A 	B.10.1.1.true.false     low 5 max  20
         //4..Kill total of 30 Mosquitoes in background A 		B.30.1.1.false.false  low 15 max 40

         //type to kill...expected number..expected type..expected wheather..one run..completed

         int autochooser = 1 + (int)(Math.random() * ((101 - 1))); //irandom.Next(1, 101);
         Integer expectedKills;
         int type;
         int bgType;
         boolean OneRun;
         String typeName;
         String bgName;
        

         //Picking a type based on result from randon value
         if (autochooser < 33)
         {
             type = 1;
             typeName = "Mosquitoes";
         }
         else if (autochooser >= 33 && autochooser < 66)
         {
             type = 2;
             typeName = "Wasp";
         }
         else
         {
             type = 3;
             typeName = "Bettle";
         }

         //Picking if you achieve value in one or not
         if (autochooser < 50)
             OneRun = true;
         else
             OneRun = false;


         //If OneRun low 5 max 20
         //low 15 max 60
         if (Mission < 10)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 5 + (int)(Math.random() * ((10 - 5))); //irandom.Next(5, 10);
             else expectedKills = 15 + (int)(Math.random() * ((25 - 15))); //irandom.Next(15, 25);
         }
         else if (Mission >= 10 & Mission < 20)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 5 + (int)(Math.random() * ((12 - 5))); //irandom.Next(5, 12);
             else expectedKills = 16 + (int)(Math.random() * ((28 - 16))); ////irandom.Next(16, 28);
         }
         else if (Mission >= 20 & Mission < 30)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 8 + (int)(Math.random() * ((14 - 8))); //irandom.Next(8, 14);
             else expectedKills = 18 + (int)(Math.random() * ((30 - 18))); //irandom.Next(18, 30);

         }
         else if (Mission >= 30 & Mission < 40)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 8 + (int)(Math.random() * ((16 - 8))); //irandom.Next(8, 16);
             else expectedKills = 20 + (int)(Math.random() * ((35 - 20))); //irandom.Next(20, 35);

         }
         else if (Mission >= 40 & Mission < 50)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 9 + (int)(Math.random() * ((16 - 9))); //irandom.Next(9, 16);
             else expectedKills = 25 + (int)(Math.random() * ((40 - 25))); //irandom.Next(25, 40);

         }
         else if (Mission >= 50 & Mission < 60)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 10 + (int)(Math.random() * ((18 - 10))); //irandom.Next(10, 18);
             else expectedKills = 30 + (int)(Math.random() * ((45 - 30))); //irandom.Next(30, 45);

         }
         else if (Mission >= 60 & Mission < 70)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 12 + (int)(Math.random() * ((18 - 12))); //irandom.Next(12, 18);
             else expectedKills = 35 + (int)(Math.random() * ((50 - 35))); //irandom.Next(35, 50);

         }
         else if (Mission >= 70 & Mission < 80)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 12 + (int)(Math.random() * ((18 - 12))); //irandom.Next(12, 18);
             else expectedKills = 40 + (int)(Math.random() * ((50 - 40))); //irandom.Next(40, 50);
         }
         else if (Mission >= 80 & Mission < 90)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 14 + (int)(Math.random() * ((20 - 14))); //irandom.Next(14, 20);
             else expectedKills = 40 + (int)(Math.random() * ((55 - 40))); //irandom.Next(40, 55);
         }
         else //if (Mission >= 90)
         {
             //If OneRun low 5 max 20
             //low 15 max 60
             if (OneRun) expectedKills = 14 + (int)(Math.random() * ((20 - 14))); //irandom.Next(14, 20);
             else expectedKills = 45 + (int)(Math.random() * ((61 - 45))); //irandom.Next(45, 61);
         }



         //Picking a background type based on result from randon value
         if (autochooser < 33)
         {
             bgType = 0;
             bgName = "Bug,Forest,";
         }
         else if (autochooser >= 33 && autochooser < 67)
         {
             bgType = 1;
             bgName = "the,Night,";
         }
         else //if (autochooser >= 50 && autochooser < 75)
         {
             bgType = 2;
             bgName = "Bug,Village,";
         }
         

         level[0] = "B" + "," + expectedKills.toString() + "," + type + "," + bgType + "," + OneRun + "," + "false"; //B.10.1.1.true.false 

         if (OneRun)
         {
             level[1] = "kill" + "," + expectedKills.toString() + "," + typeName + "," + "in" + "," + bgName + "in,one,run,";
         }
         else //total run
         {
             level[1] = "kill" + "," + expectedKills.toString() + "," + typeName + "," + "in" + "," + bgName + "in,total,";
         }


         //Kill total of 30 Wasp  in Bugs forest
         //Kill 10 Mosquitoes in background A


       

         return level;
     }

     String[] CreateC()
     {
         String[] level = new String[2];
        // Make a new record 	C.false

         level[0] = "C,false";
         level[1] = "make,new,record,";

         return level;
     }

     String[] CreateD()
     {
         String[] level = new String[2];
        // Survive 50 Meters without killing a Mosquitoes			D.50.1.false    low 50 , max 300

         int autochooser = 1 + (int)(Math.random() * ((101 - 1))); //irandom.Next(1, 101);
         Integer expectedMeter;
         Integer type;
         String typeName;
         

         //Picking a type based on result from randon value
         if (autochooser < 33)
         {
             type = 1;
             typeName = "Mosquitoe";
         }
         else if (autochooser >= 33 && autochooser < 66)
         {
             type = 2;
             typeName = "Wasp";
         }
         else
         {
             type = 3;
             typeName = "Bettle";
         }


        //low 50 , max 300
         if (Mission < 10)
         {
             //low 50 , max 300
            expectedMeter = 150 + (int)(Math.random() * ((300 - 150))); // irandom.Next(150, 300);
             
         }
         else if (Mission >= 10 & Mission < 20)
         {
             //low 50 , max 300
             expectedMeter = 150 + (int)(Math.random() * ((500 - 150))); //irandom.Next(150, 500);
         }
         else if (Mission >= 20 & Mission < 30)
         {
             //low 50 , max 300
             expectedMeter = 200 + (int)(Math.random() * ((800 - 200))); //irandom.Next(200, 800);
         }
         else if (Mission >= 30 & Mission < 40)
         {
             //low 50 , max 300
             expectedMeter = 300 + (int)(Math.random() * ((100 - 300))); //irandom.Next(300, 1000);
         }
         else if (Mission >= 40 & Mission < 50)
         {
             //low 50 , max 300
             expectedMeter = 400 + (int)(Math.random() * ((1200 - 400))); //irandom.Next(400, 1200);
         }
         else if (Mission >= 50 & Mission < 60)
         {
             //low 50 , max 300
             expectedMeter = 400 + (int)(Math.random() * ((1500 - 400))); //irandom.Next(400, 1500);
         }
         else if (Mission >= 60 & Mission < 70)
         {
             //low 50 , max 300
             expectedMeter = 500 + (int)(Math.random() * ((1500 - 500))); //irandom.Next(500, 1500);
         }
         else if (Mission >= 70 & Mission < 80)
         {
             //low 50 , max 300
             expectedMeter = 500 + (int)(Math.random() * ((2000 - 500))); //irandom.Next(500, 2000);
         }
         else if (Mission >= 80 & Mission < 90)
         {
             //low 50 , max 300
             expectedMeter = 500 + (int)(Math.random() * ((2500 - 500))); //irandom.Next(500, 2500);
         }
         else //if (Mission >= 90)
         {
             //low 50 , max 300
             expectedMeter = 700 + (int)(Math.random() * ((3000 - 700))); //irandom.Next(700, 3000);
         }


         level[0] = "D" + "," + expectedMeter.toString() + "," + type.toString() +  "," + "false"; //A.10.1.true.false 


         level[1] = "Survive" + "," + expectedMeter.toString()  + ",meters,without,killing,a" + "," + typeName + ",";
         

         // Survive 50 Meters without killing a Mosquitoes			D.50.1.false    low 50 , max 300
         return level;
     }

     String[] CreateE()
     {
         String[] level = new String[2];

         int autochooser =  1 + (int)(Math.random() * ((101 - 1))); // irandom.Next(1, 101);
         Integer expectedKills;
         Integer type;
         String typeName;
         Integer gunType;
         String gunName;
         boolean OneRun;



         //Picking a type based on result from randon value
         if (autochooser < 33)
         {
             type = 1;
             typeName = "Mosquitoes";
         }
         else if (autochooser >= 33 && autochooser < 66)
         {
             type = 2;
             typeName = "Wasp";
         }
         else
         {
             type = 3;
             typeName = "Bettle";
         }

         //Picking if you achieve value in one or not
         if (autochooser < 50)
             OneRun = true;
         else
             OneRun = false;


        // low 5 max 35
        //low 5 max 60
         if (Mission < 10)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60         
             if (OneRun) expectedKills = 5 + (int)(Math.random() * ((10 - 5))); // irandom.Next(5, 10);
             else expectedKills = 15 + (int)(Math.random() * ((25 - 15))); // irandom.Next(15, 25);
             gunType =  0 + (int)(Math.random() * ((2 - 0))); //irandom.Next(0, 2);
         }
         else if (Mission >= 10 & Mission < 20)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills =  10 + (int)(Math.random() * ((18 - 10))); //irandom.Next(10, 18);
             else expectedKills =  16 + (int)(Math.random() * ((28 - 16))); //irandom.Next(16, 28);
             gunType =  1 + (int)(Math.random() * ((4 - 1))); //irandom.Next(1, 4);
         }
         else if (Mission >= 20 & Mission < 30)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills =  8 + (int)(Math.random() * ((18 - 8))); //irandom.Next(8, 18);
             else expectedKills =  18 + (int)(Math.random() * ((30 - 18))); //irandom.Next(18, 30);
             gunType =  1 + (int)(Math.random() * ((6 - 1))); //irandom.Next(1, 6);

         }
         else if (Mission >= 30 & Mission < 40)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills =  10 + (int)(Math.random() * ((18 - 10))); //irandom.Next(10, 18);
             else expectedKills =  20 + (int)(Math.random() * ((35 - 20))); //irandom.Next(20, 35);
             gunType =  1 + (int)(Math.random() * ((8 - 1))); //irandom.Next(1, 8);

         }
         else if (Mission >= 40 & Mission < 50)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills =  12 + (int)(Math.random() * ((18 - 12))); //irandom.Next(12, 18);
             else expectedKills =  25 + (int)(Math.random() * ((40 - 25))); //irandom.Next(25, 40);
             gunType =  1 + (int)(Math.random() * ((10 - 1))); //irandom.Next(1, 10);

         }
         else if (Mission >= 50 & Mission < 60)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills =  14 + (int)(Math.random() * ((20 - 14))); //irandom.Next(14, 20);
             else expectedKills =  30 + (int)(Math.random() * ((35 - 30))); //irandom.Next(30, 45);
             gunType = 1 + (int)(Math.random() * ((12 - 3))); //irandom.Next(1, 12);

         }
         else if (Mission >= 60 & Mission < 70)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 16 + (int)(Math.random() * ((22 - 16))); // irandom.Next(16, 22);
             else expectedKills = 35 + (int)(Math.random() * ((50 - 35))); // irandom.Next(35, 50);
             gunType = 1 + (int)(Math.random() * ((14 - 1))); // irandom.Next(1, 14);

         }
         else if (Mission >= 70 & Mission < 80)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 18 + (int)(Math.random() * ((25 - 18))); // irandom.Next(18, 25);
             else expectedKills =  40 + (int)(Math.random() * ((50 - 40))); //irandom.Next(40, 50);
             gunType =  1 + (int)(Math.random() * ((16 - 1))); //irandom.Next(1, 16);
         }
         else if (Mission >= 80 & Mission < 90)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 20 + (int)(Math.random() * ((31 - 20))); // irandom.Next(20, 31);
             else expectedKills = 40 + (int)(Math.random() * ((55 - 40))); // irandom.Next(40, 55);
             gunType = 1 + (int)(Math.random() * ((18 - 1))); // irandom.Next(1, 18);
         }
         else //if (Mission >= 90)
         {
             //If OneRun low 5 max 30
             //If Total low 15 max 60
             if (OneRun) expectedKills = 20 + (int)(Math.random() * ((35 - 20))); // irandom.Next(20, 35);
             else expectedKills = 45 + (int)(Math.random() * ((61 - 45))); // irandom.Next(45, 61);
             gunType =  1 + (int)(Math.random() * ((18 - 1))); //irandom.Next(1, 18);
         }


         if (gunType == 0) gunName = "Light,Machine,Gun,";
         else if (gunType == 1) gunName = "Revolver,";
         else if (gunType == 2) gunName = "10,guage,SuperShot,";
         else if (gunType == 3) gunName = "12,guage,SuperShot,";
         else if (gunType == 4) gunName = "ZRU,12,Advanced,";
         else if (gunType == 5) gunName = "Police,Model,500,";
         else if (gunType == 6) gunName = "C,16,Auto,";
         else if (gunType == 7) gunName = "AR,19,Basic,";
         else if (gunType == 8) gunName = "MP,924,";
         else if (gunType == 9) gunName = "Desert,Hawk,";
         else if (gunType == 10) gunName = "B,19,";
         else if (gunType == 11) gunName = "Hp,k5,";
         else if (gunType == 12) gunName = "M30,schmeizer,";
         else if (gunType == 13) gunName = "k7,yugoslavian,";
         else if (gunType == 14) gunName = "Browning,M1919,";
         else if (gunType == 15) gunName = "M31,Devastator,";
         else if (gunType == 16) gunName = "Mortara,M75,";
         else gunName = "kasa,55,";//if (gunType == 17)


         //8..Kill 15 Mosquitoes with A-Type Gun				E.15.1.1.false          low 5 max 35
         //9..Kill  60 Bugs with Revolver	                E.60.6.2.false          low 5 max 60
         level[0] = "E" + "," + expectedKills.toString() + "," + type.toString() + "," + gunType.toString() + "," + OneRun + "," + "false"; //E.15.1.1.false.false  

         if (OneRun)
         {
             level[1] = "Kill" + "," + expectedKills.toString() + "," + typeName + ",with" + "," + gunName +  "in,one,run,";
         }
         else //total run
         {
             level[1] = "Kill" + "," + expectedKills.toString() + "," + typeName + ",with" + "," + gunName +  "in,Total,";
         }

         return level;
     }

     String[] CreateF()
     {
         String[] level = new String[2];
         //9..Collect 20 coins in one run				F.20.true.false             low 20 max 500
         //10..Collect Total of 50 coin 				F.50.false.false                low 50 max 1000
         int autochooser = 1 + (int)(Math.random() * ((101 - 1))); //irandom.Next(1, 101);
         Integer expectedCoins;
         boolean OneRun;


         //Picking if you achieve value in one or not
         if (autochooser < 50)
             OneRun = true;
         else
             OneRun = false;

         //If OneRun low 20 max 500
         //If Total low 50 max 1000
         if (Mission < 10)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 20 + (int)(Math.random() * ((60 - 20))); //irandom.Next(20, 60);
             else expectedCoins = 50 + (int)(Math.random() * ((100 - 50))); //irandom.Next(50, 100);
         }
         else if (Mission >= 10 & Mission < 20)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 40 + (int)(Math.random() * ((120 - 40))); //irandom.Next(40, 120);
             else expectedCoins = 100 + (int)(Math.random() * ((200 - 100))); //irandom.Next(100, 200);
         }
         else if (Mission >= 20 & Mission < 30)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 60 + (int)(Math.random() * ((180 - 60))); //irandom.Next(60, 180);
             else expectedCoins = 200 + (int)(Math.random() * ((300 - 200))); //irandom.Next(200, 300);

         }
         else if (Mission >= 30 & Mission < 40)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 80 + (int)(Math.random() * ((240 - 80))); //irandom.Next(80, 240);
             else expectedCoins = 300 + (int)(Math.random() * ((400 - 300))); //irandom.Next(300, 400);

         }
         else if (Mission >= 40 & Mission < 50)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 100 + (int)(Math.random() * ((280 - 100))); //irandom.Next(100, 280);
             else expectedCoins = 400 + (int)(Math.random() * ((500 - 400))); //irandom.Next(400, 500);

         }
         else if (Mission >= 50 & Mission < 60)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 150 + (int)(Math.random() * ((300 - 150))); //irandom.Next(150, 300);
             else expectedCoins = 500 + (int)(Math.random() * ((600 - 500))); //irandom.Next(500, 600);

         }
         else if (Mission >= 60 & Mission < 70)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 200 + (int)(Math.random() * ((400 - 200))); // irandom.Next(200, 400);
             else expectedCoins =  500 + (int)(Math.random() * ((700 - 500))); //irandom.Next(500, 700);

         }
         else if (Mission >= 70 & Mission < 80)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins =  300 + (int)(Math.random() * ((500 - 300))); //irandom.Next(300, 500);
             else expectedCoins =  500 + (int)(Math.random() * ((800 - 500))); //irandom.Next(500,800);
         }
         else if (Mission >= 80 & Mission < 90)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins = 300 + (int)(Math.random() * ((500 - 300))); // irandom.Next(300, 500);
             else expectedCoins = 600 + (int)(Math.random() * ((1001 - 600))); // irandom.Next(600, 1001);
         }
         else //if (Mission >= 90)
         {
             //If OneRun low 20 max 500
             //If Total low 50 max 1000
             if (OneRun) expectedCoins =  31 + (int)(Math.random() * ((300 - 31))); //irandom.Next(300, 31);
             else expectedCoins = 700 + (int)(Math.random() * ((1000 - 700))); // irandom.Next(700, 1000);
         }

         //9..Collect 20 coins in one run				c             low 20 max 500

         level[0] = "F" + "," + expectedCoins.toString() + "," + OneRun + "," + "false"; //F.20.true.false  

         if (OneRun)
         {
             level[1] = "collect" + "," + expectedCoins.toString() + ",coins," + "in,one,run,";
         }
         else //total run
         {
             level[1] = "collect" + "," + expectedCoins.toString() + ",coins," + "in,total,";
         }

         return level;
     }
}
