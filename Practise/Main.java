abstract class Figure {
    String color;

    void setColor(String color) {
        this.color = color;
    }

    void getColor() {
        System.out.println("The color of the figure is " + color);
    }

    abstract double area();   // FIXED
}

class Triangle extends Figure {
    int base;
    int height;

    Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {   // FIXED
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Triangle t1 = new Triangle(10, 20);

        t1.setColor("pink");
        t1.getColor();

        System.out.println("The area of the triangle is " + t1.area());
    }
}