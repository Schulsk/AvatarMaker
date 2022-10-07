



class Color{
    public Color(){

    }

    public static int makeRGB(double r, double g, double b){
        // Make the new values
        int newR = convertDoubleToRGBValue(r);
        int newG = convertDoubleToRGBValue(g);
        int newB = convertDoubleToRGBValue(b);
        // Assemble the new values
        int rgb = newR;
        rgb = (rgb << 8) + newG;
        rgb = (rgb << 8) + newB;
        return rgb;
    }

    public static int convertDoubleToRGBValue(double value){
        int max = 255;
        int rgbValue = (int) (value * max);
        return rgbValue;
    }

    public static int calculateColor(String string){
        int r = 0;
        int g = 0;
        int b = 0;
        String [] alphabet = StringAnalyzer.FREQUENCY_ALPHABET;
        int subGroupLength = StringAnalyzer.LETTER_VALUE_GROUP01.length;

        for (int i = 0; i < string.length(); i ++){
            String character = Character.toString(string.charAt(i));
            for (int j = 0; j < alphabet.length; j++){

                if (character.equals(alphabet[j])){
                    for (int k = 0; k < subGroupLength; k++){
                        String group01 = StringAnalyzer.LETTER_VALUE_GROUP01[k];
                        String group02 = StringAnalyzer.LETTER_VALUE_GROUP02[k];
                        String group03 = StringAnalyzer.LETTER_VALUE_GROUP03[k];
                        if (character.equals(group01)){
                            r += k;
                        }
                        else if (character.equals(group02)){
                            g += k;
                        }
                        else if(character.equals(group03)){
                            b += k;
                        }
                    }
                }
            }
        }

        double max = Math.max((double)r, (double)g);
        max = Math.max((double)max, (double)b);
        System.out.println("r: " + r + " g: " + g + " b: " + b);
        if (max == 0){
            return 0;
        }
        System.out.println("max: " + max + " r/max: " + (double) r/max + " g/max: " + (double) g/max + " b/max: " + (double)b/max);
        int color = Color.makeRGB((double)r/max, (double)g/max, (double)b/max);
        System.out.println("color: " + color);
        return color;
    }
}
