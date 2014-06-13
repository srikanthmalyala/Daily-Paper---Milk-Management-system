package com.globallogic.dao;




import com.globallogic.dao.GenericDao;
import com.globallogic.model.Item;
import com.globallogic.model.Paper;
import java.util.ArrayList;

public interface ItemDao extends GenericDao<Item,Long> {
    
     public Item getItemByName(String itemName);
    
}
