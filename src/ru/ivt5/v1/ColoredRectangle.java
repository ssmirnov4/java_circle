package ru.ivt5.v1;

import ru.ivt5.v1.colors.Color;
import ru.ivt5.v1.iface.*;
import processing.core.PApplet;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored, Movable, Resizable, Stretchable, HasArea {
    private Color color;

    public ColoredRectangle(PApplet sketch, Point leftTop, Point rightBottom, Color color) {
        super(sketch,leftTop,rightBottom);
        this.color = color;
    }

    public ColoredRectangle(PApplet sketch,int xLeft, int yTop, int xRight, int yBottom, Color color) {
        this(sketch,new Point(xLeft,yTop), new Point(xRight,yBottom), color);
    }

    public ColoredRectangle(PApplet sketch,int length, int width, Color color) {
        this(sketch, new Point(0,-width),new Point(length,0), color);
    }

	public ColoredRectangle(PApplet sketch,Color color) {
        this(sketch, new Point(0,-1),new Point(1,0),color);
    }

    public ColoredRectangle(PApplet sketch) {
        this(sketch, new Point(0,-1),new Point(1,0),Color.RED);
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

    public void render(PApplet sketch) {
        int r = color.getR();
        int g = color.getG();
        int b = color.getB();

        sketch.fill(r, g, b);
            sketch.rect(getTopLeft().getX(), getTopLeft().getY(), getLength(), getWidth());

    }
}
