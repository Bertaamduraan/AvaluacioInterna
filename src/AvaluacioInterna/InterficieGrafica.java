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

    //CAMPOS DE TEXTO LOG IN
    CamposTextoRect UserName, Contra;
    BotonConTexto LogIn;

    //BOTONES MENÚ
    BotonConTexto bMHome, bMCalendar, bMBuscar, bMVinos, bMCatas;
    BotonConFoto bLMenu;
    PImage logoMenu;

    //BOTONES, CAMPOS/AREA DE TEXTO Y ROUNDBUTTONS ADDVINOS
    BotonConTexto BAceptarV, BEliminarV, BAddV;
    CamposDeTexto TNombre, TBodega, TDenominacion, TVariedad, TCosecha;
    CamposTextoRect TRPrecio, TRCapacidad, TRUbicacion;
    RadioButton RBCenasV, RBCatasV;
    GrupoRadioButton grb;
    AreaTexto ATVinos;
    SelectList Cocineros;
    String[][] COCINEROSva= {{"0", "Grupo Joan"}, {"1", "Grupo Toni"}, {"2", "Grupo Pere"}, {"3", "Grupo Jacinto"}};


    //BOTONES Y CAMPOS/AREA DE TEXTO ADDCATAS
    CamposTextoRect TRvino1, TRvino2, TRvino3, TRvino4;
    BotonConTexto BAceptarC, BEliminarC;
    AreaTexto ATCatas;


    //SELECTOR BUSCADOR
    Selector sColor;
    String [] VColor= {"BLANCO", "TINTO", "ROSADO", "CAVA", "OTROS"};
    SelectList SLdenominacion, SLañada, SLbodega, SLvariedad, SLcenas;
    String [][] ValoresDO= {{"0", "Rioja"}, {"1", "Ribera de Duero"}, {"2", "Priorat"}, {"3", "Rueda"}};
    String [][] ValoresA= {{"0", "2014"}, {"1", "2015"}, {"2", "2016"}, {"3", "2017"}, {"4", "2018"}};
    String [][] ValoresBo= {{"0", "Cero"}, {"1", "Una"}, {"2", "Dos"}, {"3", "Tres"}, {"4", "Cuatro"}};
    String [][] ValoresVa= {{"0", "CERO"},{"1", "UNO"},{"2", "DOS"}, {"3", "TRES"}, {"4", "CUATRO"}};
    String [][] ValoresC= {{"0", "01/02/03"},{"1", "04/05/06"},{"2", "07/08/09"}, {"3", "10/11/12"}, {"4", "14/12/13"}};
    CamposTextoRect TRCapacidadB, TRPrecioB, TRCantidadB;



    //Constructor de la interficie gràfica
    public InterficieGrafica(PApplet p5){
        pantallaActual= PANTALLA.INICIO;

        ColoresApp= new Colors(p5); //Constructor de los colores de la App
        FontsApp= new Fonts(p5); // Constructor de las tipografias de la App

        //CAMPOS DE TEXTO INICIO
        UserName = new CamposTextoRect(p5, 465, 450, 605, "USER NAME: ");
        UserName.setHeightRectSizeLetra(70, 30);
        UserName.setColoresCamposTextoRect(255, 200, 0);

        Contra= new CamposTextoRect(p5, 465, 450+70+(int)marginV, 605, "CONTRASEÑA: ");
        Contra.setHeightRectSizeLetra(70, 30);
        Contra.setColoresCamposTextoRect(255, 200, 0);

        LogIn= new BotonConTexto(p5, 465, 450+140+2*(int)marginV, 605, 70, "LOG IN");
        LogIn.setMidaTextoBoton(30);
        LogIn.setColores(255, 200, 0, 0);

        //Botón Logo Menú
        logoMenu= p5.loadImage("LogoMenu.png"); //Cargar foto
        bLMenu= new BotonConFoto(p5, logoMenu, 2*marginH, 2*marginV, LogoMenuWidth, LogoMenuHeight);


        //BOTONES MENÚ
        bMHome= new BotonConTexto(p5, marginH*2, marginV+MenuHeight/3, MiniBotonesWidth, MiniBotonesHeight, "HOME");
        bMBuscar= new BotonConTexto(p5,marginH*2, 2*marginV+MenuHeight/3 + MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight,"BUSCAR");
        bMCalendar= new BotonConTexto(p5,marginH*2, 3*marginV+MenuHeight/3 + 2*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CALENDARIO");
        bMVinos= new BotonConTexto(p5, marginH*2, 4*marginV+MenuHeight/3 + 3*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "VINOS");
        bMCatas= new BotonConTexto(p5, marginH*2, 5*marginV+MenuHeight/3 + 4*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CATAS");

        //CAMPOS DE TEXTO, BOTONES Y ROUND BUTTONS ADDVINOS
        TNombre= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (2*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Nom: ");
        TBodega= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (5*marginV+HeadLineHeight+115), (int) columnVinosWidth, "Bodega: ");
        TDenominacion= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Denominació de origen: ");
        TVariedad= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Variedad: ");
        TCosecha= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (14*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Añada: ");

        TRPrecio= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (2*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Precio: ");
        TRPrecio.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5), 0);
        TRCapacidad= new CamposTextoRect(p5,(int)(4*marginH+2*columnVinosWidth), (int)(5*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Capacidad: " );
        TRCapacidad.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);
        TRUbicacion= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Ubicacion: ");
        TRUbicacion.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);

        BAceptarV= new BotonConTexto(p5,4*marginH+columnVinosWidth,25*marginV+HeadLineHeight+115,150,55, "GUARDAR" );
        BAceptarV.setMidaTextoBoton(29);
        BAceptarV.setColores(255, ColoresApp.getColorAt(7), 0, 0);
        BEliminarV= new BotonConTexto(p5, 13*marginH+columnVinosWidth,25*marginV+HeadLineHeight+115,150,55, "ELIMINAR");
        BEliminarV.setMidaTextoBoton(29);
        BEliminarV.setColores(255, ColoresApp.getColorAt(7),0, 0);
        BAddV= new BotonConTexto(p5, 22*marginH+columnVinosWidth, 25*marginV+HeadLineHeight+115,150,55, "AÑADIR");
        BAddV.setMidaTextoBoton(29);
        BAddV.setColores(255, ColoresApp.getColorAt(7), 0, 0);

        RBCatasV= new RadioButton(p5,(int) (5*marginH+2*columnVinosWidth), (int)(17*marginV+HeadLineHeight+115), 10, "CATA");
        RBCenasV= new RadioButton(p5, (int)(5*marginH+2*columnVinosWidth), (int) (19*marginV+HeadLineHeight+115), 10, "CENA FINAL DE MES");
        grb= new GrupoRadioButton(2);
        grb.setRadioButtons(RBCatasV, RBCenasV);
        grb.setSeleccionado(2);

        ATVinos= new AreaTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (15.5*marginV+HeadLineHeight+115), (int)columnVinosWidth, 170, 40, 10);

        Cocineros= new SelectList(p5, COCINEROSva, (int) (5.5*marginH+2*columnVinosWidth), (int) (21*marginV+HeadLineHeight+115), 155, 50, "COCINEROS");

        //CAMPOS DE TEXTO Y BOTONES ADDCATAS
        TRvino1= new CamposTextoRect(p5, (int) (2*marginH+columnCatasWidth), (int)(2*marginV+HeadLineHeight+90), (int) (columnCatasWidth), "Primer vino: ");
        TRvino1.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5), 0);
        TRvino1.setHeightRectSizeLetra(60,25);
        TRvino2= new CamposTextoRect(p5,(int) (2*marginH+columnCatasWidth), (int)(7*marginV+HeadLineHeight+90), (int) (columnCatasWidth), "Segundo vino: ");
        TRvino2.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);
        TRvino2.setHeightRectSizeLetra(60,25);
        TRvino3= new CamposTextoRect(p5, (int) (2*marginH+columnCatasWidth), (int) (12*marginV+HeadLineHeight+90), (int)(columnCatasWidth), "Tercer vino: ");
        TRvino3.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);
        TRvino3.setHeightRectSizeLetra(60,25);
        TRvino4= new CamposTextoRect(p5, (int) (2*marginH+columnCatasWidth), (int)(17*marginV+HeadLineHeight+90), (int) (columnCatasWidth), "Cuarto vino: ");
        TRvino4.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);
        TRvino4.setHeightRectSizeLetra(60,25);
        ATCatas= new AreaTexto(p5, 2*(int)marginH, 320, (int)columnCatasWidth-2*(int)marginH, 400, 80, 30);

        BAceptarC= new BotonConTexto(p5,4*marginH+columnVinosWidth,24*marginV+HeadLineHeight+115,160,55, "GUARDAR");
        BAceptarC.setMidaTextoBoton(30);
        BAceptarC.setColores(255, ColoresApp.getColorAt(7), 0, 0);
        BEliminarC= new BotonConTexto(p5, 13*marginH+columnVinosWidth, 24*marginV+HeadLineHeight+115,160,55, "ELIMINAR");
        BEliminarC.setMidaTextoBoton(30);
        BEliminarC.setColores(255, ColoresApp.getColorAt(7), 0, 0);


        //SELCTORS Y CAMPOS DE TEXTOS BUSCADOR
        sColor= new Selector(VColor, 870, 200, 100, 70, 10);
        sColor.setSelectedValue("Color");
        TRCapacidadB= new CamposTextoRect(p5, 970, 200, 200, "Capacidad: ");
        TRCapacidadB.setColoresCamposTextoRect(255, 200, 0);
        TRCapacidadB.setHeightRectSizeLetra(70, 18);
        TRPrecioB= new CamposTextoRect(p5, 430, 280, 170, "Precio: ");
        TRPrecioB.setColoresCamposTextoRect(255, 200, 0);
        TRPrecioB.setHeightRectSizeLetra(70, 18);
        TRCantidadB= new CamposTextoRect(p5, 590, 280, 180, "Cantidad: ");
        TRCantidadB.setColoresCamposTextoRect(255, 200, 0);
        TRCantidadB.setHeightRectSizeLetra(70, 18);

        SLcenas= new SelectList(p5, ValoresC, 920, 280, 150, 70, "Cenas");
        SLvariedad= new SelectList(p5, ValoresVa, 760, 280, 170, 70, "Variedad");
        SLdenominacion= new SelectList(p5, ValoresDO, 340, 200, 250, 70, "Denominación de Origen");
        SLañada= new SelectList(p5, ValoresA, 580, 200, 150, 70, "Añada");
        SLbodega= new SelectList(p5, ValoresBo, 720, 200, 150, 70, "Bodega");



    }

    // PANTALLAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaPantallaInicio(PApplet p5){

        p5.background(ColoresApp.getThirdColor());
        dibujaLogo(p5);
        dibujaRectanguloCentro(p5);
        UserName.display(p5);
        Contra.display(p5);
        LogIn.display(p5);

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
        //dibuja1Fila(p5);
        sColor.display(p5);
        TRCapacidadB.display(p5);
        TRPrecioB.display(p5);
        TRCantidadB.display(p5);
        SLdenominacion.display(p5);
        SLañada.display(p5);
        SLbodega.display(p5);
        SLvariedad.display(p5);
        SLcenas.display(p5);

    }

    public void dibujaPantallaCalendario(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja3Fila(p5);
    }

    public void dibujaPantallaVinos(PApplet p5){
        p5.background(ColoresApp.getColorAt(1));
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja3Columna(p5);
        TNombre.display(p5);
        TBodega.display(p5);
        TDenominacion.display(p5);
        TCosecha.display(p5);
        TVariedad.display(p5);
        TRPrecio.display(p5);
        TRCapacidad.display(p5);
        TRUbicacion.display(p5);
        BAceptarV.display(p5);
        BEliminarV.display(p5);
        BAddV.display(p5);
        RBCatasV.display(p5);
        RBCenasV.display(p5);
        grb.display(p5);
        ATVinos.display(p5);
        Cocineros.display(p5);
    }

    public void dibujaPantallaCatas(PApplet p5){
        p5.background(ColoresApp.getColorAt(1));
        dibujaLogoMenu(p5);
        dibujaHeadLine(p5);
        dibuja2Columna(p5);
        TRvino1.display(p5);
        TRvino2.display(p5);
        TRvino3.display(p5);
        TRvino4.display(p5);
        p5.pushStyle();
            p5.textFont(FontsApp.getFirstFont());
            BAceptarC.display(p5);
            BEliminarC.display(p5);
        p5.popStyle();
        ATCatas.display(p5);
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
                    p5.width/2, marginV*5);
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
     bMHome.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0, 255);
     bMHome.display(p5);

      //BOTÓN PARA IR AL BUSCADOR
      bMBuscar.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0, 255);
      bMBuscar.display(p5);

      //BOTÓN PARA IR AL CALENDARIO
      bMCalendar.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0, 255);
      bMCalendar.display(p5);

      //BOTÓN PARA IR AÑADIR VINOS
      bMVinos.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0, 255);
      bMVinos.display(p5);

      //BOTÓN PARA IR AÑADIR CATAS
      bMCatas.setColores(ColoresApp.getColorAt(0), ColoresApp.getColorAt(6),0, 255);
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
        p5.fill(255);
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

    boolean LogInCorrecto(){
        return (UserName.texto.equals("USER NAME: admin")&& Contra.texto.equals("CONTRASEÑA: 234"));
    }

}
