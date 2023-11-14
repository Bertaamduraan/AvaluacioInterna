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

    }

}