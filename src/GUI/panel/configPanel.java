package GUI.panel;

import GUI.listener.ConfigResetListener;
import GUI.listener.ConfigUpdateListener;
import Service.ConfigService;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class configPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static configPanel instance = new configPanel();

    public JLabel lBudget = new JLabel("每月预算");
    public JTextField tBudget = new JTextField(ConfigService.budgetValue);
    public JLabel lMode = new JLabel("模式设置");
    public JRadioButton bSafe = new JRadioButton("安全模式");
    public JRadioButton bFree = new JRadioButton("自由模式");
    public JLabel lMysqlPath = new JLabel("Mysql路径");
    public JTextField tMysqlPath = new JTextField(ConfigService.mysqlPathValue);
    JButton bReset = new JButton("重置");
    JButton bUpdate = new JButton("更新");

    private configPanel(){
        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.CENTER);
        this.add(south(),BorderLayout.SOUTH);
        addListener();
    }

    private JPanel center(){
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(1,2));
        ButtonGroup bg = new ButtonGroup();
        bg.add(bSafe);
        bg.add(bFree);
        bp.add(bSafe);
        bp.add(bFree);
        if(ConfigService.modeValue.equals("安全模式")){
            bSafe.setSelected(true);
        }else bFree.setSelected(true);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6,1,20,20));
        p.add(lBudget);
        p.add(tBudget);
        p.add(lMode);
        p.add(bp);
        p.add(lMysqlPath);
        p.add(tMysqlPath);
        return p;
    }
    private JPanel south(){
        GUIUtil.setColor(colorUtil.blueColor,bReset);
        GUIUtil.setColor(colorUtil.blueColor,bUpdate);
        JPanel p = new JPanel();
        p.add(bReset);
        p.add(bUpdate);
        return p;
    }

    private void addListener(){
        ConfigResetListener configResetListener = new ConfigResetListener();
        bReset.addActionListener(configResetListener);
        ConfigUpdateListener configUpdateListener = new ConfigUpdateListener();
        bUpdate.addActionListener(configUpdateListener);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(configPanel.instance,1.0f);
    }
}
