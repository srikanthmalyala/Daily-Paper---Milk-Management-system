/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.GenericDao;
import com.globallogic.dao.PaperDao;
import com.globallogic.model.Paper;
import com.globallogic.service.PaperManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author srikanth.malyala
 */

@Service("paperService")
public class PaperDetailManagerImpl extends GenericManagerImpl<Paper, Long> implements PaperManager {
    
    
    private PaperDao paperDao; 
  

   @Autowired
    public void setPaperDao(PaperDao paperDao) {
        this.dao=paperDao;
        this.paperDao=paperDao;
     }

   
   @Autowired 
   public PaperDetailManagerImpl(PaperDao paperDao) {
        super(paperDao);
        this.paperDao=paperDao;
    }
    
    
    
}
