import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Prototype interface
abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

// Concrete Prototype classes
class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// Prototype Registry
class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}

// Client
public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter shape ID to clone (1 - Circle, 2 - Rectangle, 0 - Exit):");
            String shapeId = scanner.next();

            if (shapeId.equals("0")) {
                System.out.println("Exiting...");
                scanner.close();
                break;
            }

            Shape clonedShape = ShapeCache.getShape(shapeId);
            if (clonedShape != null) {
                System.out.println("Shape: " + clonedShape.getType());
                clonedShape.draw();
            } else {
                System.out.println("Invalid shape ID");
            }
        }
    }
}