package AvaluacioInterna;

import processing.core.PApplet;

public class RadioButton {

    int x, y, r;
    String texto;

    int colorSinSeleccionar, colorTrazo, colorSeleccionado;

    boolean seleccionado;
    Fonts FontsApp;

    public RadioButton(PApplet p5, int x, int y, int radio, String t){
        this.x= x;
        this.y=y;
        this.r= radio;
        this.colorSinSeleccionar= p5.color(0);
        this.colorTrazo= p5.color(255);
        this.colorSeleccionado= p5.color(200);
        this.texto= t;
        FontsApp= new Fonts(p5);
    }

    //GETTER
    public boolean estaSeleccionado(){
        return this.seleccionado;
    }

    public void setSeleccionado(boolean b){
        this.seleccionado= b;
    }

    public void cambio(){
        this.seleccionado=!this.seleccionado;
    }


    public void display(PApplet p5){
        p5.pushStyle();
            p5.noStroke();
            p5.textFont(FontsApp.getFontAt(2));
            p5.fill(colorSinSeleccionar);
            p5.ellipse(x, y, 2*r, 2*r);
            p5.textAlign(p5.LEFT, p5.CENTER);
            p5.textSize(25);
            p5.fill(0);
            p5.text(this.texto, this.x+30, this.y);
        p5.popStyle();

        if(this.seleccionado){
            p5.fill(colorSeleccionado);
            p5.ellipse(x, y, 1.5f*r, 1.5f*r);
        }
    }

    public boolean cursorEncima (PApplet p5){
        return p5.dist(p5.mouseX, p5.mouseY, this.x, this.y) < this.r;
    }
}
