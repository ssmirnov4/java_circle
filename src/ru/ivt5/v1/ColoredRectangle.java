package ru.ivt5.v1;

import ru.ivt5.v1.colors.Color;
import ru.ivt5.v1.iface.*;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored, Movable, Resizable, Stretchable, HasArea {
    private Color color;

    public ColoredRectangle(Point leftTop, Point rightBottom, Color color) {
        super(leftTop,rightBottom);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) {
        this(new Point(xLeft,yTop), new Point(xRight,yBottom), color);
    }

    public ColoredRectangle(int length, int width, Color color) {
        this(new Point(0,-width),new Point(length,0), color);
    }

	public ColoredRectangle(Color color) {
        this(new Point(0,-1),new Point(1,0),color);
    }

    public ColoredRectangle() {
        this(new Point(0,-1),new Point(1,0),Color.RED);
    }

 	public Color getColor() {
 	    return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColoredRectangle)) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return getColor() == that.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }
}
