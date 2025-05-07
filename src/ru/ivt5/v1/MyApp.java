package ru.ivt5.v1;

import java.util.ArrayList;
import processing.core.PApplet;
import ru.ivt5.v1.colors.Color;


public class MyApp extends PApplet {

    private ArrayList<ColoredCircle> circles = new ArrayList<>();
    private ArrayList<ColoredRectangle> rectangles = new ArrayList<>();

    public void settings() {
        size(800, 800);
        circles.add(new ColoredCircle(this, width / 2, height / 2, 50, Color.RED));
        rectangles.add(new ColoredRectangle(this, new Point(100,100), new Point(200,160), Color.RED));
    }

    public void draw() {
        background(64);
        for (ColoredCircle c : circles) {
            c.move();
            c.render(this);
        }

        for(ColoredRectangle r : rectangles){
            r.move();
            r.render(this);
        }
    }
    private Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[(int) random(colors.length)];
    }

    public void mouseDragged() {
        Color randomColor = getRandomColor();
        circles.add(new ColoredCircle(this, mouseX, mouseY, parseInt(super.random(10, 50)), randomColor));
        rectangles.add(new ColoredRectangle(this, new Point(mouseX, mouseY), new Point(mouseX+parseInt(random(10, 50)), mouseY+parseInt(random(10, 50))), randomColor));
    }


    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
        MyApp mySketch = new MyApp();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
