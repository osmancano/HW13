package com.ironyard.data;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class IronYardUser {
    private int id;
    private String username;
    private String password;
    private String displayname;

    public IronYardUser(){

    }
    public IronYardUser(int id, String username, String password, String displayname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
    }

    public IronYardUser(String username, String password, String displayname) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}
