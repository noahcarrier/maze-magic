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

import javax.swing.event.SwingPropertyChangeSupport;


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

            //testPrint(maze, r,c);       // Test print to verify validity of matrix

            
            Coordinate source  = method.findSastry(); // Finding position of Sastry in the maze

            if (source == null)           // If position cannot be found, exit program
            { System.out.println("\n\nError (1): No source position discovered!\n"); System.exit(1); }


            //System.out.println("K position = " + source.pos);       //Reciting position of Sastry
            
            
            int minDist = findTargetBST(source, '$');
            
            

            System.out.println(minDist);
            input.close();
        }


        catch(FileNotFoundException exception) {
            System.out.println("Error (0): File ['in.txt'] Not Found!");
            System.exit(0);
        }
    }
    //====================================================================












    //==============     FIND TARGET METHOD       =============
    private static int findTargetBST(Coordinate source, int target) {

        LinkedList <Coordinate> queue = new LinkedList<>();
        queue.add(source);

        int [] distance = new int [r*c];
        Arrays.fill(distance, -1);
        source.distance = 0;

        int [] xDirs = {1,0,-1,0};
        int [] yDirs = {0,1,0,-1};
        
        //======    Breadth First Search    ======
        while (!queue.isEmpty())
        {
            Coordinate currentSpot = new Coordinate();
            currentSpot = queue.poll();
            
            //System.out.println("\nCurrent Spot\n==============\n" + "\nValue: "+ currentSpot.val+"\nX & Y: [" + currentSpot.x +","+currentSpot.y+"]" +"\nCurrent Position: "+currentSpot.pos+"\n");
            
            for (int i = 0; i < xDirs.length; i++)
            {
                int nextY = currentSpot.pos/c + yDirs[i];
                int nextX = currentSpot.pos%c + xDirs[i];

                if (currentSpot.val == '$')
                    return distance[currentSpot.pos];


                if (nextX < 0 || nextX >= c-1 || nextY < 0 || nextY >= r-1)
                    continue;
				if (maze[nextY][nextX] == '#')
                    continue;
                if (maze[nextY][nextX] >= 95 && maze[nextY][nextX] <= 90)
                    teleport();
				if (distance[nextX*r+nextY] != -1)
                    continue;

                Coordinate nextCoord;
                nextCoord = new Coordinate(nextY, nextX, nextX * r + nextY, maze[nextY][nextX]);

                //System.out.println("New Coord: ["+nextX + "," + nextY+"] Val: "+maze[nextX][nextY]);

                nextCoord.distance = (currentSpot.distance + 1);
                distance[nextX * r + nextY] = nextCoord.distance*2;
                
                queue.offer(nextCoord);
            }
            
        }

        

        return -1;
    }
    //=========================================================

















    private static void teleport() {
    }












    //==============     FIND SOURCE METHOD       =============
    public Coordinate findSastry() {
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (maze[i][j] == '*')
                {
                    return new Coordinate(i, j, i * r + j);
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




//==============     COORDINATE CLASS       =============
class Coordinate {
    int x, y, pos, distance;
    char val;
    Coordinate (int y, int x, int pos, char val) {
        this.x = x;
        this.y = y;
        this.pos = pos;
        this.val = val;
    }
    Coordinate () {
    }
    Coordinate (int y, int x, int pos) {
        this.x = x;
        this.y = y;
        this.pos = pos;
    }
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
