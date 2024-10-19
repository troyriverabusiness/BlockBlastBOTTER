package Shapes;

public class ZShape extends Shape {

    public ZShape(ZOrientation orientation) {
        if (orientation == ZOrientation.Horizontal || orientation == ZOrientation.HorizontalInverted) {
            this.height = 2;
            this.width = 3;
        } else {
            this.height = 3;
            this.width = 2;
        }
        this.blocks = (byte) (height + width - 1);
        this.gridRepresentation = createGridRepresentation(orientation);
    }

    public byte[][] createGridRepresentation(ZOrientation orientation) {
        return switch (orientation) {
            case Horizontal -> new byte[][] {{1,1,0},{0,1,1}};
            case Vertical -> new byte[][] {{0,1},{1,1},{1,0}};
            case HorizontalInverted -> new byte[][] {{0,1,1},{1,1,0}};
            case VerticalInverted -> new byte[][] {{1,0},{1,1},{0,1}};
        };
    }
}
