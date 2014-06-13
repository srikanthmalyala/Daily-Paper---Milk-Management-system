/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.dao.hibernate;

import com.globallogic.dao.ItemDao;
import com.globallogic.model.Item;
import org.springframework.stereotype.Repository;

/**
 *
 * @author srikanth.malyala
 */
@Repository("itemDao")
public class ItemDaoHibernate extends GenericDaoHibernate<Item,Long> implements ItemDao{

    public ItemDaoHibernate() {
        super(Item.class);
    }
    
  
    public Item getItemByName(String itemName)
    {
        return (Item) getHibernateTemplate().find("from Item where item=?",itemName).get(0);
    }
    
 }
