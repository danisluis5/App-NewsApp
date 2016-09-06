/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import bean.Category;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.ModelCategory;

/**
 *
 * @author vuongluis
 */
public class RenderComboBoxModelCat implements ComboBoxModel<Category> {
    
    private ArrayList<Category> list = new ArrayList<>();
    private ModelCategory model;
    private Category selectedItem = new Category();
    
    public RenderComboBoxModelCat(boolean isSearch, Category objCat) {
        model = new ModelCategory();
        list = model.getList();
        if(isSearch){
            list.add(0,new Category(0, "--- Chọn danh mục tìm kiếm ---"));
        }
        if(objCat == null){
            selectedItem = list.get(0);
        }else{
            selectedItem = objCat;
        }
    }
    // DEVELOPMENT - IDEAL 
//    public RenderComboBoxModelCat(){
//        model = new ModelCategory();
//        list = model.getList();
//        selectedItem = list.get(0);
//    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Category getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
}
