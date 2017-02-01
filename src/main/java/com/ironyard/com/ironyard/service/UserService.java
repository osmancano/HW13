package com.ironyard.com.ironyard.service;

import com.google.gson.Gson;
import com.ironyard.data.GroceryItem;
import com.ironyard.data.IronYardUser;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class UserService {

    public ArrayList<IronYardUser> getAllUsers(){
        ArrayList<IronYardUser> found = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try (FileReader fr = new FileReader(classLoader.getResource("users.json").getFile())) {

            Gson tmpG = new Gson();
            IronYardUser[] t = tmpG.fromJson(fr, IronYardUser[].class);
            if(t != null && t.length>0){
                found = new ArrayList<IronYardUser>(Arrays.asList(t));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return found;
    }

    public IronYardUser getUser(String email, String pass) {
        IronYardUser foundUser = null;
        List<IronYardUser> all = getAllUsers();
        for(IronYardUser tmpUser:all) {
            if (tmpUser.getUsername().equals(email) && tmpUser.getPassword().equals(pass)){
                foundUser = tmpUser;
            }
        }
        return foundUser;
    }

    public IronYardUser getUserFromDB(String email, String password){
        IronYardUser found = null;
        try {
            DbService dbService = new DbService();
            Connection con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"Store\".gusers where user_name= ? and password = ?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                found = new IronYardUser(rs.getInt("user_id"),rs.getString("user_name"), rs.getString("password"),rs.getString("display_name"));
                break;
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
        return found;
    }

    public IronYardUser save(IronYardUser gUser){
        Connection con = null;
        try {
            DbService dbService = new DbService();
            con = dbService.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO \"Store\".gusers " +
                            "(user_name, password, display_name)  " +
                            "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            // set values
            ps.setString(1,gUser.getUsername());
            ps.setString(2,gUser.getPassword());
            ps.setString(3,gUser.getDisplayname());
            ps.execute();

            // populate id that was generated from DB
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                gUser.setId(rs.getInt("user_id"));
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
        return gUser;

    }
}
