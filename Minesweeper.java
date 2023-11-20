/**
 * Nathan Blanchard
 * CS 110 - Final Project
 * Class Minesweeper gets user input to control the flow of the game
 */

import java.util.Scanner;

public class Minesweeper
{
    // instance variables
    private Grid board;
    private String difficulty;
    private int gameStatus;
    private int height;
    private int width;
    private boolean first;
    // class constants
    private static int BEG_H = 8;
    private static int BEG_W = 8;
    private static int BEG_M = 8;
    private static int INT_H = 10;
    private static int INT_W = 12;
    private static int INT_M = 10;
    private static int EXP_H = 16;
    private static int EXP_W = 20;
    private static int EXP_M = 50;

    /**
     * method StartGame displays welcome message, creates game board, and begins game
     */
    public void startGame()
    {
        System.out.println("Welcome to Minesweeper!");
        createBoard(false);
        play();
    }

    /**
     * method createBoard generates a game board with a selected difficulty
     * @param reroll true if the first selected square was a mine, false otherwise
     */
    private void createBoard(boolean reroll)
    {
        // asks for user input only if the user has not already selected a difficulty. reroll is true if the first
        // selected square was a mine, so the game generates a new board with previously selected difficulty.
        if(!reroll)
        {
            Scanner kboard = new Scanner(System.in);
            difficulty = "";
            boolean validInput = false;
            // loop until user inputs a valid difficulty selection
            while (!validInput) {
                System.out.println("""
                        Select a difficulty:
                            1) Beginner - 8 x 8 grid with 8 mines
                            2) Intermediate - 10 x 12 grid with 10 mines
                            3) Expert 16 x 20 grid with 50 mines""");
                difficulty = kboard.nextLine();
                // validate input
                if (difficulty.equals("1") || difficulty.equals("2") || difficulty.equals("3"))
                    validInput = true;
                else
                    System.out.println("Invalid input, try again\n");
            }
        }
        // create game board based on selected difficulty
        if(difficulty.equals("1"))
        {
            this.height = BEG_H;
            this.width = BEG_W;
            board = new Grid(height, width, BEG_M);
        }
        else if(difficulty.equals("2"))
        {
            this.height = INT_H;
            this.width = INT_W;
            board = new Grid(height, width, INT_M);
        }
        else
        {
            this.height = EXP_H;
            this.width = EXP_W;
            board = new Grid(height, width, EXP_M);
        }
    }

    /**
     * method nextAction prompts user for next move
     * @return game state after move representing win, loss, or neither
     */
    private int nextAction()
    {
        String[] input;
        String opt = "";
        int row = -1;
        int col = -1;
        boolean validInput = false;
        Scanner kboard = new Scanner(System.in);
        // loop until user inputs a valid move selection
        while (!validInput)
        {
            System.out.println("\nWhat next?");
            System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit");
            // prompts user for move selection, validates input
            try
            {
                input = kboard.nextLine().split(" ");
                opt = input[0];
                if(opt.equals("u") || opt.equals("f"))
                {
                    row = Integer.parseInt(input[1]);
                    col = Integer.parseInt(input[2]);
                    if(row >= height || row < 0 || col >= width || col < 0)
                    {
                        System.out.println("Invalid input, try again");
                    }
                    else
                        validInput = true;
                }
                else if(opt.equals("q"))
                {
                    validInput = true;
                }
                else
                {
                    System.out.println("Invalid input, try again");
                }
            }
            catch(NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Invalid input, try again");
            }
        }
        // executes selected move
        if(opt.equals("u"))
        {
            int result = board.uncoverSquare(row, col);
            // makes a new board if the first selected square is a mine
            while(result == -1 && first)
            {
                createBoard(true);
                result = board.uncoverSquare(row, col);
            }
            first = false;
            return result;
        }
        else if(opt.equals("f"))
        {
            board.flagSquare(row, col);
            return 0;
        }
        return -1;
    }

    /**
     * method play controls the flow of the game
     */
    private void play()
    {
        first = true;
        gameStatus = 0;
        // asks for next move until the user wins, loses, or quits
        while(gameStatus == 0)
        {
            System.out.println(board);
            gameStatus = nextAction();
        }
        // determines the outcome of the game based on game state value returned by nextAction
        if(gameStatus == 1)
        {
            board.exposeMines();
            System.out.println(board);
            System.out.println("You Won!");
        }
        else
        {
            board.exposeMines();
            System.out.println(board);
            System.out.println("Better luck next time!");
        }
        // determines if the user wants to play again
        playAgain();
    }

    /**
     * method playAgain asks the user if they would like to play again, calls startGame if y, nothing if n
     */
    private void playAgain()
    {
        Scanner kboard = new Scanner(System.in);
        String input = "";
        // loop until user inputs a valid selection
        while(!input.equals("y") && !input.equals("n"))
        {
            System.out.println("Play again? (y/n)");
            input = kboard.nextLine().toLowerCase();
            if(!input.equals("y") && !input.equals("n"))
            {
                System.out.println("Invalid input, try again");
            }
        }
        // restarts game if the user inputs y
        if(input.equals("y"))
        {
            startGame();
        }
    }
}
