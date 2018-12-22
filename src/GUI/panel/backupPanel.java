package GUI.panel;

import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;

public class backupPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static backupPanel instance = new backupPanel();

    JButton b = new JButton("备份");

    private backupPanel(){
        GUIUtil.setColor(colorUtil.blueColor, b);
        b.setBounds((getWidth()- b.getWidth())/2,(getHeight()- b.getHeight())/2,
                b.getWidth(), b.getHeight());
        this.add(b);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(backupPanel.instance,0.5f);
    }
}
