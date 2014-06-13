/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service;

import com.globallogic.dao.PaperDao;
import com.globallogic.model.Paper;

/**
 *
 * @author srikanth.malyala
 */
public interface PaperManager extends GenericManager<Paper,Long>  {
    
    
    void setPaperDao(PaperDao paperDao);
    
}
