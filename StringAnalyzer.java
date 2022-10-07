
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

class StringAnalyzer{
    public static final int BLACK = 0;
    public static final int WHITE = Color.makeRGB(1.0, 1.0, 1.0);

    public static final String[] FREQUENCY_ALPHABET = {"e", "n", "t", "r", "a", "i",
                                                     "s", "d", "l", "o", "g", "k",
                                                     "m", "v", "h", "u", "p", "f",
                                                     "å", "b", "j", "ø", "y", "c",
                                                     "w", "æ", "z", "x", "q"};

    public static final String[] LETTER_VALUE_GROUP01 = {"e", "r", "s", "o", "m", "dummy", "z", "c", "j", "f"};
    public static final String[] LETTER_VALUE_GROUP02 = {"n", "a", "d", "g", "v", "q", "æ", "y", "b", "p"};
    public static final String[] LETTER_VALUE_GROUP03 = {"t", "i", "l", "k", "h", "x", "w", "ø", "å", "u"};

    public static final int MIN_SQUARES = 8;
    public static final int MAX_SQUARES = 16;

    public StringAnalyzer(){

    }

    public static Grid analyzeString(String string){
        Grid grid = Grid.makeAlphabetGrid();
        grid.setColor(Color.calculateColor(string));
        for (int i = 0; i < string.length(); i++){
            String letter = Character.toString(string.charAt(i)).toLowerCase();
            grid.countValue(letter);
        }

        return grid;
    }

    public static void writeToPNG(Grid grid, String pathname){
        BufferedImage image = transferPixelsFromGridToBufferedImage(grid);
        File file = new File(pathname);

        try{
            ImageIO.write(image, "png", file);
        }
        catch(Exception e){
            System.out.println("An error occured while trying to write to the image file");
            System.exit(1);
        }
    }

    public static BufferedImage transferPixelsFromGridToBufferedImage(Grid grid){
        int width = grid.getWidth();
        int height = grid.getHeight();
        int color = grid.getColor();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                int rgb = BLACK;
                if (grid.getWeight(x, y) == 0){
                    rgb = color;
                }
                image.setRGB(x, y, rgb);
            }
        }

        return image;
    }

}
