package AvaluacioInterna;

    import processing.core.PApplet;

public class Main extends PApplet{

    // Interfície Gráfica (Pantallas y componentes)
    InterficieGrafica gui;


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
        gui= new InterficieGrafica(this); //Constructor de la interfície gráfica
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

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_VINOS) {
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

        }


        if(gui.pantallaActual==InterficieGrafica.PANTALLA.BUSCADOR) {
            gui.ATVinos.isPressed(this);

            gui.SLdenominacion.getTextField().isPressed(this);
            gui.SLdenominacion.buttonPressed(this);
            gui.SLañada.getTextField().isPressed(this);
            gui.SLañada.buttonPressed(this);
            gui.SLbodega.getTextField().isPressed(this);
            gui.SLbodega.buttonPressed(this);
            gui.SLvariedad.getTextField().isPressed(this);
            gui.SLvariedad.buttonPressed(this);
            if (gui.sColor.cursorEncimaBoton(this)) {
                if (!gui.sColor.estaPlegado()) {
                    gui.sColor.actualizar(this);
                }
                gui.sColor.cambio();
            }
        }

        if(gui.pantallaActual==InterficieGrafica.PANTALLA.AÑADIR_CATA){
            gui.TRvino1.isPressed(this);
            gui.TRvino2.isPressed(this);
            gui.TRvino3.isPressed(this);
            gui.TRvino4.isPressed(this);

            gui.ATCatas.isPressed(this);
        }


        if(gui.ADD.mouseEncimaBoton(this)){
            gui.OpcionesOpen = !gui.OpcionesOpen;
        }

        if(gui.bLMenu.mouseEncimaBoton(this)){
            gui.MenuOpen= !gui.MenuOpen;
        }

        if(gui.OpcionesOpen){
            if(gui.addVinos.cursorEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_VINOS;
            }
            if(gui.addCatas.cursorEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.AÑADIR_CATA;
            }
        }


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

    public void mouseDragged(){
        //println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        //println("MOUSE RELEASED");
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.INICIO;
        }
        else if(key=='1'){
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
        }


    }


}