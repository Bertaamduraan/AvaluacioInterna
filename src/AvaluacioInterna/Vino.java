package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

public class Vino {

    PImage foto;
    String nombre;
    String DO;
    String bodega;
    String color;
    String ubicacion;
    int c;
    int textSize= 12;
   float margen= 10;
    Fonts FontsApp;

    public Vino(PApplet p5, String n, String DO, String color, String u, String f){
        this.nombre=n;
        this.DO= DO;
        this.color= color;
        this.ubicacion= u;
        c= p5.color(200);
        setPortada (f, p5);
        FontsApp= new Fonts(p5);
    }

   public void setPortada(String imgName, PApplet p5){
       this.foto= p5.loadImage(imgName);
   }

   public void setColor(int c){
       this.c= c;
   }

    public void setTextSizeSelected(int textSize){
        this.textSize= textSize;
    }

    /**
     * DIBUJAR VINO CUANDO SE HA CLICADO ENCIMA
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param x Posición "x" de la foto que se crea al clicar encima de un vino
     * @param y Posición "y" de la foto que se crea al clicar encima de un vino
     * @param w Anchura de la foto que se crea al clicar encima de un vino
     * @param h Altura de la foto que se crea al clicar ecnima de un vino
     */
   public void display(PApplet p5, float x, float y, float w, float h){
    p5.pushStyle();
       p5.textFont(FontsApp.getFontAt(0));
        p5.rectMode(p5.CORNER);
        p5.fill(c);
        p5.stroke(0);
        p5.strokeWeight(2);
        p5.rect(x, y, w, h, 7);

        //DIBUJA LA FOTO DEL VINO
       if(foto!=null){
           p5.imageMode(p5.CORNER);
           p5.image(foto, x+margen, y+margen, w-2*margen, 3*h/4);
       } else{
           p5.fill(0);
           p5.rect(x+ margen, y + margen, w - 2*margen, 3*h/4);
       }

       //MUESTRA EL TEXTO DE LA INFORMACIÓN DEL VINO
       p5.textAlign(p5.LEFT);
       p5.textSize(textSize);
       p5.fill(0);
       p5.text(this.nombre, margen+x, y+3*h/4+10, w - 2*margen, h+100);
       /*p5.textSize(12);
       p5.text(this.DO, margen+x, y + 3*h/4 + 25);
       p5.text(this.bodega, margen+x, y + 3*h/4 + 35);
       p5.text(this.color, margen+x, y + 3*h/4 + 45);
       p5.text(this.añada, margen+x, y + 3*h/4 + 55);*/
   p5.popStyle();
   }
   public boolean cursorEncima (PApplet p5, float x, float y, float w, float h){
       return p5.mouseX>x && p5.mouseX<x+w && p5.mouseY>y && p5.mouseY<y+h;
   }
}
