package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;
import static AvaluacioInterna.Layout.*;

public class CalendarCarrousel {

    float  x, y, w, h;

    float margenH= 15;

    int numTotalAño;

    Colors coloresApp;

    int currentYear;

    String [] años;

    BotonConFoto bPrev, bNext;

    public CalendarCarrousel(PApplet p5, float x, float y, float w, float h, String[] años){
        this.coloresApp= new Colors(p5);
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.currentYear=0;
        this.numTotalAño= años.length;
        this.años= años;
    }

    //SETTERS
    public void setBotones (PApplet p5, String img1, String img2){
        PImage imgPrev= p5.loadImage(img1);
        bPrev= new BotonConFoto(p5, imgPrev, x-10, y+h/2, 30, 30);

        PImage imgNext= p5.loadImage(img2);
        bNext= new BotonConFoto(p5, imgNext, w+10, y+h/2, 30, 30);
    }

    public void next(){
        if(this.currentYear<this.numTotalAño-1){
            this.currentYear++;
        }
    }

    public void prev(){
        if(this.currentYear>0){
            this.currentYear--;
        }
    }


    public void display(PApplet p5){
        p5.pushStyle();
            p5.fill(this.coloresApp.getColorAt(3));
            p5.noStroke();
            p5.rect(2*marginH, 3*marginV+HeadLineHeight, FilaCalendarioWidth, FilaCalendarioHeight, 10);


                //CAMBIAR TÍTULO CADA VEZ QUE SE CAMBIA
                p5.fill(0);
                p5.textAlign(p5.LEFT, p5.LEFT);
                p5.textSize(30);
                p5.text("AÑO: "+años[this.currentYear], 3*marginH, 3*marginV+HeadLineHeight);

        p5.popStyle();

        if(bNext!=null){
            bNext.display(p5);
        }

        if(bPrev!=null){
            bPrev.display(p5);
        }
    }

    public void checkBotones(PApplet p5){
        if(bNext.mouseEncimaBoton(p5)){
            this.next();
        }
        else if(bPrev.mouseEncimaBoton(p5)){
            this.prev();
        }
    }
}
