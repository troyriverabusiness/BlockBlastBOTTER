public class RAShape extends Shape {
    // RA-Shape := Right-Angle Shape
    public Angle angle;

    public RAShape(byte height, byte width, Angle angle) {
        this.height = height;
        this.width = width;
        this.angle = angle;
        this.blocks = (byte) (height + width - 1);
        this.gridRepresentation = initialiseBlocks(height, width, angle);
    }

    private byte[][] initialiseBlocks(byte height, byte width, Angle angle) {
        if (height <= 1 || width <= 1) {
            throw new IllegalArgumentException("This shape is not a RA-Shape. Height and width must be greater than 1");
        }
        byte[][] blocks = new byte[height][width];
        switch (angle){
            case TL -> {
                for (int i = 0; i < height; i++) {
                    blocks[i][0] = 1;
                }
                for (int i = 0; i < width; i++) {
                    blocks[0][i] = 1;
                }
            }
            case TR -> {
                for (int i = 0; i < height; i++) {
                    // blocks in the last column
                    blocks[i][width-1] = 1;
                }
                for (int i = 0; i < width; i++) {
                    blocks[0][i] = 1;
                }
            }
            case BL -> {
                for (int i = 0; i < height; i++) {
                    blocks[i][0] = 1;
                }
                for (int i = 0; i < width; i++) {
                    blocks[height-1][i] = 1;
                }
            }
            case BR -> {
                for (int i = 0; i < height; i++) {
                    blocks[i][width-1] = 1;
                }
                for (int i = 0; i < width; i++) {
                    blocks[height-1][i] = 1;
                }
            }
        }
        return blocks;
    }

}
