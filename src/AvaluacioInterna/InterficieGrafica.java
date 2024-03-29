package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import static AvaluacioInterna.Layout.*;
import static AvaluacioInterna.mides.midaSubtitol;
import static AvaluacioInterna.mides.midaTitol;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.RIGHT;


public class InterficieGrafica {


    // Enumerado de las Pantallas de la App
    public enum PANTALLA {INICIO, BODEGA, VISUALIZAR_VINOS, BUSCADOR, CALENDARIO, AÑADIR_VINOS, AÑADIR_CATA}


    // Pantalla Actual
    public PANTALLA pantallaActual;

    String ptitulo;


    //Colores y topigrafias de la App
    Colors ColoresApp; Fonts FontsApp;


    //FOTO LOGO COFRADIA
    PImage FotoLogo;


    //CAMPOS DE TEXTO Y BOTONES LOG IN
    CamposTextoRect UserName, Contra;
    BotonConTexto LogIn;

    BotonConFoto ADD, LogOut;
    PImage logoAdd, logoLogOut;
    boolean OpcionesOpen= false;
    boolean MenuOpen= false;
    BotonConTexto addVinos, addCatas;


    //BOTONES HOME
    Estante e1, e2, e3;
    Vino vinoSeleccionado1= null;
    Vino vinoSeleccionado2= null;
    Vino vinoSeleccionado3= null;

    String infoVinosE1 [][]= {
            {"Soldadito Marinero", "Utiel-Rquena", "una", "blanco", "B1", "2014", "vinoSoldadito.jpg" },
    };

    String [][] infoVinosE2;


    String [][] infoVinosE3  = {
            {"Soldadito Marinero", "Utiel-Rquena", "una", "blanco", "B1", "2014", "vinoSoldadito.jpg" },
    };

    boolean wineSelected= false;

    SelectList denominacioDeOrigen;
    String [][] ValoresDorigenHome;

    BotonConTexto OK;
    
    
    //BOTONES CALENDARIO
    CalendarCarrousel c1;
    String [] años= {"AÑADA: 2022", "AÑADA: 2023", "AÑADA: 2024"};


    //BOTONES MENÚ
    BotonConTexto bMHome, bMCalendar, bMBuscar, bMVinos, bMCatas;
    BotonConFoto bLMenu;
    PImage logoMenu;


    //BOTONES, CAMPOS/AREA DE TEXTO Y ROUNDBUTTONS AÑADIR VINOS
    BotonConTexto BAceptarV, BEliminarV;
    CamposDeTexto TNombre, TBodega, TDenominacion, TVariedad, TCosecha;
    CamposTextoRect TRPrecio, TRCapacidad, TRUbicacion;
    RadioButton RBCenasV, RBCatasV;
    GrupoRadioButton grb;
    AreaTexto ATVinos;
    SelectList Cocineros;
    String[][] COCINEROSva= {{"0", "Grupo Joan"}, {"1", "Grupo Toni"}, {"2", "Grupo Pere"}, {"3", "Grupo Jacinto"}};
    Selector ColorVino;
    CalendarioPlus cVinosCata;
    CalendarioPlus cVinosCena;
    BotonConFoto bCalendarioVino1;
    BotonConFoto bCalendarioVino2;
    String dataCalendario1= "";
    String dataCalendario2= "";
    PImage flechaUp, flechaDown;
    Contador contadorVinos;
    PImage imagen;
    String titulo= "";
    BotonConTexto bImagenVino;

    BotonConFoto bCalendarioVinos;
    CalendarioPlus cVinos;
    String dataCalendarioVinos= "";

    float popWidth= 400;
    float popHeight= 200;
    Confirmar cAddVinos;
    Confirmar cEliminarVinos;


    //BOTONES Y CAMPOS/AREA DE TEXTO AÑADIR CATA
    CamposTextoRect TRvino1, TRvino2, TRvino3, TRvino4;
    BotonConTexto BAceptarC, BEliminarC;
    AreaTexto ATCatas;
    CalendarioPlus cCata;
    BotonConFoto bCalendarioCata;
    PImage fotoCalendario;
    String dataCalendario= "";
    Confirmar cAddCatas;
    Confirmar cEliminarCatas;


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

    DataBase db;


