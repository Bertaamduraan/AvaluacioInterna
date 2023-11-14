package AvaluacioInterna;

import processing.core.PApplet;

import static AvaluacioInterna.Layout.LogoMenuHeight;
import static AvaluacioInterna.Layout.LogoMenuWidth;

public class BotonMenu {

        // Propietats d'un botó:

        float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
        int fillColor, strokeColor; // Colors del boto (fill / stroke).
        int fillColorOver, fillColorDisabled;  // Colors del boto (actiu / inactiu).
        boolean enabled;  // Estat del botó (actiu / inactiu).

        // Constructor
        public BotonMenu(PApplet p5, String text, float x, float y, float w, float h){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.enabled = true;
            this.fillColor = p5.color(155, 55, 155);
            this.fillColorOver = p5.color(255, 55, 155);
            this.fillColorDisabled = p5.color(150);
            this.strokeColor = p5.color(0);
        }

        // Setters

        public void setEnabled(boolean b){
            this.enabled = b;
        }


        public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
            this.fillColor = cFill;
            this.strokeColor = cStroke;
            this.fillColorOver = cOver;
            this.fillColorDisabled = cDisabled;
        }

        // Dibuixa el botó
        public void display(PApplet p5){
            p5.pushStyle();
            if(!enabled){
                p5.fill(fillColorDisabled);  // Color desabilitat
            }
            else if(mouseOverButton(p5)){
                p5.fill(fillColorOver);      // Color quan ratolí a sobre
            }
            else{
                p5.fill(fillColor);          // Color actiu però ratolí fora
            }
            p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
            p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

            // Text (color, alineació i mida)
            p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
            int marge= 10;
           p5.rect(this.x+marge, this.y+LogoMenuWidth/7, LogoMenuWidth+marge, LogoMenuHeight);
            p5.popStyle();
        }

        // Indica si el cursor està sobre el botó
        public boolean mouseOverButton(PApplet p5){
            return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                    (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
        }

        // Indica si cal posar el cursor a HAND
        public boolean updateHandCursor(PApplet p5){
            return mouseOverButton(p5) && enabled;
        }
    }

