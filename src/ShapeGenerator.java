import Shapes.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static Shapes.Angle.*;

public class ShapeGenerator {

    private Random random = new Random();

    public Set<Shape> generate3Shapes() {
        Set<Shape> shapes = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            shapes.add(generateRandomShape());
        }
        return shapes;
    }


    public Shape generateRandomShape() {
        int shapenum = random.nextInt(4);
        // following values must be greater than 0
        int height = random.nextInt(3) + 1;
        int width = random.nextInt(3) + 1;
        int orientation = random.nextInt(4);

        assert (height > 0 && width > 0);
        assert (orientation < 4);

        // Generate random shape orientations
        ZOrientation zOrientation = switch (orientation) {
            case 0 -> ZOrientation.Horizontal;
            case 1 -> ZOrientation.Vertical;
            case 2 -> ZOrientation.HorizontalInverted;
            case 3 -> ZOrientation.VerticalInverted;
            default -> throw new IllegalStateException("Unexpected zOrientation: " + orientation);
        };
        PointingDirection tOrientation = switch (orientation) {
            case 0 -> PointingDirection.Left;
            case 1 -> PointingDirection.Right;
            case 2 -> PointingDirection.Up;
            case 3 -> PointingDirection.Down;
            default -> throw new IllegalStateException("Unexpected tOrientation: " + orientation);
        };
        Angle angle = switch (orientation) {
            case 0 -> TR;
            case 1 -> TL;
            case 2 -> BR;
            case 3 -> BL;
            default -> throw new IllegalStateException("Unexpected angle: " + orientation);
        };


        // Generate the random shape with the random parameters
        return switch (shapenum) {
            case 0 -> new Rectangle(height,width);
            case 1 -> {
                // modify height so that even here it doesn't come out as a line
                if (height == 1) {
                    height++;
                }
                if (width == 1) {
                    width++;
                }
                yield new RAShape(height, width, angle);
            }
            case 2 -> new TShape(tOrientation);
            case 3 -> new ZShape(zOrientation);
            default -> throw new IllegalArgumentException("No Shape was generated");
        };
    }


}
