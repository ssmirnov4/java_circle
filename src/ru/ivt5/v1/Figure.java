package ru.ivt5.v1;

public abstract class Figure {
    protected Point pointTopLeft;

    public Point getTopLeft() {
        return pointTopLeft;
    }

    public void setTopLeft(Point pointTopLeft) {
        this.pointTopLeft = pointTopLeft;
    }
}
