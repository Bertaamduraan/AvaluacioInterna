package AvaluacioInterna;

import processing.core.PApplet;


public class Cercle {
    float x, y, r;
    int c;

    public Cercle(float x, float y, float r){
        this.x = x; this.y = y;
        this.r = r;
    }

    public void setColor(int c){
        this.c = c;
    }

    public void display(PApplet p5){
        p5.fill(c); p5.stroke(0); p5.strokeWeight(3);
        p5.ellipse(x, y, r, r);
    }

}
