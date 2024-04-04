import java.awt.Color;

public class PA7 {

    // ------------------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------------------

    public static void testCanvas() {
        // Create a blue canvas with width 5 and height 10
        Image canvas1 = new Image(5, 10, Color.blue);

        // Verify the result is a 10x5 blue canvas
        canvas1.explore();

        // TODO: Add two more test cases

        Image canvas2 = new Image(1000,1000,Color.blue);
        canvas2.explore();
    }

    public static void testCrop() {
        // Create a 4x4 2D array
        Color[][] test1 = { { Color.black, Color.black, Color.red, Color.red }, // row0
                { Color.black, Color.black, Color.red, Color.red }, // row1
                { Color.black, Color.black, Color.red, Color.red }, // row2
                { Color.black, Color.black, Color.black, Color.black } // row3
        };

        // Create an image from the array and crop the bottom right corner
        Image img1 = new Image(test1);
        Image cropped1 = img1.crop(2, 0, 4, 3);

        // Visualize the cropped picture
        cropped1.explore();

        Image img2 = new Image("images/pixel-heart.png");
        Image cropped2 = img2.crop(100, 100, 500, 500);

        cropped2.explore();

        // TODO: Add two more test cases


    }

    public static void testOverlay() {
        // Create a 4x4 2D array
        Color[][] bgTest1 = {
            { Color.black, Color.black, Color.black, Color.black }, // row0
            { Color.black, Color.black, Color.black, Color.black }, // row1
            { Color.black, Color.black, Color.black, Color.black }, // row2
            { Color.black, Color.black, Color.black, Color.black } // row3
        };

        // Create a 3x2 2D array
        Color[][] fgTest1 = { { Color.red, Color.red }, { Color.red, Color.red }, { Color.red, Color.red } };

        // Create an image from the array and crop the bottom right corner
        Image bgImg1 = new Image(bgTest1);
        Image fgImg1 = new Image(fgTest1);
        Image overlayed1 = fgImg1.overlay(bgImg1, 1, 1);

        // Visualize the cropped picture
        overlayed1.explore();

        // TODO: Add two more test cases

        Image bgImg2 = new Image("images/pixel-heart.png");
        Image fgImg2 = new Image("images/grace-hopper.png");
        Image overlayed2 = fgImg2.overlay(bgImg2, 222, 222);
        overlayed2.explore();

    }

    public static void testChromakey() {
        // Create a 4x4 2D array
        Color[][] bgTest1 = {
            { Color.red, Color.red, Color.red, Color.red }, // row0
            { Color.red, Color.red, Color.red, Color.red }, // row1
            { Color.red, Color.red, Color.red, Color.red }, // row2
            { Color.red, Color.red, Color.red, Color.red } // row3
        };

        // Create a 3x2 2D array
        Color[][] fgTest1 = {
            { Color.green, Color.green, Color.black, Color.black }, // row0
            { Color.green, Color.green, Color.black, Color.black }, // row1
            { Color.green, Color.green, Color.black, Color.black }, // row2
            { Color.green, Color.green, Color.black, Color.black } // row3
        };

        // Create an image from the array and crop the bottom right corner
        Image bgImg1 = new Image(bgTest1);
        Image fgImg1 = new Image(fgTest1);
        Image chromakeyed1 = fgImg1.chromakey(bgImg1, Color.green, 1);

        // Visualize the cropped picture
        //chromakeyed1.explore();

        // TODO: Add two more test cases

        Image bgImg2 = new Image("images/yasuo.jpg");
        Image fgImg2 = new Image("images/fire.jpg");
        Image chromakeyed2 = fgImg2.chromakey(bgImg2, Color.black, 1);
        chromakeyed2.explore();
    }

    public static void testFlipHorizontal() {
        // Create 4x4 2D array
        Color[][] test1 = { { Color.black, Color.black, Color.black, Color.black }, // row0
                { Color.black, Color.black, Color.black, Color.black }, // row1
                { Color.red, Color.red, Color.red, Color.red }, // row2
                { Color.red, Color.red, Color.red, Color.red } // row3
        };

        // First visualize the original image
        Image img1 = new Image(test1);
        //img1.explore();

        // Flip the image and visualize the result
        Image flippedImg1 = img1.flipHorizontal();
        //flippedImg1.explore();

        // TODO: Add two more test cases

        Image img2 = new Image("images/pixel-heart.png");
        Image flippedImg2 = img2.flipHorizontal();
        flippedImg2.explore();

        Image img3 = new Image("images/yasuo.jpg");
        Image flippedImg3 = img3.flipHorizontal();
        flippedImg3.explore();
    }

    // ------------------------------------------------------------------------
    // Main Method
    // ------------------------------------------------------------------------
public static void newimg(){
    Image bgImg = new Image("images/yasuo.jpg");
    Image bgImg0 = bgImg.negative();

    //Image canvas = new Image(1440, 1080, Color.black);

    // Image fgImg0 = new Image("images/fire1.jpg");
    // Image overlayed0 = fgImg0.overlay(canvas, 100, 0);
    // Image chromakeyed0 = overlayed0.chromakey(bgImg0, Color.black, 30);
    //
    // Image fgImg1 = new Image("images/thunder4.jpg");
    // Image overlayed1 = fgImg1.overlay(canvas, 650, 10);
    // Image chromakeyed1 = overlayed1.chromakey(chromakeyed0, Color.black, 25);
    //
    // Image caption = new Image("images/captions1.jpg");
    // Image capConvert = caption.negative();
    // Image capCrop = capConvert.crop(100,0,1100,156);
    // Image overlayed2 = capCrop.overlay(canvas, 40, 480);
    //Image chromakeyed2 = bgImg0.chromakey(chromakeyed1, Color.black, 1);


    bgImg0.explore();
}
    public static void main(String[] args) {
        // You may want to uncomment one test at a time
        // NOTE: testCanvas will error unless the canvas constructor is implemented
        // please implement the canvas constructor before uncommenting that line.

        newimg();
        // testCanvas();
         //testCrop();
        // testOverlay();
         //testChromakey();
         //testFlipHorizontal();

        // TODO: Add code for Part 2 here
    }
}
