package GUI.panel;

import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class configPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static configPanel instance = new configPanel();

    JLabel lBudget = new JLabel("每月预算");
    JTextField tBudget = new JTextField();
    JLabel lMode = new JLabel("模式设置");
    JRadioButton bSafe = new JRadioButton("安全模式");
    JRadioButton bFree = new JRadioButton("自由模式");
    JLabel lMysqlPath = new JLabel("Mysql路径");
    JTextField tMysqlPath = new JTextField();
    JButton bUpdate = new JButton("更新");

    private configPanel(){
        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.CENTER);
        this.add(south(),BorderLayout.SOUTH);
    }

    private JPanel center(){
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(1,2));
        ButtonGroup bg = new ButtonGroup();
        bg.add(bSafe);
        bg.add(bFree);
        bp.add(bSafe);
        bp.add(bFree);

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
        GUIUtil.setColor(colorUtil.blueColor,bUpdate);
        JPanel p = new JPanel();
        p.add(bUpdate);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(configPanel.instance,1.0f);
    }
}
