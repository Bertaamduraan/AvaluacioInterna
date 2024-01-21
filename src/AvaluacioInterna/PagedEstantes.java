package AvaluacioInterna;

import processing.core.PApplet;

public class PagedEstantes {

    String[][] EstantesData; //Datos de cada Estante
    Estanteria[] estantes; //Estantes
    int numEstantes;
    int numEstantePorPag;

    int numPagina;
    int numTotalPaginas;

    //CONSTRUCTOR
    public PagedEstantes(int nep){
        this.numEstantePorPag= nep;
        this.numPagina= 0;
    }

    //SETTERS
    public void setData(String [][] d){
        this.EstantesData=d;
        this.numTotalPaginas= 100;
    }

    public void setEstantes(){
        estantes= new Estanteria[this.EstantesData.length];

        for(int np=0; np<=numTotalPaginas; np++){
            int firstEstantePage= numEstantePorPag*np;
            int lastEstantePage= numEstantePorPag*(np+1)-1;

            for(int i= firstEstantePage; i<=lastEstantePage; i++){
                if(i<estantes.length){

                }
            }
        }
    }

    public void nextPage(){
        if(this.numPagina<this.numTotalPaginas){
            this.numPagina++;
        }
    }

    public void prevPage(){
        if(this.numPagina>0){
            this.numPagina--;
        }
    }

    public void display(PApplet p5){
        p5.pushStyle();

        //DIBUJA LA ESTANTERIA CORRESPONIENTES A LA PÀGINA
        int firstEstatePage= numEstantePorPag*numPagina;
        int lastEstantePage= numEstantePorPag*(numPagina+1) - 1;

        for(int i= firstEstatePage; i<=lastEstantePage; i++){
            if(i< estantes.length && estantes[i]!=null){
                estantes[i].display(p5);
            }
        }
    }
}
