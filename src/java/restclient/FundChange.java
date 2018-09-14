/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nathan
 */
@Entity
@Table(name = "FUND_CHANGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FundChange.findAll", query = "SELECT f FROM FundChange f")
    , @NamedQuery(name = "FundChange.findByChangeId", query = "SELECT f FROM FundChange f WHERE f.changeId = :changeId")
    , @NamedQuery(name = "FundChange.findByChangeType", query = "SELECT f FROM FundChange f WHERE f.changeType = :changeType")
    , @NamedQuery(name = "FundChange.findByAmount", query = "SELECT f FROM FundChange f WHERE f.amount = :amount")
    , @NamedQuery(name = "FundChange.findByChangeDate", query = "SELECT f FROM FundChange f WHERE f.changeDate = :changeDate")
    , @NamedQuery(name = "FundChange.findByLocationName", query = "SELECT f FROM FundChange f WHERE f.locationName = :locationName")
    , @NamedQuery(name = "FundChange.findByLocationLatitude", query = "SELECT f FROM FundChange f WHERE f.locationLatitude = :locationLatitude")
    , @NamedQuery(name = "FundChange.findByLocationLongitude", query = "SELECT f FROM FundChange f WHERE f.locationLongitude = :locationLongitude")
    , @NamedQuery(name = "FundChange.findByDescription", query = "SELECT f FROM FundChange f WHERE f.description = :description")})
public class FundChange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CHANGE_ID")
    private Integer changeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CHANGE_TYPE")
    private String changeType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHANGE_DATE")
    @Temporal(TemporalType.DATE)
    private Date changeDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOCATION_NAME")
    private String locationName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_LATITUDE")
    private double locationLatitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_LONGITUDE")
    private double locationLongitude;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    @ManyToOne(optional = false)
    private Account accountId;
    @JoinColumn(name = "CHILD_CATEGORY_ID", referencedColumnName = "CHILD_CATEGORY_ID")
    @ManyToOne(optional = false)
    private ChildCategory childCategoryId;

    public FundChange() {
    }

    public FundChange(Integer changeId) {
        this.changeId = changeId;
    }

    public FundChange(Integer changeId, String changeType, double amount, Date changeDate, String locationName, double locationLatitude, double locationLongitude) {
        this.changeId = changeId;
        this.changeType = changeType;
        this.amount = amount;
        this.changeDate = changeDate;
        this.locationName = locationName;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public ChildCategory getChildCategoryId() {
        return childCategoryId;
    }

    public void setChildCategoryId(ChildCategory childCategoryId) {
        this.childCategoryId = childCategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (changeId != null ? changeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FundChange)) {
            return false;
        }
        FundChange other = (FundChange) object;
        if ((this.changeId == null && other.changeId != null) || (this.changeId != null && !this.changeId.equals(other.changeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restclient.FundChange[ changeId=" + changeId + " ]";
    }
    
}
