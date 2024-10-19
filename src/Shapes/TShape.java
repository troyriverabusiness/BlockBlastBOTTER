package Shapes;

public class TShape extends Shape {

    public TShape(PointingDirection pointingDirection) {
        if (pointingDirection == PointingDirection.Up || pointingDirection == PointingDirection.Down) {
            this.height = 2;
            this.width = 3;
        } else {
            this.height = 3;
            this.width = 2;
        }
        this.blocks = (byte) (height + width - 1);
        this.gridRepresentation = createGridRepresentation(pointingDirection);
    }

    private byte[][] createGridRepresentation(PointingDirection pointingDirection) {
        return switch (pointingDirection) {
            case Up -> new byte[][]{{0,1,0},{1,1,1}};
            case Down -> new byte[][]{{1,1,1},{0,1,0}};
            case Left -> new byte[][]{{0,1},{1,1},{0,1}};
            case Right -> new byte[][]{{1,0},{1,1},{1,0}};
        };
    }
}
