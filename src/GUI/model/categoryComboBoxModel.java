package GUI.model;

import Entity.Category;
import Service.CategoryService;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class categoryComboBoxModel implements ComboBoxModel<Category> {
    //Category记得写toString方法，否则分类无法正常显示
    public List<Category> categories = CategoryService.list();
    public Category categoryDefault;

    public categoryComboBoxModel(){
        if(!categories.isEmpty()){
            categoryDefault = categories.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        categoryDefault = (Category)anItem;
    }

    @Override
    public Object getSelectedItem() {
        if(!categories.isEmpty()){
            return categoryDefault;
        }
        else return null;
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public Category getElementAt(int index) {
        try{
            return categories.get(index);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
