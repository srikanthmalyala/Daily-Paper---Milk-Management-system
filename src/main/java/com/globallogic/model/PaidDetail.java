/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

/**
 *
 * @author srikanth.malyala
 */
@Entity
@Table(name = "paid_detail")
@Searchable
@XmlRootElement
public class PaidDetail extends BaseObject {
    
    private Long id;
    private Customer customer;
    private Long customerId;
    private Date paidDate;
    private Float paidAmount;

     @Column(name = "paid_amount", nullable = false)
    public Float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Column(name = "paid_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @SearchableProperty
    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Transient
    public Long getCustomerId() {
        if (customer != null) {
            customerId = customer.getCustomerId();
        }
        return customerId;
    }

    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaidDetail other = (PaidDetail) obj;
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "LeaveDetail{" + "customer=" + customer + '}';
    }
    
    
}
