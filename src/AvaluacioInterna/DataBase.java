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

    public String [] getColumnaNameDeo(){
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


    public String [][] getInfoTablaVinosPorColor(String colorVino){
        int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM vinos v, color col, denominacion den, imagen img WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND col.Color = '"+colorVino+"'; ");
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT v.nombreVinos AS NOMBRE, " + "den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, " +
                    "img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img WHERE v.COLOR_idCOLOR = col.idColor AND " +
                    "v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND col.Color = '"+colorVino+"'  ORDER BY NOMBRE ASC;");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("NOMBRE");
                info[nr][1] = rs.getString("DO");
                info[nr][2]= rs.getString("COLOR");
                info[nr][3]= rs.getString("UBICACION");
                info[nr][4]= rs.getString("FOTO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoTablaVinosPorColorDO(String colorVino, String DeO){
        String qn = "SELECT COUNT(*) AS n FROM vinos v, color col, denominacion den, imagen img " +
                "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND " +
                "col.Color = '"+colorVino+"' AND den.NombreDEO = '"+DeO+"'";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, " +
                    "img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img " +
                    "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND " +
                    "col.Color = '"+colorVino+"' AND den.NombreDEO = '"+DeO+"' ORDER BY NOMBRE ASC;";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);

            int nr = 0;
            while (rs.next()) {
                info[nr][0] = rs.getString("NOMBRE");
                info[nr][1] = rs.getString("DO");
                info[nr][2]= rs.getString("COLOR");
                info[nr][3]= rs.getString("UBICACION");
                info[nr][4]= rs.getString("FOTO");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }


    }

    /*public String [] getInfoVino(String nom){
        int numCols= 11;
        String [] vino= new String [numCols];
        try{
            ResultSet resultSet= query.executeQuery("SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color as COLOR, c.Ubicación AS UBICACIO, " +
                                                    "img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img" +
                    "WHERE v.nombreVinos= '"+nom+"' AND v.COLOR_idCOLOR= col.idColor AND ");
        }
    }*/

    //INSERT
    public void insertInfoTaulaVino(String nombre, String a, String p, String u, int can, String color, String cap, String DO, String b){
        try{
            String Snombre= nombre.replace("\"'", "\\'");
            String Can= String.valueOf(can);
            String q= "INSERT INTO vinos (nombreVinos, Añada, Precio, Ubicación, Cantidad, COLOR_idCOLOR, CAPACIDAD_idCAPACIDAD, DENOMINACIÓN, bodega) VALUES " +
                    "('"+Snombre + "','" +a + "','" + p + "','" +u + "','" + Can + "','" + color + "','" +cap + "','"+ DO + "','" +b + "')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public String getClaveFromTabla(String nombreTabla, String nombreColumnaClave, String nombreColumnaValor, String valorColumna){
        try {
            String queryText = "SELECT " + nombreColumnaClave + " AS clave FROM " + nombreTabla+ " WHERE " +nombreColumnaValor+ " = '"+valorColumna+"'";
            System.out.println(queryText);
            ResultSet rs = query.executeQuery(queryText);
            rs.next();
            return rs.getString("clave");
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }


}
