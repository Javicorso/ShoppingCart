/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huber
 */
@Entity
@Table(name = "grupo6.tbl_shoppingcart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblShoppingcart.findAll", query = "SELECT t FROM TblShoppingcart t"),
    @NamedQuery(name = "TblShoppingcart.findByIdcart", query = "SELECT t FROM TblShoppingcart t WHERE t.idcart = :idcart"),
    @NamedQuery(name = "TblShoppingcart.findByCartdate", query = "SELECT t FROM TblShoppingcart t WHERE t.cartdate = :cartdate"),
    @NamedQuery(name = "TblShoppingcart.findByCartstate", query = "SELECT t FROM TblShoppingcart t WHERE t.iduser = :idUser and t.cartstate = true")})
public class TblShoppingcart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcart")
    private Integer idcart;
    @Column(name = "cartdate")
    @Temporal(TemporalType.DATE)
    private Date cartdate;
    @Column(name = "cartstate")
    private Boolean cartstate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblShoppingcart")
    private List<TblProductcart> tblProductcartList;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private TblUser iduser;

    public TblShoppingcart() {
    }

    public TblShoppingcart(Integer idcart) {
        this.idcart = idcart;
    }

    public Integer getIdcart() {
        return idcart;
    }

    public void setIdcart(Integer idcart) {
        this.idcart = idcart;
    }

    public Date getCartdate() {
        return cartdate;
    }

    public void setCartdate(Date cartdate) {
        this.cartdate = cartdate;
    }

    public Boolean getCartstate() {
        return cartstate;
    }

    public void setCartstate(Boolean cartstate) {
        this.cartstate = cartstate;
    }

    @XmlTransient
    public List<TblProductcart> getTblProductcartList() {
        return tblProductcartList;
    }

    public void setTblProductcartList(List<TblProductcart> tblProductcartList) {
        this.tblProductcartList = tblProductcartList;
    }

    public TblUser getIduser() {
        return iduser;
    }

    public void setIduser(TblUser iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcart != null ? idcart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblShoppingcart)) {
            return false;
        }
        TblShoppingcart other = (TblShoppingcart) object;
        if ((this.idcart == null && other.idcart != null) || (this.idcart != null && !this.idcart.equals(other.idcart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unbosque.swii.shopping.model.TblShoppingcart[ idcart=" + idcart + " ]";
    }
    
}
