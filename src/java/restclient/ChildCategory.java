/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author nathan
 */
@Entity
@Table(name = "CHILD_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChildCategory.findAll", query = "SELECT c FROM ChildCategory c")
    , @NamedQuery(name = "ChildCategory.findByChildCategoryId", query = "SELECT c FROM ChildCategory c WHERE c.childCategoryId = :childCategoryId")
    , @NamedQuery(name = "ChildCategory.findByChildName", query = "SELECT c FROM ChildCategory c WHERE c.childName = :childName")})
public class ChildCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CHILD_CATEGORY_ID")
    private Integer childCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CHILD_NAME")
    private String childName;
    @JoinColumn(name = "PARENT_CATEGORY_ID", referencedColumnName = "PARENT_CATEGORY_ID")
    @ManyToOne(optional = false)
    private ParentCategory parentCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "childCategoryId")
    private Collection<FundChange> fundChangeCollection;

    public ChildCategory() {
    }

    public ChildCategory(Integer childCategoryId) {
        this.childCategoryId = childCategoryId;
    }

    public ChildCategory(Integer childCategoryId, String childName) {
        this.childCategoryId = childCategoryId;
        this.childName = childName;
    }

    public Integer getChildCategoryId() {
        return childCategoryId;
    }

    public void setChildCategoryId(Integer childCategoryId) {
        this.childCategoryId = childCategoryId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public ParentCategory getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(ParentCategory parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @XmlTransient
    public Collection<FundChange> getFundChangeCollection() {
        return fundChangeCollection;
    }

    public void setFundChangeCollection(Collection<FundChange> fundChangeCollection) {
        this.fundChangeCollection = fundChangeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (childCategoryId != null ? childCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChildCategory)) {
            return false;
        }
        ChildCategory other = (ChildCategory) object;
        if ((this.childCategoryId == null && other.childCategoryId != null) || (this.childCategoryId != null && !this.childCategoryId.equals(other.childCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restclient.ChildCategory[ childCategoryId=" + childCategoryId + " ]";
    }
    
}
