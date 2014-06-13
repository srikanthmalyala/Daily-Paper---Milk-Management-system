package com.globallogic.webapp.controller;

import java.util.Date;

/**
 * Command class to handle uploading of a file.
 *
 * <p> <a href="FileUpload.java.html"><i>View Source</i></a> </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class FileUpload {

    private Date forDate;
    private byte[] file;

    public void setFile(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }

    public Date getForDate() {
        return forDate;
    }

    public void setForDate(Date forDate) {
        this.forDate = forDate;
    }
}