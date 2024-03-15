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

        String info= db.connected ? "OK" : "ERROR";
        fill(0); textSize(28);
        text("Connexió a la BBDD: "+ info, 200, 100);

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
                gui.MenuOpen= false;
            } else if (gui.bMCatas.cursorEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.AÑADIR_CATA;
                gui.MenuOpen= false;
            } /*else if (gui.bLMenu.mouseEncimaBoton(this)) {
                gui.pantallaActual = InterficieGrafica.PANTALLA.MENU;
                gui.MenuOpen= false;
            }*/
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


        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BODEGA){
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
                gui.ptitulo= "VISUALIZAR VINO";
            }
        }

        if(gui.pantallaActual== InterficieGrafica.PANTALLA.CALENDARIO){
            gui.c1.checkBotones(this);
        }



        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS && !gui.cVinosCena.visible && !gui.cVinosCata.visible && !gui.cVinos.visible) {

            gui.TNombre.isPressed(this);
            gui.TBodega.isPressed(this);
            gui.TDenominacion.isPressed(this);
            gui.TCosecha.isPressed(this);
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
                    }
                    if(gui.cAddVinos.bAceptar.cursorEncimaBoton(this) && gui.cAddVinos.bAceptar.funciona) {
                        gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                        gui.cAddVinos.visible= false;
                        String color= gui.db.getClaveFromTabla("color", "idColor", "Color", gui.ColorVino.getSelectedValue());
                        System.out.println(color);

                        String bodega = gui.db.getClaveFromTabla("bodega", "idbodega", "nombreBodega", gui.TBodega.getSoloTexto());
                        println(bodega);
                        String capacidad= gui.db.getClaveFromTabla("capacidad", "idCapacidad", "Capacidad", gui.TRCapacidad.getSoloTexto());
                        println(capacidad);
                        String denominacion= gui.db.getClaveFromTabla("denominacion", "idDenominacion", "NombreDEO", gui.TDenominacion.getSoloTexto());
                        print(denominacion);
                        gui.db.insertInfoTaulaVino(gui.TNombre.getSoloTexto(), gui.TCosecha.getSoloTexto(), gui.TRPrecio.getSoloTexto(), gui.TRUbicacion.getSoloTexto(), gui.contadorVinos.getValor(),
                                                    color, capacidad, denominacion, bodega);

                    }
                    else if (gui.cAddVinos.bCancelar.cursorEncimaBoton(this) && gui.cAddVinos.bCancelar.funciona){
                        gui.cAddVinos.visible= false;
                    }

                    if(gui.BEliminarV.cursorEncimaBoton(this)){
                        gui.cEliminarVinos.visible= true;
                    }
                    if(gui.cEliminarVinos.bAceptar.cursorEncimaBoton(this) && gui.cEliminarVinos.bAceptar.funciona){
                        gui.pantallaActual= InterficieGrafica.PANTALLA.BODEGA;
                        gui.cAddVinos.visible= false;
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


        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BUSCADOR) {
            gui.ATVinos.isPressed(this);
            gui.TRCantidadB.isPressed(this);
            gui.TRPrecioB.isPressed(this);
            gui.TRCapacidadB.isPressed(this);

            if (gui.sColor.cursorEncimaBoton(this)) {
                if (!gui.sColor.estaPlegado()) {
                    gui.sColor.actualizar(this);
                }
                gui.sColor.cambio();
            }
            gui.SLdenominacion.getTextField().isPressed(this);
            gui.SLdenominacion.buttonPressed(this);
            gui.SLañada.getTextField().isPressed(this);
            gui.SLañada.buttonPressed(this);
            gui.SLbodega.getTextField().isPressed(this);
            gui.SLbodega.buttonPressed(this);
            gui.SLvariedad.getTextField().isPressed(this);
            gui.SLvariedad.buttonPressed(this);
            gui.SLcenas.getTextField().isPressed(this);
            gui.SLcenas.buttonPressed(this);
        }

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_CATA && !gui.cCata.visible ){
            gui.TRvino1.isPressed(this);
            gui.TRvino2.isPressed(this);
            gui.TRvino3.isPressed(this);
            gui.TRvino4.isPressed(this);

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
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_VINOS;
            }
            if(gui.addCatas.cursorEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_CATA;
            }
        }



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

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS) {
            gui.TNombre.keyPressed(key, (int) keyCode);
            gui.TBodega.keyPressed(key, (int) keyCode);
            gui.TDenominacion.keyPressed(key, (int) keyCode);
            gui.TCosecha.keyPressed(key, (int) keyCode);
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
            gui.TRvino1.keyPressed(key, (int) keyCode);
            gui.TRvino2.keyPressed(key, (int) keyCode);
            gui.TRvino3.keyPressed(key, (int) keyCode);
            gui.TRvino4.keyPressed(key, (int) keyCode);

            gui.ATCatas.keyPressed(key, (int) keyCode);
        }


        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BUSCADOR) {
            if (gui.SLdenominacion.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLdenominacion.getTextField().keyPressed(key, (int) keyCode);
                gui.SLdenominacion.update(this);
            }
            if (gui.SLañada.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLañada.getTextField().keyPressed(key, (int) keyCode);
                gui.SLañada.update(this);
            }
            if (gui.SLbodega.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLbodega.getTextField().keyPressed(key, (int) keyCode);
                gui.SLbodega.update(this);
            }
            if (gui.SLvariedad.getTextField().cursorEncimaCampoTexto(this)) {
                gui.SLvariedad.getTextField().keyPressed(key, (int) keyCode);
                gui.SLvariedad.update(this);
            }
            if(gui.SLcenas.getTextField().cursorEncimaCampoTexto(this)){
                gui.SLcenas.getTextField().keyPressed(key, (int) keyCode);
                gui.SLcenas.update(this);
            }


            gui.TRCantidadB.keyPressed(key, (int) keyCode);
            gui.TRCapacidadB.keyPressed(key, (int) keyCode);
            gui.TRPrecioB.keyPressed(key, (int) keyCode);
        }


    }


}