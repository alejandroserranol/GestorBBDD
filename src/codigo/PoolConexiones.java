/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class PoolConexiones {
    
    Connection conexion = null;
    
    public PoolConexiones(){
        
        BasicDataSource bdSource = new BasicDataSource();
        bdSource.setUrl("jdbc:mysql://localhost:3306/discografica");
        bdSource.setUsername("root");
        bdSource.setPassword("root");
        
        try {
            conexion = bdSource.getConnection();
            if(conexion != null){
                System.out.println("Conexión creada satisfactoriamente.");
            } else {
                System.out.println("No se puede crear una nueva conexión.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public int cerrarConexion() {
        try {
            conexion.close();            
            return 0;
        } catch (SQLException ex) {
            return -1;
        }    
    }
    
}
