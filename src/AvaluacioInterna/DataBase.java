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

    public String [][] getInfoTablaBodega(){
        int numFiles= getNumeroFilasTabla("bodega");
        int numCols= 2;
        String [][] info= new String [numFiles][numCols];
        try{
            ResultSet rs= query.executeQuery( "SELECT * FROM bodega");
            int nr= 0;
            while(rs.next()){
                info[nr][0]= String.valueOf(rs.getInt("idbodega"));
                info[nr][1]= rs.getString("nombreBodega");
                nr++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
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

    public String [][] getInfoTablaVinosFiltros(String colorVino, String DeO, String añada, String pvp, String bodega, String capacidad, String cantidad){
        String qColor="";
        if(!colorVino.equals("Color")){
            qColor = " AND col.Color = '"+colorVino+"' ";
        }
        String qDO="";
        if(DeO!=""){
            qDO = " AND den.NombreDEO = '"+DeO+"' ";
        }
        String qAño="";
        if(añada!=""){
            qAño = " AND v.Añada='"+añada+"' ";
        }
        String qPrecio="";
        if(pvp!=""){
            qPrecio = " AND v.Precio='"+pvp+"' ";
        }
        String qBodega="";
        if(bodega!=""){
            qBodega = " AND v.bodega='"+bodega+"' ";
        }
        String qCapacidad= "";
        if(capacidad!=""){
            qCapacidad= " AND v.CAPACIDAD_idCAPACIDAD='"+capacidad+"' ";
        }
        String qCantidad= "";
        if(cantidad!= ""){
            qCantidad= " AND v.Cantidad='" + cantidad+"' ";
        }
        String qn = "SELECT COUNT(*) AS n FROM vinos v, color col, denominacion den, imagen img " +
                "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen " +
                " " + qAño + qPrecio + qColor + qDO + qBodega + qCapacidad + qCantidad;
        System.out.println(qn);
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, " +
                        "img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img " +
                        "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen  " +
                        qAño + qPrecio + qColor + qDO + qBodega + qCapacidad + qCantidad+ " ORDER BY NOMBRE ASC;";
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

    public String[][] getInfoTablaRepresentantes(){
        int numFiles= getNumeroFilasTabla("cocineros");
        int numCols= 2;
        String [][] info= new String [numFiles][numCols];
        try{
            ResultSet rs= query.executeQuery( "SELECT * FROM cocineros");
            int nr= 0;
            while(rs.next()){
                info[nr][0]= String.valueOf(rs.getInt("idGrupo"));
                info[nr][1]= rs.getString("Representante");
                nr++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String[] getNombreVinos(){
        int numFiles= getNumeroFilasTabla("vinos");
        String []info= new String [numFiles];
        try{
            ResultSet rs= query.executeQuery( "SELECT (*) FROM vino");
            int nr= 0;
            while(rs.next()){
                info[nr]= rs.getString("nombreVinos");
                nr++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String [][] getInfoTablaVinos(){
        String qn = "SELECT COUNT(*) AS n FROM vinos v, color col, denominacion den, imagen img " +
                "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen ";
        int numFiles = getNumRowsQuery(qn);
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, " +
                    "img.Imagen AS FOTO FROM vinos v, color col, denominacion den, imagen img " +
                    "WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen ";
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

    public String [] getInfoVino(String nom){
        int numCols= 11;
        String [] vino= new String [numCols];
        try{
            String q=  "SELECT v.nombreVinos AS NOMBRE, den.NombreDEO AS DO, col.Color AS COLOR, v.Ubicación AS UBICACION, img.Imagen AS FOTO, cap.Capacidad AS CAPACIDAD, " +
                        " bo.nombreBodega AS BODEGA, v.Añada AS YEAR, v.Precio AS PRECIO, v.Cantidad AS CANTIDAD, v.Fecha AS FECHA "+
                        " FROM vinos v, color col, denominacion den, imagen img, capacidad cap, bodega bo " +
                        " WHERE v.COLOR_idCOLOR = col.idColor AND v.DENOMINACIÓN= den.idDenominacion AND v.IMAGEN_idIMAGEN = img.idImagen AND "+
                        " v.CAPACIDAD_idCAPACIDAD= cap.idCapacidad AND v.bodega= bo.idbodega AND v.nombreVinos= '"+nom+"'";
            System.out.println(q);
            ResultSet rs= query.executeQuery (q);
            rs.next();
            vino[0]= rs.getString("NOMBRE");
            vino[1]= rs.getString("DO");
            vino[2]= rs.getString("COLOR");
            vino[3]= rs.getString("UBICACION");
            vino[4]= rs.getString("FOTO");
            vino[5]= rs.getString("CAPACIDAD");
            vino[6]= rs.getString("BODEGA");
            vino[7]= rs.getString("YEAR");
            if(vino[7]== null){
                vino[7]= "0000";
            }
            vino[8]= String.valueOf(rs.getInt("PRECIO"));
            vino[9]= String.valueOf(rs.getString("CANTIDAD"));
            vino[10]= String.valueOf(rs.getString("FECHA"));
            if(vino[10]== "null"){
                vino[10]= "0000";
            }
            return vino;
        }
        catch(Exception e){
            System.out.println (e);
            return null;
        }
    }

    public String[][] getAñadaFecha(String año){
        int numFiles= getNumeroFilasTabla("vinos");
        int numCols= 3;
        String [][] vino= new String [numFiles][numCols];

        try{
            String q= "SELECT v.nombreVinos as NOMBRE, v.Fecha as FECHA, v.Añada as AÑO FROM  vinos v WHERE v.Añada= '" +año+ "'";
            ResultSet rs= query.executeQuery(q);
            int nr = 0;
            while (rs.next()) {
                vino[nr][0] = rs.getString("NOMBRE");
                vino[nr][1]= rs.getString("FECHA");
                nr++;
            }
            return vino;
        }
        catch(Exception e){
            System.out.println (e);
            return null;
        }
    }

    public int getFilasVinoAñada(String año){
        String qn= "SELECT COUNT(*) AS n FROM vinos v " + "WHERE v.Añada= '"+ año+ "'";
        int N= getNumRowsQuery(qn);
        return N;
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

    public String [] getTipos(){
        int numFilas=3;
        String [] tipos= new String [numFilas];
        try{
            ResultSet rs= query.executeQuery("SELECT Color FROM color");
            int nr= 0;
            while(rs.next()){
                tipos[nr]= rs.getString("Color");
                nr++;
            }
            return tipos;
        }
        catch (Exception e){
            return null;
        }
    }

    public String [] getInfoEvento(String fecha){
        int numCols = 4;
        String [] evento = new String [numCols];
        try{
            String q= "SELECT e.NombreEvento AS NOMBRE, e.Fecha AS FECHA, c.Representante AS COCINEROS, e.DescriptionEvent AS TEXTO FROM evento e, cocineros c "+
                    "WHERE e.Cocineros = c.idGrupo AND e.Fecha = '"+ fecha+ "'";
            System.out.println(q);
            ResultSet rs= query.executeQuery(q);
            rs.next();
            evento[0]= rs.getString("FECHA");
            evento[1]= rs.getString("NOMBRE");
            if(evento[1]==null){
                evento[1]= "";
            }
            evento[2]= rs.getString("COCINEROS");
            evento[3]= rs.getString("TEXTO");
        }
        catch (Exception e){
            return null;
        }
        return evento;
    }

    /*public String [] getVinosEvento(String fecha){
        String c= getClaveFromTabla("evento", "idEvento", "Fecha", fecha);
        String [] vinosEvento= new String [4];

    }*/


    //INSERT
    public void insertInfoTaulaVino(String nombre, String a, String p, String u, int can, String color, String cap, String DO, String b, String año){
        try{
            String Snombre= nombre.replace("\"'", "\\'");
            String Can= String.valueOf(can);
            String q= "INSERT INTO vinos (nombreVinos, Añada, Precio, Ubicación, Cantidad, COLOR_idCOLOR, CAPACIDAD_idCAPACIDAD, DENOMINACIÓN, bodega, Fecha) VALUES " +
                    "('"+Snombre + "','" +a + "','" + p + "','" +u + "','" + Can + "','" + color + "','" +cap + "','"+ DO + "','" +b + "','" + año+ "')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertDenominacion(String nombreDO){
        try{
            String Snombre= nombreDO.replace("\"'", "\\'");
            String q= "INSERT INTO denominacion (nombreDEO) VALUES " +
                    "('" +Snombre+ "')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertBodega(String nombreBodega){
        try{
            String Snombre= nombreBodega.replace("\"'", "\\'");
            String q= "INSERT INTO bodega (nombreBodega) VALUES " +
                    "('" +Snombre+ "')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //DELETE
    public void deleteVino (String nombreV){
        try {
            String q = "DELETE FROM vinos WHERE nombreVinos= '" + nombreV + "'";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteVinosEvento(String clave){
        try {
            String q = "DELETE FROM vinos_evento WHERE Evento_idEvento= '" + clave + "'";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteEvento(String fecha){
        String c= getClaveFromTabla("evento", "idEvento", "Fecha", fecha);
        deleteVinosEvento(c);
        try {
            String q = "DELETE FROM evento WHERE idEvento= '" + c + "'";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
