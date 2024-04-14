package AvaluacioInterna;

import processing.core.PApplet;

public class Colors {

    int[] colors;

    public Colors(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    void setColors(PApplet p5){
        this.colors = new int[12];
        this.colors[0] = p5.color(0xFFAB3663); //Color vino
        this.colors[2] = p5.color(0xFF095256); //Color turquesa fuerte
        this.colors[3]= p5.color(0xFF5AAA95); //Color tuquesa flojo
        this.colors[5] = p5.color(0xFF087F8C); //Color AZUL medio
        this.colors[6] = p5.color(0xFFAEECEF); //Color azul flojo

        //COLORES BOTONES
        this.colors[8]= p5.color(0xFFB86221); //NARANJA FUERTE
        this.colors[9]= p5.color(0xFFED8D0E); //NARANJA CLARO

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
