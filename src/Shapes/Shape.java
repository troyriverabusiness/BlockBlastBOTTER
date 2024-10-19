package Shapes;

import java.util.Arrays;

public abstract class Shape {

    protected byte height;
    protected byte width;
    protected byte blocks;

    protected byte[][] gridRepresentation;


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (byte[] row : gridRepresentation) {
            str.append(Arrays.toString(row)).append("\n");
        }
        return str.toString();
    }

    // ============== Getter & Setter ==================
    public byte getHeight() {
        return height;
    }

    public byte getWidth() {
        return width;
    }

    public byte getBlocks() {
        return blocks;
    }

    public byte[][] getGridRepresentation() {
        return gridRepresentation;
    }

}
