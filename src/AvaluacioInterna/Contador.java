package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

public class Contador {

    //VALOR DEL COMPTADOR
    int valor= 0;
    int minValor= 0;
    int maxValor= 256;
    int incrementValor= 1;

    //PROPIEDADES DE UN CONTADOR
    float x, y, w, h;

    //COLORES
    int fillColor, strokeColor;

    PImage up, down; //ICONOS DEL BOTÓN

    //MÉTODO DEL CONSTRUCTOR
    Contador (PImage mas, PImage menos, float x, float y, float w, float h){
        this.up= mas;
        this.down = menos;
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
    }

    //SETTERS
    public void setValorInicial(int n){
        this.valor= n;
    }

    public void setValores(int minValor, int maxValor){
        this.minValor= minValor;
        this.maxValor= maxValor;
    }


    //DIBUJAR EL BOTÓN
    public void display(PApplet p5){

        p5.fill(255);
        p5.stroke(0);
        p5.rect(this.x, this.y, this.w, this.h*2, 7);

        p5.fill(0);
        p5.textSize(32);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text(valor, this.x+this.w/2, this.y+this.h);

        p5.noStroke();
        p5.noFill();
        p5.rect(this.x+this.w+10, this.y, this.h, this.h);
        p5.image(up, this.x+this.w+10, this.y, this.h, this.h);
        p5.rect( this.x+this.w+10, this.y+this.h, this.h, this.h);
        p5.image(down, this.x+this.w+10, this.y+this.h, this.h, this.h);
    }

    public boolean cursorEncimaBoton(PApplet p5){
        return cursorEncimaBotonUp(p5) || cursorEncimaBotonDown(p5);
    }

    public boolean cursorEncimaBotonUp(PApplet p5){
        return p5.mouseX >= this.x + this.w+10 && p5.mouseX <= this.x + this.w+10 + this.h &&
                p5.mouseY >= this.y && p5.mouseY <= this.y + this.h;
    }

    public boolean cursorEncimaBotonDown(PApplet p5){
        return p5.mouseX >= this.x + this.w+10 && p5.mouseX <= this.x + this.w + 10 + this.h &&
                p5.mouseY >= this.y+this.h && p5.mouseY <= this.y + this.h*2;
    }

    public void incrementar(){
        this.valor+= incrementValor;
        if(this.valor>this.maxValor){
            this.valor= this.maxValor;
        }
    }

    public void decrementar (){
        this.valor-= incrementValor;
        if(this.valor<this.minValor){
            this.valor= this.minValor;
        }
    }

    public void update(PApplet p5){
        if(cursorEncimaBotonUp(p5)){
            incrementar();
        }

        else if(cursorEncimaBotonDown(p5)){
            decrementar();
        }
    }


}
