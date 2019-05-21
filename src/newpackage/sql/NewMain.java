/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.sql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC02
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zonalerosPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            Desarrolladora Ubisoft = new Desarrolladora(0, 2, "Ubisoft");
            Ubisoft.setCodigoDesarrolladora(2);
            em.persist(Ubisoft);
    }
        
}
    

