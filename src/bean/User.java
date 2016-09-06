/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author vuongluis
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private boolean active;

    public User(int id, String username, String password, String fullname, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.active = active;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
