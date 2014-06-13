/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author srikanth.malyala
 */
@Entity
@Table(name = "customer_item")
public class CustomerItem extends BaseObject{
    
    
    private int id;
    private Customer customer;
    private Item item;
    private Long customerId;
    private Long itemId;

    
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
     @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    @ManyToOne
   
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

   
    @Transient
    
    public Long getItemId() {
        if (item != null) {
            itemId = item.getId();
        }
         return customerId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

  @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerItem other = (CustomerItem) obj;
        if (this.customer!= other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ShiftDetail{" + "user=" + customer + '}';
    }
    
    
    
}
