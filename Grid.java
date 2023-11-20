/**
 * Nathan Blanchard
 * CS 110 - Final Project
 * Class Grid creates an array of NumberSquares and MineSquares of specified height, width, and number of mines
 */

public class Grid
{
    // instance variables
    private Square[][] grid;
    private int width;
    private int height;
    private int numMines;
    private int numSquaresUncovered;
    // class constants
    public static final int OK = 0;
    public static final int WIN = 1;
    public static final int MINE = -1;

    /**
     * constructor creates an array of Square objects with specified height, width, and number of mines
     * @param height number of rows
     * @param width number of columns
     * @param numMines number of mines
     */
    public Grid(int height, int width, int numMines)
    {
        this.height = height;
        this.width = width;
        this.numMines = numMines;
        this.grid = new Square[height][width];
        // fill grid with specified number of MinesSquare objects
        for(int i = 0; i < numMines; i++)
        {
            int row = (int) (Math.random() * (height - 1));
            int col = (int) (Math.random() * (width - 1));
            if(grid[row][col] == null)
                grid[row][col] = new MineSquare();
            else
                i--;
        }
        // fills the rest of grid with NumberSquare objects
        for(int row = 0; row < height; row++)
        {
            for(int col = 0; col < width; col++)
                if(grid[row][col] == null)
                {
                    grid[row][col] = new NumberSquare(getNeighbors(row, col), row, col);
                }
        }
    }

    /**
     * method getNeighbors returns the number of nearby mines for a specified square
     * @param r square row
     * @param c square column
     * @return number of nearby mines
     */
    public int getNeighbors(int r, int c)
    {
        int numNeighbors = 0;
        for(int row = r - 1; row <= r + 1; row++)
        {
            for(int col = c - 1; col <= c + 1; col++)
            {
                if(row >= 0 && row < height && col >= 0 && col < width)
                {
                    // increments numNeighbors if the nearby square contains a mine
                    if(grid[row][col] != null)
                        numNeighbors += grid[row][col].isMine() ? 1 : 0;
                }
            }
        }
        return numNeighbors;
    }

    /**
     * method uncoverSquare reveals the contents of a selected square, returns a
     * game state value representing a win, loss, or neither based on contents
     * @param r square row
     * @param c square column
     * @return game state value
     */
    public int uncoverSquare(int r, int c)
    {
        // check to make sure square is not flagged or uncovered
        if(!grid[r][c].isFlagged() && !grid[r][c].isUncovered())
        {
            // uncover square, increment numSquaresUncovered
            grid[r][c].uncover();
            numSquaresUncovered++;
            // determine return value based on type of square
            if (grid[r][c].isMine())
            {
                return MINE;
            }
            else if (numSquaresUncovered == width * height - numMines)
            {
                return WIN;
            }
            // recursively uncover nearby squares if there are no nearby mines
            if (((NumberSquare) grid[r][c]).getNeighborMines() == 0) {
                for (int row = r - 1; row <= r + 1; row++) {
                    for (int col = c - 1; col <= c + 1; col++) {
                        if (row >= 0 && row < height && col >= 0 && col < width && !grid[row][col].isUncovered() && !grid[row][col].isFlagged()) {
                            uncoverSquare(row, col);
                        }
                    }
                }
            }
        }
        return OK;
    }

    /**
     * method exposeMines reveals the locations of all mines
     */
    public void exposeMines()
    {
        for(int row = 0; row < height; row++)
        {
            for(int col = 0; col < width; col++)
            {
                if(grid[row][col].isMine())
                {
                    grid[row][col].uncover();
                }
            }
        }
    }

    /**
     * method flagSquare flags a specified square
     * @param r row
     * @param c column
     */
    public void flagSquare(int r, int c)
    {
        grid[r][c].flagSquare();
    }

    /**
     * method toString returns a formatted string representing a game board
     * @return formatted string representing game board
     */
    @Override
    public String toString()
    {
        String output = "";
        for(int col = 0; col < width; col++)
        {
            output += String.format("\t%d", col);
        }
        for(int row = 0; row < height; row++)
        {
            output += String.format("\n%d", row);
            for(int col = 0; col < width; col++)
            {
                output += String.format("\t%s", grid[row][col]);
            }
        }
        return output;
    }
}
