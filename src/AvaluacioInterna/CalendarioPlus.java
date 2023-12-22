package AvaluacioInterna;

import processing.core.PApplet;

import java.util.Calendar;

public class CalendarioPlus {
    String [] meses= {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                        "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};

    //INFORMACIÓN DEL CALENDARIO
    int año, mes, dia;
    int numeroDiasMes, numeroDiasMesAnt;
    int diaDelaSem, primerDia;

    //SELECCIONADO
    boolean dateSelected=false;
    int diaSeleccionado=0, mesSeleccionado=0, añoSeleccionado=0;


    //CALENDARIO ACTUAL Y DEL MES ANTERIOR
    Calendar calendario, CalendarioPrevio;

    //BOTONES DEL CALENDARIO
    BotonesDias[] botones;
    BotonConTexto bnext, bprev, bAccept;

    //DIMENSIONES DEL CALENDARIO
    int x, y, w, h;

    //VISIBILIDAD DEL CALENDARIO
    boolean visible = false;

    CalendarioPlus(PApplet p5, int x, int y, int w, int h){
        this.botones= new BotonesDias[37];
        calendario.set(Calendar.DAY_OF_MONTH, 1);

        this.año= calendario.get(Calendar.YEAR);
        this.mes= calendario.get(Calendar.MONTH)+1;
        this.dia= calendario.get(Calendar.DATE);

        this.numeroDiasMes= calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaDelaSem= calendario.get(Calendar.DAY_OF_WEEK);
        if(diaDelaSem==Calendar.SUNDAY){
            this.diaDelaSem=6;
        }else{
            this.diaDelaSem=this.diaDelaSem -2;
        }

        calendario.set(Calendar.DAY_OF_WEEK, calendario.getFirstDayOfWeek());
        this.primerDia= calendario.get(Calendar.DATE);

        CalendarioPrevio= Calendar.getInstance();
        setCalendarioPrevio(1, this.mes-2, this.año);


        this.numeroDiasMesAnt= CalendarioPrevio.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.x= x;
        this.y= y;
        this.w= w;
        this.h= h;
        crearCalendario(x, y, w, h);

        bnext= new BotonConTexto(p5, x+w/3, y-70, 100, 50, "Siguiente");
        bprev= new BotonConTexto(p5, x+w/3+100, y - 70, 100, 50, "Anterior");
        bAccept= new BotonConTexto(p5, x+w/3+200, y-70, 50, 50, "OK");
    }

    //SETTERS
    void setCalendario(int d, int m, int y){
        calendario.set(Calendar.YEAR, y);
        calendario.set(Calendar.MONTH, m);
        calendario.set(Calendar.DATE, d);
    }

    void setCalendarioPrevio(int d, int m, int y){
        CalendarioPrevio.set(Calendar.YEAR, y);
        CalendarioPrevio.set(Calendar.MONTH, m);
        CalendarioPrevio.set(Calendar.DATE, d);
    }

    void setDateSelected(int d, int m, int y){
        this.diaSeleccionado= d;
        this.mesSeleccionado= m;
        this.añoSeleccionado= y;
    }


    void mesAterior(){
        this.botones= new BotonesDias[37];

        this.mes--;
        if(this.mes==0){
            this.mes= 12;
            this.año--;
        }
        setCalendario(this.dia, this.mes-1, this.año);

        this.numeroDiasMes= calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaDelaSem= calendario.get(Calendar.DAY_OF_WEEK);
        if(diaDelaSem== Calendar.SUNDAY){
            this.diaDelaSem= 6;
        }else{
            this.diaDelaSem= this.diaDelaSem-2;
        }

        calendario.set(Calendar.DAY_OF_WEEK, calendario.getFirstDayOfWeek());
        this.primerDia= calendario.get(Calendar.DATE);

        setCalendarioPrevio(1, this.mes-2, this.año);
        this.numeroDiasMesAnt= CalendarioPrevio.getActualMaximum(Calendar.DAY_OF_MONTH);

        crearCalendario(x, y, w, h);
    }

    void crearCalendario(int x, int y, int w, int h){
        float dayWidth= w/7;
        float dayHeight= h/6;
        int numeroDia=1;
        int f= 0;
        int nb= 0;

        while(numeroDia<=numeroDiasMes){
            if(primerDia!=1 && f==0){
                int calendarioPrevio=0;
                for(int p= primerDia, c=0; p<=numeroDiasMesAnt; p++, c++){
                    botones[nb]= new BotonesDias(x+c*dayWidth, y+f*dayHeight, dayWidth, dayHeight, p, mes, año);
                    calendarioPrevio++;
                    nb++;
                }

                for(int c= calendarioPrevio; c<7; c++){
                    botones[nb]= new BotonesDias(x+c*dayWidth, y+f*dayHeight, dayWidth, dayHeight, numeroDia, mes, año);
                    numeroDia++;
                    nb++;
                    if(numeroDia<numeroDiasMes){
                        break;
                    }
                }
                f++;
            }
        }
    }

    //VA UN MES ADELANTE EN EL CALENDARIO
    void ProximoMes(){
        this.botones= new BotonesDias[37];

        this.mes++;
        if(this.mes==13){
            this.mes=1;
            this.año++;
        }

        setCalendario(this.dia, this.mes-1, this.año);

        this.numeroDiasMes= calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.diaDelaSem= calendario.get(Calendar.DAY_OF_WEEK);
        if(diaDelaSem==Calendar.SUNDAY){
            this.diaDelaSem= 6;
        }else{
            this.diaDelaSem= diaDelaSem-2;
        }

        calendario.set(Calendar.DAY_OF_WEEK, calendario.getFirstDayOfWeek());
        this.primerDia= calendario.get(Calendar.DATE);

        setCalendarioPrevio(1, this.mes-2, this.año);

        this.numeroDiasMesAnt= CalendarioPrevio.getActualMaximum(Calendar.DAY_OF_MONTH);

        crearCalendario(x, y, w, h);
    }


    //DIBUJA EL CALENDARIO
    void display(PApplet p5){

        if(visible){
            p5.pushStyle();
                p5.fill(255);
                p5.noStroke();
                p5.rect(x, y-80, w, h);

                p5.fill(0);
                p5.textSize(36);
                p5.textAlign(p5.LEFT);
                p5.text(meses[mes-1]+"/"+año, x, y-30);
                for(BotonesDias b: botones){
                    if(b!=null){
                        b.display(p5);
                    }
                }

                if(dateSelected){
                    String dateText= this.diaSeleccionado+"/"+this.mesSeleccionado+"/"+this.añoSeleccionado;
                    p5.fill(0);
                    p5.textSize(24);
                    p5.textAlign(p5.RIGHT);
                    p5.text(dateText, x+w, y-30);
                }

                //DIBUJAR LOS BOTONES
                bnext.display(p5);
                bprev.display(p5);
                bAccept.display(p5);
            p5.popStyle();
        }
    }

    //COMPROVAR SI SE PULSAN LOS BOTONES DEL CALENDARIO
    void checkBotones(PApplet p5){
        for(BotonesDias b: botones){
            if((b!=null)&& (b.cursorEncimaBotonDia(p5))){
                boolean estadoAnterior= b.selected;
                deseleccionarTodo();
                b.setSeleccionado(!estadoAnterior);
                if(b.selected){
                    dateSelected=true;
                    setDateSelected(b.dia, b.mes, b.año);
                }else{
                    dateSelected= false;
                }
            }
        }
    }

    //DESELECCIONAR TODOS LOS BOTONES DEL CALENDARIO
    void deseleccionarTodo(){
        for(BotonesDias b: botones){
            if(b!=null){
                b.setSeleccionado(false);
            }
        }
    }
}
