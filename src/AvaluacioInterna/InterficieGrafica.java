package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static AvaluacioInterna.Layout.*;
import static AvaluacioInterna.mides.midaSubtitol;
import static AvaluacioInterna.mides.midaTitol;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

;

public class InterficieGrafica {


    // Enumerado de las Pantallas de la App
    public enum PANTALLA {INICIO, HOME, MENU, BUSCADOR, CALENDARIO, ADDVINOS, ADDCATAS};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    //Colores y topigrafias de la App
    Colors ColoresApp; Fonts FontsApp;

    //BOTONES MENÚ
    BotonConTexto bMHome, bMCalendar, bMBuscar, bMVinos, bMCatas;
    BotonConFoto bLMenu;
    PImage logoMenu;

    //BOTONES y CAMPOS DE TEXTO ADDVINOS
    CamposDeTexto TNombre, TBodega, TDenominacion, TVariedad, TCosecha;


    //Constructor de la interficie gràfica
    public InterficieGrafica(PApplet p5){
        pantallaActual= PANTALLA.INICIO;

        ColoresApp= new Colors(p5); //Constructor de los colores de la App
        FontsApp= new Fonts(p5); // Constructor de las tipografias de la App

        //Botón Logo Menú
        logoMenu= p5.loadImage("LogoMenu.png"); //Cargar foto
        bLMenu= new BotonConFoto(p5, logoMenu, 2*marginH, 2*marginV, LogoMenuWidth, LogoMenuHeight);


        //BOTONES MENÚ
        bMHome= new BotonConTexto(p5, marginH*2, marginV+MenuHeight/3, MiniBotonesWidth, MiniBotonesHeight, "HOME");
        bMBuscar= new BotonConTexto(p5,marginH*2, 2*marginV+MenuHeight/3 + MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight,"BUSCAR");
        bMCalendar= new BotonConTexto(p5,marginH*2, 3*marginV+MenuHeight/3 + 2*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CALENDARIO");
        bMVinos= new BotonConTexto(p5, marginH*2, 4*marginV+MenuHeight/3 + 3*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "VINOS");
        bMCatas= new BotonConTexto(p5, marginH*2, 5*marginV+MenuHeight/3 + 4*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CATAS");

        //CAMPOS DE TEXTO ADDVINOS
        TNombre= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (2*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Nom: ");
        TBodega= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (5*marginV+HeadLineHeight+115), (int) columnVinosWidth, "Bodega: ");
        TDenominacion= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Denominació de origen: ");
        TVariedad= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Variedad: ");
        TCosecha= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (14*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Cosecha: ");

    }

    // PANTALLAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaPantallaInicio(PApplet p5){

        p5.background(ColoresApp.getThirdColor());
        dibujaLogo(p5);
        dibujaRectanguloCentro(p5);
    }

    public void dibujaPantallaHome(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibujaRectanguloCentro(p5);
    }

    public void dibujaPantallaMenu(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaHeadLine(p5);
        dibujaRectanguloCentro(p5);
        dibujaMenu(p5);
        dibujaLogoMenu(p5);
        dibuja5MiniFilas(p5);
    }

    public void dibujaPantallaBuscador(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaHeadLine(p5);
        dibujaLogoMenu(p5);
        dibuja1Fila(p5);
    }

    public void dibujaPantallaCalendario(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja3Fila(p5);
    }

    public void dibujaPantallaVinos(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja3Columna(p5);
        TNombre.display(p5);
        TBodega.display(p5);
        TDenominacion.display(p5);
        TCosecha.display(p5);
        TVariedad.display(p5);
    }

    public void dibujaPantallaCatas(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja2Columna(p5);
    }



    //ZONAS DE LA INTERFÍCIE GRÁFICA

    public void dibujaLogo(PApplet p5){
        p5.fill(ColoresApp.getColorAt(5));
        p5.rectMode(CORNER);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getFirstFont());
        p5.textSize(midaTitol);
        p5.text("LOGO", marginH+logoWidth/2, marginV+logoHeight/2);
    }

    public void dibujaHeadLine(PApplet p5){
        p5.fill(ColoresApp.getColorAt(3));
        p5.rect(LogoMenuWidth +3*marginH, marginV, HeadLineWidth, HeadLineHeight);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getFirstFont());
        p5.textSize(midaTitol);
        p5.text("PANTALLA " +  pantallaActual + "("+pantallaActual.ordinal() +")",
                2*marginH + LogoMenuWidth + HeadLineWidth/2, marginV + HeadLineHeight/2);
    }

