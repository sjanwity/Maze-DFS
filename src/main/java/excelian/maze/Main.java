package excelian.maze;

/**
 * Created by 43777164 on 07/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        int checkRow = 3;
        int checkColumn = 4;

        Maze maze = new Maze();
        try{
            maze.makeMazeFromTextfile("src/main/resources/ExampleMaze.txt");
            maze.printToConsole();
            //System.out.println(maze.toString());
            System.out.println("There are " + maze.getSpaces() + " empty spaces");
            System.out.println("There are " + maze.getWalls() + " walls");
            System.out.println("At " +checkRow  + "," + checkColumn + " the object located there is " + maze.getObjectAtLocationDetailed(checkRow, checkColumn));
            System.out.println("The start point is located at " + maze.startColumn + "," + maze.startRow);
            System.out.println("The end point is located at " + maze.finishColumn + "," + maze.finishRow);

            Explorer explorer = new Explorer(maze);
            System.out.println("Is there a solution? " + explorer.exploreMaze());
            explorer.printCorrectPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
