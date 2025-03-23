package ru.ivt5.v1;

import processing.core.PApplet;
import ru.ivt5.v1.iface.HasArea;
import ru.ivt5.v1.iface.Movable;
import ru.ivt5.v1.iface.Resizable;

import java.util.Objects;

public class Triangle extends Figure implements Movable, Resizable, HasArea {

    protected PApplet sketch;
    protected Point pointA;
    protected Point pointB;
    protected Point pointC;
    protected int dx;
    protected int dy;

    // Конструкторы
    public Triangle(PApplet sketch, Point pointA, Point pointB, Point pointC) {
        this.sketch = sketch;
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.dx = PApplet.parseInt(sketch.random(-10, 10));
        this.dy = PApplet.parseInt(sketch.random(-10, 10));
    }

    public Triangle(PApplet sketch, int xA, int yA, int xB, int yB, int xC, int yC) {
        this(sketch, new Point(xA, yA), new Point(xB, yB), new Point(xC, yC));
    }

    public Triangle(PApplet sketch) {
        this(sketch, new Point(0, 0), new Point(10, 0), new Point(5, 10));
    }

    // Геттеры и сеттеры
    public Point getPointA() { return pointA; }
    public Point getPointB() { return pointB; }
    public Point getPointC() { return pointC; }

    public void setPointA(Point pointA) { this.pointA = pointA; }
    public void setPointB(Point pointB) { this.pointB = pointB; }
    public void setPointC(Point pointC) { this.pointC = pointC; }



    public void move() {
        pointA.moveRel(dx, dy);
        pointB.moveRel(dx, dy);
        pointC.moveRel(dx, dy);

        if (pointA.getX() < 0 || pointB.getX() < 0 || pointC.getX() < 0 || pointA.getX() > sketch.width || pointB.getX() > sketch.width || pointC.getX() > sketch.width) {
            dx *= -1;
        }

        if (pointA.getY() < 0 || pointB.getY() < 0 || pointC.getY() < 0 || pointA.getY() > sketch.height || pointB.getY() > sketch.height || pointC.getY() > sketch.height) {
            dy *= -1;
        }
    }



    public void moveRel(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    public void moveTo(int x, int y) {
        pointA.setX(x);
        pointA.setY(y);
        pointB.setX(x + 10);  // Сдвиг для второй вершины
        pointB.setY(y);
        pointC.setX(x + 5);   // Сдвиг для третьей вершины
        pointC.setY(y + 10);
    }

    public void moveTo(Point point) {
        pointA.setX(point.getX());
        pointA.setY(point.getY());
        pointB.setX(point.getX() + 10);  // Сдвиг для второй вершины
        pointB.setY(point.getY());
        pointC.setX(point.getX() + 5);   // Сдвиг для третьей вершины
        pointC.setY(point.getY() + 10);
    }

    public void resize(double ratio) {
        pointA.setX((int)(pointA.getX() * ratio));
        pointA.setY((int)(pointA.getY() * ratio));
        pointB.setX((int)(pointB.getX() * ratio));
        pointB.setY((int)(pointB.getY() * ratio));
        pointC.setX((int)(pointC.getX() * ratio));
        pointC.setY((int)(pointC.getY() * ratio));
    }



    public double getArea() {
        double a = pointA.distanceTo(pointB);
        double b = pointB.distanceTo(pointC);
        double c = pointC.distanceTo(pointA);
        double s = (a + b + c) / 2; // полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }



    public double getPerimeter() {
        double a = pointA.distanceTo(pointB);
        double b = pointB.distanceTo(pointC);
        double c = pointC.distanceTo(pointA);
        return a + b + c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return pointA.equals(triangle.pointA) && pointB.equals(triangle.pointB) && pointC.equals(triangle.pointC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointA, pointB, pointC);
    }



    public void render(PApplet sketch) {
        sketch.triangle(PApplet.parseFloat(pointA.getX()), PApplet.parseFloat(pointA.getY()),
                PApplet.parseFloat(pointB.getX()), PApplet.parseFloat(pointB.getY()),
                PApplet.parseFloat(pointC.getX()), PApplet.parseFloat(pointC.getY()));
    }
}
