package AvaluacioInterna;

public class PagedEstantes2 {


    int filasWidth;
    int numColumnas;
    int numFilas;

    int numPag;
    int numTotalPag;

    //CONSTRUCTOR
    public PagedEstantes2(int nc, int nf){
        this.numColumnas= nc;
        this.numFilas= nf;
        this.numPag=0;
    }

    //SETTERS
    public void setFilasWidth(int w){
        this.filasWidth=w;
    }

    public void nextPage(){
        if(this.numPag<this.numTotalPag){
            this.numPag++;
        }
    }

    public void prevPage(){
        if(this.numPag>0){
            this.numPag--;
        }
    }


    //DIBUJAR TABLA

}
