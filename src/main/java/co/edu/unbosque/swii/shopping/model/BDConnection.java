/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Huber
 */
public class BDConnection {

//    private static final String driver = "org.postgresql.Driver";
//    private static final String connectString = "jdbc:postgresql://aretico.com:5432/software_2?searchpath=grupo6";
//    private static final String user = "grupo6_5";
//    private static final String password = "YckGwYC8r3";
    private static EntityManagerFactory emf;
    private static BDConnection instance = new BDConnection();

    
    public static EntityManagerFactory getEMF() {
        if (instance == null) {
            System.out.println(instance);
            instance = new BDConnection();
        }
        return BDConnection.emf;
    }

    private BDConnection() {
        try {
            emf = Persistence
                .createEntityManagerFactory("CarritoComprasUnit");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
