package AvaluacioInterna;

import processing.core.PApplet;

public class Estanteria {

    Estante [] estanteria;

    Estanteria(Estante es1, Estante es2, Estante es3){
        estanteria= new Estante[3];
        estanteria[0]= es1;
        estanteria[1]= es2;
        estanteria[2]= es3;
    }

    public void display(PApplet p5){
        p5.pushStyle();
            p5.rectMode(p5.LEFT);
            p5.noFill();
            p5.stroke(0);
            p5.strokeWeight(3);
            p5.rect(estanteria[0].x, estanteria[0].y, estanteria[0].w+300, estanteria[0].h*4+25);
        p5.popStyle();
    }

}
