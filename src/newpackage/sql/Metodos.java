/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC02
 */
public class Metodos {
    
  public void insertar(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZonalerosPU");
        EntityManager em = emf.createEntityManager();
        
     // iniciar y finalizr transaciones
        em.getTransaction().begin();
            //aqui se realizan las operaciones de prueba
            Desarrolladora EA = new Desarrolladora(0, 1, "EA");
            EA.setCodigoDesarrolladora(1);
        em.getTransaction().commit();
  }
}
