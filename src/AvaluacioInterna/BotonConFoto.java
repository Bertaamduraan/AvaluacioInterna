package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

public class BotonConFoto {

    //Propiedades de un botón
    float x, y, w, h;
    int fillColor;
    int strokeColor;
    int ColorRelleno;
    int ColorRellenoEncima;
    int ColorTrazo;
    PImage logo;

    //Constructor
    public BotonConFoto(PApplet p5, PImage logo, float x, float y, float w, float h){
        this.logo= logo;
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        p5.noStroke();
    }

    //Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        p5.noFill();
        p5.rect(this.x, this.y, this.w, this.h);

        //Imagen del boton
        p5.image(this.logo, this.x, this.y, this.w, this.h);
        p5.popStyle();
    }

    //Indicar si el cursor está sobre el botón
    public boolean mouseEncimaBoton(PApplet p5) {
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }
}
