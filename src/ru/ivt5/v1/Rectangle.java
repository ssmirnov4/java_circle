package ru.ivt5.v1;

import processing.core.PApplet;
import ru.ivt5.v1.iface.HasArea;
import ru.ivt5.v1.iface.Movable;
import ru.ivt5.v1.iface.Resizable;
import ru.ivt5.v1.iface.Stretchable;

import java.util.Objects;

public class Rectangle extends Figure implements Movable, Resizable, Stretchable, HasArea {
    protected Point pointTopLeft;
    protected Point pointBottomRight;

    protected PApplet sketch;
    protected int dx;
    protected int dy;

    public Rectangle(PApplet sketch, Point leftTop, Point rightBottom) {
        this.pointTopLeft = leftTop;
        this.pointBottomRight = rightBottom;
        this.sketch = sketch;
        this.dx = PApplet.parseInt(sketch.random(-5, 5));
        this.dy = PApplet.parseInt(sketch.random(-5, 5));
        if (dx == 0) dx = 1;
        if (dy == 0) dy = 1;
    }

    public Rectangle(PApplet sketch, int xLeft, int yTop, int xRight, int yBottom) {
        this(sketch, new Point(xLeft, yTop), new Point(xRight, yBottom));
    }

    public Rectangle(PApplet sketch, int length, int width) {
        this(sketch, new Point(0, -width), new Point(length, 0));
    }

    public Rectangle(PApplet sketch) {
        this(sketch, new Point(0, -1), new Point(1, 0));
    }

    public final void setBottomRight(Point bottomRight) {
        pointBottomRight = bottomRight;
    }

    public Point getTopLeft(){
        return pointTopLeft;
    }


    public Point getBottomRight() {
        return pointBottomRight;
    }

    public int getLength() {
        return pointBottomRight.getX() - pointTopLeft.getX();
    }

    public final int getWidth() {
        return pointBottomRight.getY() - pointTopLeft.getY();
    }

    public void moveTo(int x, int y) {
        if (pointTopLeft != null && pointBottomRight != null) {
            pointBottomRight.moveTo(x + getLength(), y + getWidth());
            pointTopLeft.moveTo(x, y);
        }
    }

    public void moveTo(Point point) {
        if (point != null) {
            pointBottomRight.moveTo(point.getX() + getLength(), point.getY() + getWidth());
            pointTopLeft = point;
        }
    }

    public void moveRel(int dx, int dy) {
        if (pointTopLeft != null && pointBottomRight != null) {
            pointTopLeft.moveRel(dx, dy);
            pointBottomRight.moveRel(dx, dy);
        }
    }

    public void move() {
        if (pointTopLeft != null && pointBottomRight != null) {
            moveRel(dx, dy);
            if (getTopLeft().getX() < 0 || getBottomRight().getX() > sketch.width) {
                dx *= -1;
            }
            if (getTopLeft().getY() < 0 || getBottomRight().getY() > sketch.height) {
                dy *= -1;
            }
        }
    }

    public void resize(double ratio) {
        if (pointBottomRight != null) {
            pointBottomRight.moveRel((int) (getLength() * ratio) - getLength(),
                    (int) (getWidth() * ratio) - getWidth());
        }
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return 2 * (getLength() + getWidth());
    }

    public boolean isInside(int x, int y) {
        return pointTopLeft.getX() <= x && pointBottomRight.getX() >= x &&
                pointTopLeft.getY() <= y && pointBottomRight.getY() >= y;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight());
    }

    public boolean isIntersects(Rectangle rectangle) {
        return getTopLeft().getX() <= rectangle.getBottomRight().getX() &&
                getBottomRight().getX() >= rectangle.getTopLeft().getX() &&
                getTopLeft().getY() <= rectangle.getBottomRight().getY() &&
                getBottomRight().getY() >= rectangle.getTopLeft().getY();
    }

    public final void stretch(double xRatio, double yRatio) {
        if (pointBottomRight != null) {
            pointBottomRight.moveRel((int) (getLength() * xRatio) - getLength(),
                    (int) (getWidth() * yRatio) - getWidth());
        }
    }

    public void render(PApplet sketch) {
        if (pointTopLeft != null) {
            sketch.rect(getTopLeft().getX(), getTopLeft().getY(), getLength(), getWidth());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return pointTopLeft.equals(rectangle.pointTopLeft) &&
                pointBottomRight.equals(rectangle.pointBottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointTopLeft, pointBottomRight);
    }
}
