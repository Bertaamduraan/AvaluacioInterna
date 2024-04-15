package AvaluacioInterna;

import processing.core.PApplet;
import processing.core.PImage;

import java.time.Year;

import static AvaluacioInterna.Layout.*;
import static AvaluacioInterna.mides.midaSubtitol;
import static AvaluacioInterna.mides.midaTitol;
import static processing.core.PConstants.*;


public class InterficieGrafica {


    // Enumerado de las Pantallas de la App
    public enum PANTALLA {INICIO, BODEGA, BUSCADOR, CALENDARIO, AÑADIR_VINOS, AÑADIR_EVENTO, VISUALIZAR_cEVENTOS}


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
    BotonConTexto addVinos, addEventos;
    PImage imagenInicial2, imagenInicial3;


    //BOTONES HOME
    Estante e1, e2, e3;
    Vino vinoSeleccionado1= null;
    Vino vinoSeleccionado2= null;
    Vino vinoSeleccionado3= null;
    String[] infoVinoSeleccionado;

    String infoVinosE1 [][];
    String [][] infoVinosE2;
    String [][] infoVinosE3;
    boolean wineSelected= false;
    boolean eventoSelected= false;
    SelectList denominacioDeOrigen;
    String [][] ValoresDorigenHome;
    BotonConTexto OK;
    
    
    //BOTONES CALENDARIO
    CalendarCarrousel c1;
    String [] años= {"2019", "2020", "2021", "2022", "2023", "2024"};
    BotonConTexto irCata, irVino;



    //BOTONES MENÚ
    BotonConTexto bMHome, bMCalendar, bMBuscar, bMVinos, bMCatas, bMVisualizar;
    BotonConFoto bLMenu;
    PImage logoMenu;


    //BOTONES, CAMPOS/AREA DE TEXTO Y ROUNDBUTTONS AÑADIR VINOS
    BotonConTexto BAceptarV, BEliminarV;
    CamposDeTexto TNombre, TBodega, TDenominacion, TVariedad, TAñadaV;
    CamposTextoRect TRPrecio, TRCapacidad, TRUbicacion, TRAño;
    AreaTexto ATVinos;
    SelectList Cocineros;
    String[][] RepreCocineros;
    Selector ColorVino;
    PImage flechaUp, flechaDown;
    Contador contadorVinos;
    PImage imagen;
    String titulo= "";
    BotonConTexto bImagenVino;

    float popWidth= 400;
    float popHeight= 200;
    Confirmar cAddVinos;
    Confirmar cEliminarVinos;
    String claveVinoSeleccionado;


    //BOTONES Y CAMPOS/AREA DE TEXTO AÑADIR EVENTO
    SelectList SLvino1, SLvino2, SLvino3, SLvino4;
    BotonConTexto BAceptarCata, BEliminarC, BAceptarCena;
    AreaTexto ATCatas;
    CalendarioPlus cCata; BotonConFoto bCalendarioCata; PImage fotoCalendario; String dataCalendario= "";
    Confirmar cAddCatas;
    Confirmar cAddCenas;
    Confirmar cEliminarEvento;
    String [][] allVinosCatas;
    CamposTextoRect NombreEvento;
    String [] infoEventoSeleccionado;
    String [] vinosEvento0;
    String claveEventoSeleccionado;


    //SELECTOR BUSCADOR
    Selector sColor;
    Vino vinoSeleccionadoBuscador= null;
    String [] VColor;
    SelectList SLdenominacion, SLbodega;
    String [][] ValoresDO;
    String [][] ValoresBo;
    CamposTextoRect TRCapacidadB, TRPrecioB, TRCantidadB, TRAñadaB;
    Estante Eb;
    String [][] allVinos;
    DataBase db;
    BotonConTexto okB;


    //CALENDARIO VISUALIZAR CATAS Y CENAS
    Calendario cVisualizarEvento;
    String dataCalendarioVisualizarEventos = "";
    Confirmar confirmVisualizarCatas;
    BotonConTexto BOKCatas;


