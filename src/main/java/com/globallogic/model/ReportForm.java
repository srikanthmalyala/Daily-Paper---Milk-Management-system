/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

import java.util.Date;
import javax.persistence.Temporal;

/**
 * This class represents the basic "reportform" object in this application that
 * allows to create report of leave for user.
 *
 * @author sheetal.sisodiya
 */
public class ReportForm {

    private Long empId;
    private Date leaveFrom;
    private Date leaveTo;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public ReportForm() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long id) {
        this.empId = id;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(Date leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(Date leaveTo) {
        this.leaveTo = leaveTo;
    }
}
