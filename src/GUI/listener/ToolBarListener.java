package GUI.listener;

import GUI.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        mainPanel p = mainPanel.instance;
        JButton button = (JButton) e.getSource();

        //可以把updateDate方法合并到centerPanel的show方法中
        if(button == p.bOverview){
            p.workingPanel.show(overviewPanel.instance);
        }
        if(button == p.bRecord){
            p.workingPanel.show(recordPanel.instance);
        }
        if(button == p.bCategory){
//            categoryPanel.instance.updateData();
            p.workingPanel.show(categoryPanel.instance);
        }
        if(button == p.bReport){
            p.workingPanel.show(reportPanel.instance);
        }
        if(button == p.bConfig){
//            configPanel.instance.updateData();
            p.workingPanel.show(configPanel.instance);
        }
        if(button == p.bBackup){
            p.workingPanel.show(backupPanel.instance);
        }
        if(button == p.bRecover){
            p.workingPanel.show(recoverPanel.instance);
        }
    }
}
