package AvaluacioInterna;

import processing.core.PApplet;

public class Colors {

    int[] colors;

    public Colors(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    void setColors(PApplet p5){
        this.colors = new int[8];
        this.colors[0] = p5.color(0xFF611633); //Color vino
        this.colors[1] = p5.color(0xFFF1DAB1); //Color beige
        this.colors[2] = p5.color(0xFF19381F); //Color verde fuerte
        this.colors[3] = p5.color(0xFF53A548); //Color verde flojo
        this.colors[4] = p5.color(0xFF4C934C); //Color verde medio
        this.colors[5] = p5.color(0xFFAEECEF); //Color azul flojo
        //COLORES BOTONES
        this.colors[6]= p5.color(0xFFB86221); //NARANJA FUERTE
        this.colors[7]= p5.color(0xFFED8D0E); //NARANJA CLARO

    }

    // Getter del número de colors
    int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    int getColorAt(int i){
        return this.colors[i];
    }

}
