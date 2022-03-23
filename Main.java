/*
 *
 *  COP 3503C Assignment 4
 *  This program is written by: Noah Carrier
 *
 */ 

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


// Main Class
public class Main {


    //===========   GLOBAL VARIABLES    ===========
    static int r, c;
    static char [][] maze;
    //=============================================






    //====================     MAIN METHOD       =======================
    public static void main(String[] args) {
        

        try {

            // Initalizing input file
            File fp = new File("in.txt");
            Scanner input = new Scanner(fp);
            Main method = new Main();
            // Reading input file

            r = input.nextInt();    // Number of rows
            c = input.nextInt();    // Number of columns

            maze = new char[r][];  // Initialize maze size

            for (int i = 0; i < r; i++)     //Reading in maze data
                maze[i] = input.next().toCharArray();

            testPrint(maze, r,c);       // Test print to verify validity of matrix

            
            Coordinate source  = method.findSastry(); // Finding position of Sastry in the maze

            if (source == null)           // If position cannot be found, exit program
            { System.out.println("\n\nError (1): No source position discovered!\n"); System.exit(1); }


            System.out.println("K position = " + source.pos);       //Reciting position of Sastry
            
            
            int minDist = findTargetBST(source);
            
            if (minDist == -1)           // If position cannot be found, exit program
            { System.out.println("\n\nError (2): No target position discovered!\n"); System.exit(2); }

            System.out.println("Minimum distance = " + minDist);
            input.close();
        }


        catch(FileNotFoundException exception) {
            System.out.println("Error (0): File ['in.txt'] Not Found!");
            System.exit(0);
        }
    }
    //====================================================================












    //==============     FIND TARGET METHOD       =============
    private static int findTargetBST(Coordinate source) {
        
        int distance = 0;
        int [] xDirs = {1,0,-1,0};
        int [] yDirs = {0,1,0,-1};
        
        LinkedList <Character> queue = new LinkedList<>();
        queue.add(source.val);
        //======    Breadth First Search    ======
        while (!queue.isEmpty())
        {

            for (int i = 0 ; i < queue.size(); i++)
            {
                Coordinate currentNode = source;
                currentNode.setVal(queue.poll());

                if (currentNode.val == '$')
                    return distance;
                for (int j = 0; j < yDirs.length; j++)
                {
                    int nextX = currentNode.x + xDirs[j];
                    int nextY = currentNode.y + yDirs[j];



                    if (xDirs[j] != 0)  nextY = 0;
                    if (yDirs[j] != 0)  nextX = 0;
                    
                    
                    
                    // Skipping out of bounds indexes or forbidden space
                    if ( (nextX < 0 || nextX >= c-1 || nextY < 0 || nextY >= r-1) || (maze[nextY][nextX] == '!') )
                        continue;
                    System.out.println("X: " + nextX + "\tY: "+nextY);

                    currentNode.pos = nextY * r + nextX;    // Convert from 2D to linear index

                    queue.add(maze[currentNode.y][currentNode.x]);   // Add the neighbors into the queue
                }
            
            }

            distance++;

        }



        return -1;
    }
    //=========================================================














    //==============     COORDINATE METHOD       =============
    class Coordinate {
        int x, y, pos;
        private char val;
        Coordinate (int y, int x, int pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
        Coordinate () {        }

        public void setPos(int pos) {
            this.pos = pos;
        }
        public int getPos() {
            return pos;
        }
        public char getVal() {
            return val;
        }
        public void setVal(char val) {
            this.val = val;
        }
    }
    //========================================================















    //==============     FIND SOURCE METHOD       =============
    public Coordinate findSastry() {
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (maze[i][j] == '*')
                {
                    return new Coordinate(r, c, i * r + j);
                }
            }
        }
        return null;
    }
    //=========================================================











    //==============     TEST PRINT METHOD       =============
    public static void testPrint (char [][] maze, int r, int c) {
        System.out.println("\n\n"+r + " Rows and " + c + " Columns\n");
        for (int i = 0 ; i < r; i++)
            System.out.println(Arrays.toString(maze[i]));
    }
    //========================================================
}
