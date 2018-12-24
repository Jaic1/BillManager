package GUI.listener;

import GUI.panel.recordPanel;
import utils.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        recordPanel panel = recordPanel.instance;
        if(!GUIUtil.checkEmpty(panel.spend,panel.lSpend.getName()))return;
    }
}
