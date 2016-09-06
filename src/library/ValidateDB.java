/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vuongluis
 */
public class ValidateDB {
    public boolean category_exits(String name){
        boolean isExist = false;
        int count = 0;
        Connection conn = new LibraryConnectDb().getConnectMySQL();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) AS CHECKA FROM category WHERE name = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                count = rs.getInt("CHECKA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(count > 0){
            isExist = true;
        }
        return isExist;
    }
    public boolean category_exitsname(String name,int id){
        boolean isExist = false;
        int count = 0;
        Connection conn = new LibraryConnectDb().getConnectMySQL();
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) AS CHECKB FROM category WHERE name = ? AND id_cat NOT IN(?)");
            pst.setString(1, name);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                count = rs.getInt("CHECKB");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(count > 0){
            isExist = true;
        }
        return isExist;
    }
    public boolean user_exist(String username){
        boolean isExist = false;
        int count = 0;
        Connection conn = new LibraryConnectDb().getConnectMySQL();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) AS CHECKA FROM users WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                count = rs.getInt("CHECKA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        isExist = ((count > 0)?true:false);
        return isExist;
    }
    public boolean user_exist(String username,int id){
        boolean isExist = false;
        int count = 0;
        Connection conn = new LibraryConnectDb().getConnectMySQL();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) AS CHECKB FROM users WHERE username = ? AND id_user NOT IN(?)");
            pst.setString(1, username);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                count = rs.getInt("CHECKB");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        isExist = ((count > 0)?true:false);
        return isExist;
    }
}
