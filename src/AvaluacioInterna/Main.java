package AvaluacioInterna;

    import processing.core.PApplet;

    import java.io.File;

public class Main extends PApplet{

    // Interfície Gráfica (Pantallas y componentes)
    InterficieGrafica gui;
    DataBase db;
    int n;


    public static void main(String[] args) {
        PApplet.main("AvaluacioInterna.Main", args);
    }

    public void settings(){
        fullScreen();
        smooth(400);
    }

    public void setup(){
        noStroke(); //Sin bordes
        textAlign(CENTER); //Alineación del texto
        textSize (18); //Mida del texto
        db= new DataBase("admin", "12345", "vinoteca");
        db.connect();
        gui= new InterficieGrafica(this, db); //Constructor de la interfície gráfica

    }

    public void draw(){
        //Dibuja la pantalla correspondiente
        switch(gui.pantallaActual){
            case INICIO: gui.dibujaPantallaInicio(this);
            break;

            case BODEGA: gui.dibujaPantallaHome(this);
            break;

            //case MENU: gui.dibujaPantallaMenu(this);
            //break;

            case BUSCADOR: gui.dibujaPantallaBuscador(this);
            break;

            case CALENDARIO: gui.dibujaPantallaCalendario(this);
            break;

            case AÑADIR_VINOS: gui.dibujaPantallaVinos(this);
            break;

            case AÑADIR_CATA: gui.dibujaPantallaCatas(this);
            break;
        }


    }

