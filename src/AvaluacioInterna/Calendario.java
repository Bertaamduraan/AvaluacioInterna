package AvaluacioInterna;

import processing.core.PApplet;

import java.util.Calendar;

public class Calendario {
    //Textos que representarán los meses
    String [] months= {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
                            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    //Información del calendario
    int año, mes, dia;
    int numDiasMes, numDiasMesAnt;
    int diaSet, firstDay;

    //Fecha seleccionada
    boolean fechaSeleccionada= false;
    int diaSelect =0, mesSelect =0, añoSelect =0;

    //Calendario actual y del mes anterior
    Calendar cal, cPrev;

    //Botones del calendario
    BotonesDias [] buttons;

    //Dimensiones del calendario
    int x, y, w, h;

    //Botones del calendario
    public BotonConTexto bNext, bPrev, bOK;


    /**
     * CONSTRUCTOR
     * @param p5 Objeto de la clase PApplet para dibujar
     * @param x Posición "x" en la que se crea el Calendario
     * @param y Posición "y" en la que se crea el Calendario
     * @param w Anchura del calendario
     * @param h Altura del calendario
     */
    public Calendario (PApplet p5, int x, int y, int w, int h){
        this.buttons= new BotonesDias[37];

        this.cal= Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        this.año= cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.dia = cal.get(Calendar.DATE);

        this.numDiasMes= cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaSet= cal.get(Calendar.DAY_OF_WEEK);

        if(diaSet==Calendar.SUNDAY){ this.diaSet = 6; }
        else { this.diaSet  = this.diaSet - 2; }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        cPrev = Calendar.getInstance();
        setCalendarioAnt(1, this.mes-2, this.año);

        this.numDiasMesAnt = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.x = x; this.y = y; this.w = w; this.h = h;
        crearCalendario(x, y, w, h);

        bPrev= new BotonConTexto(p5, x+ w/4+50, h+350, 270, 60, "ANTERIOR");
        bPrev.setColores(255, 200, 0, 0);
        bPrev.setMidaTextoBoton(40);

        bNext= new BotonConTexto(p5, x+w/4+360, h+350, 300, 60, "SIGUIENTE");
        bNext.setColores(255, 200, 0, 0);
        bNext.setMidaTextoBoton(40);

        bOK= new BotonConTexto(p5, x+w/4+700, h+350, 90, 60, "OK");
        bOK.setColores(255, 200, 0, 0);
        bOK.setMidaTextoBoton(40);
    }

    //GETTERS
    public boolean EstaFechaSeleccionada(){
        return this.fechaSeleccionada;
    }

    public String getFechaSeleccionada(){
        String date="";
        if(mesSelect<10){
             date= this.diaSelect +"/0"+ this.mesSelect +"/"+ this.añoSelect;
        }
        if(mesSelect>=10){
            date= this.diaSelect +"/"+ this.mesSelect +"/"+ this.añoSelect;
        }
        return date;
    }

    //SETTERS
    public void setCalendario(int d, int m, int a){
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DATE, d);
    }

    public void setCalendarioAnt (int d, int m, int y){
        cPrev.set(Calendar.YEAR, y);
        cPrev.set(Calendar.MONTH, m);
        cPrev.set(Calendar.DATE, d);
    }

    public void setFechaSeleccionada(int d, int m, int y){
        this.diaSelect = d;
        this.mesSelect = m;
        this.añoSelect = y;
    }

    //Se mueve a un mes anterior en el Calendario
    public void prevMonth(){
        this.buttons= new BotonesDias[37];

        this.mes--;
        if(this.mes==0){
            this.mes= 12;
            this.año--;
        }

        setCalendario(this.dia, this.mes-1, this.año);

        this.numDiasMes= cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaSet= cal.get(Calendar.DAY_OF_WEEK);
         if(diaSet==Calendar.SUNDAY){
             this.diaSet = 6;
         }
         else {
             this.diaSet  = this.diaSet - 2;
         }
         cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
         this.firstDay = cal.get(Calendar.DATE);

         setCalendarioAnt(1, this.mes-2, this.año);
         this.numDiasMesAnt= cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

         crearCalendario(x, y, w, h);
    }

    public void crearCalendario(int x, int y, int w, int h){
        float dayWidth= w/7;
        float dayHeight= h/6;
        int numDia =1;
        int f= 0;
        int nb= 0;

        while(numDia<=numDiasMes){
            if(firstDay!=1 && f==0){
                int cPrev=0;
                for(int p= firstDay, c=0; p<=numDiasMesAnt; p++, c++){
                    buttons[nb]= new BotonesDias(x+ c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, p, mes, año);
                    //buttons[nb].setEnabled(false);
                    cPrev++;
                    nb++;
                }

                for(int c=cPrev; c<7; c++){
                    buttons[nb]= new BotonesDias(x+ c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, numDia, mes, año);
                    numDia++;
                    nb++;
                }
                f++;
            }

            else{
                for(int c=0; c<7; c++){
                    buttons[nb] = new BotonesDias(x + c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, numDia, mes, año);
                    numDia++; nb++;
                    if(numDia>numDiasMes){
                        break;
                    }
                }
                f++;
            }
        }

    }

    //Se va a un mes posterior en el Calendario
    public void nextMonth(){
        this.buttons = new BotonesDias[37];

        this.mes ++;
        if(this.mes==13){
            this.mes = 1;
            this.año++;
        }

        setCalendario(this.dia, this.mes-1, this.año);
        this.numDiasMes= cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaSet=  cal.get(Calendar.DAY_OF_WEEK);
        if(diaSet==Calendar.SUNDAY){
            this.diaSet= 6;
        }
        else {
            this.diaSet= this.diaSet-2;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay= cal.get(Calendar.DATE);

        setCalendarioAnt(1, this.mes-2, this.año);

        this.numDiasMesAnt= cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        crearCalendario(x, y, w, h);
    }

    //DIBUJA EL CALENDARIO
    public void display(PApplet p5){
        p5.pushStyle();
            p5.fill(0);
            p5.textSize(70);
            p5.textAlign(p5.CENTER);
            p5.text(months[mes-1] +" "+ año, x+w/2, y-30);
            for(BotonesDias b: buttons){
                if(b!=null){
                    b.display(p5, 150, 40, 20, 3, 0xFFAB3663);
                }
            }

            //DIBUJAR LOS BOTONES
            bPrev.display(p5);
            bNext.display(p5);
            bOK.display(p5);

        p5.popStyle();
    }

    //COMPRUEBA SI PULSAMOS SOBRE LOS BOTONES DEL CALENDARIO
    public void checkButtons(PApplet p5){
        for(BotonesDias b : buttons){
            if((b!=null)&&(b.cursorEncimaBotonDia(p5))){
                boolean prevState = b.selected;
                deselectAll();
                b.setSeleccionado(!prevState);
                if(b.selected){
                    fechaSeleccionada = true;
                    setFechaSeleccionada(b.dia,b.mes,b.año);
                }
                else {
                    fechaSeleccionada = false;
                }
            }
        }
    }

    //DESELECCIONA TODOS LOS BOTONES DEL CALENDARIO
    public void deselectAll(){
        for(BotonesDias b : buttons){
            if(b!=null){
                b.setSeleccionado(false);
            }
        }
    }
}
