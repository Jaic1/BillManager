package GUI.panel;

import GUI.listener.BackupListener;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class backupPanel extends workingPanelAbstractClass {
    static {
        GUIUtil.useLNF();
    }
    public static backupPanel instance = new backupPanel();

    JButton b = new JButton("备份");

    private backupPanel(){
        this.add(b);
        GUIUtil.setColor(colorUtil.blueColor, b);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener backupListener = new BackupListener();
        b.addActionListener(backupListener);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(backupPanel.instance,0.5f);
    }

}
