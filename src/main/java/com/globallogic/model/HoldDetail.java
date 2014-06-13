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
 * This class represents the basic "hold" object in this application that
 * allows for hold management.
 *
 * @author sheetal.sisodiya
 */
@Entity
@Table(name = "hold_detail")
@Searchable
@XmlRootElement
public class HoldDetail extends BaseObject {

    private Long id;
    private Customer customer;
    private Long customerId;
    private Date holdFrom;
    private Date holdTo;
    private int days; 
    private String holdActions;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public HoldDetail() {
    }

    /**
     * Create a new instance and set the customer.
     *
     * @param customer login Customer detail.
     */
    public HoldDetail(final Customer customer) {
        this.customer = customer;
    }

    @Column(length = 50, nullable = false)
    @SearchableProperty
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    @Transient
    public Long getCustomerId() {
        if (customer != null) {
            customerId = customer.getCustomerId();
        }
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    @Column(name = "hold_from", length = 50, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @SearchableProperty
    public Date getHoldFrom() {
        return holdFrom;
    }

    public void setHoldFrom(Date holdFrom) {
        this.holdFrom = holdFrom;
    }

    @SearchableProperty
    @Column(name = "hold_to", length = 50, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getHoldTo() {
        return holdTo;
    }

    public void setHoldTo(Date holdTo) {
        this.holdTo = holdTo;
    }

   

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Transient
    public String getholdActions() {
        holdActions = "<a href=/admin/holdForm?id=" + id + "&eid=" + customer.getId() + "&from=list  title='hold Edit'><img src='/images/edit.jpg' width='18px' height='18px'/></a><a href=/holdActions?action=delete&id=" + id + "&eid=" + customer.getId() + " onClick='return confirmDelete()'  title='Delete Hold'><img src='/images/delete.jpg' width='18px' height='18px'/></a>";
        return holdActions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoldDetail other = (HoldDetail) obj;
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
        return "HoldDetail{" + "customer=" + customer + '}';
    }
}
