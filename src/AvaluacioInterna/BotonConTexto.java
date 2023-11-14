package AvaluacioInterna;

import processing.core.PApplet;

import static AvaluacioInterna.Layout.*;
import static AvaluacioInterna.mides.midaSubtitol;


public class BotonConTexto {

    //PROPIEDADES DE UN BOTON
    float x, y, w, h, r; //posición y dimensiones

    int ColorRelleno, ColorTrazo;
    int ColorRellenoEncima; //Color del botón cuando está activo
    String TextoBoton; //TEXTO
    boolean funciona;


    //CONSTRUCTOR
    public BotonConTexto(PApplet p5,  float x, float y, float w, float h, String text){
       this.TextoBoton= text;
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.r= MiniBotonesRadio;
        this.funciona= true;
    }

    //SETTERS
    public void setTextoBoton(String t){
        this.TextoBoton= t;
    }

    public void setColores(int cRelleno, int colorRellenoEncima, int cTrazo){
        this.ColorRelleno= cRelleno;
        this.ColorTrazo= cTrazo;
        this.ColorRellenoEncima= colorRellenoEncima;
    }

    //DIBUJAR EL BOTÓN
    public void display(PApplet p5){
        p5.pushStyle();
        if(mouseEncimaBoton(p5)){
            p5.fill(ColorRellenoEncima); //color cuando el cursor está encima
        }
        else {
            p5.fill(ColorRelleno); //color cuando el botón está activo pero el cursor no está encima
        }

        p5.stroke(ColorTrazo);
        p5.rect(this.x, this.y, this.w, this.h, this.r);

        //Texto botón (color, alienación y mida)
        p5.fill(255);
        p5.textAlign(p5.CENTER);
        p5.textSize(midaSubtitol);
        p5.text(TextoBoton, this.x+MiniBotonesWidth/2, this.y+ 35);

            p5.popStyle();
    }

    //INDICAR SI EL CURSOR ESTÁ SOBRE EL BOTÓN
    public boolean mouseEncimaBoton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }
}
