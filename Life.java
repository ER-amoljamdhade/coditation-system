import java.io.*;


public class Life  {

    int width;
    int height;
    int[][] grid;
    public Life(){
        
    }    
    public Life(int width, int height) {
       
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
    }

    public void printGrid() {
        System.out.println("---");
        for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.grid[x][y] == 0) {
                    line += ".";
                } else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }

    public void setAlive(int x, int y) {
        this.grid[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.grid[x][y] = 0;
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }

        if (y < 0 || y >= height) {
            return 0;
        }

        return this.grid[x][y];
    }

    public void step() {
        int[][] newGrid = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x, y);

                if (getState(x, y) == 1) {
                    if (aliveNeighbours < 2) {
                        newGrid[x][y] = 0;
                    } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newGrid[x][y] = 1;
                    } else if (aliveNeighbours > 3) {
                        newGrid[x][y] = 0;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        newGrid[x][y] = 1;
                    }
                }

            }
        }

        this.grid = newGrid;
    }

    public static void main(String[] args) throws IOException{
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter number of points:");
        int n = Integer.parseInt(br.readLine());  

        System.out.println("Enter length of grid size:");
        int a = Integer.parseInt(br.readLine());
   
        System.out.println("Enter breadth of grid size:");
        int b = Integer.parseInt(br.readLine());
   
        Life life = new Life(a, b);     
        for(int i =0; i<n;i++){
            System.out.println("Enter point"+(i+1)+":");
            int x = Integer.parseInt(br.readLine());
            int y = Integer.parseInt(br.readLine());
            life.setAlive(x, y); 
        }
        
        while(true){
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("1. Enter a new alive cell\n2. Print Next Generation\n3. Print Current Generation\n4. Exit");
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            
            int ch = Integer.parseInt(br.readLine());
            switch(ch){
                case 1: 
                System.out.println("Enter point: ");
                int x = Integer.parseInt(br.readLine());
                int y = Integer.parseInt(br.readLine());
               
                life.setAlive(x, y); 
                life.printGrid();
                break; //setting new alive from user
                
                case 2: life.step(); life.printGrid(); break;//next gen and print
               
                case 3: life.printGrid(); break; //print
               
                case 4: System.exit(0); break;
                default: System.out.println("Enter valid choice"); break;
            }
        }
    }

}
