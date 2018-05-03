package chibu.soft.evilbugs;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;



public class Sprite {
	
	public Bitmap texture2D;
	public Bitmap texture2Dflip;
 	private double DefaultDeviceWidth = 800, DefaultDeviceheight =480; //pls make sure this value is always double if not you wont get 
 	//exact value when you multiply

 	 public double Texture2DWidth, Texture2DHeight;//pls make sure this value is always double if not you wont get 
  	//exact value when you multiply
 	 
     private Rect rectangle;

     private double deviceWidth, deviceHeight;

     //new code from javasprite
     private int rows, columns, spriteWidth, spriteHeight;
     public int currentIndex;
     public Vector2 location;

     
 	//Creating frames
     public Bitmap[] frame;
     int framecount;
     int checkframe = 0;
     public float x;
     public float y;
     private int speed;
     public int counter = 0;
     
     Matrix matrix;
     
     float scaleX, scaleY;
     
     Rect src;
     Rect dst;
     
	
     //just to rotate
    //Matrix matrix = mMatrix;
    // matrix.reset ();
    // int rotation = getDegrees ();
    // matrix.postRotate(rotation);
  // Finally, draw the bitmap using the matrix as a guide.
  
     //canvas.drawBitmap (pBitmap, matrix, null);
     
     //flip image
    // Matrix flipHorizontalMatrix = new Matrix();
     //flipHorizontalMatrix.setScale(-1,1);
    // flipHorizontalMatrix.postTranslate(myBitmap.getWidth(),0);

    // canvas.drawBitmap(myBitmap, flipHorizontalMatrix, myPaint);
	
	private Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
	//private Paint mpaint = new Paint();	
	
		
	 public  Sprite(Vector2 Canvas,Bitmap[] frames, int Framecount, float X, float Y,int Speed)
     {
		 matrix = new Matrix();
		 
		 
         this.frame = frames;
         this.framecount = Framecount;
         this.x = X;
         this.y = Y;
         this.speed = Speed;

         deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;
         
         y = (float) ((deviceHeight * y) / DefaultDeviceheight); //for checking where y should be in different screens

         x = (float) ((deviceWidth * x) / DefaultDeviceWidth);  //for checking where x should be in different screens

         //200(target screen width)/100(default screen width) = 2
         double width = deviceWidth / DefaultDeviceWidth; 
    	 width = width *  frames[0].getWidth();
    	 
    	 
    	 double height = deviceHeight / DefaultDeviceheight;
    	 height = height *   frames[0].getHeight();
    	 
    	
         rectangle = new Rect((int)y, (int)x, (int)width, (int)height);
        
         Texture2DWidth = width;
         Texture2DHeight = height;
     }
	 
