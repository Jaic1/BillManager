package GUI.panel;

import GUI.listener.ToolBarListener;
import utils.GUIUtil;
import utils.centerPanel;

import javax.swing.*;
import javax.swing.plaf.ToolBarUI;
import java.awt.*;

public class mainPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static mainPanel instance = new mainPanel();
    public JToolBar toolBar = new JToolBar();
    public JButton bOverview = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();
    public centerPanel workingPanel = new centerPanel(0.85);

    private mainPanel(){
        GUIUtil.setImgIcon(bOverview,"home.png","血腥场面");
        GUIUtil.setImgIcon(bRecord,"record.png","剁一只");
        GUIUtil.setImgIcon(bCategory,"category2.png","消费分类");
        GUIUtil.setImgIcon(bReport,"report.png","报表");
        GUIUtil.setImgIcon(bConfig,"config.png","设置");
        GUIUtil.setImgIcon(bBackup,"backup.png","备份");
        GUIUtil.setImgIcon(bRecover,"restore.png","恢复");

        toolBar.add(bOverview);
        toolBar.add(bRecord);
        toolBar.add(bCategory);
        toolBar.add(bReport);
        toolBar.add(bConfig);
        toolBar.add(bBackup);
        toolBar.add(bRecover);
        toolBar.setFloatable(false);

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);
        addListener();
    }

    private void addListener(){
        ToolBarListener toolBarListener = new ToolBarListener();
        bOverview.addActionListener(toolBarListener);
        bRecord.addActionListener(toolBarListener);
        bCategory.addActionListener(toolBarListener);
        bRecord.addActionListener(toolBarListener);
        bConfig.addActionListener(toolBarListener);
        bBackup.addActionListener(toolBarListener);
        bRecover.addActionListener(toolBarListener);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(mainPanel.instance,1);
    }
}
