package AvaluacioInterna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataBase {

    Connection c;

    Statement query;
    String user, password, databaseName;
    boolean connected = false;

    public DataBase (String user, String password, String databaseName){
        this.user= user;
        this.password= password;
        this.databaseName= databaseName;
    }

    public void connect(){
        try{
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            System.out.println("Connectat a la BBDD");
            query = c.createStatement();
            connected= true;
        }
        catch(Exception e){
            System.out.print(e);
        }
    }

    public int getNumeroFilasTabla(String nombreTabla){
        try {
            String queryText = "SELECT COUNT(*) AS n FROM " + nombreTabla;
            ResultSet rs = query.executeQuery(queryText);
            rs.next();
            int numeroFilas = rs.getInt("n");
            return numeroFilas;
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    // Retorna el número de files que retornaria una query SELECT qualsevol amb valor "n"
    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int getNumeroColumnasTabla(String nombreTaula){
        try {
            String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nombreTaula +"' AND table_schema='"+databaseName+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery( q);
            rs.next();
            int numCols = rs.getInt("n");
            return numCols;
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }


    public String [][] getInfoTablaDO(){
        int numFiles = getNumeroFilasTabla("denominacion");
        int numCols  = 2;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM denominacion");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("idDenominacion"));
                info[nr][1] = rs.getString("NombreDEO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [] getColumnaNameTDeo(){
        int numFilas= getNumeroColumnasTabla("denominacion");
        String [] info = new String[numFilas];

        try{
            ResultSet rs= query.executeQuery("SELECT NombreDEO FROM denominacion");
            int nr= 0;
            while(rs.next()){
                info[nr]= rs.getString("NombreDEO");
                nr++;
            }
            return info;
        }
        catch (Exception e){
            return null;
        }
    }

    public String [][] getInfoTablaVinos(String colorVino){
        int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM vinos v, color col, denominacion den, imagen img WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND col.Color = '"+colorVino+"'  ORDER BY NOMBRE ASC;");
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            //ResultSet rs = query.executeQuery( "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND col.Color = 'Blanco' AND den.NombreDEO = 'Francia' ORDER BY NOMBRE ASC;");
            ResultSet rs = query.executeQuery( "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND col.Color = '"+colorVino+"'  ORDER BY NOMBRE ASC;");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("NOMBRE"));
                info[nr][1] = rs.getString("NombreDEO");
                info[nr][2]=
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


}
