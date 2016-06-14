/**
 * Created by 43777164 on 07/04/2016.
 */
import excelian.maze.Explorer;
import excelian.maze.InvalidMazeException;
import excelian.maze.Maze;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyUnitTest {

    @Test
    public void testValidMaze()
    {
        Maze maze = new Maze();

        try {
            maze.makeMazeFromTextfile("src/main/resources/ExampleMaze.txt");
        } catch (InvalidMazeException e) {
            e.printStackTrace();
        }

        //tests if they're the same maze, and also if correct number of walls and spaces
        assertEquals(1, maze.finishRow);
        assertEquals(14, maze.finishColumn);
        assertEquals(15, maze.height);
        assertEquals(15,maze.length);
        assertEquals(3, maze.startRow);
        assertEquals(3, maze.startColumn);
        assertEquals(74, maze.getSpaces());
        assertEquals(149, maze.getWalls());
    }

    @Test
    public void testInvalidMaze()
    {
        Throwable t = null;
        Maze maze = new Maze();
        try{
            maze.makeMazeFromTextfile("src/main/resources/InvalidMaze.txt");
        }
        catch(Exception e)
        {
            t = e;
        }
        assertTrue(t instanceof InvalidMazeException);

    }

    @Test
    public void testMovementOptions()
    {
        Maze maze = new Maze();

        try {
            maze.makeMazeFromTextfile("src/main/resources/ExampleMaze2.txt");
        } catch (InvalidMazeException e) {
            e.printStackTrace();
        }

        Explorer explorer = new Explorer(maze);
        assertFalse(explorer.legitimateMove(3,3,"moveLeft",maze));
        assertTrue(explorer.legitimateMove(3,3,"moveRight",maze));
        assertFalse(explorer.legitimateMove(3,3,"moveUp",maze));
        assertTrue(explorer.legitimateMove(3,3,"moveDown",maze));


    }

    @Test
    public void testMazeSolution()
    {
        Maze maze = new Maze();
        boolean[][] correctPath = {{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,true,true,true,true,true,true,true,true,true,true,true,true,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,false,false,true,false},
                {false,true,false,true,true,true,true,true,true,true,true,true,false,true,false},
                {false,true,false,true,false,false,false,false,false,false,false,true,false,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,true,false,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,true,false,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,true,false,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,true,false,true,false},
                {false,true,false,true,true,true,true,true,true,true,true,true,false,true,false},
                {false,true,false,true,false,false,false,false,false,false,false,false,false,true,false},
                {false,true,false,true,false,false,false,false,false,false,false,false,false,true,false},
                {false,true,false,true,true,true,true,true,true,true,true,true,false,true,false},
                {false,true,false,false,false,false,false,false,false,false,false,true,true,true,false},
                {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}};

        try {
            maze.makeMazeFromTextfile("src/main/resources/ExampleMaze2.txt");
        } catch (InvalidMazeException e) {
            e.printStackTrace();
        }
        Explorer explorer = new Explorer(maze);
        explorer.exploreMaze();
        assertArrayEquals(correctPath,explorer.correctPath);
    }


}
