/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service.impl;

import com.globallogic.dao.ItemDao;
import com.globallogic.model.Item;
import com.globallogic.service.ItemManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author srikanth.malyala
 */

@Service("itemService")
public class ItemDetailManagerImpl extends GenericManagerImpl<Item, Long> implements ItemManager {
    
    
    private ItemDao itemDao; 
  

   @Autowired
    public void setPaperDao(ItemDao itemDao) {
        this.dao=itemDao;
        this.itemDao=itemDao;
     }

   
   @Autowired 
   public ItemDetailManagerImpl(ItemDao itemDao) {
        super(itemDao);
        this.itemDao=itemDao;
   }
    
     public Item getItemByName(String itemName){
         
         return itemDao.getItemByName(itemName);
         
     }
    
}
