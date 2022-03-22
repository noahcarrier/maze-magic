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
import java.util.Scanner;


// Main Class
public class Main
{
    //===========   GLOBAL VARIABLES    ===========
    static int r, c;
    static char [][] maze;

    //=============================================






    //====================     MAIN METHOD       =======================
    public static void main(String[] args)
    {
        

        try
        {
            // Initalizing input file
            File fp = new File("in.txt");
            Scanner input = new Scanner(fp);

            // Reading input file

            r = input.nextInt();    // Number of rows
            c = input.nextInt();    // Number of columns

            maze = new char[r][];  // Initialize maze size

            for (int i = 0; i < r; i++)     //Reading in maze data
                maze[i] = input.next().toCharArray();

            testPrint(maze, r,c);


            int source  = findSastry();
            System.out.println("K position = " + source);
            //int minDist = findTargetBST(source, 0);
            
            input.close();
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Error (0): File ['in.txt'] Not Found!");
        }
    }
    //====================================================================












    //==============     FIND TARGET METHOD       =============
    private static int findTargetBST(int source, int i) {
        return 0;
    }
    //=========================================================














    //==============     COORDINATE METHOD       =============
    class Coordinate {
        int x, y;
        Coordinate (int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
    //========================================================















    //==============     FIND SOURCE METHOD       =============
    private static int findSastry() {

        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (maze[i][j] == '*')
                    return i * r + c;
            }
        }
        System.out.println("\n\nError (1): No source position discovered!\n");
        System.exit(-1);
        return -1;
    }
    //=========================================================











    //==============     TEST PRINT METHOD       =============
    public static void testPrint (char [][] maze, int r, int c)    {
        System.out.println("\n\n"+r + " Rows and " + c + " Columns\n");
        for (int i = 0 ; i < r; i++)
            System.out.println(Arrays.toString(maze[i]));
    }
    //========================================================
}
