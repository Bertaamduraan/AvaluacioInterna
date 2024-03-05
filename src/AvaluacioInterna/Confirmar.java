package AvaluacioInterna;

import processing.core.PApplet;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class Confirmar {
    float x, y, w, h;

    String titulo;
    String mensaje;

    Colors ColoresAPP;
    Fonts FontsAPP;

    BotonConTexto bAceptar, bCancelar;
    float widthBoton= 95, heightBoton= 45;

    boolean visible= false;

    //CONSTRUCTOR
    Confirmar (PApplet p5, String t, String m, float x, float y, float w, float h) {
        ColoresAPP= new Colors(p5);
        FontsAPP= new Fonts(p5);
        this.titulo = t;
        this.mensaje = m;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bAceptar = new BotonConTexto(p5, x-w/4 , y + h/5, widthBoton, heightBoton, "ACEPTAR");
        this.bCancelar = new BotonConTexto(p5, x+w/16, y + h/5, widthBoton, heightBoton, "CANCELAR");
    }

    //SETTERS
    void setTextoBotones (String texto1, String texto2){
        this.bAceptar.setTextoBoton(texto1);
        this.bCancelar.setTextoBoton(texto2);
    }

    void setTextos (String t, String m){
        this.titulo= t;
        this.mensaje= m;
    }

    void setVisible (boolean visible){
        this.visible= visible;
        if(!this.visible){
            this.bAceptar.setFunciona(false);
            this.bCancelar.setFunciona(false);
        }
        else{
            this.bAceptar.setFunciona(true);
            this.bCancelar.setFunciona(true);
        }
    }

    void display(PApplet p5){
        if(this.visible){
            float borde= 10;
            p5.pushStyle();
                p5.rectMode(CENTER);
                p5.textAlign(CENTER);
                p5.stroke(0);
                p5.strokeWeight(2);
                p5.fill(ColoresAPP.getColorAt(3));
                p5.rect(x, y, w, h, borde);
                p5.line(x-w/2, y-5*borde, x+w/2, y-5*borde);

                p5.textFont(FontsAPP.getFirstFont());
                //Título
                p5.fill(0);
                p5.textSize(25);
                p5.text(titulo, x, (float) (y-5.5*borde));

                //Mensaje
                p5.fill(0);
                p5.textSize(17);
                p5.textAlign(CENTER);
                p5.text(mensaje, x, y-10);

                //BOTONES
                p5.rectMode(CORNER);
                bAceptar.setColores(255, 200, 0, 0);
                bCancelar.setColores(255, 200, 0, 0);
                bAceptar.setMidaTextoBoton(17);
                bCancelar.setMidaTextoBoton(17);
                bAceptar.display(p5);
                bCancelar.display(p5);
            p5.popStyle();

        }
    }
}
