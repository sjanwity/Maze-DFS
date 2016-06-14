package excelian.maze;



public class Explorer {

    private boolean[][] wasHere;
    public boolean[][] correctPath;
    Maze maze;


    public Explorer(Maze maze)
    {
        this.maze = maze;
    }

    /**
     *
     * @return true if there is a solution
     */
	public boolean exploreMaze()
    {
        wasHere = new boolean[maze.length][maze.height];
        correctPath = new boolean[maze.length][maze.height]; // The solution to the maze
        return recursiveSolve(maze.startColumn,maze.startRow);

	}

    /**
     * Solution courtesy of Wikipedia
     *
     * @param start position x
     * @param start position y
     * @return true if there is a solution
     */
    private boolean recursiveSolve(int x, int y)
    {
        if (x == maze.finishColumn && y == maze.finishRow) return true; // If you reached the end
        if (maze.getMaze()[x][y].equals("X") || wasHere[x][y]) return false; // If you are on a wall or already were here

        wasHere[x][y] = true;
        if (x != 0) // Checks if not on left edge
            if (recursiveSolve(x-1, y)) { // Recalls method one to the left
                correctPath[x][y] = true; // Sets that path value to true;
                return true;
            }
        if (x != maze.length - 1) // Checks if not on right edge
            if (recursiveSolve(x+1, y)) { // Recalls method one to the right
                correctPath[x][y] = true;
                return true;
            }
        if (y != 0)  // Checks if not on top edge
            if (recursiveSolve(x, y-1)) { // Recalls method one up
                correctPath[x][y] = true;
                return true;
            }
        if (y != maze.height- 1) // Checks if not on bottom edge
            if (recursiveSolve(x, y+1)) { // Recalls method one down
                correctPath[x][y] = true;
                return true;
            }
        //printCorrectPath();
        return false;
    }

    public boolean legitimateMove(int x, int y, String direction, Maze maze)
    {
        switch (direction)
        {
            //for arrays the co-ordinates are inverted
            case "moveLeft":
                return !(maze.getObjectAtLocation(x, y - 1).equals("X") || x - 1 < 0);
            case "moveRight":
                return !(maze.getObjectAtLocation(x, y + 1).equals("X") || x + 1 > maze.length);
            case "moveUp":
                return !(maze.getObjectAtLocation(x - 1, y).equals("X") || y - 1 < 0);
            case "moveDown":
                return !(maze.getObjectAtLocation(x + 1, y).equals("X") || y + 1 > maze.height);
            default: return false;
        }
    }

    public void printCorrectPath()
    {
            for (int i=0; i < maze.height; i++)
            {
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<maze.length; j++)
                {
                    if (correctPath[i][j])
                        sb.append("t");
                    else
                        sb.append("f");

                }
                System.out.println(sb.toString());

            }

    }
}
