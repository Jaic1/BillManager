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
            String name;
            do {
                name = JOptionPane.showInputDialog("请输入新增分类的名称");
                if (name == null) return;
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(panel, "输入不能为空");
                }else break;
            }while (true);
            new CategoryService().add(name);
        }
        if(button == panel.bEdit){
            Category category = panel.getSelectedCategory();
            if(category == null){
                JOptionPane.showMessageDialog(panel,"请选择你要编辑的分类");
                return;
            }
            Object[] options = {"名称","单笔上限"};
            int opt = JOptionPane.showOptionDialog(panel,"请选择需要修改的项",null,
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(opt == 0){
                String name;
                do {
                    name = JOptionPane.showInputDialog("请输入新名称");
                    if (name == null) return;
                    if (name.length() == 0) {
                        JOptionPane.showMessageDialog(panel, "输入不能为空");
                    }else break;
                }while (true);
                category.setName(name);
                new CategoryService().update(category);
            }else if(opt == 1){
                String upper;
                Float upperBound;
                do {
                    upper = JOptionPane.showInputDialog("请输入新的单笔上限");
                    if (upper == null) return;
                    if (upper.length() == 0) {
                        JOptionPane.showMessageDialog(panel, "输入不能为空");
                    }else{
                        try{
                            upperBound = Float.parseFloat(upper);
                            break;
                        }catch (NumberFormatException ee){
                            JOptionPane.showMessageDialog(panel,"输入需要是浮点数");
                        }
                    }
                }while (true);
                category.setUpperBound(upperBound);
                new CategoryService().update(category);
            }
        }
        if(button == panel.bDelete){
            Category category = panel.getSelectedCategory();
            if(category == null){
                JOptionPane.showMessageDialog(panel,"请选择你要删除的分类");
                return;
            }
            if(category.getNum() != 0){
                JOptionPane.showMessageDialog(panel,"无法删除，请在未消费的情况下删除（每月更新）");
                return;
            }
            if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,"确认删除？")){
                return;
            }
            new CategoryService().delete(category.getId());
        }

        panel.updateData();
    }
}
