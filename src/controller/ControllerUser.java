/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.ModelUser;

/**
 *
 * @author vuongluis
 */
public class ControllerUser extends AbstractTableModel{

    private JTable table;
    private ModelUser model;
    private String[] cols = {
        "<html><center><p style='color:#00434a;'>STT</p></center></html>",
        "<html><p style='color:#00434a;'>Tên Đăng Nhập</p></html>",
        "<html><p style='color:#00434a;'>Họ Và Tên</p></html>",
        "<html><p style='color:#00434a;'>Kích Hoạt</p></html>"
    };
    
    private ArrayList<User> alItem = new ArrayList<User>();
    
    public ControllerUser(JTable table){
        this.table = table;
        model = new ModelUser();
        alItem = model.getList();
    }
    public ControllerUser(JTable table,User objUser){
        this.table = table;
        model = new ModelUser();
        alItem = model.getItem(objUser);
    }
    @Override
    public int getRowCount() {
        return alItem.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User Item = alItem.get(rowIndex);
        Object object = null;
        switch(columnIndex){
            case 0:
                object = Item.getId();
                break;
            case 1:
                object = Item.getUsername();
                break;
            case 2:
                object = Item.getFullname();
                break;
            case 3:
                object = Item.isActive();
                break;
        }
        return object;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Integer.class;
        }
        if(columnIndex == 3){
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); 
    }
    
    public void loadTable(){
        
        this.table.setModel(this);
        this.table.setAutoCreateRowSorter(true);
        ((JComponent)table.getDefaultRenderer(Boolean.class)).setOpaque(true);     
        
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 12));
        table.setRowHeight(26);
        table.setFont(new Font("Tahoma",Font.PLAIN, 12));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(500);
        table.getColumnModel().getColumn(3).setPreferredWidth(500);
    }

    public int addItem(User objUser, int row) {
        int result = 0;
        // thêm vào database
        result = model.addItem(objUser);
        // thêm vào model
        objUser.setId(result);
        alItem.add(objUser);
        this.fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount()-1, 0, true));
        return result;
    }

    public int deleteItem(User objUser, int row) {
        // xóa trong database
        int result = model.delItem(objUser);
        // xóa trong model
        int rowmodel = table.convertRowIndexToModel(row);
        alItem.remove(rowmodel);
        this.fireTableDataChanged();
        return result;
    }

    public int editItem(User objUser, int row) {
        // xóa trong database
        int result = model.editItem(objUser);
        // xóa trong model
        objUser.setId(result);
        int rowModel=table.convertRowIndexToModel(row);
        alItem.set(rowModel,objUser);
        this.fireTableDataChanged();
        return result;
    }
}
