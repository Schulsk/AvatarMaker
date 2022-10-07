
import java.util.Scanner;
class Test{
    public static void main(String[] args){
        String inp = " ";
        Scanner scanner = new Scanner(System.in);

        while (!inp.equals("")){
            inp = scanner.nextLine();
            if (inp.length() > 0){
                System.out.println(Hasher.hash(inp, 8));
            }
        }
    }
}
