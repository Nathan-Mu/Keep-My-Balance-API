/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nathan
 */
@Entity
@Table(name = "ACCOUNT_TRANSACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountTransaction.findAll", query = "SELECT a FROM AccountTransaction a")
    , @NamedQuery(name = "AccountTransaction.findByTransactionId", query = "SELECT a FROM AccountTransaction a WHERE a.transactionId = :transactionId")
    , @NamedQuery(name = "AccountTransaction.findByAmount", query = "SELECT a FROM AccountTransaction a WHERE a.amount = :amount")
    , @NamedQuery(name = "AccountTransaction.findByIssueTime", query = "SELECT a FROM AccountTransaction a WHERE a.issueTime = :issueTime")})
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISSUE_TIME")
    @Temporal(TemporalType.DATE)
    private Date issueTime;
    @JoinColumn(name = "OUTPUT_ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    @ManyToOne(optional = false)
    private Account outputAccountId;
    @JoinColumn(name = "INPUT_ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    @ManyToOne(optional = false)
    private Account inputAccountId;

    public AccountTransaction() {
    }

    public AccountTransaction(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public AccountTransaction(Integer transactionId, BigDecimal amount, Date issueTime) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.issueTime = issueTime;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Account getOutputAccountId() {
        return outputAccountId;
    }

    public void setOutputAccountId(Account outputAccountId) {
        this.outputAccountId = outputAccountId;
    }

    public Account getInputAccountId() {
        return inputAccountId;
    }

    public void setInputAccountId(Account inputAccountId) {
        this.inputAccountId = inputAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountTransaction)) {
            return false;
        }
        AccountTransaction other = (AccountTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "restclient.AccountTransaction[ transactionId=" + transactionId + " ]";
    }
    
}