    //Constructor de la interficie gràfica
    public InterficieGrafica(PApplet p5, DataBase db){

        this.db = db;

        pantallaActual= PANTALLA.INICIO;

        ColoresApp= new Colors(p5); //Constructor de los colores de la App
        FontsApp= new Fonts(p5); // Constructor de las tipografias de la App
        fotoCalendario= p5.loadImage("299092_calendar_icon.png");


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

        FotoLogo= p5.loadImage("LOGO_TASTAVINS.JPG");

        //LOGOS MENU, ADD, LOG OUT
        logoMenu= p5.loadImage("LogoMenu.png"); //Cargar foto
        bLMenu= new BotonConFoto(p5, logoMenu, 2*marginH, 2*marginV, LogoMenuWidth, LogoMenuHeight);

        logoAdd= p5.loadImage("Plus_symbol.png");
        ADD= new BotonConFoto(p5, logoAdd, 1400, 2*marginV, 30, 30);
        addVinos= new BotonConTexto(p5, 1390, 4*marginV, 100, 30, "AÑADIR VINO");
        addVinos.setMidaTextoBoton(15);
        addVinos.setColores(255, 200,0, 0);
        addCatas= new BotonConTexto(p5, 1390, 6*marginV, 100, 30, "AÑADIR CATA");
        addCatas.setMidaTextoBoton(15);
        addCatas.setColores(255, 200,0, 0 );

        logoLogOut= p5.loadImage("LogoLogOut.png");
        LogOut= new BotonConFoto(p5, logoLogOut, 1450, 2*marginV, 30, 30);

        //ESTANTERIA Y BOTONES HOME
        infoVinosE1= db.getInfoTablaVinosPorColor( "Tinto");
        infoVinosE2= db.getInfoTablaVinosPorColor("Blanco");
        infoVinosE3= db.getInfoTablaVinosPorColor("Otros");
        setEstanterias(p5, infoVinosE1, infoVinosE2, infoVinosE3);

        ValoresDorigenHome= db.getInfoTablaDO();
        denominacioDeOrigen= new SelectList(p5,ValoresDorigenHome, 1100, 150, 250, 50, "Denominación de Origen");
        OK= new BotonConTexto(p5, 1350, 150, 50, 50, "OK");
        OK.setColores(255, 200, 0, 0);
        OK.setMidaTextoBoton(20);

        //CARROUSEL CALENDARIO
        c1= new CalendarCarrousel(p5, 2*marginH, 3*marginV+HeadLineHeight, FilaCalendarioWidth, FilaCalendarioHeight, años);
        c1.setBotones(p5,"flechaAtrás.png", "flechaAdelante.png");



        //BOTONES MENÚ
        bMHome= new BotonConTexto(p5, marginH*2, marginV+MenuHeight/3, MiniBotonesWidth, MiniBotonesHeight, "BODEGA");
        bMBuscar= new BotonConTexto(p5,marginH*2, 2*marginV+MenuHeight/3 + MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight,"BUSCADOR");
        bMCalendar= new BotonConTexto(p5,marginH*2, 3*marginV+MenuHeight/3 + 2*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CALENDARIO");
        bMVinos= new BotonConTexto(p5, marginH*2, 4*marginV+MenuHeight/3 + 3*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "AÑADIR VINOS");
        bMCatas= new BotonConTexto(p5, marginH*2, 5*marginV+MenuHeight/3 + 4*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "AÑADIR CATAS");

        //CAMPOS DE TEXTO, BOTONES Y ROUND BUTTONS AÑADIR_VINOS
        TNombre= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (2*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Nom: ");
        TBodega= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (5*marginV+HeadLineHeight+115), (int) columnVinosWidth, "Bodega: ");
        TDenominacion= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Denominació de origen: ");
        TVariedad= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Variedad: ");
        TCosecha= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Añada: ");

        TRPrecio= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (2*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Precio: ");
        TRPrecio.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5), 0);
        TRCapacidad= new CamposTextoRect(p5,(int)(4*marginH+2*columnVinosWidth), (int)(5*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Capacidad: " );
        TRCapacidad.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);
        TRUbicacion= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Ubicacion: ");
        TRUbicacion.setColoresCamposTextoRect(255, ColoresApp.getColorAt(5),0);

        BAceptarV= new BotonConTexto(p5,7*marginH+columnVinosWidth,29*marginV+HeadLineHeight+115,150,55, "GUARDAR");
        BAceptarV.setMidaTextoBoton(29);
        BAceptarV.setColores(255, 200, 0, 0);
        BEliminarV= new BotonConTexto(p5, 16*marginH+columnVinosWidth,29*marginV+HeadLineHeight+115,150,55, "ELIMINAR");
        BEliminarV.setMidaTextoBoton(29);
        BEliminarV.setColores(255, 200,0, 0);

        RBCatasV= new RadioButton(p5,(int) (5*marginH+2*columnVinosWidth), (int)(17*marginV+HeadLineHeight+115), 10, "CATA");
        RBCenasV= new RadioButton(p5, (int)(5*marginH+2*columnVinosWidth), (int) (19*marginV+HeadLineHeight+115), 10, "CENA FINAL DE MES");
        grb= new GrupoRadioButton(2);
        grb.setRadioButtons(RBCatasV, RBCenasV);
        grb.setSeleccionado(2);

        ATVinos= new AreaTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (15.5*marginV+HeadLineHeight+115), (int)columnVinosWidth, 170, 40, 10);

        Cocineros= new SelectList(p5, COCINEROSva, (int) (5.5*marginH+2*columnVinosWidth), (int) (21*marginV+HeadLineHeight+115), 155, 50, "COCINEROS");
        ColorVino= new Selector(p5, VColor, (int) (3.5*marginH+columnVinosWidth), (int) (HeadLineHeight+50), 150, 60, 10);
        ColorVino.setSelectedValue("COLOR VINO");

        cVinosCata= new CalendarioPlus(p5, 700, 300, 300, 250);
        bCalendarioVino1= new BotonConFoto(p5, fotoCalendario, (int) (8.6*marginH+2*columnVinosWidth), (int)(16.5*marginV+HeadLineHeight+105), 40, 40);

        cVinosCena= new CalendarioPlus(p5, 700, 600, 300, 250);
        bCalendarioVino2= new BotonConFoto(p5, fotoCalendario, (int) (14*marginH+2*columnVinosWidth), (int)(18.5*marginV+HeadLineHeight+105), 40, 40);


        flechaUp= p5.loadImage("flechas1.2.png");
        flechaDown= p5.loadImage("flechas1.1.png");
        contadorVinos= new Contador(flechaUp, flechaDown, (int) (2*columnVinosWidth+5*marginH), 430, 250, 30);

        bImagenVino= new BotonConTexto(p5, 3*marginH, 9*HeadLineHeight+20, 80, 30, "IMAGEN");

        bCalendarioVinos= new BotonConFoto(p5, fotoCalendario, (int) (5*marginH+2*columnVinosWidth), HeadLineHeight+3*marginV, 80, 80);
        cVinos= new CalendarioPlus(p5, 700, 300, 300, 250);

        cAddVinos= new Confirmar(p5, "GUARDAR VINO", "Quieres guardar la información de este vino?", p5.width/2, p5.height/2, popWidth, popHeight);
        cEliminarVinos= new Confirmar(p5, "ELIMINAR VINO", "Quieres eleminar la información de este vino?", p5.width/2, p5.height/2, popWidth, popHeight);

        //CAMPOS DE TEXTO Y BOTONES AÑADIR_CATA
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
        ATCatas= new AreaTexto(p5, 2*(int)marginH, 260, (int)columnCatasWidth-2*(int)marginH, 460, 80, 30);

        BAceptarC= new BotonConTexto(p5,4*marginH+columnVinosWidth,28*marginV+HeadLineHeight+115,160,55, "GUARDAR");
        BAceptarC.setMidaTextoBoton(30);
        BAceptarC.setColores(255, ColoresApp.getColorAt(7), 0, 0);
        BEliminarC= new BotonConTexto(p5, 13*marginH+columnVinosWidth, 28*marginV+HeadLineHeight+115,160,55, "ELIMINAR");
        BEliminarC.setMidaTextoBoton(30);
        BEliminarC.setColores(255, ColoresApp.getColorAt(7), 0, 0);

        cCata= new CalendarioPlus(p5, 350, 250, 300,250);
        bCalendarioCata= new BotonConFoto(p5,fotoCalendario, 2*marginH, 150, 80, 80);

        cAddCatas= new Confirmar(p5, "GUARDAR CATA", "Quieres guardar la información de esta cata?", p5.width/2, p5.height/2, popWidth, popHeight);
        cEliminarCatas= new Confirmar (p5, "ELIMINAR CATA", "Quieres eliminar la información de esta cata?", p5.width/2, p5.height/2, popWidth, popHeight);



        //SELECTORS Y CAMPOS DE TEXTOS BUSCADOR
        sColor= new Selector(p5, VColor, 870, 200, 100, 70, 10);
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

    void setEstanterias(PApplet p5, String[][] infoVinosE1, String[][] infoVinosE2,String[][] infoVinosE3){
        //infoVinosE1= db.getInfoTablaVinosPorColor( "Tinto");
        e1 = new Estante(1, "TINTO",100, 150, 800, 200, 5);
        e1.addVinos(infoVinosE1, p5);
        e1.setColor(ColoresApp.getColorAt(0));
        e1.setButtons(p5,"flechaAtrás.png", "flechaAdelante.png");

        //infoVinosE2= db.getInfoTablaVinosPorColor(cV);
        e2= new Estante(2, "BLANCO", 100, 380, 800, 200, 5);
        e2.addVinos(infoVinosE2, p5);
        e2.setColor(ColoresApp.getColorAt(0));
        e2.setButtons(p5, "flechaAtrás.png", "flechaAdelante.png");

        //infoVinosE3= db.getInfoTablaVinosPorColor("Otros");
        e3= new Estante(3, "OTROS", 100, 610, 800, 200, 5);
        e3.addVinos(infoVinosE3, p5);
        e3.setColor(ColoresApp.getColorAt(0));
        e3.setButtons(p5, "flechaAtrás.png", "flechaAdelante.png");
    }

    // PANTALLAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaPantallaInicio(PApplet p5){
        p5.background(0);
        dibujaLogo(p5);
        dibujaRectanguloCentro(p5);
        p5.textFont(FontsApp.getFirstFont());
        UserName.display(p5);
        Contra.display(p5);
        LogIn.display(p5);
    }


