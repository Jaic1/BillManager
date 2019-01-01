package GUI.panel;

import GUI.listener.RecoverListener;
import utils.GUIUtil;
import utils.colorUtil;

import javax.swing.*;

public class recoverPanel extends workingPanelAbstractClass {
    static {
        GUIUtil.useLNF();
    }
    public static recoverPanel instance = new recoverPanel();

    public JButton b = new JButton("恢复");

    private recoverPanel(){
        GUIUtil.setColor(colorUtil.blueColor, b);
        this.add(b);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener recoverListener = new RecoverListener();
        b.addActionListener(recoverListener);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(recoverPanel.instance, 0.5f);
    }
}
