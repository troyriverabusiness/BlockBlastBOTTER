public class Board {

    public byte[][] grid;
    public int points;
    public int combo;

    public Board() {
        grid = new byte[8][8];
        points = 0;
        combo = 0;
    }


    public void addShape(byte x, byte y, Shape shape) {
        if (!canBeAdded(x, y, shape)) {
            return;
        }
        // add the shape grid at the index provided.

        // check if any rows/columns can be destroyed
    }

    public boolean canBeAdded(byte x, byte y, Shape shape) {
        // quick checks to see if a shape can be added
        if (shape instanceof Rectangle) {
            for (byte i = x; i < grid.length; i++) {
                for (byte j = y; j < grid.length; j++) {
                    if (grid[i][j] == 1) return false;
                }
            }
            return true;
        }

        // add checks for the rest of shapes

        return false;
    }

    public void destroyColumn(int x) {
        // destroys a column like in the game
        for (int i = 0; i < grid.length; i++) {
            grid[i][x] = 0;
        }
    }

    public void destroyRow(int y) {
        // destroys a row like in the game
        for (int i = 0; i < grid.length; i++) {
            grid[y][i] = 0;
        }
    }

}
