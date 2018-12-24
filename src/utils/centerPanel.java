package utils;

import GUI.panel.workingPanel;

import javax.swing.*;
import java.awt.*;

public class centerPanel extends JPanel {
    //居中面板，可以使得加入的组件居中显示

    private double rate;
    private JComponent component;
    private boolean stretch;

    public centerPanel(double rate, boolean stretch) {
        this.setLayout(null);
        this.rate = rate;
        this.stretch = stretch;
    }

    public centerPanel(double rate) {
        this(rate, true);
    }

    public void repaint() {
        if (component != null) {
            Dimension panelSize = this.getSize();
            Dimension componentSize = component.getPreferredSize();
            if (stretch) {
                componentSize.width = (int) (panelSize.width * rate);
                componentSize.height = (int) (panelSize.height * rate);
            }
            component.setSize(componentSize);
            component.setLocation(panelSize.width / 2 - componentSize.width / 2,
                    panelSize.height / 2 - componentSize.height / 2);
        }
        super.repaint();
    }

    public void show(JComponent component) {
        this.component = component;
        Component[] components = getComponents();
        for (Component componentTemp : components) {
            remove(componentTemp);
        }
        add(this.component);
        if(component instanceof workingPanel){
            ((workingPanel)component).updateData();
        }
        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(500, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerPanel testInstance = new centerPanel(0.8,false);
        f.setContentPane(testInstance);
        f.setVisible(true);

        JButton b = new JButton("just for test");
        testInstance.show(b);
    }
}