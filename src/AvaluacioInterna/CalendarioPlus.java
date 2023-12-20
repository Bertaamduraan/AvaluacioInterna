package AvaluacioInterna;

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
    Calendar calendario, calendarioPrevio;

    //BOTONES DEL CALENDARIO
    BotonesDias[] botones;
    BotonConTexto bnext, bprev, bAccept;

    //DIMENSIONES DEL CALENDARIO
    int x, y, w, h;

    //VISIBILIDAD DEL CALENDARIO
    boolean visible = false;

    CalendarioPlus(int x, int y, int w, int h){
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

        }
    }
}
