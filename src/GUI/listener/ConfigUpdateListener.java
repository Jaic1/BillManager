package GUI.listener;

import Entity.Config;
import GUI.panel.configPanel;
import Service.ConfigService;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigUpdateListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        configPanel panel = configPanel.instance;
        if(!GUIUtil.checkNumber(panel.tBudget,panel.lBudget.getText())){
            return;
        }

        if(!panel.bSafe.isSelected()&&!panel.bFree.isSelected()){
            JOptionPane.showMessageDialog(panel,"请选择模式");
            panel.bSafe.setSelected(true);
            return;
        }

        String mysqlPath = panel.tMysqlPath.getText();
        if(mysqlPath.length() != 0){
            File commandFile = new File(mysqlPath);
            if(!commandFile.exists()){
                JOptionPane.showMessageDialog(panel,"mysql路径不正确");
                panel.tMysqlPath.grabFocus();
                return;
            }
        }

        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget,panel.tBudget.getText());
        cs.update(ConfigService.mode,panel.bSafe.isSelected()?panel.bSafe.getText():panel.bFree.getText());
        cs.update(ConfigService.mysqlPath,panel.tMysqlPath.getText());
        JOptionPane.showMessageDialog(panel,"更新设置成功");
    }
}
