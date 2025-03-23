package ru.ivt5.v1;

import processing.core.PApplet;
import ru.ivt5.v1.colors.Color;
import ru.ivt5.v1.iface.Colored;
import ru.ivt5.v1.iface.HasArea;
import ru.ivt5.v1.iface.Movable;
import ru.ivt5.v1.iface.Resizable;

import java.util.Objects;

public class ColoredCircle extends Circle implements Colored, Movable, Resizable, HasArea {
    private Color color;

    public ColoredCircle(PApplet sketch, Point center, int radius, Color color) {
        super(sketch, center, radius);
        this.color = color;
    }

    public ColoredCircle(PApplet sketch, int xCenter, int yCenter, int radius, Color color) {
        this(sketch, new Point(xCenter,yCenter), radius, color);
    }

    public ColoredCircle(PApplet sketch, int radius, Color color) {
        this(sketch, new Point(0,0), radius, color);
    }

    public ColoredCircle(PApplet sketch, Color color) {
        this(sketch, new Point(0,0), 1, color);
    }

    public ColoredCircle(PApplet sketch) {
        this(sketch, new Point(0,0), 1, Color.RED);
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
        if (!(o instanceof ColoredCircle)) return false;
        if (!super.equals(o)) return false;
        ColoredCircle that = (ColoredCircle) o;
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

        sketch.ellipse(PApplet.parseFloat(pointTopLeft.getX()), PApplet.parseFloat(pointTopLeft.getY()), PApplet.parseFloat(getRadius()), PApplet.parseFloat(getRadius()));
    }

}
