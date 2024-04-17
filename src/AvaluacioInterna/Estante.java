package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.constrain;
import static processing.core.PConstants.CENTER;

public class Estante {

    //ATRIBUTOS DE LA CLASE ESTANTE
    float x, y, w, h;
    int pos;
    int cFons;
    int currentWine=0;
    int numVinos= 0;
    int numVinosVisibles;
    Vino[] vinos;
    float vinoWidth;
    float margenH= 10;
    String nombre;
    BotonConFoto bPrev, bNext;

    int selected= -1;
    Fonts FontsApp;

    /**
     * CONSTRUCTOR
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param p Posición del estante si se dibujan más estantes
     * @param n Nombre del estante
     * @param x  Posición "x" en la que se crea el estante
     * @param y  Posición "x" en la que se crea el estante
     * @param w Anchura del estante
     * @param h Altura del estante
     * @param numVisible Número visible de estantes a la misma vez en un estante
     */
    public Estante(PApplet p5, int p, String n, float x, float y, float w, float h, int numVisible){
        this.pos= p;
        this.nombre= n;
        this.vinos= new Vino[100];
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.numVinosVisibles=numVisible;
        this.vinoWidth= w/(float)numVisible;
        FontsApp= new Fonts(p5);
    }

    public Estante(PApplet p5, String n, float x, float y, float w, float h,int numVisible){
        this.nombre= n;
        this.vinos= new Vino[300];
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.numVinosVisibles=numVisible;
        this.vinoWidth= w/(float)numVisible-4;
        FontsApp= new Fonts(p5);
    }

    public void addVino(Vino v){
        if(numVinos<this.vinos.length){
            this.vinos[numVinos]= v;
            numVinos++;
        }
    }

    void addVinos(Vino[] vs){
        for(Vino v: vs){
            addVino(v);
        }
    }

    public void addVINOinfo(String [] info, PApplet p5){
        //Vino v= new Vino(p5, info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
        Vino v= new Vino(p5, info[0], info[1], info[2], info[3], info[4]);
        addVino(v);
    }

    public void addVinos(String[][]info, PApplet p5){
        for(int f=0; f<info.length; f++){
            addVINOinfo(info[f], p5);
        }
    }
    public void setColor(int c){
        this.cFons= c;
    }

    public void setButtons(PApplet p5,String img1, String img2){
        PImage imgPrev= p5.loadImage(img1);
        bPrev= new BotonConFoto(p5, imgPrev, x-40, y+h/2, 30, 30);

        PImage imgNext= p5.loadImage(img2);
        bNext= new BotonConFoto(p5, imgNext, x+w+(vinoWidth/2)-20, y+h/2, 30, 30);
    }

    public void next(){
        if(this.currentWine<this.numVinos-this.numVinosVisibles){
            this.currentWine+=this.numVinosVisibles;
            this.currentWine= constrain(this.currentWine, 0, this.numVinos-1);
        }
    }

    public void prev(){
        if(this.currentWine>0){
            this.currentWine-=this.numVinosVisibles;
            this.currentWine= constrain(this.currentWine, 0, this.numVinos-1);
        }
    }
    //DIBUJA EL ESTANTE
    public void display(PApplet p5){
    p5.pushStyle();
            p5.textFont(FontsApp.getFirstFont());
            p5.fill(cFons);
            p5.stroke(0);
            p5.strokeWeight(2);
            p5.rectMode(p5.CORNER);
            p5.rect(x-5, y-5, w+(vinoWidth/2)-40,  h+10, 7);

            p5.rect(x+w-200+margenH*2+5, y-40, 200, 40, 5);
            p5.noStroke();
            p5.rect(x+w-200+ margenH*2+6, y-39, 198, 44, 7);

            p5.fill(255);
            p5.textAlign(p5.CENTER);
            p5.textSize(24);
            p5.text("("+this.numVinos+") "+this.nombre, x+w-100+margenH*2+10, y-10);

            for(int i=0; i<this.numVinosVisibles; i++){
                int index=i+this.currentWine;

                if(index<this.numVinos){
                    //POSICIÓN EN EL ESTANTE
                    float xPos= x+i*(this.vinoWidth+this.margenH)+3;

                    //VINO A MOSTRAR
                    Vino v= vinos[index];
                    v.setTextSizeSelected(15);
                    v.display(p5, xPos, y, this.vinoWidth, h);

                    //NÚMERO DEL VINO
                    p5.fill(cFons);
                    p5.noStroke();
                    p5.rectMode(p5.CENTER);
                    p5.rect(xPos+45, y+35, 78, 40, 7);
                    p5.fill(0);
                    p5.textAlign(CENTER);
                    p5.text(vinos[index].ubicacion, xPos+45, y+42);
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

    public boolean checkButtons(PApplet p5){
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


    public int checkClickVino(PApplet p5){
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
}
