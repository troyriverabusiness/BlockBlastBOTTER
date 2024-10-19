import Shapes.Shape;

import java.util.LinkedList;
import java.util.Set;

public class Board {

    public byte[][] grid;
    public int points;
    public int combo;

    public Set<Shape> ShapesTriple;
    // the three shapes that are to be added

    public Board() {
        grid = new byte[8][8];
        points = 0;
        combo = 0;
    }


    public void addShapeAtIndex(byte x, byte y, Shape shape) {
        if (!canBeAdded(x, y, shape)) {
            throw new IllegalArgumentException("Shape.Shape cannot be added at this index");
        }
        int thisRoundsPoints = 0;

        // add the shape to the grid at the index provided.
        for (byte across = x, shapeX = 0; across < x + shape.getWidth(); across++, shapeX++) {
            for (byte down = y, shapeY = 0; down < y + shape.getHeight(); down++, shapeY++) {
                // the shapes blocks are placed on the grid
                grid[down][across] += shape.getGridRepresentation()[shapeY][shapeX];
            }
        }
        // add assertion that the grid only contains {0,1}
        assert (true);

        thisRoundsPoints += shape.getBlocks();

        // check if any rows/columns can be destroyed
        LinkedList<Byte> fullRows = getFullRows(y, shape);
        LinkedList<Byte> fullColumns = getFullColumns(x, shape);

        // delete the full rows/columns while adding the points
        int destroyRowColumnPoints = 0;
        if (!fullRows.isEmpty()) {
            for (Byte row : fullRows) {
                destroyRow(row);
                destroyRowColumnPoints += 10;     // add the points from completing a row
            }
        }
        if (!fullColumns.isEmpty()) {
            for (Byte column : fullColumns) {
                destroyColumn(column);
                destroyRowColumnPoints += 10;     // add the points from completing a column
            }
        }
        thisRoundsPoints += destroyRowColumnPoints;

        // Calculate the combo points
        int comboPoints = combo * destroyRowColumnPoints;
        thisRoundsPoints += comboPoints;

        // add points
        points += thisRoundsPoints;
    }

    // checks if a shape can fit in given index without overlapping others.
    public boolean canBeAdded(byte x, byte y, Shape shape) {
        // for a rectangle, it checks simply if the allocated space is free (== 0)
        if (shape instanceof Shapes.Rectangle) {
            for (byte across = x; across < x + shape.getWidth(); across++) {
                for (byte down = y; down < y + shape.getHeight(); down++) {
                    if (grid[down][across] == 1) return false;
                }
            }
        } else {
            // else it places the shape directly onto the grid and checks if any overlapping
            byte[][] gridCopy = this.grid;
            for (byte across = x, shapeX = 0; across < x + shape.getWidth(); across++, shapeX++) {
                for (byte down = y, shapeY = 0; down < y + shape.getHeight(); down++, shapeY++) {
                    // the shapes blocks are placed on the grid copy
                    grid[down][across] += shape.getGridRepresentation()[shapeY][shapeX];
                    // if any overlap then the shape cannot fit
                    if (grid[down][across] > 1) return false;
                }
            }
        }
        return true;
    }


    // gets the rows in the grid that are full starting from y-index "y"
    private LinkedList<Byte> getFullRows(byte y, Shape shape) {
        LinkedList<Byte> rows = new LinkedList<>();
        for (byte downIndex = y; downIndex < y + shape.getHeight(); downIndex++) {
            byte counter = 0;
            for (byte across = 0; across < grid.length; across++) {
                if (grid[downIndex][across] == 1) counter++;
            }
            if (counter == 8) rows.add(downIndex);
        }
        return rows;
    }

    // gets columns that are full starting from x-index "x"
    private LinkedList<Byte> getFullColumns(byte x, Shape shape) {
        LinkedList<Byte> cols = new LinkedList<>();
        // Checks for full COLUMNS
        for (byte acrossIndex = x; acrossIndex < x + shape.getWidth(); acrossIndex++) {
            // iterates horizontally over the columns where the shape has been placed, instead of the full grid
            byte counter = 0;
            for (byte down = 0; down < grid.length; down++) {
                if (grid[down][acrossIndex] == 1) counter++;
            }
            if (counter == 8) cols.add(acrossIndex);
            // if the column is full it adds the index of the filled column
        }
        return cols;
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
