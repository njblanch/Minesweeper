/**
 * Nathan Blanchard
 * CS 110 - Final Project
 * Class MineSquare is a Square that contains a mine
 */

public class MineSquare extends Square
{
    /**
     * method uncover uncovers square and sets superclass variable element to *
     */
    public void uncover()
    {
        this.setUncovered();
        this.setElement("*");
    }

    /**
     * method isMine returns true because the square is a mine
     * @return true
     */
    public boolean isMine()
    {
        return true;
    }
}
