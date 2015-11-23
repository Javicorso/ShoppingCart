/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.managedBeans;

import co.edu.unbosque.swii.shopping.model.CartDAO;
import co.edu.unbosque.swii.shopping.model.ProductDAO;
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
public class ProductTblController implements Serializable {

    private List<TblProducts> listProducts;
    private TblProducts selectedProduct;
    private static List<TblProducts> listSelectedProducts;

    public ProductTblController() {
        updateSelectedProd();
    }
    
    

    public TblProducts getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(TblProducts selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<TblProducts> getListSelectedProducts() {
        return listSelectedProducts;
    }

    public void setListSelectedProducts(List<TblProducts> listSelectedProducts) {
        this.listSelectedProducts = listSelectedProducts;
    }

    public List<TblProducts> getListProducts() {
        if (listProducts == null) {
            return ProductDAO.selectAllProduct();
        }
        return listProducts;
    }

    public void addProduct() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Map<String, Object> userInfo = (Map<String, Object>) sessionMap.get("userInfo");
        if(CartDAO.addProductToCart(userInfo, selectedProduct)){
            showMessage(FacesMessage.SEVERITY_INFO, "Producto añadido a su carrito de compras");
            updateSelectedProd();
        } else {
            showMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error añadiendo el producto");
        }
    }
    
    public static void updateSelectedProd(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Map<String, Object> userInfo = (Map<String, Object>) sessionMap.get("userInfo");
        listSelectedProducts = CartDAO.getSelectedProds(userInfo);
    }
    
    public void showMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, ""));
    }
    
    public String goShoppingCart(){
        ShoppingCartTblController.updateSelectedProd();
        return "shoppingCart";
    }

}
