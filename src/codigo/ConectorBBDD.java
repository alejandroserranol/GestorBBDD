/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class ConectorBBDD {
    
    Connection conexion = null;
    
    public int ConectarBBDD(){
        try {
            String urlBBDD = "jdbc:mysql://localhost:3306/discografica";
            String usuario = "root";
            String password = "root";
            
            conexion = DriverManager.getConnection(urlBBDD, usuario, password);
            
            return 0;
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
    }
    
    public int cerrarConexion(){
        try {
            conexion.close();
            
            return 0;
        } catch (SQLException ex) {
            return -1;
        }        
    }
    
    public int annadirCampo(String _tabla, String _nombreColumna, String _tipoDato, String _varSize){
        String tipoDato = "";
        if(_varSize.length() > 0){
            tipoDato = _tipoDato + "(" + _varSize + ")";
        } else {
            tipoDato = _tipoDato;
        }
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("ALTER TABLE " + _tabla + " ADD " + _nombreColumna + " " + tipoDato + ";");
            sta.close();
            return 0;
        } catch(SQLException ex){
            if(ex.toString().contains("java.sql.SQLSyntaxErrorException: Duplicate column name")){
                return -1;
            } else {
                return -2;
            }
        }        
    }    

    public String consultaAlbum_Statement(String _dbName) {
        
        String cadena_resultado = "";
        Statement sta;
        try{
            sta = conexion.createStatement();
            String query = "SELECT * FROM " + _dbName + ";";
            ResultSet rs = sta.executeQuery(query);
                
            if(_dbName.equals("album")){
                while (rs.next()){
                    cadena_resultado += "ID: " + rs.getInt("id") +
                                    ", Título: " + rs.getString("titulo") +
                                    ", Grupo: " + rs.getString("grupo") +
                                    ", Duracion: " + rs.getTime("duracion") +
                                    ", Productor: " + rs.getString("productor") + "\n";
                }
            } else {
                while (rs.next()){
                    cadena_resultado += "ID: " + rs.getInt("id") +
                                    ", Título: " + rs.getString("titulo") +
                                    ",Duracion: " + rs.getTime("duracion") +
                                    ", Escritor: " + rs.getString("escritor") +
                                    ", Album: " + rs.getString("album") + "\n";
                }
            }
            return cadena_resultado;
        } catch (Exception e){
            return "Error al mostrar contenido de la base de datos.";
        }
        
    }

    public ArrayList<String> getTitulosAlbum() {
        
        ArrayList<String> aux = new ArrayList<>();
        Statement sta;
        try{
            sta = conexion.createStatement();
            String query = "SELECT titulo FROM album;";
            ResultSet rs = sta.executeQuery(query);

            while (rs.next()){
                aux.add(rs.getString("titulo"));
            }                
                
            return aux;
        } catch (Exception e){
            System.out.println("Error al mostrar contenido de la base de datos.");
            return null;
        } 
        
    }
    
    public int annadirAlbum(String _titulo, String _grupo, String _duracion, String _productor) {
        
        Statement sta;
        try {
            System.out.println(_titulo + "\t" + _grupo + "\t" + _duracion  + "\t" + _productor);
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO album (id, titulo, grupo, duracion, productor) "
                            + "VALUE (NULL, '" +
                                        _titulo + "', '" +
                                        _grupo +  "', '" +
                                        _duracion + "', '" +
                                        _productor + "');");            
            sta.close();
            return 0;
        } catch(SQLException ex){
            if(ex.toString().contains("java.sql.SQLSyntaxErrorException: Duplicate column name")){
                return -1;
            } else {
                return -2;
            }
        }  
        
    }

    public int annadirCancion(String _titulo, String _duracion, String _escritor, int _album) {
        
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO cancion (id, titulo, duracion, escritor, album) "
                            + "VALUE (NULL, '" +
                                        _titulo + "', '" +
                                        _duracion +  "', '" +
                                        _escritor + "', '" +
                                        _album + "');");
            sta.close();
            return 0;
        } catch(SQLException ex){
            if(ex.toString().contains("java.sql.SQLSyntaxErrorException: Duplicate column name")){
                return -1;
            } else {
                return -2;
            }
        } 
        
    }
    
    
    
}
