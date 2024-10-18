public class Rectangle extends Shape {

    public Rectangle(byte height, byte width) {
        this.height = height;
        this.width = width;
        this.blocks = (byte) (height * width);
        this.gridRepresentation = initialiseBlocks(height, width);
    }

    private byte[][] initialiseBlocks(byte height, byte width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Height or width must be greater than 0");
        }
        byte[][] grid = new byte[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = 1;
            }
        }
        return grid;
    }


}
