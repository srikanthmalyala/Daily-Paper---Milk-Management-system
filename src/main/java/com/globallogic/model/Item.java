package com.globallogic.model;


import java.io.Serializable;
import javax.persistence.*;

@Entity

public class Item implements Serializable {
    
    
    private String item;   
    private String cost;
    private String deleteItem;
    private long  id;
   
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false, length = 50, unique = true)
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
     public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    
    

  
     @Transient
    public String getDeleteItem() {
        deleteItem = "<a href=deleteMilkItem?id=" + id + " onClick='return confirmDelete()' title='Delete User'><img src='/images/delete.jpg' width='18px' height='18px'/></a>";
        return deleteItem;
    }
     
      @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        return true;
    }

   
    
}
