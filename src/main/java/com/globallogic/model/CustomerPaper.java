/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import javax.persistence.*;

/**
 *
 * @author Srikanth.Malyala
 */

@Entity
@Table(name = "customer_paper")
public class CustomerPaper {
    
    private int id;
    private Customer customer;
    private Paper paper;
    private Long customerId;
    private Long paperId;

    
    
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

   @Transient
    public Long getCustomerId() {
        return customerId;
    }

   
   public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

   @ManyToOne
    @JoinColumn(name = "paper_id", referencedColumnName = "id", nullable = false)
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Item item) {
        this.paper = paper;
    }

    @Transient
    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long Id) {
        this.paperId = Id;
    }
        
}
