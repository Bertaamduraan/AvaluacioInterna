package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PFont;

public class Fonts {
    //Array de tipografies
    PFont[] fonts;

    //Constructor de les Fonts de l'App
    public Fonts(PApplet p5) {
        this.setFonts(p5);
    }

    // Estableix les fonts de l'App
    public void setFonts(PApplet p5){
        this.fonts = new PFont[4];
        this.fonts[0] = p5.createFont("data/WONDER SEASON.ttf", 200);
        this.fonts[1] = p5.createFont("data/CASTLEROCKS-Regular_Demo.ttf", 200);
        this.fonts[2] = p5.createFont("data/Philosopher-Bold.ttf", 200);
    }

    //Getter del número de fonts
    public int getNumFonts(){
        return this.fonts.length;
    }

    // Getter de la font primaria
    public PFont getFirstFont(){
        return  this.fonts[0];
    }

    // Getter del font secundaria
    public PFont getSecondFont(){
        return  this.fonts[1];
    }

    // Getter de la font i-èssima
    public PFont getFontAt(int i){
        return this.fonts[i];
    }

}