    public void dibujaPantallaHome(PApplet p5){

        p5.background(ColoresApp.getColorAt(5));
        //dibujaRectanguloCentro(p5);
        dibujaHeadLine(p5);
        dibujaLogos(p5);
    p5.pushStyle();
        e1.display(p5);
        if(vinoSeleccionado1!=null){
            vinoSeleccionado1.setTextSizeSelected(20);
            vinoSeleccionado1.display(p5, 1100, 300, 300, 500);

            if(vinoSeleccionado1.cursorEncima(p5, 1100, 300, 300, 500)){
                wineSelected=true;
            }
        }
        e2.display(p5);
        if(vinoSeleccionado2!=null){
            vinoSeleccionado2.setTextSizeSelected(20);
            vinoSeleccionado2.display(p5, 1100, 300, 300, 500);

            if(vinoSeleccionado2.cursorEncima(p5, 1100, 300, 300, 500)){
                wineSelected=true;
            }

        }

        e3.display(p5);
        if(vinoSeleccionado3!=null){
            vinoSeleccionado3.setTextSizeSelected(20);
            vinoSeleccionado3.display(p5, 1100, 300, 300, 500);

            if(vinoSeleccionado3.cursorEncima(p5, 1100, 300, 300, 500)){
                wineSelected=true;
            }
        }

        denominacioDeOrigen.display(p5);
        OK.display(p5);


        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }
    p5.popStyle();
    }

    /*public void dibujaPantallaMenu(PApplet p5){
        p5.background(ColoresApp.getThirdColor());
        dibujaHeadLine(p5);
        dibujaRectanguloCentro(p5);
        dibujaMenu(p5);
        dibujaLogos(p5);
        dibuja5MiniFilas(p5);
        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }
    }*/

    public void dibujaPantallaBuscador(PApplet p5){
        p5.background(ColoresApp.getColorAt(5));
        dibujaHeadLine(p5);
        dibujaLogos(p5);
        //dibuja1Fila(p5);
        TRCapacidadB.display(p5);
        TRPrecioB.display(p5);
        TRCantidadB.display(p5);
        SLdenominacion.display(p5);
        SLañada.display(p5);
        SLvariedad.display(p5);
        SLcenas.display(p5);
        sColor.display(p5);
        SLbodega.display(p5);
        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }

    }

    public void dibujaPantallaCalendario(PApplet p5){
        p5.background(ColoresApp.getColorAt(5));
        dibujaHeadLine(p5);
        dibujaLogos(p5);
        dibuja3Fila(p5);
        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }
    }

    public void dibujaPantallaVinos(PApplet p5){
        p5.background(ColoresApp.getColorAt(5));
        dibujaHeadLine(p5);
        dibujaLogos(p5);
        dibuja3Columna(p5);
        TNombre.display(p5);
        TBodega.display(p5);
        TDenominacion.display(p5);
        TCosecha.display(p5);
        //TVariedad.display(p5);
        TRPrecio.display(p5);
        TRCapacidad.display(p5);
        TRUbicacion.display(p5);
        BAceptarV.display(p5);
        BEliminarV.display(p5);
        RBCatasV.display(p5);
        RBCenasV.display(p5);
        grb.display(p5);
        ATVinos.display(p5);
        Cocineros.display(p5);
        ColorVino.display(p5);
        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }
        dibujaCalendarioVinos(p5);
        dibujaCalendarioVinos2(p5);
        dibujaCalendarioVINOS(p5);
        contadorVinos.display(p5);

        if(imagen!=null){
            p5.image(imagen, 2*marginH+20, 2*marginV+HeadLineHeight+20, columnVinosWidth-40, columnVinosHeight-40);
            p5.textSize(14);
            p5.textAlign(RIGHT);
            p5.fill(0);
            p5.textAlign(CENTER, CENTER);
            p5.text(titulo, 10*marginH,HeadLineHeight*9+35);
        }

        bImagenVino.setColores(255, 220, 0, 0);
        bImagenVino.setMidaTextoBoton(14);
        bImagenVino.display(p5);
        cAddVinos.display(p5);
        cEliminarVinos.display(p5);
    }

    public void dibujaPantallaCatas(PApplet p5){
        p5.pushStyle();
            p5.background(ColoresApp.getColorAt(5));
            dibujaHeadLine(p5);
            dibujaLogos(p5);
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
            dibujaCalendarioCatas(p5);
            if(OpcionesOpen){
                dibujaOpciones(p5);
            }
            if(MenuOpen){
                dibujaMenu(p5);
            }
        cAddCatas.display(p5);
        cEliminarCatas.display(p5);
        p5.popStyle();
    }



    //ZONAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaLogo(PApplet p5){
    p5.pushStyle();
        p5.imageMode(CENTER);
        p5.image(FotoLogo, p5.width/2, p5.height/2, logoWidth, logoHeight);
    p5.popStyle();
    }

    public void dibujaHeadLine(PApplet p5){
        //p5.fill(ColoresApp.getColorAt(3));
        //p5.rect(LogoMenuWidth +3*marginH, marginV, HeadLineWidth, HeadLineHeight);
        p5.pushStyle();
            p5.fill(0);
            p5.stroke(0);
            p5.strokeWeight(3);
            p5.line(LogoMenuWidth+4*marginH, HeadLineHeight, HeadLineWidth, HeadLineHeight);
            //TEXTO
            p5.fill(0);
            p5.textFont(FontsApp.getFirstFont());
            p5.textSize(midaTitol);
            p5.textAlign(p5.LEFT, p5.CENTER);
            if(pantallaActual==PANTALLA.AÑADIR_VINOS && wineSelected==true){
                p5.text("" + ptitulo, 4 * marginH + LogoMenuWidth, HeadLineHeight - 25);
            }
            else{
                ptitulo = pantallaActual.toString().replace("_", " ");
                p5.text("" + ptitulo, 4 * marginH + LogoMenuWidth, HeadLineHeight - 25);
            }


        p5.popStyle();
    }

    public void dibujaRectanguloCentro(PApplet p5){
        p5.fill(ColoresApp.getColorAt(0), 90);
        p5.pushStyle();
            p5.rectMode(CENTER);
            p5.rect(p5.width/2, p5.height/2 +marginV*6, CentRectWidth+130, CentRectHeight-150, 10);
            //TEXTO
            p5.fill(0);
            p5.textFont(FontsApp.getFirstFont());
            p5.textSize(midaTitol);
            //p5.text("PANTALLA "+ pantallaActual+ "(" +pantallaActual.ordinal() +")", p5.width/2, marginV*5);
        p5.popStyle();

    }

    public void dibujaLogos(PApplet p5){
        bLMenu.display(p5);
        ADD.display(p5);
        LogOut.display(p5);
    }

    public void dibujaMenu(PApplet p5){ //Será la del Menú
        p5.fill(0xFF083E4A);
        p5.rect(marginH, marginV, MenuWidth, MenuHeight, 10);
        dibuja5MiniFilas(p5);
        bLMenu.display(p5);
        //TEXTO
        p5.fill(0);
        p5.textFont(FontsApp.getFirstFont());
        p5.textSize(midaTitol);
        //p5.text("MENÚ", marginH+ MenuWidth/2, (marginV+LogoMenuHeight-20)+ MenuHeight/4);
    }


    public void dibuja5MiniFilas(PApplet p5){ //Botones dentro del menú
        p5.fill(255);
        p5.textFont(FontsApp.getSecondFont());
        p5.textSize(midaSubtitol);

        //BOTÓN PARA IR A BODEGA
     bMHome.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
     bMHome.display(p5);

      //BOTÓN PARA IR AL BUSCADOR
      bMBuscar.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMBuscar.display(p5);

      //BOTÓN PARA IR AL CALENDARIO
      bMCalendar.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMCalendar.display(p5);

      //BOTÓN PARA IR AÑADIR VINOS
      bMVinos.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMVinos.display(p5);

      //BOTÓN PARA IR AÑADIR CATAS
      bMCatas.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
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
        /*p5.fill(ColoresApp.getColorAt(3));
        p5.rect(2*marginH, 3*marginV+HeadLineHeight, FilaCalendarioWidth, FilaCalendarioHeight);
        p5.rect(2*marginH, 4*marginV+HeadLineHeight+FilaBuscadorHeight, FilaCalendarioWidth, FilaCalendarioHeight);
        p5.rect(2*marginH, 5*marginV+HeadLineHeight+2*FilaBuscadorHeight, FilaCalendarioWidth, FilaCalendarioHeight);*/

        c1.display(p5);
        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.LEFT);
        p5.textSize(24);
        p5.text("Soldadito marinero", 3*marginH, 5*marginV+HeadLineHeight);
        p5.text("hasta 2030", FilaCalendarioWidth-5*marginH, 5*marginV+HeadLineHeight);


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
        p5.fill(ColoresApp.getColorAt(0));
        p5.rect(2*marginH, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
        p5.rect(3*marginH+columnVinosWidth, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
        p5.rect(4*marginH+2*columnVinosWidth, 2*marginV+HeadLineHeight, columnVinosWidth, columnVinosHeight);
    }

    public void dibujaOpciones(PApplet p5){
        p5.textFont(FontsApp.getFirstFont());
        addVinos.display(p5);
        addCatas.display(p5);
    }


    public boolean LogInCorrecto(){
        return (UserName.texto.equals("USER NAME: admin")&& Contra.texto.equals("CONTRASEÑA: 234"));
    }


    public void dibujaCalendarioCatas(PApplet p5){
        p5.pushStyle();
        p5.fill(255);
        p5.rectMode(p5.CORNER);
        p5.rect(2*marginH+80, 170, 300, 55, 7);

        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.textFont(FontsApp.getFontAt(2));
        p5.textSize(20);
        p5.text(dataCalendario, 2*marginH+90, 170+25);
        cCata.display(p5);
        bCalendarioCata.display(p5);
        p5.popStyle();
    }


    public void dibujaCalendarioVinos(PApplet p5){ //CALENDARIO CATAS
        p5.pushStyle();
            p5.fill(255);
            p5.rectMode(p5.CORNER);
            p5.rect((int)(11*marginH+2*columnVinosWidth), (int)(16.8*marginV+HeadLineHeight+105), 100, 30, 7); //CUADRADO DE LAS CATAS

            p5.fill(0);
            p5.textAlign(p5.LEFT, p5.CENTER);
            p5.textSize(12);
            p5.text(dataCalendario1, (int)(11*marginH+2*columnVinosWidth+10), (int)(17.5*marginV+HeadLineHeight+105));

        bCalendarioVino1.display(p5);
        cVinosCata.display(p5);
        p5.popStyle();
    }
    public void dibujaCalendarioVinos2(PApplet p5){ //CALENDARIO CENAS DE FINAL DE MES
        p5.pushStyle();
        p5.fill(255);
        p5.rectMode(p5.CORNER);
        p5.rect((int)(16.3*marginH+2*columnVinosWidth), (int)(18.8*marginV+HeadLineHeight+105), 100, 30, 7); //CUADRADO DE LAS CENAS DE FINAL DE MES

        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.textSize(12);
        p5.text(dataCalendario2, (int)(16.3*marginH+2*columnVinosWidth+10), (int)(19.5*marginV+HeadLineHeight+105));

        bCalendarioVino2.display(p5);
        cVinosCena.display(p5);
        p5.popStyle();
    }

    public void dibujaCalendarioVINOS(PApplet p5){
        p5.pushStyle();
        p5.fill(255);
        p5.rectMode(p5.CORNER);
        p5.rect(9*marginH+2*columnVinosWidth, HeadLineHeight+4*marginV, 250, 50, 7);

        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(30);
        p5.text(dataCalendarioVinos, (int)(9*marginH+2*columnVinosWidth+10), (int)(HeadLineHeight+5*marginV+15));

        bCalendarioVinos.display(p5);
        cVinos.display(p5);
        p5.popStyle();
    }


}
