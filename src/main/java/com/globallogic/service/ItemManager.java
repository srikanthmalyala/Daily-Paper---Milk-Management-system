/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.service;

import com.globallogic.dao.ItemDao;
import com.globallogic.dao.PaperDao;
import com.globallogic.model.Item;
import com.globallogic.model.Paper;

/**
 *
 * @author srikanth.malyala
 */
public interface ItemManager extends GenericManager<Item,Long>  {
    
    
    void setPaperDao(ItemDao milkDao);
    Item getItemByName(String itemName);
    
}