	 public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight)
	 {
	     int width = bm.getWidth();
	     int height = bm.getHeight();
	     float scaleWidth = ((float) newWidth) / width;
	     float scaleHeight = ((float) newHeight) / height;
	     // create a matrix for the manipulation
	     Matrix matrix = new Matrix();
	     // resize the bit map
	     matrix.postScale(scaleWidth, scaleHeight);
	     // recreate the new Bitmap
	     Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	     return resizedBitmap;
	 }
	 
	 
	 public static int calculateInSampleSize(
	            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
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
	 
	 public Sprite(Resources game, Vector2 Canvas, int image) 
     {
		// Drawable aa = getAssetImage(getBaseContext(), "hello");
		
		 matrix = new Matrix();
		 
		 deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;
         
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds = true;
         BitmapFactory.decodeResource(game, image, options);
         int imageHeight = options.outHeight;
         int imageWidth = options.outWidth;
         //String imageType = options.outMimeType;
         
         double width = deviceWidth / DefaultDeviceWidth; 
    	 width = width * imageWidth;
    	 
    	 
    	 double height = deviceHeight / DefaultDeviceheight;
    	 height = height * imageHeight;
         
    	 
    	 scaleX =  ((float) width) / imageWidth;
         scaleY = ((float) height) / imageHeight;
         
         texture2D = decodeSampledBitmapFromResource(game, image, (int)width, (int)height);
         
         Texture2DWidth = width;
         Texture2DHeight = height;
         
       
        
         
         //old code
//         matrix = new Matrix();
//
//         deviceWidth = (int)Canvas.X;
//         deviceHeight = (int)Canvas.Y;
//
//    	 //this.texture2D = game.Load<Texture2D>(image);
//    	  texture2D = BitmapFactory.decodeResource(game, image); 
//
//    	 //200(target screen width)/100(default screen width) = 2
//    	 double width = deviceWidth / DefaultDeviceWidth; 
//    	 width = width * texture2D.getWidth();
//    	 
//    	 
//    	 double height = deviceHeight / DefaultDeviceheight;
//    	 height = height * texture2D.getHeight();
//
//    	 
//    	 texture2D = Bitmap.createScaledBitmap(texture2D, (int)width, (int)height, false);
//    	
//         Texture2DWidth = width;
//    	 Texture2DHeight = height;
     }
	 	 
	 public Sprite(Resources game, Vector2 Canvas, int image, float X, float Y)
     {
         matrix = new Matrix();
         
         deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;
         
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds = true;
         BitmapFactory.decodeResource(game, image, options);
         int imageHeight = options.outHeight;
         int imageWidth = options.outWidth;
         //String imageType = options.outMimeType;
         
         double width = deviceWidth / DefaultDeviceWidth; 
    	 width = width * imageWidth;
    	 
    	 
    	 double height = deviceHeight / DefaultDeviceheight;
    	 height = height * imageHeight;
         
       
    	 scaleX =  ((float) width) / imageWidth;
         scaleY = ((float) height) / imageHeight;
         
         texture2D = decodeSampledBitmapFromResource(game, image, (int)width, (int)height);
         
         location = new Vector2(X, Y);
    	  x = location.X; y = location.Y;
         
         Texture2DWidth = width;
         Texture2DHeight = height;
         
        
         
		 //old code
//			 matrix = new Matrix();
//			 
//	         deviceWidth = (int)Canvas.X;
//	         deviceHeight = (int)Canvas.Y;
//
//	    	 //this.texture2D = game.Load<Texture2D>(image);
//	    	 texture2D = BitmapFactory.decodeResource(game, image); 
//
//	         //200(target screen width)/100(default screen width) = 2
//	         double width = deviceWidth / DefaultDeviceWidth; 
//	    	 width = width * texture2D.getWidth();  //used to calculate rescale
//	    	 
//	    	 
//	    	 double height = deviceHeight / DefaultDeviceheight;
//	    	 height = height * texture2D.getHeight();  //used to calculate rescale
//	    	 
//	    	 texture2D = Bitmap.createScaledBitmap(texture2D, (int)width, (int)height, false);
//	    	 
//	    	
//	         location = new Vector2(X, Y);
//
//	         x = location.X; y = location.Y;
//
//	         //rectangle = new Rect(0, 0, (int)width, (int)height);
//
//	         Texture2DWidth = width;
//	         Texture2DHeight = height;
//		
     }
	 
	 public Sprite(Resources game, Vector2 Canvas, int image, int spriteWidth, int spriteHeight)
	    { 
		 matrix = new Matrix();
		 
		 deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;
         
         int spriteW = spriteWidth;
         int spriteH = spriteHeight;
        
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(game, image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        //String imageType = options.outMimeType;
        
        int frameW = imageWidth /spriteWidth; //this is done to get sprite column 
 		
        int frameH = imageHeight / spriteHeight;  //this is done to sprite new rows 
        
        double width = deviceWidth / DefaultDeviceWidth; 
        //this is done to get new spritewidth based on device screen size
         spriteWidth = (int)(spriteWidth * width);
   	   //this is used to calculate sprite total image based on new sprite
   	     width = spriteWidth * frameW;
   	 
   	 
   	     double height = deviceHeight / DefaultDeviceheight;
   	    //this is done to get new spriteHeight based on device screen size ratio
 	     spriteHeight = (int)(spriteHeight * height); 
   	     //this is used to calculate sprite total image based on new sprite
   	     height = spriteHeight * frameH;
   	    
   	
   	  scaleX =  ((float) width) / imageWidth;
      scaleY = ((float) height) / imageHeight;

      texture2D = decodeSampledBitmapFromResource(game, image, (int)width, (int)height);
        
      location = new Vector2();

//      
//        if(spriteW > spriteWidth)
//        	{
//        	JavaSprite(texture2D, spriteWidth, spriteHeight);
//        	 Texture2DWidth = spriteWidth;
// 	        Texture2DHeight = spriteHeight;
//        	}
//        else 
//        	{
        	JavaSprite(texture2D, spriteW, spriteH);
        	Texture2DWidth = spriteWidth;
 	        Texture2DHeight = spriteHeight;
//        	}
			
		
        
       
        
		 //old code
//		  matrix = new Matrix();
//		  
//	        deviceWidth = (int)Canvas.X;
//	        deviceHeight = (int)Canvas.Y;
//
//	    	 //this.texture2D = game.Load<Texture2D>(image);
//	    	 texture2D = BitmapFactory.decodeResource(game, image); 
//
//	        int frameW = texture2D.getWidth() /spriteWidth; //this is done to get sprite column 
//     		
//	        int frameH = texture2D.getHeight() / spriteHeight;  //this is done to sprite new rows 
//	        
//	        //200(target screen width)/100(default screen width) = 2
//	        double width = deviceWidth / DefaultDeviceWidth; //this is used to get ratio of new screen
//	        spriteWidth = (int)(spriteWidth * width); //this is done to get new spritewidth based on device screen size
//	       // width = width * texture2D.getWidth();
//	        width = spriteWidth * frameW; //this is used to calculate sprite total image based on new sprite
//	    	 
//	    	 
//	    	 double height = deviceHeight / DefaultDeviceheight; //this is used to get ratio of new screen
//	    	 spriteHeight = (int)(spriteHeight * height);        //this is done to get new spriteHeight based on device screen size
//		      // width = width * texture2D.getWidth();
//	    	 height = spriteHeight * frameH;   //this is used to calculate sprite total image based on new sprite
//	    	 
//	    	 
//	    
//	    	 texture2D = Bitmap.createScaledBitmap(texture2D, (int)width, (int)height, false); //this is used to rescale image based on new calculation
//
//	        
//	    	
//	       // rectangle = new Rect(0, 0, (int)spriteWidth, (int)spriteHeight);
//
//	        Texture2DWidth = width;
//	        Texture2DHeight = height;
//
//	       // w= spriteWidth; h = spriteHeight;
//	        
//	        JavaSprite(texture2D, spriteWidth, spriteHeight);
//	        //JavaSprite(texture2D, spriteWidth, spriteHeight);

	    }
	 
	 public Sprite(Resources game, Vector2 Canvas, int image,int imagefliped, int spriteWidth, int spriteHeight)
	    { 
		 matrix = new Matrix();
		 
		 deviceWidth = (int)Canvas.X;
        deviceHeight = (int)Canvas.Y;
      
      int spriteW = spriteWidth;
      int spriteH = spriteHeight;
     
     BitmapFactory.Options options = new BitmapFactory.Options();
     options.inJustDecodeBounds = true;
     BitmapFactory.decodeResource(game, image, options);
     int imageHeight = options.outHeight;
     int imageWidth = options.outWidth;
     //String imageType = options.outMimeType;
     
     int frameW = imageWidth /spriteWidth; //this is done to get sprite column 
		
     int frameH = imageHeight / spriteHeight;  //this is done to sprite new rows 
     
     double width = deviceWidth / DefaultDeviceWidth; 
     //this is done to get new spritewidth based on device screen size
      spriteWidth = (int)(spriteWidth * width);
	   //this is used to calculate sprite total image based on new sprite
	     width = spriteWidth * frameW;
	 
	 
	     double height = deviceHeight / DefaultDeviceheight;
	    //this is done to get new spriteHeight based on device screen size ratio
	     spriteHeight = (int)(spriteHeight * height); 
	     //this is used to calculate sprite total image based on new sprite
	     height = spriteHeight * frameH;
	    
	
	  scaleX =  ((float) width) / imageWidth;
      scaleY = ((float) height) / imageHeight;

      texture2D = decodeSampledBitmapFromResource(game, image, (int)width, (int)height);
      texture2Dflip = decodeSampledBitmapFromResource(game, imagefliped, (int)width, (int)height);


     	JavaSprite(texture2D, spriteW, spriteH);
     	Texture2DWidth = spriteWidth;
	    Texture2DHeight = spriteHeight;

	    }
	 
	  	    
	 public Sprite(Vector2 Canvas, Bitmap image)
	     {
		   matrix = new Matrix();
		   
	         deviceWidth = (int)Canvas.X;
	         deviceHeight = (int)Canvas.Y;

	         this.texture2D = image;

	         //200(target screen width)/100(default screen width) = 2
	         double width = deviceWidth / DefaultDeviceWidth; 
	    	 width = width *  texture2D.getWidth();
	    	 
	    	 
	    	 double height = deviceHeight / DefaultDeviceheight;
	    	 height = height *  texture2D.getHeight();
	 
	    	 scaleX =  ((float) width) / texture2D.getWidth();
	         scaleY = ((float) height) / texture2D.getHeight();

	         Texture2DWidth = width;
	         Texture2DHeight = height;
	     }
	 
	 public Sprite(Vector2 Canvas, Bitmap image, float X, float Y, Boolean ScalePosition)
     {
	     matrix = new Matrix();
	   
         deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;

         this.texture2D = image;


         //200(target screen width)/100(default screen width) = 2
         double width = deviceWidth / DefaultDeviceWidth; 
         width = width * texture2D.getWidth();
    	 
    	 
    	 double height = deviceHeight / DefaultDeviceheight;
    	 height = height * texture2D.getHeight();	    	 
    	
    	 if(ScalePosition == true)
    	 {
         location = new Vector2(X, Y);
    	 }
    	 else 
    	 {  		 
    		 location = new Vector2(X, Y,false);
    	 }
    		

         x = location.X; y = location.Y;

         scaleX =  ((float) width) / texture2D.getWidth();
         scaleY = ((float) height) / texture2D.getHeight();

         Texture2DWidth = width;
         Texture2DHeight = height;
     }
	   
	 public Sprite(Vector2 Canvas, Bitmap image, float X, float Y)
	     {
		   matrix = new Matrix();
		   
	         deviceWidth = (int)Canvas.X;
	         deviceHeight = (int)Canvas.Y;

	         this.texture2D = image;


	         //200(target screen width)/100(default screen width) = 2
	         double width = deviceWidth / DefaultDeviceWidth; 
	         width = width * texture2D.getWidth();
	    	 
	    	 
	    	 double height = deviceHeight / DefaultDeviceheight;
	    	 height = height * texture2D.getHeight();	    	 
	    	
	         location = new Vector2(X, Y);

	         x = location.X; y = location.Y;

	         scaleX =  ((float) width) / texture2D.getWidth();
	         scaleY = ((float) height) / texture2D.getHeight();

	         Texture2DWidth = width;
	         Texture2DHeight = height;
	     }
	   
	 public Sprite( Vector2 Canvas, Bitmap image, int spriteWidth, int spriteHeight)
	     {
		   matrix = new Matrix();
		   
	         deviceWidth = (int)Canvas.X;
	         deviceHeight = (int)Canvas.Y;

	         this.texture2D = image;
	         
	         
	         
	         int spriteW = spriteWidth;
	         int spriteH = spriteHeight;
	         
	         int frameW =  texture2D.getWidth() /spriteWidth; //this is done to get sprite column 
	  		
	         int frameH = texture2D.getHeight() / spriteHeight;  //this is done to sprite new rows 
	         
	         double width = deviceWidth / DefaultDeviceWidth; 
	         //this is done to get new spritewidth based on device screen size
	          spriteWidth = (int)(spriteWidth * width);
	    	   //this is used to calculate sprite total image based on new sprite
	    	     width = spriteWidth * frameW;
	    	 
	    	 
	    	     double height = deviceHeight / DefaultDeviceheight;
	    	    //this is done to get new spriteHeight based on device screen size ratio
	  	     spriteHeight = (int)(spriteHeight * height); 
	    	     //this is used to calculate sprite total image based on new sprite
	    	     height = spriteHeight * frameH;
	    	    
	    	
	    	  scaleX =  ((float) width) / texture2D.getWidth();
	          scaleY = ((float) height) / texture2D.getHeight();

	      
//	         if(spriteW > spriteWidth)
//	         	{
//	         	JavaSprite(texture2D, spriteWidth, spriteHeight);
//	         	 Texture2DWidth = spriteWidth;
//	  	        Texture2DHeight = spriteHeight;
//	         	}
//	         else 
//	         	{
	         	JavaSprite(texture2D, spriteW, spriteH);
	         	Texture2DWidth = spriteWidth;
	  	        Texture2DHeight = spriteHeight;
//	         	}
	 			


//	           int frameW = texture2D.getWidth() /spriteWidth;
//     		
//		        int frameH = texture2D.getHeight() / spriteHeight;
//		        
//		        //200(target screen width)/100(default screen width) = 2
//		        double width = deviceWidth / DefaultDeviceWidth; 
//		        spriteWidth = (int)(spriteWidth * width);
//		       // width = width * texture2D.getWidth();
//		        width = spriteWidth * frameW;
//		    	 
//		    	 
//		    	 double height = deviceHeight / DefaultDeviceheight;
//		    	 spriteHeight = (int)(spriteHeight * height);
//			      // width = width * texture2D.getWidth();
//		    	 height = spriteHeight * frameH;
//		    	 
//		    
//		    	// texture2D = Bitmap.createScaledBitmap(texture2D, (int)width, (int)height, true);
//
//	         Texture2DWidth = width;
//	         Texture2DHeight = height;
//	         
//	         //w= spriteWidth; h = spriteHeight;
//
//	         JavaSprite( texture2D, spriteWidth, spriteHeight);
//	         //JavaSprite( texture2D, width, height);

	     }
	 
	 
	 public Sprite( Vector2 Canvas, Bitmap image, Bitmap imagefliped, int spriteWidth, int spriteHeight)
     {
	   matrix = new Matrix();
	   
         deviceWidth = (int)Canvas.X;
         deviceHeight = (int)Canvas.Y;

         this.texture2D = image;
         this.texture2Dflip = imagefliped;
         
         
         
         int spriteW = spriteWidth;
         int spriteH = spriteHeight;
         
         int frameW =  texture2D.getWidth() /spriteWidth; //this is done to get sprite column 
  		
         int frameH = texture2D.getHeight() / spriteHeight;  //this is done to sprite new rows 
         
         double width = deviceWidth / DefaultDeviceWidth; 
         //this is done to get new spritewidth based on device screen size
          spriteWidth = (int)(spriteWidth * width);
    	   //this is used to calculate sprite total image based on new sprite
    	     width = spriteWidth * frameW;
    	 
    	 
    	     double height = deviceHeight / DefaultDeviceheight;
    	    //this is done to get new spriteHeight based on device screen size ratio
  	     spriteHeight = (int)(spriteHeight * height); 
    	     //this is used to calculate sprite total image based on new sprite
    	     height = spriteHeight * frameH;
    	    
    	
    	  scaleX =  ((float) width) / texture2D.getWidth();
          scaleY = ((float) height) / texture2D.getHeight();

      
         	JavaSprite(texture2D, spriteW, spriteH);
         	Texture2DWidth = spriteWidth;
  	        Texture2DHeight = spriteHeight;

     }
	 

	   
	  public void DrawFrame(Canvas spriteBatch,int frameIndex,float X, float Y)
	     {
		   
	         rectangle.left = (int)x;
	         rectangle.top = (int)y;

	         spriteBatch.drawBitmap(frame[frameIndex], null, rectangle, paint);

	     }
	   
	   public void DrawFrame(Canvas spriteBatch)
	     {
	         if (checkframe < framecount)
	         {

	                 spriteBatch.drawBitmap(frame[checkframe], x, y, paint);
	            
	             checkframe += 1;
	         }

	         if (checkframe == framecount) checkframe = 0;
	     }
	   
	   public void DrawFrame(Canvas spriteBatch, int max)
	     {
	         rectangle.left = (int)x;
	         rectangle.top = (int)y;

	         counter += 1;
	         if (checkframe < framecount)
	         {	             
	                 spriteBatch.drawBitmap(frame[checkframe],null, rectangle, paint);	               
	         }

	         if (counter == max)
	         {
	             checkframe += 1;
	             counter = 0;
	         }


	         if (checkframe == framecount) checkframe = 0;
	     }
	   
	   public void setLocation(Vector2 ilocation)
	     {	       
	         location = ilocation;

	         this.x = ilocation.X; this.y = ilocation.Y;
	     }
	     
	   public void setLocation(float X,float Y)
	     {
	         location = new Vector2(X,Y);

	         this.x = X; this.y = Y;
	     }
	   
	   public void Draw(Canvas spriteBatch,int X,int Y) 
	     {
//		 matrix.reset();
//      	 matrix.setTranslate(location.X, location.Y);
//      	 matrix.setScale(scaleX,scaleY);
//      	 spriteBatch.drawBitmap(texture2D,matrix, paint);
      	 
	          spriteBatch.save();
	          spriteBatch.scale(scaleX, scaleY, X, Y);
	          spriteBatch.drawBitmap(texture2D, X, Y, paint);
	          spriteBatch.restore();

	     }
	   
	   public void Draw(Canvas spriteBatch,float  X, float Y)
	     {
	     
	           spriteBatch.save();
	          spriteBatch.scale(scaleX, scaleY, X, Y);
	          spriteBatch.drawBitmap(texture2D, X, Y, paint);
	          spriteBatch.restore();
	           
	     }
	   
	   public void Draw(Canvas spriteBatch,Vector2 Mylocation)
	     {
	          location = Mylocation;
         
	          spriteBatch.save();
	          spriteBatch.scale(scaleX, scaleY, Mylocation.X, Mylocation.Y);
	          spriteBatch.drawBitmap(texture2D, Mylocation.X, Mylocation.Y, paint);
	          spriteBatch.restore();
 
	     }
	   	   
	   public void Draw(Canvas spriteBatch, Vector2 Mylocation, int flip)
	     {
		   //i think flip should be -1 or 1 not yet tested
		   
	         location = Mylocation;
    
	         if(flip == 1)
	         {
              //code is used to flip image
	        	 matrix.reset();
	        	 matrix.setTranslate(location.X, location.Y);
	        	 matrix.setScale(-1,1);
	        	 matrix.postTranslate(location.X + texture2D.getWidth(), location.Y);
                // spriteBatch.drawBitmap(texture2D,matrix,paint);
               
              spriteBatch.save();
 	          spriteBatch.scale(scaleX, scaleY, Mylocation.X, Mylocation.Y);
 	          spriteBatch.drawBitmap(texture2D,matrix,paint);
 	          spriteBatch.restore();

	         }
	         else if(flip == 0)
	         { 
	        	
	        // spriteBatch.drawBitmap(texture2D, location.X, location.Y, paint);	         
	          spriteBatch.save();
	          spriteBatch.scale(scaleX, scaleY, Mylocation.X, Mylocation.Y);
	          spriteBatch.drawBitmap(texture2D, Mylocation.X, Mylocation.Y, paint);
	          spriteBatch.restore();
	         }
	      
	     }
	   
	   public void Draw(Canvas spriteBatch, Vector2 Mylocation,Paint color)
	     {
		   //The caller of these method should call paint and pass in the color method
		   	     
	         location = Mylocation;
	       
	         spriteBatch.save();
	          spriteBatch.scale(scaleX, scaleY, Mylocation.X, Mylocation.Y);
	          spriteBatch.drawBitmap(texture2D, Mylocation.X, Mylocation.Y, color);
	          spriteBatch.restore();
	         
	     }
	   
	   public void Draw(Canvas spriteBatch,Boolean fullscreen) 
	     {
	             spriteBatch.drawBitmap(texture2D,null, new Rect(0, 0, (int)(deviceWidth), (int)(deviceHeight)), paint); 
	             	            
	     }
	   
	   public void Draw(Canvas spriteBatch,float scale,int Rotation, int X, int Y)
	     {
	      //This method is meant to draw a part of an image on screen based on origial image size and dimensions
		   //thats why we scale image Y back to original screen b4 drawing it

		   
		   // Use 960 * 60 = 57600 / 480 = 120
	    	 
	       	 int olddeviceheight = 480;
	    	 
	    	 Y = (int) olddeviceheight* Y  / (int)deviceHeight;
	
	    	 int YDiff = Y - 320;  //we use 320 cause thats the starting point of Y so we can add diff on both sides
	    	 //we also did this for only Y cause X is constant
		      src = new Rect(X,Y,texture2D.getWidth(), texture2D.getHeight()  + YDiff); //this is image 
	          dst = new Rect(0,0, (int)deviceWidth,(int)deviceHeight);  //XandY on screen to draw plus width and height
	             
	            
		     spriteBatch.drawBitmap(texture2D,src,dst, paint);  //we dont need scale cause we have already scaled in dst
		     
	     }
	   
	   public void Draw(Canvas spriteBatch, float scale, float angleRadians, Vector2 Position, Vector2 Origin,int flip)
	     {
	        
	           //   spriteBatch.Draw(texture2D, new Vector2(Position.X, Position.Y), null, Color.White, angleRadians, Origin, scale, spriteEffects, 0);
	          if(flip == 1)
	          {
	        	 // Matrix matrix = new Matrix();
	        	  //code is used to flip image
		        	 matrix.reset();
		        	 matrix.setTranslate(Position.X, Position.Y);
		        	 matrix.setScale(-1,1);
		        	 matrix.postTranslate(Position.X + texture2D.getWidth(), Position.Y);
	             
		         	  spriteBatch.save();
		              spriteBatch.scale(scaleX, scaleY, Position.X, Position.Y );
		              spriteBatch.rotate(angleRadians,Position.X + Origin.X,Position.Y + Origin.Y );//+ Origin.Y
		              spriteBatch.drawBitmap(texture2D,matrix, paint);
		              spriteBatch.restore();
	              
	          }
	          else if(flip == 0)
	          {  
	              spriteBatch.save();
	              spriteBatch.scale(scaleX, scaleY, Position.X, Position.Y );
	              spriteBatch.rotate(angleRadians,Position.X + Origin.X,Position.Y + Origin.Y );//+ Origin.Y
	              spriteBatch.drawBitmap(texture2D,Position.X,Position.Y, paint);
	              spriteBatch.restore();
	        	  

	          }
		   

	     }
	  
	   //Java Sprite things 
       private void JavaSprite(Bitmap textture2D, int width, int height)
        {
            texture2D = textture2D;
            columns = texture2D.getWidth() / width;
            rows = texture2D.getHeight() / height;
            if ((columns * width) != texture2D.getWidth()) throw new  IllegalStateException("Invalid width parameter supplied");
            else if ((rows * height) != texture2D.getHeight()) throw new  IllegalStateException("Invalid height parameter supplied");
            currentIndex = 0;
            spriteWidth = width;
            spriteHeight = height;
 	   
 	 
        }
       
       public static Bitmap rotateImage(Bitmap src, float degree) 
       {
               // create new matrix
               Matrix matrix = new Matrix();
               // setup rotation degree
               matrix.postRotate(degree);
               Bitmap bmp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
               return bmp;
       }

       public void Draw(Canvas spriteBatch, int imageIndex, float scale, float angleRadians, Vector2 Position, Vector2 Origin, int flip)
       {
      
        int drawY = (imageIndex / columns);
        int drawX = imageIndex - drawY * columns;

      
	     int spriteX =  drawX * spriteWidth;
	     int spriteY =  drawY * spriteHeight;
	         
          src = new Rect( spriteX, spriteY,spriteX + spriteWidth, spriteY + spriteHeight);
          dst = new Rect(Position.X, Position.Y, Position.X + spriteWidth,Position.Y + spriteHeight);
         

      if(flip == 1)
      { 	   
    	  spriteBatch.save();
          spriteBatch.scale(scaleX, scaleY, Position.X, Position.Y );
          spriteBatch.rotate(angleRadians,Position.X + Origin.X,Position.Y + Origin.Y);//, Origin.X, Origin.Y
          spriteBatch.drawBitmap(texture2D,src,dst, paint);
          spriteBatch.restore();
      }
      else if(flip == 0) 
      {
    	  spriteBatch.save();
          spriteBatch.scale(scaleX, scaleY, Position.X, Position.Y );
          spriteBatch.rotate(angleRadians,Position.X + Origin.X,Position.Y + Origin.Y);//+ Origin.Y
          spriteBatch.drawBitmap(texture2D,src,dst, paint);
          spriteBatch.restore();
	  }
       

        this.SetFrame(imageIndex);

    }

       public void Draw(Canvas spriteBatch, int X, int Y, int imageIndex)
       {
       
        int drawY = (imageIndex / columns);
        int drawX = imageIndex - drawY * columns;
   
	      int spriteX =  drawX * spriteWidth;
	      int spriteY =  drawY * spriteHeight;
	         
           src = new Rect( spriteX, spriteY,spriteX + spriteWidth, spriteY + spriteHeight);
           dst = new Rect(X, Y, X + spriteWidth,Y + spriteHeight);

          spriteBatch.save();
          spriteBatch.scale(scaleX, scaleY, X, Y);
          spriteBatch.drawBitmap(texture2D,src,dst, paint);
          spriteBatch.restore();
      
        this.SetFrame(imageIndex);

    }

       public void Draw(Canvas spriteBatch, int X, int Y, int imageIndex,int flip)
      {
      
        int drawY = (imageIndex / columns);
        int drawX = imageIndex - drawY * columns;

      
	     int spriteX =  drawX * spriteWidth;
	     int spriteY =  drawY * spriteHeight;
	         
          src = new Rect( spriteX, spriteY,spriteX + spriteWidth, spriteY + spriteHeight);
          dst = new Rect(X, Y, X + spriteWidth,Y + spriteHeight);
         
         location.X = X;
         location.Y = Y;
          
          if(flip == 0) 
          {

              spriteBatch.save();
           	  spriteBatch.scale(scaleX, scaleY, X, Y);   
   	          spriteBatch.drawBitmap(texture2D,src,dst, paint);
   	          spriteBatch.restore();
				         
	          //Use simple use this to flip image canvas.scale(-1, 1)
          }
          else if(flip == 1) 
          {
         	 if(texture2Dflip != null)
         	 {
         	  spriteBatch.save();
   	          spriteBatch.scale(scaleX, scaleY, X, Y);
   	          spriteBatch.drawBitmap(texture2Dflip,src,dst, paint);
   	          spriteBatch.restore();
         	 }
         	 else
         	 {
         		 spriteBatch.save();
      	          spriteBatch.scale(scaleX, scaleY, X, Y);
      	          spriteBatch.drawBitmap(texture2D,src,dst, paint);
      	          spriteBatch.restore();
         	 }
         	 
		  }
       
          
        this.SetFrame(imageIndex);
    }
    
       public void SetFrame(int imageIndex)
        {
            this.currentIndex = imageIndex;
        }
         
      //this method returns smthing that can be divided by what you send as mulitpy of
	  public static double round(double num, int multipleOf) 
	  {
	        return Math.floor((num +  (double)multipleOf / 2) / multipleOf) * multipleOf;
	    }

}
