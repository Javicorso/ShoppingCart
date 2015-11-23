/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Huber
 */
public class CartDAO {

    public static boolean addProductToCart(Map<String, Object> userInfo, TblProducts product) {

        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TblShoppingcart shoppingcart = (TblShoppingcart) userInfo.get("cart");
            TblProductcartPK pK = new TblProductcartPK(shoppingcart.getIdcart(), product.getIdproduct());
            TblProductcart productcart = new TblProductcart(pK);
            em.persist(productcart);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public static boolean deleteProductToCart(Map<String, Object> userInfo, TblProducts product) {

        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TblShoppingcart shoppingcart = (TblShoppingcart) userInfo.get("cart");
            TblProductcartPK pK = new TblProductcartPK(shoppingcart.getIdcart(), product.getIdproduct());
            TblProductcart productcart = em.find(TblProductcart.class, pK);
            em.remove(productcart);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static List<TblProducts> getSelectedProds(Map<String, Object> userInfo) {
        List<TblProducts> listProducts = new ArrayList<>();
        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            TblShoppingcart shoppingcart = (TblShoppingcart) userInfo.get("cart");
            listProducts = em.createNamedQuery("TblProductcart.findByIdcart")
                    .setParameter("idcart", shoppingcart.getIdcart())
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }

    public static List<TblProducts> getCartProds(Map<String, Object> userInfo) {
        List<TblProducts> listProducts = new ArrayList<>();
        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            TblShoppingcart shoppingcart = (TblShoppingcart) userInfo.get("cart");
            List<TblProductcart> listProductsCart = em.createNamedQuery("TblProductcart.findByIdcart")
                    .setParameter("idcart", shoppingcart.getIdcart())
                    .getResultList();
            for (TblProductcart productcart : listProductsCart) {
                TblProducts product = (TblProducts) em.createNamedQuery("TblProducts.findByIdproduct")
                        .setParameter("idproduct", productcart.getTblProductcartPK().getIdproduct())
                        .getSingleResult();
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }
    
    public static boolean payShoppingCart(Map<String, Object> userInfo){
        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            TblShoppingcart shoppingcart = (TblShoppingcart) userInfo.get("cart");
            shoppingcart.setCartstate(Boolean.FALSE);
            em.merge(shoppingcart);
            em.flush();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
