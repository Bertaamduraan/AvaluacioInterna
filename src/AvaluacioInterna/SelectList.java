package AvaluacioInterna;

import processing.core.PApplet;

import java.util.ArrayList;

public class SelectList {
    float x, y, w, h;
    String [][] texts;

    CamposTextoRect TextField;

    int selectedFila; //Fila seleccionada
    String selectedID; //ID seleccionado
    String ValorSelected; //Valor seleccionado
    String t0= "0";

    int NumCoincidencias=0;
    ArrayList<BotonConTexto> buttons;

    public SelectList(PApplet p5, String [][] textos, float x, float y, float w, float h){
        this.texts= textos;
        this.selectedID="";
        this.ValorSelected= "";
        this.x= x;
        this.y= y;
        this.w= w;
        this.h=h;

        this.TextField= new CamposTextoRect(p5, (int) x, (int) y, (int) w, t0);
        this.buttons= new ArrayList<BotonConTexto>();
    }

    public void display(PApplet p5){
        p5.pushStyle();
            TextField.display(p5);
            for(BotonConTexto b: buttons){
                b.display(p5);
            }
        p5.popStyle();

    }
}
