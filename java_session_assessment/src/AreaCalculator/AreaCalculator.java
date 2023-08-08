package AreaCalculator;
import java.util.Scanner;

public class AreaCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Calculate area of a rectangle
        System.out.println("Rectangle Area Calculator");
        System.out.print("Enter length: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width: ");
        double width = scanner.nextDouble();
        double rectangleArea = calculateRectangleArea(length, width);
        System.out.println("Area of Rectangle: " + rectangleArea);

        // Calculate area of a circle
        System.out.println("\nCircle Area Calculator");
        System.out.print("Enter radius: ");
        double radius = scanner.nextDouble();
        double circleArea = calculateCircleArea(radius);
        System.out.println("Area of Circle: " + circleArea);

        // Calculate area of a triangle
        System.out.println("\nTriangle Area Calculator");
        System.out.print("Enter base: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height: ");
        double height = scanner.nextDouble();
        double triangleArea = calculateTriangleArea(base, height);
        System.out.println("Area of Triangle: " + triangleArea);
        
        scanner.close();
    }

    /**
     * Calculates the area of a rectangle.
     *
     * @param length The length of the rectangle.
     * @param width  The width of the rectangle.
     * @return The area of the rectangle.
     */
    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    /**
     * Calculates the area of a circle.
     *
     * @param radius The radius of the circle.
     * @return The area of the circle.
     */
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the area of a triangle.
     *
     * @param base   The base length of the triangle.
     * @param height The height of the triangle.
     * @return The area of the triangle.
     */
    public static double calculateTriangleArea(double base, double height) {
        return 0.5 * base * height;
    }
    
}
