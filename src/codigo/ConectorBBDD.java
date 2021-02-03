/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    
    public ArrayList<Album> albumList() {
        ArrayList<Album> albumesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM album;";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            Album album;
            while(rs.next()){
                album = new Album(rs.getInt("id"), rs.getString("titulo"), rs.getString("grupo"), rs.getTime("duracion"), rs.getString("productor"));
                albumesList.add(album);
            }
            
            rs.close();
            st.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return albumesList;
    }
    
    public ArrayList<Cancion> cancionList() {
        ArrayList<Cancion> cancionesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM cancion;";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            Cancion cancion;
            while(rs.next()){
                cancion = new Cancion(rs.getInt("id"), rs.getString("titulo"), rs.getTime("duracion"), rs.getString("escritor"), rs.getInt("album"));
                cancionesList.add(cancion);
            }
            
            rs.close();
            st.close();            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cancionesList;        
    }
    
    public ArrayList<Cancion> cancionListFiltroAlbum(int _album) {
        ArrayList<Cancion> cancionesList = new ArrayList<>();
        String query = "SELECT * FROM cancion WHERE album= ?;";
        try {
            PreparedStatement pst = conexion.prepareStatement(query);            
            pst.setString(1, String.valueOf(_album));
            
            ResultSet rs = pst.executeQuery();
            
            Cancion cancion;
            while(rs.next()){
                cancion = new Cancion(rs.getInt("id"), rs.getString("titulo"), rs.getTime("duracion"), rs.getString("escritor"), rs.getInt("album"));
                cancionesList.add(cancion);
            }
            
            rs.close();
            pst.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cancionesList;        
    }
    
    public ArrayList<Cancion> cancionListFiltroDuracion(String _duracion, int _orden) {
        String query = "";
        ArrayList<Cancion> cancionesList = new ArrayList<>();
        if(_orden == 0) {
            query = "SELECT * FROM cancion WHERE duracion< ? ORDER BY duracion;";
        } else if(_orden == 1){
            query = "SELECT * FROM cancion WHERE duracion< ? ORDER BY duracion DESC;";
        }
        try {
            PreparedStatement pst = conexion.prepareStatement(query);            
            pst.setString(1, String.valueOf(_duracion));
            
            ResultSet rs = pst.executeQuery();
            
            Cancion cancion;
            while(rs.next()){
                cancion = new Cancion(rs.getInt("id"), rs.getString("titulo"), rs.getTime("duracion"), rs.getString("escritor"), rs.getInt("album"));
                cancionesList.add(cancion);
            }
            
            rs.close();
            pst.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cancionesList;        
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
            
            rs.close();
            sta.close();
            return aux;
        } catch (Exception e){
            System.out.println("Error al mostrar contenido de la base de datos.");
            return null;
        } 
        
    }
    
    public int annadirAlbum(String _titulo, String _grupo, String _duracion, String _productor) {
        
        Statement sta;
        try {
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

    public void updateCancion(String _id, String _titulo, String _duracion, String _escritor, int _album) {
        
        Statement sta;
        String query = "UPDATE cancion SET titulo= ?, duracion= ?, escritor= ?, album = ? WHERE id= ?;";
        try {
            sta = conexion.createStatement();
            sta.execute("SET foreign_key_checks = 0;");
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, String.valueOf(_album+1)+_titulo.substring(1));
            pst.setString(2, _duracion);
            pst.setString(3, _escritor);
            pst.setInt(4, _album+1);
            pst.setString(5, _id);
            sta.execute("SET foreign_key_checks = 1;");
            
            pst.executeUpdate();
            
            sta.close();
            pst.close();
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    public void deleteCancion(int _id) {
        
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.execute("SET foreign_key_checks = 0;");
            sta.execute("DELETE FROM cancion WHERE id='"+_id+"';");
            sta.execute("SET foreign_key_checks = 1;");
            
            sta.close();
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }

    public void updateAlbum(String _id, String _titulo, String _grupo, String _duracion, String _productor) {
        
        String query = "UPDATE album SET titulo= ?, grupo= ?, duracion= ?, productor = ? WHERE id= ?;";
        try {
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, _titulo);
            pst.setString(2, _grupo);
            pst.setString(3, _duracion);
            pst.setString(4, _productor);
            pst.setString(5, _id);
            
            pst.executeUpdate();
            
            pst.close();
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    public void deleteAlbum(int _id) {
        
        Statement sta;
        try {
            sta = conexion.createStatement();
            sta.execute("DELETE FROM album WHERE id='"+_id+"';");
            
            sta.close();
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
    
}