    public void fileSelected(File selection){
        if(selection== null){
            println ("No se ha seleccionado ningún documento");
        }
        else{
            String rutaImagen= selection.getAbsolutePath();

            gui.imagen= loadImage(rutaImagen); //ACTUALIZAR IMAGEN
            gui.titulo= selection.getName(); //ACTUALIZAR TÍTULO
        }

    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

        if(gui.MenuOpen) {
            if (gui.bMHome.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.BODEGA;
                gui.MenuOpen= false;
            } else if (gui.bMBuscar.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.BUSCADOR;
                gui.MenuOpen= false;
            } else if (gui.bMCalendar.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.CALENDARIO;
                gui.MenuOpen= false;
            } else if (gui.bMVinos.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.AÑADIR_VINOS;
                gui.ptitulo= "AÑADIR VINO";
                gui.MenuOpen= false;
            } else if (gui.bMCatas.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.AÑADIR_CATA;
                gui.MenuOpen= false;
            }
        }

            if(gui.LogOut.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.INICIO;
            }

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.INICIO) {
            gui.UserName.isPressed(this);
            gui.Contra.isPressed(this);
            if (gui.LogIn.cursorEncimaBoton(this)) {
                println(gui.UserName.texto, gui.Contra.texto);
                if (gui.LogInCorrecto()) {
                    gui.pantallaActual = InterficieGrafica.PANTALLA.BODEGA;
                }
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BODEGA){


            gui.denominacioDeOrigen.getTextField().isPressed(this);
            gui.denominacioDeOrigen.buttonPressed(this);

            if(gui.OK.cursorEncimaBoton(this) && gui.denominacioDeOrigen.ValorSelected!= ""){
                String VS= gui.denominacioDeOrigen.ValorSelected;
                print("Quina DO:",VS);
                String [][]infoVinosE1= gui.db.getInfoTablaVinosPorColorDO("Tinto", VS);
                String [][]infoVinosE2= gui.db.getInfoTablaVinosPorColorDO("Blanco", VS);
                String [][] infoVinosE3= gui.db.getInfoTablaVinosPorColorDO("Otros", VS);

                gui.setEstanterias(this, infoVinosE1, infoVinosE2, infoVinosE3);
            }

            if(gui.wineSelected==true){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_VINOS;
                gui.wineSelected= false;
                if(gui.vinoSeleccionado1!= null) {
                    gui.dibujarPantallaVizualizarVino(gui.vinoSeleccionado1.nombre, this);

                }
                if (gui.vinoSeleccionado2!= null){
                    gui.dibujarPantallaVizualizarVino(gui.vinoSeleccionado2.nombre, this);
                }
                if (gui.vinoSeleccionado3!= null){
                    gui.dibujarPantallaVizualizarVino(gui.vinoSeleccionado3.nombre, this);
                }


            }


            if(!gui.e1.checkButtons(this)){
                int nV= gui.e1.checkClickVino(this);
                if(nV!=-1){
                    gui.vinoSeleccionado1= gui.e1.vinos[nV];
                }
                else{
                    gui.vinoSeleccionado1= null;
                }

            }

            if(!gui.e2.checkButtons(this)){
                int nV2= gui.e2.checkClickVino(this);
                if(nV2!=-1){
                    gui.vinoSeleccionado2= gui.e2.vinos[nV2];
                }
                else{
                    gui.vinoSeleccionado2= null;
                }
            }

            if(!gui.e3.checkButtons(this)){
                int nV3= gui.e3.checkClickVino(this);
                if(nV3!=-1){
                    gui.vinoSeleccionado3= gui.e3.vinos[nV3];
                }
                else{
                    gui.vinoSeleccionado3= null;
                }
            }
        }

        if(gui.pantallaActual== InterficieGrafica.PANTALLA.CALENDARIO){
            gui.c1.checkBotones(this);
        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS && !gui.cVinosCena.visible && !gui.cVinosCata.visible && !gui.cVinos.visible) {

            gui.TNombre.isPressed(this);
            gui.TBodega.isPressed(this);
            gui.TDenominacion.isPressed(this);
            gui.TAñadaV.isPressed(this);
            gui.TVariedad.isPressed(this);
            gui.TRPrecio.isPressed(this);
            gui.TRCapacidad.isPressed(this);
            gui.TRUbicacion.isPressed(this);

            gui.TRCapacidadB.isPressed(this);
            gui.TRPrecioB.isPressed(this);

            gui.Cocineros.getTextField().isPressed(this);
            gui.Cocineros.buttonPressed(this);

            gui.ATVinos.isPressed(this);

            if (gui.ColorVino.cursorEncimaBoton(this)) {
                if (!gui.ColorVino.estaPlegado()) {
                    gui.ColorVino.actualizar(this);
                }
                gui.ColorVino.cambio();
            }
            if (gui.RBCenasV.cursorEncima(this)) {
                gui.RBCenasV.cambio();
            } else if (gui.RBCatasV.cursorEncima(this)) {
                gui.RBCatasV.cambio();
            }

            if(gui.bCalendarioVino1.mouseEncimaBoton(this)){
                gui.cVinosCata.visible= !gui.cVinosCata.visible;
            }

            if(gui.bCalendarioVino2.mouseEncimaBoton(this)){
                gui.cVinosCena.visible= !gui.cVinosCena.visible;
            }

            if(gui.bCalendarioVinos.mouseEncimaBoton(this)){
                gui.cVinos.visible= !gui.cVinos.visible;
            }

            gui.contadorVinos.update(this);

            if(gui.bImagenVino.cursorEncimaBoton(this)){
                selectInput("Selecciona una imagen...", "fileSelected");
            }

                    if(gui.BAceptarV.cursorEncimaBoton(this)){
                        gui.cAddVinos.visible= true;
                        gui.cAddVinos.bAceptar.funciona= true;
                        gui.cEliminarVinos.bAceptar.funciona= false;
                    }
                    if(gui.cAddVinos.bAceptar.cursorEncimaBoton(this) && gui.cAddVinos.bAceptar.funciona) {
                        gui.cAddVinos.visible= false;
                        gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                        String color= gui.db.getClaveFromTabla("color", "idColor", "Color", gui.ColorVino.getSelectedValue());
                        String bodega = gui.db.getClaveFromTabla("bodega", "idbodega", "nombreBodega", gui.TBodega.getSoloTexto());
                        String capacidad= gui.db.getClaveFromTabla("capacidad", "idCapacidad", "Capacidad", gui.TRCapacidad.getSoloTexto());
                        String denominacion= gui.db.getClaveFromTabla("denominacion", "idDenominacion", "NombreDEO", gui.TDenominacion.getSoloTexto());

                        if(denominacion== null){
                            println("No existe DEO");
                            String nuevaDenominacion= gui.TDenominacion.getSoloTexto();
                            gui.db.insertDenominacion(nuevaDenominacion);
                            denominacion= gui.db.getClaveFromTabla("denominacion", "idDenominacion", "NombreDEO", nuevaDenominacion);
                            /*gui.db.insertInfoTaulaVino(gui.TNombre.getSoloTexto(), gui.TAñadaV.getSoloTexto(), gui.TRPrecio.getSoloTexto(), gui.TRUbicacion.getSoloTexto(), gui.contadorVinos.getValor(),
                                    color, capacidad, denominacion, bodega);*/

                        }
                        if(bodega== null){
                            println("No existe bodega");
                                String nuevaBodega= gui.TBodega.getSoloTexto();
                                gui.db.insertBodega(nuevaBodega);
                                bodega= gui.db.getClaveFromTabla("bodega", "idbodega", "nombreBodega", nuevaBodega);

                        }

                        gui.db.insertInfoTaulaVino(gui.TNombre.getSoloTexto(), gui.TAñadaV.getSoloTexto(), gui.TRPrecio.getSoloTexto(), gui.TRUbicacion.getSoloTexto(),
                                                    gui.contadorVinos.getValor(), color, capacidad, denominacion, bodega);
                        gui.infoVinosE1= db.getInfoTablaVinosPorColor( "Tinto");
                        gui.infoVinosE2= db.getInfoTablaVinosPorColor("Blanco");
                        gui.infoVinosE3= db.getInfoTablaVinosPorColor("Otros");
                        gui.setEstanterias(this, gui.infoVinosE1, gui.infoVinosE2, gui.infoVinosE3);


                    }
                    else if (gui.cAddVinos.bCancelar.cursorEncimaBoton(this) && gui.cAddVinos.bCancelar.funciona){
                        gui.cAddVinos.visible= false;

                    }

                    if(gui.BEliminarV.cursorEncimaBoton(this)){
                        gui.cEliminarVinos.visible= true;
                        gui.cEliminarVinos.bAceptar.funciona= true;
                    }
                    if(gui.cEliminarVinos.bAceptar.cursorEncimaBoton(this) && gui.cEliminarVinos.bAceptar.funciona){
                        gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                        gui.cAddVinos.visible= false;
                        gui.db.deleteVino(gui.TNombre.getSoloTexto());
                        gui.infoVinosE1= db.getInfoTablaVinosPorColor( "Tinto");
                        gui.infoVinosE2= db.getInfoTablaVinosPorColor("Blanco");
                        gui.infoVinosE3= db.getInfoTablaVinosPorColor("Otros");
                        gui.setEstanterias(this, gui.infoVinosE1, gui.infoVinosE2, gui.infoVinosE3);
                    }
                    else if(gui.cEliminarVinos.bCancelar.cursorEncimaBoton(this) && gui.cEliminarVinos.bCancelar.funciona){
                        gui.cEliminarVinos.visible= false;
                    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
        } else if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS && !gui.cVinosCena.visible && gui.cVinosCata.visible && !gui.cVinos.visible) {
            //CATAS
            gui.cVinosCata.checkBotones(this);

            if (gui.bCalendarioVino1.mouseEncimaBoton(this)) {
                gui.cVinosCata.visible = !gui.cVinosCata.visible;
            }
            if (gui.cVinosCata.bnext.cursorEncimaBoton(this)) {
                gui.cVinosCata.ProximoMes();
            }
            if (gui.cVinosCata.bprev.cursorEncimaBoton(this)) {
                gui.cVinosCata.mesAnterior();
            }
            if (gui.cVinosCata.bAccept.cursorEncimaBoton(this) && gui.cVinosCata.dateSelected) {
                gui.dataCalendario1 = gui.cVinosCata.diaSeleccionado + "/" + gui.cVinosCata.mesSeleccionado + "/" + gui.cVinosCata.añoSeleccionado;
                gui.cVinosCata.visible = false;
            }
        }
        else if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS && gui.cVinosCena.visible && !gui.cVinosCata.visible && !gui.cVinos.visible){
            gui.cVinosCena.checkBotones(this);
            if(gui.bCalendarioVino2.mouseEncimaBoton(this)){
                gui.cVinosCena.visible= !gui.cVinosCena.visible;
            }
            if(gui.cVinosCena.bnext.cursorEncimaBoton(this)){
                gui.cVinosCena.ProximoMes();
            }
            if(gui.cVinosCena.bprev.cursorEncimaBoton(this)){
                gui.cVinosCena.mesAnterior();
            }
            if(gui.cVinosCena.bAccept.cursorEncimaBoton(this) && gui.cVinosCena.dateSelected){
                gui.dataCalendario2= gui.cVinosCena.diaSeleccionado+"/"+gui.cVinosCena.mesSeleccionado+"/"+gui.cVinosCena.añoSeleccionado;
                gui.cVinosCena.visible= false;
            }

        }
        else if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS && !gui.cVinosCena.visible && !gui.cVinosCata.visible && gui.cVinos.visible){
            gui.cVinos.checkBotones(this);
            if(gui.bCalendarioVinos.mouseEncimaBoton(this)){
                gui.cVinos.visible= !gui.cVinos.visible;
            }
            if(gui.cVinos.bnext.cursorEncimaBoton(this)){
                gui.cVinos.ProximoMes();
            }
            if(gui.cVinos.bprev.cursorEncimaBoton(this)){
                gui.cVinos.mesAnterior();
            }
            if(gui.cVinos.bAccept.cursorEncimaBoton(this) && gui.cVinos.dateSelected){
                gui.dataCalendarioVinos= gui.cVinos.diaSeleccionado+"/"+gui.cVinos.mesSeleccionado+"/"+gui.cVinos.añoSeleccionado;
                gui.cVinos.visible= false;
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BUSCADOR) {
            gui.ATVinos.isPressed(this);
            gui.TRCantidadB.isPressed(this);
            gui.TRPrecioB.isPressed(this);
            gui.TRCapacidadB.isPressed(this);
            gui.TRAñadaB.isPressed(this);

            if (gui.sColor.cursorEncimaBoton(this)) {
                if (!gui.sColor.estaPlegado()) {
                    gui.sColor.actualizar(this);
                }
                gui.sColor.cambio();
            }

            gui.SLdenominacion.getTextField().isPressed(this);
            gui.SLdenominacion.buttonPressed(this);
            gui.SLbodega.getTextField().isPressed(this);
            gui.SLbodega.buttonPressed(this);

            if(!gui.Eb.checkButtons(this)){
                int nV= gui.Eb.checkClickVino(this);
                if(nV!=-1){
                    gui.vinoSeleccionadoBuscador= gui.Eb.vinos[nV];
                }
                else{
                    gui.vinoSeleccionadoBuscador= null;
                }

            }

            if(gui.wineSelected==true){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_VINOS;
                gui.ptitulo= "VISUALIZAR VINO";
            }

            if(gui.okB.cursorEncimaBoton(this)){
                gui.allVinos= gui.db.getInfoTablaVinosFiltros(gui.sColor.selectedValue, gui.SLdenominacion.getSelectedValue(), gui.TRAñadaB.getSoloTexto(), gui.TRPrecioB.getSoloTexto(), gui.SLbodega.getSelectedValue(), gui.TRCapacidadB.getSoloTexto(), gui.TRCantidadB.getSoloTexto());
                gui.Eb= new Estante(this, "TODOS", 85, 480, 1310, 250, 8);
                gui.Eb.setColor(gui.ColoresApp.getColorAt(0));
                gui.Eb.setButtons(this, "flechaAtrás.png", "flechaAdelante.png");
                gui.Eb.addVinos(gui.allVinos, this);
            }

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_CATA && !gui.cCata.visible ){

            gui.ATCatas.isPressed(this);

            if(gui.bCalendarioCata.mouseEncimaBoton(this)){
                gui.cCata.visible= !gui.cCata.visible;
            }


            if(gui.BAceptarC.cursorEncimaBoton(this)){
                gui.cAddCatas.visible= true;
            }
            if(gui.cAddCatas.bAceptar.cursorEncimaBoton(this) && gui.cAddCatas.bAceptar.funciona){
                gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                gui.cAddCatas.visible= false;
            }
            else if (gui.cAddCatas.bCancelar.cursorEncimaBoton(this) && gui.cAddCatas.bCancelar.funciona){
                gui.cAddCatas.visible= false;
            }

            if(gui.BEliminarC.cursorEncimaBoton(this)){
                gui.cEliminarCatas.visible= true;
            }
            if(gui.cEliminarCatas.bAceptar.cursorEncimaBoton(this) && gui.cEliminarCatas.bAceptar.funciona){
                gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                gui.cEliminarCatas.visible= false;
            }
            else if (gui.cEliminarCatas.bCancelar.cursorEncimaBoton(this) && gui.cEliminarCatas.bCancelar.funciona){
                gui.cEliminarCatas.visible= false;
            }
        }
        else if (gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_CATA && gui.cCata.visible){
            gui.cCata.checkBotones(this);
            if(gui.bCalendarioCata.mouseEncimaBoton(this)){
                gui.cCata.visible= !gui.cCata.visible;
            }

            if(gui.cCata.bnext.cursorEncimaBoton(this)){
                gui.cCata.ProximoMes();
            }

            if(gui.cCata.bprev.cursorEncimaBoton(this)){
                gui.cCata.mesAnterior();
            }

            if(gui.cCata.bAccept.cursorEncimaBoton(this) && gui.cCata.dateSelected){
                gui.dataCalendario= gui.cCata.diaSeleccionado+"/"+gui.cCata.mesSeleccionado+"/"+gui.cCata.añoSeleccionado;
                gui.cCata.visible= false;
            }
        }

        if(gui.ADD.mouseEncimaBoton(this)){
            gui.OpcionesOpen = !gui.OpcionesOpen;
        }

        if(gui.bLMenu.mouseEncimaBoton(this)){
            gui.MenuOpen= !gui.MenuOpen;
            gui.wineSelected= false;
        }

        if(gui.OpcionesOpen){
            if(gui.addVinos.cursorEncimaBoton(this)){
                gui.ptitulo= "AÑADIR VINO";
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_VINOS;
            }
            if(gui.addCatas.cursorEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_CATA;
            }
        }

        gui.SLvino1.getTextField().isPressed(this);
        gui.SLvino1.buttonPressed(this);

        gui.SLvino2.getTextField().isPressed(this);
        gui.SLvino2.buttonPressed(this);

        gui.SLvino3.getTextField().isPressed(this);
        gui.SLvino3.buttonPressed(this);

        gui.SLvino4.getTextField().isPressed(this);
        gui.SLvino4.buttonPressed(this);



    }

    public void mouseDragged(){
        //println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        //println("MOUSE RELEASED");
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        /*if(key=='0'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.INICIO;
        }*/
        if(key=='1'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.BODEGA;
        }
        /*else if(key=='2'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.MENU;
        }
        else if(key=='3'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.BUSCADOR;
        }
        else if(key=='4'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.CALENDARIO;
        }
        else if(key=='5'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.AÑADIR_VINOS;
        }
        else if(key=='6'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.AÑADIR_CATA;
        }*/

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.INICIO){
            gui.UserName.keyPressed(key, (int) keyCode);
            gui.Contra.keyPressed(key, (int) keyCode);
        }

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BODEGA){
            if(keyCode==LEFT){
                gui.e1.prev();
            }
            else if(keyCode==RIGHT){
                gui.e1.next();
            }

            if (gui.denominacioDeOrigen.getTextField().cursorEncimaCampoTexto(this)) {
                gui.denominacioDeOrigen.getTextField().keyPressed(key, (int) keyCode);
                gui.denominacioDeOrigen.update(this);
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS) {
            gui.TNombre.keyPressed(key, (int) keyCode);
            gui.TBodega.keyPressed(key, (int) keyCode);
            gui.TDenominacion.keyPressed(key, (int) keyCode);
            gui.TAñadaV.keyPressed(key, (int) keyCode);
            gui.TVariedad.keyPressed(key, (int) keyCode);
            gui.TRPrecio.keyPressed(key, (int) keyCode);
            gui.TRCapacidad.keyPressed(key, (int) keyCode);
            gui.TRUbicacion.keyPressed(key, (int) keyCode);
            gui.TRCapacidadB.keyPressed(key, (int)keyCode);
            gui.TRPrecioB.keyPressed(key, (int)keyCode);

            gui.ATVinos.keyPressed(key, (int) keyCode);

            if (gui.Cocineros.getTextField().cursorEncimaCampoTexto(this)) {
                gui.Cocineros.getTextField().keyPressed(key, (int) keyCode);
                gui.Cocineros.update(this);
            }
        }

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_CATA) {
            if (gui.SLvino1.getTextField().cursorEncimaCampoTexto(this)) {
                    gui.SLvino1.getTextField().keyPressed(key, (int) keyCode);
                gui.SLvino1.update(this);
            }

            if (gui.SLvino2.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLvino2.getTextField().keyPressed(key, (int) keyCode);
                gui.SLvino2.update(this);
            }

            if (gui.SLvino3.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLvino3.getTextField().keyPressed(key, (int) keyCode);
                gui.SLvino3.update(this);
            }

            if (gui.SLvino4.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLvino4.getTextField().keyPressed(key, (int) keyCode);
                gui.SLvino4.update(this);
            }

            gui.ATCatas.keyPressed(key, (int) keyCode);
        }


        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BUSCADOR) {
            if (gui.SLdenominacion.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLdenominacion.getTextField().keyPressed(key, (int) keyCode);
                gui.SLdenominacion.update(this);
            }
            if (gui.SLbodega.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLbodega.getTextField().keyPressed(key, (int) keyCode);
                gui.SLbodega.update(this);
            }

            gui.TRCantidadB.keyPressed(key, (int) keyCode);
            gui.TRCapacidadB.keyPressed(key, (int) keyCode);
            gui.TRPrecioB.keyPressed(key, (int) keyCode);
            gui.TRAñadaB.keyPressed(key, (int) keyCode);
        }

    }

}