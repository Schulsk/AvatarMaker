
import java.util.Scanner;
class Test{
    public static void main(String[] args){
        String inp = " ";
        Scanner scanner = new Scanner(System.in);
        FaceMaker faceMaker = new FaceMaker(FaceMaker.IMAGE_SET_TEST01);

        while (!inp.equals("")){
            inp = scanner.nextLine();
            if (inp.length() > 0){
                String hash = Hasher.hash(inp, 8);
                System.out.println("Hash: " + hash);
                faceMaker.makeImage(hash);
            }
        }

    }
}
