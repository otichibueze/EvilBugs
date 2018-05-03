package chibu.soft.evilbugs;


//import android.R.bool;



public class Vector2 {
	
	 public int X = 0, Y = 0;
		
		//private final static double defaultDeviceWidth = 800; //This should be the original width worked with
		//private final static double defaultDeviceHeight = 480; //This should be the original height worked
		
		
		//private static int deviceHeight ;
		//private static int deviceWidth ;
		
		// find the width and height of the screen:
		//Display d = getWindowManager().getDefaultDisplay();
		//int x = d.getWidth();
		//int y = d.getHeight();
		
		
		public Vector2()
		{
			X = 0; Y = 0;
		}
		
		public Vector2(int x, int y){
			
		     //deviceHeight = EvilBugsView.deviceHeight;
			 //deviceWidth = EvilBugsView.deviceWidth;
			
			//y = (int) ((deviceHeight * y) / defaultDeviceHeight); //this get y position for new screen
			//x = (int) ((deviceWidth * x) / defaultDeviceWidth);  //this gets the x position for new screen
			x = (int)(x * EvilBugsView.PositionfactorX);
			y = (int)(y * EvilBugsView.PositionfactorY);
			X = x; Y = y; 
			
			
		}
		public Vector2(float x, float y){
			// deviceHeight = EvilBugsView.deviceHeight;
			// deviceWidth = EvilBugsView.deviceWidth;
			 
			//y = (float) ((deviceHeight * y) / defaultDeviceHeight); //this get y position for new screen
			//x = (float) ((deviceWidth * x) / defaultDeviceWidth);  //this gets the x position for new screen
			x = (float)(x * EvilBugsView.PositionfactorX);
			y = (float)(y * EvilBugsView.PositionfactorY);
			X = (int)x; Y = (int)y; 
		}
		public Vector2(double x, double y){
			
			
			X = (int)x; Y = (int)y; 
			
		}
        public Vector2(double x, double y,Boolean scale){
			
        	if(scale)
        	{
        		//deviceHeight = EvilBugsView.deviceHeight;
    			//deviceWidth = EvilBugsView.deviceWidth;
    			
    			//y = (deviceHeight * y) / defaultDeviceHeight; //this get y position for new screen
    			//x = (deviceWidth * x) / defaultDeviceWidth;  //this gets the x position for new screen
				x = x * EvilBugsView.PositionfactorX;
				y = y * EvilBugsView.PositionfactorY;
    			X = (int)x; Y = (int)y; 
        	}
        	else 
        	{
        		   X = (int)x; Y = (int)y; 
		    }
			
			
		}

		
		public int getX(){return  X;}
		public int getY(){return Y;}
		public void setX(int x){X =  x;}
		public void setY(int y){Y =  y;}
		public void setX(float x){X = (int)x;}
		public void setY(float y){Y = (int)y;}
		public void setX(double x){X = (int)x;}
		public void setY(double y){Y = (int)y;}

}
