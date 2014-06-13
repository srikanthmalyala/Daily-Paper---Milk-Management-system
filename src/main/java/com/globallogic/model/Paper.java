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

public class Paper implements Serializable {
    
    private String paperName;
    private String deleteItem;
    private long id;
    private float monday;
    private float tuesday;
    private float wednesday;
    private float thursday;
    private float friday;
    private float saturday;
    private float sunday;
    
  
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public float getFriday() {
        return friday;
    }

    public void setFriday(float friday) {
        this.friday = friday;
    }

    public float getMonday() {
        return monday;
    }

    public void setMonday(float monday) {
        this.monday = monday;
    }

    @Column(name="paper_name")
    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public float getSaturday() {
        return saturday;
    }

    public void setSaturday(float saturday) {
        this.saturday = saturday;
    }

    public float getSunday() {
        return sunday;
    }

    public void setSunday(float sunday) {
        this.sunday = sunday;
    }

    public float getThursday() {
        return thursday;
    }

    public void setThursday(float thursday) {
        this.thursday = thursday;
    }

    public float getTuesday() {
        return tuesday;
    }

    public void setTuesday(float tuesday) {
        this.tuesday = tuesday;
    }

    public float getWednesday() {
        return wednesday;
    }

    public void setWednesday(float wednesday) {
        this.wednesday = wednesday;
    }

  
     @Transient
    public String getDeleteItem() {
        deleteItem = "<a href=deletePaperItem?id=" + id + " onClick='return confirmDelete()' title='Delete User'><img src='/images/delete.jpg' width='18px' height='18px'/></a>";
        return deleteItem;
    }
    
}
