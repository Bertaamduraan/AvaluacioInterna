package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class AbrirCarpeta {

    PImage imagen;
    String título;

    void documentoSleccionado(File selection, PApplet p5){
        if(selection== null){
            p5.println("No se ha seleccionado ningún documento");
        }
        else{
            String rutaImagne= selection.getAbsolutePath();

            imagen= p5.loadImage(rutaImagne); //ACTUALIZAR IMAGEN
            título= selection.getName(); //ACTUALIZAR TÍTULO
        }

    }
}
