/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

/**
 *
 * @author srikanth.malyala
 */

@Entity
@Table(name = "paper_detail")
@Searchable
@XmlRootElement
public class PaperDetail extends BaseObject{
    
    private Long id;
    private Customer customer;
    private Long customerId;
    private  String paper;

    public PaperDetail() {
    }

    @Column(name = "paper", nullable = false)
    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }
    
    public PaperDetail(final Customer customer) {
        this.customer = customer;
    }

   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaperDetail other = (PaperDetail) obj;
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
