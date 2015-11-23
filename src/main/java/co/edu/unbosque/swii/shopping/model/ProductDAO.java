/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Huber
 */
public class ProductDAO {

    public static List<TblProducts> selectAllProduct() {
        List<TblProducts> listProducts = new ArrayList<>();
        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            listProducts = (List<TblProducts>) em.createNamedQuery("TblProducts.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }
}
