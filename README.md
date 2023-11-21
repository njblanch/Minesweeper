# Minesweeper
###### By Nathan Blanchard
Welcome to the text-based Java implementation of the classic game Minesweeper!

This game was created as a final project for the class `Intermediate Programming` at UVM.

## Game Rules
The objective of Minesweeper is to uncover all the cells on the grid without detonating
any mines. Each cell either contains a mine or a number indicating the number of mines
in adjacent cells. Use these numbers to strategically reveal safe cells and avoid the
mines.

## Difficulty Levels
This game provides three difficulty levels:

1. **Beginner:** 8 x 8 grid with 8 mines
2. **Intermediate:** 10 x 12 grid with 10 mines
3. **Expert:** 16 x 20 grid with 50 mines

## How to Play
1. Choose a difficulty level at the start of the game.
2. Use coordinates to uncover or flag cells on the grid.
3. Keep uncovering cells until you safely uncover the entire grid or uncover a mine.
4. After each game, you'll be prompted to play again. Choose a new difficulty or exit
the game.

### Controls
- To uncover a cell: Enter `U` followed by the row and column coordinates (e.g., `U 2 3` uncovers row 2 column 3).
- To flag a potential mine: Enter `F` followed by the row and column coordinates (e.g., `F 4 1` flags row 4 column 1).
- To quit the game: Enter `Q`.
