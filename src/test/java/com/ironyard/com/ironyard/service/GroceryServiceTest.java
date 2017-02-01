package com.ironyard.com.ironyard.service;

import com.ironyard.data.GroceryItem;
import com.ironyard.data.IronYardUser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class GroceryServiceTest {
    @Test
    public void getAll() throws Exception {
        GroceryService gService = new GroceryService();
        IronYardUser osman = new IronYardUser();
        osman.setUsername("canohibro@gmail.com");
        IronYardUser wail = new IronYardUser();
        osman.setUsername("wail.yousif@gmail.com");
        List<GroceryItem> lItemsOne = gService.getAll(osman);
        List<GroceryItem> lItemsTwo = gService.getAll(wail);
        assertNotEquals("Users must not share Lists", osman,wail);
    }

    @Test
    public void save() throws Exception {
        IronYardUser aUser = new IronYardUser();
        aUser.setUsername("skipper.jason@gmail.com");
        GroceryItem item = new GroceryItem();
        item.setName("Carrot");
        item.setIsle(7);
        item.setPrice(3.99);
        item.setQuantity(100);

        GroceryService gs = new GroceryService();
        gs.save(aUser, item);

        List<GroceryItem> allGs = gs.getAll(aUser);
        assertNotNull(allGs);
        assertEquals(1, allGs.size());
        GroceryItem foundItem = allGs.get(0);
        assertEquals(item.getName(), foundItem.getName());
        assertEquals(7, foundItem.getIsle());
        assertEquals(3.99, foundItem.getPrice(), .001);

    }

    @Test
    public void justShowItems(){
        IronYardUser aUser = new IronYardUser();
        aUser.setUsername("skipper.jason@gmail.com");

        GroceryService gs = new GroceryService();
        List<GroceryItem> allGs = gs.getAll(aUser);
        if(allGs!=null) {
            for (GroceryItem item : allGs) {
                System.out.print("found:" + item.getName());
            }
        }
    }

    @Test
    public void loadItems(){
        IronYardUser aUser = new IronYardUser();
        GroceryService gs = new GroceryService();
        aUser.setUsername("canohibro@gmail.com");
        GroceryItem item;
        for(int i = 1; i < 1001; i++ ){
            item = new GroceryItem();
            item.setName("ITEM "+i);
            item.setIsle(i%10 +1);
            item.setPrice(4.99);
            item.setQuantity(70);
            item.setCategory((i%5) +1);
            gs.save(aUser, item);
        }
    }
}