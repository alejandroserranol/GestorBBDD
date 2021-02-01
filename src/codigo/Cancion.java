/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Time;

/**
 *
 * @author Alejandro Serrano Loredo
 */
class Cancion {
    private int id, album;
    private String titulo, escritor;
    private Time duracion;
    
    public Cancion(int _id, String _titulo, Time _duracion, String _escritor, int _album){
        
        this.id = _id;
        this.titulo = _titulo;
        this.duracion = _duracion;
        this.escritor = _escritor;
        this.album = _album;
        
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public Time getDuracion(){
        return duracion;
    }
    
    public String getEscritor(){
        return escritor;
    }
    
    public int getAlbum(){
        return album;
    }
}
