package excelian.maze;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {


    private String[][] maze;
    public int length;
    public int height;
    public int startRow;
    public int startColumn;
    public int finishRow;
    public int finishColumn;

    public Maze(){}

    public Maze(String[][] maze)
    {
        this.maze = maze;
    }

    public String[][] getMaze() {
        return maze;
    }

    /**
     * Assumptions: maze is perfectly quadrilateral. May need to write a check later
     *
     * @param filename
     * @return true for successful creation of maze
     * @throws InvalidMazeException
     */
    public boolean makeMazeFromTextfile(String filename) throws InvalidMazeException
    {

        List<String> rows = new ArrayList<>();
        try
        {
        /*
         * Note: I first tried to parse file character by character, check if its a valid maze construct then add into a list
         * temporarily so we can initialize a 2d array. However this proved to take too long to implement. Then I found this
         * beautiful JDK 7 method...
         */
            rows = Files.readAllLines(Paths.get(filename), Charset.forName("UTF-8"));

        }
        catch (IOException ie)
        {
            ie.printStackTrace();
        }

        //then turn it into a 2d array. Will error if maze is not quadrilateral
        length = rows.get(0).length();
        height = rows.size();
        maze = new String[length][height];
        int i = 0;
        for (String row : rows)
        {
            if (row.matches("[XSF ]*"))
            {
                if (row.contains("S"))
                {
                    startColumn = i;
                    startRow = row.indexOf("S");
                }
                if (row.contains("F"))
                {
                    finishColumn = i;
                    finishRow = row.indexOf("F");
                }

                maze[i] = row.split("(?!^)");
                //Note: row.split("") gives an empty first value, which breaks our code. Java 8 fixes this.
            }
            else throw new InvalidMazeException("Maze contains invalid characters on index " + i);

            //also check if maze is a valid quadrilateral
            if (row.length() != length) throw new InvalidMazeException("Maze is not a valid quadrilateral");
            i++;
        }
        return true;
    }

    public int getSpaces()
    {
        int spaces = 0;
        for (int i=0;i<height;i++)
        {
            spaces += findCharacterOccurencesInline(" ",maze[i]);
        }
        return spaces;

    }

    public int getWalls()
    {
        int walls = 0;
        for (int i=0;i<height;i++)
        {
            walls += findCharacterOccurencesInline("X",maze[i]);
        }
        return walls;

    }

    private int findCharacterOccurencesInline(String character, String[] array)
    {
        int occurrences = 0;
        for (int i =0; i<array.length;i++)
        {
            if (character.equals(array[i]))
                occurrences++;
        }

        return occurrences;
    }

    public String getObjectAtLocation(int x, int y)
    {
        return maze[x][y];

    }

    public String getObjectAtLocationDetailed(int x, int y)
    {
        switch(getObjectAtLocation(x, y))
        {
            case "X":
                return "a wall";
            case " ":
                return "an empty space";
            case "S":
                return "the start point";
            case "F":
                return "the finish point";
            default: return null;
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(maze);
    }

    public void printToConsole()
    {
        for (int i=0; i < height; i++)
        {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<length; j++)
            {
                sb.append(maze[i][j]);
            }
            System.out.println(sb.toString());

        }
    }
}
