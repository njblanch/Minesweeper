/**
 * Nathan Blanchard
 * CS 110 - Final Project
 * Abstract class Square provides methods to be used by NumberSquare and MineSquare objects
 */

public abstract class Square
{
    // instance variables
    private String element;
    private boolean flagged;
    private boolean uncovered;

    /**
     * constructor creates a Square object with default values for element, flagged, and uncovered
     */
    public Square()
    {
        this.element = "x";
        this.flagged = false;
        this.uncovered = false;
    }

    /**
     * constructor creates a Square object with specified values for element, flagged, and uncovered
     * @param element value to be displayed on game board
     * @param flagged true if square is flagged
     * @param uncovered true if square is uncovered
     */
    public Square(String element, boolean flagged, boolean uncovered)
    {
        this.element = element;
        this.flagged = flagged;
        this.uncovered = uncovered;
    }

    /**
     * method isFlagged returns if the square has been flagged
     * @return true if the square has been flagged, false otherwise
     */
    public boolean isFlagged()
    {
        return this.flagged;
    }

    /**
     * method isUncovered returns if the square has been uncovered
     * @return true if the square has been uncovered, false otherwise
     */
    public boolean isUncovered()
    {
        return this.uncovered;
    }

    /**
     * method flagSquare switches the boolean value of flagged
     */
    public void flagSquare()
    {
        if(!uncovered)
        {
            this.flagged = !flagged;
            this.element = flagged ? "f" : "x";
        }
    }

    /**
     * method setUncovered sets the value of uncovered to true
     */
    public void setUncovered()
    {
        this.uncovered = true;
    }

    /**
     * method setElement sets the square's value that is displayed on the game board
     * @param element value to be displayed on game board
     */
    public void setElement(String element)
    {
        this.element = element;
    }

    /**
     * abstract method uncover to be overwritten is used to uncover a square
     */
    public abstract void uncover();

    /**
     * abstract method isMine to be overwritten returns if the square is a mine
     * @return true if the square is a mine, false otherwise
     */
    public abstract boolean isMine();


    /**
     * method toString returns the square's element to be displayed on the game board
     * @return element to be displayed on game board
     */
    @Override
    public String toString()
    {
        return element;
    }
}
