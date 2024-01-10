package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

public class Vino {

    PImage foto;
    String nombre;
    String DO;
    String bodega;
    String colors;
    String ubicacion;
    String añada;
    int c;

   float margen= 10;

   Vino(PApplet p5, String n, String DO, String b, String color, String u, String a, String f){
       this.nombre=n;
       this.DO= DO;
       this.bodega=b;
       this.colors= color;
       this.ubicacion= u;
       this.añada=a;
       c= p5.color(200);
       setPortada (f, p5);
   }

   void setPortada(String imgName, PApplet p5){
       this.foto= p5.loadImage(imgName);
   }

   void setColor(int c){
       this.c= c;
   }

}
