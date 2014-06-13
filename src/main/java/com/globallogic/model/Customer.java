/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import java.util.Date;
import java.util.List;
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
@Table(name = "customer")
@Searchable
@XmlRootElement
public class Customer extends BaseObject{
    
    
    private Long  id;
    private int deposit;
    private Long  customerId;
    private String firstName;  
    private String lastName;
    private String lineOne;
    private String lineTwo;
    private String lineThree;
    private Long phoneNo;
    private Date createdAt;   
    private boolean accountEnabled=true;
    private List<String> items;
    private List<String> papers;
    private String disableUser;

    
    @Transient
    public List<String> getPapers() {
        return papers;
    }

    public void setPapers(List<String> papers) {
        this.papers = papers;
    }
    
    
    

    @Transient
    public List<String> getItems() {
        return items;
    }

     
    public void setItems(List<String> items) {
        this.items = items;
    }
 
    
    
       @Transient
    public String getFullName() {
        return firstName+lastName;
    }

    
   
    public Customer(final Long  customerId) {
        this.customerId=customerId;
    }

    public Customer() {
        
    }
    
        @Transient
    public String getDisableUser() {
        disableUser = "<a href=/disableCustomer?id=" + id + " onClick='return confirmDelete()' title='Delete Customer'><img src='/images/delete.jpg' width='18px' height='18px'/></a><a href=/admin/customerForm?id=" + id + " title='Edit Customer'><img src='/images/edit.jpg' width='18px' height='18px'/></a>";
        return disableUser;
    }

    public void setDisableUser(String disableUser) {
        this.disableUser = disableUser;
    }



    @Column(name = "account_enabled", length = 50, nullable = false,columnDefinition="Decimal(1)")
    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    
    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }
    
    

    @Column(name = "created_at", length = 50)
    @Temporal(javax.persistence.TemporalType.DATE)
    @SearchableProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    @Column(name = "deposit", nullable = false)
    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
    
    
    @Column(name="customer_id",nullable = false, length = 50, unique = true)
    @SearchableId
     public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

     @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

     @Column(name = "line_one", nullable = false)
    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    @Column(name = "line_three", nullable = false)
    public String getLineThree() {
        return lineThree;
    }
     
     
    
    public void setLineThree(String lineThree) {
        this.lineThree = lineThree;
    }

    @Column(name = "line_two", nullable = false)
    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

     @Column(name = "phone_number", nullable = false)
    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

     
    
  public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }

        final Customer customer = (Customer) o;

        return !(customerId != null ? !customerId.equals(customer.getCustomerId()) : customer.getCustomerId()!= null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (customerId!= null ? customerId.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "";
    }
    
 }
