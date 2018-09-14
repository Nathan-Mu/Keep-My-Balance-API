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
@Table(name = "PARENT_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentCategory.findAll", query = "SELECT p FROM ParentCategory p")
    , @NamedQuery(name = "ParentCategory.findByParentCategoryId", query = "SELECT p FROM ParentCategory p WHERE p.parentCategoryId = :parentCategoryId")
    , @NamedQuery(name = "ParentCategory.findByParentCategoryName", query = "SELECT p FROM ParentCategory p WHERE p.parentCategoryName = :parentCategoryName")})
public class ParentCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PARENT_CATEGORY_ID")
    private Integer parentCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PARENT_CATEGORY_NAME")
    private String parentCategoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentCategoryId")
    private Collection<ChildCategory> childCategoryCollection;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private UserInfo userId;

    public ParentCategory() {
    }

    public ParentCategory(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public ParentCategory(Integer parentCategoryId, String parentCategoryName) {
        this.parentCategoryId = parentCategoryId;
        this.parentCategoryName = parentCategoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    @XmlTransient
    public Collection<ChildCategory> getChildCategoryCollection() {
        return childCategoryCollection;
    }

    public void setChildCategoryCollection(Collection<ChildCategory> childCategoryCollection) {
        this.childCategoryCollection = childCategoryCollection;
    }

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentCategoryId != null ? parentCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentCategory)) {
            return false;
        }
        ParentCategory other = (ParentCategory) object;
        if ((this.parentCategoryId == null && other.parentCategoryId != null) || (this.parentCategoryId != null && !this.parentCategoryId.equals(other.parentCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restclient.ParentCategory[ parentCategoryId=" + parentCategoryId + " ]";
    }
    
}
