package AvaluacioInterna;

import processing.core.PApplet;

import static AvaluacioInterna.Layout.MiniBotonesRadio;


public class BotonConTexto {

    //PROPIEDADES DE UN BOTON
    float x, y, w, h, r; //posición y dimensiones

    int ColorRelleno, ColorTrazo;
    int ColorRellenoEncima; //Color del botón cuando está activo
    String TextoBoton; //TEXTO
    int ColorLetra; //Color texto

    Fonts tipoTexto;
    int MidaTextoBoton= 35;
    boolean funciona;
    Fonts FontsApp;


    /**
     * CONSTRUCTOR
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param x Posición 'x' donde se dibujarà el botón
     * @param y Posición  'y' donde se dibujarà el botón
     * @param w Anchura del botón
     * @param h Altura del botón
     * @param text Texto que tendrá escrito el botón
     */
    public BotonConTexto(PApplet p5,  float x, float y, float w, float h, String text){
       this.TextoBoton= text;
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.r= MiniBotonesRadio;
        this.funciona= true;
        FontsApp= new Fonts(p5);
    }

    //SETTERS
    public void setTextoBoton(String t){
        this.TextoBoton= t;
    }
    public void setMidaTextoBoton(int m){
        this.MidaTextoBoton= m;
    }

    public void setColores(int cRelleno, int colorRellenoEncima, int cTrazo, int cLetras){
        this.ColorRelleno= cRelleno;
        this.ColorTrazo= cTrazo;
        this.ColorRellenoEncima= colorRellenoEncima;
        this.ColorLetra= cLetras;
    }

    public void setFunciona (boolean f){
        this.funciona= f;
    }

    //DIBUJAR EL BOTÓN
    public void display(PApplet p5){
        p5.pushStyle();
            p5.textFont(FontsApp.getFontAt(3));
        if(funciona) {
            if (cursorEncimaBoton(p5)) {
                p5.fill(ColorRellenoEncima); //color cuando el cursor está encima
            } else {
                p5.fill(ColorRelleno); //color cuando el botón está activo pero el cursor no está encima
            }

            p5.stroke(ColorTrazo);
            p5.rect(this.x, this.y, this.w, this.h, this.r);

            //Texto botón (color, alienación y mida)
            p5.fill(ColorLetra);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.textSize(MidaTextoBoton);
            p5.text(TextoBoton, this.x + this.w / 2, this.y + this.h / 2);
        }
        p5.popStyle();
    }

    //INDICAR SI EL CURSOR ESTÁ SOBRE EL BOTÓN
    public boolean cursorEncimaBoton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

}
