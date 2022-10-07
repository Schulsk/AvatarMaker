// import java.System.out.println as println;
import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Grid grid = new Grid(2, 5);

        System.out.println(grid);

        grid.setValue("Hello", 0, 0);
        System.out.println(grid);

        Grid newGrid = StringAnalyzer.analyzeString("Julian");
        System.out.println(newGrid);
        StringAnalyzer.writeToPNG(newGrid, "test.png");



        Scanner scanner = null;
        try{
            scanner = new Scanner(System.in);
        }
        catch (Exception e){
            System.exit(2);
        }

        String inp = " ";
        while (!inp.equals("")){
            inp = scanner.nextLine();

            if (! inp.equals("")){
                grid = StringAnalyzer.analyzeString(inp);
                grid = Grid.mirrorGrid(grid);
                System.out.println(grid);
                StringAnalyzer.writeToPNG(grid, "test.png");
                Grid border = Grid.makeBorder(grid, 300, 10);
                StringAnalyzer.writeToPNG(border, "border.png");
                grid = Grid.mirrorGrid(grid);
                grid = Grid.mirrorGrid(grid);
                // System.out.println(grid);
                StringAnalyzer.writeToPNG(grid, "test2.png");
            }
        }
    }
}






// helt rett
