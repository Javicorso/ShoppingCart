/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Huber
 */
@Embeddable
public class TblProductcartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcart")
    private int idcart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduct")
    private int idproduct;

    public TblProductcartPK() {
    }
    
    public TblProductcartPK(int idcart) {
        this.idcart = idcart;

    }

    public TblProductcartPK(int idcart, int idproduct) {
        this.idcart = idcart;
        this.idproduct = idproduct;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcart;
        hash += (int) idproduct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductcartPK)) {
            return false;
        }
        TblProductcartPK other = (TblProductcartPK) object;
        if (this.idcart != other.idcart) {
            return false;
        }
        if (this.idproduct != other.idproduct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unbosque.swii.shopping.model.TblProductcartPK[ idcart=" + idcart + ", idproduct=" + idproduct + " ]";
    }
    
}
