import image.Pixel;
import image.SimplePicture;
import java.awt.Color;

/**
 * Simple class used to represent an image.
 *
 * Internally, it stores the image data as a 2D array of Color objects where
 * each element in the array represents one pixel in the given image object.
 */
public class Image {

    // DO NOT CHANGE
    public static Image dummy = new Image(new Color[][]{{Color.white}});

    // Member variable used to store the pixels of the Image object
    private Color[][] pixels;

    // Member variables used to store the width and height of the Image object
    private int width;
    private int height;

    /**
     * Constructor that creates a new Image object from the image file specified by
     * the given path.
     */
    public Image(String path) {
        // Use SimplePicture to parse file and convert Pixel object
        // to Color object for this Image object
        SimplePicture pic = new SimplePicture(path);
        Pixel[] pixelsToLoad = pic.getPixels();
        this.width = pic.getWidth();
        this.height = pic.getHeight();
        this.pixels = new Color[this.height][this.width];
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Pixel p = pixelsToLoad[row * width + col];
                this.pixels[row][col] = new Color(p.getRed(), p.getGreen(), p.getBlue());
            }
        }
    }

    /**
     * Constructor that creates a new Image object from the given pixels.
     */
    public Image(Color[][] pixelsToLoad) {
        // Make a copy of the pixels array to avoid mutating this Image object
        this.width = pixelsToLoad[0].length;
        this.height = pixelsToLoad.length;
        this.pixels = new Color[this.height][this.width];
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                this.pixels[row][col] = pixelsToLoad[row][col];
            }
        }
    }

    /**
     * Gets the distance between two colors
     */
    public static double colorDistance(Color color1, Color color2) {
        int redDistance = color1.getRed()-color2.getRed();
        int greenDistance = color1.getGreen()-color2.getGreen();
        int blueDistance = color1.getBlue()-color2.getBlue();
        int totalDistance = redDistance * redDistance +
            greenDistance * greenDistance +
            blueDistance * blueDistance;
        return totalDistance / 1000.0;
    }

    /**
     * Visualizes this Image object in an interactive window.
     */
    public void explore() {
        SimplePicture picToExplore = new SimplePicture(this.width, this.height);
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                picToExplore.setBasicPixel(col, row, this.pixels[row][col].getRGB());
            }
        }
        picToExplore.explore();
    }

    /**
     * Returns the width of this Image object.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of this Image object.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns a copy of the pixels of this Image object.
     */
    public Color[][] getPixels2D() {
        Color[][] copy = new Color[this.height][this.width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                copy[row][col] = this.pixels[row][col];
            }
        }
        return copy;
    }

    /**
     * Returns a String representation of this Image object
     */
    @Override
    public String toString() {
        String pixelRef = this.pixels.toString();
        String p = pixelRef.substring(pixelRef.indexOf("@"));
        return "Image[width=" + this.width + ", height=" + this.height + ", pixels=" + p + "]";
    }

    // ------------------------------------------------------------------------
    // TODO: Implement the methods below
    // ------------------------------------------------------------------------

    /**
     * A Canvas Constructor that creates a new Image object
     * that is a canvas of the given color with the given dimensions.
     */

     public Image negative(){
       Color[][] newArray = new Color[this.getHeight()][this.getWidth()];
       for (int row = 0;row < this.getHeight();row++){
         for (int col = 0;col < this.getWidth();col++){
           Color x = this.pixels[row][col];
           int RED = x.getRed();
           int GREEN = x.getGreen();
           int BLUE = x.getBlue();
           newArray[row][col] = new Color(RED,GREEN/4,BLUE);
         }
       }
       return new Image(newArray);
     }


     public Image contrast(){
       Color[][] newArray = new Color[this.getHeight()][this.getWidth()];
       for (int row = 0;row < this.getHeight();row++){
         for (int col = 0;col < this.getWidth();col++){
           Color x = this.pixels[row][col];
           int RED = x.getRed();
           int GREEN = x.getGreen();
           int BLUE = x.getBlue();
           if (RED<127){
           newArray[row][col] = new Color(2*RED,GREEN/2,BLUE/2);
           }else{
             newArray[row][col] = new Color(255,GREEN/4,BLUE/4);
           }
         }
       }
       return new Image(newArray);
     }




    public Image(int widthIn, int heightIn, Color color) {
    this.width = widthIn;
    this.height = heightIn;
    this.pixels = new Color[this.height][this.width];
    for (int row = 0; row < heightIn; row += 1){
      for (int col = 0; col < widthIn; col += 1){
        this.pixels[row][col] = color;
      }
    }
        // TODO: Create constructor method that sets the width, height, and pixels
    }

    public Image crop(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
      int TX = topLeftX;
      int TY = topLeftY;
      int BX = bottomRightX;
      int BY = bottomRightY;
      Color[][] cropped = new Color[BY-TY][BX-TX];
      for (int row = TY; row < BY; row += 1){
        for (int col = TX; col < BX; col += 1){
          cropped[row-TY][col-TX] = this.pixels[row][col];
        }
      }
        // TODO: Implement method, replace return value and delete this comment
        return new Image(cropped);
    }

    public Image overlay(Image bg, int topLeftX, int topLeftY) {
      Color[][] bgPixels = bg.getPixels2D();
      for (int row = topLeftY; row < this.height + topLeftY; row++){
        for (int col = topLeftX; col < this.width + topLeftX; col++){
          bgPixels[row][col] = this.pixels[row - topLeftY][col - topLeftX];
        }
      }
        // TODO: Implement method, replace return value and delete this comment
        return new Image(bgPixels);
    }

    public Image chromakey(Image bg, Color key, double threshold) {

      Color[][] bgPixels = bg.getPixels2D();
      for (int row = 0; row < this.height; row++){
        for (int col =0; col < this.width; col++){
          Color COLOR = this.pixels[row][col];
          if (colorDistance(key, COLOR) < threshold){
            this.pixels[row][col] = bgPixels[row][col];
          }
        }
      }
        // TODO: Implement method, replace return value and delete this comment
        return new Image(this.pixels);
    }

    public Image flipHorizontal() {
      Color[][] FLIP = new Color[this.height][this.width];
      for (int row = 0; row < this.height; row += 1){
        for (int col = 0; col < this.width; col += 1){
          FLIP[row][col] = this.pixels[this.height - row -1][col];
        }
      }
        // TODO: Implement method, replace return value and delete this comment
        return new Image(FLIP);
    }
}
