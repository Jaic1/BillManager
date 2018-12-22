package GUI.panel;

import GUI.model.categoryTableModel;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class categoryPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static categoryPanel instance = new categoryPanel();

    public categoryTableModel category = new categoryTableModel();
    public JTable table = new JTable(category);
    public JButton bAdd = new JButton("添加");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    private categoryPanel(){
        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    private JScrollPane center(){
        JScrollPane sp = new JScrollPane(table);
        return sp;
    }
    private JPanel south(){
        GUIUtil.setColor(colorUtil.blueColor,bAdd,bEdit,bDelete);
        JPanel p = new JPanel();
        p.add(bAdd);
        p.add(bEdit);
        p.add(bDelete);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(categoryPanel.instance,1.0f);
    }
}
