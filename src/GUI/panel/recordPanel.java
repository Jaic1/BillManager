package GUI.panel;

import GUI.model.categoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class recordPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static recordPanel instance = new recordPanel();

    public JLabel lSpend = new JLabel("金额");
    public JLabel lCategory = new JLabel("类别");
    public JLabel lComment = new JLabel("备注");
    public JLabel lDate = new JLabel("日期");

    public JTextField spend = new JTextField();
    public categoryComboBoxModel categoryModel = new categoryComboBoxModel();
    public JComboBox<String> categoryBox = new JComboBox<>(categoryModel);
    public JTextField comment = new JTextField();
    public JXDatePicker datepick = new JXDatePicker(new Date());
    public JButton b = new JButton("记一下");

    private recordPanel(){
        this.setLayout(new BorderLayout());
        this.add(input(), BorderLayout.CENTER);
        this.add(submit(), BorderLayout.SOUTH);
    }

    private JPanel input(){
        GUIUtil.setColor(colorUtil.geryColor,lSpend,lCategory,lComment,lDate);
        JPanel p = new JPanel();
        int gap = 40;
        p.setLayout(new GridLayout(4,2,gap,gap));

        p.add(lSpend);
        p.add(spend);
        p.add(lCategory);
        p.add(categoryBox);
        p.add(lComment);
        p.add(comment);
        p.add(lDate);
        p.add(datepick);
        return p;
    }
    private JPanel submit(){
        GUIUtil.setColor(colorUtil.blueColor, b);
        JPanel p = new JPanel();
        p.add(b);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(recordPanel.instance, 1.0f);
    }
}