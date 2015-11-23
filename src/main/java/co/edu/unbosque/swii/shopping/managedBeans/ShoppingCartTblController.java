/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.managedBeans;

import co.edu.unbosque.swii.shopping.model.CartDAO;
import co.edu.unbosque.swii.shopping.model.TblProducts;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huber
 */
@ManagedBean
@SessionScoped
public class ShoppingCartTblController implements Serializable {

    private static List<TblProducts> shoppingCarts;
    private TblProducts selectedProduct;


    public List<TblProducts> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<TblProducts> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public TblProducts getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(TblProducts selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void deleteSelectedProduct() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Map<String, Object> userInfo = (Map<String, Object>) sessionMap.get("userInfo");
        if (CartDAO.deleteProductToCart(userInfo, selectedProduct)) {
            showMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado de su carrito de compras");
            updateSelectedProd();
        } else {
            showMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error eliminando el producto");
        }
    }

    public String payShoppingCart() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Map<String, Object> userInfo = (Map<String, Object>) sessionMap.get("userInfo");
        boolean res = CartDAO.payShoppingCart(userInfo);
        if (res) {
            showMessage(FacesMessage.SEVERITY_INFO, "Su pago a sido realizado");
            updateSelectedProd();
            return "shoppingList";
        } else {
            showMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error durante el proceso de pago");
            return "shoppingCart";
        }
    }

    public static void updateSelectedProd() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Map<String, Object> userInfo = (Map<String, Object>) sessionMap.get("userInfo");
        shoppingCarts = CartDAO.getCartProds(userInfo);
    }

    public void showMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }
    
    public String goShoppingList(){
        ProductTblController.updateSelectedProd();
        return "shoppingList";
    }
}
