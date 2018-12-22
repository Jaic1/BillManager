package GUI.panel;

import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;

public class recoverPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static recoverPanel instance = new recoverPanel();

    public JButton b = new JButton("恢复");

    private recoverPanel(){
        GUIUtil.setColor(colorUtil.blueColor, b);
        b.setBounds((getWidth()- b.getWidth())/2,(getHeight()- b.getHeight())/2,
                b.getWidth(), b.getHeight());
        this.add(b);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(recoverPanel.instance, 0.5f);
    }
}
