package AvaluacioInterna;

import processing.core.PApplet;

public class Selector {
    float x, y, w, h, r;

    String [] textos; //Valores posibles a eligir
    String selectedValue; //Valor seleccionado

    boolean plegado= true;

    float espacioLineas= 8; //Espacio entre líneas
    Fonts FontsApp;
    int TamañoTexto= 18;

    /**
     * CONSTRUCTOR
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param texts Array de opciones que puede tener el selector
     * @param x Posición "x" en la que se crea el selector
     * @param y Posición "x" en la que se crea el selector
     * @param w Anchura del selector
     * @param h Altura del selector
     * @param r Valor para redondear el rectángulo del selector
     */
    public Selector(PApplet p5, String[] texts, float x, float y, float w, float h, float r){

        this.textos= texts;
        this.selectedValue= "";
        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        this.r= r;
        this.plegado= true;
        FontsApp= new Fonts(p5);
    }

    public boolean estaPlegado(){
        return this.plegado;
    }

    public void setTamañoTexto(int t){
        TamañoTexto= t;
    }

    //DIBUJA EL SELECTOR PLEGADO Y DESPLEGADO CON LOS VALORES CORRESPONDIENTES
    public void display(PApplet p5){
    p5.pushStyle();
        p5.textFont(FontsApp.getFontAt(3));
        p5.stroke(0);
        p5.strokeWeight(2);
        p5.fill(255);
        p5.rect(x, y, w, h, r); //rectangulo grande

        p5.stroke(0); p5.stroke(0);
        p5.triangle(x + w - 25, y+5, x + w - 15, y + 25, x + w - 5 , y+5);

        p5.fill(0);
        p5.textSize(TamañoTexto);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text(selectedValue, x+(w/2), y+(h/2));

        if(!this.plegado){
            p5.fill(255); p5.stroke(0);
            p5.rect(x, y+h, w, (h+espacioLineas)*this.textos.length);

             for(int i=0; i< this.textos.length; i++){

                 if(i== OpcionClicked(p5)){
                     p5.fill(200); p5.noStroke();
                     p5.rect(x+4, y+4 + h + (h + espacioLineas)*i - 2, w -8, h + espacioLineas - 8);
                 }

                 p5.fill(0);
                 p5.text(this.textos[i], x + (w/2), y + h + 25 + (h + espacioLineas)*i);
             }
        }
    p5.popStyle();
    }

    public void setSelectedValue(String t){
        this.selectedValue= t;
    }

    public void setPlegado(boolean b){
        this.plegado= b;
    }

    public String getSelectedValue(){
        return this.selectedValue;
    }
    public void cambio(){
        this.plegado= !this.plegado;
    }

    public void actualizar(PApplet p5){
        int option = OpcionClicked(p5);
        selectedValue= this.textos[option];
    }

    //indica si el cursos está sobre el selector
    public boolean cursorEncimaBoton(PApplet p5){
        if(this.plegado){
            return (p5.mouseX >= x) &&
                    (p5.mouseX <= x + w) &&
                    (p5.mouseY >= y) &&
                    (p5.mouseY <= y + h);
        }
        else {
            return (p5.mouseX>= x) &&
                    (p5.mouseX<= x + w) &&
                    (p5.mouseY>= y) &&
                    (p5.mouseY<= y + h + (h + espacioLineas)*this.textos.length);
        }
    }

    int OpcionClicked(PApplet p5){
        int i= (int) p5.map(p5.mouseY, y+h, y+h+(h+espacioLineas)* this.textos.length, 0, this.textos.length);
        return i;
    }
}
