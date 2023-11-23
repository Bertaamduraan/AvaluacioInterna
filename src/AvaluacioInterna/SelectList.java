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

    public void update(PApplet p5){
        String buscar= this.TextField.texto;
        System.out.print("BUSCAR "+ buscar);

        this.NumCoincidencias= 0;
        this.buttons= new ArrayList<BotonConTexto>();

        if(buscar.length()>0){
            for(int i=0; i< texts.length; i++){
                if(texts[i][1].startsWith(buscar)){
                    BotonConTexto b= new BotonConTexto(p5, x+10, y+h+50+(h+50)*NumCoincidencias, w, h, t0);
                    buttons.add(b);
                    this.NumCoincidencias++;
                    if(this.NumCoincidencias==5){
                        break;
                    }
                }
            }

        }
    }

    public boolean cursorEncimaBoton(PApplet p5){
        for(BotonConTexto b: buttons){
            if(b.mouseEncimaBoton(p5)){
                return true;
            }
        }
        return false;
    }

    public void buttonPressed(PApplet p5){
        boolean pressed = false;
        for(BotonConTexto b : buttons){
            if(b.mouseEncimaBoton(p5)){
                TextField.texto = b.TextoBoton;
                this.ValorSelected = b.TextoBoton;
                pressed = true;
            }
        }
        if(pressed){
            buttons.clear();
        }
    }


}
