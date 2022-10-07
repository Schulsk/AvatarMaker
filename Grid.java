


class Grid{
    int width, height, color;
    Node[][] grid;

    public Grid(int sizeX, int sizeY){
        width = sizeX;
        height = sizeY;
        color = 0;
        grid = new Node[sizeY][sizeX];
        for (int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid[y].length; x++){
                grid[y][x] = new Node();
            }
        }
    }


    @Override
    public String toString(){
        String string = "";
        for (int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid[y].length; x++){
                string += grid[y][x] + " ";
            }
            string += "\n";
        }
        return string;
    }

    public void setValue(String string, int x, int y){
        Node node = null;
        try{
            node = grid[y][x];
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("setValue - string:"+ string +", x: "+ x +", y: "+ y);
            System.exit(1);
        }

        node.setString(string);
    }

    public String getValue(int x, int y){
        String string = null;
        try{
            string = grid[y][x].getValue();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("getValue - x: "+ x +", y: "+ y);
            System.exit(1);
        }
        return string;
    }

    public int getWeight(int x, int y){
        if (! validIndex(x, y)){
            System.out.println("Invalid index");
            System.exit(3);
        }
        return grid[y][x].getWeight();
    }

    public void setWeight(int value, int x, int y){
        if (! validIndex(x, y)){
            System.out.println("Invalid index");
            System.exit(3);
        }
        grid[y][x].setWeight(value);
    }

    public void increaseWeight(int amount, int x, int y){
        grid[y][x].increaseWeight(amount);
    }

    public void countValue(String value){
        for (int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid[y].length; x++){
                if (grid[y][x].getValue().equals(value)){
                    grid[y][x].increaseWeight(1);
                }
            }
        }
    }

    public Node getNode(int x, int y){
        return grid[y][x];
    }

    public void setNode(Node node, int x, int y){
        grid[y][x] = node;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }

    private boolean validIndex(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height){
            return false;
        }
        return true;
    }

    public static Grid makeAlphabetGrid(){
        Grid alphabetGrid = new Grid(5, 6);
        alphabetGrid.setValue("q", 0, 0);
        alphabetGrid.setValue("w", 1, 0);
        alphabetGrid.setValue("å", 2, 0);
        alphabetGrid.setValue("m", 3, 0);
        alphabetGrid.setValue("l", 4, 0);
        alphabetGrid.setValue("z", 0, 1);
        alphabetGrid.setValue("j", 1, 1);
        alphabetGrid.setValue("h", 2, 1);
        alphabetGrid.setValue("s", 3, 1);
        alphabetGrid.setValue("t", 4, 1);
        alphabetGrid.setValue("y", 0, 2);
        alphabetGrid.setValue("p", 1, 2);
        alphabetGrid.setValue("g", 2, 2);
        alphabetGrid.setValue("a", 3, 2);
        alphabetGrid.setValue("e", 4, 2);
        alphabetGrid.setValue("c", 0, 3);
        alphabetGrid.setValue("f", 1, 3);
        alphabetGrid.setValue("k", 2, 3);
        alphabetGrid.setValue("i", 3, 3);
        alphabetGrid.setValue("n", 4, 3);
        alphabetGrid.setValue("x", 0, 4);
        alphabetGrid.setValue("ø", 1, 4);
        alphabetGrid.setValue("u", 2, 4);
        alphabetGrid.setValue("d", 3, 4);
        alphabetGrid.setValue("r", 4, 4);
        alphabetGrid.setValue(" ", 0, 5);
        alphabetGrid.setValue("æ", 1, 5);
        alphabetGrid.setValue("b", 2, 5);
        alphabetGrid.setValue("v", 3, 5);
        alphabetGrid.setValue("o", 4, 5);

        return alphabetGrid;
    }

    public static Grid flipGrid(Grid grid){
        int width = grid.getWidth();
        int height = grid.getHeight();
        Grid newGrid = new Grid(width, height);
        // System.out.println("width: " + width + " height: " + height);

        for (int y = 0; y < height; y++){
            // System.out.println("y: " + y);
            for (int x = 0; x < width; x++){
                // System.out.println("x: " + x);
                newGrid.setNode(grid.getNode(x, y), width -1 - x, y);
            }
        }
        return newGrid;
    }

    public static Grid mirrorGrid(Grid grid){
        Grid flipGrid = Grid.flipGrid(grid);
        int width = grid.getWidth() * 2;
        int height = grid.getHeight();

        Grid newGrid = new Grid(width, height +4);
        newGrid.setColor(grid.getColor());

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                int yMod = y + 2;
                if (x < width / 2){
                    newGrid.setNode(grid.getNode(x, y), x, yMod);
                }
                else{
                    newGrid.setNode(flipGrid.getNode(x - (width/2), y), x, yMod);
                }
            }
        }
        return newGrid;
    }

    public static Grid makeBorder(Grid grid, int sizeX, int sizeY){
        // Do direction too
        Grid newGrid = new Grid(sizeX, sizeY);
        newGrid.setColor(grid.getColor());
        int width = newGrid.getWidth();
        int height = newGrid.getHeight();
        int counterX = 0;
        int counterY = 0;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (counterX >= grid.getWidth()){
                    counterX = 0;
                }
                if (counterY >= grid.getHeight()){
                    counterY = 0;
                }
                // newGrid.setWeight(grid.getWeight(counterX, counterY), x, y);
                newGrid.setNode(grid.getNode(counterX, counterY), x, y);
                counterX++;
            }
            counterY++;
        }
        return newGrid;
    }

}
