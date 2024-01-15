package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class Estante {

    float x, y, w, h;

    int pos;
    String nombre;
    int cFons;

    int currentWine=0;
    int numVinos= 0;
    int numVinosVisibles;
    Vino[] vinos;

    float vinoWidth;
    float margenH= 15;
    int selected= -1;


    BotonConFoto bPrev, bNext;

    Estante(int p, String n, float x, float y, float w, float h, int numVisible){
        this.pos= p;
        this.nombre= n;
        this.vinos= new Vino[100];
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.numVinosVisibles=numVisible;
        this.vinoWidth= w/(float)numVisible;
    }

    void addVino(Vino v){
        if(numVinos<this.vinos.length){
            this.vinos[numVinos]= v;
            numVinos++;
        }
    }

    /*void addVinos(Vino[] vs){
        for(Vino v: vs){
            addVino(v);
        }
    }*/

    void addVINOinfo(String [] info, PApplet p5){
        Vino v= new Vino(p5, info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
        addVino(v);
    }

    void addVinos(String[][]info, PApplet p5){
        for(int f=0; f<info.length; f++){
            addVINOinfo(info[f], p5);
        }
    }

    void setButtons(PApplet p5,String img1, String img2){
        PImage imgPrev= p5.loadImage(img1);
        bPrev= new BotonConFoto(p5, imgPrev, x-40, y+h/2, 30, 30);

        PImage imgNext= p5.loadImage(img2);
        bNext= new BotonConFoto(p5, imgNext, x+w+(vinoWidth/2)+5, y+h/2, 30, 30);
    }

    void setColor(int c){
        this.cFons= c;
    }

    void next(){
        if(this.currentWine<this.numVinos-this.numVinosVisibles){
            this.currentWine+=this.numVinosVisibles;
            this.currentWine= constrain(this.currentWine, 0, this.numVinos-1);
        }
    }

    void prev(){
        if(this.currentWine>0){
            this.currentWine-=this.numVinosVisibles;
            this.currentWine= constrain(this.currentWine, 0, this.numVinos-1);
        }
    }

    void display(PApplet p5){
    p5.pushStyle();
            p5.fill(cFons);
            p5.stroke(0);
            p5.strokeWeight(2);
            p5.rectMode(p5.CORNER);
            p5.rect(x-5, y-5, w+(vinoWidth/2)+5,  h+10, 7);

            p5.rect(x+w-200+margenH*2+5, y-40, 200, 40, 5);
            p5.noStroke();
            p5.rect(x+w-200+ margenH*2+6, y -39, 198, 44, 7);

            p5.fill(0);
            p5.textAlign(p5.CENTER);
            p5.textSize(24);
            p5.text("("+this.numVinos+")"+this.nombre, x+w-100+margenH*2+10, y-10);

            for(int i=0; i<this.numVinosVisibles; i++){
                int index=i+this.currentWine;

                if(index<this.numVinos){
                    //POSICIÓN EN EL ESTANTE
                    float xPos= x+i*(this.vinoWidth+this.margenH);

                    //VINO A MOSTRAR
                    Vino v= vinos[index];
                    v.display(p5, xPos, y, this.vinoWidth, h);

                    //NÚMERO DEL VINO
                    p5.fill(cFons);
                    p5.noStroke();
                    p5.rectMode(p5.CENTER);
                    p5.rect(xPos+40, y+40, 40, 40, 7);
                    p5.fill(0);
                    p5.text(vinos[index].ubicacion, xPos+40, y+48);
                }
            }

        if(bNext!=null && (this.currentWine+this.numVinosVisibles)<this.numVinos){
            bNext.display(p5);
            bNext.setEnable(true);
        }
        else{
            bNext.setEnable(false);
        }
        if(bPrev!=null&& this.currentWine>0){
            bPrev.display(p5);
            bPrev.setEnable(true);
        } else{
            bPrev.setEnable(false);
        }
    p5.popStyle();
    }

    boolean checkButtons(PApplet p5){
        if(bNext.mouseEncimaBoton(p5) && bNext.enable){
            this.next();
            return true;
        }
        else if(bPrev.mouseEncimaBoton(p5) && bPrev.enable){
            this.prev();
            return true;
        }
        return false;
    }

    boolean checkCursor(PApplet p5){
        if(bNext.mouseEncimaBoton(p5)&& bNext.enable){
            return true;
        }
        else if(bPrev.mouseEncimaBoton(p5) && bPrev.enable){
            return true;
        }
        else if(this.checkMouseVino(p5)){
            return true;
        }
        return false;
    }

    int checkClickVino(PApplet p5){
        for(int i= 0; i<this.numVinosVisibles; i++){
            int index = i+this.currentWine;
            if(index<this.numVinos){
                float xPos= x+i*(this.vinoWidth+this.margenH);
                Vino v= vinos[index];
                if(v.cursorEncima(p5, xPos, y, this.vinoWidth, h)) {
                    return index;
                }
            }
        }
        return -1;
    }

    boolean checkMouseVino(PApplet p5){
        for(int i= 0; i<this.numVinosVisibles; i++){
            int index= i+this.currentWine;
            if(index<this.numVinos){
                float xPos= x+i*(this.vinoWidth+this.margenH);
                Vino v= vinos[index];
                if(v.cursorEncima(p5, xPos, y, this.vinoWidth, h)){
                    return true;
                }
            }
        }
        return false;
    }

}
