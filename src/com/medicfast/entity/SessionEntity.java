
package com.medicfast.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;


public class SessionEntity {
    static EntityManager em ;
    
    public static EntityManager getEntity(){
        EntityManagerFactory mf = Persistence.createEntityManagerFactory("MedicFastPU");
        em = mf.createEntityManager();
        return em;
    }
    
    
}