    //Constructor de la interficie gràfica
    public InterficieGrafica(PApplet p5, DataBase db){

        this.db = db;

        pantallaActual= PANTALLA.INICIO;

        int minAño = 2017;
        Year thisYear = Year.now();
        int maxAño =  thisYear.getValue();
        p5.println("MAX: "+maxAño);
        int numAños = maxAño - minAño + 1;
        años = new String[numAños];
        for(int i=0, a=minAño; a<=maxAño; a++, i++){
            años[i] = String.valueOf(a);
            p5.println(años[i]);
        }

        ColoresApp= new Colors(p5); //Constructor de los colores de la App
        FontsApp= new Fonts(p5); // Constructor de las tipografias de la App
        fotoCalendario= p5.loadImage("299092_calendar_icon.png");


        //CAMPOS DE TEXTO INICIO
        UserName = new CamposTextoRect(p5, 650, p5.height/2, 605, "USER NAME: ");
        UserName.setHeightRectSizeLetra(70, 30);
        UserName.setColoresCamposTextoRect(255, 200, 0);

        Contra= new CamposTextoRect(p5, 650, (p5.height/2)+70+(int)marginV, 605, "CONTRASEÑA: ");
        Contra.setHeightRectSizeLetra(70, 30);
        Contra.setColoresCamposTextoRect(255, 200, 0);

        LogIn= new BotonConTexto(p5, 675, (p5.height/2)+140+2*(int)marginV, 550, 60, "LOG IN");
        LogIn.setMidaTextoBoton(30);
        LogIn.setColores(255, 200, 0, 0);

        FotoLogo= p5.loadImage("LOGO_TASTAVINS.JPG");
        imagenInicial2= p5.loadImage("Cofradia_Foto_Prova(1).png");

        //LOGOS MENU, ADD, LOG OUT
        logoMenu= p5.loadImage("LogoMenu.png"); //Cargar foto
        bLMenu= new BotonConFoto(p5, logoMenu, 2*marginH, 2*marginV, LogoMenuWidth, LogoMenuHeight);

        logoAdd= p5.loadImage("Plus_symbol.png");
        ADD= new BotonConFoto(p5, logoAdd, HeadLineWidth+30, 3*marginV, 30, 30);
        addVinos= new BotonConTexto(p5, HeadLineWidth+10, 5*marginV, 140, 30, "AÑADIR VINO");
        addVinos.setMidaTextoBoton(15);
        addVinos.setColores(255, 200,0, 0);
        addEventos = new BotonConTexto(p5, HeadLineWidth+10, 7*marginV, 140, 30, "AÑADIR EVENTO");
        addEventos.setMidaTextoBoton(15);
        addEventos.setColores(255, 200,0, 0 );

        logoLogOut= p5.loadImage("LogoLogOut.png");
        LogOut= new BotonConFoto(p5, logoLogOut, HeadLineWidth+80, 3*marginV, 30, 30);

        //ESTANTERIA Y BOTONES HOME
        infoVinosE1= db.getInfoTablaVinosPorColor( "Tinto");
        infoVinosE2= db.getInfoTablaVinosPorColor("Blanco");
        infoVinosE3= db.getInfoTablaVinosPorColor("Otros");
        setEstanterias(p5, infoVinosE1, infoVinosE2, infoVinosE3);

        ValoresDorigenHome= db.getInfoTablaDO();
        denominacioDeOrigen= new SelectList(p5,ValoresDorigenHome, 1360, 180, 320, 80, "Denominación de Origen", 22);
        denominacioDeOrigen.setSizeText(25);
        OK= new BotonConTexto(p5, 1680, 195, 50, 50, "OK");
        OK.setColores(255, 200, 0, 0);
        OK.setMidaTextoBoton(20);

        //CARROUSEL CALENDARIO
        c1= new CalendarCarrousel(p5, 2*marginH, 3*marginV+HeadLineHeight, FilaCalendarioWidth, FilaCalendarioHeight, años);
        c1.setBotones(p5,"flechaAtrás.png", "flechaAdelante.png");


        //BOTONES MENÚ
        bMHome= new BotonConTexto(p5, marginH*2, 2*marginV+MenuHeight/4, MiniBotonesWidth, MiniBotonesHeight, "BODEGA");
        bMBuscar= new BotonConTexto(p5,marginH*2, 4*marginV+MenuHeight/4 + MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight,"BUSCADOR");
        bMCalendar= new BotonConTexto(p5,marginH*2, 6*marginV+MenuHeight/4 + 2*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "CALENDARIO");
        bMVinos= new BotonConTexto(p5, marginH*2, 8*marginV+MenuHeight/4 + 3*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "AÑADIR VINOS");
        bMCatas= new BotonConTexto(p5, marginH*2, 10*marginV+MenuHeight/4 + 4*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "AÑADIR EVENTO");
        bMVisualizar= new BotonConTexto(p5, marginH*2, 12*marginV+MenuHeight/4 + 5*MiniBotonesHeight, MiniBotonesWidth, MiniBotonesHeight, "VISUALIZAR EVENTOS");

        //CAMPOS DE TEXTO, BOTONES Y ROUND BUTTONS AÑADIR_VINOS
        TNombre= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (4*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Nombre: ");
        TNombre.setTamañoTexto(25);
        TBodega= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (8*marginV+HeadLineHeight+115), (int) columnVinosWidth, "Bodega: ");
        TBodega.setTamañoTexto(25);
        TDenominacion= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (12*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Denominación de origen: ");
        TDenominacion.setTamañoTexto(25);
        TVariedad= new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Variedad: ");
        TAñadaV = new CamposDeTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (16*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Añada: ");
        TAñadaV.setTamañoTexto(25);

        TRPrecio= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (3*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Precio: ");
        TRPrecio.setColoresCamposTextoRect(255, 200, 0);
        TRCapacidad= new CamposTextoRect(p5,(int)(4*marginH+2*columnVinosWidth), (int)(7*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Capacidad: " );
        TRCapacidad.setColoresCamposTextoRect(255, 200,0);
        TRUbicacion= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (11*marginV+HeadLineHeight+115), (int)columnVinosWidth, "Ubicacion: ");
        TRUbicacion.setColoresCamposTextoRect(255, 200,0);
        TRAño= new CamposTextoRect(p5, (int) (4*marginH+2*columnVinosWidth), (int) (marginV+HeadLineHeight+75), (int)columnVinosWidth, "Consumir a partir de: ");
        TRAño.setColoresCamposTextoRect(255, 200, 0);

        ColorVino= new Selector(p5, VColor, (int) (3.5*marginH+columnVinosWidth), (int) (HeadLineHeight+50), 180, 80, 10);
        ColorVino.setTamañoTexto(25);
        ColorVino.setSelectedValue("COLOR VINO");

        flechaUp= p5.loadImage("flechas1.2.png");
        flechaDown= p5.loadImage("flechas1.1.png");
        contadorVinos= new Contador(p5, flechaUp, flechaDown, (int) (2*columnVinosWidth+5*marginH), (int) (16*marginV+HeadLineHeight+115), 250, 30);

        bImagenVino= new BotonConTexto(p5, 3*marginH, 1000, 100, 40, "IMAGEN");

        BAceptarV= new BotonConTexto(p5,7*marginH+columnVinosWidth,3*marginV+HeadLineHeight+columnVinosHeight,150,55, "GUARDAR");
        BAceptarV.setMidaTextoBoton(27);
        BAceptarV.setColores(255, 200, 0, 0);
        BEliminarV= new BotonConTexto(p5, 16*marginH+columnVinosWidth,3*marginV+HeadLineHeight+columnVinosHeight,150,55, "ELIMINAR");
        BEliminarV.setMidaTextoBoton(27);
        BEliminarV.setColores(255, 200,0, 0);


        cAddVinos= new Confirmar(p5, "GUARDAR VINO", "Quieres guardar la información de este vino?", p5.width/2, p5.height/2, popWidth, popHeight);
        cEliminarVinos= new Confirmar(p5, "ELIMINAR VINO", "Quieres eleminar la información de este vino?", p5.width/2, p5.height/2, popWidth, popHeight);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CAMPOS DE TEXTO Y BOTONES AÑADIR_CATAiCENA
        allVinosCatas= db.getAllVinos();
        SLvino1= new SelectList(p5, allVinosCatas, (int) (3*marginH+columnCatasWidth), (int)(6*marginV+HeadLineHeight+90), (int) (columnCatasWidth), 60, "Primer vino", 30);
        SLvino1.setSizeText(35);
        SLvino2= new SelectList(p5,  allVinosCatas, (int) (3*marginH+columnCatasWidth), (int)(11*marginV+HeadLineHeight+90), (int) (columnCatasWidth), 60,  "Segundo vino", 30);
        SLvino2.setSizeText(35);
        SLvino3= new SelectList(p5, allVinosCatas, (int) (3*marginH+columnCatasWidth), (int) (16*marginV+HeadLineHeight+90), (int)(columnCatasWidth), 60, "Tercer vino", 30);
        SLvino3.setSizeText(35);
        SLvino4= new SelectList(p5, allVinosCatas, (int) (3*marginH+columnCatasWidth), (int)(21*marginV+HeadLineHeight+90), (int) (columnCatasWidth), 60, "Cuarto vino", 30);
        SLvino4.setSizeText(35);

        ATCatas= new AreaTexto(p5, 2*(int)marginH, 300, (int)columnCatasWidth, 460, 80, 30, "");

        cCata= new CalendarioPlus(p5, 350, 250, 300,250);
        bCalendarioCata= new BotonConFoto(p5,fotoCalendario, 3*marginH, 150, 100, 100);
        String dataCalendario= "";

        /*RBCatasV= new RadioButton(p5,(int) (5*marginH+columnCatasWidth), (int)(columnCatasHeight-150), 20, "CATA");
        RBCenasV= new RadioButton(p5, (int) (5*marginH+columnCatasWidth), (int) (columnCatasHeight-50), 20, "CENA FINAL DE MES");
        grb= new GrupoRadioButton(2);
        grb.setRadioButtons(RBCatasV, RBCenasV);
        grb.setSeleccionado(2);*/

        RepreCocineros= db.getInfoTablaRepresentantes();
        Cocineros= new SelectList(p5, RepreCocineros, (int) (5*marginH+columnCatasWidth), (int)(columnCatasHeight-120), 500, 80, "REPRESENTANTE GRUPO COCINEROS", 30);
        Cocineros.setSizeText(25);

        ATVinos= new AreaTexto(p5, (int) (3*marginH+columnVinosWidth), (int) (20*marginV+HeadLineHeight+115), (int)columnVinosWidth, 270, 40, 10, "");
        NombreEvento = new CamposTextoRect(p5, (int) (3*marginH+columnCatasWidth), (int)(3*marginV+HeadLineHeight+30), (int) (columnCatasWidth),  "Nombre de la cena o cata: ");
        NombreEvento.setHeightRectSizeLetra(70, 30);
        NombreEvento.setColoresCamposTextoRect(255, 200, 0);

        BAceptarCata = new BotonConTexto(p5,columnVinosWidth-6*marginH,3*marginV+HeadLineHeight+columnVinosHeight,350,55, "GUARDAR COMO CATA");
        BAceptarCata.setMidaTextoBoton(27);
        BAceptarCata.setColores(255, 200, 0, 0);
        BAceptarCena= new BotonConTexto(p5, 2*columnVinosWidth-17*marginH-15, 3*marginV+HeadLineHeight+columnVinosHeight, 350, 55, "GUARDAR COMO CENA");
        BAceptarCena.setMidaTextoBoton(27);
        BAceptarCena.setColores(255, 200, 0, 0);
        BEliminarC= new BotonConTexto(p5, 2*columnVinosWidth+5, 3*marginV+HeadLineHeight+columnVinosHeight,160,55, "ELIMINAR");
        BEliminarC.setMidaTextoBoton(27);
        BEliminarC.setColores(255, 200, 0, 0);

        cAddCatas= new Confirmar(p5, "GUARDAR COMO CATA", "Quieres guardar la información de esta cata?", p5.width/2, p5.height/2, popWidth, popHeight);
        cAddCenas= new Confirmar(p5, "GUARDAR COMO CENA", "Quieres guardar la información de esta cena",  p5.width/2, p5.height/2, popWidth, popHeight);
        cEliminarEvento = new Confirmar (p5, "ELIMINAR CATA", "Quieres eliminar la información de esta cata?", p5.width/2, p5.height/2, popWidth, popHeight);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //SELECTORS Y CAMPOS DE TEXTOS BUSCADOR
        ValoresDO= db.getInfoTablaDO();
        SLdenominacion= new SelectList(p5, ValoresDO, 440, 270, 400, 80, "Denominación de Origen", 25);

        TRAñadaB = new CamposTextoRect(p5,830, 270, 200,  "Añada: ");
        TRAñadaB.setColoresCamposTextoRect(255, 200, 0);
        TRAñadaB.setHeightRectSizeLetra(80, 25);

        ValoresBo= db.getInfoTablaBodega();
        SLbodega= new SelectList(p5, ValoresBo, 1020, 270, 250, 80, "Bodega", 25);

        VColor= db.getTipos();
        sColor= new Selector(p5, VColor, 1270, 270, 130, 80, 10);
        sColor.setSelectedValue("COLOR");
        sColor.setTamañoTexto(25);
//***************************************************************************************************************
        TRPrecioB= new CamposTextoRect(p5, 630, 360, 200, "Precio: ");
        TRPrecioB.setColoresCamposTextoRect(255, 200, 0);
        TRPrecioB.setHeightRectSizeLetra(80, 25);

        TRCapacidadB= new CamposTextoRect(p5, 1030, 360, 250, "Capacidad: ");
        TRCapacidadB.setColoresCamposTextoRect(255, 200, 0);
        TRCapacidadB.setHeightRectSizeLetra(80, 25);

        TRCantidadB= new CamposTextoRect(p5, 820, 360, 220, "Cantidad: ");
        TRCantidadB.setColoresCamposTextoRect(255, 200, 0);
        TRCantidadB.setHeightRectSizeLetra(80, 25);

        Eb= new Estante(p5, "TODOS", 100, 600, 1600, 300, 8);
        Eb.setColor(ColoresApp.getColorAt(0));
        Eb.setButtons(p5, "flechaAtrás.png", "flechaAdelante.png");
        allVinos= db.getInfoTablaVinos();
        Eb.addVinos(allVinos, p5);
        okB= new BotonConTexto(p5, 840, 500, 200,50, "OK");
        okB.setColores(ColoresApp.getColorAt(6), 200, 0, 0);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CALENDARIO VISUALIZAR CATAS
        cVisualizarEvento = new Calendario(p5, 100, 350, 1700, 600);
        BOKCatas= new BotonConTexto(p5, 580, 185, 70, 60, "OK");
        BOKCatas.setColores(255, 200, 0, 0);
        BOKCatas.setMidaTextoBoton(30);
        confirmVisualizarCatas= new Confirmar(p5, "VISUALIZAR EVENTO", "Quieres visualizar este evento?", p5.width/2, p5.height/2, popWidth, popHeight);
    }

    public void setEstanterias(PApplet p5, String[][] infoVinosE1, String[][] infoVinosE2,String[][] infoVinosE3){
        e1 = new Estante(p5, 1, "TINTO",100, 140, 950, 250, 5);
        e1.addVinos(infoVinosE1, p5);
        e1.setColor(ColoresApp.getColorAt(0));
        e1.setButtons(p5,"flechaAtrás.png", "flechaAdelante.png");

        e2= new Estante(p5, 2, "BLANCO", 100, 450, 950, 250, 5);
        e2.addVinos(infoVinosE2, p5);
        /*for(int i=0; i<infoVinosE2.length; i++){
            for(int j=0; j<infoVinosE2[i].length; j++){
                p5.print(infoVinosE2[i][j]+ "\t");
            }
            p5.println();
        }*/
        e2.setColor(ColoresApp.getColorAt(0));
        e2.setButtons(p5, "flechaAtrás.png", "flechaAdelante.png");

        e3= new Estante(p5, 3, "OTROS", 100, 760, 950, 250, 5);
        e3.addVinos(infoVinosE3, p5);
        e3.setColor(ColoresApp.getColorAt(0));
        e3.setButtons(p5, "flechaAtrás.png", "flechaAdelante.png");
    }

    public void setPantallaVinos(PApplet p5){
        TNombre.setSoloTexto("");
        TBodega.setSoloTexto("");
        TDenominacion.setSoloTexto("");
        TAñadaV.setSoloTexto("");

        TRPrecio.setSoloTexto("");
        TRCapacidad.setSoloTexto("");
        TRUbicacion.setSoloTexto("");
        TRAño.setSoloTexto("");

        contadorVinos.valor=0;

        imagen = p5.loadImage("silueta_Vino.png");

        ColorVino.setSelectedValue("COLOR VINO");
    }

    public void setPantallaAñadirEvento(PApplet p5){
        allVinosCatas= db.getAllVinos();
        SLvino1.setTextoInicial("PRIMER VINO");
        SLvino1.TextField.texto= "";

        SLvino2.setTextoInicial("SEGUNDO VINO");
        SLvino2.TextField.texto= "";

        SLvino3.setTextoInicial("TERCER VINO");
        SLvino3.TextField.texto= "";

        SLvino4.setTextoInicial("CUARTO VINO");
        SLvino4.TextField.texto= "";

        ATCatas.lineas[0]= "";  //DESCRPCIÓN
        ATCatas.setTexto(ATCatas.lineas[0]);

        dataCalendario= "";

        RepreCocineros= db.getInfoTablaRepresentantes();
        Cocineros.setTextoInicial("REPRESENTANTE GRUPO COCINEROS");
        Cocineros.TextField.texto= "";

        NombreEvento.setSoloTexto("");

    }

    // PANTALLAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaPantallaInicio(PApplet p5){
        p5.pushStyle();
            p5.background(0);
            p5.imageMode(p5.CENTER);
            p5.image(imagenInicial2, p5.width/2, p5.height-450, 1900, 1450);
            p5.textFont(FontsApp.getFontAt(1));
            p5.textAlign(p5.CENTER, p5.LEFT);
            p5.textSize(180);
            p5.fill(0);
            p5.text("WineKeeper", p5.width/2+50, 350);
            dibujaLogo(p5);
            p5.textFont(FontsApp.getFontAt(2));
            UserName.display(p5);
            Contra.display(p5);
            p5.strokeWeight(2);
            LogIn.display(p5);
        p5.popStyle();
    }


    public void dibujaPantallaHome(PApplet p5){
        p5.background(ColoresApp.getColorAt(5));
        dibujaHeadLine(p5);
        dibujaLogos(p5);
        p5.pushStyle();
            e1.display(p5);
            if(vinoSeleccionado1!=null){
                vinoSeleccionado1.setTextSizeSelected(30);
                vinoSeleccionado1.display(p5, 1350, 300, 400, 600);

                if(vinoSeleccionado1.cursorEncima(p5, 1350, 300, 400, 600)){
                    wineSelected=true;
                }
            }
            e2.display(p5);
            if(vinoSeleccionado2!=null){
                vinoSeleccionado2.setTextSizeSelected(30);
                vinoSeleccionado2.display(p5, 1350, 300, 400, 600);

                if(vinoSeleccionado2.cursorEncima(p5, 1350, 300, 400, 600)){
                    wineSelected=true;
                }
            }
            e3.display(p5);
            if(vinoSeleccionado3!=null){
                vinoSeleccionado3.setTextSizeSelected(30);
                vinoSeleccionado3.display(p5, 1350, 300, 400, 600);

                if(vinoSeleccionado3.cursorEncima(p5, 1350, 300, 400, 600)){
                    wineSelected=true;
                }
            }

            denominacioDeOrigen.display(p5);
            p5.strokeWeight(2);
            OK.display(p5);


            if(OpcionesOpen){
                dibujaOpciones(p5);
            }
            if(MenuOpen){
                dibujaMenu(p5);
            }

        p5.popStyle();
    }


    public void dibujaPantallaBuscador(PApplet p5){
        p5.background(ColoresApp.getColorAt(5));
        dibujaHeadLine(p5);
        dibujaLogos(p5);
        Eb.display(p5);
        if(vinoSeleccionadoBuscador!=null){
                wineSelected=true;
        }
        TRCapacidadB.display(p5);
        TRPrecioB.display(p5);
        TRCantidadB.display(p5);
        TRAñadaB.display(p5);
        SLdenominacion.display(p5);
        sColor.display(p5);
        SLbodega.display(p5);
        okB.display(p5);
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
        c1.display(p5);
        dibujaVinosCalendario(p5);
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
        TAñadaV.display(p5);
        TRPrecio.display(p5);
        TRCapacidad.display(p5);
        TRUbicacion.display(p5);
        TRAño.display(p5);

        BAceptarV.display(p5);
        BEliminarV.display(p5);
        //ATVinos.display(p5);
        ColorVino.display(p5);

        contadorVinos.display(p5);

        if(imagen!=null){
            p5.image(imagen, 2*marginH+20, 2*marginV+HeadLineHeight+20, columnVinosWidth-40, columnVinosHeight-40);
            p5.textSize(14);
            p5.textAlign(RIGHT);
            p5.fill(0);
            p5.textAlign(CENTER, CENTER);
        }

        bImagenVino.setColores(255, 220, 0, 0);
        bImagenVino.setMidaTextoBoton(20);
        bImagenVino.display(p5);
        cAddVinos.display(p5);
        cEliminarVinos.display(p5);

        if(OpcionesOpen){
            dibujaOpciones(p5);
        }
        if(MenuOpen){
            dibujaMenu(p5);
        }
    }

    public void dibujaPantallaAñadirEvento(PApplet p5){
        p5.pushStyle();
            p5.background(ColoresApp.getColorAt(5));
            dibujaHeadLine(p5);
            dibujaLogos(p5);
            dibuja2Columna(p5);
            Cocineros.display(p5);
            SLvino4.display(p5);
            SLvino3.display(p5);
            SLvino2.display(p5);
            SLvino1.display(p5);
            p5.pushStyle();
                p5.textFont(FontsApp.getFirstFont());
                BAceptarCata.display(p5);
                BAceptarCena.display(p5);
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
            cEliminarEvento.display(p5);
            cAddCenas.display(p5);
            NombreEvento.display(p5);

        p5.popStyle();
    }

    public void dibujaVisualizarEventos(PApplet p5){
        p5.pushStyle();
            ptitulo= "VISUALIZAR EVENTO";
            p5.background(ColoresApp.getColorAt(5));
            dibujaHeadLine(p5);
            dibujaLogos(p5);
            p5.fill(255);
            p5.rectMode(CORNER);
            p5.rect(3*marginH+100, 175, 400, 85, 7);

            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.textFont(FontsApp.getFontAt(2));
            p5.textSize(35);
            p5.text(dataCalendarioVisualizarEventos, 13*marginH, 215);
            cVisualizarEvento.display(p5);
            BOKCatas.display(p5);
            confirmVisualizarCatas.display(p5);

            if(OpcionesOpen){
                dibujaOpciones(p5);
            }
            if(MenuOpen){
                dibujaMenu(p5);
            }
        p5.popStyle();
    }

    public void dibujaVisualizarEvento(PApplet p5){
        p5.pushStyle();
            ptitulo= "VISUALIZAR EVENTO";
            dibujaHeadLine(p5);
            String dataEvento= formataFechaEng(dataCalendarioVisualizarEventos);
            infoEventoSeleccionado = db.getInfoEvento(dataEvento);
            dataCalendario= formataFechaEsp(infoEventoSeleccionado[0]); //FECHA EVENTO
            NombreEvento.setSoloTexto(infoEventoSeleccionado[1]); //NOMBRE
            if(infoEventoSeleccionado[2]== "NULL") {
                SelectList Cocineros = new SelectList(p5, RepreCocineros, (int) (5*marginH+columnCatasWidth), (int)(columnCatasHeight-120), 500, 80, "REPRESENTANTE GRUPO COCINEROS", 30);
                Cocineros.setSizeText(60);
                System.out.println("BUENAS");

            } else{
                Cocineros.TextField.texto = infoEventoSeleccionado[2]; //COCINEROS
                Cocineros.t0= "";
            }

            ATCatas.lineas[0]= infoEventoSeleccionado[3];  //DESCRPCIÓN
            ATCatas.setTexto(ATCatas.lineas[0]);

            vinosEvento0= new String[4];
            vinosEvento0 = db.getVinosEvento(dataEvento);
                SLvino1.TextField.texto = vinosEvento0[0]; //VINO 1
                SLvino1.t0= "";
                    SLvino2.TextField.texto = vinosEvento0[1]; //VINO 2
                    SLvino2.t0= "";
                        SLvino3.TextField.texto= vinosEvento0[2]; //VINO 3
                        SLvino3.t0= "";
                            SLvino4.TextField.texto= vinosEvento0[3]; //VINO 4
                            SLvino4.t0= "";

        p5.popStyle();
    }



    //ZONAS DE LA INTERFÍCIE GRÁFICA
    public void dibujaLogo(PApplet p5){
    p5.pushStyle();
        p5.imageMode(CORNER);
        p5.image(FotoLogo, p5.width-300, 30, logoWidth, logoHeight);
    p5.popStyle();
    }

    public void dibujaHeadLine(PApplet p5){
        p5.pushStyle();
            p5.fill(0);
            p5.stroke(0);
            p5.strokeWeight(3);
            p5.line(LogoMenuWidth+4*marginH, HeadLineHeight, HeadLineWidth, HeadLineHeight);
            //TEXTO
            p5.fill(0);
            p5.textFont(FontsApp.getFontAt(0));
            p5.textSize(midaTitol);
            p5.textAlign(p5.LEFT, p5.CENTER);
            if(pantallaActual==PANTALLA.AÑADIR_VINOS || pantallaActual== PANTALLA.AÑADIR_EVENTO){
                p5.text("" + ptitulo, 4 * marginH + LogoMenuWidth, HeadLineHeight - 25);
            }
            else{
                ptitulo = pantallaActual.toString().replace("_", " ");
                ptitulo= ptitulo.replace("i",  " I ");
                p5.text("" + ptitulo, 4 * marginH + LogoMenuWidth, HeadLineHeight - 25);
            }


        p5.popStyle();
    }

    public void dibujaLogos(PApplet p5){
        bLMenu.display(p5);
        ADD.display(p5);
        LogOut.display(p5);
    }

    public void dibujaMenu(PApplet p5){ //Será la del Menú
        p5.pushStyle();
            p5.fill(0xFF083E4A);
            p5.rect(marginH, marginV, MenuWidth, MenuHeight, 10);
            p5.strokeWeight(1);
            dibuja5MiniFilas(p5);
            bLMenu.display(p5);
            //TEXTO
            p5.fill(0);
            p5.textFont(FontsApp.getFirstFont());
            p5.textSize(midaTitol);
            //p5.text("MENÚO", marginH+ MenuWidth/2, (marginV+LogoMenuHeight-20)+ MenuHeight/4);
        p5.popStyle();
    }


    public void dibuja5MiniFilas(PApplet p5){ //Botones dentro del menú
        p5.fill(255);
        p5.textFont(FontsApp.getSecondFont());
        p5.textSize(midaSubtitol);

     //BOTÓN PARA IR A BODEGA
     bMHome.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
     bMHome.setMidaTextoBoton(39);
     bMHome.display(p5);

      //BOTÓN PARA IR AL BUSCADOR
      bMBuscar.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMBuscar.setMidaTextoBoton(39);
      bMBuscar.display(p5);

      //BOTÓN PARA IR AL CALENDARIO
      bMCalendar.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMCalendar.setMidaTextoBoton(38);
      bMCalendar.display(p5);

      //BOTÓN PARA IR AÑADIR VINOS
      bMVinos.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMVinos.setMidaTextoBoton(38);
      bMVinos.display(p5);

      //BOTÓN PARA IR AÑADIR CATAS
      bMCatas.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMCatas.setMidaTextoBoton(38);
      bMCatas.display(p5);

      bMVisualizar.setColores(ColoresApp.getColorAt(8), ColoresApp.getColorAt(9),0, 255);
      bMVisualizar.setMidaTextoBoton(38);
      bMVisualizar.display(p5);

    }


    public void dibuja2Columna(PApplet p5){ //AÑADIR CATAS
        p5.fill(ColoresApp.getColorAt(0));
        p5.rect(2*marginH, 2*marginV+HeadLineHeight, columnCatasWidth, columnCatasHeight);
        p5.rect(3*marginH+columnCatasWidth, 2*marginV+HeadLineHeight, columnCatasWidth, columnCatasHeight);
        //TEXTO
        p5.fill(255);
        p5.textFont(FontsApp.getSecondFont());
        p5.textSize(midaTitol);
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
        addEventos.display(p5);
    }


    public boolean LogInCorrecto(){
        return (UserName.texto.equals("USER NAME: admin")&& Contra.texto.equals("CONTRASEÑA: 234"));
    }


    public void dibujaCalendarioCatas(PApplet p5){
        p5.pushStyle();
        p5.fill(255);
        p5.rectMode(CORNER);
        p5.rect(3*marginH+100, 175, 300, 60, 7);

        p5.fill(0);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.textFont(FontsApp.getFontAt(2));
        p5.textSize(20);
        p5.text(dataCalendario, 11*marginH, 205);
        cCata.display(p5);
        bCalendarioCata.display(p5);
        p5.popStyle();
    }



    public void dibujarPantallaVizualizarVino (String nombreVino, PApplet p5){
        ptitulo= "VISUALIZAR VINO";
        dibujaHeadLine(p5);
        String nombreVinoSeleccionado= nombreVino;
        infoVinoSeleccionado = db.getInfoVino(nombreVinoSeleccionado);
        TNombre.setSoloTexto(nombreVinoSeleccionado);
        TDenominacion.setSoloTexto(infoVinoSeleccionado[1]);
        ColorVino= new Selector(p5, VColor, (int) (3.5*marginH+columnVinosWidth), (int) (HeadLineHeight+50), 180, 80, 10);
        ColorVino.setTamañoTexto(25);
        ColorVino.setSelectedValue(infoVinoSeleccionado[2]);
        TRUbicacion.setSoloTexto (infoVinoSeleccionado[3]);
        titulo= infoVinoSeleccionado[4]; imagen= p5.loadImage(titulo);
        System.out.println(titulo);
        TRCapacidad.setSoloTexto(infoVinoSeleccionado[5]);
        TBodega.setSoloTexto(infoVinoSeleccionado[6]);
        String Añada= getOnlyYear(infoVinoSeleccionado[7]);
        TAñadaV.setSoloTexto(Añada);
        TRPrecio.setSoloTexto(infoVinoSeleccionado[8]);
        contadorVinos.valor= Integer.valueOf(infoVinoSeleccionado[9]);
        String año= getOnlyYear(infoVinoSeleccionado[10]);
        TRAño.setSoloTexto(año);
    }


    public void dibujaVinosCalendario(PApplet p5){
        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.LEFT);
        p5.textFont(FontsApp.getFontAt(3));
        p5.textSize(27);
        for(int i= 0; i<=3; i++) {
            String[][] vinos = db.getAñadaFecha(c1.años[c1.currentYear]);
            for(int j= 0; j<db.getFilasVinoAñada(c1.años[c1.currentYear]); j++) {
                p5.text(vinos[j][0], 3 * marginH, 5 * marginV + HeadLineHeight+(50*j));
                if(vinos[j][1]==null){
                    vinos[j][1]= "0000-00-00";
                }
                vinos[j][1]= getOnlyYear(vinos[j][1]);
                p5.text("Consumir a partir de " + vinos[j][1], FilaCalendarioWidth - 21*marginH, 5 * marginV + HeadLineHeight+(50*j));
            }
        }
    }

    public static String formataFechaEsp (String fechaEntrada){
        String y= fechaEntrada.split("-")[0];
        String m= fechaEntrada.split("-")[1];
        String d= fechaEntrada.split("-")[2];
        return d+"/"+m+"/"+y;
    }

    public static String formataFechaEng(String fechaEntrada){

        String y = fechaEntrada.split("/")[2];
        String m = fechaEntrada.split("/")[1];
        String d = fechaEntrada.split("/")[0];

        return y+"-"+m+"-"+d;
    }

    public String getOnlyYear (String fecha){
        String y= fecha.split("-")[0];
        String m= fecha.split("-")[1];
        String d= fecha.split("-")[2];
        return y;
    }
}
