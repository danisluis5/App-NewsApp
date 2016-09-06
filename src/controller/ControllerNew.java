/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.News;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.ModelNews;
import render.RendererCellDate;

/**
 *
 * @author vuongluis
 */
public class ControllerNew extends AbstractTableModel{
    
    private ArrayList<News> listTT = new ArrayList<News>();
    private String[] cols = {
        "<html><strong>STT</strong></html>",
        "<html><strong>Tên Tin Tức</strong></html>",
        "<html><strong>Mô Tả</strong></html>",
        "<html><strong>Ngày Đăng</strong></html>",
        "<html><strong>Danh Mục</strong></html>"
    };
    private JTable tbNew;
    /** database **/
    private ModelNews model;
    
    public ControllerNew(JTable table){
        tbNew = table;
        model = new ModelNews();
        listTT = model.getList();
    }
    @Override
    public int getRowCount() {
        return listTT.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        News object = listTT.get(rowIndex);
        Object result = null;
        switch(columnIndex){
            case 0:
                result = object.getId_news();
                break;
            case 1:
                result = object.getName();
                break;
            case 2:
                result = object.getPreview_text();
                break;
            case 3:
                result  = object.getDate_create();
                break;
            case 4:
                result = object.getNamecat(); // get name but column default is object 
                break;
        }
        return result;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Integer.class;
        }
        return  super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
    /** Setting TABLE contain data **/
    public void loadTable(){
        tbNew.setModel(this);
        tbNew.setAutoCreateRowSorter(true);
        
        tbNew.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tbNew.getTableHeader().setFont(new Font("Tahoma",Font.BOLD, 12));
        tbNew.setRowHeight(26);
        tbNew.setFont(new Font("Tahoma",Font.PLAIN, 12));
        
        tbNew.getColumnModel().getColumn(0).setPreferredWidth(20);
        tbNew.getColumnModel().getColumn(1).setPreferredWidth(280);
        tbNew.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbNew.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbNew.getColumnModel().getColumn(3).setCellRenderer(new RendererCellDate());
        
        tbNew.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    public int addItem(News tinTuc) {
        //thêm vào database
        int id = model.addItem(tinTuc);
        tinTuc.setId_news(id);
        //thêm vào model
        listTT.add(tinTuc);
        this.fireTableDataChanged();
        //cuộn đến cuối dòng
        tbNew.scrollRectToVisible(tbNew.getCellRect(this.getRowCount()-1, 0, true));
        return id;
    }

    public int delItem(int id, int row) {
        // xóa trong database 
        int result = model.delItem(id);
        // xóa trong model
        int rowmodel = tbNew.convertRowIndexToModel(row);
        listTT.remove(rowmodel);
        this.fireTableDataChanged();
        return result;
    }

    public int editItem(News tinTuc,int row) {
        // cập nhật trong database
        int id=model.editItem(tinTuc);
        // cập nhật trong model
        int rowModel=tbNew.convertRowIndexToModel(row);
        listTT.set(rowModel,tinTuc);
        this.fireTableDataChanged();
        return id;
    }
}
