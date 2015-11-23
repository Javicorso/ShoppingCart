/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huber
 */
@Entity
@Table(name = "grupo6.tbl_productcart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProductcart.findAll", query = "SELECT t FROM TblProductcart t"),
    @NamedQuery(name = "TblProductcart.findByPK", query = "SELECT t FROM TblProductcart t WHERE t.tblProductcartPK.idcart = :idcart AND t.tblProductcartPK.idproduct = :idproduct"),
    @NamedQuery(name = "TblProductcart.findByIdcart", query = "SELECT t FROM TblProductcart t WHERE t.tblProductcartPK.idcart = :idcart"),
    @NamedQuery(name = "TblProductcart.findByIdproduct", query = "SELECT t FROM TblProductcart t WHERE t.tblProductcartPK.idproduct = :idproduct"),
    @NamedQuery(name = "TblProductcart.findByPcquantity", query = "SELECT t FROM TblProductcart t WHERE t.pcquantity = :pcquantity")})
public class TblProductcart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblProductcartPK tblProductcartPK;
    @Column(name = "pcquantity")
    private Integer pcquantity;
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblProducts tblProducts;
    @JoinColumn(name = "idcart", referencedColumnName = "idcart", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblShoppingcart tblShoppingcart;

    public TblProductcart() {
    }

    public TblProductcart(TblProductcartPK tblProductcartPK) {
        this.tblProductcartPK = tblProductcartPK;
    }

    public TblProductcart(int idcart, int idproduct) {
        this.tblProductcartPK = new TblProductcartPK(idcart, idproduct);
    }

    public TblProductcartPK getTblProductcartPK() {
        return tblProductcartPK;
    }

    public void setTblProductcartPK(TblProductcartPK tblProductcartPK) {
        this.tblProductcartPK = tblProductcartPK;
    }

    public Integer getPcquantity() {
        return pcquantity;
    }

    public void setPcquantity(Integer pcquantity) {
        this.pcquantity = pcquantity;
    }

    public TblProducts getTblProducts() {
        return tblProducts;
    }

    public void setTblProducts(TblProducts tblProducts) {
        this.tblProducts = tblProducts;
    }

    public TblShoppingcart getTblShoppingcart() {
        return tblShoppingcart;
    }

    public void setTblShoppingcart(TblShoppingcart tblShoppingcart) {
        this.tblShoppingcart = tblShoppingcart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblProductcartPK != null ? tblProductcartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductcart)) {
            return false;
        }
        TblProductcart other = (TblProductcart) object;
        if ((this.tblProductcartPK == null && other.tblProductcartPK != null) || (this.tblProductcartPK != null && !this.tblProductcartPK.equals(other.tblProductcartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unbosque.swii.shopping.model.TblProductcart[ tblProductcartPK=" + tblProductcartPK + " ]";
    }
    
}
