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
        smooth(10);
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

            case HOME: gui.dibujaPantallaHome(this);
            break;

            case MENU: gui.dibujaPantallaMenu(this);
            break;

            case BUSCADOR: gui.dibujaPantallaBuscador(this);
            break;

            case CALENDARIO: gui.dibujaPantallaCalendario(this);
            break;

            case ADDVINOS: gui.dibujaPantallaVinos(this);
            break;

            case ADDCATAS: gui.dibujaPantallaCatas(this);
            break;
        }
    }


    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){

            if(gui.bMHome.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.HOME;
            }else if(gui.bMBuscar.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.BUSCADOR;
            }else if(gui.bMCalendar.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.CALENDARIO;
            }else if(gui.bMVinos.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.ADDVINOS;
            }else if (gui.bMCatas.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.ADDCATAS;
            }else if(gui.bLMenu.mouseEncimaBoton(this)){
                gui.pantallaActual= InterficieGrafica.PANTALLA.MENU;
            }

        gui.TNombre.isPressed(this);
        gui.TBodega.isPressed(this);
        gui.TDenominacion.isPressed(this);
        gui.TCosecha.isPressed(this);
        gui.TVariedad.isPressed(this);
        gui.TRPrecio.isPressed(this);
        gui.TRCapacidad.isPressed(this);
        gui.TRUbicacion.isPressed(this);

        gui.TRvino1.isPressed(this);
        gui.TRvino2.isPressed(this);
        gui.TRvino3.isPressed(this);
        gui.TRvino4.isPressed(this);

        gui.TRCapacidadB.isPressed(this);
        gui.TRPrecioB.isPressed(this);

        if(gui.RBDenominacionOrigen.cursorEncimaBoton(this)){
            if(!gui.RBDenominacionOrigen.estaPlegado()){
                gui.RBDenominacionOrigen.actualizar(this);
            }
            gui.RBDenominacionOrigen.cambio();
        }

        if(gui.RBAñada.cursorEncimaBoton(this)){
            if(!gui.RBAñada.estaPlegado()){
                gui.RBAñada.actualizar(this);
            }
            gui.RBAñada.cambio();
        }

        if(gui.RBBodega.cursorEncimaBoton(this)){
            if(!gui.RBBodega.estaPlegado()){
                gui.RBBodega.actualizar(this);
            }
            gui.RBBodega.cambio();
        }

        if(gui.sColor.cursorEncimaBoton(this)){
            if(!gui.sColor.estaPlegado()){
                gui.sColor.actualizar(this);
            }
            gui.sColor.cambio();
        }

        if(gui.RBCenasV.cursorEncima(this)){
            gui.RBCenasV.cambio();
        }
        else if(gui.RBCatasV.cursorEncima(this)){
            gui.RBCatasV.cambio();
        }

        gui.ATVinos.isPressed(this);
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.INICIO;
        }
        else if(key=='1'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.HOME;
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
            gui.pantallaActual = InterficieGrafica.PANTALLA.ADDVINOS;
        }
        else if(key=='6'){
            gui.pantallaActual = InterficieGrafica.PANTALLA.ADDCATAS;
        }*/

        gui.TNombre.keyPressed(key, (int) keyCode);
        gui.TBodega.keyPressed(key, (int) keyCode);
        gui.TDenominacion.keyPressed(key, (int) keyCode);
        gui.TCosecha.keyPressed(key, (int) keyCode);
        gui.TVariedad.keyPressed(key, (int) keyCode);
        gui.TRPrecio.keyPressed(key, (int)keyCode);
        gui.TRCapacidad.keyPressed(key, (int) keyCode);
        gui.TRUbicacion.keyPressed(key, (int) keyCode);

        gui.TRvino1.keyPressed(key, (int)keyCode);
        gui.TRvino2.keyPressed(key, (int) keyCode);
        gui.TRvino3.keyPressed(key, (int) keyCode);
        gui.TRvino4.keyPressed(key, (int) keyCode);

        gui.TRCapacidadB.keyPressed(key, (int)keyCode);
        gui.TRPrecioB.keyPressed(key, (int)keyCode);

        gui.ATVinos.keyPressed(key, (int) keyCode);

    }

}