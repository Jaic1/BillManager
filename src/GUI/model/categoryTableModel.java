package GUI.model;

import Entity.Category;
import Service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class categoryTableModel extends AbstractTableModel {
    public String[] columnNames = new String[]{"分类名称","消费次数","消费总额","单笔上限"};
    public List<Category> cs = new CategoryService().list();

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnNames[columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Category category = cs.get(rowIndex);
            if(category == null)return null;
            if(columnIndex == 0)return category.getName();
            else if(columnIndex == 1)return category.getNum();
            else if(columnIndex == 2)return category.getSum();
            else if(columnIndex == 3)return category.getUpperBound();
        }catch (IndexOutOfBoundsException e){
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
