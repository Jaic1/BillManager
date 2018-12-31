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
//        ConfigService cs = new ConfigService();
//        cs.reset();
//        panel.tBudget.setText(ConfigService.budgetDefaultValue);
//        panel.bSafe.setSelected(true);
//        panel.tMysqlPath.setText(ConfigService.mysqlPathDefaultValue);
        ConfigService.reset();
        panel.updateData();
        JOptionPane.showMessageDialog(panel,"重置成功");
    }
}
