
import java.util.Stack;

class Hasher{
    // public static final char[] SEED = {'4', 'e', '1', '1', '0',
    //                                     '0', '7', '0', '0', '0',
    //                                     '1', '1', '7', '7', '1',
    //                                     'e', '0', 'f', '0', 'c',
    //                                     'c', 'e', '8', '1'};
    public static final String SEED = "4e1100700011771e0f0cce81751929561293566283846321234012347819230af";
    public static final int ITERATIONS = 20;
    private char[] seed;

    public Hasher(String string){
        seed = reverse(stringToChar(string));
    }

    public static String hash(String string, int outputLength){
        // outputLength is the number of hexadecimal digits the method outputs
        String newString = addPostfix(string, outputLength);
        int stringLength = newString.length();
        // convert string to hex value
        String hex = stringToHexString(newString);
        System.out.println("hex: " + hex);
        // Convert the string to char array
        char[] array = stringToChar(newString);

        // Do the iterations
        String hash = hex;
        for (int i = 0; i < ITERATIONS; i++){
            hash = bucketTranspose(hash, 3);
            hash = substitution(hash, outputLength, 1);
            // hash = substitution(hash, outputLength, 3);
            hash = bucketTranspose(hash, 5);
            hash = substitution(hash, outputLength, 2);
            // hash = substitution(hash, outputLength, 5);
            hash = bucketTranspose(hash, 7);
            hash = substitution(hash, outputLength, 1);
            // hash = substitution(hash, outputLength, 7);
        }

        return hash;
    }

    public static String stringToHexString(String string){
        String hex = "";
        for (int i = 0; i < string.length(); i++){
            // hex += charToHex(string.charAt(i));
            hex += Integer.toHexString(string.charAt(i));
        }
        return hex;
    }

    private static String addPostfix(String string, int outputLength){
        int stringLength = string.length();
        String postfix = Integer.toString(stringLength);
        int modulus = (string + postfix).length() % outputLength;
        for (int i = 0; i < outputLength - modulus; i++){
            postfix = "0" + postfix;
        }
        return string + postfix;
    }

    private static String bucketTranspose(String string, int bucketNumber){
        // Make the buckets
        Stack<Character>[] buckets = new Stack[bucketNumber];
        for (int i = 0; i < bucketNumber; i++){
            buckets[i] = new Stack<Character>();
        }

        // Put chars into buckets
        int stringIndex = 0;
        int bucketIndex = 0;
        int stringLength = string.length();
        while (stringIndex < stringLength){
            if (bucketIndex >= bucketNumber){
                bucketIndex = 0;
            }
            buckets[bucketIndex].push(string.charAt(stringIndex));
            bucketIndex++;
            stringIndex++;
        }

        // Pick the chars from the buckets
        String newString ="";
        for (int i = 0; i < bucketNumber; i++){
            while (!buckets[i].empty()){
                newString += Character.toString(buckets[i].pop());
            }
        }

        return newString;
    }

    private static char[] bucketTranspose(char[] string, int bucketNumber){
        // Make the buckets
        Stack<Character>[] buckets = new Stack[bucketNumber];
        for (int i = 0; i < bucketNumber; i++){
            buckets[i] = new Stack<Character>();
        }

        // Put chars into buckets
        int stringIndex = 0;
        int bucketIndex = 0;
        int stringLength = string.length;
        while (stringIndex < stringLength){
            if (bucketIndex >= bucketNumber){
                bucketIndex = 0;
            }
            buckets[bucketIndex].push(string[stringIndex]);
            bucketIndex++;
            stringIndex++;
        }

        // Pick the chars from the buckets
        char[]newString = new char[stringLength];
        int j = 0;
        for (int i = 0; i < bucketNumber; i++){
            while (!buckets[i].empty()){
                newString[j] += buckets[i].pop();
                j++;
            }
        }

        return newString;
    }

    public static String substitution(String string, int outputLength, int multiplier){
        // Takes a char array of hex values and substitutes them, shortening the
        // sequence if it is longer than the given outputLength

        // initialize starting seed
        String newString = "";
        for (int i = 0; i < outputLength; i++){
            newString += Character.toString(SEED.charAt(i % outputLength));
        }

        // Do loop
        int i = 0;
        int j = 0;
        while (i < string.length()){
            // reset counter if it exceeds the max value
            if (j >= outputLength){
                j = 0;
            }
            // set value to combination, if value is max set value to min plus remainder
            int x = Integer.parseInt(newString.subSequence(j, j+1).toString(), 16) + (Integer.parseInt(string.subSequence(i, i+1).toString(), 16) * multiplier);
            int max = 15;
            while (x > max){
                x -= max;
            }
            char[] temp = newString.toCharArray();
            temp[j] = Integer.toHexString(x).charAt(0);
            i++;
            j++;
            newString = String.copyValueOf(temp);
        }
        return newString;
    }

    // public static char[] substitution(char[] array, int outputLength){
    //     // Takes a char array of hex values and substitutes them, shortening the
    //     // sequence if it is longer than the given outputLength
    //
    //     // initialize starting seed
    //     char[] temp = new char[outputLength];
    //     for (int i = 0; i < temp.length && i < SEED.length; i++){
    //         temp[i] = SEED[i];
    //     }
    //
    //     // Do loop
    //     int i = 0;
    //     int j = 0;
    //     while (i < array.length){
    //         if (j >= outputLength){
    //             j = 0;
    //         }
    //         // set value to combination, if value is max set value to min plus remainder
    //         int x = Integer.parseInt(Character.toString(temp[j]), 16) + Integer.parseInt(Character.toString(array[i]), 16);
    //         // int x = temp[j] + array[i];
    //         int max = 15;
    //         if (x > max){
    //             x -= max;
    //         }
    //         temp[j] = Integer.toHexString(x).charAt(0);
    //         i++;
    //         j++;
    //     }
    //     return temp;
    // }

    public static char[] reverse(char[] array){
        int length = array.length;
        char[] newArray = new char[length];
        for (int i = 0; i < length; i++){
            newArray[i] = array[length-1 - i];
        }
        return newArray;
    }

    public static char[] stringToChar(String string){
        // This method converts a string to an array of chars
        int length = string.length();
        char[] array = new char[length];
        for (int i = 0; i < length; i++){
            array[i] = string.charAt(i);
        }
        return array;
    }

    public static String charToString(char[] array){
        int length = array.length;
        String string = "";
        for (int i = 0; i < length; i++){
            string += array[i];
        }
        return string;
    }

    public static String charToHex(char[] array){
        String hex = "";
        for (int i = 0; i < array.length; i++){
            hex += Integer.toHexString((int) array[i]);
        }
        return hex;
    }
}

/**
Possibly useful methods in java

    String.copyValueOf(char[] data) - static String
        Returns a String that represents the character sequence in the array specified.

    String.toCharArray() - char[]
        Converts this string to a new character array.

    String.subSequence(int beginIndex, int endIndex)
        Returns a new character sequence that is a subsequence of this sequence.

    String.substring(int beginIndex, int endIndex)
        Returns String

    Integer.parseInt(String s, int radix) - static int
        Parses the string argument as a signed integer in the radix specified by the second argument.
*/
