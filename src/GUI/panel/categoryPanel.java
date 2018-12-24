package GUI.panel;

import Entity.Category;
import GUI.listener.CategoryListener;
import GUI.model.categoryTableModel;
import Service.CategoryService;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class categoryPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static categoryPanel instance = new categoryPanel();

    public categoryTableModel categoryModel = new categoryTableModel();
    public JTable table = new JTable(categoryModel);
    public JButton bAdd = new JButton("添加");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    private categoryPanel(){
        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
        addListener();
    }

    private JScrollPane center(){
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane sp = new JScrollPane(table);
        return sp;
    }
    private JPanel south(){
        GUIUtil.setColor(colorUtil.blueColor,bAdd,bEdit,bDelete);
        JPanel p = new JPanel();
        p.add(bAdd);
        p.add(bEdit);
        p.add(bDelete);
        return p;
    }

    private void addListener(){
        CategoryListener categoryListener = new CategoryListener();
        bAdd.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bDelete.addActionListener(categoryListener);
    }

    //获取单行即某一分类
    public Category getSelectedCategory(){
        try{
            return categoryModel.cs.get(table.getSelectedRow());
        }catch (Exception e){
            return null;
        }
    }

    //更新
    public void update(){
        categoryModel.cs = new CategoryService().list();
        table.updateUI();
        table.setRowSelectionInterval(0,0);
        if(categoryModel.cs.size() == 0){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(categoryPanel.instance,1.0f);
    }
}
