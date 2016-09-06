package model;

import bean.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.LibraryConnectDb;

public class ModelCategory {

    private LibraryConnectDb lcdb;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection conn;

    public ModelCategory() {
        lcdb = new LibraryConnectDb();
    }

    public ArrayList<Category> getList() {
        ArrayList<Category> alItem = new ArrayList<>();
        String sql = "SELECT * FROM category";

        conn = lcdb.getConnectMySQL();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                alItem.add(new Category(rs.getInt("id_cat"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelCategory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return alItem;
    }

    public int addItem(Category item) {
        int result = 0;
        conn = lcdb.getConnectMySQL();

        String sql = "INSERT INTO category(name) VALUES (?)";
        try {
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getName());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public int delItem(int cid) {
        int result = 0;
        conn = lcdb.getConnectMySQL();

        String sql = "DELETE FROM category WHERE id_cat = ? LIMIT 1";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cid);
            pst.executeUpdate();
            result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Category getItem(int cid) {
        conn = lcdb.getConnectMySQL();
        Category c = null;
        String sql = "SELECT * FROM category WHERE id_cat = ? LIMIT 1";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cid);
            rs = pst.executeQuery();
            if (rs.next()) {
                c = new Category(rs.getInt("id_cat"), rs.getString("name"));
            }
        } catch (SQLException e) {
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return c;
    }

    public int editItem(Category c) {
        int result = 0;
        conn = lcdb.getConnectMySQL();
        String sql = "UPDATE category SET name=? WHERE id_cat=? LIMIT 1";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, c.getName());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
            result = c.getId();
        } catch (SQLException e) {
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return result;
    }
}
