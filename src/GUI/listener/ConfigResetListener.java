package GUI.listener;

import GUI.panel.configPanel;
import Service.ConfigService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigResetListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        configPanel panel = configPanel.instance;
        if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,"确认重置？")){
            return;
        }
        Object[] options = {"是","否"};
        int obj = JOptionPane.showOptionDialog(panel,"是否保留消费分类设置？",null,
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if(obj == 0){
            ConfigService.resetWithCategoryConserved();
        }else if(obj == 1){
            ConfigService.resetWithoutCategoryConserved();
        }else return;
        panel.updateData();
        JOptionPane.showMessageDialog(panel,"重置成功");
    }
}
