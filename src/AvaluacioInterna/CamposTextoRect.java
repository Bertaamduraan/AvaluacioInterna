package AvaluacioInterna;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class CamposTextoRect {
    int x, y, w, h;

    int GrosorTrazo;
    int ColorTrazo;
    int ColorRellenoEncima;
    int ColorRelleno;
    String textoEstatico;
    String texto= "";
    int TamañoTexto= 30;

    boolean selected = false;
    Fonts FontsApp;

    /**
     * CONSTRUCTIR
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param x Posición "x" en la que se crea el campo de texto
     * @param y Posición "y" en la que se crea el campo de texto
     * @param w Anchura de la línea del campod e texto
     * @param t Texto inicial del campo de texto
     */
    public CamposTextoRect(PApplet p5, int x, int y, int w, String t){
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= 50;
        this.texto= t;
        this.textoEstatico = t;
        this.GrosorTrazo= 2;
        this.ColorTrazo= p5.color(0);
        FontsApp= new Fonts(p5);

    }

    //SETTERS
    public void setColoresCamposTextoRect(int cRellleno, int colorRellenoEncima, int cTrazo){
        this.ColorRelleno= cRellleno;
        this.ColorRellenoEncima= colorRellenoEncima;
        this.ColorTrazo= cTrazo;
    }

    public void setHeightRectSizeLetra(int height, int s){
        this.h= height;
        this.TamañoTexto= s;
    }


    //Dibuja el campo de texto
    public void display(PApplet p5){
    p5.pushStyle();
        if(cursorEncimaCampoTexto(p5)){
            p5.fill(ColorRellenoEncima);
        }
        else {
            p5.fill(ColorRelleno);
        }
        p5.textFont(FontsApp.getFontAt(2));
        p5.strokeWeight(GrosorTrazo);
        p5.stroke(ColorTrazo);
        p5.rect(this.x+10, this.y, this.w-20, this.h, 10);

        p5.fill(0);
        p5.textSize(TamañoTexto);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text(texto, x+20, y+(this.h/2));
    p5.popStyle();
    }

    //AÑADIR O QUITAR EL TEXTO QUE SE TECLEA
    void keyPressed(char key, int keyCode){
        if (selected) {
            if (keyCode == (int) BACKSPACE && texto.length()>textoEstatico.length()) {
                eliminarTexto();
            }
            else if (keyCode == 32) {
                añadirTexto(' '); // SPACE
            }
            else if(keyCode==16){
                añadirTexto2("");
            }
            else {
                añadirTexto(key);
            }
        }
    }

    public void añadirTexto(char c){
        if(this.texto.length()<w){
            this.texto= this.texto+c;
        }
    }

    public void añadirTexto2(String c){
        if(this.texto.length()<w){
            this.texto= this.texto+c;
        }
    }

    public void eliminarTexto(){
        if (texto.length() > 0) {
            texto = texto.substring(0, texto.length() - 1);
        }
    }

    public String getTexto(){
        return this.texto;
    }
    public String getSoloTexto(){
        return this.texto.replace(this.textoEstatico, "");
    }

    public void setSoloTexto(String textoVa){
        this.texto= this.textoEstatico + textoVa;
    }


    public boolean cursorEncimaCampoTexto(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y+20);
    }

    public void isPressed(PApplet p5) {
        if (cursorEncimaCampoTexto(p5)) {
            selected = true;
        } else {
            selected = false;
        }

    }

}