    public void dibujaRectanguloCentro(PApplet p5){
        p5.fill(ColoresApp.getColorAt(4));
        p5.pushStyle();
            p5.rectMode(CENTER);
            p5.rect(p5.width/2, p5.height/2 +marginV*6, CentRectWidth, CentRectHeight);
            //TEXTO
            p5.fill(0);
            p5.textFont(FontsApp.getFirstFont());
            p5.textSize(midaTitol);
            p5.text("PANTALLA "+ pantallaActual+ "(" +pantallaActual.ordinal() +")",
                    p5.width/2, p5.height/2+marginV*5);
        p5.popStyle();

    }

    public void dibujaLogoMenu(PApplet p5){
        bLMenu.display(p5);
    }

    public void dibujaMenu(PApplet p5){ //Será la del Menú
        p5.fill(ColoresApp.getSecondColor());
        p5.rect(marginH, marginV, MenuWidth, MenuHeight);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getFirstFont());
        p5.textSize(midaTitol);
        p5.text("MENÚ", marginH+ MenuWidth/2, (marginV+LogoMenuHeight-20)+ MenuHeight/4);
    }


    public void dibuja5MiniFilas(PApplet p5){ //Botones dentro del menú
        p5.fill(255);
        p5.textFont(FontsApp.getSecondFont());
        p5.textSize(midaSubtitol);

        //BOTÓN PARA IR A HOME
     bMHome.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0);
     bMHome.display(p5);

      //BOTÓN PARA IR AL BUSCADOR
      bMBuscar.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0);
      bMBuscar.display(p5);

      //BOTÓN PARA IR AL CALENDARIO
      bMCalendar.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0);
      bMCalendar.display(p5);

      //BOTÓN PARA IR AÑADIR VINOS
      bMVinos.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0);
      bMVinos.display(p5);

      //BOTÓN PARA IR AÑADIR CATAS
      bMCatas.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0);
      bMCatas.display(p5);

    }

    public void dibuja1Fila(PApplet p5){ //BUSCADOR
        p5.fill(ColoresApp.getColorAt(1));
        p5.rect(marginH, 3*marginV+HeadLineHeight, FilaBuscadorWidth, FilaBuscadorHeight);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getFirstFont());
        p5.textSize(midaTitol);
        p5.text("FILTROS",marginH+FilaBuscadorWidth/2 ,3*marginV+HeadLineHeight+FilaBuscadorHeight/2);

    }

    public void dibuja3Fila(PApplet p5){ //CALENDARIO
        p5.fill(ColoresApp.getColorAt(5));
        p5.rect(2*marginH, 3*marginV+HeadLineHeight, FilaCalendarioWidth, FilaCalendarioHeight);
        p5.rect(2*marginH, 4*marginV+HeadLineHeight+FilaBuscadorHeight, FilaCalendarioWidth, FilaCalendarioHeight);
        p5.rect(2*marginH, 5*marginV+HeadLineHeight+2*FilaBuscadorHeight, FilaCalendarioWidth, FilaCalendarioHeight);


    }

    public void dibuja2Columna(PApplet p5){ //AÑADIR CATAS
        p5.fill(ColoresApp.getColorAt(0));
        p5.rect(marginH, 2*marginV+HeadLineHeight, columnCatasWidth, columnCatasHeight);
        p5.rect(2*marginH+columnCatasWidth, 2*marginV+HeadLineHeight, columnCatasWidth, columnCatasHeight);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getSecondFont());
        p5.textSize(midaTitol);
        p5.text("CALENDARIO Y \n ASISTENTES", marginH+columnCatasWidth/2, 2*marginV+HeadLineHeight+columnCatasHeight/2);
        p5.text("LISTA DE VINOS", 2*marginH+columnCatasWidth+columnCatasWidth/2, 2*marginV+HeadLineHeight+columnCatasHeight/2);
    }

    public void dibuja3Columna(PApplet p5){ //AÑADIR VINO
        p5.fill(ColoresApp.getColorAt(5));
        p5.rect(2*marginH, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
        p5.rect(3*marginH+columnVinosWidth, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
        p5.rect(4*marginH+2*columnVinosWidth, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
    }


}
