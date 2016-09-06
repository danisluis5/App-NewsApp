/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Category;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.ModelCategory;
import render.RenderComboBoxModelCat;

/**
 *
 * @author vuongluis
 */
public class ControllerCat extends AbstractTableModel{
    
    private JTable table;
    private ModelCategory model;
    private String[] cols = {
        "<html><center><p style='color:#00434a;font-weight:bold;'>STT</p></center></html>",
        "<html><p style='color:#00434a;font-weight:bold;'>Danh Mục Tin</p></html>"
    };
    private ArrayList<Category> alItem = new ArrayList<Category>();
    
    public ControllerCat(JTable table){
        this.table = table;
        model = new ModelCategory();
        alItem = model.getList();
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
        Category Item = alItem.get(rowIndex);
        Object object = null;
        switch(columnIndex){
            case 0:
                object = Item.getId();
                break;
            case 1:
                object = Item.getName();
                break;
        }
        return object;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Integer.class;
        }
        return super.getColumnClass(columnIndex); 
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex); 
    }
    
    public void loadTable(){
        
        this.table.setModel(this);
        this.table.setAutoCreateRowSorter(true);
        
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 12));
        table.setRowHeight(26);
        table.setFont(new Font("Tahoma",Font.PLAIN, 12));
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(600);
    }

    public int addItem(Category objCat) {
        // thêm vào database
        int result = model.addItem(objCat);
        // thêm vào model
        objCat.setId(result);
        alItem.add(objCat);
        this.fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount()-1, 0, true));
        return result;
    }

    public int deleteItem(Category objCat, int row) {
        // xóa trong database
        int result = model.delItem(objCat.getId());
        // xóa trong model
        int rowmodel = table.convertRowIndexToModel(row);
        alItem.remove(rowmodel);
        this.fireTableDataChanged();
        return result;
    }

    public int editItem(Category objCat, int row) {
        // sữa trong database
        int id = model.editItem(objCat);
        // sữa trong model
        objCat.setId(id);
        int rowModel=table.convertRowIndexToModel(row);
        alItem.set(rowModel,objCat);
        this.fireTableDataChanged();
        return id;
    }
    public void loadCategory(JComboBox<Category> cbDanhMuc, boolean isSearch, Category objCat) {
        cbDanhMuc.setModel(new RenderComboBoxModelCat(isSearch,objCat));
    }
}
