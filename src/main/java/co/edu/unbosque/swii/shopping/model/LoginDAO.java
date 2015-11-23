/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Huber
 */
public class LoginDAO {

    public static Map<String, Object> validateLogin(String user, String pass) {
        Map<String, Object> userInfo = new HashMap();
        TblUser tblUser = new TblUser();
        try {
            EntityManagerFactory emf = BDConnection.getEMF();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            tblUser = (TblUser) em.createNamedQuery("TblUser.findByUserNamePass")
                    .setParameter("username", user)
                    .setParameter("userpass", pass)
                    .getSingleResult();
            System.out.println("Usuario encontrado: " + tblUser.getUsername());
            
            userInfo.put("user", tblUser);
            
            List<TblShoppingcart> shoppingcart = (List<TblShoppingcart>) em.createNamedQuery("TblShoppingcart.findByCartstate")
                    .setParameter("idUser", tblUser)
                    .getResultList();
            if (!shoppingcart.isEmpty()) {
                userInfo.put("cart", shoppingcart.get(0));
            } else {
                shoppingcart = (List<TblShoppingcart>) em.createNamedQuery("TblShoppingcart.findAll").getResultList();
                Integer idCart = 1;
                if(!shoppingcart.isEmpty()){
                    idCart = shoppingcart.get(shoppingcart.size() - 1).getIdcart() + 1;
                }
                System.out.println("IDCart: " + idCart);
                TblShoppingcart tblShoppingcart = new TblShoppingcart();
                tblShoppingcart.setIdcart(idCart);
                tblShoppingcart.setCartstate(Boolean.TRUE);
                tblShoppingcart.setIduser(tblUser);
                tblShoppingcart.setCartdate(new Date());
                em.persist(tblShoppingcart);
                em.flush();
                userInfo.put("cart", tblShoppingcart);
            }
            em.getTransaction().commit();
        } catch (NoResultException ne) {
            System.out.println("Error: " + ne.getMessage());
            tblUser.setUsername("0");
            userInfo.put("user", tblUser);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            tblUser.setUsername("-");
            userInfo.put("user", tblUser);
        }
        return userInfo;
    }

}
