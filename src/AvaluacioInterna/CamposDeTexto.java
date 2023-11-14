package AvaluacioInterna;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class CamposDeTexto {

    int x, y, w, h;

    int GrosorTrazo;
    int ColorLinea;
    String texto;
    int TamañoTexto= 20;

    boolean selected = false;

    //Constructor
    public CamposDeTexto(PApplet p5, int x, int y, int w, String t){
        this.x= x;
        this.y= y;
        this.w= w;
        this.texto= t;
        this.GrosorTrazo= 3;
        this.ColorLinea= p5.color(0);
    }

    //Dibuja el campo de texto
    public void display(PApplet p5){
        p5.pushStyle();
            p5.strokeWeight(GrosorTrazo);
            p5.stroke(ColorLinea);
            p5.line(this.x+10, this.y, this.x+this.w-15, this.y);

            p5.fill(0);
            p5.textSize(TamañoTexto);
            p5.textAlign(p5.LEFT, p5.CENTER);
            p5.text(texto, x+10, y-10);
        p5.popStyle();
    }

    //AÑADIR O QUITAR EL TEXTO QUE SE TECLEA
    void keyPressed(char key, int keyCode){
        if (selected) {
            if (keyCode == (int) BACKSPACE) {
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
            this.texto+=c;
        }
    }

    public void eliminarTexto(){
        if (texto.length() > 0) {
            texto = texto.substring(0, texto.length() - 1);
        }
    }


    public boolean cursorEncimaCampoTexto(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y-50 && p5.mouseY <= this.y);
    }

    public void isPressed(PApplet p5) {
        //fghjkl
        if (cursorEncimaCampoTexto(p5)) {
            selected = true;
        } else {
            selected = false;
        }

        System.out.println("SELECTED: "+selected);
    }
}
