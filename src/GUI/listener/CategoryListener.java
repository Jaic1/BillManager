package GUI.listener;

import Entity.Category;
import GUI.panel.categoryPanel;
import Service.CategoryService;
import Service.ConfigService;

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
            String upperBound;
            do {
                name = JOptionPane.showInputDialog("请输入新增分类的名称");
                if (name == null) return;
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(panel, "输入不能为空");
                }else break;
            }while (true);
            do {
                upperBound = JOptionPane.showInputDialog("请输入单笔上限");
                if(upperBound == null)return;
                if(upperBound.length() == 0){
                    JOptionPane.showMessageDialog(panel,"输入不能为空");
                }else break;
            }while (true);
            try {
                CategoryService.add(name, Float.parseFloat(upperBound));
            }catch (Exception e1){
                e1.printStackTrace();
            }
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
                CategoryService.update(category);
            }else if(opt == 1){
                if(ConfigService.modeValue.equals("安全模式")){
                    JOptionPane.showMessageDialog(panel,"安全模式下不允许修改单笔上限");
                    return;
                }

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
                CategoryService.update(category);
            }
        }
        if(button == panel.bDelete){
            Category category = panel.getSelectedCategory();
            if(category == null){
                JOptionPane.showMessageDialog(panel,"请选择你要删除的分类");
                return;
            }
            if(ConfigService.modeValue.equals("安全模式")){
                if(category.getNum() != 0){
                    JOptionPane.showMessageDialog(panel,"安全模式下无法删除在本月已消费的分类");
                    return;
                }
                if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,"确认删除？")){
                    return;
                }
                CategoryService.delete(category.getId());
            }
            else if(ConfigService.modeValue.equals("自由模式")){
                if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(panel,
                        "删除则相应分类下的消费记录均被删除，确认删除？")){
                    CategoryService.deleteRecordByCategory(category.getId());
                    CategoryService.delete(category.getId());
                }
            }
        }
        panel.updateData();
    }
}
