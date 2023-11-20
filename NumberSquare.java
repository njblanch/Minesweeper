/**
 * Nathan Blanchard
 * CS 110 - Final Project
 * Class NumberSquare represents a Square that does not contain a mine
 */

public class NumberSquare extends Square
{
    // instance variables
    private int neighborMines;
    private int myRow;
    private int myCol;

    /**
     * constructor creates a NumberSquare with specified number of nearby mines, row, and column
     * @param neighborMines number of nearby mines
     * @param myRow row
     * @param myCol column
     */
    public NumberSquare(int neighborMines, int myRow, int myCol)
    {
        this.neighborMines = neighborMines;
        this.myRow = myRow;
        this.myCol = myCol;
    }

    /**
     * method uncover uncovers square and sets superclass variable element to
     * underscore or value of neighborMines based on number of nearby mines
     */
    @Override
    public void uncover()
    {
        setUncovered();
        if(neighborMines == 0)
        {
            setElement("_");
        }
        else
        {
            setElement(""+neighborMines);
        }
    }

    /**
     * method getNeighborMines returns the number of nearby mines
     * @return number of nearby mines
     */
    public int getNeighborMines()
    {
        return neighborMines;
    }

    /**
     * method isMine returns false because the square is not a mine
     * @return false
     */
    @Override
    public boolean isMine()
    {
        return false;
    }
}
