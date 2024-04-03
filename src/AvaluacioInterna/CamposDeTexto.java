package AvaluacioInterna;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class CamposDeTexto {

    int x, y, w, h;

    int GrosorTrazo;
    int ColorLinea;
    String textoEstatico;
    String texto= "";
    int TamañoTexto= 20;

    boolean selected = false;
    Fonts FontsApp;

    public void setTamañoTexto(int t){
        TamañoTexto= t;
    }

    //Constructor
    public CamposDeTexto(PApplet p5, int x, int y, int w, String t){
        this.x= x;
        this.y= y;
        this.w= w;
        this.texto= t;
        this.textoEstatico = t;
        this.GrosorTrazo= 3;
        this.ColorLinea= p5.color(0);
        FontsApp= new Fonts(p5);
    }

    //Dibuja el campo de texto
    public void display(PApplet p5){
        p5.pushStyle();
            p5.textFont(FontsApp.getFontAt(2));
            p5.strokeWeight(GrosorTrazo);
            p5.stroke(ColorLinea);
            p5.line(this.x+10, this.y, this.x+this.w-15, this.y);

            p5.fill(0);
            p5.textSize(TamañoTexto);
            p5.textAlign(p5.LEFT, p5.CENTER);
            p5.text(texto, x+10, y-15);
            //p5.text (texto, x+10+textoEstatico.length(), y-10);
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
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y-50 && p5.mouseY <= this.y);
    }

    public void isPressed(PApplet p5) {
        if (cursorEncimaCampoTexto(p5)) {
            selected = true;
        } else {
            selected = false;
        }

    }
}
