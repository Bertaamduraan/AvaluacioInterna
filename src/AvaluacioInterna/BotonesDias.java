package AvaluacioInterna;

import processing.core.PApplet;

public class BotonesDias {
    //DIMENSIONES BOTONES
    float x, y, w, h;

    //DATOS CARACTERÍSTICOS
    int dia, mes, año;

    boolean selected;

    //CONSTRUCTOR
    BotonesDias(float x, float y, float w, float h, int d, int m, int a){
        this.x=x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.dia= d;
        this.mes= m;
        this.año= a;
        this.selected= false;
    }


    void setSeleccionado(boolean b){
        this.selected=b;
    }

    //DIBUJAR EL BOTÓN
    void display(PApplet p5){
        p5.pushStyle();
            p5.fill(255);
            p5.noStroke();
            p5.rect(x, y, w, h);

            if(selected){
                p5.fill(200);
                p5.ellipse(x+w/2, y+h/2, 80, 80);
            }

            p5.fill(0);
            p5.textSize(13);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(dia, x+w/2, y+h/2);
        p5.popStyle();
    }


    boolean cursorEncimaBotonDia(PApplet p5){
        return p5.mouseX>= this.x && p5.mouseX<=this.x+this.w &&
                p5.mouseY>= this.y && p5.mouseY<=this.y+this.h;
    }

}
