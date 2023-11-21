package AvaluacioInterna;

import processing.core.PApplet;

import static java.lang.Math.min;
import static processing.core.PApplet.constrain;
import static processing.core.PConstants.BACKSPACE;

public class AreaTexto {
    int x, y, h, w;
    int numColumn, numFilas;

    int ColorSinSeleccionar, ColorSeleccionado, ColorTrazo;

    //TEXTO
    String texto= "";
    String [] lineas;
    int sizeTexto=20;

    boolean seleccionado= false;

    public AreaTexto(PApplet p5, int x, int y, int w, int h, int nc, int nf){
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;

        this.numColumn= nc;
        this.numFilas= nf;
        this.lineas= new String[nf];

        this.ColorSinSeleccionar= p5.color(255);
        this.ColorTrazo= p5.color(0);
        this.ColorSeleccionado= p5.color(100);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        if(seleccionado){
            p5.fill(ColorSeleccionado);
        } else{
            p5.fill(ColorSinSeleccionar);
        }

        p5.strokeWeight(2);
        p5.stroke(ColorTrazo);
        p5.rect(x, y, w, h, 5);

        p5.fill(0);
        p5.textSize(sizeTexto);
        for(int i=0; i< lineas.length; i++){
            if(lineas[i]!=null){
                p5.text(lineas[i], x+5, y+(i+1)*sizeTexto);
            }
        }
        p5.popStyle();
    }

    public void saltoLinea(){
        if(texto.length()>0){
            int numLines = constrain(texto.length() / numColumn, 0, this.numFilas-1);
            for(int i=0; i<=numLines; i++){
                int start = i*numColumn;
                int end = min(texto.length(), (i+1)*numColumn);
                lineas[i] = texto.substring(start, end);
            }
        }
        else {
            for(int i=0; i<lineas.length; i++){
                lineas[i] ="";
            }
        }
    }

    public void keyPressed(char key, int keyCode) {
        if (seleccionado) {
            if (keyCode == (int)BACKSPACE) {
                removeTexto();
            } else if (keyCode == 32) {
                addTexto(' '); // SPACE
            } else {
                addTexto(key);
            }
        }
    }

    // Afegeix la lletra c al final del text
    public void addTexto(char c) {
        if (this.texto.length() < this.numColumn*this.numFilas) {
            this.texto += c;
        }
        saltoLinea();
    }

    // Lleva la darrera lletra del text
    public void removeTexto() {
        if (texto.length()> 0) {
            texto = texto.substring(0, texto.length()-1);
        }
        saltoLinea();
    }

    // Indica si el cursor está sobre el campo de texto
    public boolean CursorEncimaAreaTexto(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }
    public void isPressed(PApplet p5) {
        if (CursorEncimaAreaTexto(p5)) {
            seleccionado = true;
        } else {
            seleccionado = false;
        }
    }
}

