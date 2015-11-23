/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unbosque.swii.shopping.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huber
 */
@Entity
@Table(name = "grupo6.tbl_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProducts.findAll", query = "SELECT t FROM TblProducts t"),
    @NamedQuery(name = "TblProducts.findByIdproduct", query = "SELECT t FROM TblProducts t WHERE t.idproduct = :idproduct"),
    @NamedQuery(name = "TblProducts.findByProductname", query = "SELECT t FROM TblProducts t WHERE t.productname = :productname"),
    @NamedQuery(name = "TblProducts.findByProductprice", query = "SELECT t FROM TblProducts t WHERE t.productprice = :productprice")})
public class TblProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduct")
    private Integer idproduct;
    @Size(max = 100)
    @Column(name = "productname")
    private String productname;
    @Column(name = "productprice")
    private Integer productprice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProducts")
    private List<TblProductcart> tblProductcartList;

    public TblProducts() {
    }

    public TblProducts(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getProductprice() {
        return productprice;
    }

    public void setProductprice(Integer productprice) {
        this.productprice = productprice;
    }

    @XmlTransient
    public List<TblProductcart> getTblProductcartList() {
        return tblProductcartList;
    }

    public void setTblProductcartList(List<TblProductcart> tblProductcartList) {
        this.tblProductcartList = tblProductcartList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduct != null ? idproduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProducts)) {
            return false;
        }
        TblProducts other = (TblProducts) object;
        if ((this.idproduct == null && other.idproduct != null) || (this.idproduct != null && !this.idproduct.equals(other.idproduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unbosque.swii.shopping.model.TblProducts[ idproduct=" + idproduct + " ]";
    }
    
}
