package AvaluacioInterna;

import processing.core.PApplet;

public class BotonesDias {
    //DIMENSIONES BOTONES
    float x, y, w, h;

    //DATOS CARACTERÍSTICOS
    int dia, mes, año;



    boolean selected;

    /**
     * CONSTRUCTOR
     * @param x Posición 'x' donde se dibujará el botón del dia
     * @param y Posición 'y' donde se dibujará el botón del dia
     * @param w Anchura del botón
     * @param h Altura del botón
     * @param d Valor numérico entre 1 y 31 que puede tener el botón
     * @param m Valor numérico que representa el mes en que se encuentra el botón
     * @param a Valor numérico que representa el año en el que se ecnuetra el botón
     */
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

    /**
     * DIBUJAR EL BOTÓN
     * @param p5 Objeto de la clase PApplet, para dibujar
     * @param n Valor para disminuir el radio del círculo que se dibuja al pulsar sobre un botón
     * @param Ts Valor numérico que representa el tamaño del texto del botón
     * @param r Valor para redondear las esquinas de los botones
     * @param sW Grosor deñ trazo
     * @param c El color del botón
     */
    void display(PApplet p5, int n, int Ts, int r, int sW, int c){
        p5.pushStyle();
            p5.fill(c);
            p5.stroke(0);
            p5.strokeWeight(sW);
            p5.rect(x, y, w, h, r);

            if(selected){
                p5.fill(30);
                p5.ellipse(x+w/2, y+h/2, w-n, w-n);
            }

            p5.fill(250);
            p5.textSize(Ts);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(dia, x+w/2, y+h/2);
        p5.popStyle();
    }


    boolean cursorEncimaBotonDia(PApplet p5){
        return p5.mouseX>= this.x && p5.mouseX<=this.x+this.w &&
                p5.mouseY>= this.y && p5.mouseY<=this.y+this.h;
    }

}
