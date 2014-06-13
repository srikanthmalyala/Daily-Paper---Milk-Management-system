/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.PaperDao;
import com.globallogic.model.Paper;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author srikanth.malyala
 */
@Repository("paperDao")
public class PaperDaoHibernate extends GenericDaoHibernate<Paper, Long> implements PaperDao{

    public PaperDaoHibernate() {
        super(Paper.class);
    }
    
    
 }
