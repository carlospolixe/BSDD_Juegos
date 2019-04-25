/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zonaleros;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC02
 */
public class Zonaleros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map<String, String> emfProperties = new HashMap<String, String>();
        emfProperties.put("javax.persistence.schema-generation.database.action", "create");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZonalerosPU", emfProperties);
        EntityManager em = emf.createEntityManager();

        // REALIZAR AQUÍ LAS OPERACIONES SOBRE LA BASE DE DATOS
        
        // Cerrar la conexión con la base de datos
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:database;shutdown=true"); 
        } catch (SQLException ex) { 
        }
    }
    }
    

