package ru.ivt5.v1;

import processing.core.PApplet;
import ru.ivt5.v1.iface.Resizable;

public class Elipse extends Circle implements Resizable {

    private int radius2;

    public Elipse(PApplet p , int r1 , int r2){
        super(p, r1);
        radius2 = r2;
    }

    public int getRadius2() {
        return radius2;
    }

    public void setRadius2(int radius2) {
        this.radius2 = radius2;
    }
}