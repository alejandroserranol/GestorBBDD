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
class Album {
    private int id;
    private String titulo, grupo, productor;
    private Time duracion;
    
    public Album(int _id, String _titulo, String _grupo, Time _duracion, String _productor){
        
        this.id = _id;
        this.titulo = _titulo;
        this.grupo = _grupo;
        this.duracion = _duracion;
        this.productor = _productor;
        
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getGrupo(){
        return grupo;
    }
    
    public Time getDuracion(){
        return duracion;
    }
    
    public String getProductor(){
        return productor;
    }
    
}
