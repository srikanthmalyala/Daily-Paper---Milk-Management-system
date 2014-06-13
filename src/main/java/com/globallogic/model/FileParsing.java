/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.model;

/**
 * This class mainly used to represents the basic "fileParsing" object in this
 * application that allows to display upload status of user or leave.
 *
 * @author XPMUser
 */
public class FileParsing {

    private int count;
    private String record;
    private String reason;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public FileParsing() {
    }

    /**
     * Create a new instance and set all the attribute.
     *
     * @param count index no for list.
     * @param record file record in uploaded file
     * @param reason reason not to upload record in database
     */
    public FileParsing(int count, String record, String reason) {
        this.count = count;
        this.record = record;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}