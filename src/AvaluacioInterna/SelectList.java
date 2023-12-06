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
    String t0;

    int NumCoincidencias=0;
    ArrayList<BotonConTexto> buttons;

    public SelectList(PApplet p5, String [][] textos, float x, float y, float w, float h, String textoIncial){
        this.texts= textos;
        this.selectedID="";
        this.ValorSelected= "";
        this.x= x;
        this.y= y;
        this.w= w;
        this.h=h;
        this.t0= textoIncial;

        this.TextField= new CamposTextoRect(p5, (int) x, (int) y, (int) w, ValorSelected);
        this.TextField.setHeightRectSizeLetra((int)this.h, 18);
        this.buttons= new ArrayList<BotonConTexto>();
    }

    public CamposTextoRect getTextField(){
        return  this.TextField;
    }

    public String getSelectedValue(){
        return this.ValorSelected;
    }


    public void display(PApplet p5){
        p5.pushStyle();
            TextField.setColoresCamposTextoRect(255, 200, 0);
            TextField.display(p5);
            if(!TextField.selected && ValorSelected.equals("")){
                p5.textSize(18);
                p5.fill(0);
                p5.textAlign(p5.CENTER, p5.CENTER);
                p5.text(t0, this.x+this.w/2, this.y+ this.h/2);
            }
            for(BotonConTexto b: buttons){
                b.setColores(255, 200, 0, 0);
                b.setMidaTextoBoton(18);
                p5.textAlign(p5.CENTER, p5.CENTER);
                b.display(p5);
            }
        p5.popStyle();
    }

    public void update(PApplet p5){
        String buscar= this.TextField.texto;
        System.out.print("BUSCAR "+ buscar);

        this.NumCoincidencias= 0;
        this.buttons = new ArrayList<BotonConTexto>();

        if(buscar.length()>0){
            for(int i=0; i< texts.length; i++){
                if(this.texts[i][1].startsWith(buscar)){
                    BotonConTexto b= new BotonConTexto(p5, x+15, y+h+5+(h-15)*NumCoincidencias, w-30, h-20, texts[i][1]);
                    buttons.add(b);
                    this.NumCoincidencias++;
                    if(this.NumCoincidencias==5){
                        break;
                    }
                }
            }

        }
    }

    public boolean cursorEncimaBotonSL(PApplet p5){
        for(BotonConTexto b: buttons){
            if(b.cursorEncimaBoton(p5)){
                return true;
            }
        }
        return false;
    }

    public void buttonPressed(PApplet p5){
        boolean pressed = false;
        for(BotonConTexto b : buttons){
            if(b.cursorEncimaBoton(p5)){
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
