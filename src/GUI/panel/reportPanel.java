package GUI.panel;

import utils.GUIUtil;
import utils.chartUtil;

import javax.swing.*;
import java.awt.*;

public class reportPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static reportPanel instance = new reportPanel();

    public JLabel label = new JLabel();

    private reportPanel(){
        this.setLayout(new BorderLayout());
        Image img = chartUtil.getImage(350,300);
        label.setIcon(new ImageIcon(img));
        this.add(label,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        GUIUtil.testComponent(reportPanel.instance,1.0f);
    }
}
