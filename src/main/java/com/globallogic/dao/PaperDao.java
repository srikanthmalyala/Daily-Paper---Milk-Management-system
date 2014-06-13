/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao;

import com.globallogic.dao.hibernate.GenericDaoHibernate;
import com.globallogic.model.Paper;
import java.io.Serializable;

/**
 *
 * @author srikanth.malyala
 */



public interface PaperDao extends GenericDao<Paper,Long> {
    
}
