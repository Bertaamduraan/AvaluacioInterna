package AvaluacioInterna;

import processing.core.PApplet;

public class GrupoRadioButton {

    RadioButton[] rbuttonsgroup;
    int OpcionSeleccionada;

    public GrupoRadioButton(int i){
        rbuttonsgroup= new RadioButton[i];
        OpcionSeleccionada=0;
    }

    public void setRadioButtons(RadioButton... rbs){
        for(int i= 0; i<rbs.length; i++){
            this.rbuttonsgroup[i]= rbs[i];
        }
    }

    public void setSeleccionado(int n){
        OpcionSeleccionada= n;
    }

    public void display(PApplet p5) {
        if (clicado1RadioButton(p5)) {
            for (int i = 0; i < rbuttonsgroup.length; i++) {
                if (rbuttonsgroup[i] != null && rbuttonsgroup[i].cursorEncima(p5)) {
                    OpcionSeleccionada = i;
                    rbuttonsgroup[i].setSeleccionado(true);
                } else {
                    rbuttonsgroup[i].setSeleccionado(false);
                }
            }
        }
    }

    public boolean clicado1RadioButton(PApplet p5){
        for(int i=0; i<rbuttonsgroup.length; i++){
            if (rbuttonsgroup[i] != null && rbuttonsgroup[i].cursorEncima(p5)) {
                return true;
            }
        }
        return false;
    }



}


