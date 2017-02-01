package com.ironyard.com.ironyard.service;

import com.google.gson.Gson;
import com.ironyard.data.GroceryItem;
import com.ironyard.data.IronYardUser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class GroceryService {

    public List<GroceryItem> getAll(IronYardUser gUser){
        ArrayList<GroceryItem> gList = new ArrayList<GroceryItem>();
        Connection con = null;
        try {
            DbService dbService = new DbService();
            con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from \"Store\".items where user_id = ?");
            // set values
            ps.setInt(1, gUser.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                long id = rs.getLong("item_id");
                String name = rs.getString("item_name");
                double price = rs.getDouble("item_price");
                int isle = rs.getInt("item_isle");
                int qty = rs.getInt("item_qty");
                int category = rs.getInt("item_category");
                System.out.println("osman "+id+" "+name+" "+price+" "+isle+" "+qty+" "+category);
                gList.add(new GroceryItem(id,name,price,qty,isle,category));
            }
            //
            rs.close();
            ps.close();
        }catch(Throwable t){
            t.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return gList;
    }

    public GroceryItem save(IronYardUser user, GroceryItem aGroceryToSave){
        Connection con = null;
        try {
            DbService dbService = new DbService();
            con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO \"Store\".items " +
                            "(item_name, item_price, item_isle, item_qty, item_category, user_id)  " +
                            "VALUES (?, ?, ?, ?,?,?);", Statement.RETURN_GENERATED_KEYS);
            // set values
            ps.setString(1,aGroceryToSave.getName());
            ps.setDouble(2,aGroceryToSave.getPrice());
            ps.setInt(3,aGroceryToSave.getIsle());
            ps.setInt(4,aGroceryToSave.getQuantity());
            ps.setInt(5,aGroceryToSave.getCategory());
            ps.setInt(6, user.getId());
            ps.execute();

            // populate id that was generated from DB
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                aGroceryToSave.setId(rs.getLong("item_id"));
            }
            //
            rs.close();
            ps.close();
        }catch(Throwable t){
            t.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return aGroceryToSave;

    }
}
