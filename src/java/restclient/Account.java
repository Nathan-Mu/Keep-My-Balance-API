/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId")
    , @NamedQuery(name = "Account.findByAccountType", query = "SELECT a FROM Account a WHERE a.accountType = :accountType")
    , @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")})
public class Account implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outputAccountId")
    private Collection<AccountTransaction> accountTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inputAccountId")
    private Collection<AccountTransaction> accountTransactionCollection1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<FundChange> fundChangeCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private UserInfo userId;

    public Account() {
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Account(Integer accountId, String accountType, BigDecimal balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restclient.Account[ accountId=" + accountId + " ]";
    }

    @XmlTransient
    public Collection<FundChange> getFundChangeCollection() {
        return fundChangeCollection;
    }

    public void setFundChangeCollection(Collection<FundChange> fundChangeCollection) {
        this.fundChangeCollection = fundChangeCollection;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection() {
        return accountTransactionCollection;
    }

    public void setAccountTransactionCollection(Collection<AccountTransaction> accountTransactionCollection) {
        this.accountTransactionCollection = accountTransactionCollection;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection1() {
        return accountTransactionCollection1;
    }

    public void setAccountTransactionCollection1(Collection<AccountTransaction> accountTransactionCollection1) {
        this.accountTransactionCollection1 = accountTransactionCollection1;
    }
    
}
