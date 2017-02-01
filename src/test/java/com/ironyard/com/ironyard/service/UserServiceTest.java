package com.ironyard.com.ironyard.service;

import com.ironyard.data.IronYardUser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class UserServiceTest {

    @Test
    public void getAllUsers() throws Exception {
        /*
        UserService uServ = new UserService();
        List<IronYardUser> users = uServ.getAllUsers();
        assertNotNull("Users list was null", users);
        assertEquals("User list size was wrone", 1, users.size());
        assertNotNull("displayname was null", users.get(0).getDisplayname());
        assertNotNull("pass was null", users.get(0).getPassword());
        assertNotNull("username was null", users.get(0).getUsername());
        */
    }

    @Test
    public void save() throws Exception{
        UserService uServ = new UserService();
        IronYardUser guser = new IronYardUser("jason.skipper@gmail.com","12345","Jason");
        IronYardUser saved = uServ.save(guser);

    }

}