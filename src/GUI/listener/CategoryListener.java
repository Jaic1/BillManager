package GUI.listener;

import Entity.Category;
import GUI.panel.categoryPanel;
import Service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        categoryPanel panel = categoryPanel.instance;
        JButton button = (JButton)e.getSource();
        if(button == panel.bAdd){
            String name = JOptionPane.showInputDialog("请输入新增分类的名称");
            if(name.length() == 0){
                JOptionPane.showMessageDialog(panel,"输入不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        if(button == panel.bEdit){
            Category category = panel.getSelectedCategory();
            //JOptionPane.showOptionDialog(panel);
        }
        if(button == panel.bDelete){

        }

        panel.update();
    }
}
