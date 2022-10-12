
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;


class FaceMaker{
    // Image Sets are the predefined filepaths of the sets of images that can be used
    public static final String IMAGE_SET_TEST01 = "image_set_test01/";

    private String chosenImageSet;

    private File chosenHead;
    private File chosenEyes;
    private File chosenMouth;
    private File chosenNose;
    private File chosenHat;

    public FaceMaker(String imageSet){
        chooseImageSet(imageSet);
    }

    public void chooseImageSet(String imageSet){
        chosenImageSet = imageSet;
    }

    public void makeImage(String seed){
        // Seed is a string of hexadecimal digits at least 5 digits long
        chosenHead = setPart(seed.substring(0, 1), "head");
        chosenEyes = setPart(seed.substring(1, 2), "eyes");
        chosenNose = setPart(seed.substring(2, 3), "nose");
        chosenMouth = setPart(seed.substring(3, 4), "mouth");
        chosenHat = setPart(seed.substring(4, 5), "hat");
        System.out.println(chosenHead);
        System.out.println(chosenEyes);
        System.out.println(chosenNose);
        System.out.println(chosenMouth);
        System.out.println(chosenHat);

        // Read
        BufferedImage head = null, eyes = null, nose = null, mouth = null, hat = null;
        try{
            head = ImageIO.read(chosenHead);
            eyes = ImageIO.read(chosenEyes);
            nose = ImageIO.read(chosenNose);
            mouth = ImageIO.read(chosenMouth);
            hat = ImageIO.read(chosenHat);
        }
        catch (Exception e ){
            System.out.println(e);
            System.exit(-1);
        }
        // Assemble

        BufferedImage image = head;
        Graphics graphics = image.getGraphics();
        graphics.drawImage(mouth, 0, 0, null);
        graphics.drawImage(eyes, 0, 0, null);
        graphics.drawImage(nose, 0, 0, null);
        graphics.drawImage(hat, 0, 0, null);
        graphics.dispose();

        // Save
        try{
            ImageIO.write(image, "png", new File("face.png"));
        }
        catch(Exception e){
            System.out.println("An error occured while trying to write to the image file");
            System.exit(1);
        }
    }

    public File setPart(String hex, String part){
        int max = (int) Math.pow(16, hex.length());
        int value = Integer.parseInt(hex, 16);
        File file = new File(chosenImageSet + part);
        File[] variations = file.listFiles();
        // Choose the index
        int numberOfVariations = variations.length;
        int index = getIndex(value, max, numberOfVariations);
        // Return result
        System.out.println("hex: " + hex);
        System.out.println("max: " + max);
        System.out.println("value: " + value);
        System.out.println("numberOfVariations: " + numberOfVariations);
        System.out.println("index: " + index);
        System.out.println("Getting variation");
        return variations[index];
    }

    public int getIndex(int value, int max, int numberOfVariations){
        System.out.println(max + "/" + numberOfVariations + " = " + max/numberOfVariations);
        System.out.println("Math.ceil(" + max + "/" + numberOfVariations + ") = " + Math.ceil(max/numberOfVariations));
        return (int) (value / Math.ceil((double)max / numberOfVariations));
    }
}
